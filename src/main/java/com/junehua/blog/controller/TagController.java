package com.junehua.blog.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.junehua.blog.pojo.Tag;
import com.junehua.blog.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TagController {

    //自动注入
    @Autowired
    private ITagService tagService;

    /**
     * 标签的首页
     * @param model
     * @param pageNum
     * @return
     */
    @GetMapping("/tags")
    public String list(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum) {
        PageHelper.startPage(pageNum, 4);
        List<Tag> allTag = tagService.getAdminTag();//查询所有
        PageInfo<Tag> pageInfo = new PageInfo<>(allTag);
        model.addAttribute("pageInfo", pageInfo);
        return "admin/tags";
    }

    /**
     * 新增标签页面
     * @return
     */
    @GetMapping("/tags/input")
    public String toAdd() {
        return "admin/tags-input";
    }

    /**
     * 新增标签页面点击提交
     * @param tag
     * @param attributes
     * @return
     */
    @PostMapping("/tags/add")
    public String add(Tag tag, RedirectAttributes attributes) {
        Tag tag1 = tagService.getByName(tag.getName());
        if (tag1 != null) {
            //不为空说明数据库已有
            attributes.addFlashAttribute("message", "已有这个标签，不能添加重复");
            return "redirect:/admin/tags/input";
        }
        tagService.saveTag(tag);
        return "redirect:/admin/tags";
    }

    /**
     * 修改标签内容
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id,Model model) {
        model.addAttribute("tag", tagService.getById(id));
        return "admin/tags-update";
    }

    @PostMapping("/tags/update")
    public String editPost(Tag tag) {
        tagService.updateTag(tag);
        return "redirect:/admin/tags";
    }

    /**
     * 删除标签内容
     * @param id
     * @return
     */
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id) {
        tagService.deleteTag(id);
        return "redirect:/admin/tags";
    }
}
