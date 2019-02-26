package com.gxj.graduation.dto;

import com.gxj.graduation.entity.WSFunction;

import java.util.ArrayList;
import java.util.List;

public class WebServiceDto {
    private int id;
    private String name;
    private String asmxUrl;
    private String discoUrl;
    private String wsdlUrl;
    private String description;
    private String Uid;
    private List<WSFunction> functions;
    private double similarity;

    public WebServiceDto() {
        this.name = "";
        this.asmxUrl = "";
        this.discoUrl = "";
        this.wsdlUrl = "";
        this.description = "";
        this.Uid = "";
        this.functions = new ArrayList<>();
    }

    public double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(double similarity) {
        this.similarity = similarity;
    }

    public List<WSFunction> getFunctions() {
        return functions;
    }

    public void setFunctions(List<WSFunction> functions) {
        this.functions = functions;
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
