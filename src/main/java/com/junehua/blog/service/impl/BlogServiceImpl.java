package com.junehua.blog.service.impl;

import com.junehua.blog.dao.BlogDao;
import com.junehua.blog.dto.*;
import com.junehua.blog.pojo.Blog;
import com.junehua.blog.pojo.Tag;
import com.junehua.blog.service.IBlogService;
import com.junehua.blog.util.MarkdownUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImpl implements IBlogService {

    private final Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    private BlogDao blogDao;

    @Override
    public ShowBlog getBlogById(Long id) {
        return blogDao.getBlogById(id);
    }

    @Override
    public List<BlogQuery> getAllBlog() {
        List<BlogQuery> allBlogQuery = blogDao.getAllBlogQuery();
        return allBlogQuery;
    }

    /**
     * 将博客对象以及（博客_标签）添加到数据库中
     * @param blog
     * @return
     */
    @Override
    public int saveBlog(Blog blog) {
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        int result=blogDao.saveBlog(blog);
        //将标签的数据存到t_blogs_tag表中
        List<Tag> tags = blog.getTags();
        BlogAndTag blogAndTag = null;
        for (Tag tag : tags) {
            blogAndTag = new BlogAndTag(tag.getId(),blog.getId());
            blogDao.saveBlogAndTag(blogAndTag);
        }
        return result;
    }

    @Override
    public int updateBlog(ShowBlog showBlog) {
        showBlog.setUpdateTime(new Date());
        return blogDao.updateBlog(showBlog);
    }

    @Override
    public int deleteBlog(Long id) {
        blogDao.deleteBlogAndTag(id);
        blogDao.deleteBlog(id);
        return 1;
    }

    @Override
    public List<BlogQuery> getBlogBySearch(SearchBlog searchBlog) {
        return blogDao.searchByTitleOrTypeOrRecommend(searchBlog);
    }

    @Override
    public void transformRecommend(SearchBlog searchBlog) {
        if (!"".equals(searchBlog.getRecommend()) && null != searchBlog.getRecommend()) {
            searchBlog.setRecommend2(1);
        }
    }

    @Override
    public List<FirstPageBlog> getAllFirstPageBlog() {
        return blogDao.getFirstPageBlog();
    }


    @Override
    public List<RecommendBlog> getRecommendedBlog() {
        List<RecommendBlog> allRecommendBlog = blogDao.getAllRecommendBlog();
        List<RecommendBlog> allRecommendedBlog = new ArrayList<>();
        for (RecommendBlog recommendBlog : allRecommendBlog) {
            if (recommendBlog.isRecommend() == true) {
                allRecommendedBlog.add(recommendBlog);
            }
        }
        return allRecommendedBlog;
    }

    @Override
    public List<FirstPageBlog> getSearchBlog(String query) {
        return blogDao.getSearchBlog(query);
    }

    @Cacheable(cacheNames = "blog",keyGenerator = "myKeyGenerator")
    @Override
    public DetailedBlog getDetailedBlog(Long id) {
        DetailedBlog detailedBlog = blogDao.getDetailedBlog(id);
        if (detailedBlog == null) {
            logger.error("错误，查询不到博客");
            return null;
        }
        String content = detailedBlog.getContent();
        detailedBlog.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        return detailedBlog;
    }

    @Override
    public List<FirstPageBlog> getByTypeId(Long typeId) {
        return blogDao.getByTypeId(typeId);
    }

    @Override
    public List<FirstPageBlog> getByTagId(Long tagId) {
        return blogDao.getByTagId(tagId);
    }


}
