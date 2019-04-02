package com.zhoubi.graindepot.biz;import com.zhoubi.graindepot.base.PagerModel;import com.zhoubi.graindepot.bean.Storage;import com.zhoubi.graindepot.bean.Suffrequ;import com.zhoubi.graindepot.mapper.StorageMapper;import com.zhoubi.graindepot.mapper.SuffrequMapper;import com.zhoubi.graindepot.base.BaseMapper;import com.zhoubi.graindepot.base.BaseService;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;import java.util.List;import java.util.Map;@Servicepublic class SuffrequBiz extends BaseService<Suffrequ> {    @Autowired    private SuffrequMapper SuffrequMapper;    @Autowired    private StorageMapper storageMapper;    @Autowired    private StorageBiz storageBiz;    @Override    protected BaseMapper<Suffrequ> getMapper() {        return SuffrequMapper;    }    public String getMaxBillcode(Integer graindepotid) {        return SuffrequMapper.getMaxBillcode(graindepotid);    }//    public List<Suffrequ> selectPageAuditList(Map<String, Object> map) {//        return SuffrequMapper.selectPageAuditList(map);//    }    //根据当前用户查询熏蒸待审批列表(分页)    public PagerModel<Suffrequ> selectAuditListByPage(PagerModel<Suffrequ> model) {        List<Suffrequ> list = SuffrequMapper.selectPageAuditList(model.getWhere());        for (Suffrequ suffrequ : list) {            Storage storage = storageBiz.selectById(suffrequ.getStorageid());            suffrequ.setStoragename(storage.getStoragename());        }//        Integer total = SuffrequMapper.selectAuditPageCount(model.getWhere());        Integer total = list.size();        model.setData(list);        model.setRecordsTotal(total);        model.setRecordsFiltered(total);        return model;    }}