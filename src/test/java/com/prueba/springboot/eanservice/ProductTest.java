package com.prueba.springboot.eanservice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ProductTest {

	@BeforeAll
	public static void setUp() {
		RestAssured.baseURI = "http://localhost:8080/products";
	}

	@Test
	public void testGetProductInfoByEAN() {
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.when()
				.get("/search/8437008000024")
				.then()
				.extract().response();

		Assertions.assertEquals(200, response.statusCode());
		Assertions.assertEquals(2, response.jsonPath().getInt("id"));
		Assertions.assertEquals("Pan de molde", response.jsonPath().getString("name"));
		Assertions.assertEquals(3.10, response.jsonPath().getDouble("price"));
		Assertions.assertEquals("Hacendado", response.jsonPath().getString("supplier"));
		Assertions.assertEquals("Mercadona España", response.jsonPath().getString("destination"));
	}

	@Test
	public void testGetProductInfoByEANWrongLength() {
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.when()
				.get("/search/843700800002")
				.then()
				.extract().response();

		Assertions.assertEquals(400, response.statusCode());
		Assertions.assertEquals("El código EAN debe tener 13 digitos", response.jsonPath().getString("message"));
	}

	@Test
	public void testGetProductInfoByEANWrongCode() {
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.when()
				.get("/search/8437908000024")
				.then()
				.extract().response();

		Assertions.assertEquals(404, response.statusCode());
		Assertions.assertEquals("Código EAN incorrecto", response.jsonPath().getString("message"));
	}

	@Test
	public void testGetProductInfoByEANWrongDestination() {
		Response response = RestAssured.given()
				.contentType(ContentType.JSON)
				.when()
				.get("/search/8437008000027")
				.then()
				.extract().response();

		Assertions.assertEquals(400, response.statusCode());
		Assertions.assertEquals("Código de destino incorrecto", response.jsonPath().getString("message"));
	}

}
