package ru.test65.data.bo;


import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Workman {

    private Long id;

    @SerializedName("f_name")
    private String fName;

    @SerializedName("l_name")
    private String lName;

    private Date birthday;

    @SerializedName("avatr_url")
    private String avatrUrl;

    private Specialty specialty;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAvatrUrl() {
        return avatrUrl;
    }

    public void setAvatrUrl(String avatrUrl) {
        this.avatrUrl = avatrUrl;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }
}
