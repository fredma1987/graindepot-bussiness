package com.zhoubi.graindepot.controller;

import com.zhoubi.graindepot.base.JsonResult;
import com.zhoubi.graindepot.base.PagerModel;
import com.zhoubi.graindepot.bean.BaseUser;
import com.zhoubi.graindepot.bean.Equipacc;
import com.zhoubi.graindepot.bean.Stolenfire;
import com.zhoubi.graindepot.bean.UserAddress;
import com.zhoubi.graindepot.biz.EquipaccBiz;
import com.zhoubi.graindepot.biz.StolenfireBiz;
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
 * Created by zhanghao on 2019/1/15.
 */
@RestController
@RequestMapping("equipacc")
public class EquipaccController extends BaseController {

    @Autowired
    private EquipaccBiz equipaccBiz;

    @GetMapping("/list/page")
    public PagerModel equipaccPageList(int start, int length,String position) {
        UserAddress ua = getUserAddress();
        PagerModel<Equipacc> e = new PagerModel();
        e.addOrder("createtime desc");
        e.setStart(start);
        e.setLength(length);
        if (StringUtils.isNotEmpty(position)) {
            e.putWhere("position", position);
        }
        PagerModel<Equipacc> result = equipaccBiz.selectListByPage(e);
        return result;
    }

    @PostMapping("/edit")
    public JsonResult equipaccEdit(Equipacc item) throws ParseException {
        UserAddress ua = getUserAddress();
        BaseUser baseUser = getCurrentUser();
        if (item.getEquipaccid() == null) {
            String occurtimestr = item.getOccurtimestr();
            if (StringUtils.isNotEmpty(occurtimestr)) {
                Date occurtime = DateUtils.parseDate(occurtimestr, "yyyy-MM-dd");
                item.setOccurtime(occurtime);
            }


                //新增
                item.setGroupid(ua.getGroupid());
                item.setGraindepotid(ua.getGraindepotid());
                item.setCompanyid(ua.getCompanyid());
                if (baseUser != null) {
                    item.setCreateuserid(baseUser.getUserid());
                }
                item.setCreatetime(new Date());
            equipaccBiz.insert(item);

            return new JsonResult("添加成功", true);
        } else {
            //修改
            equipaccBiz.update(item);
            return new JsonResult("修改成功", true);
        }

    }

    @PostMapping("/del")
    public JsonResult equipaccDel(String ids) {

        if (StringUtils.isNotEmpty(ids)) {
            Map map = new HashMap();
            map.put("Where_IdsStr", ids);
            equipaccBiz.deleteMap(map);
        }
        return new JsonResult("删除成功", true);
    }



}
