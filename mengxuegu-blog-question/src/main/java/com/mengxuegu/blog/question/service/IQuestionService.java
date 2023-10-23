package com.mengxuegu.blog.question.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mengxuegu.blog.entities.Question;
import com.mengxuegu.blog.feign.req.UserInfoREQ;
import com.mengxuegu.blog.question.req.QuestionUserREQ;
import com.mengxuegu.blog.util.base.BaseRequest;
import com.mengxuegu.blog.util.base.Result;

/**
 * <p>
 * 问题信息表 服务类
 * </p>
 *
 * @author 梦学谷-www.mengxuegu.com
 */
public interface IQuestionService extends IService<Question> {

    /**
     * 分页查询热门问答列表
     * @param req 分页参数
     * @return
     */
    Result findHotList(BaseRequest<Question> req);

    /**
     * 分页查询最新问答列表
     * @param req
     * @return
     */
    Result findNewList(BaseRequest<Question> req);

    /**
     * 分页查询等待回答列表
     * @param req
     * @return
     */
    Result findWaitList(BaseRequest<Question> req);

    /**
     * 根据标签id分页查询问题列表
     * @param req 分页相关的对象
     * @param labelId 标签id
     * @return
     */
    Result findListByLabelId(BaseRequest<Question> req, String labelId);

    /**
     * 通过问题id查询详情
     * @param id 问题id
     * @return
     */
    Result findById(String id);

    /**
     * 更新问题浏览数
     * @param id 问题id
     * @return
     */
    Result updateViewCount(String id);

    /**
     * 修改或新增问题数据
     * @param question
     * @return
     */
    Result updateOrSave(Question question);

    /**
     * 假删除，只是装饰状态更新为 0 ，表示删除
     * @param id 问题id
     * @return
     */
    Result deleteById(String id);

    /**
     * 更新点赞数
     * @param id 问题id
     * @param count 是1（点赞）或者-1 (取消点赞)，
     * @return
     */
    Result updateThumhup(String id, int count);

    /**
     * 根据用户id查询问题列表
     * @param req
     * @return
     */
    Result findListByUserId(QuestionUserREQ req);

    /**
     * 统计提问总记录
     * @return
     */
    Result getQuestionTotal();

    /**
     * 更新问题表和回答表的用户信息
     * @param req
     * @return
     */
    boolean updateUserInfo(UserInfoREQ req);

}
