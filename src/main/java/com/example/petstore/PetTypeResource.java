package com.example.petstore;


import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.json.JSONException;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/petTypes")
@Produces("application/json")
public class PetTypeResource {

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "All Pets", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "PetType"))) })


    @GET
    public Response getPetsType() {

        return Response.ok(PetTypeDataSet.getInstance().getArrayList()).build();
    }


    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Pet Type for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "PetType"))),
            @APIResponse(responseCode = "404", description = "No Pet Type found for the id.") })



    @GET
    @Path("{petTypeId}")
    public Response getPet(@PathParam("petTypeId") int petTypeId) {
        if (petTypeId < 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        PetType petTypeDetails = new PetType();
        petTypeDetails.setPetTypeId(petTypeId);
        petTypeDetails.setPetTypeName("Dog");

        return Response.ok(petTypeDetails).build();

    }



    @Path("/add")
    @Produces("application/json")
    @POST
    public Response addPetType(String request) throws JSONException {
        JSONObject jsonObject = new JSONObject(request);
        if(jsonObject.has("type_name") && jsonObject.has("type_qty")){
            PetType petTypeData= new PetType();
            petTypeData.setPetTypeName(jsonObject.getString("type_name"));
            petTypeData.setPetTypeQty(Integer.parseInt(jsonObject.getString("type_qty")));


            petTypeData.setPetTypeId(PetTypeDataSet.getInstance().getArrayList().get(PetTypeDataSet.getInstance().getArrayList().size()-1).getPetTypeId()+1);
            List<PetType> petList= new ArrayList<PetType>();
            List<PetType> temp = new ArrayList<PetType>();

            petList.add(petTypeData);
            temp.addAll(PetTypeDataSet.getInstance().getArrayList());
            temp.addAll(petList);
            PetTypeDataSet.getInstance().setArrayList(temp);

            return Response.ok(petList).build();

        }else{
            return Response.ok("Error").build();
        }
    }



    @Path("/search")
    @Produces("application/json")
    @GET
    public Response searchPetType(@DefaultValue("-1") @QueryParam("type_id") int petTypeId,
                              @DefaultValue("null") @QueryParam("type_name") String petTypeName,
                              @DefaultValue("0") @QueryParam("type_qty") int petTypeQty){
        boolean isPetFound = false;
        int id = 0;
        if(petTypeId != -1 && petTypeName.equals("null") && petTypeQty == 0){
            if (petTypeId < 0) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            for (int i=0;i<PetTypeDataSet.getInstance().getArrayList().size();i++){
                if(petTypeId == PetTypeDataSet.getInstance().getArrayList().get(i). getPetTypeId()){
                    isPetFound = true;
                    id = i;
                }
            }
            if(isPetFound){
                return Response.ok(PetTypeDataSet.getInstance().getArrayList().get(id)).build();
            }else{
                return Response.ok("There is not available pet type with id = "+petTypeId).build();
            }
        }else if(petTypeId == -1 && !petTypeName.equals("null") && petTypeQty == 0){
            for (int i=0;i<PetTypeDataSet.getInstance().getArrayList().size();i++){
                if(petTypeName.equals(PetTypeDataSet.getInstance().getArrayList().get(i).getPetTypeName())){
                    isPetFound = true;
                    id = i;
                }
            }
            if(isPetFound){
                return Response.ok(PetTypeDataSet.getInstance().getArrayList().get(id)).build();
            }else{
                return Response.ok("There is not available pet type with name = "+petTypeName).build();
            }
        }else if(petTypeId == -1 && petTypeName.equals("null") && petTypeQty != 0){
            List<PetType> temp = new ArrayList<PetType>();
            for (int i=0;i<PetTypeDataSet.getInstance().getArrayList().size();i++){
                if(petTypeQty == PetTypeDataSet.getInstance().getArrayList().get(i).getPetTypeQty()){
                    isPetFound = true;
                    id = i;
                    temp.add(PetTypeDataSet.getInstance().getArrayList().get(id));
                }
            }
            if(isPetFound){
                return Response.ok(temp).build();
            }else{
                return Response.ok("There is not available pet with pet type quantity = "+petTypeQty).build();
            }
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }


    }


    @DELETE
    @Produces("application/json")
    @Path("/delete/{petTypeId}")
    public Response deletePetType(@PathParam("petTypeId") int petTypeId){
        boolean isPetFound = false;
        for (int i=0;i<PetTypeDataSet.getInstance().getArrayList().size();i++){
            if(petTypeId == PetTypeDataSet.getInstance().getArrayList().get(i).getPetTypeId()){
                PetTypeDataSet.getInstance().getArrayList().remove(i);
                isPetFound = true;
            }
        }
        if(isPetFound){
            return Response.ok("{\n" + "\"successful\":true\n" + "}").build();
        }else{
            return Response.ok("{\n" + "\"successful\":false\n" + "}").build();
        }

    }




    @PUT
    @Produces("application/json")
    @Path("/edit/{petTypeId}")
    public Response editPetType(@PathParam("petTypeId") int petId,String petData) throws JSONException {
        JSONObject jsonObject = new JSONObject(petData);
        boolean isUpdated = false;
        int id = 0;
        if(jsonObject.has("type_name")){
            for (int i=0;i<PetTypeDataSet.getInstance().getArrayList().size();i++){
                if(petId == PetTypeDataSet.getInstance().getArrayList().get(i).getPetTypeId()){
                    PetTypeDataSet.getInstance().getArrayList().get(i).setPetTypeName(jsonObject.getString("type_name"));
                    isUpdated=true;
                    id = i;
                }
            }
        }
        if(jsonObject.has("type_qty")){
            for (int i=0;i<PetTypeDataSet.getInstance().getArrayList().size();i++){
                if(petId == PetTypeDataSet.getInstance().getArrayList().get(i).getPetTypeId()){
                    PetTypeDataSet.getInstance().getArrayList().get(i).setPetTypeQty(Integer.parseInt(jsonObject.getString("type_qty")));
                    isUpdated=true;
                    id = i;
                }
            }
        }

        if(isUpdated){
            return Response.ok(PetTypeDataSet.getInstance().getArrayList().get(id)).build();
        }else{
            return Response.ok("{\n" + "\"success\":false\n" + "}").build();
        }

    }





}
