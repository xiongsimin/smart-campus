package com.graduation.design.smartcmapusadminservice.dao;

import com.graduation.design.smartcmapusadminservice.entity.Topic;
import com.graduation.design.smartcmapusadminservice.entity.TopicReply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TopicReplyDao {
    /**
     * 添加一条回复记录
     *
     * @param topicReply
     * @return
     */
    int addTopicReply(TopicReply topicReply);

    /**
     * 根据id删除回复(同时删除属于该回复的二级回复)
     *
     * @param id
     * @return
     */
    int delTopicReplyById(int id);

    /**
     * 删除某个兴趣帖下的所有回复
     * @param topicId
     * @return
     */
    int delTopicReplyByTopicId(int topicId);

    /**
     * 根据id查询回复
     * @param id
     * @return
    TopicReply findTopicReplyById(int id);*/

    /**
     * 根据兴趣帖id查询回复
     * @param topicId
     * @return
     */
    List<TopicReply> findTopicReplyByTopicId(int topicId);

    /**根据一级回复的id查找回复
     *
     * @return
     */
    List<TopicReply> findSubTopicReplyById(int id);

    /**
     * 查询被举报超过5次的评论
     * @return
     */
    List<TopicReply> findAccusedTopicReply();
}
