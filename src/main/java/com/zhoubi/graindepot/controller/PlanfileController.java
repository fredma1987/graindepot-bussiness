package com.zhoubi.graindepot.controller;

import com.zhoubi.graindepot.base.JsonResult;
import com.zhoubi.graindepot.base.PagerModel;
import com.zhoubi.graindepot.bean.BaseUser;
import com.zhoubi.graindepot.bean.Planfile;
import com.zhoubi.graindepot.biz.PlanfileBiz;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhanghao on 2019/1/15.
 */
@RestController
@RequestMapping("planfile")
public class PlanfileController extends BaseController {
    @Autowired
    private PlanfileBiz planfileBiz;


    @GetMapping("/list/page")
    public PagerModel planfilePageList(int start, int length, String planNo) {
        PagerModel<Planfile> e = new PagerModel();
        e.addOrder("createtime desc");
        e.setStart(start);
        e.setLength(length);
        if (StringUtils.isNotEmpty(planNo)) {
            e.putWhere("planNo", "%" + planNo + "%");
        }
        PagerModel<Planfile> result = planfileBiz.selectListByPage(e);
        return result;
    }

    @PostMapping("/edit")
    public JsonResult planfileEdit(Planfile item) throws ParseException {
        String dispatchdateStr = item.getDispatchdateStr();
        if (StringUtils.isNotEmpty(dispatchdateStr)) {
            Date dispatchdate = DateUtils.parseDate(dispatchdateStr, "yyyy-MM-dd");
            item.setDispatchdate(dispatchdate);
        }
        String planBeginDateStr = item.getPlanBeginDateStr();
        if (StringUtils.isNotEmpty(planBeginDateStr)) {
            Date planBeginDate = DateUtils.parseDate(planBeginDateStr, "yyyy-MM-dd");
            item.setPlanBeginDate(planBeginDate);
        }
        String planEndDateStr = item.getPlanEndDateStr();
        if (StringUtils.isNotEmpty(planEndDateStr)) {
            Date planEndDate = DateUtils.parseDate(planEndDateStr, "yyyy-MM-dd");
            item.setPlanEndDate(planEndDate);
        }

        if (item.getPlanfileid() == 0) {
            //新增
            item.setCreatetime(new Date());
            planfileBiz.insert(item);
            return new JsonResult("添加成功", true);
        } else {
            //修改
            planfileBiz.update(item);
            return new JsonResult("修改成功", true);
        }

    }

    @PostMapping("/del")
    public JsonResult planfileDel(String ids) {
        if (StringUtils.isNotEmpty(ids)) {
            Map map = new HashMap();
            map.put("Where_IdsStr", ids);
            planfileBiz.deleteMap(map);
        }
        return new JsonResult("删除成功", true);
    }

    //获取下拉框列表数据
    @GetMapping("/selectorList")
    public List<Planfile> selectorList() {
        Map param = new HashMap();
        List<Planfile> result = planfileBiz.selectorList(param);
        return result;
    }


}
