package com.zhoubi.graindepot.mapper;

import com.zhoubi.graindepot.bean.*;

import java.util.List;
import java.util.Map;

/**
 * Created by zhanghao on 2019/1/16.
 */
public interface SelectorMapper {

    List<Company> companyList(Map map);

    List<Graindepot> graindepotList(Map map);

    List<Grain> grainList(Map param);

    List<Grainattr> grainattrList(Map param);

    List<Trader> traderList(Map param);

    List<Contracttype> contracttypeList(Map param);

    List<Goodstype> goodstypeList(Map param);
}
