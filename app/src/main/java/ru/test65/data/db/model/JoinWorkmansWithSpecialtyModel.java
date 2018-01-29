package ru.test65.data.db.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class JoinWorkmansWithSpecialtyModel {

    @Id
    private Long id;
    private Long workmanId;
    private Long specialtyId;

    @Generated(hash = 1181477995)
    public JoinWorkmansWithSpecialtyModel(Long id, Long workmanId,
                                          Long specialtyId) {
        this.id = id;
        this.workmanId = workmanId;
        this.specialtyId = specialtyId;
    }

    @Generated(hash = 1259173491)
    public JoinWorkmansWithSpecialtyModel() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWorkmanId() {
        return this.workmanId;
    }

    public void setWorkmanId(Long workmanId) {
        this.workmanId = workmanId;
    }

    public Long getSpecialtyId() {
        return this.specialtyId;
    }

    public void setSpecialtyId(Long specialtyId) {
        this.specialtyId = specialtyId;
    }

}
