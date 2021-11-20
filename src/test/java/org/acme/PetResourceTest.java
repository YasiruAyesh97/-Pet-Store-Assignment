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
          .when().get("/pets")
          .then()
             .statusCode(200);


//        given()
//                .when().get("/add")
//                .then()
//                .statusCode(200);



//             .body(hasItem(
// 		            allOf(
//    		                hasEntry("pet_id", "1"),
//    		                hasEntry("pet_type", "Dog"),
//    		                hasEntry("pet_name", "Boola"),
//    		                hasEntry("pet_age", "3")
//    		            )
//    		      )
//    		 );
    }

    @Test
    @Order(2)
    public void testPetsAddEndPointSuccess(){
        given()
                .header("Content-Type","application/json")
                .body("{\r\n    \"name\":\"Dane\",\r\n   \"age\":4,\r\n   \"type\":\"Bird\"\r\n}")
                .when().post("/pets/add")
                .then()
                .assertThat()
                .statusCode(200)
                .body("petId",notNullValue())
                .body("petAge",equalTo(4))
                .body("petName",equalTo("Dane"))
                .body("petType",notNullValue());




    }

    @Test
    public void testPetUpdateEndPoint(){
        given()
                .header("Content-Type","application/json")
                .pathParam("petId",1)
                .body("{\n\"name\":\"Poppy\"\n}")
                .when().put("/pets/edit/{petId}")
                .then()
                .statusCode(200)
                .body("petId",equalTo(1))
                .body("petAge",notNullValue())
                .body("petName",equalTo("Poppy"))
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