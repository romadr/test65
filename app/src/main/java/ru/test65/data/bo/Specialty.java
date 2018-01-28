package ru.test65.data.bo;


import com.google.gson.annotations.SerializedName;

public class Specialty {

    @SerializedName("specialty_id")
    private Long specialtyId;

    private String name;

    public Long getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(Long specialtyId) {
        this.specialtyId = specialtyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
