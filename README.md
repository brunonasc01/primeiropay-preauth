# Preauth
image:https://img.shields.io/badge/vert.x-3.9.1-purple.svg[link="https://vertx.io"]

This application was generated using http://start.vertx.io

## Building

To package your application:
```
./mvnw clean package
```
To run your application:
```
./mvnw clean compile exec:java
```

## Summary
This microservice offer an endpoint for PrimeiroPay Pre Auth service

**URL** : `/pre-auth`

**Method** : `POST`

**Auth required** : Beater

Provide entity_id, amount and card data to do the request.
```json
{
	"entity_id": "[hash key]",
	"amount":"[number]",
	"currency":"[string]",
	"paymentBrand":"[string]",
	"paymentType":"[string]",
	"card": {
	"number":"[number]",
	"holder":"[string]",
	"expiryMonth":"[number]",
	"expiryYear":"[number]",
	"cvv":"[number]"
	}
}
```
**Data example** All fields must be sent.
```json
{
	"entity_id": "XYZ1234567890",
	"amount":"92.00",
	"currency":"EUR",
	"paymentBrand":"VISA",
	"paymentType":"DB",
	"card": {
	"number":"4200000000000000",
	"holder":"Jane Jones",
	"expiryMonth":"05",
	"expiryYear":"2020",
	"cvv":"123"
	}
}
```