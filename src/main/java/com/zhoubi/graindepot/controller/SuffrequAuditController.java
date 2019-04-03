package com.zhoubi.graindepot.controller;

import com.zhoubi.graindepot.base.JsonResult;
import com.zhoubi.graindepot.base.PagerModel;
import com.zhoubi.graindepot.bean.BaseUser;
import com.zhoubi.graindepot.bean.Suffrequ;
import com.zhoubi.graindepot.bean.UserAddress;
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
@RequestMapping("suffrequAudit")
public class SuffrequAuditController extends BaseController {

    @Autowired
    private SuffrequBiz suffrequBiz;


    //熏蒸审批
    @GetMapping("/auditList/page")
    public PagerModel suffrequAuditPageList(int start, int length) {
        UserAddress ua = getUserAddress();
        BaseUser baseUser = getCurrentUser();
        PagerModel<Suffrequ> e = new PagerModel();
        e.addOrder("createtime desc");
        e.setStart(start);
        e.setLength(length);
        Integer userid = baseUser.getUserid();
        if(userid != null){
            //查询当前登录用户待审批熏蒸申请单
            e.putWhere("userid",userid);
        }
//        if (StringUtils.isNotEmpty(billcode)) {
//            e.putWhere("billcode", billcode);
//        } else if (billkind != null) {
//            e.putWhere("billkind", billkind);
//        }
        PagerModel<Suffrequ> result = suffrequBiz.selectAuditListByPage(e);
        return result;
    }

    @PostMapping("/audit")
    public JsonResult suffrequAudit(Suffrequ item,Integer audistate,String audiopinion) throws ParseException {
        UserAddress ua = getUserAddress();
        BaseUser baseUser = getCurrentUser();
        //查询表单状态
        Suffrequ suffrequ = suffrequBiz.selectById(item.getBillid());
        if (item.getBillid() != null && audistate != -1) {
            if(suffrequ.getFormstatus() == 0){
                suffrequ.setBillid(item.getBillid());
                suffrequ.setAudistate1( 1);
                suffrequ.setFormstatus(1);
                suffrequ.setAudiopinion1(audiopinion);
                suffrequ.setAuditime1(new Date());
            }else if(suffrequ.getFormstatus() == 1){
                suffrequ.setBillid(item.getBillid());
                suffrequ.setAudistate2( 1);
                suffrequ.setFormstatus(2);
                suffrequ.setAudiopinion2(audiopinion);
                suffrequ.setAuditime2(new Date());
            }else if(suffrequ.getFormstatus() == 2){
                suffrequ.setBillid(item.getBillid());
                suffrequ.setAudistate3( 1);
                suffrequ.setFormstatus(3);
                suffrequ.setAudiopinion3(audiopinion);
                suffrequ.setAuditime3(new Date());
            }else if(suffrequ.getFormstatus() == 3){
                suffrequ.setBillid(item.getBillid());
                suffrequ.setAudistate4( 1);
                suffrequ.setFormstatus(4);
                suffrequ.setAudiopinion4(audiopinion);
                suffrequ.setAuditime4(new Date());
            }else if(suffrequ.getFormstatus() == 4){
                suffrequ.setBillid(item.getBillid());
                suffrequ.setAudistate5( 1);
                suffrequ.setFormstatus(5);
                suffrequ.setAudiopinion5(audiopinion);
                suffrequ.setAuditime5(new Date());
            }
            suffrequBiz.update(suffrequ);
            return new JsonResult("修改成功", true);
        }else if(item.getBillid() != null && audistate == -1){
            //审核不通过
            if(suffrequ.getFormstatus() == 0){
                suffrequ.setBillid(item.getBillid());
                suffrequ.setAudistate1(  -1);
                suffrequ.setFormstatus(-1);
                suffrequ.setAudiopinion1(audiopinion);
            }else if(suffrequ.getFormstatus() == 1){
                suffrequ.setBillid(item.getBillid());
                suffrequ.setAudistate2( -1);
                suffrequ.setFormstatus(-2);
                suffrequ.setAudiopinion2(audiopinion);
            }else if(suffrequ.getFormstatus() == 2){
                suffrequ.setBillid(item.getBillid());
                suffrequ.setAudistate3( -1);
                suffrequ.setFormstatus(-3);
                suffrequ.setAudiopinion3(audiopinion);
            }else if(suffrequ.getFormstatus() == 3){
                suffrequ.setBillid(item.getBillid());
                suffrequ.setAudistate4( -1);
                suffrequ.setFormstatus(-4);
                suffrequ.setAudiopinion4(audiopinion);
            }else if(suffrequ.getFormstatus() == 4){
                suffrequ.setBillid(item.getBillid());
                suffrequ.setAudistate5( -1);
                suffrequ.setFormstatus(-5);
                suffrequ.setAudiopinion5(audiopinion);
            }
//            if(suffrequ.getFormstatus() == 0){
//                suffrequ.setBillid(item.getBillid());
//                suffrequ.setAudistate1(  -1);
//                suffrequ.setFormstatus(1);
//                suffrequ.setAudiopinion1(audiopinion);
//            }else if(suffrequ.getFormstatus() == 1){
//                suffrequ.setBillid(item.getBillid());
//                suffrequ.setAudistate2( -1);
//                suffrequ.setFormstatus(2);
//                suffrequ.setAudiopinion2(audiopinion);
//            }else if(suffrequ.getFormstatus() == 2){
//                suffrequ.setBillid(item.getBillid());
//                suffrequ.setAudistate3( -1);
//                suffrequ.setFormstatus(3);
//                suffrequ.setAudiopinion3(audiopinion);
//            }else if(suffrequ.getFormstatus() == 3){
//                suffrequ.setBillid(item.getBillid());
//                suffrequ.setAudistate4( -1);
//                suffrequ.setFormstatus(4);
//                suffrequ.setAudiopinion4(audiopinion);
//            }else if(suffrequ.getFormstatus() == 4){
//                suffrequ.setBillid(item.getBillid());
//                suffrequ.setAudistate5( -1);
//                suffrequ.setFormstatus(5);
//                suffrequ.setAudiopinion5(audiopinion);
//            }
            suffrequBiz.update(suffrequ);
        }

        return new JsonResult("添加成功", true);
    }





}
