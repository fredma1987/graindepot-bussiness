package com.zhoubi.graindepot.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.netflix.discovery.converters.Auto;
import com.zhoubi.graindepot.bean.*;
import com.zhoubi.graindepot.biz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

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
    @Autowired
    private ReserveScaleBiz reserveScaleBiz;
    @Autowired
    private IndividualBiz individualBiz;
    @Autowired
    private SampleBiz sampleBiz;
    @Autowired
    private InstorelossBiz instorelossBiz;
    @Autowired
    private EquiptypeBiz equiptypeBiz;
    @Autowired
    private EquipBiz equipBiz;
    @Autowired
    private DrugkindBiz drugkindBiz;
    @Autowired
    private DrugBiz drugBiz;
    @Autowired
    private GrainattrUpdateBiz grainattrUpdateBiz;
    @Autowired
    private OutstorelossBiz outstorelossBiz;
    @Autowired
    private DruginstoreBiz druginstoreBiz;
    @Autowired
    private DruginstoreDetailBiz druginstoreDetailBiz;
    @Autowired
    private DrugoutstoreBiz drugoutstoreBiz;
    @Autowired
    private DrugoutstoreDetailBiz drugoutstoreDetailBiz;
    @Autowired
    private GoodstypeBiz goodstypeBiz;
    @Autowired
    private GoodsBiz goodsBiz;
    @Autowired
    private CommonBiz commonBiz;
    @Autowired
    private TruckBiz truckBiz;
    @Autowired
    private AllotBiz allotBiz;
    @Autowired
    private GraintempBiz graintempBiz;
    @Autowired
    private BlanklistBiz blanklistBiz;
    @Autowired
    private SuffrequBiz suffrequBiz;
    @Autowired
    private SafeActivityBiz safeActivityBiz;
    @Autowired
    private CasualtiesBiz casualtiesBiz;
    @Autowired
    private SafeInspBiz safeInspBiz;
    @Autowired
    private StolenfireBiz stolenfireBiz;
    @Autowired
    private EquipaccBiz equipaccBiz;
    @Autowired
    private SafeleaderBiz safeleaderBiz;
    @Autowired
    private MechventlogBiz mechventlogBiz;
    @Autowired
    private  EquipaccDetailBiz equipaccDetailBiz;



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

    //----------------------------------储备规模---------------------------------------------
    //储备规模主页
    @GetMapping("/reserveScale")
    public String to_reserveScale(Model model) {
        String title = "储备规模";
        model.addAttribute("title", title);
        String path = "reserveScale/list";
        return path;
    }

    //储备规模编辑
    @GetMapping("/reserveScale/edit")
    public String to_reserveScale_edit(Model model, Integer id) {
        String title = "质量检测";
        model.addAttribute("title", title);
        model.addAttribute("id", id);
        ReserveScale item = new ReserveScale();
        if (id != null) {
            item = reserveScaleBiz.selectById(id);
        }
        model.addAttribute("item", item);
        String path = "reserveScale/edit";
        return path;
    }

    //储备规模详情页
    @GetMapping("/reserveScale/detail/{id}")
    public String to_reserveScale_detail(Model model, @PathVariable int id) {
        String title = "储备规模详情";
        ReserveScale item = reserveScaleBiz.selectById(id);
        model.addAttribute("title", title);
        model.addAttribute("item", item);
        String path = "reserveScale/detail";
        return path;
    }


    //----------------------------------涉粮人员---------------------------------------------
    //涉粮人员主页
    @GetMapping("/individual")
    public String to_individual(Model model) {
        String title = "涉粮人员";
        model.addAttribute("title", title);
        String path = "individual/list";
        return path;
    }

    //涉粮人员编辑
    @GetMapping("/individual/edit")
    public String to_individual_edit(Model model, Integer id) {
        String title = "涉粮人员";
        model.addAttribute("title", title);
        model.addAttribute("id", id);
        Individual item = new Individual();
        if (id != null) {
            item = individualBiz.selectById(id);
        }
        model.addAttribute("item", item);
        String path = "individual/edit";
        return path;
    }

    //涉粮人员详情页
    @GetMapping("/individual/detail/{id}")
    public String to_individual_detail(Model model, @PathVariable int id) {
        String title = "涉粮人员详情";
        Individual item = individualBiz.selectById(id);
        model.addAttribute("title", title);
        model.addAttribute("item", item);
        String path = "individual/detail";
        return path;
    }

    //----------------------------------样品管理---------------------------------------------
    //样品管理主页
    @GetMapping("/sample")
    public String to_sample(Model model) {
        String title = "样品管理";
        model.addAttribute("title", title);
        String path = "sample/list";
        return path;
    }

    //样品管理编辑
    @GetMapping("/sample/edit")
    public String to_sample_edit(Model model, Integer id) {
        String title = "样品管理";
        model.addAttribute("title", title);
        model.addAttribute("id", id);
        Sample item = new Sample();
        if (id != null) {
            item = sampleBiz.selectById(id);
        }
        model.addAttribute("item", item);
        String path = "sample/edit";
        return path;
    }

    //样品管理详情页
    @GetMapping("/sample/detail/{id}")
    public String to_sample_detail(Model model, @PathVariable int id) {
        String title = "样品管理详情";
        Sample item = sampleBiz.selectById(id);
        model.addAttribute("title", title);
        model.addAttribute("item", item);
        String path = "sample/detail";
        return path;
    }

    //----------------------------------入库整理耗---------------------------------------------
    //入库整理耗主页
    @GetMapping("/instoreloss")
    public String to_instoreloss(Model model) {
        String title = "入库整理耗";
        model.addAttribute("title", title);
        String path = "instoreloss/list";
        return path;
    }

    //入库整理耗编辑
    @GetMapping("/instoreloss/edit")
    public String to_instoreloss_edit(Model model, Integer id) {
        String title = "入库整理耗";
        model.addAttribute("title", title);
        model.addAttribute("id", id);
        Instoreloss item = new Instoreloss();
        if (id != null) {
            item = instorelossBiz.selectById(id);
        }
        model.addAttribute("item", item);
        String path = "instoreloss/edit";
        return path;
    }

    //入库整理耗详情页
    @GetMapping("/instoreloss/detail/{id}")
    public String to_instoreloss_detail(Model model, @PathVariable int id) {
        String title = "入库整理耗详情";
        Instoreloss item = instorelossBiz.selectById(id);
        model.addAttribute("title", title);
        model.addAttribute("item", item);
        String path = "instoreloss/detail";
        return path;
    }

    //----------------------------------设备类型---------------------------------------------
    //设备类型主页
    @GetMapping("/equiptype")
    public String to_equiptype(Model model) {
        String title = "设备类型";
        model.addAttribute("title", title);
        String path = "equiptype/list";
        return path;
    }

    //设备类型编辑
    @GetMapping("/equiptype/edit")
    public String to_equiptype_edit(Model model, Integer id) {
        String title = "设备类型";
        model.addAttribute("title", title);
        model.addAttribute("id", id);
        Equiptype item = new Equiptype();
        if (id != null) {
            item = equiptypeBiz.selectById(id);
        }
        model.addAttribute("item", item);
        String path = "equiptype/edit";
        return path;
    }

    //设备类型详情页
    @GetMapping("/equiptype/detail/{id}")
    public String to_equiptype_detail(Model model, @PathVariable int id) {
        String title = "设备类型详情";
        Equiptype item = equiptypeBiz.selectById(id);
        model.addAttribute("title", title);
        model.addAttribute("item", item);
        String path = "equiptype/detail";
        return path;
    }


    //----------------------------------设备---------------------------------------------
    //设备主页
    @GetMapping("/equip")
    public String to_equip(Model model) {
        String title = "设备";
        model.addAttribute("title", title);
        String path = "equip/list";
        return path;
    }

    //设备编辑
    @GetMapping("/equip/edit")
    public String to_equip_edit(Model model, Integer id) {
        String title = "设备";
        model.addAttribute("title", title);
        model.addAttribute("id", id);
        Equip item = new Equip();
        if (id != null) {
            item = equipBiz.selectById(id);
        }
        model.addAttribute("item", item);
        String path = "equip/edit";
        return path;
    }

    //设备详情页
    @GetMapping("/equip/detail/{id}")
    public String to_equip_detail(Model model, @PathVariable int id) {
        String title = "设备详情";
        Equip item = equipBiz.selectById(id);
        model.addAttribute("title", title);
        model.addAttribute("item", item);
        String path = "equip/detail";
        return path;
    }


    //----------------------------------药剂种类---------------------------------------------
    //药剂种类主页
    @GetMapping("/drugkind")
    public String to_drugkind(Model model) {
        String title = "药剂种类";
        model.addAttribute("title", title);
        String path = "drugkind/list";
        return path;
    }

    //药剂种类编辑
    @GetMapping("/drugkind/edit")
    public String to_drugkind_edit(Model model, Integer id) {
        String title = "药剂种类";
        model.addAttribute("title", title);
        model.addAttribute("id", id);
        Drugkind item = new Drugkind();
        if (id != null) {
            item = drugkindBiz.selectById(id);
        }
        model.addAttribute("item", item);
        String path = "drugkind/edit";
        return path;
    }

    //药剂种类详情页
    @GetMapping("/drugkind/detail/{id}")
    public String to_drugkind_detail(Model model, @PathVariable int id) {
        String title = "药剂种类详情";
        Drugkind item = drugkindBiz.selectById(id);
        model.addAttribute("title", title);
        model.addAttribute("item", item);
        String path = "drugkind/detail";
        return path;
    }


    //----------------------------------药剂---------------------------------------------
    //药剂主页
    @GetMapping("/drug")
    public String to_drug(Model model) {
        String title = "药剂";
        model.addAttribute("title", title);
        String path = "drug/list";
        return path;
    }

    //药剂编辑
    @GetMapping("/drug/edit")
    public String to_drug_edit(Model model, Integer id) {
        String title = "药剂";
        model.addAttribute("title", title);
        model.addAttribute("id", id);
        Drug item = new Drug();
        if (id != null) {
            item = drugBiz.selectById(id);
        }
        model.addAttribute("item", item);
        String path = "drug/edit";
        return path;
    }

    //药剂详情页
    @GetMapping("/drug/detail/{id}")
    public String to_drug_detail(Model model, @PathVariable int id) {
        String title = "药剂详情";
        Drug item = drugBiz.selectById(id);
        model.addAttribute("title", title);
        model.addAttribute("item", item);
        String path = "drug/detail";
        return path;
    }


    //----------------------------------定性管理---------------------------------------------
    //定性管理主页
    @GetMapping("/grainattrUpdate")
    public String to_grainattrUpdate(Model model) {
        String title = "定性管理";
        model.addAttribute("title", title);
        String path = "grainattrUpdate/list";
        return path;
    }

    //定性管理编辑
    @GetMapping("/grainattrUpdate/edit")
    public String to_grainattrUpdate_edit(Model model, Integer id) {
        String title = "定性管理";
        model.addAttribute("title", title);
        model.addAttribute("id", id);
        GrainattrUpdate item = new GrainattrUpdate();
        if (id != null) {
            item = grainattrUpdateBiz.selectById(id);
        }
        model.addAttribute("item", item);
        String path = "grainattrUpdate/edit";
        return path;
    }

    //定性管理详情页
    @GetMapping("/grainattrUpdate/detail/{id}")
    public String to_grainattrUpdate_detail(Model model, @PathVariable int id) {
        String title = "定性管理详情";
        GrainattrUpdate item = grainattrUpdateBiz.selectById(id);
        model.addAttribute("title", title);
        model.addAttribute("item", item);
        String path = "grainattrUpdate/detail";
        return path;
    }

    //----------------------------------出库保管耗---------------------------------------------
    //出库保管耗主页
    @GetMapping("/outstoreloss")
    public String to_outstoreloss(Model model) {
        String title = "出库保管耗";
        model.addAttribute("title", title);
        String path = "outstoreloss/list";
        return path;
    }

    //出库保管耗编辑
    @GetMapping("/outstoreloss/edit")
    public String to_outstoreloss_edit(Model model, Integer id) {
        String title = "出库保管耗";
        model.addAttribute("title", title);
        model.addAttribute("id", id);
        Outstoreloss item = new Outstoreloss();
        if (id != null) {
            item = outstorelossBiz.selectById(id);
        }
        model.addAttribute("item", item);
        String path = "outstoreloss/edit";
        return path;
    }

    //出库保管耗详情页
    @GetMapping("/outstoreloss/detail/{id}")
    public String to_outstoreloss_detail(Model model, @PathVariable int id) {
        String title = "出库保管耗详情";
        Outstoreloss item = outstorelossBiz.selectById(id);
        model.addAttribute("title", title);
        model.addAttribute("item", item);
        String path = "outstoreloss/detail";
        return path;
    }


    //----------------------------------药剂验收入库---------------------------------------------
    //药剂验收入库主页
    @GetMapping("/druginstore")
    public String to_druginstore(Model model) {
        String title = "药剂验收入库";
        model.addAttribute("title", title);
        String path = "druginstore/list";
        return path;
    }

    //药剂验收入库编辑
    @GetMapping("/druginstore/edit")
    public String to_druginstore_edit(Model model, Integer id) {
        String title = "药剂验收入库";
        model.addAttribute("title", title);
        model.addAttribute("id", id);
        Druginstore item = new Druginstore();
        if (id != null) {
            item = druginstoreBiz.selectById(id);
        }
        model.addAttribute("item", item);
        String path = "druginstore/edit";
        return path;
    }

    //药剂验收入库详情页
    @GetMapping("/druginstore/detail/{id}")
    public String to_druginstore_detail(Model model, @PathVariable int id) {
        String title = "药剂验收入库详情";
        Druginstore item = druginstoreBiz.selectById(id);
        model.addAttribute("title", title);
        model.addAttribute("item", item);
        String path = "druginstore/detail";
        return path;
    }


    //----------------------------------药剂验收入库明细---------------------------------------------
    //药剂验收入库明细主页
    @GetMapping("/druginstoreDetail")
    public String to_druginstoreDetail(Model model, Integer billid) {
        String title = "药剂验收入库明细";
        model.addAttribute("title", title);
        model.addAttribute("billid", billid);
        String path = "druginstore/druginstoreDetail/list";
        return path;
    }

    //药剂验收入库明细编辑
    @GetMapping("/druginstoreDetail/edit")
    public String to_druginstoreDetail_edit(Model model, Integer id, Integer billid) {
        String title = "修改药剂验收入库明细";
        model.addAttribute("title", title);
        model.addAttribute("id", id);
        model.addAttribute("billid", billid);
        DruginstoreDetail item = new DruginstoreDetail();
        if (id != null) {
            item = druginstoreDetailBiz.selectById(id);
        }
        model.addAttribute("item", item);
        String path = "druginstore/druginstoreDetail/edit";
        return path;
    }

    //药剂验收入库明细详情页
    @GetMapping("/druginstoreDetail/detail/{id}")
    public String to_druginstoreDetail_detail(Model model, @PathVariable int id) {
        String title = "药剂验收入库明细详情";
        DruginstoreDetail item = druginstoreDetailBiz.selectById(id);
        model.addAttribute("title", title);
        model.addAttribute("item", item);
        String path = "druginstore/druginstoreDetail/detail";
        return path;
    }


    //----------------------------------药剂验收出库---------------------------------------------
    //药剂验收出库主页
    @GetMapping("/drugoutstore")
    public String to_drugoutstore(Model model) {
        String title = "药剂验收出库";
        model.addAttribute("title", title);
        String path = "drugoutstore/list";
        return path;
    }

    //药剂验收出库编辑
    @GetMapping("/drugoutstore/edit")
    public String to_drugoutstore_edit(Model model, Integer id) {
        String title = "药剂验收出库";
        model.addAttribute("title", title);
        model.addAttribute("id", id);
        Drugoutstore item = new Drugoutstore();
        if (id != null) {
            item = drugoutstoreBiz.selectById(id);
        }
        model.addAttribute("item", item);
        String path = "drugoutstore/edit";
        return path;
    }

    //药剂验收出库详情页
    @GetMapping("/drugoutstore/detail/{id}")
    public String to_drugoutstore_detail(Model model, @PathVariable int id) {
        String title = "药剂验收出库详情";
        Drugoutstore item = drugoutstoreBiz.selectById(id);
        model.addAttribute("title", title);
        model.addAttribute("item", item);
        String path = "drugoutstore/detail";
        return path;
    }


    //----------------------------------药剂验收出库明细---------------------------------------------
    //药剂验收出库明细主页
    @GetMapping("/drugoutstoreDetail")
    public String to_drugoutstoreDetail(Model model, Integer billid) {
        String title = "药剂验收出库明细";
        model.addAttribute("title", title);
        model.addAttribute("billid", billid);
        String path = "drugoutstore/drugoutstoreDetail/list";
        return path;
    }

    //药剂验收出库明细编辑
    @GetMapping("/drugoutstoreDetail/edit")
    public String to_drugoutstoreDetail_edit(Model model, Integer id, Integer billid) {
        String title = "修改药剂验收出库明细";
        model.addAttribute("title", title);
        model.addAttribute("id", id);
        model.addAttribute("billid", billid);
        DrugoutstoreDetail item = new DrugoutstoreDetail();
        if (id != null) {
            item = drugoutstoreDetailBiz.selectById(id);
        }
        model.addAttribute("item", item);
        String path = "drugoutstore/drugoutstoreDetail/edit";
        return path;
    }

    //药剂验收出库明细详情页
    @GetMapping("/drugoutstoreDetail/detail/{id}")
    public String to_drugoutstoreDetail_detail(Model model, @PathVariable int id) {
        String title = "药剂验收出库明细详情";
        DrugoutstoreDetail item = drugoutstoreDetailBiz.selectById(id);
        model.addAttribute("title", title);
        model.addAttribute("item", item);
        String path = "drugoutstore/drugoutstoreDetail/detail";
        return path;
    }

    //----------------------------------物料类型---------------------------------------------
    //物料类型列表
    @GetMapping("/goodsType")
    public String toGoodsType(Model model) {
        String title = "物料类型";
        model.addAttribute("title", title);
        String path = "goodsType/list";
        return path;
    }

    //物料类型编辑
    @GetMapping("/goodsType/edit")
    public String toGoodsType_edit(Model model, Integer id) {
        String title = "物料类型编辑";
        model.addAttribute("title", title);
        model.addAttribute("id", id);
        Goodstype item = new Goodstype();
        if (id != null) {
            item = goodstypeBiz.selectById(id);
        }
        model.addAttribute("item", item);
        String path = "goodsType/edit";
        return path;
    }

    //----------------------------------物料---------------------------------------------
    //物料列表
    @GetMapping("/goods")
    public String toGoods(Model model) {
        String title = "物料";
        model.addAttribute("title", title);
        String path = "goods/list";
        return path;
    }

    //物料编辑
    @GetMapping("/goods/edit")
    public String toGoods_edit(Model model, Integer id) {
        String title = "物料编辑";
        model.addAttribute("title", title);
        model.addAttribute("id", id);
        Goods item = new Goods();
        if (id != null) {
            item = goodsBiz.selectById(id);
        }
        model.addAttribute("item", item);
        String path = "goods/edit";
        return path;
    }

    //物料详情页
    @GetMapping("/goods/detail/{id}")
    public String toGoods_detail(Model model, @PathVariable int id) {
        String title = "物料详情";
        Goods item = goodsBiz.selectById(id);
        BaseUser baseUser = getCurrentUser();
        model.addAttribute("title", title);
        model.addAttribute("item", item);
        model.addAttribute("baseUser", baseUser);
        String path = "goods/detail";
        return path;
    }

    //三维粮情列表
    @GetMapping("/graintemp")
    public String toGraintemp(Model model) {
        String title = "粮情信息";
        model.addAttribute("title", title);
        String path = "graintemp/list";
        return path;
    }

    //三维粮情详情
    @GetMapping("/graintemp/detail/{id}")
    public String toGraintemp_detail(Model model, @PathVariable int id) {
        String title = "粮情三维图";
        Graintemp item = graintempBiz.selectById(id);
        //计算平均粮温度  最高温  最低温
        String temperatureset = item.getTemperatureset();
        List<String> temperaturesetList = Arrays.asList(temperatureset.split(","));
        Double maxTemp = -100.0;
        Double minTemp = 100.0;
        Double sum = 0.0;
        Integer count = 0;
        for (String t : temperaturesetList) {
            //TODO 判断空
            Double d = Double.parseDouble(t);
            if (d < -100 || d > 100) {
                continue;
            }
            maxTemp = Math.max(maxTemp, d);
            minTemp = Math.min(minTemp, d);
            sum += d;
            count += 1;
        }
        if (count > 0) {
            DecimalFormat df = new DecimalFormat("#.00");
            String avg = df.format(sum / count);
            item.setAveragetemp(avg);
        } else {
            item.setAveragetemp("0");
        }
        item.setMaxLw(maxTemp);
        item.setMinLw(minTemp);
        model.addAttribute("title", title);
        model.addAttribute("item", item);
        String path = "graintemp/grainTemp3D";
        return path;
    }


    //----------------------------------内部车辆---------------------------------------------
    //内部车辆列表
    @GetMapping("/truck")
    public String toTruck(Model model) {
        String title = "内部车辆";
        model.addAttribute("title", title);
        String path = "truck/list";
        return path;
    }

    //内部车辆编辑
    @GetMapping("/truck/edit")
    public String toTruck_edit(Model model, Integer id) {
        String title = "内部车辆编辑";
        model.addAttribute("title", title);
        model.addAttribute("id", id);
        Truck item = new Truck();
        if (id != null) {
            item = truckBiz.selectById(id);
        }
        model.addAttribute("item", item);
        String path = "truck/edit";
        return path;
    }

    //内部车辆详情页
    @GetMapping("/truck/detail/{id}")
    public String toTruck_detail(Model model, @PathVariable int id) {
        String title = "内部车辆";
        Truck item = truckBiz.selectById(id);
        model.addAttribute("title", title);
        model.addAttribute("item", item);
        String path = "truck/detail";
        return path;
    }

    //----------------------------------移库单---------------------------------------------
    //移库单
    @GetMapping("/allot")
    public String toAllot(Model model) {
        String title = "移库单";
        model.addAttribute("title", title);
        String path = "allot/list";
        return path;
    }

    //移库单编辑
    @GetMapping("/allot/edit")
    public String toAllot_edit(Model model, Integer id) {
        String title = "移库单编辑";
        model.addAttribute("title", title);
        model.addAttribute("id", id);
        Allot item = new Allot();
        if (id != null) {
            item = allotBiz.selectById(id);
        }
        model.addAttribute("item", item);
        String path = "allot/edit";
        return path;
    }

    //移库单详情页
    @GetMapping("/allot/detail/{id}")
    public String toAllot_detail(Model model, @PathVariable int id) {
        String title = "移库单详情";
        Allot item = allotBiz.selectById(id);
//        Storage storage = storageBiz.selectById();
        model.addAttribute("title", title);
        model.addAttribute("item", item);
        String path = "allot/detail";
        return path;
    }


    //仓房点位设置
    @GetMapping("/storagePosition")
    public String toStoragePosition(Model model) {
        String title = "仓房点位设置";
        model.addAttribute("title", title);
        String path = "storagePosition/cblycjg";
        return path;
    }

    //视频点位设置
    @GetMapping("/videoPosition")
    public String toVideoPosition(Model model) {
        String title = "视频点位设置";
        model.addAttribute("title", title);
        String path = "storagePosition/positionmanage";
        return path;
    }

    //----------------------------------熏蒸申请---------------------------------------------
    //熏蒸申请
    @GetMapping("/suffrequ")
    public String toSuffrequ(Model model) {
        String title = "熏蒸申请";
        model.addAttribute("title", title);
        String path = "suffrequApply/list";
        return path;
    }

    //熏蒸申请编辑
    @GetMapping("/suffrequ/edit")
    public String toSuffrequ_edit(Model model, Integer id) {
        String title = "熏蒸申请编辑";
        model.addAttribute("title", title);
        model.addAttribute("id", id);
        Suffrequ item = new Suffrequ();
        if (id != null) {
            item = suffrequBiz.selectById(id);
        }
        model.addAttribute("item", item);
        String path = "suffrequApply/edit";
        return path;
    }

    //熏蒸申请详情页
    @GetMapping("/suffrequ/detail/{id}")
    public String toSuffrequ_detail(Model model, @PathVariable int id) {
        String title = "熏蒸申请详情";
        Suffrequ item = suffrequBiz.selectById(id);
//        Storage storage = storageBiz.selectById();
        model.addAttribute("title", title);
        model.addAttribute("item", item);
        String path = "suffrequApply/detail";
        return path;
    }

    //----------------------------------熏蒸审批---------------------------------------------
    //熏蒸审批
    @GetMapping("/suffrequAudit")
    public String toSuffrequAuditList(Model model) {
        String title = "熏蒸审批列表";
        model.addAttribute("title", title);
        String path = "suffrequAudit/list";
        return path;
    }

    //熏蒸申请审批
    @GetMapping("/suffrequAudit/audit")
    public String toSuffrequAudit_edit(Model model, Integer id) {
        String title = "熏蒸审批";
        model.addAttribute("title", title);
        model.addAttribute("id", id);
        Suffrequ item = new Suffrequ();
        if (id != null) {
            item = suffrequBiz.selectById(id);
        }
        model.addAttribute("item", item);
        String path = "suffrequAudit/audit";
        return path;
    }


    //----------------------------------涉粮人员黑名单---------------------------------------------
    //涉粮人员黑名单主页
    @GetMapping("/blanklist")
    public String to_blanklist(Model model) {
        String title = "涉粮人员黑名单";
        model.addAttribute("title", title);
        String path = "blanklist/individual/list";
        return path;
    }

    //涉粮人员未列入黑名单主页
    @GetMapping("/unBlanklist")
    public String to_unBlanklist(Model model) {
        String title = "未列入黑名单涉粮人员";
        model.addAttribute("title", title);
        String path = "blanklist/individual/unBlankList";
        return path;
    }

    //涉粮人员黑名单编辑
    @GetMapping("/blanklist/edit")
    public String to_blanklist_edit(Model model, Integer id) {
        String title = "涉粮人员黑名单";
        model.addAttribute("title", title);
        model.addAttribute("id", id);
        Blanklist item = new Blanklist();
        if (id != null) {
            item = blanklistBiz.selectById(id);
        }
        model.addAttribute("item", item);
        String path = "blanklist/individual/edit";
        return path;
    }

    //涉粮人员黑名单详情页
    @GetMapping("/blanklist/detail/{id}")
    public String to_blanklist_detail(Model model, @PathVariable int id) {
        String title = "涉粮人员黑名单详情";
        Blanklist item = blanklistBiz.selectById(id);
        model.addAttribute("title", title);
        model.addAttribute("item", item);
        String path = "blanklist/individual/detail";
        return path;
    }


    //----------------------------------往来单位黑名单---------------------------------------------
    //往来单位黑名单主页
    @GetMapping("/blanklist/trader")
    public String to_blanklist_trader(Model model) {
        String title = "往来单位黑名单";
        model.addAttribute("title", title);
        String path = "blanklist//trader/list";
        return path;
    }

    //往来单位未列入黑名单主页
    @GetMapping("/unBlanklist/trader")
    public String to_unBlanklist_trader(Model model) {
        String title = "未列入黑名单往来单位";
        model.addAttribute("title", title);
        String path = "blanklist/trader/unBlankList";
        return path;
    }

    //往来单位黑名单编辑
    @GetMapping("/blanklist/trader/edit")
    public String to_blanklist_trader_edit(Model model, Integer id) {
        String title = "往来单位黑名单";
        model.addAttribute("title", title);
        model.addAttribute("id", id);
        Blanklist item = new Blanklist();
        if (id != null) {
            item = blanklistBiz.selectById(id);
        }
        model.addAttribute("item", item);
        String path = "blanklist/trader/edit";
        return path;
    }

    //往来单位黑名单详情页
    @GetMapping("/blanklist/trader/detail/{id}")
    public String to_blanklist_trader_detail(Model model, @PathVariable int id) {
        String title = "往来单位黑名单详情";
        Blanklist item = blanklistBiz.selectById(id);
        model.addAttribute("title", title);
        model.addAttribute("item", item);
        String path = "blanklist/trader/detail";
        return path;
    }

    //----------------------------------重大安全生产活动---------------------------------------------
    //重大安全生产活动列表
    @GetMapping("/safeActivity")
    public String toSafeActivity(Model model) {
        String title = "重大安全生产活动";
        model.addAttribute("title", title);
        String path = "safeActivity/list";
        return path;
    }

    //重大安全生产活动编辑
    @GetMapping("/safeActivity/edit")
    public String toSafeActivity_edit(Model model, Integer id) {
        String title = "重大安全生产活动编辑";
        model.addAttribute("title", title);
        model.addAttribute("id", id);
        SafeActivity item = new SafeActivity();
        if (id != null) {
            item = safeActivityBiz.selectById(id);
        }
        model.addAttribute("item", item);
        String path = "safeActivity/edit";
        return path;
    }

    //重大安全生产活动详情页
    @GetMapping("/safeActivity/detail/{id}")
    public String toSafeActivity_detail(Model model, @PathVariable int id) {
        String title = "重大安全生产活动详情";
        SafeActivity item = safeActivityBiz.selectById(id);
        model.addAttribute("title", title);
        model.addAttribute("item", item);
        String path = "safeActivity/detail";
        return path;
    }

    //----------------------------------伤亡事故---------------------------------------------
    //伤亡事故列表
    @GetMapping("/casualties")
    public String toCasualties(Model model) {
        String title = "伤亡事故";
        model.addAttribute("title", title);
        String path = "casualties/list";
        return path;
    }

    //伤亡事故编辑
    @GetMapping("/casualties/edit")
    public String toCasualties_edit(Model model, Integer id) {
        String title = "伤亡事故编辑";
        model.addAttribute("title", title);
        model.addAttribute("id", id);
        Casualties item = new Casualties();
        if (id != null) {
            item = casualtiesBiz.selectById(id);
        }
        model.addAttribute("item", item);
        String path = "casualties/edit";
        return path;
    }

    //伤亡事故详情页
    @GetMapping("/casualties/detail/{id}")
    public String toCasualties_detail(Model model, @PathVariable int id) {
        String title = "伤亡事故详情";
        Casualties item = casualtiesBiz.selectById(id);
        model.addAttribute("title", title);
        model.addAttribute("item", item);
        String path = "casualties/detail";
        return path;
    }


    //----------------------------------安全检查记录---------------------------------------------
    //安全检查记录列表
    @GetMapping("/safeinsp")
    public String toSafeinsp(Model model) {
        String title = "安全检查记录";
        model.addAttribute("title", title);
        String path = "safeinsp/list";
        return path;
    }

    //安全检查记录编辑
    @GetMapping("/safeinsp/edit")
    public String toSafeinsp_edit(Model model, Integer id) {
        String title = "安全检查记录编辑";
        model.addAttribute("title", title);
        model.addAttribute("id", id);
        SafeInsp item = new SafeInsp();
        if (id != null) {
            item = safeInspBiz.selectById(id);
        }
        model.addAttribute("item", item);
        String path = "safeinsp/edit";
        return path;
    }

    //安全检查记录详情页
    @GetMapping("/safeinsp/detail/{id}")
    public String toSafeinsp_detail(Model model, @PathVariable int id) {
        String title = "安全检查记录详情";
        SafeInsp item = safeInspBiz.selectById(id);
        model.addAttribute("title", title);
        model.addAttribute("item", item);
        String path = "safeinsp/detail";
        return path;
    }


    //----------------------------------粮库失窃、火灾事故---------------------------------------------
    //粮库失窃、火灾事故列表
    @GetMapping("/stolenfire")
    public String toStolenfire(Model model) {
        String title = "粮库失窃、火灾事故";
        model.addAttribute("title", title);
        String path = "stolenfire/list";
        return path;
    }

    //粮库失窃、火灾事故编辑
    @GetMapping("/stolenfire/edit")
    public String toStolenfire_edit(Model model, Integer id) {
        String title = "粮库失窃、火灾事故编辑";
        model.addAttribute("title", title);
        model.addAttribute("id", id);
        Stolenfire item = new Stolenfire();
        if (id != null) {
            item = stolenfireBiz.selectById(id);
        }
        model.addAttribute("item", item);
        String path = "stolenfire/edit";
        return path;
    }

    //粮库失窃、火灾事故详情页
    @GetMapping("/stolenfire/detail/{id}")
    public String toStolenfire_detail(Model model, @PathVariable int id) {
        String title = "粮库失窃、火灾事故详情";
        Stolenfire item = stolenfireBiz.selectById(id);
        model.addAttribute("title", title);
        model.addAttribute("item", item);
        String path = "stolenfire/detail";
        return path;
    }


    //----------------------------------粮库机械设备事故---------------------------------------------
    //粮库机械设备事故列表
    @GetMapping("/equipacc")
    public String toEquipacc(Model model) {
        String title = "粮库机械设备事故";
        model.addAttribute("title", title);
        String path = "equipacc/list";
        return path;
    }

    //粮库机械设备事故编辑
    @GetMapping("/equipacc/edit")
    public String toEquipacc_edit(Model model, Integer id) {
        String title = "粮库机械设备事故编辑";
        model.addAttribute("title", title);
        model.addAttribute("id", id);
        Equipacc item = new Equipacc();
        if (id != null) {
            item = equipaccBiz.selectById(id);
        }
        model.addAttribute("item", item);
        String path = "equipacc/edit";
        return path;
    }

    //粮库机械设备事故详情页
    @GetMapping("/equipacc/detail/{id}")
    public String toEquipacc_detail(Model model, @PathVariable int id) {
        String title = "粮库机械设备事故详情";
        Equipacc item = equipaccBiz.selectById(id);
        model.addAttribute("title", title);
        model.addAttribute("item", item);
        String path = "equipacc/detail";
        return path;
    }

    //----------------------------------粮库安全生产领导小组---------------------------------------------
    //粮库安全生产领导小组列表
    @GetMapping("/safeleader")
    public String toSafeleader(Model model) {
        String title = "粮库安全生产领导小组";
        model.addAttribute("title", title);
        String path = "safeleader/list";
        return path;
    }

    //粮库安全生产领导小组编辑
    @GetMapping("/safeleader/edit")
    public String toSafeleader_edit(Model model, Integer id) {
        String title = "粮库安全生产领导小组编辑";
        model.addAttribute("title", title);
        model.addAttribute("id", id);
        Safeleader item = new Safeleader();
        if (id != null) {
            item = safeleaderBiz.selectById(id);
        }
        model.addAttribute("item", item);
        String path = "safeleader/edit";
        return path;
    }

    //粮库安全生产领导小组详情页
    @GetMapping("/safeleader/detail/{id}")
    public String toSafeleader_detail(Model model, @PathVariable int id) {
        String title = "粮库安全生产领导小组详情";
        Safeleader item = safeleaderBiz.selectById(id);
        model.addAttribute("title", title);
        model.addAttribute("item", item);
        String path = "safeleader/detail";
        return path;
    }



    //----------------------------------机械通风---------------------------------------------
    //通风作业列表
    @GetMapping("/mechventlog")
    public String toMechventlog(Model model) {
        String title = "通风作业";
        model.addAttribute("title", title);
        String path = "mechventlog/list";
        return path;
    }

    //通风作业编辑
    @GetMapping("/mechventlog/edit")
    public String toMechventlog_edit(Model model, Integer id) {
        String title = "通风作业编辑";
        model.addAttribute("title", title);
        model.addAttribute("id", id);
        Mechventlog item = new Mechventlog();
        if (id != null) {
            item = mechventlogBiz.selectById(id);
        }
        model.addAttribute("item", item);
        String path = "mechventlog/edit";
        return path;
    }

    //通风作业详情页
    @GetMapping("/mechventlog/detail/{id}")
    public String toMechventlog_detail(Model model, @PathVariable int id) {
        String title = "通风作业详情";
        Mechventlog item = mechventlogBiz.selectById(id);
        model.addAttribute("title", title);
        model.addAttribute("item", item);
        String path = "mechventlog/detail";
        return path;
    }

    //----------------------------------粮库机械设备事故详情---------------------------------------------
    //粮库机械设备事故详情列表
    @GetMapping("/equipaccDetail")
    public String toEquipaccDetail(Model model) {
        String title = "粮库机械设备事故详情";
        model.addAttribute("title", title);
        String path = "equipaccDetail/list";
        return path;
    }

    //粮库机械设备事故详情编辑
    @GetMapping("/equipaccDetail/edit")
    public String toEquipaccDetail_edit(Model model, Integer id) {
        String title = "粮库机械设备事故详情编辑";
        model.addAttribute("title", title);
        model.addAttribute("id", id);
        EquipaccDetail item = new EquipaccDetail();
        if (id != null) {
            item = equipaccDetailBiz.selectById(id);
        }
        model.addAttribute("item", item);
        String path = "equipaccDetail/edit";
        return path;
    }


}
