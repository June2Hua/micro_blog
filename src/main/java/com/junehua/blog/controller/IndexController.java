package com.junehua.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller
public class IndexController {

//    @GetMapping("/{id}/{name}")
    public String indexRoot(@PathVariable Integer id,@PathVariable String name){
        System.out.println("----------");
        return "tags";
    }
}
