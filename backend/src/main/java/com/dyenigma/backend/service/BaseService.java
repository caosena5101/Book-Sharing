package com.dyenigma.backend.service;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * sharing/com.dyenigma.backend.service
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/4/18 9:03
 */
public interface BaseService<T> {
    /**
     * 根据id查询实体
     *
     * @param id
     * @return T
     * @author dingdongliang
     * @date 2018/4/18 9:16
     */
    T selectByPrimaryKey(String id);

    /**
     * 查询所有
     *
     * @return java.util.List<T>
     * @author dingdongliang
     * @date 2018/4/18 9:16
     */
    List<T> selectAll();

    /**
     * 查询记录数
     *
     * @param t
     * @return int
     * @author dingdongliang
     * @date 2018/4/18 9:16
     */
    int selectCount(T t);

    /**
     * 全部分页
     *
     * @param page
     * @param rows
     * @return com.github.pagehelper.PageInfo<T>
     * @author dingdongliang
     * @date 2018/4/18 9:16
     */
    PageInfo<T> selectPageByAll(int page, int rows);

    /**
     * 查询一条记录
     *
     * @param t
     * @return T
     * @author dingdongliang
     * @date 2018/4/18 9:17
     */
    T selectOne(T t);

    /**
     * 插入
     *
     * @param t
     * @return int
     * @author dingdongliang
     * @date 2018/4/18 9:17
     */
    int insert(T t);

    /**
     * 新增非空字段
     *
     * @param t
     * @return int
     * @author dingdongliang
     * @date 2018/4/18 9:17
     */
    int insertSelective(T t);

    /**
     * 根据主键更新
     *
     * @param t
     * @return int
     * @author dingdongliang
     * @date 2018/4/18 9:17
     */
    int updateByPrimaryKey(T t);

    /**
     * 根据主键更新非空字段
     *
     * @param t
     * @return int
     * @author dingdongliang
     * @date 2018/4/18 9:17
     */
    int updateByPrimaryKeySelective(T t);

    /**
     * 根据主键删除
     *
     * @param id
     * @return int
     * @author dingdongliang
     * @date 2018/4/18 9:17
     */
    int deleteByPrimaryKey(String id);

}
