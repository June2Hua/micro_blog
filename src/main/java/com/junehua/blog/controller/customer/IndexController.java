package com.junehua.blog.controller.customer;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.junehua.blog.dto.DetailedBlog;
import com.junehua.blog.dto.FirstPageBlog;
import com.junehua.blog.dto.RecommendBlog;
import com.junehua.blog.pojo.Comment;
import com.junehua.blog.pojo.Tag;
import com.junehua.blog.pojo.Type;
import com.junehua.blog.service.IBlogService;
import com.junehua.blog.service.ICommentService;
import com.junehua.blog.service.ITagService;
import com.junehua.blog.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class IndexController {

    @Autowired
    private IBlogService blogService;

    @Autowired
    private ITagService tagService;

    @Autowired
    private ITypeService typeService;

    @Autowired
    private ICommentService commentService;

    /**
     * 进入首页，查询所有的blog、tag、type、推荐博客
     * @param model
     * @param pageNum
     * @return
     */
    @GetMapping("/")
    public String index(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum, 6);
        //查询所有的blog
        List<FirstPageBlog> allFirstPageBlog = blogService.getAllFirstPageBlog();
        //查询所有的type，右侧
        List<Type> allType = typeService.getAllType();
        //查询所有的tag，右侧
        List<Tag> allTag = tagService.getAllTag();
        //查询推荐的blog，右侧
        List<RecommendBlog> recommendedBlog = blogService.getRecommendedBlog();
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(allFirstPageBlog);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("tags", allTag);
        model.addAttribute("types", allType);
        model.addAttribute("recommendedBlogs", recommendedBlog);
        return "index";
    }

    /**
     * 点击查询按钮，进行模糊查询
     * @param model
     * @param pageNum
     * @param query
     * @return
     */
    @GetMapping("/search")
    public String search(Model model,
                         @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                         @RequestParam String query) {
        PageHelper.startPage(pageNum, 100);
        //模糊查询
        List<FirstPageBlog> searchBlog = blogService.getSearchBlog(query);
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(searchBlog);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("query", query);
        return "search";
    }


    /**
     * 点击谋篇具体的博客
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) {
        //查询该blog的具体信息
        DetailedBlog detailedBlog = blogService.getDetailedBlog(id);
        System.out.println(detailedBlog);
        //查询博客的评价
        List<Comment> comments = commentService.listCommentByBlogId(id);
        System.out.println(comments);
        model.addAttribute("comments", comments);
        model.addAttribute("blog", detailedBlog);
        return "blog";
    }
}
