package com.zhoubi.graindepot.biz;import com.zhoubi.graindepot.bean.Suffrequ;import com.zhoubi.graindepot.mapper.SuffrequMapper;import com.zhoubi.graindepot.base.BaseMapper;import com.zhoubi.graindepot.base.BaseService;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;@Servicepublic class SuffrequBiz extends BaseService<Suffrequ>  {	@Autowired	private SuffrequMapper SuffrequMapper;	@Override
	protected BaseMapper<Suffrequ> getMapper() {		return SuffrequMapper;	}}