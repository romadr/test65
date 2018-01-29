package ru.test65.data.db.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class SpecialtyModel {

    @Id
    private Long specialtyId;

    private String name;

    @Generated(hash = 836427693)
    public SpecialtyModel(Long specialtyId, String name) {
        this.specialtyId = specialtyId;
        this.name = name;
    }

    @Generated(hash = 2055341918)
    public SpecialtyModel() {
    }

    public Long getSpecialtyId() {
        return this.specialtyId;
    }

    public void setSpecialtyId(Long specialtyId) {
        this.specialtyId = specialtyId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
