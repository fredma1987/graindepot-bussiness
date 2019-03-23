package com.zhoubi.graindepot.controller;

import com.zhoubi.graindepot.base.JsonResult;
import com.zhoubi.graindepot.base.PagerModel;
import com.zhoubi.graindepot.bean.BaseUser;
import com.zhoubi.graindepot.bean.Equip;
import com.zhoubi.graindepot.bean.UserAddress;
import com.zhoubi.graindepot.biz.EquipBiz;
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
@RequestMapping("equip")
public class EquipController extends BaseController {
    @Autowired
    private EquipBiz equipBiz;

    @GetMapping("/list/page")
    public PagerModel equipPageList(int start, int length, Integer equiptypeid) {
        UserAddress ua=getUserAddress();
        PagerModel<Equip> e = new PagerModel();
        e.addOrder("sequno asc");
        e.setStart(start);
        e.setLength(length);
        e.putWhere("equiptypeid",equiptypeid);
        e.putWhere("graindepotid",ua.getGraindepotid());
        PagerModel<Equip> result = equipBiz.selectListByPage(e);
        return result;
    }

    @PostMapping("/edit")
    public JsonResult equipEdit(Equip item) throws ParseException {
        BaseUser user=getCurrentUser();
        UserAddress ua=getUserAddress();
        String indatestr = item.getIndatestr();
        if (StringUtils.isNotEmpty(indatestr)) {
            item.setIndate(DateUtils.parseDate(indatestr, "yyyy-MM-dd"));
        }
        String usedatestr = item.getUsedatestr();
        if (StringUtils.isNotEmpty(usedatestr)) {
            item.setUsedate(DateUtils.parseDate(usedatestr, "yyyy-MM-dd"));
        }
        String producingdatestr = item.getProducingdatestr();
        if (StringUtils.isNotEmpty(producingdatestr)) {
            item.setProducingdate(DateUtils.parseDate(producingdatestr, "yyyy-MM-dd"));
        }
        String outfacdatestr = item.getOutfacdatestr();
        if (StringUtils.isNotEmpty(outfacdatestr)) {
            item.setOutfacdate(DateUtils.parseDate(outfacdatestr, "yyyy-MM-dd"));
        }
        String gaveupdatestr = item.getGaveupdatestr();
        if (StringUtils.isNotEmpty(gaveupdatestr)) {
            item.setGaveupdate(DateUtils.parseDate(gaveupdatestr, "yyyy-MM-dd"));
        }

        if (item.getEquipid() == null) {
            //新增
            item.setGroupid(ua.getGroupid());
            item.setCompanyid(ua.getCompanyid());
            item.setGraindepotid(ua.getGraindepotid());
            item.setCreateuserid(user.getUserid());
            item.setCreatetime(new Date());
            equipBiz.insert(item);
            return new JsonResult("添加成功", true);
        } else {
            //修改
            item.setUpdateuserid(user.getUserid());
            item.setUpdatetime(user.getUpdatetime());
            equipBiz.update(item);
            return new JsonResult("修改成功", true);
        }

    }

    @PostMapping("/del")
    public JsonResult equipDel(String ids) {
        if (StringUtils.isNotEmpty(ids)) {
            Map map = new HashMap();
            map.put("Where_IdsStr", ids);
            equipBiz.deleteMap(map);
        }
        return new JsonResult("删除成功", true);
    }

   /* @PostMapping("/checkRepeat")
    public String checkRepeat(String equipName, Integer equipID) {
        Map map = new HashMap();
        map.put("equipName", equipName);
        map.put("equipID", equipID);
        int result = equipBiz.checkRepeat(map);
        if (result == 0) {
            return "{\"valid\":true}";
        } else {
            return "{\"valid\":false}";
        }

    }*/


}
