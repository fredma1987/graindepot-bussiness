package com.zhoubi.graindepot.controller;

import com.zhoubi.graindepot.base.JsonResult;
import com.zhoubi.graindepot.base.PagerModel;
import com.zhoubi.graindepot.bean.BaseUser;
import com.zhoubi.graindepot.bean.Outstoreloss;
import com.zhoubi.graindepot.bean.UserAddress;
import com.zhoubi.graindepot.biz.OutstorelossBiz;
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
@RequestMapping("outstoreloss")
public class OutstorelossController extends BaseController {
    @Autowired
    private OutstorelossBiz outstorelossBiz;

    @GetMapping("/list/page")
    public PagerModel outstorelossPageList(int start, int length, Integer grainid) {
        UserAddress ua = getUserAddress();
        PagerModel<Outstoreloss> e = new PagerModel();
        e.addOrder("createtime desc");
        e.setStart(start);
        e.setLength(length);
        e.putWhere("grainid", grainid);
        e.putWhere("graindepotid", ua.getGraindepotid());
        PagerModel<Outstoreloss> result = outstorelossBiz.selectListByPage(e);
        return result;
    }

    @PostMapping("/edit")
    public JsonResult outstorelossEdit(Outstoreloss item) throws ParseException {
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
                String maxBillcode = outstorelossBiz.getMaxBillcode(ua.getGraindepotid());
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
                outstorelossBiz.insert(item);
            }
            return new JsonResult("添加成功", true);
        } else {
            //修改
            item.setUpdatetime(new Date());
            item.setUpdateuserid(user.getUserid());
            outstorelossBiz.update(item);
            return new JsonResult("修改成功", true);
        }

    }

    @PostMapping("/del")
    public JsonResult outstorelossDel(String ids) {
        if (StringUtils.isNotEmpty(ids)) {
            Map map = new HashMap();
            map.put("Where_IdsStr", ids);
            outstorelossBiz.deleteMap(map);
        }
        return new JsonResult("删除成功", true);
    }

   /* @PostMapping("/checkRepeat")
    public String checkRepeat(String outstorelossName, Integer outstorelossID) {
        Map map = new HashMap();
        map.put("outstorelossName", outstorelossName);
        map.put("outstorelossID", outstorelossID);
        int result = outstorelossBiz.checkRepeat(map);
        if (result == 0) {
            return "{\"valid\":true}";
        } else {
            return "{\"valid\":false}";
        }

    }*/


}
