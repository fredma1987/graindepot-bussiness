package com.zhoubi.graindepot.controller;

import com.zhoubi.graindepot.base.JsonResult;
import com.zhoubi.graindepot.base.PagerModel;
import com.zhoubi.graindepot.bean.BaseUser;
import com.zhoubi.graindepot.bean.Goods;
import com.zhoubi.graindepot.bean.UserAddress;
import com.zhoubi.graindepot.biz.GoodsBiz;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhanghao on 2019/1/15.
 */
@RestController
@RequestMapping("goods")
public class GoodsController extends BaseController {
    @Autowired
    private GoodsBiz goodsBiz;


    @GetMapping("/list/page")
    public PagerModel goodsPageList(int start, int length, String goodsname,Integer goodstypeid) {
        UserAddress ua=getUserAddress();
        PagerModel<Goods> e = new PagerModel();
        e.addOrder("createtime desc");
        e.setStart(start);
        e.setLength(length);
        if (StringUtils.isNotEmpty(goodsname)) {
            e.putWhere("goodsname", "%" + goodsname + "%");
        }else if(goodstypeid != null ){
            e.putWhere("goodstypeid",goodstypeid);
        }
//        e.putWhere("graindepotid",ua.getGraindepotid());
        PagerModel<Goods> result = goodsBiz.selectListByPage(e);
        return result;
    }

    @PostMapping("/edit")
    public JsonResult goodsEdit(Goods item) throws ParseException {
        UserAddress ua=getUserAddress();
        BaseUser baseUser = getCurrentUser();
        if (item.getGoodsid() == null) {
            //新增
            item.setGroupid(ua.getGroupid());
            item.setGraindepotid(ua.getGraindepotid());
            item.setCompanyid(ua.getCompanyid());
            item.setCreateuserid(baseUser.getUserid());
            item.setCreatetime(new Date());
            item.setUpdatetime(new Date());
            goodsBiz.insert(item);
            return new JsonResult("添加成功", true);
        } else {
            //修改
            goodsBiz.update(item);
            return new JsonResult("修改成功", true);
        }

    }

    @PostMapping("/del")
    public JsonResult goodsDel(String ids) {

        if (StringUtils.isNotEmpty(ids)) {
            Map map = new HashMap();
            map.put("Where_IdsStr", ids);
            goodsBiz.deleteMap(map);
        }
        return new JsonResult("删除成功", true);
    }

    //校验物料名称是否重复
    @PostMapping("/checkRepeat")
    public String checkRepeat(String goodsname, Integer goodsid) {
        UserAddress ua = getUserAddress();
        Map map = new HashMap();
        map.put("goodsname", goodsname);
        map.put("goodsid", goodsid);
        map.put("graindepotid", ua.getGraindepotid());
        int result = goodsBiz.checkRepeat(map);
        if (result == 0) {
            return "{\"valid\":true}";
        } else {
            return "{\"valid\":false}";
        }

    }

    //获取下拉框列表数据
//    @GetMapping("/selectorList")
//    public List<Goods> selectorList() {
//        UserAddress ua=getUserAddress();
//        Map param = new HashMap();
//        param.put("graindepotid",ua.getGraindepotid());
//        List<Goods> result = goodsBiz.selectorList(param);
//        return result;
//    }


}
