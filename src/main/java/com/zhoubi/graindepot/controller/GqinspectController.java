package com.zhoubi.graindepot.controller;

import com.zhoubi.graindepot.base.JsonResult;
import com.zhoubi.graindepot.base.PagerModel;
import com.zhoubi.graindepot.bean.BaseUser;
import com.zhoubi.graindepot.bean.Gqinspect;
import com.zhoubi.graindepot.bean.UserAddress;
import com.zhoubi.graindepot.biz.GqinspectBiz;
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

@RestController
@RequestMapping("gqinspect")
public class GqinspectController extends BaseController {
    @Autowired
    private GqinspectBiz gqinspectBiz;

    @GetMapping("/list/page")
    public PagerModel gqinspectPageList(int start, int length, String contractname) {
        BaseUser user=getCurrentUser();
        UserAddress ua=getUserAddress();
        PagerModel<Gqinspect> e = new PagerModel();
        e.addOrder("createtime desc");
        e.setStart(start);
        e.setLength(length);
        if (StringUtils.isNotEmpty(contractname)) {
            e.putWhere("contractname", "%"+contractname+"%");
        }
        e.putWhere("graindepotid", ua.getGraindepotid());
        PagerModel<Gqinspect> result = gqinspectBiz.selectListByPage(e);
        return result;
    }


    @PostMapping("/del")
    public JsonResult contractDel(String ids) {
        if (StringUtils.isNotEmpty(ids)) {
            Map map = new HashMap();
            map.put("Where_IdsStr", ids);
            gqinspectBiz.deleteMap(map);
        }
        return new JsonResult("删除成功", true);
    }

    @PostMapping("/edit")
    public JsonResult gqinspectEdit(Gqinspect item) throws ParseException {
        BaseUser user=getCurrentUser();
        String reportdatestr = item.getReportdatestr();
        if (StringUtils.isNotEmpty(reportdatestr)) {
            item.setReportdate(DateUtils.parseDate(reportdatestr,"yyyy-MM-dd"));
        }
        String samplingdatestr = item.getSamplingdatestr();
        if (StringUtils.isNotEmpty(samplingdatestr)) {
            item.setSamplingdate(DateUtils.parseDate(samplingdatestr,"yyyy-MM-dd"));
        }
        String inspectdatestr = item.getInspectdatestr();
        if (StringUtils.isNotEmpty(inspectdatestr)) {
            item.setInspectdate(DateUtils.parseDate(inspectdatestr,"yyyy-MM-dd"));
        }
        if (item.getGqinspectid() == null) {
            //新增
            item.setGroupid(user.getGroupid());
            item.setCompanyid(user.getCompanyid());
            item.setGraindepotid(user.getGraindepotid());
            item.setCreateuserid(user.getUserid());
            item.setCreatetime(new Date());
            gqinspectBiz.insert(item);
            return new JsonResult("添加成功", true);
        } else {
            //修改
            gqinspectBiz.update(item);
            return new JsonResult("修改成功", true);
        }

    }

}
