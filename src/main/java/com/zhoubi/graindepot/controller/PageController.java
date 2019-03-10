package com.zhoubi.graindepot.controller;

import com.zhoubi.graindepot.bean.*;
import com.zhoubi.graindepot.biz.*;
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
    @Autowired
    private TraderBiz traderBiz;
    @Autowired
    private ContractBiz contractBiz;
    @Autowired
    private PlanfileInplanBiz planfileInplanBiz;
    @Autowired
    private PlanfileOutplanBiz planfileOutplanBiz;
    @Autowired
    private GqinspectBiz gqinspectBiz;

    //----------------------------------计划信息---------------------------------------------
    //计划信息列表
    @GetMapping("/planfile")
    public String toPlanfile(Model model) {
        String title = "计划文件";
        model.addAttribute("title", title);
        String path = "planfile/list";
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
        String path = "planfile/edit";
        return path;
    }

    //计划信息详情页
    @GetMapping("/planfile/detail/{id}")
    public String toPlanfile_detail(Model model, @PathVariable int id) {
        String title = "计划信息详情";
        Planfile item = planfileBiz.selectById(id);
        model.addAttribute("title", title);
        model.addAttribute("item", item);
        String path = "planfile/detail";
        return path;
    }

    //----------------------------------计划文件详情---------------------------------------------
    //计划文件详情
    @GetMapping("/planfileDetail")
    public String toPlanfileDetail(Model model) {
        String title = "计划详情";
        model.addAttribute("title", title);
        String path = "planfileDetail/list";
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
        String path = "planfileDetail/edit";
        return path;
    }

    //计划信息详情页
    @GetMapping("/planfileDetail/detail/{id}")
    public String toPlanfileDetail_detail(Model model, @PathVariable int id) {
        String title = "计划信息详情";
        PlanfileDetail item = planfileDetailBiz.selectById(id);
        model.addAttribute("title", title);
        model.addAttribute("item", item);
        String path = "planfileDetail/detail";
        return path;
    }

    //----------------------------------往来单位---------------------------------------------
    //往来单位主页
    @GetMapping("/trader")
    public String to_trader(Model model) {
        String title = "往来单位";
        model.addAttribute("title", title);
        String path = "trader/list";
        return path;
    }

    //往来单位编辑
    @GetMapping("/trader/edit")
    public String to_trader_edit(Model model, Integer id) {
        String title = "往来单位编辑";
        model.addAttribute("title", title);
        model.addAttribute("id", id);
        Trader item = new Trader();
        if (id != null) {
            item = traderBiz.selectById(id);
        }
        model.addAttribute("item", item);
        String path = "trader/edit";
        return path;
    }

    //往来单位情页
    @GetMapping("/trader/detail/{id}")
    public String to_trader_detail(Model model, @PathVariable int id) {
        String title = "合同类型详情";
        Trader item = traderBiz.selectById(id);
        model.addAttribute("title", title);
        model.addAttribute("item", item);
        String path = "trader/detail";
        return path;
    }

    //----------------------------------合同---------------------------------------------
    //合同列表主页
    @GetMapping("/contract")
    public String to_contract(Model model) {
        String title = "合同列表";
        model.addAttribute("title", title);
        String path = "contract/list";
        return path;
    }

    //合同列表编辑
    @GetMapping("/contract/edit")
    public String to_contract_edit(Model model, Integer id) {
        String title = "合同编辑";
        model.addAttribute("title", title);
        model.addAttribute("id", id);
        Contract item = new Contract();
        if (id != null) {
            item = contractBiz.selectById(id);
        }
        model.addAttribute("item", item);
        String path = "contract/edit";
        return path;
    }

    //合同列表详情页
    @GetMapping("/contract/detail/{id}")
    public String to_contract_detail(Model model, @PathVariable int id) {
        String title = "合同列表详情";
        Contract item = contractBiz.selectById(id);
        model.addAttribute("title", title);
        model.addAttribute("item", item);
        String path = "contract/detail";
        return path;
    }


    //----------------------------------入库计划---------------------------------------------
    //入库计划主页
    @GetMapping("/planfileInplan")
    public String to_PlanfileInplan(Model model) {
        String title = "入库计划";
        model.addAttribute("title", title);
        String path = "planfileInplan/list";
        return path;
    }

    //入库计划编辑
    @GetMapping("/planfileInplan/edit")
    public String to_planfileInplan_edit(Model model, Integer id, Integer planfileDetailId) {
        String title = "入库计划编辑";
        model.addAttribute("title", title);
        model.addAttribute("id", id);
        PlanfileInplan item = new PlanfileInplan();
        PlanfileDetail pfd = new PlanfileDetail();
        if (id != null) {
            item = planfileInplanBiz.selectById(id);
        }
        if (planfileDetailId != null) {
            pfd = planfileDetailBiz.selectById(planfileDetailId);
        }
        model.addAttribute("item", item);
        model.addAttribute("pfd", pfd);
        String path = "planfileInplan/edit";
        return path;
    }

    //入库计划详情页
    @GetMapping("/planfileInplan/detail/{id}")
    public String to_planfileInplan_detail(Model model, @PathVariable int id) {
        String title = "入库计划详情";
        PlanfileInplan item = planfileInplanBiz.selectById(id);
        model.addAttribute("title", title);
        model.addAttribute("item", item);
        String path = "planfileInplan/detail";
        return path;
    }

    //入库计划页跳出计划详情页
    @GetMapping("/planfileInplan/planfileDetailList")
    public String to_PlanfileInplan_planfileDetailList(Model model) {
        String title = "入库计划";
        model.addAttribute("title", title);
        String path = "planfileInplan/planfileDetailList";
        return path;
    }

    //----------------------------------出库计划---------------------------------------------
    //出库计划主页
    @GetMapping("/planfileOutplan")
    public String to_PlanfileOutplan(Model model) {
        String title = "出库计划";
        model.addAttribute("title", title);
        String path = "planfileOutplan/list";
        return path;
    }

    //出库计划编辑
    @GetMapping("/planfileOutplan/edit")
    public String to_planfileOutplan_edit(Model model, Integer id, Integer planfileDetailId) {
        String title = "出库计划编辑";
        model.addAttribute("title", title);
        model.addAttribute("id", id);
        PlanfileOutplan item = new PlanfileOutplan();
        PlanfileDetail pfd = new PlanfileDetail();
        if (id != null) {
            item = planfileOutplanBiz.selectById(id);
        }
        if (planfileDetailId != null) {
            pfd = planfileDetailBiz.selectById(planfileDetailId);
        }
        model.addAttribute("item", item);
        model.addAttribute("pfd", pfd);
        String path = "planfileOutplan/edit";
        return path;
    }

    //出库计划详情页
    @GetMapping("/planfileOutplan/detail/{id}")
    public String to_planfileOutplan_detail(Model model, @PathVariable int id) {
        String title = "出库计划详情";
        PlanfileOutplan item = planfileOutplanBiz.selectById(id);
        model.addAttribute("title", title);
        model.addAttribute("item", item);
        String path = "planfileOutplan/detail";
        return path;
    }

    //出库计划页跳出计划详情页
    @GetMapping("/planfileOutplan/planfileDetailList")
    public String to_PlanfileOutplan_planfileDetailList(Model model) {
        String title = "出库计划";
        model.addAttribute("title", title);
        String path = "planfileOutplan/planfileDetailList";
        return path;
    }

    //----------------------------------质量检测---------------------------------------------
    //质量检测主页
    @GetMapping("/gqinspect")
    public String to_gqinspect(Model model) {
        String title = "质量检测";
        model.addAttribute("title", title);
        String path = "gqinspect/list";
        return path;
    }

    //质量检测编辑
    @GetMapping("/gqinspect/edit")
    public String to_gqinspect_edit(Model model, Integer id) {
        String title = "质量检测";
        model.addAttribute("title", title);
        model.addAttribute("id", id);
        Gqinspect item = new Gqinspect();
        if (id != null) {
            item = gqinspectBiz.selectById(id);
        }
        model.addAttribute("item", item);
        String path = "gqinspect/edit";
        return path;
    }

    //合同列表详情页
    @GetMapping("/gqinspect/detail/{id}")
    public String to_gqinspect_detail(Model model, @PathVariable int id) {
        String title = "质量检测详情";
        Gqinspect item = gqinspectBiz.selectById(id);
        model.addAttribute("title", title);
        model.addAttribute("item", item);
        String path = "gqinspect/detail";
        return path;
    }

}
