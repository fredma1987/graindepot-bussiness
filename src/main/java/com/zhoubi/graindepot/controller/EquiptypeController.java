package com.zhoubi.graindepot.controller;

import com.zhoubi.graindepot.base.JsonResult;
import com.zhoubi.graindepot.base.PagerModel;
import com.zhoubi.graindepot.bean.BaseUser;
import com.zhoubi.graindepot.bean.Equiptype;
import com.zhoubi.graindepot.bean.UserAddress;
import com.zhoubi.graindepot.biz.EquiptypeBiz;
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
import java.util.List;
import java.util.Map;

/**
 * Created by 1A12 on 2019/1/19/0019.
 */
@RestController
@RequestMapping("equiptype")
public class EquiptypeController extends BaseController {
    @Autowired
    private EquiptypeBiz equiptypeBiz;

    @GetMapping("/list/page")
    public PagerModel equiptypePageList(int start, int length, String equiptypename) {
        UserAddress ua=getUserAddress();
        PagerModel<Equiptype> e = new PagerModel();
        e.addOrder("sequno asc");
        e.setStart(start);
        e.setLength(length);
        if (StringUtils.isNotEmpty(equiptypename)) {
            e.putWhere("equiptypename", "%"+equiptypename+"%");
        }
        e.putWhere("graindepotid",ua.getGraindepotid());
        PagerModel<Equiptype> result = equiptypeBiz.selectListByPage(e);
        return result;
    }

    @PostMapping("/edit")
    public JsonResult equiptypeEdit(Equiptype item) throws ParseException {
        BaseUser user=getCurrentUser();
        UserAddress ua=getUserAddress();
        if (item.getEquiptypeid() == null) {
            //新增
            item.setGroupid(ua.getGroupid());
            item.setCompanyid(ua.getCompanyid());
            item.setGraindepotid(ua.getGraindepotid());
            equiptypeBiz.insert(item);
            return new JsonResult("添加成功", true);
        } else {
            //修改
            equiptypeBiz.update(item);
            return new JsonResult("修改成功", true);
        }

    }

    @PostMapping("/del")
    public JsonResult equiptypeDel(String ids) {
        if (StringUtils.isNotEmpty(ids)) {
            Map map = new HashMap();
            map.put("Where_IdsStr", ids);
            equiptypeBiz.deleteMap(map);
        }
        return new JsonResult("删除成功", true);
    }

   /* @PostMapping("/checkRepeat")
    public String checkRepeat(String equiptypeName, Integer equiptypeID) {
        Map map = new HashMap();
        map.put("equiptypeName", equiptypeName);
        map.put("equiptypeID", equiptypeID);
        int result = equiptypeBiz.checkRepeat(map);
        if (result == 0) {
            return "{\"valid\":true}";
        } else {
            return "{\"valid\":false}";
        }

    }*/



}
