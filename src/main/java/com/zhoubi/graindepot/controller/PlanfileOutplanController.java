package com.zhoubi.graindepot.controller;

import com.zhoubi.graindepot.base.JsonResult;
import com.zhoubi.graindepot.base.PagerModel;
import com.zhoubi.graindepot.bean.BaseUser;
import com.zhoubi.graindepot.bean.PlanfileOutplan;
import com.zhoubi.graindepot.biz.PlanfileOutplanBiz;
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
@RequestMapping("planfileOutplan")
public class PlanfileOutplanController extends BaseController {
    @Autowired
    private PlanfileOutplanBiz planfileOutplanBiz;

    @GetMapping("/list/page")
    public PagerModel planfileOutplanPageList(int start, int length, String planno) {
        PagerModel<PlanfileOutplan> e = new PagerModel();
        e.addOrder("t.createtime desc");
        e.setStart(start);
        e.setLength(length);
        if (StringUtils.isNotEmpty(planno)) {
            e.putWhere("planno", "%" + planno + "%");
        }
        PagerModel<PlanfileOutplan> result = planfileOutplanBiz.selectListByPage(e);
        return result;
    }

    @PostMapping("/edit")
    public JsonResult planfileOutplanEdit(PlanfileOutplan item) throws ParseException {
        BaseUser user = getCurrentUser();
        Double tqty = item.getTqty();
        if (tqty != null) {
            item.setKgqty(tqty * 1000);
        }
        Double tprice = item.getTprice();
        if (tprice != null) {
            item.setKgprice(tprice * 1000);
        }
        String planBeginDateStr = item.getPlanbegindatestr();
        if (StringUtils.isNotEmpty(planBeginDateStr)) {
            item.setPlanbegindate(DateUtils.parseDate(planBeginDateStr, "yyyy-MM-dd"));
        }
        String planEndDateStr = item.getPlanenddatestr();
        if (StringUtils.isNotEmpty(planEndDateStr)) {
            item.setPlanenddate(DateUtils.parseDate(planEndDateStr, "yyyy-MM-dd"));
        }
        if (item.getOutplanid() == null) {
            //新增
            if (user != null) {
                item.setCreateuserid(user.getUserid());
            }
            item.setCreatetime(new Date());
            planfileOutplanBiz.insert(item);
            return new JsonResult("添加成功", true);
        } else {
            //修改
            planfileOutplanBiz.update(item);
            return new JsonResult("修改成功", true);
        }

    }

    @PostMapping("/del")
    public JsonResult planfileOutplanDel(String ids) {
        if (StringUtils.isNotEmpty(ids)) {
            Map map = new HashMap();
            map.put("Where_IdsStr", ids);
            planfileOutplanBiz.deleteMap(map);
        }
        return new JsonResult("删除成功", true);
    }

   /* @PostMapping("/checkRepeat")
    public String checkRepeat(String planfileOutplanName, Integer planfileOutplanID) {
        Map map = new HashMap();
        map.put("planfileOutplanName", planfileOutplanName);
        map.put("planfileOutplanID", planfileOutplanID);
        int result = planfileOutplanBiz.checkRepeat(map);
        if (result == 0) {
            return "{\"valid\":true}";
        } else {
            return "{\"valid\":false}";
        }

    }*/


}
