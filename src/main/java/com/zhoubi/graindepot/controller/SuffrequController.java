package com.zhoubi.graindepot.controller;

import com.zhoubi.graindepot.base.JsonResult;
import com.zhoubi.graindepot.base.PagerModel;
import com.zhoubi.graindepot.bean.Allot;
import com.zhoubi.graindepot.bean.BaseUser;
import com.zhoubi.graindepot.bean.Suffrequ;
import com.zhoubi.graindepot.bean.UserAddress;
import com.zhoubi.graindepot.biz.AllotBiz;
import com.zhoubi.graindepot.biz.SuffrequBiz;
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
 * Created by zhanghao on 2019/1/15.
 */
@RestController
@RequestMapping("suffrequ")
public class SuffrequController extends BaseController {

    @Autowired
    private SuffrequBiz suffrequBiz;

    @GetMapping("/list/page")
    public PagerModel suffrequPageList(int start, int length) {
        UserAddress ua = getUserAddress();
        PagerModel<Suffrequ> e = new PagerModel();
//        e.addOrder("createtime desc");
        e.setStart(start);
        e.setLength(length);
//        if (StringUtils.isNotEmpty(billcode)) {
//            e.putWhere("billcode", billcode);
//        } else if (billkind != null) {
//            e.putWhere("billkind", billkind);
//        }
        PagerModel<Suffrequ> result = suffrequBiz.selectListByPage(e);
        return result;
    }

    @PostMapping("/edit")
    public JsonResult suffrequEdit(Suffrequ item) throws ParseException {
        UserAddress ua = getUserAddress();
        BaseUser baseUser = getCurrentUser();
        if (item.getBillid() == null) {
//            String billdatestr = item.getBilldatestr();
//            if (StringUtils.isNotEmpty(billdatestr)) {
//                Date billdate = DateUtils.parseDate(billdatestr, "yyyy-MM-dd");
//                item.setBilldate(billdate);
//            }

            synchronized (ua.getGraindepotid() + "") {
                String maxBillcode = suffrequBiz.getMaxBillcode(ua.getGraindepotid());
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
                String instordatestr = item.getInstordatestr();
                String lastsuffdatestr = item.getLastsuffdatestr();
                if (StringUtils.isNotEmpty(instordatestr)) {
                    Date instordate = DateUtils.parseDate(instordatestr, "yyyy-MM-dd");
                    item.setInstordate(instordate);
                }

                if (StringUtils.isNotEmpty(lastsuffdatestr)) {
                    Date lastsuffdate = DateUtils.parseDate(lastsuffdatestr, "yyyy-MM-dd");
                    item.setLastsuffdate(lastsuffdate);
                }
                item.setGroupid(ua.getGroupid());
                item.setGraindepotid(ua.getGraindepotid());
                item.setCompanyid(ua.getCompanyid());
                if (baseUser != null) {
                    item.setCreateuserid(baseUser.getUserid());
                }
                item.setCreatetime(new Date());
                item.setBilldate(new Date());
                //设置一级审核状态为0：待审核
                item.setAudistate1(0);
                //设置表单审核状态为0：待审核
                item.setFormstatus(0);
                suffrequBiz.insert(item);
            }
            return new JsonResult("添加成功", true);
        } else {
            //修改
            suffrequBiz.update(item);
            return new JsonResult("修改成功", true);
        }

    }

    @PostMapping("/del")
    public JsonResult suffrequDel(String ids) {

        if (StringUtils.isNotEmpty(ids)) {
            Map map = new HashMap();
            map.put("Where_IdsStr", ids);
            suffrequBiz.deleteMap(map);
        }
        return new JsonResult("删除成功", true);
    }

}
