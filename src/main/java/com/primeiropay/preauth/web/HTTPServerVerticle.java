package com.primeiropay.preauth.web;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.io.IOUtils;

import com.primeiropay.preauth.model.AuthRequestModel;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class HTTPServerVerticle extends AbstractVerticle {

    private final Logger LOGGER = LoggerFactory.getLogger(HTTPServerVerticle.class);

    private final JsonObject configuration;
    
    public HTTPServerVerticle(final JsonObject configuration) {
        this.configuration = configuration;
    }
    
    @Override
    public void start(final Future<Void> startFuture) {
    	final HttpServer httpServer = vertx.createHttpServer();

    	final Router router =  Router.router(vertx);
    	router.post().handler(BodyHandler.create());
    	router.post("/pre-auth").handler(this::doPreAuth);
    	
    	final Integer port = getServerPort();
    	httpServer.requestHandler(router)
    	.listen(port, listenHandler -> {
    		if(listenHandler.failed()) {
    			LOGGER.error("HTTP Server error", listenHandler.cause());
    			return;
    		}
    		LOGGER.info("HTTP Server started on port " + port);
    	});
    }

    private Integer getServerPort() {
        return configuration.getInteger("server.port");
    }
    
    private void doPreAuth(RoutingContext rc) {
        AuthRequestModel authRquest = rc.getBodyAsJson().mapTo(AuthRequestModel.class);

        String response = "";
        try {
			response = postAuth(authRquest.toString(), rc.request().getHeader("Authorization"));
		} catch (IOException e) {
			LOGGER.error("HTTP Post error", e.getCause());
		}
        
        rc.response()
            .setStatusCode(201)
            .putHeader("content-type", 
              "application/json; charset=utf-8")
            .end(response);
    }
    
    private String postAuth(String payload, String authorization) throws IOException {
    	URL url = new URL("https://test.oppwa.com/v1/payments");

    	HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
    	conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", authorization);
    	conn.setDoInput(true);
    	conn.setDoOutput(true);

    	DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
    	wr.writeBytes(payload);
    	wr.flush();
    	wr.close();
    	int responseCode = conn.getResponseCode();
    	InputStream is;

    	if (responseCode >= 400) is = conn.getErrorStream();
    	else is = conn.getInputStream();

    	return IOUtils.toString(is);
    }
}
