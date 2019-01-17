package com.zhoubi.graindepot.controller;

import com.zhoubi.graindepot.bean.Company;
import com.zhoubi.graindepot.bean.Grain;
import com.zhoubi.graindepot.bean.Graindepot;
import com.zhoubi.graindepot.biz.CommonBiz;
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
 */
@RestController
@RequestMapping("common")
public class CommonController extends BaseController {

    @Autowired
    private CommonBiz commonBiz;



    @GetMapping("companyList")
    public List<Company> companyList(HttpServletRequest request) {
        Map param = new HashMap();
        List<Company> resultList = commonBiz.companyList(param);
        return resultList;

    }

    @GetMapping("graindepotList")
    public List<Graindepot> graindepotList(HttpServletRequest request, Integer companyid) {
        Map param = new HashMap();
        if (companyid != null) {
            param.put("companyid", companyid);
        }
        List<Graindepot> resultList = commonBiz.graindepotList(param);
        return resultList;

    }
    @GetMapping("grainList")
    public List<Grain> grainList(HttpServletRequest request) {
        Map param = new HashMap();
        List<Grain> resultList = commonBiz.grainList(param);
        return resultList;

    }


}
