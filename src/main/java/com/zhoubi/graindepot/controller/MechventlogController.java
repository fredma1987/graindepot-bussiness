package com.zhoubi.graindepot.controller;

import com.zhoubi.graindepot.base.JsonResult;
import com.zhoubi.graindepot.base.PagerModel;
import com.zhoubi.graindepot.bean.BaseUser;
import com.zhoubi.graindepot.bean.Mechventlog;
import com.zhoubi.graindepot.bean.UserAddress;
import com.zhoubi.graindepot.biz.MechventlogBiz;
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
@RequestMapping("mechventlog")
public class MechventlogController extends BaseController {
    @Autowired
    private MechventlogBiz mechventlogBiz;

    @GetMapping("/list/page")
    public PagerModel mechventlogPageList(int start, int length, String mechventlogname) {
        UserAddress ua=getUserAddress();
        PagerModel<Mechventlog> e = new PagerModel();
        e.addOrder("createtime desc");
        e.setStart(start);
        e.setLength(length);
        if (StringUtils.isNotEmpty(mechventlogname)) {
            e.putWhere("mechventlogname", "%"+mechventlogname+"%");
        }
        e.putWhere("graindepotid",ua.getGraindepotid());
        PagerModel<Mechventlog> result = mechventlogBiz.selectListByPage(e);
        return result;
    }

    @PostMapping("/edit")
    public JsonResult mechventlogEdit(Mechventlog item) throws ParseException {
        BaseUser user=getCurrentUser();
        UserAddress ua=getUserAddress();
        String begintimestr = item.getBegintimestr();
        if (StringUtils.isNotEmpty(begintimestr)) {
            Date begintime = DateUtils.parseDate(begintimestr, "yyyy-MM-dd HH:mm");
            item.setBegintime(begintime);
        }
        String endtimestr = item.getEndtimestr();
        if (StringUtils.isNotEmpty(endtimestr)) {
            Date endtime = DateUtils.parseDate(endtimestr, "yyyy-MM-dd HH:mm");
            item.setEndtime(endtime);
        }

        if (item.getKeyid() == null) {
            synchronized (ua.getGraindepotid()+""){
                String maxBillcode = mechventlogBiz.getMaxBillcode(ua.getGraindepotid());
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

                //新增
                if (user!=null) {
                    item.setCreateuserid(user.getUserid());
                }
                item.setBilldate(new Date());
                item.setCompanyid(ua.getCompanyid());
                item.setGraindepotid(ua.getGraindepotid());
                item.setCreatetime(new Date());
                mechventlogBiz.insert(item);
                return new JsonResult("添加成功", true);
            }
        } else {
            //修改
            item.setUpdatetime(new Date());
            item.setUpdateuserid(user.getUserid());
            mechventlogBiz.update(item);
            return new JsonResult("修改成功", true);
        }

    }

    @PostMapping("/del")
    public JsonResult mechventlogDel(String ids) {
        if (StringUtils.isNotEmpty(ids)) {
            Map map = new HashMap();
            map.put("Where_IdsStr", ids);
            mechventlogBiz.deleteMap(map);
        }
        return new JsonResult("删除成功", true);
    }

   /* @PostMapping("/checkRepeat")
    public String checkRepeat(String mechventlogName, Integer mechventlogID) {
        Map map = new HashMap();
        map.put("mechventlogName", mechventlogName);
        map.put("mechventlogID", mechventlogID);
        int result = mechventlogBiz.checkRepeat(map);
        if (result == 0) {
            return "{\"valid\":true}";
        } else {
            return "{\"valid\":false}";
        }

    }*/


}
