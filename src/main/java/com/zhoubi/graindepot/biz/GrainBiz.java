package com.zhoubi.graindepot.biz;import com.zhoubi.graindepot.bean.Grain;import com.zhoubi.graindepot.mapper.GrainMapper;import com.zhoubi.graindepot.base.BaseMapper;import com.zhoubi.graindepot.base.BaseService;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;import java.util.Map;@Servicepublic class GrainBiz extends BaseService<Grain>  {	@Autowired	private GrainMapper GrainMapper;	@Override	protected BaseMapper<Grain> getMapper() {		return GrainMapper;	}	public int checkRepeat(Map map){		return GrainMapper.checkRepeat(map);	}}