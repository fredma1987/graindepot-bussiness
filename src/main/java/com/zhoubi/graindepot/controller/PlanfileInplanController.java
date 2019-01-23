package com.zhoubi.graindepot.controller;

import com.zhoubi.graindepot.base.JsonResult;
import com.zhoubi.graindepot.base.PagerModel;
import com.zhoubi.graindepot.bean.BaseUser;
import com.zhoubi.graindepot.bean.PlanfileInplan;
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
    public PagerModel planfileInplanPageList(int start, int length,String planNo) {
        PagerModel<PlanfileInplan> e = new PagerModel();
        e.addOrder("t.createtime desc");
        e.setStart(start);
        e.setLength(length);
        if (StringUtils.isNotEmpty(planNo)) {
            e.putWhere("planNo","%"+planNo+"%");
        }
        PagerModel<PlanfileInplan> result = planfileInplanBiz.selectListByPage(e);
        return result;
    }

    @PostMapping("/edit")
    public JsonResult planfileInplanEdit(PlanfileInplan item) throws ParseException {
        BaseUser user=getCurrentUser();
        Double tqty=item.gettQty();
        if (tqty!=null) {
            item.setkGQty(tqty * 1000);
        }
        Double tprice=item.gettPrice();
        if (tprice!=null) {
            item.setkGPrice(tprice * 1000);
        }
        String planBeginDateStr=item.getPlanBeginDateStr();
        if (StringUtils.isNotEmpty(planBeginDateStr)) {
            item.setPlanBeginDate(DateUtils.parseDate(planBeginDateStr, "yyyy-MM-dd"));
        }
        String planEndDateStr=item.getPlanEndDateStr();
        if (StringUtils.isNotEmpty(planEndDateStr)) {
            item.setPlanEndDate(DateUtils.parseDate(planEndDateStr, "yyyy-MM-dd"));
        }
        if (item.getInplanid() == null) {
            //新增
            if (user!=null) {
                item.setCreateuserid(user.getUserid());
            }
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
