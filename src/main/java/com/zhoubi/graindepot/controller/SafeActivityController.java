package com.zhoubi.graindepot.controller;

import com.zhoubi.graindepot.base.JsonResult;
import com.zhoubi.graindepot.base.PagerModel;
import com.zhoubi.graindepot.bean.BaseUser;
import com.zhoubi.graindepot.bean.SafeActivity;
import com.zhoubi.graindepot.bean.SafeActivityPtc;
import com.zhoubi.graindepot.bean.UserAddress;
import com.zhoubi.graindepot.biz.SafeActivityBiz;
import com.zhoubi.graindepot.biz.SafeActivityPtcBiz;
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
@RequestMapping("safeActivity")
public class SafeActivityController extends BaseController {

    @Autowired
    private SafeActivityBiz safeActivityBiz;
    @Autowired
    private SafeActivityPtcBiz safeActivityPtcBiz;

    @GetMapping("/list/page")
    public PagerModel safeActivityPageList(int start, int length,String activityname) {
        UserAddress ua = getUserAddress();
        PagerModel<SafeActivity> e = new PagerModel();
//        e.addOrder("createtime desc");
        e.setStart(start);
        e.setLength(length);
        if (StringUtils.isNotEmpty(activityname)) {
            e.putWhere("activityname", activityname);
        }
        PagerModel<SafeActivity> result = safeActivityBiz.selectListByPage(e);
        return result;
    }

    @PostMapping("/edit")
    public JsonResult safeActivityEdit(SafeActivity item, String namestr) throws ParseException {
        UserAddress ua = getUserAddress();
        BaseUser baseUser = getCurrentUser();
        if (item.getActivityid() == null) {
            String activitytimestr = item.getActivitytimestr();
            if (StringUtils.isNotEmpty(activitytimestr)) {
                Date activitytime = DateUtils.parseDate(activitytimestr, "yyyy-MM-dd HH:mm");
                item.setActivitytime(activitytime);
            }

            //新增
            item.setGroupid(ua.getGroupid());
            item.setGraindepotid(ua.getGraindepotid());
            item.setCompanyid(ua.getCompanyid());
            if (baseUser != null) {
                item.setCreateuserid(baseUser.getUserid());
            }
            item.setCreatetime(new Date());
            safeActivityBiz.insert(item);

            //保存参加人员
            SafeActivityPtc safeActivityPtc = new SafeActivityPtc();
            safeActivityPtc.setActivityid(item.getActivityid());
            safeActivityPtc.setParticipant(namestr);
            safeActivityPtcBiz.insert(safeActivityPtc);
            return new JsonResult("添加成功", true);
        } else {
            //修改
            safeActivityBiz.update(item);
            return new JsonResult("修改成功", true);
        }

    }

    @PostMapping("/del")
    public JsonResult safeActivityDel(String ids) {

        if (StringUtils.isNotEmpty(ids)) {
            Map map = new HashMap();
            map.put("Where_IdsStr", ids);
            safeActivityBiz.deleteMap(map);
        }
        return new JsonResult("删除成功", true);
    }


}
