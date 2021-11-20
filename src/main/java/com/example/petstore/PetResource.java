package com.example.petstore;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONException;
import org.json.JSONObject;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/pets")
@Produces("application/json")
public class PetResource {

	//get All
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "All Pets", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))) })
	@GET
	@Path("all")
	public Response getPets() {

		return Response.ok(PetDataSet.getInstance().getArrayList()).build();
	}

	// get pet by id
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Pet for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the id.") })
	@GET
	@Path("{petId}")
	public Response getPet(@PathParam("petId") int petId) {
		if (petId < 0) {
			return Response.status(Status.NOT_FOUND).build();
		}
		Pet pet = new Pet();
		pet = PetDataSet.getInstance().getPet(petId);
		return Response.ok(pet).build();

	}


	// Add new pet
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Added new pets", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the id.") })
	@POST
	@Path("add")
	@Produces("application/json")
	public Response addPet(String request)  throws JSONException {
		JSONObject insertObject = new JSONObject(request);
		ArrayList<Pet> pets = new ArrayList<Pet>();
		if( insertObject.has("petName") && insertObject.has("petType") && insertObject.has("petAge")){

			//{"petName":"Boola","petAge":10,"petId":1,"petType":"Dog"}
			Pet insert_pet =new Pet();
			insert_pet.setPetId(Integer.parseInt(insertObject.getString("petId")));
			insert_pet.setPetAge(Integer.parseInt(insertObject.getString("petAge")));
			insert_pet.setPetName(insertObject.getString("petName"));
			insert_pet.setPetType(insertObject.getString("petType"));
			pets = PetDataSet.getInstance().addPets(insert_pet);
			return Response.ok(pets).build();
		}else{
			return Response.ok("Fail").build();

		}
	}


	@Path("/search")
	@Produces("application/json")
	@GET
	public Response searchPet(@DefaultValue("-1") @QueryParam("id") int petId,
							  @DefaultValue("null") @QueryParam("name") String petName,
							  @DefaultValue("0") @QueryParam("age") int petAge){
		boolean isPetFound = false;
		int id = 0;
		if(petId != -1 && petName.equals("null") && petAge == 0){
			if (petId < 0) {
				return Response.status(Status.NOT_FOUND).build();
			}

			for (int i=0;i<PetDataSet.getInstance().getArrayList().size();i++){
				if(petId == PetDataSet.getInstance().getArrayList().get(i).getPetId()){
					isPetFound = true;
					id = i;
				}
			}
			if(isPetFound){
				return Response.ok(PetDataSet.getInstance().getArrayList().get(id)).build();
			}else{
				return Response.ok("No pet with id = "+petId).build();
			}
		}else if(petId == -1 && !petName.equals("null") && petAge == 0){
			for (int i=0;i<PetDataSet.getInstance().getArrayList().size();i++){
				if(petName.equals(PetDataSet.getInstance().getArrayList().get(i).getPetName())){
					isPetFound = true;
					id = i;
				}
			}
			if(isPetFound){
				return Response.ok(PetDataSet.getInstance().getArrayList().get(id)).build();
			}else{
				return Response.ok("Not pet with name = "+petName).build();
			}
		}else if(petId == -1 && petName.equals("null") && petAge != 0){
			List<Pet> temp = new ArrayList<Pet>();
			for (int i=0;i<PetDataSet.getInstance().getArrayList().size();i++){
				if(petAge == PetDataSet.getInstance().getArrayList().get(i).getPetAge()){
					isPetFound = true;
					id = i;
					temp.add(PetDataSet.getInstance().getArrayList().get(id));
				}
			}
			if(isPetFound){
				return Response.ok(temp).build();
			}else{
				return Response.ok("No pet with age = "+petAge).build();
			}
		}else{
			return Response.status(Status.NOT_FOUND).build();
		}


	}

	// delete pet
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Deleted pet", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the id.") })
	@DELETE
	@Produces("application/json")
	@Path("delete/{petId}")
	public Response deletePet(@PathParam("petId") int petId) {
		ArrayList<Pet> pets = new ArrayList<Pet>();
		pets = PetDataSet.getInstance().deletePet(petId);
		return Response.ok(pets).build();

	}




	// update pet
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Updated pet details", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
			@APIResponse(responseCode = "404", description = "No Pet found for the id.") })
	@POST
	@Path("/update")
	@Produces("application/json")
	public Response updatePet(String request) throws JSONException{
		JSONObject updateObject = new JSONObject(request);
		//{"petName":"Boola","petAge":10,"petId":1,"petType":"Dog"}
		Pet pet =new Pet();
		pet.setPetId(Integer.parseInt(updateObject.getString("petId")));
		pet.setPetAge(Integer.parseInt(updateObject.getString("petAge")));
		pet.setPetName(updateObject.getString("petName"));
		pet.setPetType(updateObject.getString("petType"));

		ArrayList<Pet> pets = new ArrayList<Pet>();
		pets = PetDataSet.getInstance().updatePet(pet);
		return Response.ok(pets).build();

	}



}
