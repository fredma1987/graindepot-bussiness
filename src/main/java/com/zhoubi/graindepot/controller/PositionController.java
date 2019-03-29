package com.zhoubi.graindepot.controller;

import com.alibaba.fastjson.JSON;
import com.zhoubi.graindepot.base.JsonResult;
import com.zhoubi.graindepot.base.PagerModel;
import com.zhoubi.graindepot.bean.BaseUser;
import com.zhoubi.graindepot.bean.Storage;
import com.zhoubi.graindepot.bean.UserAddress;
import com.zhoubi.graindepot.bean.Video;
import com.zhoubi.graindepot.biz.PositionBiz;
import com.zhoubi.graindepot.biz.StorageBiz;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 1A12 on 2019/1/19/0019.
 */
@RestController
@RequestMapping("position")
public class PositionController extends BaseController {
    @Autowired
    private PositionBiz positionBiz;

    @GetMapping("/stoage/{storageid}")
    public JsonResult stoage(@PathVariable Integer storageid) throws ParseException {
        Storage storage = positionBiz.selectStorageById(storageid);
        return new JsonResult(storage, "更新完成", true);
    }

    @GetMapping("/stoageList")
    public JsonResult stoageList() throws ParseException {
        UserAddress ua = getUserAddress();
        Map map = new HashMap();
        map.put("graindepotid", ua.getGraindepotid());
        List<Storage> stoageList = positionBiz.selectStorageList(map);
        return new JsonResult(stoageList, "更新完成", true);
    }

    @GetMapping("/videoList")
    public List<Video> videoList() throws ParseException {
        UserAddress ua = getUserAddress();
        Map map = new HashMap();
        map.put("graindepotid", ua.getGraindepotid());
        List<Video> videoList = positionBiz.selectVideoList(map);
        return videoList;
    }

    @PostMapping("/video/edit")
    public JsonResult videoPositionEdit(String json) throws ParseException {
        BaseUser user=getCurrentUser();
        UserAddress ua=getUserAddress();
        List<Video> videos = JSON.parseArray(json, Video.class);
        for (Video video:videos) {
            positionBiz.updateVideoPosition(video);
        }
        return new JsonResult("更新完成",true);
    }

    @PostMapping("/edit")
    public JsonResult storagePositionEdit(String json) throws ParseException {
        BaseUser user = getCurrentUser();
        UserAddress ua = getUserAddress();
        List<Storage> storages = JSON.parseArray(json, Storage.class);
        for (Storage storage : storages) {
            positionBiz.updateStoragePosition(storage);
        }
        return new JsonResult("更新完成", true);
    }


}
