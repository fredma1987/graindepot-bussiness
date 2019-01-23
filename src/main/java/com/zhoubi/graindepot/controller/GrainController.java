package com.zhoubi.graindepot.controller;

import com.zhoubi.graindepot.base.JsonResult;
import com.zhoubi.graindepot.base.PagerModel;
import com.zhoubi.graindepot.bean.Grain;
import com.zhoubi.graindepot.biz.GrainBiz;
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
@RequestMapping("grain")
public class GrainController extends BaseController {
    @Autowired
    private GrainBiz grainBiz;

    @GetMapping("/list/page")
    public PagerModel grainPageList(int start, int length, String grainname) {
        PagerModel<Grain> e = new PagerModel();
        e.addOrder("orderno desc");
        e.setStart(start);
        e.setLength(length);
        if (grainname != null) {
            e.putWhere("grainname", "%"+grainname+"%");
        }
        PagerModel<Grain> result = grainBiz.selectListByPage(e);
        return result;
    }

    @PostMapping("/edit")
    public JsonResult grainEdit(Grain item) throws ParseException {

        if (item.getGrainid() == null) {
            //新增
            grainBiz.insert(item);
            return new JsonResult("添加成功", true);
        } else {
            //修改
            grainBiz.update(item);
            return new JsonResult("修改成功", true);
        }

    }

    @PostMapping("/del")
    public JsonResult grainDel(String ids) {
        if (StringUtils.isNotEmpty(ids)) {
            Map map = new HashMap();
            map.put("Where_IdsStr", ids);
            grainBiz.deleteMap(map);
        }
        return new JsonResult("删除成功", true);
    }

    @PostMapping("/checkRepeat")
    public String checkRepeat(String grainname, Integer grainid) {
        Map map = new HashMap();
        map.put("grainname", grainname);
        map.put("grainid", grainid);
        int result = grainBiz.checkRepeat(map);
        if (result == 0) {
            return "{\"valid\":true}";
        } else {
            return "{\"valid\":false}";
        }

    }


}
