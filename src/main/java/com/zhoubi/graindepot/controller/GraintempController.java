package com.zhoubi.graindepot.controller;

import com.zhoubi.graindepot.base.JsonResult;
import com.zhoubi.graindepot.base.PagerModel;
import com.zhoubi.graindepot.bean.BaseUser;
import com.zhoubi.graindepot.bean.Graintemp;
import com.zhoubi.graindepot.bean.UserAddress;
import com.zhoubi.graindepot.biz.GraintempBiz;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 1A12 on 2019/1/19/0019.
 */
@RestController
@RequestMapping("graintemp")
public class GraintempController extends BaseController {
    @Autowired
    private GraintempBiz graintempBiz;

    @GetMapping("/list/page")
    public PagerModel graintempPageList(int start, int length, Integer storageid) {
        UserAddress ua=getUserAddress();
        PagerModel<Graintemp> e = new PagerModel();
        e.addOrder("testdate desc");
        e.setStart(start);
        e.setLength(length);
        e.putWhere("graindepotid",ua.getGraindepotid());
        e.putWhere("storageid",storageid);
        PagerModel<Graintemp> result = graintempBiz.selectListByPage(e);
        return result;
    }



}
