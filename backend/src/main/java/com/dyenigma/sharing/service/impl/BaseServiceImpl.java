package com.dyenigma.sharing.service.impl;

import com.dyenigma.sharing.dao.BaseMapper;
import com.dyenigma.sharing.service.BaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import javax.annotation.Resource;
import java.util.List;

/**
 * sharing/com.dyenigma.sharing.service.impl
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/4/18 9:17
 */
public class BaseServiceImpl<T> implements BaseService<T> {

    @Resource
    private BaseMapper<T> baseMapper;

    /**
     * 根据id查询实体
     *
     * @param id
     * @return T
     * @author dingdongliang
     * @date 2018/4/18 9:16
     */
    @Override
    public T selectByPrimaryKey(String id) {
        return baseMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有
     *
     * @return java.util.List<T>
     * @author dingdongliang
     * @date 2018/4/18 9:16
     */
    @Override
    public List<T> selectAll() {
        return baseMapper.selectAll();
    }

    /**
     * 条件查询
     *
     * @param t
     * @return java.util.List<T>
     * @author dingdongliang
     * @date 2018/4/18 9:16
     */
    @Override
    public List<T> selectByExample(T t) {
        return baseMapper.selectByExample(t);
    }

    /**
     * 查询记录数
     *
     * @param t
     * @return int
     * @author dingdongliang
     * @date 2018/4/18 9:16
     */
    @Override
    public int selectCount(T t) {
        return baseMapper.selectCount(t);
    }

    /**
     * 分页
     *
     * @param t
     * @param page
     * @param rows
     * @return com.github.pagehelper.PageInfo<T>
     * @author dingdongliang
     * @date 2018/4/18 9:16
     */
    @Override
    public PageInfo<T> selectPageByCondition(T t, int page, int rows) {
        PageHelper.startPage(page, rows);
        List<T> list = baseMapper.selectByExample(t);
        return new PageInfo<>(list);
    }

    /**
     * 查询一条记录
     *
     * @param t
     * @return T
     * @author dingdongliang
     * @date 2018/4/18 9:17
     */
    @Override
    public T selectOne(T t) {
        return baseMapper.selectOne(t);
    }

    /**
     * 插入
     *
     * @param t
     * @return int
     * @author dingdongliang
     * @date 2018/4/18 9:17
     */
    @Override
    public int insert(T t) {
        return baseMapper.insert(t);
    }

    /**
     * 新增非空字段
     *
     * @param t
     * @return int
     * @author dingdongliang
     * @date 2018/4/18 9:17
     */
    @Override
    public int insertSelective(T t) {
        return baseMapper.insertSelective(t);
    }

    /**
     * 根据主键更新
     *
     * @param t
     * @return int
     * @author dingdongliang
     * @date 2018/4/18 9:17
     */
    @Override
    public int updateByPrimaryKey(T t) {
        return baseMapper.updateByPrimaryKey(t);
    }

    /**
     * 根据主键更新非空字段
     *
     * @param t
     * @return int
     * @author dingdongliang
     * @date 2018/4/18 9:17
     */
    @Override
    public int updateByPrimaryKeySelective(T t) {
        return baseMapper.updateByPrimaryKeySelective(t);
    }

    /**
     * 根据主键删除
     *
     * @param id
     * @return int
     * @author dingdongliang
     * @date 2018/4/18 9:17
     */
    @Override
    public int deleteByPrimaryKey(String id) {
        return baseMapper.deleteByPrimaryKey(id);
    }
}
