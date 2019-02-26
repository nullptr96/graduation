package com.gxj.graduation.dto;

import com.gxj.graduation.entity.WSParam;

import java.util.ArrayList;
import java.util.List;

public class WSFunctionDto {
    private int id;
    private String name;
    private String description;
    private int WSid;
    private List<WSParam> inputParams;
    private List<WSParam> outputParams;

    public WSFunctionDto() {
        this.name = "";
        this.description = "";
        this.inputParams = new ArrayList<>();
        this.outputParams = new ArrayList<>();
    }

    public List<WSParam> getInputParams() {
        return inputParams;
    }

    public void setInputParams(List<WSParam> inputParams) {
        this.inputParams = inputParams;
    }

    public List<WSParam> getOutputParams() {
        return outputParams;
    }

    public void setOutputParams(List<WSParam> outputParams) {
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

    public int getWSid() {
        return WSid;
    }

    public void setWSid(int WSid) {
        this.WSid = WSid;
    }

}
