package com.zhoubi.graindepot.controller;

import com.zhoubi.graindepot.base.JsonResult;
import com.zhoubi.graindepot.base.PagerModel;
import com.zhoubi.graindepot.bean.BaseUser;
import com.zhoubi.graindepot.bean.DruginstoreDetail;
import com.zhoubi.graindepot.bean.UserAddress;
import com.zhoubi.graindepot.biz.DruginstoreDetailBiz;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 1A12 on 2019/1/19/0019.
 */
@RestController
@RequestMapping("druginstoreDetail")
public class DruginstoreDetailController extends BaseController {
    @Autowired
    private DruginstoreDetailBiz druginstoreDetailBiz;

    @GetMapping("/list/page")
    public PagerModel druginstoreDetailPageList(int start, int length, Integer billid) {
        UserAddress ua = getUserAddress();
        PagerModel<DruginstoreDetail> e = new PagerModel();
        e.addOrder("createtime desc");
        e.setStart(start);
        e.setLength(length);
        e.putWhere("billid", billid);
        PagerModel<DruginstoreDetail> result = druginstoreDetailBiz.selectListByPage(e);
        return result;
    }

    @PostMapping("/edit")
    public JsonResult druginstoreDetailEdit(DruginstoreDetail item) throws ParseException {
        BaseUser user = getCurrentUser();
        UserAddress ua = getUserAddress();
        String proddatestr=item.getProddatestr();
        if (StringUtils.isNotEmpty(proddatestr)) {
            item.setProddate(DateUtils.parseDate(proddatestr, "yyyy-MM-dd"));
        }
        if (item.getKeyid() == null) {
            //新增
            druginstoreDetailBiz.insert(item);
            return new JsonResult("添加成功", true);
        } else {
            //修改
            druginstoreDetailBiz.update(item);
            return new JsonResult("修改成功", true);
        }

    }

    @PostMapping("/del")
    public JsonResult druginstoreDetailDel(String ids) {
        if (StringUtils.isNotEmpty(ids)) {
            Map map = new HashMap();
            map.put("Where_IdsStr", ids);
            druginstoreDetailBiz.deleteMap(map);
        }
        return new JsonResult("删除成功", true);
    }

   /* @PostMapping("/checkRepeat")
    public String checkRepeat(String druginstoreDetailName, Integer druginstoreDetailID) {
        Map map = new HashMap();
        map.put("druginstoreDetailName", druginstoreDetailName);
        map.put("druginstoreDetailID", druginstoreDetailID);
        int result = druginstoreDetailBiz.checkRepeat(map);
        if (result == 0) {
            return "{\"valid\":true}";
        } else {
            return "{\"valid\":false}";
        }

    }*/


}
