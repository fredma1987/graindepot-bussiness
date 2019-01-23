package com.zhoubi.graindepot.controller;

import com.zhoubi.graindepot.base.JsonResult;
import com.zhoubi.graindepot.base.PagerModel;
import com.zhoubi.graindepot.bean.BaseUser;
import com.zhoubi.graindepot.bean.Contract;
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
    public PagerModel contractPageList(int start, int length, String contractName) {
        PagerModel<Contract> e = new PagerModel();
        e.addOrder("createtime desc");
        e.setStart(start);
        e.setLength(length);
        if (StringUtils.isNotEmpty(contractName)) {
            e.putWhere("contractName", "%"+contractName+"%");
        }
        PagerModel<Contract> result = contractBiz.selectListByPage(e);
        return result;
    }

    @PostMapping("/edit")
    public JsonResult contractEdit(Contract item) throws ParseException {
        BaseUser user=getCurrentUser();
        Double tqty=item.gettQty();
        if (tqty!=null) {
            item.setkGQty(tqty * 1000);
        }
        Double tprice=item.gettPrice();
        if (tprice!=null) {
            item.settPrice(tprice * 1000);
        }
        String signDateStr=item.getSignDateStr();
        if (StringUtils.isNotEmpty(signDateStr)) {
            item.setSignDate(DateUtils.parseDate(signDateStr, "yyyy-MM-dd"));
        }
        String execDateStr=item.getExecDateStr();
        if (StringUtils.isNotEmpty(execDateStr)) {
            item.setExecDate(DateUtils.parseDate(execDateStr, "yyyy-MM-dd"));
        }
        String honourBeginDateStr=item.getHonourBeginDateStr();
        if (StringUtils.isNotEmpty(honourBeginDateStr)) {
            item.setHonourBeginDate(DateUtils.parseDate(honourBeginDateStr, "yyyy-MM-dd"));
        }
        String honourEndDateStr=item.getHonourEndDateStr();
        if (StringUtils.isNotEmpty(honourEndDateStr)) {
            item.setHonourEndDate(DateUtils.parseDate(honourEndDateStr, "yyyy-MM-dd"));
        }

        if (item.getContractid() == null) {
            //新增
            if (user!=null) {
                item.setCreateuserid(user.getUserid());
            }
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
