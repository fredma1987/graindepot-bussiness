package com.zhoubi.graindepot.controller;

import com.zhoubi.graindepot.base.JsonResult;
import com.zhoubi.graindepot.base.PagerModel;
import com.zhoubi.graindepot.bean.BaseUser;
import com.zhoubi.graindepot.bean.Notice;
import com.zhoubi.graindepot.bean.UserAddress;
import com.zhoubi.graindepot.biz.NoticeBiz;
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
@RequestMapping("notice")
public class NoticeController extends BaseController {
    @Autowired
    private NoticeBiz noticeBiz;

    @GetMapping("/list/page")
    public PagerModel noticePageList(int start, int length
            , Integer noticetypeid, Integer spflag, Integer fbflag) {
        UserAddress ua = getUserAddress();
        PagerModel<Notice> e = new PagerModel();
        e.addOrder("createtime desc");
        e.setStart(start);
        e.setLength(length);
        e.putWhere("noticetypeid", noticetypeid);
        e.putWhere("graindepotid", ua.getGraindepotid());
        e.putWhere("spflag", spflag);
        e.putWhere("fbflag", fbflag);
        PagerModel<Notice> result = noticeBiz.selectListByPage(e);
        return result;
    }

    @GetMapping("/approve/list/page")
    public PagerModel approve_noticePageList(int start, int length
            , Integer noticetypeid, Integer spflag, Integer fbflag) {
        BaseUser user = getCurrentUser();
        UserAddress ua = getUserAddress();
        PagerModel<Notice> e = new PagerModel();
        e.addOrder("createtime desc");
        e.setStart(start);
        e.setLength(length);
        e.putWhere("noticetypeid", noticetypeid);
        e.putWhere("graindepotid", ua.getGraindepotid());
        e.putWhere("spuserid", user.getUserid());
        e.putWhere("spflag", spflag);
        e.putWhere("fbflag", fbflag);
        PagerModel<Notice> result = noticeBiz.selectListByPage(e);
        return result;
    }

    @GetMapping("/release/list/page")
    public PagerModel release_noticePageList(int start, int length
            , Integer noticetypeid, Integer fbflag) {
        BaseUser user = getCurrentUser();
        UserAddress ua = getUserAddress();
        PagerModel<Notice> e = new PagerModel();
        e.addOrder("createtime desc");
        e.setStart(start);
        e.setLength(length);
        e.putWhere("noticetypeid", noticetypeid);
        e.putWhere("graindepotid", ua.getGraindepotid());
        e.putWhere("noticeuserid", user.getUserid());
        e.putWhere("fbflag", fbflag);
        e.putWhere("spflag", 1);
        PagerModel<Notice> result = noticeBiz.selectListByPage(e);
        return result;
    }

    @PostMapping("/edit")
    public JsonResult noticeEdit(Notice item) throws ParseException {
        BaseUser user = getCurrentUser();
        UserAddress ua = getUserAddress();
        /*String honourenddatestr=item.getHonourenddatestr();
        if (StringUtils.isNotEmpty(honourenddatestr)) {
            item.setHonourenddate(DateUtils.parseDate(honourenddatestr, "yyyy-MM-dd"));
        }*/
        if (item.getNoticeid() == null) {
            //新增
            item.setCreateuserid(user.getUserid());
            item.setGroupid(ua.getGroupid());
            item.setCompanyid(ua.getCompanyid());
            item.setGraindepotid(ua.getGraindepotid());
            item.setCreatetime(new Date());
            item.setSpflag(0);
            item.setFbflag(0);
            noticeBiz.insert(item);
            return new JsonResult("添加成功", true);
        } else {
            //修改
            noticeBiz.updateApply(item);
            return new JsonResult("修改成功", true);
        }

    }

    @PostMapping("/del")
    public JsonResult noticeDel(String ids) {
        if (StringUtils.isNotEmpty(ids)) {
            Map map = new HashMap();
            map.put("Where_IdsStr", ids);
            noticeBiz.deleteMap(map);
        }
        return new JsonResult("删除成功", true);
    }

    @PostMapping("/approve/edit")
    public JsonResult approve_edit(Integer spflag, Integer id) {
        Map map = new HashMap();
        map.put("Where_noticeid", id);
        map.put("spflag", spflag);
        map.put("sptime", new Date());
        noticeBiz.updateMap(map);
        return new JsonResult("审核成功", true);
    }

    @PostMapping("/release/edit")
    public JsonResult release_edit(Notice item) throws ParseException {
        String noticebgtimestr=item.getNoticebgtimestr();
        if (StringUtils.isNotEmpty(noticebgtimestr)) {
            item.setNoticebgtime(DateUtils.parseDate(noticebgtimestr, "yyyy-MM-dd HH:mm"));
        }
        String noticeendtimestr=item.getNoticeendtimestr();
        if (StringUtils.isNotEmpty(noticeendtimestr)) {
            item.setNoticeendtime(DateUtils.parseDate(noticeendtimestr, "yyyy-MM-dd HH:mm"));
        }
        item.setNoticetime(new Date());
        noticeBiz.updateRelease(item);
        return new JsonResult("发布成功", true);
    }

   /* @PostMapping("/checkRepeat")
    public String checkRepeat(String noticeName, Integer noticeID) {
        Map map = new HashMap();
        map.put("noticeName", noticeName);
        map.put("noticeID", noticeID);
        int result = noticeBiz.checkRepeat(map);
        if (result == 0) {
            return "{\"valid\":true}";
        } else {
            return "{\"valid\":false}";
        }

    }*/


}
