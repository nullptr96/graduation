package com.gxj.graduation.entity;

public class SimpleWebService {
    private int id;
    private String name;
    private String asmxUrl;
    private String discoUrl;
    private String wsdlUrl;
    private String description;
    private String Uid;

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

    public String getAsmxUrl() {
        return asmxUrl;
    }

    public void setAsmxUrl(String asmxUrl) {
        this.asmxUrl = asmxUrl;
    }

    public String getDiscoUrl() {
        return discoUrl;
    }

    public void setDiscoUrl(String discoUrl) {
        this.discoUrl = discoUrl;
    }

    public String getWsdlUrl() {
        return wsdlUrl;
    }

    public void setWsdlUrl(String wsdlUrl) {
        this.wsdlUrl = wsdlUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }
}
