package com.zhoubi.graindepot.biz;import com.zhoubi.graindepot.bean.Planfile;import com.zhoubi.graindepot.mapper.PlanfileMapper;import com.zhoubi.graindepot.base.BaseMapper;import com.zhoubi.graindepot.base.BaseService;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;@Servicepublic class PlanfileBiz extends BaseService<Planfile>  {	@Autowired	private PlanfileMapper PlanfileMapper;	@Override
	protected BaseMapper<Planfile> getMapper() {		return PlanfileMapper;	}}