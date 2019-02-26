package com.gxj.graduation.service;


import com.gxj.graduation.vo.NodeDataVo;
import com.gxj.graduation.vo.WSFNodeDataVo;

import java.util.List;

public interface BussinessFlowService {
    int saveBF();
    List<WSFNodeDataVo> matchWebService(List<NodeDataVo> nodeDataVoList);
}
