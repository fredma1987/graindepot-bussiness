package com.zhoubi.graindepot.biz;import com.zhoubi.graindepot.bean.Outstoreloss;import com.zhoubi.graindepot.mapper.OutstorelossMapper;import com.zhoubi.graindepot.base.BaseMapper;import com.zhoubi.graindepot.base.BaseService;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;@Servicepublic class OutstorelossBiz extends BaseService<Outstoreloss>  {	@Autowired	private OutstorelossMapper OutstorelossMapper;	@Override	protected BaseMapper<Outstoreloss> getMapper() {		return OutstorelossMapper;	}	public String getMaxBillcode(Integer graindepotid) {		return OutstorelossMapper.getMaxBillcode(graindepotid);	}}