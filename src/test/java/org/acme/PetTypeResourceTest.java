package org.acme;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class PetTypeResourceTest {

//    @Test
//    @Order(1)
//    public void testPetTypeEndpoint() {
//        given()
//                .when().get("/petTypes")
//                .then()
//                .statusCode(200);


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
  //  }

//    @Test
//    @Order(2)
//    public void testPetTypeAddEndPointSuccess(){
//        given()
//                .header("Content-Type","application/json")
//                .body("{\r\n   \"type_name\":\"Snakes\",\r\n   \"type_qty\":4,\r\n }")
//                .when().post("/petTypes/add")
//                .then()
//                .assertThat()
//                .statusCode(200)
//                .body("petTypeId",notNullValue())
//                .body("petTypeName",notNullValue())
//                .body("petTypeQty",notNullValue());
//
//    }

//    @Test
//    public void testPetTypeUpdateEndPoint(){
//        given()
//                .header("Content-Type","application/json")
//                .pathParam("petTypeId",1)
//                .body("{\n\"type_name\":\"Elephant\"\n}")
//                .when().put("/petTypes/edit/{petTypeId}")
//                .then()
//                .statusCode(200)
//                .body("petTypeId",equalTo(1))
//                .body("petTypeName",equalTo("Elephant"))
//                .body("petTypeQty",notNullValue());
//
//    }


//    @Test
//    public void testPetDeleteEndPoint(){
//        given()
//                .header("Content-Type","application/json")
//                .pathParam("petId",1)
//                .when().delete("/pets/delete/{petId}")
//                .then()
//                .statusCode(200)
//                .body("successful",equalTo(true));
//    }

}
