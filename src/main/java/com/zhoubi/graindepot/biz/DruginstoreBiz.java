package com.zhoubi.graindepot.biz;import com.zhoubi.graindepot.bean.Druginstore;import com.zhoubi.graindepot.mapper.DruginstoreMapper;import com.zhoubi.graindepot.base.BaseMapper;import com.zhoubi.graindepot.base.BaseService;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;@Servicepublic class DruginstoreBiz extends BaseService<Druginstore>  {	@Autowired	private DruginstoreMapper DruginstoreMapper;	@Override	protected BaseMapper<Druginstore> getMapper() {		return DruginstoreMapper;	}	public String getMaxBillcode(Integer graindepotid) {		return DruginstoreMapper.getMaxBillcode(graindepotid);	}}