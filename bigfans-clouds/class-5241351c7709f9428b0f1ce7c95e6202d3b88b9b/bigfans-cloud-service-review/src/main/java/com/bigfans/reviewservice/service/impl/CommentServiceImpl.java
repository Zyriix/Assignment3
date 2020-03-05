package com.bigfans.reviewservice.service.impl;

import com.bigfans.framework.dao.BaseServiceImpl;
import com.bigfans.framework.event.ApplicationEventBus;
import com.bigfans.framework.model.PageBean;
import com.bigfans.framework.model.PageContext;
import com.bigfans.model.event.comment.CommentCreatedEvent;
import com.bigfans.reviewservice.clients.UserServiceClient;
import com.bigfans.reviewservice.dao.CommentDAO;
import com.bigfans.reviewservice.model.Comment;
import com.bigfans.reviewservice.model.CommentScore;
import com.bigfans.reviewservice.service.CommentService;
import com.bigfans.reviewservice.service.OrderItemSurveyService;
import com.bigfans.reviewservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

/**
 * @author lichong 2014年12月27日上午9:27:48
 * @Description:评论服务类
 */
@Service(CommentServiceImpl.BEAN_NAME)
public class CommentServiceImpl extends BaseServiceImpl<Comment> implements CommentService {

    public static final String BEAN_NAME = "commentService";

    @Autowired
    private ApplicationEventBus applicationEventBus;
    @Autowired
    private UserService userService;
    @Autowired
    private UserServiceClient userServiceClient;
    @Autowired
    private OrderItemSurveyService orderItemSurveyService;

    private CommentDAO commentDAO;

    @Autowired
    public CommentServiceImpl(CommentDAO commentDAO) {
        super(commentDAO);
        this.commentDAO = commentDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public CommentScore getScore(String pgId) throws Exception {
        CommentScore score = commentDAO.getScore(pgId);
//        Map<String, String> scoreMap = commentDAO.getScore(productId);
//        CommentScore score = commentDAO.getScore(productId);
//        score.setHpCount(Integer.parseInt(scoreMap.get(CommentDAO.SCORE_HP)));
//        score.setZpCount(Integer.parseInt(scoreMap.get(CommentDAO.SCORE_ZP)));
//        score.setCpCount(Integer.parseInt(scoreMap.get(CommentDAO.SCORE_CP)));
//        score.setAllCount(Integer.parseInt(scoreMap.get(CommentDAO.SCORE_ALL)));
//        score.setAvgRate(Float.parseFloat(scoreMap.get(CommentDAO.SCORE_AVG)));
//        score.setHpPercent(Float.parseFloat(scoreMap.get(CommentDAO.SCORE_HP_PERCENT)));
        return score;
    }

    @Override
    @Transactional(readOnly = true)
    public Integer countByPg(String pgId) throws Exception {
        return commentDAO.count(pgId, null);
    }

    @Override
    @Transactional(readOnly = true)
    public String getFormatedCountByPg(String pgId) throws Exception {
        Integer count = commentDAO.count(pgId, null);
        if (count < 1000) {
            return String.valueOf(count);
        }
        if (count >= 1000 && count < 10000) {
            int c = count / 100;
            return c * 100 + "+";
        }
        float c = (float) count / 10000;
        DecimalFormat df = new DecimalFormat("0.0");
        return df.format(c) + "万+";
    }

    @Override
    @Transactional(readOnly = true)
    public Integer countByProd(String pgId, String prodId) throws Exception {
        return commentDAO.count(pgId, prodId);
    }

    @Transactional(readOnly = true)
    public PageBean<Comment> pageByProduct(String productId, Long start, Long pagesize) throws Exception {
        List<Comment> data = commentDAO.listByProduct(productId, start, pagesize);
        PageBean<Comment> pageBean = new PageBean<>(data, PageContext.getDataCount());
        return pageBean;
    }

    @Override
    @Transactional(readOnly = true)
    public PageBean<Comment> pageByPg(String pgId, Long start, Long pagesize) throws Exception {
        List<Comment> data = commentDAO.listByPg(pgId, start, pagesize);
        PageBean<Comment> pageBean = new PageBean<>(data, PageContext.getDataCount());
        return pageBean;
    }

    @Transactional
    public void create(Comment comment) throws Exception {
        super.create(comment);
        orderItemSurveyService.deleteByOrderItem(comment.getUserId() , comment.getOrderItemId());
        applicationEventBus.publishEvent(new CommentCreatedEvent(comment.getOrderItemId(), comment.getId()));
    }
}
