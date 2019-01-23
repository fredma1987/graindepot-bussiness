package com.zhoubi.graindepot.controller;

import com.zhoubi.graindepot.base.JsonResult;
import com.zhoubi.graindepot.base.PagerModel;
import com.zhoubi.graindepot.bean.Grainattr;
import com.zhoubi.graindepot.biz.GrainattrBiz;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 1A12 on 2019/1/19/0019.
 */
@RestController
@RequestMapping("grainattr")
public class GrainattrController extends BaseController {
    @Autowired
    private GrainattrBiz grainattrBiz;

    @GetMapping("/list/page")
    public PagerModel grainattrPageList(int start, int length, String grainattrname) {
        PagerModel<Grainattr> e = new PagerModel();
        e.addOrder("orderno desc");
        e.setStart(start);
        e.setLength(length);
        if (grainattrname != null) {
            e.putWhere("grainattrname", "%"+grainattrname+"%");
        }
        PagerModel<Grainattr> result = grainattrBiz.selectListByPage(e);
        return result;
    }

    @PostMapping("/edit")
    public JsonResult grainattrEdit(Grainattr item) throws ParseException {

        if (item.getGrainattrid() == null) {
            //新增
            grainattrBiz.insert(item);
            return new JsonResult("添加成功", true);
        } else {
            //修改
            grainattrBiz.update(item);
            return new JsonResult("修改成功", true);
        }

    }

    @PostMapping("/del")
    public JsonResult grainattrDel(String ids) {
        if (StringUtils.isNotEmpty(ids)) {
            Map map = new HashMap();
            map.put("Where_IdsStr", ids);
            grainattrBiz.deleteMap(map);
        }
        return new JsonResult("删除成功", true);
    }

    @PostMapping("/checkRepeat")
    public String checkRepeat(String grainattrname, Integer grainattrid) {
        Map map = new HashMap();
        map.put("grainattrname", grainattrname);
        map.put("grainattrid", grainattrid);
        int result = grainattrBiz.checkRepeat(map);
        if (result == 0) {
            return "{\"valid\":true}";
        } else {
            return "{\"valid\":false}";
        }

    }


}
