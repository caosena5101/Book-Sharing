package com.dyenigma.sharing.dao;

import java.util.List;

/**
 * sharing/com.dyenigma.sharing.dao
 *
 * @Description : 自定义通用Mapper接口，继承tk.Mapper,为了方便扩展
 * @Author : dingdongliang
 * @Date : 2018/4/18 8:34
 */
public interface BaseMapper<T> {
    T selectOne(T record);

    List<T> select(T record);

    List<T> selectAll();

    int selectCount(T record);

    T selectByPrimaryKey(Object key);

    int insert(T record);

    int insertSelective(T record);

    int updateByPrimaryKey(T record);

    int updateByPrimaryKeySelective(T record);

    int delete(T record);

    int deleteByPrimaryKey(Object key);
}
