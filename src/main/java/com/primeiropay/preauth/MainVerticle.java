package com.primeiropay.preauth;

import com.primeiropay.preauth.web.HTTPServerVerticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class MainVerticle extends AbstractVerticle {

	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		vertx.deployVerticle(new HTTPServerVerticle(8080));
	}
}
