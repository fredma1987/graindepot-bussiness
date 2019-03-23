package com.zhoubi.graindepot.controller;

import com.zhoubi.graindepot.base.JsonResult;
import com.zhoubi.graindepot.base.PagerModel;
import com.zhoubi.graindepot.bean.BaseUser;
import com.zhoubi.graindepot.bean.Individual;
import com.zhoubi.graindepot.bean.UserAddress;
import com.zhoubi.graindepot.biz.IndividualBiz;
import org.apache.commons.lang.StringUtils;
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
@RequestMapping("individual")
public class IndividualController extends BaseController {
    @Autowired
    private IndividualBiz individualBiz;

    @GetMapping("/list/page")
    public PagerModel individualPageList(int start, int length) {
        UserAddress ua=getUserAddress();
        PagerModel<Individual> e = new PagerModel();
        e.addOrder("createtime desc");
        e.setStart(start);
        e.setLength(length);
        e.putWhere("graindepotid",ua.getGraindepotid());
        PagerModel<Individual> result = individualBiz.selectListByPage(e);
        return result;
    }

    @PostMapping("/edit")
    public JsonResult individualEdit(Individual item) throws ParseException {
        BaseUser user=getCurrentUser();
        UserAddress ua=getUserAddress();
        if (item.getIndividualid() == null) {
            //新增
            if (user!=null) {
                item.setCreateuserid(user.getUserid());
            }
            item.setGroupid(ua.getGroupid());
            item.setCompanyid(ua.getCompanyid());
            item.setGraindepotid(ua.getGraindepotid());
            item.setCreatetime(new Date());
            individualBiz.insert(item);
            return new JsonResult("添加成功", true);
        } else {
            //修改
            individualBiz.update(item);
            return new JsonResult("修改成功", true);
        }

    }

    @PostMapping("/del")
    public JsonResult individualDel(String ids) {
        if (StringUtils.isNotEmpty(ids)) {
            Map map = new HashMap();
            map.put("Where_IdsStr", ids);
            individualBiz.deleteMap(map);
        }
        return new JsonResult("删除成功", true);
    }

   /* @PostMapping("/checkRepeat")
    public String checkRepeat(String individualName, Integer individualID) {
        Map map = new HashMap();
        map.put("individualName", individualName);
        map.put("individualID", individualID);
        int result = individualBiz.checkRepeat(map);
        if (result == 0) {
            return "{\"valid\":true}";
        } else {
            return "{\"valid\":false}";
        }

    }*/


}
