package com.zhoubi.graindepot.controller;

import com.zhoubi.graindepot.base.JsonResult;
import com.zhoubi.graindepot.base.PagerModel;
import com.zhoubi.graindepot.bean.PlanfileDetail;
import com.zhoubi.graindepot.biz.PlanfileDetailBiz;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public PagerModel userPageList(int start, int length) {
        PagerModel<PlanfileDetail> e = new PagerModel();
        e.addOrder("createtime desc");
        e.setStart(start);
        e.setLength(length);
        PagerModel<PlanfileDetail> result = planfileDetailBiz.selectListByPage(e);
        return result;
    }

    @PostMapping("/del")
    public JsonResult del(String ids) {
        if (StringUtils.isNotEmpty(ids)) {
            Map map = new HashMap();
            map.put("Where_IdsStr", ids);
            planfileDetailBiz.deleteMap(map);
        }
        return new JsonResult("删除成功", true);
    }

}
