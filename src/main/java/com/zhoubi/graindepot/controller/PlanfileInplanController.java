package com.zhoubi.graindepot.controller;

import com.zhoubi.graindepot.base.JsonResult;
import com.zhoubi.graindepot.base.PagerModel;
import com.zhoubi.graindepot.bean.BaseUser;
import com.zhoubi.graindepot.bean.PlanfileInplan;
import com.zhoubi.graindepot.bean.UserAddress;
import com.zhoubi.graindepot.biz.PlanfileInplanBiz;
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
@RequestMapping("planfileInplan")
public class PlanfileInplanController extends BaseController {
    @Autowired
    private PlanfileInplanBiz planfileInplanBiz;

    @GetMapping("/list/page")
    public PagerModel planfileInplanPageList(int start, int length,String planno) {
        UserAddress ua=getUserAddress();
        PagerModel<PlanfileInplan> e = new PagerModel();
        e.addOrder("t.createtime desc");
        e.setStart(start);
        e.setLength(length);
        if (StringUtils.isNotEmpty(planno)) {
            e.putWhere("planno","%"+planno+"%");
        }
        e.putWhere("graindepotid",ua.getGraindepotid());
        PagerModel<PlanfileInplan> result = planfileInplanBiz.selectListByPage(e);
        return result;
    }

    @PostMapping("/edit")
    public JsonResult planfileInplanEdit(PlanfileInplan item) throws ParseException {
        BaseUser user=getCurrentUser();
        UserAddress ua=getUserAddress();
        Double tqty=item.getTqty();
        if (tqty!=null) {
            item.setKgqty(tqty * 1000);
        }
        Double tprice=item.getTprice();
        if (tprice!=null) {
            item.setKgprice(tprice * 1000);
        }
        String planbegindatestr=item.getPlanbegindatestr();
        if (StringUtils.isNotEmpty(planbegindatestr)) {
            item.setPlanbegindate(DateUtils.parseDate(planbegindatestr, "yyyy-MM-dd"));
        }
        String planenddateStr=item.getPlanenddatestr();
        if (StringUtils.isNotEmpty(planenddateStr)) {
            item.setPlanenddate(DateUtils.parseDate(planenddateStr, "yyyy-MM-dd"));
        }
        if (item.getInplanid() == null) {
            //新增
            if (user!=null) {
                item.setCreateuserid(user.getUserid());
            }
            item.setGroupid(ua.getGroupid());
            item.setGraindepotid(ua.getGraindepotid());
            item.setCompanyid(ua.getCompanyid());
            item.setCreatetime(new Date());
            planfileInplanBiz.insert(item);
            return new JsonResult("添加成功", true);
        } else {
            //修改
            planfileInplanBiz.update(item);
            return new JsonResult("修改成功", true);
        }

    }

    @PostMapping("/del")
    public JsonResult planfileInplanDel(String ids) {
        if (StringUtils.isNotEmpty(ids)) {
            Map map = new HashMap();
            map.put("Where_IdsStr", ids);
            planfileInplanBiz.deleteMap(map);
        }
        return new JsonResult("删除成功", true);
    }

   /* @PostMapping("/checkRepeat")
    public String checkRepeat(String planfileInplanName, Integer planfileInplanID) {
        Map map = new HashMap();
        map.put("planfileInplanName", planfileInplanName);
        map.put("planfileInplanID", planfileInplanID);
        int result = planfileInplanBiz.checkRepeat(map);
        if (result == 0) {
            return "{\"valid\":true}";
        } else {
            return "{\"valid\":false}";
        }

    }*/


}
