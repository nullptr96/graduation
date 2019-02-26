package com.gxj.graduation.tools;

import com.gxj.graduation.dto.WSFunctionDto;
import com.gxj.graduation.dto.WebServiceDto;
import com.gxj.graduation.entity.WSParam;
import com.gxj.graduation.vo.NodeDataVo;
import com.gxj.graduation.vo.ParamVo;
import com.gxj.graduation.vo.SimilarServiceVo;
import com.gxj.graduation.vo.WSFNodeDataVo;

import java.util.List;

public class ClassTransfer {
    private ClassTransfer(){}

    public static WSFNodeDataVo transfer2WSFNodeDataVo(NodeDataVo vo){
        WSFNodeDataVo ret = new WSFNodeDataVo();
        ret.setType(vo.getType());
        ret.setCategory(vo.getCategory());
        ret.setText(vo.getText());
        ret.setKey(vo.getKey());
        ret.setLoc(vo.getLoc());
        ret.setFigure(vo.getFigure());
        return ret;
    }

    public static SimilarServiceVo transfer2SimilarServiceVo(WebServiceDto wsDto, WSFunctionDto wsFuncDto, List<WSParam> inputs, List<WSParam> outputs){
        SimilarServiceVo ret = new SimilarServiceVo();
        System.out.println(wsDto.toString());
        ret.setWsid(wsDto.getId());
        ret.setWsName(wsDto.getName());
        ret.setWsAsmx(wsDto.getAsmxUrl());
        ret.setFuncId(wsFuncDto.getWSid());
        ret.setFuncName(wsFuncDto.getName());
        ret.setFuncDesc(wsFuncDto.getDescription());
        for (WSParam p : inputs) {
            ret.getInputParam().add(transfer2ParamVo(p));
        }
        for (WSParam p : outputs) {
            ret.getOutputParam().add(transfer2ParamVo(p));
        }
        return ret;
    }

    public static ParamVo transfer2ParamVo(WSParam wsParam){
        ParamVo ret =new ParamVo();
        ret.setName(wsParam.getName());
        ret.setType(wsParam.getType());
        return ret;
    }
}
