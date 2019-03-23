package com.zhoubi.graindepot.controller;

import com.zhoubi.graindepot.base.JsonResult;
import com.zhoubi.graindepot.base.PagerModel;
import com.zhoubi.graindepot.bean.BaseUser;
import com.zhoubi.graindepot.bean.Sample;
import com.zhoubi.graindepot.bean.UserAddress;
import com.zhoubi.graindepot.biz.SampleBiz;
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
@RequestMapping("sample")
public class SampleController extends BaseController {
    @Autowired
    private SampleBiz sampleBiz;

    @GetMapping("/list/page")
    public PagerModel samplePageList(int start, int length, Integer grainid) {
        UserAddress ua = getUserAddress();
        PagerModel<Sample> e = new PagerModel();
        e.addOrder("createtime desc");
        e.setStart(start);
        e.setLength(length);
        e.putWhere("grainid", grainid);
        e.putWhere("graindepotid", ua.getGraindepotid());
        PagerModel<Sample> result = sampleBiz.selectListByPage(e);
        return result;
    }

    @PostMapping("/edit")
    public JsonResult sampleEdit(Sample item) throws ParseException {
        BaseUser user = getCurrentUser();
        UserAddress ua = getUserAddress();
        String sampdatestr = item.getSampdatestr();
        if (StringUtils.isNotEmpty(sampdatestr)) {
            item.setSampdate(DateUtils.parseDate(sampdatestr, "yyyy-MM-dd"));
        }
        String validdatestr = item.getValiddatestr();
        if (StringUtils.isNotEmpty(validdatestr)) {
            item.setValiddate(DateUtils.parseDate(validdatestr, "yyyy-MM-dd"));
        }
        String cleardatestr = item.getCleardatestr();
        if (StringUtils.isNotEmpty(cleardatestr)) {
            item.setCleardate(DateUtils.parseDate(cleardatestr, "yyyy-MM-dd"));
        }
        if (item.getSampid() == null) {
            //新增
            if (user != null) {
                item.setCreateuserid(user.getUserid());
            }
            item.setGroupid(ua.getGroupid());
            item.setCompanyid(ua.getCompanyid());
            item.setGraindepotid(ua.getGraindepotid());
            item.setCreatetime(new Date());
            sampleBiz.insert(item);
            return new JsonResult("添加成功", true);
        } else {
            //修改
            sampleBiz.update(item);
            return new JsonResult("修改成功", true);
        }

    }

    @PostMapping("/del")
    public JsonResult sampleDel(String ids) {
        if (StringUtils.isNotEmpty(ids)) {
            Map map = new HashMap();
            map.put("Where_IdsStr", ids);
            sampleBiz.deleteMap(map);
        }
        return new JsonResult("删除成功", true);
    }

   /* @PostMapping("/checkRepeat")
    public String checkRepeat(String sampleName, Integer sampleID) {
        Map map = new HashMap();
        map.put("sampleName", sampleName);
        map.put("sampleID", sampleID);
        int result = sampleBiz.checkRepeat(map);
        if (result == 0) {
            return "{\"valid\":true}";
        } else {
            return "{\"valid\":false}";
        }

    }*/


}
