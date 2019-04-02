package com.zhoubi.graindepot.controller;

import com.zhoubi.graindepot.base.JsonResult;
import com.zhoubi.graindepot.base.PagerModel;
import com.zhoubi.graindepot.bean.*;
import com.zhoubi.graindepot.biz.BlanklistBiz;
import com.zhoubi.graindepot.biz.IndividualBiz;
import com.zhoubi.graindepot.biz.TraderBiz;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.*;

/**
 * Created by 1A12 on 2019/1/19/0019.
 */
@RestController
@RequestMapping("blanklist")
public class BlanklistController extends BaseController {
    @Autowired
    private BlanklistBiz blanklistBiz;
    @Autowired
    private IndividualBiz individualBiz;
    @Autowired
    private TraderBiz traderBiz;

    @GetMapping("/list/page")
    public PagerModel blanklistPageList(int start, int length, Integer blanklisttype) {
        UserAddress ua=getUserAddress();
        PagerModel<Blanklist> e = new PagerModel();
        //e.addOrder("sequno asc");
        e.setStart(start);
        e.setLength(length);
        e.putWhere("graindepotid",ua.getGraindepotid());
        e.putWhere("blanklisttype",blanklisttype);
        PagerModel<Blanklist> result = blanklistBiz.selectListByPage(e);
        return result;
    }

    @PostMapping("/edit")
    public JsonResult blanklistEdit(String ids,String reason,Integer blanklisttype) throws ParseException {
        BaseUser user=getCurrentUser();
        UserAddress ua=getUserAddress();
        List<Blanklist> blanklists=new ArrayList();
        if (StringUtils.isNotEmpty(ids)) {
            String[] id=ids.split(",");
            for (int i=0;i<id.length;i++) {
                Blanklist item=new Blanklist();
                item.setRefid(Integer.valueOf(id[i]));
                item.setBlanklisttype(blanklisttype);
                item.setGroupid(ua.getGroupid());
                item.setCompanyid(ua.getCompanyid());
                item.setGraindepotid(ua.getGraindepotid());
                item.setReason(reason);
                item.setCreatetime(new Date());
                item.setCreateuserid(user.getUserid());
                item.setSpstate(0);
                blanklists.add(item);
            }
        }
        blanklistBiz.insertList(blanklists);
        return new JsonResult("保存成功", true);

    }

    @PostMapping("/edit/reason")
    public JsonResult blanklistEdit(Integer id,String reason) throws ParseException {
        BaseUser user=getCurrentUser();
        UserAddress ua=getUserAddress();
        Map map=new HashMap();
        map.put("Where_keyid",id);
        map.put("reason",reason);
        blanklistBiz.updateMap(map);
        return new JsonResult("保存成功", true);

    }

    @PostMapping("/del")
    public JsonResult blanklistDel(String ids) {
        if (StringUtils.isNotEmpty(ids)) {
            Map map = new HashMap();
            map.put("Where_IdsStr", ids);
            blanklistBiz.deleteMap(map);
        }
        return new JsonResult("删除成功", true);
    }

    //获取还未申请列入黑名单的涉粮用户 包含审核不通过用户
    @GetMapping("/unBlankIndividual/page/list")
    public PagerModel unBlankIndividualList(int start, int length) {
        UserAddress ua=getUserAddress();
        PagerModel<Individual> e = new PagerModel();
        //e.addOrder("sequno asc");
        e.setStart(start);
        e.setLength(length);
        e.putWhere("graindepotid",ua.getGraindepotid());
        PagerModel<Individual> result = individualBiz.selectListByPage(e,"getUnBlankIndividualPageList","getUnBlankIndividualPageCount");
        return result;
    }
    //获取还未申请列入黑名单的涉粮用户 包含审核不通过用户
    @GetMapping("/unBlankTrader/page/list")
    public PagerModel unBlankTraderList(int start, int length) {
        UserAddress ua=getUserAddress();
        PagerModel<Trader> e = new PagerModel();
        //e.addOrder("sequno asc");
        e.setStart(start);
        e.setLength(length);
        e.putWhere("graindepotid",ua.getGraindepotid());
        PagerModel<Trader> result = traderBiz.selectListByPage(e,"getUnBlankTraderPageList","getUnBlankTraderPageCount");
        return result;
    }

   /* @PostMapping("/checkRepeat")
    public String checkRepeat(String blanklistName, Integer blanklistID) {
        Map map = new HashMap();
        map.put("blanklistName", blanklistName);
        map.put("blanklistID", blanklistID);
        int result = blanklistBiz.checkRepeat(map);
        if (result == 0) {
            return "{\"valid\":true}";
        } else {
            return "{\"valid\":false}";
        }

    }*/



}
