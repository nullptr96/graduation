package com.gxj.graduation.service.impl;

import com.gxj.graduation.baiduAipUtil.AipNlpUtil;
import com.gxj.graduation.dao.WebServiceDao;
import com.gxj.graduation.dto.WSFunctionDto;
import com.gxj.graduation.dto.WebServiceDto;
import com.gxj.graduation.entity.WSParam;
import com.gxj.graduation.service.BussinessFlowService;
import com.gxj.graduation.tools.ClassTransfer;
import com.gxj.graduation.util.ListUtil;
import com.gxj.graduation.vo.NodeDataVo;
import com.gxj.graduation.vo.SimilarServiceVo;
import com.gxj.graduation.vo.WSFNodeDataVo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BussinessFlowServiceImpl implements BussinessFlowService {
    @Autowired
    WebServiceDao wsDao;

    @Override
    public int saveBF(){
        return 0;
    }

    @Override
    public List<WSFNodeDataVo> matchWebService(List<NodeDataVo> nodeDataVoList) {
        List<WSFNodeDataVo> wsfNodeDataVos = new ArrayList<>();
        //查询所有WS
        List<WebServiceDto> allWS = wsDao.queryAllIdAndName();
        Map<Integer, WebServiceDto> wsMap = allWS.stream().collect(Collectors.toMap(WebServiceDto::getId, a -> a,(k1, k2)->k1));
        //遍历nodeDataList，把每个节点匹配上相似的ws方法列表
        for (NodeDataVo data: nodeDataVoList) {
            WSFNodeDataVo vo = ClassTransfer.transfer2WSFNodeDataVo(data);
            if("operation".equals(vo.getType())) {
                //遍历allWS，给每个ws附上相对于当前node的相似度，并降幂排序
                for (WebServiceDto ws : allWS) {
                    JSONObject o = AipNlpUtil.getInstance().simnet(data.getText(), (String) ws.getName());
                    double similarity = o.getDouble("score");
                    ws.setSimilarity(similarity);
                }
                Collections.sort(allWS, new Comparator<WebServiceDto>() {
                    @Override
                    public int compare(WebServiceDto ws1, WebServiceDto ws2) {
                        if (ws1.getSimilarity() > ws2.getSimilarity()) return -1;
                        else return 1;
                    }
                });
                //找到与当前node最相似的 X 个wsFunction
                List<WSFunctionDto> similarFunctions = this.matchWSFunction(data, allWS);
                if (similarFunctions.size() > 0) {
                    vo.setSelectedService(getSimilarServiceVo(wsMap, similarFunctions.get(0)));
                    for (WSFunctionDto dto : similarFunctions) {
                        vo.getSimilarServices().add(getSimilarServiceVo(wsMap, dto));
                    }
                }
            }
            wsfNodeDataVos.add(vo);
        }
        return wsfNodeDataVos;
    }

    private SimilarServiceVo getSimilarServiceVo(Map<Integer, WebServiceDto> wsMap, WSFunctionDto wsFuncDto) {
        return ClassTransfer.transfer2SimilarServiceVo(
                            wsMap.get(wsFuncDto.getWSid()),
                            wsFuncDto,
                            wsFuncDto.getInputParams(),
                            wsFuncDto.getOutputParams()
                    );
    }

    public List<WSFunctionDto> matchWSFunction(NodeDataVo data, List<WebServiceDto> allWS ){
        //构建返回值列表
        List<WSFunctionDto> retFuncs = new ArrayList<WSFunctionDto>();
        //allWS为空，返回一个空列表
        if(allWS.isEmpty()) return retFuncs;
        //选取相似度最高的 X 个WS方法遍历
        List<WebServiceDto> _allWS = allWS;//备注：修改_allWS会改变外部allWS，所以不允许修改
        if(allWS.size() >2 ){  //如果allWS超过10个，取前10个
            _allWS = allWS.subList(0,2);
        }
        for(WebServiceDto wsDto: _allWS){
            List<WSFunctionDto> wsfunctions=wsDao.queryWSFunctionByWSid(wsDto.getId());
            for(WSFunctionDto funcDto: wsfunctions) {
                funcDto.setInputParams(wsDao.queryParamsByFid(funcDto.getId(),"input"));
                funcDto.setOutputParams(wsDao.queryParamsByFid(funcDto.getId(),"output"));
                double w_text =0.5 ,w_input = 0 ,w_output = 0;
                double similarity_text = 0,similarity_input = 0 ,similarity_output = 0;
                if(!data.getInput().isEmpty()){
                    w_input = 0.25;
                    similarity_input = calculateSimilarityFromParams(data.getInput().split(";"),funcDto.getInputParams() );
                }
                if(!data.getOutput().isEmpty()){
                    w_output = 0.25;
                    similarity_output = calculateSimilarityFromParams(data.getInput().split(";"),funcDto.getInputParams() );
                }
                JSONObject o = AipNlpUtil.getInstance().simnet(data.getText(), funcDto.getDescription());
                similarity_text = o.getDouble("score");
                double similarity = (w_text * similarity_text +w_input * similarity_input + w_output * similarity_output)/(w_text + w_input + w_output);
                funcDto.setSimilarity(similarity);
                retFuncs.add(funcDto);
            }
        }
        Collections.sort(retFuncs,new Comparator<WSFunctionDto>() {
            @Override
            public int compare(WSFunctionDto f1, WSFunctionDto f2) {
                if(f1.getSimilarity()>f2.getSimilarity()) return -1;
                else return 1;
            }
        });

        if(retFuncs.size() >5 ){  //如果allWS超过5个，取前5个
            retFuncs.subList(5,retFuncs.size()).clear();
        }
        if(retFuncs.get(0).getSimilarity() >= 0.4){
            while(ListUtil.getLastElement(retFuncs).getSimilarity() < 0.4 ){
                retFuncs.remove(retFuncs.size()-1);
            }
        }
        return retFuncs;
    }

    private double calculateSimilarityFromParams(String[] strParams, List<WSParam> wsParams) {
        if(strParams.length == 0 || wsParams.size() ==0 )return 0;

        return 0; //TODO : 该方法未实现
    }
}
