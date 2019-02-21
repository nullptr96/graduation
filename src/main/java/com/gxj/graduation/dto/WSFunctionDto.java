package com.gxj.graduation.dto;

import com.gxj.graduation.entity.WSParams;

import java.util.List;

public class WSFunctionDto {
    private int id;
    private String name;
    private String description;
    private String WSid;
    private List<WSParams> inputParams;
    private List<WSParams> outputParams;

    public List<WSParams> getInputParams() {
        return inputParams;
    }

    public void setInputParams(List<WSParams> inputParams) {
        this.inputParams = inputParams;
    }

    public List<WSParams> getOutputParams() {
        return outputParams;
    }

    public void setOutputParams(List<WSParams> outputParams) {
        this.outputParams = outputParams;
    }

    private double similarity;


    public double getSimilarity() {
        return similarity;
    }
    public void setSimilarity(double similarity) {
        this.similarity = similarity;
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

    public String getWSid() {
        return WSid;
    }

    public void setWSid(String WSid) {
        this.WSid = WSid;
    }

}
