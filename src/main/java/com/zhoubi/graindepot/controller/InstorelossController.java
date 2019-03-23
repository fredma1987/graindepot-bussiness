package com.zhoubi.graindepot.controller;

import com.zhoubi.graindepot.base.JsonResult;
import com.zhoubi.graindepot.base.PagerModel;
import com.zhoubi.graindepot.bean.BaseUser;
import com.zhoubi.graindepot.bean.Instoreloss;
import com.zhoubi.graindepot.bean.UserAddress;
import com.zhoubi.graindepot.biz.InstorelossBiz;
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
@RequestMapping("instoreloss")
public class InstorelossController extends BaseController {
    @Autowired
    private InstorelossBiz instorelossBiz;

    @GetMapping("/list/page")
    public PagerModel instorelossPageList(int start, int length, Integer grainid) {
        UserAddress ua = getUserAddress();
        PagerModel<Instoreloss> e = new PagerModel();
        e.addOrder("createtime desc");
        e.setStart(start);
        e.setLength(length);
        e.putWhere("grainid", grainid);
        e.putWhere("graindepotid", ua.getGraindepotid());
        PagerModel<Instoreloss> result = instorelossBiz.selectListByPage(e);
        return result;
    }

    @PostMapping("/edit")
    public JsonResult instorelossEdit(Instoreloss item) throws ParseException {
        BaseUser user = getCurrentUser();
        UserAddress ua = getUserAddress();
        String instordatestr = item.getInstordatestr();
        if (StringUtils.isNotEmpty(instordatestr)) {
            item.setInstordate(DateUtils.parseDate(instordatestr, "yyyy-MM-dd"));
        }
        if (item.getBillid() == null) {
            //新增
            if (user != null) {
                item.setCreateuserid(user.getUserid());
            }
            synchronized (ua.getGraindepotid() + "") {
                String billcode = instorelossBiz.getMaxBillcode(ua.getGraindepotid());
                item.setBillcode(billcode);
                item.setBilldate(new Date());
                item.setGroupid(ua.getGroupid());
                item.setCompanyid(ua.getCompanyid());
                item.setGraindepotid(ua.getGraindepotid());
                item.setCreatetime(new Date());
                item.setCreateuserid(user.getUserid());
                instorelossBiz.insert(item);
            }
            return new JsonResult("添加成功", true);
        } else {
            //修改
            item.setUpdatetime(new Date());
            item.setUpdateuserid(user.getUserid());
            instorelossBiz.update(item);
            return new JsonResult("修改成功", true);
        }

    }

    @PostMapping("/del")
    public JsonResult instorelossDel(String ids) {
        if (StringUtils.isNotEmpty(ids)) {
            Map map = new HashMap();
            map.put("Where_IdsStr", ids);
            instorelossBiz.deleteMap(map);
        }
        return new JsonResult("删除成功", true);
    }

   /* @PostMapping("/checkRepeat")
    public String checkRepeat(String instorelossName, Integer instorelossID) {
        Map map = new HashMap();
        map.put("instorelossName", instorelossName);
        map.put("instorelossID", instorelossID);
        int result = instorelossBiz.checkRepeat(map);
        if (result == 0) {
            return "{\"valid\":true}";
        } else {
            return "{\"valid\":false}";
        }

    }*/


}
