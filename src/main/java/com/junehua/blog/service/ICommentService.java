package com.junehua.blog.service;

import com.junehua.blog.pojo.Comment;

import java.util.List;

public interface ICommentService {
    List<Comment> listCommentByBlogId(Long blogId);

    int saveComment(Comment comment);
}
