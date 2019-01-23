package com.zhoubi.graindepot.controller;

import com.zhoubi.graindepot.base.JsonResult;
import com.zhoubi.graindepot.base.PagerModel;
import com.zhoubi.graindepot.bean.Planfile;
import com.zhoubi.graindepot.bean.PlanfileDetail;
import com.zhoubi.graindepot.biz.PlanfileDetailBiz;
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
 * Created by zhanghao on 2019/1/16.
 */
@RestController
@RequestMapping("planfileDetail")
public class PlanfileDetailController extends BaseController {
    @Autowired
    private PlanfileDetailBiz planfileDetailBiz;

    @GetMapping("/list/page")
    public PagerModel userPageList(int start, int length,String planNo) {
        PagerModel<PlanfileDetail> e = new PagerModel();
        e.addOrder("createtime desc");
        e.setStart(start);
        e.setLength(length);
        if (StringUtils.isNotEmpty(planNo)) {
            e.putWhere("planNo","%"+planNo+"%");
        }
        PagerModel<PlanfileDetail> result = planfileDetailBiz.selectListByPage(e);
        return result;
    }
    @PostMapping("/edit")
    public JsonResult planfileDetailEdit(PlanfileDetail item) throws ParseException {
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
        item.setkGQty(item.gettQty()*1000);
        item.setkGPrice(item.gettPrice()*1000);

        if (item.getPlandetailid() == null) {
            //新增
            planfileDetailBiz.insert(item);
            return new JsonResult("添加成功", true);
        } else {
            //修改
            planfileDetailBiz.update(item);
            return new JsonResult("修改成功", true);
        }

    }

    @PostMapping("/del")
    public JsonResult planfileDel(String ids) {
        if (StringUtils.isNotEmpty(ids)) {
            Map map = new HashMap();
            map.put("Where_IdsStr", ids);
            planfileDetailBiz.deleteMap(map);
        }
        return new JsonResult("删除成功", true);
    }

}
