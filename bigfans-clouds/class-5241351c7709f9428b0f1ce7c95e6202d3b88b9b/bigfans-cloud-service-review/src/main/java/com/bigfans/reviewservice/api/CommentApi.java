package com.bigfans.reviewservice.api;

import com.bigfans.framework.Applications;
import com.bigfans.framework.CurrentUser;
import com.bigfans.framework.annotations.NeedLogin;
import com.bigfans.framework.model.PageBean;
import com.bigfans.framework.model.PageContext;
import com.bigfans.framework.utils.StringHelper;
import com.bigfans.framework.web.BaseController;
import com.bigfans.framework.web.RestResponse;
import com.bigfans.reviewservice.clients.UserServiceClient;
import com.bigfans.reviewservice.form.CreateCommentForm;
import com.bigfans.reviewservice.model.Comment;
import com.bigfans.reviewservice.model.CommentScore;
import com.bigfans.reviewservice.model.OrderItemSurvey;
import com.bigfans.reviewservice.model.User;
import com.bigfans.reviewservice.service.CommentService;
import com.bigfans.reviewservice.service.OrderItemSurveyService;
import com.bigfans.reviewservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
public class CommentApi extends BaseController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderItemSurveyService orderItemSurveyService;
    @Autowired
    private UserServiceClient userServiceClient;

    @RequestMapping(value = "/comments", method = RequestMethod.GET)
    @ResponseBody
    public RestResponse listProductComments(
            @RequestParam(name = "pgId") String pgId,
            @RequestParam(name = "cp" , defaultValue = "1") Long cp
    ) throws Exception {
        PageContext.setCurrentPage(cp);
        PageContext.setPageSize(40L);

        PageBean<Comment> commentPage = commentService.pageByPg(pgId, PageContext.getStart(), PageContext.getPageSize());
        CommentScore score = commentService.getScore(pgId);
        Map<String, Object> data = new HashMap<>();
        data.put("commentPage", commentPage);
        data.put("score", score);
        return RestResponse.ok(data);
    }

    @PostMapping(value = "/comment")
    @ResponseBody
    @NeedLogin
    public RestResponse comment(@RequestBody CreateCommentForm form) throws Exception {
        CurrentUser currentUser = Applications.getCurrentUser();
        OrderItemSurvey survey = orderItemSurveyService.getByOrderItem(currentUser.getUid(), form.getOrderItemId());
        Comment entity = new Comment();
        entity.setUserId(currentUser.getUid());
        entity.setUserNickname(currentUser.getNickname());
        entity.setContent(form.getContent());
        entity.setOrderId(survey.getOrderId());
        entity.setOrderItemId(survey.getOrderItemId());
        entity.setPgId(survey.getPgId());
        entity.setProdId(survey.getProdId());
        entity.setRate(form.getRate());
        entity.setIsAnonymous(form.getIsAnonymous());
        commentService.create(entity);
        return RestResponse.ok();
    }

    @RequestMapping(value = "/countComment", method = RequestMethod.GET)
    @ResponseBody
    public RestResponse count(@RequestParam(name = "pid") String prodId, @RequestParam(name = "pgId") String pgId) throws Exception {
        Integer count = 0;
        if (StringHelper.isNotEmpty(prodId)) {
            count = commentService.countByProd(pgId, prodId);
        } else {
            count = commentService.countByPg(pgId);
        }
        return RestResponse.ok(count);
    }

}
