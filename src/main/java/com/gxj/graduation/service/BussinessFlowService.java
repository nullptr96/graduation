package com.gxj.graduation.service;


import com.gxj.graduation.vo.NodeData;
import javafx.util.Pair;

import java.util.List;

public interface BussinessFlowService {
    int saveBF();
//    List<Pair<String, Double>>  matchWebServices(List<NodeData>)
    void matchWebService(List<NodeData> nodeDataList);
}
