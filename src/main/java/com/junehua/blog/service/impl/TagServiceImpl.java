package com.junehua.blog.service.impl;


import com.junehua.blog.dao.TagDao;
import com.junehua.blog.pojo.Tag;
import com.junehua.blog.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TagServiceImpl implements ITagService {

    @Autowired
    private TagDao tagDao;

    /**
     * 保存
     * @param tag
     * @return
     */
    @Override
    public int saveTag(Tag tag) {
        return tagDao.saveTag(tag);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public int deleteTag(Long id) {
        return tagDao.deleteTag(id);
    }

    /**
     * 更新
     * @param tag
     * @return
     */
    @Override
    public int updateTag(Tag tag) {
        return tagDao.updateTag(tag);
    }

    /**
     * 查询一个
     * @param id
     * @return
     */
    @Override
    public Tag getById(Long id) {
        return tagDao.getById(id);
    }

    /**
     * 根据名称查询
     * @param name
     * @return
     */
    @Override
    public Tag getByName(String name) {
        return tagDao.getByName(name);
    }

    /**
     * 联合查询,返回博客信息和标签信息
     * @return
     */
    @Override
    public List<Tag> getAllTag() {
        return tagDao.getAllTag();
    }

    /**
     * 将text字符串转化为list集合，根据list集合查询数据库
     * @param text
     * @return
     */
    @Override
    public List<Tag> getTagByString(String text) {
        List<Tag> tags = new ArrayList<>();
        List<Long> longs = convertToList(text);
        for (Long aLong : longs) {
            tags.add(tagDao.getById(aLong));
        }
        return tags;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Tag> getAdminTag() {
        return tagDao.getAdminTag();
    }

    /**
     * 根据ids字符串，转化为list集合
     * @param ids
     * @return
     */
    private List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i=0; i < idarray.length;i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }
}
