package com.gxj.graduation.entity;

public class WSParam {
    int id;
    String name;
    String description;
    String  type;
    String mode;
    int Fid;

    public WSParam() {
        this.name = "";
        this.description = "";
        this.type = "";
        this.mode = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public int getFid() {
        return Fid;
    }

    public void setFid(int fid) {
        Fid = fid;
    }
}
