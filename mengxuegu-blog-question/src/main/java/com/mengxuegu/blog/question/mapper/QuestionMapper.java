package com.mengxuegu.blog.question.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mengxuegu.blog.entities.Question;
import com.mengxuegu.blog.feign.req.UserInfoREQ;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 问题信息表 Mapper 接口
 * </p>
 *
 * @author 梦学谷-www.mengxuegu.com
 */
public interface QuestionMapper extends BaseMapper<Question> {

    /**
     * 根据标签id分页查询问题列表
     * @param page
     * @param labelId
     * @return
     */
    IPage<Question> findListByLabelId(
            IPage<Question> page, @Param("labelId") String labelId);

    /**
     * 根据问题id查询问题详情与属性标签ids
     * @param id
     * @return
     */
    Question findQuestionAndLabelIdsById(String id);

    /**
     * 通过问题id删除问题标签中间表
     * @param questionId
     * @return
     */
    boolean deleteQuestionLabel(@Param("questionId") String questionId);

    /**
     * 新增问题标签中间表数据
     * @param questionId 问题id
     * @param labelIds 标签id集合
     * @return
     */
    boolean saveQuestionLabel(@Param("questionId") String questionId,
                              @Param("labelIds") List<String> labelIds);

    /**
     * 更新问题表和回答表的用户信息
     * @param req
     * @return
     */
    boolean updateUserInfo(UserInfoREQ req);
}
