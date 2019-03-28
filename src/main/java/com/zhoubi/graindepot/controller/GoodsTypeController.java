package com.zhoubi.graindepot.controller;

import com.zhoubi.graindepot.base.JsonResult;
import com.zhoubi.graindepot.base.PagerModel;
import com.zhoubi.graindepot.bean.Goodstype;
import com.zhoubi.graindepot.bean.UserAddress;
import com.zhoubi.graindepot.biz.GoodstypeBiz;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhanghao on 2019/1/15.
 */
@RestController
@RequestMapping("goodsType")
public class GoodsTypeController extends BaseController {
    @Autowired
    private GoodstypeBiz goodstypeBiz;

    @GetMapping("/list/page")
    public PagerModel goodsTypePageList(int start, int length) {
        UserAddress ua = getUserAddress();
        PagerModel<Goodstype> e = new PagerModel();
//        e.addOrder("createtime desc");
        e.setStart(start);
        e.setLength(length);
        PagerModel<Goodstype> result = goodstypeBiz.selectListByPage(e);
        return result;
    }

//    @GetMapping("/findAllByCondition")
//    public  List findAllByCondition() {
//        UserAddress ua = getUserAddress();
//        Map map = new HashMap();
//        map.put("graindepot",ua.getGraindepotid());
//        List list = goodstypeBiz.selectList(map);
//        return  list;
//    }

    @PostMapping("/edit")
    public JsonResult goodsTypeEdit(Goodstype item) throws ParseException {
        UserAddress ua = getUserAddress();
        if (item.getGoodstypeid() == null) {
            //新增
            item.setGroupid(ua.getGroupid());
            item.setCompanyid(ua.getCompanyid());
            item.setGraindepotid(ua.getGraindepotid());
            goodstypeBiz.insert(item);
            return new JsonResult("添加成功", true);
        } else {
            //修改
            goodstypeBiz.update(item);
            return new JsonResult("修改成功", true);
        }

    }

    @PostMapping("/del")
    public JsonResult goodstypeDel(String ids) {

        if (StringUtils.isNotEmpty(ids)) {
            Map map = new HashMap();
            map.put("Where_IdsStr", ids);
            goodstypeBiz.deleteMap(map);
        }
        return new JsonResult("删除成功", true);
    }


    //校验物料类型名称是否重复
    @PostMapping("/checkRepeat")
    public String checkRepeat(String goodstypename, Integer goodstypeid) {
        UserAddress ua = getUserAddress();
        Map map = new HashMap();
        map.put("goodstypename", goodstypename);
        map.put("goodstypeid", goodstypeid);
        map.put("graindepotid", ua.getGraindepotid());
        int result = goodstypeBiz.checkRepeat(map);
        if (result == 0) {
            return "{\"valid\":true}";
        } else {
            return "{\"valid\":false}";
        }

    }

//    //获取下拉框列表数据
//    @GetMapping("/selectorList")
//    public List<Planfile> selectorList() {
//        UserAddress ua=getUserAddress();
//        Map param = new HashMap();
//        param.put("graindepotid",ua.getGraindepotid());
//        List<Planfile> result = planfileBiz.selectorList(param);
//        return result;
//    }


}
