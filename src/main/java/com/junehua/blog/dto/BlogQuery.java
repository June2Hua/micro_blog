package com.junehua.blog.dto;

import com.junehua.blog.pojo.Type;

import java.io.Serializable;
import java.util.Date;

/**
 * 博客列表页显示简要博客的类
 * id，标题，更新时间，
 */

public class BlogQuery implements Serializable {
    private Long id;
    private String title;
    private Date updateTime;
    private Integer recommend;
    private Long typeId;

    private Type type;

    public BlogQuery() {
    }

    public BlogQuery(Long id, String title, Date updateTime, Integer recommend, Long typeId, Type type) {
        this.id = id;
        this.title = title;
        this.updateTime = updateTime;
        this.recommend = recommend;
        this.typeId = typeId;
        this.type = type;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getRecommend() {
        return recommend;
    }

    public void setRecommend(Integer recommend) {
        this.recommend = recommend;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
