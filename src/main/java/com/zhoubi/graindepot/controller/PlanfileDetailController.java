package com.zhoubi.graindepot.controller;

import com.zhoubi.graindepot.base.JsonResult;
import com.zhoubi.graindepot.base.PagerModel;
import com.zhoubi.graindepot.bean.Planfile;
import com.zhoubi.graindepot.bean.PlanfileDetail;
import com.zhoubi.graindepot.bean.UserAddress;
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
    public PagerModel userPageList(int start, int length,String planno) {
        PagerModel<PlanfileDetail> e = new PagerModel();
        UserAddress ua=getUserAddress();
        e.addOrder("createtime desc");
        e.setStart(start);
        e.setLength(length);
        if (StringUtils.isNotEmpty(planno)) {
            e.putWhere("planno","%"+planno+"%");
        }
        e.putWhere("graindepotid",ua.getGraindepotid());
        PagerModel<PlanfileDetail> result = planfileDetailBiz.selectListByPage(e);
        return result;
    }
    @PostMapping("/edit")
    public JsonResult planfileDetailEdit(PlanfileDetail item) throws ParseException {
        String planbegindatestr = item.getPlanbegindatestr();
        if (StringUtils.isNotEmpty(planbegindatestr)) {
            Date planBeginDate = DateUtils.parseDate(planbegindatestr, "yyyy-MM-dd");
            item.setPlanbegindate(planBeginDate);
        }
        String planenddatestr = item.getPlanenddatestr();
        if (StringUtils.isNotEmpty(planenddatestr)) {
            Date planEndDate = DateUtils.parseDate(planenddatestr, "yyyy-MM-dd");
            item.setPlanenddate(planEndDate);
        }
        item.setKgqty(item.getTqty() * 1000);
        item.setKgprice(item.getTprice() * 1000);

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
