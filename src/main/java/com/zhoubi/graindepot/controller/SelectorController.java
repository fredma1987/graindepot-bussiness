package com.zhoubi.graindepot.controller;

import com.zhoubi.graindepot.bean.*;
import com.zhoubi.graindepot.biz.SelectorBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhanghao on 2019/1/16.
 * 下拉框后台请求
 */
@RestController
@RequestMapping("selector")
public class SelectorController extends BaseController {

    @Autowired
    private SelectorBiz selectorBiz;

    @GetMapping("companyList")
    public List<Company> companyList(HttpServletRequest request) {
        Map param = new HashMap();
        List<Company> resultList = selectorBiz.companyList(param);
        return resultList;

    }

    @GetMapping("graindepotList")
    public List<Graindepot> graindepotList(HttpServletRequest request, Integer companyid) {
        Map param = new HashMap();
        if (companyid != null) {
            param.put("companyid", companyid);
        }
        List<Graindepot> resultList = selectorBiz.graindepotList(param);
        return resultList;

    }

    @GetMapping("grainList")
    public List<Grain> grainList(HttpServletRequest request) {
        Map param = new HashMap();
        List<Grain> resultList = selectorBiz.grainList(param);
        return resultList;

    }

    //粮食性质下拉框
    @GetMapping("grainattrList")
    public List<Grainattr> grainattrList(HttpServletRequest request) {
        Map param = new HashMap();
        List<Grainattr> resultList = selectorBiz.grainattrList(param);
        return resultList;

    }

    //往来单位下拉框
    @GetMapping("traderList")
    public List<Trader> traderList(HttpServletRequest request) {
        Map param = new HashMap();
        List<Trader> resultList = selectorBiz.traderList(param);
        return resultList;

    }

    //合同类型下拉框
    @GetMapping("contracttypeList")
    public List<Contracttype> contracttypeList(HttpServletRequest request,Integer buySellFlag) {
        Map param = new HashMap();
        if (buySellFlag!=null) {
            param.put("buySellFlag",buySellFlag);
        }
        List<Contracttype> resultList = selectorBiz.contracttypeList(param);
        return resultList;

    }

    //物料类型下拉框
    @GetMapping("goodstypeList")
    public List<Goodstype> goodstypeList(HttpServletRequest request) {
        UserAddress ua=getUserAddress();
        Map param = new HashMap();
        param.put("graindepotid",ua.getGraindepotid());
        List<Goodstype> resultList = selectorBiz.goodstypeList(param);
        return resultList;

    }


}
