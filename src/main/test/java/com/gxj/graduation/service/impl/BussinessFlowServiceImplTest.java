package com.gxj.graduation.service.impl;

import baseUnitTest.BaseUnitTest;
import com.gxj.graduation.service.BussinessFlowService;
import com.gxj.graduation.vo.NodeDataVo;
import net.sf.json.JSONArray;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BussinessFlowServiceImplTest extends BaseUnitTest {
    @Autowired
    BussinessFlowService bussinessFlowService;
    @Test
    public void matchWebService() {
        String nodeDataArray="[{\"type\":\"condition\",\"text\":\"查询火车 \",\"figure\":\"Diamond\",\"key\":-4,\"loc\":\"-491 -73\",\"__gohashid\":1376,\"input\":\"\",\"output\":\"\"},{\"type\":\"operation\",\"category\":\"Stress\",\"text\":\"查询明天的天气\",\"key\":-3,\"loc\":\"-466 -235\",\"__gohashid\":1638,\"input\":\"日期；国家；城市\",\"output\":\"天气\"}]";
        JSONArray _nodeDataArray=JSONArray.fromObject(nodeDataArray);
        List nodeDataList = (List)JSONArray.toCollection(_nodeDataArray, NodeDataVo.class);
        bussinessFlowService.matchWebService(nodeDataList);
    }

}