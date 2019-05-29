package com.graduation.design.smartcmapusadminservice.dao;

import com.graduation.design.smartcmapusadminservice.entity.Column;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ColumnDao {
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
     *
     * @param id
     * @return
     */
    int delColumn(int id);

    /**
     * 根据id查找兴趣版块
     *
     * @param id
     * @return
     */
    Column findColumnById(int id);
}
