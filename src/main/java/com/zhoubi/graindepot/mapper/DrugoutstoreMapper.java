package com.zhoubi.graindepot.mapper;import com.zhoubi.graindepot.bean.Drugoutstore;import com.zhoubi.graindepot.base.BaseMapper;public interface DrugoutstoreMapper extends BaseMapper<Drugoutstore> {    String getMaxBillcode(Integer graindepotid);}