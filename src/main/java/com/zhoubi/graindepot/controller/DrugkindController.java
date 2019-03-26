package com.zhoubi.graindepot.controller;

import com.zhoubi.graindepot.base.JsonResult;
import com.zhoubi.graindepot.base.PagerModel;
import com.zhoubi.graindepot.bean.BaseUser;
import com.zhoubi.graindepot.bean.Drugkind;
import com.zhoubi.graindepot.bean.UserAddress;
import com.zhoubi.graindepot.biz.DrugkindBiz;
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
@RequestMapping("drugkind")
public class DrugkindController extends BaseController {
    @Autowired
    private DrugkindBiz drugkindBiz;

    @GetMapping("/list/page")
    public PagerModel drugkindPageList(int start, int length, String drugkindname) {
        UserAddress ua=getUserAddress();
        PagerModel<Drugkind> e = new PagerModel();
        e.addOrder("drugkindid desc");
        e.setStart(start);
        e.setLength(length);
        if (StringUtils.isNotEmpty(drugkindname)) {
            e.putWhere("drugkindname", "%"+drugkindname+"%");
        }
        e.putWhere("graindepotid",ua.getGraindepotid());
        PagerModel<Drugkind> result = drugkindBiz.selectListByPage(e);
        return result;
    }

    @PostMapping("/edit")
    public JsonResult drugkindEdit(Drugkind item) throws ParseException {
        BaseUser user=getCurrentUser();
        UserAddress ua=getUserAddress();
        if (item.getDrugkindid() == null) {
            //新增
            item.setGroupid(ua.getGroupid());
            item.setCompanyid(ua.getCompanyid());
            item.setGraindepotid(ua.getGraindepotid());
            drugkindBiz.insert(item);
            return new JsonResult("添加成功", true);
        } else {
            //修改
            drugkindBiz.update(item);
            return new JsonResult("修改成功", true);
        }

    }

    @PostMapping("/del")
    public JsonResult drugkindDel(String ids) {
        if (StringUtils.isNotEmpty(ids)) {
            Map map = new HashMap();
            map.put("Where_IdsStr", ids);
            drugkindBiz.deleteMap(map);
        }
        return new JsonResult("删除成功", true);
    }

   /* @PostMapping("/checkRepeat")
    public String checkRepeat(String drugkindName, Integer drugkindID) {
        Map map = new HashMap();
        map.put("drugkindName", drugkindName);
        map.put("drugkindID", drugkindID);
        int result = drugkindBiz.checkRepeat(map);
        if (result == 0) {
            return "{\"valid\":true}";
        } else {
            return "{\"valid\":false}";
        }

    }*/



}
