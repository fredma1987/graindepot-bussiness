package com.zhoubi.graindepot.controller;

import com.zhoubi.graindepot.base.JsonResult;
import com.zhoubi.graindepot.base.PagerModel;
import com.zhoubi.graindepot.bean.Trader;
import com.zhoubi.graindepot.biz.TraderBiz;
import org.apache.commons.lang.StringUtils;
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
@RequestMapping("trader")
public class TraderController extends BaseController {
    @Autowired
    private TraderBiz traderBiz;

    @GetMapping("/list/page")
    public PagerModel traderPageList(int start, int length, String tradername) {
        PagerModel<Trader> e = new PagerModel();
        e.addOrder("createtime desc");
        e.setStart(start);
        e.setLength(length);
        if (StringUtils.isNotEmpty(tradername)) {
            e.putWhere("tradername", "%"+tradername+"%");
        }
        PagerModel<Trader> result = traderBiz.selectListByPage(e);
        return result;
    }

    @PostMapping("/edit")
    public JsonResult contracttypeEdit(Trader item) throws ParseException {

        if (item.getTraderid() == null) {
            //新增
            item.setCreatetime(new Date());
            traderBiz.insert(item);
            return new JsonResult("添加成功", true);
        } else {
            //修改
            traderBiz.update(item);
            return new JsonResult("修改成功", true);
        }

    }

    @PostMapping("/del")
    public JsonResult contracttypeDel(String ids) {
        if (StringUtils.isNotEmpty(ids)) {
            Map map = new HashMap();
            map.put("Where_IdsStr", ids);
            traderBiz.deleteMap(map);
        }
        return new JsonResult("删除成功", true);
    }

    @PostMapping("/checkRepeat")
    public String checkRepeat(String tradername, Integer traderid) {
        Map map = new HashMap();
        map.put("tradername", tradername);
        map.put("traderid", traderid);
        int result = traderBiz.checkRepeat(map);
        if (result == 0) {
            return "{\"valid\":true}";
        } else {
            return "{\"valid\":false}";
        }

    }


}
