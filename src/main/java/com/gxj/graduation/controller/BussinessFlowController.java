package com.gxj.graduation.controller;


import com.gxj.graduation.vo.NodeDataVo;
import com.gxj.graduation.service.BussinessFlowService;
import com.gxj.graduation.vo.WSFNodeDataVo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/bussinessFlow")
public class BussinessFlowController {
    @Autowired
    private BussinessFlowService bussinessFlowService;

    @RequestMapping("/saveBF")
    @ResponseBody
    public Boolean saveBF(Model model, String nodeDataArray, String linkDataArray){
//        JSONArray listArray=JSONArray.fromObject(nodeDataArray);
//        JSONObject  m=JSONArray.fromObject(nodeDataArray).getJSONObject(0);
//        NodeDataVo e = (NodeDataVo)JSONObject.toBean(m, NodeDataVo.class);
//        String  s="1";
        return true;
    }

    @RequestMapping("/generateWSF")
    @ResponseBody
    public List<WSFNodeDataVo> generateWSF(Model model, String nodeDataArray, String linkDataArray){
        JSONArray _nodeDataArray=JSONArray.fromObject(nodeDataArray);
        List nodeDataList = (List)JSONArray.toCollection(_nodeDataArray, NodeDataVo.class);
        return bussinessFlowService.matchWebService(nodeDataList);
//        JSONArray _linkDataArray=JS   ONArray.fromObject(nodeDataArray);
//        List linkDataList = (List)JSONArray.toCollection(_linkDataArray, NodeDataVo.class);
    }
}
