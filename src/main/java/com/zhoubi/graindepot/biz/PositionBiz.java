package com.zhoubi.graindepot.biz;import com.zhoubi.graindepot.base.BaseMapper;import com.zhoubi.graindepot.base.BaseService;import com.zhoubi.graindepot.bean.Storage;import com.zhoubi.graindepot.bean.Video;import com.zhoubi.graindepot.mapper.PositionMapper;import com.zhoubi.graindepot.mapper.StorageMapper;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;import java.util.List;import java.util.Map;@Servicepublic class PositionBiz  {	@Autowired	private PositionMapper positionMapper;	public int updateStoragePosition(Storage item){		return positionMapper.updateStoragePosition(item);	}	public List<Video> selectVideoList(Map map){		return positionMapper.selectVideoList(map);	}	public Storage selectStorageById(Integer id){		return positionMapper.selectStorageById(id);	}	public List<Storage> selectStorageList(Map map){		return positionMapper.selectStorageList(map);	}	public int updateVideoPosition(Video item){		return positionMapper.updateVideoPosition(item);	}}