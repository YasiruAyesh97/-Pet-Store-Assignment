package com.example.petstore;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "PetType")
public class PetType {


    @Schema(required = true, description = "Pet type id")
    @JsonProperty("pet_type_id")
    private Integer petTypeId;

    @Schema(required = true, description = "Pet type name")
    @JsonProperty("pet_type_name")
    private String petTypeName;



    @Schema(required = true, description = "Pet type quantity")
    @JsonProperty("pet_type_qty")
    private Integer petTypeQty;


    public Integer getPetTypeId() {
        return petTypeId;
    }

    public void setPetTypeId(Integer petTypeId) {
        this.petTypeId = petTypeId;
    }

    public String getPetTypeName() {
        return petTypeName;
    }

    public void setPetTypeName(String petTypeName) {
        this.petTypeName = petTypeName;
    }


    public Integer getPetTypeQty() {
        return petTypeQty;
    }

    public void setPetTypeQty(Integer petTypeQty) {
        this.petTypeQty = petTypeQty;
    }


}
