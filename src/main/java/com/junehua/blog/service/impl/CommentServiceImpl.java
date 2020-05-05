package com.junehua.blog.service.impl;

import com.junehua.blog.dao.BlogDao;
import com.junehua.blog.dao.CommentDao;
import com.junehua.blog.pojo.Comment;
import com.junehua.blog.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private BlogDao blogDao;

    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        //查询该博客下的评论，查找出来的是对作者的评价，没有父节点的默认为-1
        List<Comment> comments = commentDao.findByBlogIdParentIdNull(blogId, Long.parseLong("-1"));
        return comments;
    }

    @Override
    //接收回复的表单
    public int saveComment(Comment comment) {
        System.out.println("comment:" + comment);
        //判断有没有在别人的评论上进行评论还是一个新的评论
        Long parentCommentId = comment.getParentCommentId();
        //没有父级评论默认是-1
        if (parentCommentId != -1) {
            //有父级评论
            comment.setParentComment(commentDao.findByParentCommentId(comment.getParentCommentId()));
        } else {
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        comment.setBlog(blogDao.getDetailedBlog(comment.getBlogId()));
        return commentDao.saveComment(comment);
    }

}
