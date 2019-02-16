package com.gxj.graduation.controller;


import com.gxj.graduation.entity.LinkData;
import com.gxj.graduation.entity.NodeData;
import com.gxj.graduation.entity.User;
import com.gxj.graduation.service.BussinessFlowService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.soap.Node;
import java.util.List;

@Controller
@RequestMapping("/bussinessFlow")
public class BussinessFlowController {
    @Autowired
    private BussinessFlowService bussinessFlowService;

    @RequestMapping("/saveBF")
    @ResponseBody
    public Boolean saveBF(Model model, String nodeDataArray, String linkDataArray){
        JSONArray listArray=JSONArray.fromObject(nodeDataArray);
        JSONObject  m=JSONArray.fromObject(nodeDataArray).getJSONObject(0);
        NodeData e = (NodeData)JSONObject.toBean(m, NodeData.class);
        String  s="1";
        return true;
    }
}
