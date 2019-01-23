package com.zhoubi.graindepot.controller;

import com.zhoubi.graindepot.base.JsonResult;
import com.zhoubi.graindepot.base.PagerModel;
import com.zhoubi.graindepot.bean.Contracttype;
import com.zhoubi.graindepot.biz.ContracttypeBiz;
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
@RequestMapping("contracttype")
public class ContracttypeController extends BaseController {
    @Autowired
    private ContracttypeBiz contracttypeBiz;

    @GetMapping("/list/page")
    public PagerModel contracttypePageList(int start, int length, Integer buySellFlag) {
        PagerModel<Contracttype> e = new PagerModel();
        e.addOrder("conttypeid desc");
        e.setStart(start);
        e.setLength(length);
        if (buySellFlag != null) {
            e.putWhere("buySellFlag", buySellFlag);
        }
        PagerModel<Contracttype> result = contracttypeBiz.selectListByPage(e);
        return result;
    }

    @PostMapping("/edit")
    public JsonResult contracttypeEdit(Contracttype item) throws ParseException {

        if (item.getContTypeID() == null) {
            //新增
            contracttypeBiz.insert(item);
            return new JsonResult("添加成功", true);
        } else {
            //修改
            contracttypeBiz.update(item);
            return new JsonResult("修改成功", true);
        }

    }

    @PostMapping("/del")
    public JsonResult contracttypeDel(String ids) {
        if (StringUtils.isNotEmpty(ids)) {
            Map map = new HashMap();
            map.put("Where_IdsStr", ids);
            contracttypeBiz.deleteMap(map);
        }
        return new JsonResult("删除成功", true);
    }

    @PostMapping("/checkRepeat")
    public String checkRepeat(String contTypeName, Integer contTypeID) {
        Map map = new HashMap();
        map.put("contTypeName", contTypeName);
        map.put("contTypeID", contTypeID);
        int result = contracttypeBiz.checkRepeat(map);
        if (result == 0) {
            return "{\"valid\":true}";
        } else {
            return "{\"valid\":false}";
        }

    }


}
