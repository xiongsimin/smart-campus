package com.graduation.design.smartcmapusadminservice.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TopicDao {
    /**
     * 将兴趣帖所属板块（被删除）清空
     * @return
     */
    int clearColumnId(int columnId);
}
