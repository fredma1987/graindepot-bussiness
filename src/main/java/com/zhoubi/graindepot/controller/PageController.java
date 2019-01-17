package com.zhoubi.graindepot.controller;

import com.zhoubi.graindepot.bean.BaseUser;
import com.zhoubi.graindepot.bean.Planfile;
import com.zhoubi.graindepot.bean.PlanfileDetail;
import com.zhoubi.graindepot.bean.UserBean;
import com.zhoubi.graindepot.biz.PlanfileBiz;
import com.zhoubi.graindepot.biz.PlanfileDetailBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhanghao on 2019/1/2.
 */
@Controller
@RequestMapping("/page")
public class PageController extends BaseController {
    @Autowired
    private PlanfileBiz planfileBiz;
    @Autowired
    private PlanfileDetailBiz planfileDetailBiz;

    //计划信息列表
    @GetMapping("/planfile")
    public String toPlanfile(Model model) {
        String title = "计划文件";
        model.addAttribute("title", title);
        String path = "/planfile/list";
        return path;
    }

    //计划信息编辑
    @GetMapping("/planfile/edit")
    public String toPlanfile_edit(Model model, Integer id) {
        String title = "计划文件编辑";
        model.addAttribute("title", title);
        model.addAttribute("id", id);
        Planfile item = new Planfile();
        if (id != null) {
            item = planfileBiz.selectById(id);
        }
        model.addAttribute("item", item);
        String path = "/planfile/edit";
        return path;
    }
    //计划信息详情页
    @GetMapping("/planfile/detail/{id}")
    public String toPlanfile_detail(Model model, @PathVariable int id) {
        String title = "计划信息详情";
        Planfile item = planfileBiz.selectById(id);
        model.addAttribute("title", title);
        model.addAttribute("item", item);
        String path = "/planfile/detail";
        return path;
    }

    //计划文件详情
    @GetMapping("/planfileDetail")
    public String toPlanfileDetail(Model model) {
        String title = "计划详情";
        model.addAttribute("title", title);
        String path = "/planfileDetail/list";
        return path;
    }


    //计划文件详情编辑
    @GetMapping("/planfileDetail/edit")
    public String toPlanfileDetail_edit(Model model, Integer id) {
        String title = "计划文件编辑";
        model.addAttribute("title", title);
        model.addAttribute("id", id);
        PlanfileDetail item = new PlanfileDetail();
        if (id != null) {
            item = planfileDetailBiz.selectById(id);
        }
        model.addAttribute("item", item);
        String path = "/planfileDetail/edit";
        return path;
    }
    //计划信息详情页
    @GetMapping("/planfileDetail/detail/{id}")
    public String toPlanfileDetail_detail(Model model, @PathVariable int id) {
        String title = "计划信息详情";
        PlanfileDetail item = planfileDetailBiz.selectById(id);
        model.addAttribute("title", title);
        model.addAttribute("item", item);
        String path = "/planfileDetail/detail";
        return path;
    }

}
