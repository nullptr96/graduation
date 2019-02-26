package com.gxj.graduation.vo;

import java.util.ArrayList;
import java.util.List;

public class WSFNodeDataVo {
    private String type;
    private String category;
    private String text;
    private String key;
    private String loc;
    private String figure;

    private SimilarServiceVo selectedService;
    private List<SimilarServiceVo> similarServices;

    public WSFNodeDataVo(){
        this.type = "";
        this.category = "";
        this.text = "";
        this.key = "";
        this.loc = "";
        this.figure = "";
        this.selectedService = new SimilarServiceVo();
        this.similarServices = new ArrayList<>();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public SimilarServiceVo getSelectedService() {
        return selectedService;
    }

    public void setSelectedService(SimilarServiceVo selectedService) {
        this.selectedService = selectedService;
    }

    public List<SimilarServiceVo> getSimilarServices() {
        return similarServices;
    }

    public void setSimilarServices(List<SimilarServiceVo> similarServices) {
        this.similarServices = similarServices;
    }

    public String getFigure() {
        return figure;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
