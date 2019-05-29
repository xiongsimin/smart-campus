package com.graduation.design.smartcmapusadminservice.serviceImp;

import com.graduation.design.smartcmapusadminservice.dao.ColumnDao;
import com.graduation.design.smartcmapusadminservice.dao.TopicDao;
import com.graduation.design.smartcmapusadminservice.entity.Column;
import com.graduation.design.smartcmapusadminservice.entity.TopicReply;
import com.graduation.design.smartcmapusadminservice.service.ColumnService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Service("columnService")
public class ColumnServiceImp implements ColumnService {
    @Resource
    private ColumnDao columnDao;
    @Resource
    private TopicDao topicDao;

    @Override
    public int addColumn(Column column) {
        return this.columnDao.addColumn(column);
    }

    @Override
    public int editColumn(Column column) {
        return this.columnDao.editColumn(column);
    }

    @Override
    public List<Column> getAllColumn() {
        return this.columnDao.getAllColumn();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delColumn(int id) {
        this.topicDao.clearColumnId(id);
        return this.columnDao.delColumn(id);
    }

    @Override
    public Column findColumnById(int id) {
        return this.columnDao.findColumnById(id);
    }
}
