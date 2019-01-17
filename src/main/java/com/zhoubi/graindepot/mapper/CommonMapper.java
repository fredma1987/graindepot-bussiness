package com.zhoubi.graindepot.mapper;

import com.zhoubi.graindepot.bean.Company;
import com.zhoubi.graindepot.bean.Grain;
import com.zhoubi.graindepot.bean.Graindepot;

import java.util.List;
import java.util.Map;

/**
 * Created by zhanghao on 2019/1/16.
 */
public interface CommonMapper {

    List<Company> companyList(Map map);

    List<Graindepot> graindepotList(Map map);

    List<Grain> grainList(Map param);
}
