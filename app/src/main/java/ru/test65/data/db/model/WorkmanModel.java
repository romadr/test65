package ru.test65.data.db.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.NotNull;

@Entity
public class WorkmanModel {

    @Id(autoincrement = true)
    private Long id;

    private String fName;
    private String lName;
    private Date birthday;

    private String avatrUrl;

    private long specialtyId;

    @ToOne(joinProperty = "specialtyId")
    private SpecialtyModel specialty;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 693118420)
    private transient WorkmanModelDao myDao;

    @Generated(hash = 243899894)
    public WorkmanModel(Long id, String fName, String lName, Date birthday,
            String avatrUrl, long specialtyId) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.birthday = birthday;
        this.avatrUrl = avatrUrl;
        this.specialtyId = specialtyId;
    }

    @Generated(hash = 795042314)
    public WorkmanModel() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFName() {
        return this.fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return this.lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAvatrUrl() {
        return this.avatrUrl;
    }

    public void setAvatrUrl(String avatrUrl) {
        this.avatrUrl = avatrUrl;
    }

    public long getSpecialtyId() {
        return this.specialtyId;
    }

    public void setSpecialtyId(long specialtyId) {
        this.specialtyId = specialtyId;
    }

    @Generated(hash = 2048110654)
    private transient Long specialty__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 306311700)
    public SpecialtyModel getSpecialty() {
        long __key = this.specialtyId;
        if (specialty__resolvedKey == null
                || !specialty__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            SpecialtyModelDao targetDao = daoSession.getSpecialtyModelDao();
            SpecialtyModel specialtyNew = targetDao.load(__key);
            synchronized (this) {
                specialty = specialtyNew;
                specialty__resolvedKey = __key;
            }
        }
        return specialty;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 965663349)
    public void setSpecialty(@NotNull SpecialtyModel specialty) {
        if (specialty == null) {
            throw new DaoException(
                    "To-one property 'specialtyId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.specialty = specialty;
            specialtyId = specialty.getSpecialtyId();
            specialty__resolvedKey = specialtyId;
        }
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1632088745)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getWorkmanModelDao() : null;
    }


}
