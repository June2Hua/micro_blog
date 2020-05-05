package com.junehua.blog.dto;

import java.io.Serializable;

/**
 * 是否推荐的博客
 */
public class RecommendBlog implements Serializable {

    private Long id;
    private String title;
    private boolean recommend;

    public RecommendBlog() {
    }

    public RecommendBlog(Long id, String title, boolean recommend) {
        this.id = id;
        this.title = title;
        this.recommend = recommend;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }
}
