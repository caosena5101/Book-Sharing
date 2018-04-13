package com.dyenigma.sharing.service;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * backend/com.dyenigma.sharing.service
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/4/11 16:12
 */
public interface BaseService<T> {


    /**
     * @param id
     * @return T
     * @Description: 根据id查询实体
     * @author dingdongliang
     * @date 2018/4/11 16:17
     */
    T selectById(String id);

    /**
     * @return java.util.List<T>
     * @Description: 查询所有
     * @author dingdongliang
     * @date 2018/4/11 16:17
     */
    List<T> selectAll();

    /**
     * @param param
     * @return java.util.List<T>
     * @Description: 条件查询
     * @author dingdongliang
     * @date 2018/4/11 16:17
     */
    List<T> selectByCondition(T param);

    /**
     * @param param
     * @return java.lang.Integer
     * @Description: 查询记录数
     * @author dingdongliang
     * @date 2018/4/11 16:17
     */
    Integer selectCount(T param);

    /**
     * @param param
     * @param page
     * @param rows
     * @return com.github.pagehelper.PageInfo<T>
     * @Description: 分页
     * @author dingdongliang
     * @date 2018/4/11 16:17
     */
    PageInfo<T> selectPageByCondition(T param, Integer page, Integer rows);

    /**
     * @param param
     * @return T
     * @Description: 查询一条记录
     * @author dingdongliang
     * @date 2018/4/11 16:17
     */
    T selectOne(T param);

    /**
     * @param param
     * @return java.lang.Integer
     * @Description: 插入
     * @author dingdongliang
     * @date 2018/4/11 16:17
     */
    Integer insert(T param);

    /**
     * @param param
     * @return java.lang.Integer
     * @Description: 新增非空字段
     * @author dingdongliang
     * @date 2018/4/11 16:18
     */
    Integer insertSelective(T param);

    /**
     * @param param
     * @return java.lang.Integer
     * @Description: 根据主键更新
     * @author dingdongliang
     * @date 2018/4/11 16:18
     */
    Integer update(T param);

    /**
     * @param param
     * @return java.lang.Integer
     * @Description: 根据主键更新非空字段
     * @author dingdongliang
     * @date 2018/4/11 16:18
     */
    Integer updateSelective(T param);

    /**
     * @param id
     * @return java.lang.Integer
     * @Description: 根据主键删除
     * @author dingdongliang
     * @date 2018/4/11 16:18
     */
    Integer deleteById(String id);
}
