package com.zhoubi.graindepot.biz;import com.zhoubi.graindepot.bean.Notice;import com.zhoubi.graindepot.mapper.NoticeMapper;import com.zhoubi.graindepot.base.BaseMapper;import com.zhoubi.graindepot.base.BaseService;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;@Servicepublic class NoticeBiz extends BaseService<Notice> {    @Autowired    private NoticeMapper NoticeMapper;    @Override    protected BaseMapper<Notice> getMapper() {        return NoticeMapper;    }    public int updateApply(Notice item) {       return NoticeMapper.updateApply(item);    }    public int updateRelease(Notice item) {        return NoticeMapper.updateRelease(item);    }}