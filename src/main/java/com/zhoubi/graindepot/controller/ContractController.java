package com.zhoubi.graindepot.controller;

import com.zhoubi.graindepot.base.JsonResult;
import com.zhoubi.graindepot.base.PagerModel;
import com.zhoubi.graindepot.bean.BaseUser;
import com.zhoubi.graindepot.bean.Contract;
import com.zhoubi.graindepot.bean.UserAddress;
import com.zhoubi.graindepot.biz.ContractBiz;
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
@RequestMapping("contract")
public class ContractController extends BaseController {
    @Autowired
    private ContractBiz contractBiz;

    @GetMapping("/list/page")
    public PagerModel contractPageList(int start, int length, String contractname) {
        UserAddress ua=getUserAddress();
        PagerModel<Contract> e = new PagerModel();
        e.addOrder("createtime desc");
        e.setStart(start);
        e.setLength(length);
        if (StringUtils.isNotEmpty(contractname)) {
            e.putWhere("contractname", "%"+contractname+"%");
        }
        e.putWhere("graindepotid",ua.getGraindepotid());
        PagerModel<Contract> result = contractBiz.selectListByPage(e);
        return result;
    }

    @PostMapping("/edit")
    public JsonResult contractEdit(Contract item) throws ParseException {
        BaseUser user=getCurrentUser();
        UserAddress ua=getUserAddress();
        Double tqty=item.getTqty();
        if (tqty!=null) {
            item.setKgqty(tqty * 1000);
        }
        Double tprice=item.getTprice();
        if (tprice!=null) {
            item.setTprice(tprice * 1000);
        }
        String signdatestr=item.getSigndatestr();
        if (StringUtils.isNotEmpty(signdatestr)) {
            item.setSigndate(DateUtils.parseDate(signdatestr, "yyyy-MM-dd"));
        }
        String execdatestr=item.getExecdatestr();
        if (StringUtils.isNotEmpty(execdatestr)) {
            item.setExecdate(DateUtils.parseDate(execdatestr, "yyyy-MM-dd"));
        }
        String honourbegindatestr=item.getHonourbegindatestr();
        if (StringUtils.isNotEmpty(honourbegindatestr)) {
            item.setHonourbegindate(DateUtils.parseDate(honourbegindatestr, "yyyy-MM-dd"));
        }
        String honourenddatestr=item.getHonourenddatestr();
        if (StringUtils.isNotEmpty(honourenddatestr)) {
            item.setHonourenddate(DateUtils.parseDate(honourenddatestr, "yyyy-MM-dd"));
        }

        if (item.getContractid() == null) {
            //新增
            if (user!=null) {
                item.setCreateuserid(user.getUserid());
            }
            item.setGroupid(ua.getGroupid());
            item.setCompanyid(ua.getCompanyid());
            item.setGraindepotid(ua.getGraindepotid());
            item.setCreatetime(new Date());
            contractBiz.insert(item);
            return new JsonResult("添加成功", true);
        } else {
            //修改
            contractBiz.update(item);
            return new JsonResult("修改成功", true);
        }

    }

    @PostMapping("/del")
    public JsonResult contractDel(String ids) {
        if (StringUtils.isNotEmpty(ids)) {
            Map map = new HashMap();
            map.put("Where_IdsStr", ids);
            contractBiz.deleteMap(map);
        }
        return new JsonResult("删除成功", true);
    }

   /* @PostMapping("/checkRepeat")
    public String checkRepeat(String contractName, Integer contractID) {
        Map map = new HashMap();
        map.put("contractName", contractName);
        map.put("contractID", contractID);
        int result = contractBiz.checkRepeat(map);
        if (result == 0) {
            return "{\"valid\":true}";
        } else {
            return "{\"valid\":false}";
        }

    }*/


}
