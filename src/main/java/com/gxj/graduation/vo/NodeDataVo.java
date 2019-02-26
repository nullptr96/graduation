package com.gxj.graduation.vo;

public class NodeDataVo {
    private String type;
    private String category;
    private String text;
    private String key;
    private String loc;
    private String input;
    private String output;
    private String figure;

    public NodeDataVo() {
        this.type = "";
        this.category = "";
        this.text = "";
        this.key = "";
        this.loc = "";
        this.input = "";
        this.output = "";
        this.figure = "";
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

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
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
