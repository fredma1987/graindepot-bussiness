package com.zhoubi.graindepot.controller;

import com.zhoubi.graindepot.base.JsonResult;
import com.zhoubi.graindepot.base.PagerModel;
import com.zhoubi.graindepot.bean.BaseUser;
import com.zhoubi.graindepot.bean.SafeInsp;
import com.zhoubi.graindepot.bean.Stolenfire;
import com.zhoubi.graindepot.bean.UserAddress;
import com.zhoubi.graindepot.biz.SafeInspBiz;
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
@RequestMapping("stolenfire")
public class StolenfireController extends BaseController {

    @Autowired
    private StolenfireBiz stolenfireBiz;

    @GetMapping("/list/page")
    public PagerModel stolenfirePageList(int start, int length,String occurarea) {
        UserAddress ua = getUserAddress();
        PagerModel<Stolenfire> e = new PagerModel();
        e.addOrder("createtime desc");
        e.setStart(start);
        e.setLength(length);
        if (StringUtils.isNotEmpty(occurarea)) {
            e.putWhere("occurarea", occurarea);
        }
        PagerModel<Stolenfire> result = stolenfireBiz.selectListByPage(e);
        return result;
    }

    @PostMapping("/edit")
    public JsonResult stolenfireEdit(Stolenfire item) throws ParseException {
        UserAddress ua = getUserAddress();
        BaseUser baseUser = getCurrentUser();
        if (item.getStolenfireid() == null) {
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
            stolenfireBiz.insert(item);

            return new JsonResult("添加成功", true);
        } else {
            //修改
            stolenfireBiz.update(item);
            return new JsonResult("修改成功", true);
        }

    }

    @PostMapping("/del")
    public JsonResult stolenfireDel(String ids) {

        if (StringUtils.isNotEmpty(ids)) {
            Map map = new HashMap();
            map.put("Where_IdsStr", ids);
            stolenfireBiz.deleteMap(map);
        }
        return new JsonResult("删除成功", true);
    }



}
