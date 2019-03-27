package com.zhoubi.graindepot.controller;

import com.zhoubi.graindepot.base.JsonResult;
import com.zhoubi.graindepot.base.PagerModel;
import com.zhoubi.graindepot.bean.BaseUser;
import com.zhoubi.graindepot.bean.DrugoutstoreDetail;
import com.zhoubi.graindepot.bean.UserAddress;
import com.zhoubi.graindepot.biz.DrugoutstoreDetailBiz;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
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
@RequestMapping("drugoutstoreDetail")
public class DrugoutstoreDetailController extends BaseController {
    @Autowired
    private DrugoutstoreDetailBiz drugoutstoreDetailBiz;

    @GetMapping("/list/page")
    public PagerModel drugoutstoreDetailPageList(int start, int length, Integer billid) {
        UserAddress ua = getUserAddress();
        PagerModel<DrugoutstoreDetail> e = new PagerModel();
        e.addOrder("billid desc");
        e.setStart(start);
        e.setLength(length);
        e.putWhere("billid", billid);
        PagerModel<DrugoutstoreDetail> result = drugoutstoreDetailBiz.selectListByPage(e);
        return result;
    }

    @PostMapping("/edit")
    public JsonResult drugoutstoreDetailEdit(DrugoutstoreDetail item) throws ParseException {
        BaseUser user = getCurrentUser();
        UserAddress ua = getUserAddress();
        if (item.getKeyid() == null) {
            //新增
            drugoutstoreDetailBiz.insert(item);
            return new JsonResult("添加成功", true);
        } else {
            //修改
            drugoutstoreDetailBiz.update(item);
            return new JsonResult("修改成功", true);
        }

    }

    @PostMapping("/del")
    public JsonResult drugoutstoreDetailDel(String ids) {
        if (StringUtils.isNotEmpty(ids)) {
            Map map = new HashMap();
            map.put("Where_IdsStr", ids);
            drugoutstoreDetailBiz.deleteMap(map);
        }
        return new JsonResult("删除成功", true);
    }

   /* @PostMapping("/checkRepeat")
    public String checkRepeat(String drugoutstoreDetailName, Integer drugoutstoreDetailID) {
        Map map = new HashMap();
        map.put("drugoutstoreDetailName", drugoutstoreDetailName);
        map.put("drugoutstoreDetailID", drugoutstoreDetailID);
        int result = drugoutstoreDetailBiz.checkRepeat(map);
        if (result == 0) {
            return "{\"valid\":true}";
        } else {
            return "{\"valid\":false}";
        }

    }*/


}
