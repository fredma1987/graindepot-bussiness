package com.zhoubi.graindepot.biz;

import com.zhoubi.graindepot.bean.Company;
import com.zhoubi.graindepot.bean.Grain;
import com.zhoubi.graindepot.bean.Graindepot;
import com.zhoubi.graindepot.mapper.CommonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by zhanghao on 2019/1/16.
 */
@Service
public class CommonBiz {
    @Autowired
    private CommonMapper mapper;

    public List<Company> companyList(Map map) {
        return mapper.companyList(map);
    }


    public List<Graindepot> graindepotList(Map map) {
        return mapper.graindepotList(map);
    }

    public List<Grain> grainList(Map param) {
        return mapper.grainList(param);
    }
}
