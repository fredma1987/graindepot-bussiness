package com.zhoubi.graindepot.controller;

import com.zhoubi.graindepot.base.JsonResult;
import com.zhoubi.graindepot.base.PagerModel;
import com.zhoubi.graindepot.bean.BaseUser;
import com.zhoubi.graindepot.bean.GrainattrUpdate;
import com.zhoubi.graindepot.bean.UserAddress;
import com.zhoubi.graindepot.biz.GrainattrUpdateBiz;
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
@RequestMapping("grainattrUpdate")
public class GrainattrUpdateController extends BaseController {
    @Autowired
    private GrainattrUpdateBiz grainattrUpdateBiz;

    @GetMapping("/list/page")
    public PagerModel grainattrUpdatePageList(int start, int length, Integer grainid) {
        UserAddress ua=getUserAddress();
        PagerModel<GrainattrUpdate> e = new PagerModel();
        e.addOrder("createtime desc");
        e.setStart(start);
        e.setLength(length);
        e.putWhere("graindepotid",ua.getGraindepotid());
        e.putWhere("grainid",grainid);
        PagerModel<GrainattrUpdate> result = grainattrUpdateBiz.selectListByPage(e);
        return result;
    }

    @PostMapping("/edit")
    public JsonResult grainattrUpdateEdit(GrainattrUpdate item) throws ParseException {
        BaseUser user=getCurrentUser();
        UserAddress ua=getUserAddress();
        if (item.getKeyid() == null) {
            //新增
            if (user!=null) {
                item.setCreateuserid(user.getUserid());
            }
            synchronized (ua.getGraindepotid()+""){
                String maxBillcode = grainattrUpdateBiz.getMaxBillcode(ua.getGraindepotid());
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
                grainattrUpdateBiz.insert(item);
                return new JsonResult("添加成功", true);
            }
        } else {
            //修改
            grainattrUpdateBiz.update(item);
            return new JsonResult("修改成功", true);
        }

    }

    @PostMapping("/del")
    public JsonResult grainattrUpdateDel(String ids) {
        if (StringUtils.isNotEmpty(ids)) {
            Map map = new HashMap();
            map.put("Where_IdsStr", ids);
            grainattrUpdateBiz.deleteMap(map);
        }
        return new JsonResult("删除成功", true);
    }

   /* @PostMapping("/checkRepeat")
    public String checkRepeat(String grainattrUpdateName, Integer grainattrUpdateID) {
        Map map = new HashMap();
        map.put("grainattrUpdateName", grainattrUpdateName);
        map.put("grainattrUpdateID", grainattrUpdateID);
        int result = grainattrUpdateBiz.checkRepeat(map);
        if (result == 0) {
            return "{\"valid\":true}";
        } else {
            return "{\"valid\":false}";
        }

    }*/


}
