package com.zhoubi.graindepot.controller;

import com.zhoubi.graindepot.base.JsonResult;
import com.zhoubi.graindepot.base.PagerModel;
import com.zhoubi.graindepot.bean.BaseUser;
import com.zhoubi.graindepot.bean.SafeActivity;
import com.zhoubi.graindepot.bean.SafeInsp;
import com.zhoubi.graindepot.bean.UserAddress;
import com.zhoubi.graindepot.biz.SafeActivityBiz;
import com.zhoubi.graindepot.biz.SafeInspBiz;
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
@RequestMapping("safeinsp")
public class SafeInspController extends BaseController {

    @Autowired
    private SafeInspBiz safeInspBiz;

    @GetMapping("/list/page")
    public PagerModel safeinspPageList(int start, int length) {
        UserAddress ua = getUserAddress();
        PagerModel<SafeInsp> e = new PagerModel();
//        e.addOrder("createtime desc");
        e.setStart(start);
        e.setLength(length);
//        if (StringUtils.isNotEmpty(activityname)) {
//            e.putWhere("activityname", activityname);
//        }
        PagerModel<SafeInsp> result = safeInspBiz.selectListByPage(e);
        return result;
    }

    @PostMapping("/edit")
    public JsonResult safeinspEdit(SafeInsp item) throws ParseException {
        UserAddress ua = getUserAddress();
        BaseUser baseUser = getCurrentUser();
        if (item.getSafeinspid() == null) {
//            String billdatestr = item.getBilldatestr();
//            if (StringUtils.isNotEmpty(billdatestr)) {
//                Date billdate = DateUtils.parseDate(billdatestr, "yyyy-MM-dd");
//                item.setBilldate(billdate);
//            }


                //新增
                item.setGroupid(ua.getGroupid());
                item.setGraindepotid(ua.getGraindepotid());
                item.setCompanyid(ua.getCompanyid());
                if (baseUser != null) {
                    item.setCreateuserid(baseUser.getUserid());
                }
                item.setCreatetime(new Date());
            safeInspBiz.insert(item);

            return new JsonResult("添加成功", true);
        } else {
            //修改
            safeInspBiz.update(item);
            return new JsonResult("修改成功", true);
        }

    }

    @PostMapping("/del")
    public JsonResult safeinspDel(String ids) {

        if (StringUtils.isNotEmpty(ids)) {
            Map map = new HashMap();
            map.put("Where_IdsStr", ids);
            safeInspBiz.deleteMap(map);
        }
        return new JsonResult("删除成功", true);
    }



}
