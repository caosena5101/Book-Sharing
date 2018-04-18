package com.dyenigma.sharing.dao;

import tk.mybatis.mapper.common.Mapper;

/**
 * sharing/com.dyenigma.sharing.dao
 *
 * @Description : 自定义通用Mapper接口，继承tk.Mapper,为了方便扩展
 * @Author : dingdongliang
 * @Date : 2018/4/18 8:34
 */
public interface BaseMapper<T> extends Mapper<T> {
}
