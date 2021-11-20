package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.allOf;

@QuarkusTest

public class PetResourceTest {

	@Test
    @Order(1)
    public void testPetEndpoint() {
        given()
          .when().get("/pets/all")
          .then()
             .statusCode(200);
    }

    @Test
    @Order(2)
    public void testPetsAddEndPointSuccess(){
        given()
                .header("Content-Type","application/json")
                .body("{\r\n    \"name\":\"Pigeon \",\r\n   \"age\":1,\r\n   \"type\":\"Bird\"\r\n}")
                .when().post("/pets/add")
                .then()
                .assertThat()
                .statusCode(200)
                .body("petId",notNullValue())
                .body("petAge",equalTo(1))
                .body("petName",equalTo("pigeon "))
                .body("petType",notNullValue());




    }

    @Test
    public void testPetUpdateEndPoint(){
        given()
                .header("Content-Type","application/json")
		.body("{\r\n    \"name\":\"Pigeon \",\r\n   \"id\":1,\r\n   \"age\":1,\r\n   \"type\":\"Bird\"\r\n}")
                .when().put("/pets/edit/{petId}")
                .then()
                .statusCode(200)
                .body("petId",equalTo(1))
                .body("petAge",notNullValue())
                .body("petName",equalTo("Pigeon"))
                .body("petType",notNullValue());

    }


    @Test
    public void testPetDeleteEndPoint(){
        given()
                .header("Content-Type","application/json")
                .pathParam("petId",1)
                .when().delete("/pets/delete/{petId}")
                .then()
                .statusCode(200)
                .body("successful",equalTo(true));
    }

}
