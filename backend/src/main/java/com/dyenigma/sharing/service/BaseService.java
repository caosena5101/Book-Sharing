package com.dyenigma.sharing.service;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * sharing/com.dyenigma.sharing.service
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
     * @param
     * @return java.util.List<T>
     * @author dingdongliang
     * @date 2018/4/18 9:16
     */
    List<T> selectAll();

    /**
     * 条件查询
     *
     * @param param
     * @return java.util.List<T>
     * @author dingdongliang
     * @date 2018/4/18 9:16
     */
    List<T> selectByExample(T param);

    /**
     * 查询记录数
     *
     * @param param
     * @return int
     * @author dingdongliang
     * @date 2018/4/18 9:16
     */
    int selectCount(T param);

    /**
     * 分页
     *
     * @param param
     * @param page
     * @param rows
     * @return com.github.pagehelper.PageInfo<T>
     * @author dingdongliang
     * @date 2018/4/18 9:16
     */
    PageInfo<T> selectPageByCondition(T param, int page, int rows);

    /**
     * 查询一条记录
     *
     * @param param
     * @return T
     * @author dingdongliang
     * @date 2018/4/18 9:17
     */
    T selectOne(T param);

    /**
     * 插入
     *
     * @param param
     * @return int
     * @author dingdongliang
     * @date 2018/4/18 9:17
     */
    int insert(T param);

    /**
     * 新增非空字段
     *
     * @param param
     * @return int
     * @author dingdongliang
     * @date 2018/4/18 9:17
     */
    int insertSelective(T param);

    /**
     * 根据主键更新
     *
     * @param param
     * @return int
     * @author dingdongliang
     * @date 2018/4/18 9:17
     */
    int updateByPrimaryKey(T param);


    /**
     * 根据主键更新非空字段
     *
     * @param param
     * @return int
     * @author dingdongliang
     * @date 2018/4/18 9:17
     */
    int updateByPrimaryKeySelective(T param);


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
