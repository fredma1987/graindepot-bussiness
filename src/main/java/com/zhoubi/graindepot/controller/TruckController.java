package com.zhoubi.graindepot.controller;

import com.zhoubi.graindepot.base.JsonResult;
import com.zhoubi.graindepot.base.PagerModel;
import com.zhoubi.graindepot.bean.BaseUser;
import com.zhoubi.graindepot.bean.Truck;
import com.zhoubi.graindepot.bean.UserAddress;
import com.zhoubi.graindepot.biz.TruckBiz;
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
 * Created by zhanghao on 2019/1/15.
 */
@RestController
@RequestMapping("truck")
public class TruckController extends BaseController {

    @Autowired
    private TruckBiz truckBiz;

    @GetMapping("/list/page")
    public PagerModel truckPageList(int start, int length, String trucknum) {
        UserAddress ua = getUserAddress();
        PagerModel<Truck> e = new PagerModel();
//        e.addOrder("createtime desc");
        e.setStart(start);
        e.setLength(length);
        if (StringUtils.isNotEmpty(trucknum)) {
            e.putWhere("trucknum", "%" + trucknum + "%");
        }
        PagerModel<Truck> result = truckBiz.selectListByPage(e);
        return result;
    }

    @PostMapping("/edit")
    public JsonResult truckEdit(Truck item) throws ParseException {
        UserAddress ua = getUserAddress();
        BaseUser baseUser = getCurrentUser();
        if (item.getTruckid() == null) {
            //新增
            item.setGroupid(ua.getGroupid());
            item.setGraindepotid(ua.getGraindepotid());
            item.setCompanyid(ua.getCompanyid());
            truckBiz.insert(item);
            return new JsonResult("添加成功", true);
        } else {
            //修改
            truckBiz.update(item);
            return new JsonResult("修改成功", true);
        }

    }

    @PostMapping("/del")
    public JsonResult truckDel(String ids) {

        if (StringUtils.isNotEmpty(ids)) {
            Map map = new HashMap();
            map.put("Where_IdsStr", ids);
            truckBiz.deleteMap(map);
        }
        return new JsonResult("删除成功", true);
    }

    //校验内部车辆名称是否重复
    @PostMapping("/checkRepeat")
    public String checkRepeat(String trucknum, Integer truckid) {
        Map map = new HashMap();
        map.put("trucknum", trucknum);
        map.put("truckid", truckid);
        int result = truckBiz.checkRepeat(map);
        if (result == 0) {
            return "{\"valid\":true}";
        } else {
            return "{\"valid\":false}";
        }

    }

}
