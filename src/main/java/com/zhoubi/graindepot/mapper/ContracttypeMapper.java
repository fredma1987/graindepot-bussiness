package com.zhoubi.graindepot.mapper;import com.zhoubi.graindepot.bean.Contracttype;import com.zhoubi.graindepot.base.BaseMapper;import java.util.Map;public interface ContracttypeMapper extends BaseMapper<Contracttype> {    int checkRepeat(Map map);}