package com.gxj.graduation.vo;

import java.util.ArrayList;
import java.util.List;

public class SimilarServiceVo {
    int wsid;
    String wsName;
    String wsAsmx;
    String funcName;
    int funcId;
    String funcDesc;
    List<ParamVo> inputParam;
    List<ParamVo> outputParam;

    public  SimilarServiceVo(){
        this.wsName = "";
        this.wsAsmx = "";
        this.funcName = "";
        this.funcDesc = "";
        this.inputParam = new ArrayList<>();
        this.outputParam = new ArrayList<>();
    }

    public String getWsName() {
        return wsName;
    }

    public void setWsName(String wsName) {
        this.wsName = wsName;
    }

    public String getWsAsmx() {
        return wsAsmx;
    }

    public void setWsAsmx(String wsAsmx) {
        this.wsAsmx = wsAsmx;
    }

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public int getFuncId() {
        return funcId;
    }

    public void setFuncId(int funcId) {
        this.funcId = funcId;
    }

    public String getFuncDesc() {
        return funcDesc;
    }

    public void setFuncDesc(String funcDesc) {
        this.funcDesc = funcDesc;
    }

    public List<ParamVo> getInputParam() {
        return inputParam;
    }

    public void setInputParam(List<ParamVo> inputParam) {
        this.inputParam = inputParam;
    }

    public List<ParamVo> getOutputParam() {
        return outputParam;
    }

    public void setOutputParam(List<ParamVo> outputParam) {
        this.outputParam = outputParam;
    }

    public int getWsid() {
        return wsid;
    }

    public void setWsid(int wsid) {
        this.wsid = wsid;
    }
}

