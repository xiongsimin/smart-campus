package com.graduation.design.smartcmapusadminservice.service;

import com.graduation.design.smartcmapusadminservice.entity.Column;

import java.util.List;

public interface ColumnService {
    /**
     * 新增兴趣版块
     *
     * @param column
     * @return
     */
    int addColumn(Column column);

    /**
     * 修改兴趣版块
     *
     * @param column
     * @return
     */
    int editColumn(Column column);

    /**
     * 获取所有版块
     *
     * @return
     */
    List<Column> getAllColumn();

    /**
     * 删除兴趣版块
     * @param id
     * @return
     */
    int delColumn(int id);

    /**
     * 根据id查找兴趣版块
     * @param id
     * @return
     */
    Column findColumnById(int id);
}
