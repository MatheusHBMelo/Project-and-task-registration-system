package model;

import java.util.Date;

public class Project {
    private int id;
    private String nameP;
    private String descriptionP;
    private Date createdAt;
    private Date updatedAt;

    public Project(int id, String nameP, String descriptionP, Date createdAt, Date updatedAt) {
        this.id = id;
        this.nameP = nameP;
        this.descriptionP = descriptionP;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    public Project(){
        this.createdAt = new Date(System.currentTimeMillis());
        this.updatedAt = new Date(System.currentTimeMillis());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameP() {
        return nameP;
    }

    public void setNameP(String nameP) {
        this.nameP = nameP;
    }

    public String getDescriptionP() {
        return descriptionP;
    }

    public void setDescriptionP(String descriptionP) {
        this.descriptionP = descriptionP;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return this.nameP;
    }
}
