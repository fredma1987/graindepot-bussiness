package com.zhoubi.graindepot.controller;

import com.zhoubi.graindepot.base.JsonResult;
import com.zhoubi.graindepot.base.PagerModel;
import com.zhoubi.graindepot.bean.BaseUser;
import com.zhoubi.graindepot.bean.Equipacc;
import com.zhoubi.graindepot.bean.EquipaccDetail;
import com.zhoubi.graindepot.bean.UserAddress;
import com.zhoubi.graindepot.biz.EquipaccBiz;
import com.zhoubi.graindepot.biz.EquipaccDetailBiz;
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
 * Created by zhanghao on 2019/1/15.
 */
@RestController
@RequestMapping("equipaccDetail")
public class EquipaccDetailController extends BaseController {

    @Autowired
    private EquipaccDetailBiz equipaccDetailBiz;

    @GetMapping("/list/page")
    public PagerModel equipaccDetailPageList(int start, int length) {
        UserAddress ua = getUserAddress();
        PagerModel<EquipaccDetail> e = new PagerModel();
//        e.addOrder("createtime desc");
        e.setStart(start);
        e.setLength(length);
//        if (StringUtils.isNotEmpty(activityname)) {
//            e.putWhere("activityname", activityname);
//        }
        PagerModel<EquipaccDetail> result = equipaccDetailBiz.selectListByPage(e);
        return result;
    }

    @PostMapping("/edit")
    public JsonResult equipaccDetailEdit(EquipaccDetail item) throws ParseException {

        if (item.getKeyid() == null) {
//            String billdatestr = item.getBilldatestr();
//            if (StringUtils.isNotEmpty(billdatestr)) {
//                Date billdate = DateUtils.parseDate(billdatestr, "yyyy-MM-dd");
//                item.setBilldate(billdate);
//            }

                //新增
            equipaccDetailBiz.insert(item);

            return new JsonResult("添加成功", true);
        } else {
            //修改
            equipaccDetailBiz.update(item);
            return new JsonResult("修改成功", true);
        }

    }

    @PostMapping("/del")
    public JsonResult equipaccDetailDel(String ids) {

        if (StringUtils.isNotEmpty(ids)) {
            Map map = new HashMap();
            map.put("Where_IdsStr", ids);
            equipaccDetailBiz.deleteMap(map);
        }
        return new JsonResult("删除成功", true);
    }



}
