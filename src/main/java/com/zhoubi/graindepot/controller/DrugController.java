package com.zhoubi.graindepot.controller;

import com.zhoubi.graindepot.base.JsonResult;
import com.zhoubi.graindepot.base.PagerModel;
import com.zhoubi.graindepot.bean.BaseUser;
import com.zhoubi.graindepot.bean.Drug;
import com.zhoubi.graindepot.bean.UserAddress;
import com.zhoubi.graindepot.biz.DrugBiz;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 1A12 on 2019/1/19/0019.
 */
@RestController
@RequestMapping("drug")
public class DrugController extends BaseController {
    @Autowired
    private DrugBiz drugBiz;

    @GetMapping("/list/page")
    public PagerModel drugPageList(int start, int length, String drugname) {
        UserAddress ua=getUserAddress();
        PagerModel<Drug> e = new PagerModel();
        e.addOrder("drugid desc");
        e.setStart(start);
        e.setLength(length);
        if (StringUtils.isNotEmpty(drugname)) {
            e.putWhere("drugname", "%"+drugname+"%");
        }
        e.putWhere("graindepotid",ua.getGraindepotid());
        PagerModel<Drug> result = drugBiz.selectListByPage(e);
        return result;
    }

    @PostMapping("/edit")
    public JsonResult drugEdit(Drug item) throws ParseException {
        BaseUser user=getCurrentUser();
        UserAddress ua=getUserAddress();
        if (item.getDrugid() == null) {
            //新增
            item.setGroupid(ua.getGroupid());
            item.setCompanyid(ua.getCompanyid());
            item.setGraindepotid(ua.getGraindepotid());
            drugBiz.insert(item);
            return new JsonResult("添加成功", true);
        } else {
            //修改
            drugBiz.update(item);
            return new JsonResult("修改成功", true);
        }

    }

    @PostMapping("/del")
    public JsonResult drugDel(String ids) {
        if (StringUtils.isNotEmpty(ids)) {
            Map map = new HashMap();
            map.put("Where_IdsStr", ids);
            drugBiz.deleteMap(map);
        }
        return new JsonResult("删除成功", true);
    }

   /* @PostMapping("/checkRepeat")
    public String checkRepeat(String drugName, Integer drugID) {
        Map map = new HashMap();
        map.put("drugName", drugName);
        map.put("drugID", drugID);
        int result = drugBiz.checkRepeat(map);
        if (result == 0) {
            return "{\"valid\":true}";
        } else {
            return "{\"valid\":false}";
        }

    }*/



}
