package com.zhoubi.graindepot.biz;import com.zhoubi.graindepot.bean.Drugoutstore;import com.zhoubi.graindepot.mapper.DrugoutstoreMapper;import com.zhoubi.graindepot.base.BaseMapper;import com.zhoubi.graindepot.base.BaseService;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;@Servicepublic class DrugoutstoreBiz extends BaseService<Drugoutstore>  {	@Autowired	private DrugoutstoreMapper DrugoutstoreMapper;	@Override	protected BaseMapper<Drugoutstore> getMapper() {		return DrugoutstoreMapper;	}	public String getMaxBillcode(Integer graindepotid) {		return DrugoutstoreMapper.getMaxBillcode(graindepotid);	}}