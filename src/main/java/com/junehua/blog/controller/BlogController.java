package com.junehua.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @RequestMapping("/blogs")
    public String blogsPage(){
        return "admin/blogs";
    }
}
