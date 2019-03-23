package com.zhoubi.graindepot.controller;

import com.zhoubi.graindepot.base.JsonResult;
import com.zhoubi.graindepot.base.PagerModel;
import com.zhoubi.graindepot.bean.BaseUser;
import com.zhoubi.graindepot.bean.ReserveScale;
import com.zhoubi.graindepot.bean.UserAddress;
import com.zhoubi.graindepot.biz.ReserveScaleBiz;
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
@RequestMapping("reserveScale")
public class ReserveScaleController extends BaseController {
    @Autowired
    private ReserveScaleBiz reserveScaleBiz;

    @GetMapping("/list/page")
    public PagerModel reserveScalePageList(int start, int length,Integer grainid) {
        UserAddress ua=getUserAddress();
        PagerModel<ReserveScale> e = new PagerModel();
        e.addOrder("createtime desc");
        e.setStart(start);
        e.setLength(length);
        e.putWhere("graindepotid",ua.getGraindepotid());
        e.putWhere("grainid",grainid);
        PagerModel<ReserveScale> result = reserveScaleBiz.selectListByPage(e);
        return result;
    }

    @PostMapping("/edit")
    public JsonResult reserveScaleEdit(ReserveScale item) throws ParseException {
        BaseUser user=getCurrentUser();
        UserAddress ua=getUserAddress();
        if (item.getScaleid() == null) {
            //新增
            if (user!=null) {
                item.setCreateuserid(user.getUserid());
            }
            item.setGroupid(ua.getGroupid());
            item.setCompanyid(ua.getCompanyid());
            item.setGraindepotid(ua.getGraindepotid());
            item.setCreatetime(new Date());
            reserveScaleBiz.insert(item);
            return new JsonResult("添加成功", true);
        } else {
            //修改
            reserveScaleBiz.update(item);
            return new JsonResult("修改成功", true);
        }

    }

    @PostMapping("/del")
    public JsonResult reserveScaleDel(String ids) {
        if (StringUtils.isNotEmpty(ids)) {
            Map map = new HashMap();
            map.put("Where_IdsStr", ids);
            reserveScaleBiz.deleteMap(map);
        }
        return new JsonResult("删除成功", true);
    }

   /* @PostMapping("/checkRepeat")
    public String checkRepeat(String reserveScaleName, Integer reserveScaleID) {
        Map map = new HashMap();
        map.put("reserveScaleName", reserveScaleName);
        map.put("reserveScaleID", reserveScaleID);
        int result = reserveScaleBiz.checkRepeat(map);
        if (result == 0) {
            return "{\"valid\":true}";
        } else {
            return "{\"valid\":false}";
        }

    }*/


}
