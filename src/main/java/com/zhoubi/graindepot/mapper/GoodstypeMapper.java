package com.zhoubi.graindepot.mapper;import com.zhoubi.graindepot.bean.Goodstype;import com.zhoubi.graindepot.base.BaseMapper;import java.util.Map;public interface GoodstypeMapper extends BaseMapper<Goodstype> {    int checkRepeat(Map map);}