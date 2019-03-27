package com.zhoubi.graindepot.controller;

import com.zhoubi.graindepot.base.JsonResult;
import com.zhoubi.graindepot.base.PagerModel;
import com.zhoubi.graindepot.bean.BaseUser;
import com.zhoubi.graindepot.bean.Druginstore;
import com.zhoubi.graindepot.bean.UserAddress;
import com.zhoubi.graindepot.biz.DruginstoreBiz;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 1A12 on 2019/1/19/0019.
 */
@RestController
@RequestMapping("druginstore")
public class DruginstoreController extends BaseController {
    @Autowired
    private DruginstoreBiz druginstoreBiz;

    @GetMapping("/list/page")
    public PagerModel druginstorePageList(int start, int length, Integer intype) {
        UserAddress ua=getUserAddress();
        PagerModel<Druginstore> e = new PagerModel();
        e.addOrder("createtime desc");
        e.setStart(start);
        e.setLength(length);
        e.putWhere("intype",intype);
        e.putWhere("graindepotid",ua.getGraindepotid());
        PagerModel<Druginstore> result = druginstoreBiz.selectListByPage(e);
        return result;
    }

    @PostMapping("/edit")
    public JsonResult druginstoreEdit(Druginstore item) throws ParseException {
        BaseUser user = getCurrentUser();
        UserAddress ua = getUserAddress();
        String indatestr = item.getIndatestr();
        if (StringUtils.isNotEmpty(indatestr)) {
            item.setIndate(DateUtils.parseDate(indatestr, "yyyy-MM-dd"));
        }
        if (item.getBillid() == null) {
            //新增
            if (user != null) {
                item.setCreateuserid(user.getUserid());
            }
            synchronized (ua.getGraindepotid() + "") {
                String maxBillcode = druginstoreBiz.getMaxBillcode(ua.getGraindepotid());
                if (org.apache.commons.lang3.StringUtils.isNotEmpty(maxBillcode)) {
                    //能找到当天最大的单据号
                    String[] maxBillcodes = maxBillcode.split("-");
                    item.setBillcode(maxBillcodes[0] + "-" + maxBillcodes[1]
                            + "-" + maxBillcodes[2] + "-" + String.format("%04d", Integer.parseInt(maxBillcodes[3]) + 1));
                } else {
                    //不能能找到当天最大的单据号
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String format = sdf.format(new Date());
                    item.setBillcode(format + "-0001");
                }
                item.setBilldate(new Date());
                item.setGroupid(ua.getGroupid());
                item.setCompanyid(ua.getCompanyid());
                item.setGraindepotid(ua.getGraindepotid());
                item.setCreatetime(new Date());
                item.setCreateuserid(user.getUserid());
                druginstoreBiz.insert(item);
            }
            return new JsonResult("添加成功", true);
        } else {
            //修改
            item.setUpdatetime(new Date());
            item.setUpdateuserid(user.getUserid());
            druginstoreBiz.update(item);
            return new JsonResult("修改成功", true);
        }

    }

    @PostMapping("/del")
    public JsonResult druginstoreDel(String ids) {
        if (StringUtils.isNotEmpty(ids)) {
            Map map = new HashMap();
            map.put("Where_IdsStr", ids);
            druginstoreBiz.deleteMap(map);
        }
        return new JsonResult("删除成功", true);
    }

   /* @PostMapping("/checkRepeat")
    public String checkRepeat(String druginstoreName, Integer druginstoreID) {
        Map map = new HashMap();
        map.put("druginstoreName", druginstoreName);
        map.put("druginstoreID", druginstoreID);
        int result = druginstoreBiz.checkRepeat(map);
        if (result == 0) {
            return "{\"valid\":true}";
        } else {
            return "{\"valid\":false}";
        }

    }*/


}
