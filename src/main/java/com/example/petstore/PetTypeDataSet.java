package com.example.petstore;

import java.util.ArrayList;
import java.util.List;

public class PetTypeDataSet {


    private static PetTypeDataSet myInstance;
    public List<PetType> petsType = new ArrayList<PetType>();
    PetType petType1 = new PetType();
    PetType petType2 = new PetType();
    PetType petType3 = new PetType();

    private PetTypeDataSet() {
        petType1.setPetTypeId(1);
        petType1.setPetTypeName("Dog");
        petType1.setPetTypeQty(10);

        petType2.setPetTypeId(2);
        petType2.setPetTypeName("Cat");
        petType2.setPetTypeQty(20);


        petType3.setPetTypeId(3);
        petType3.setPetTypeName("Bird");
        petType3.setPetTypeQty(30);



        petsType.add(petType1);
        petsType.add(petType2);
        petsType.add(petType3);
    }
    public static PetTypeDataSet getInstance() {
        if (myInstance == null)
            myInstance = new PetTypeDataSet();
        return myInstance;
    }
    public void setArrayList(List<PetType> petsType) {
        this.petsType = petsType;
    }

    public List<PetType> getArrayList() {
        return this.petsType;

    }

}
