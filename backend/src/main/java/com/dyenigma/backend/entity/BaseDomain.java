package com.dyenigma.backend.entity;

import com.alibaba.fastjson.JSONObject;
import com.dyenigma.backend.constant.SystemConstant;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import java.io.Serializable;
import java.util.Date;

/**
 * backend/com.dyenigma.backend.entity
 *
 * @Description : 实体类基类，重写toString()方法，实现序列化接口， 否则Spring将对象存入Redis时会报错
 * @Author : dingdongliang
 * @Date : 2018/4/9 11:38
 */
@Getter
@Setter
public class BaseDomain implements Serializable {

    /**
     * 创造日期
     */
    protected Date created = new Date();
    /**
     * 修改日期
     */
    protected Date lastmod = new Date();
    /**
     * 创建人
     */
    protected String creater;
    /**
     * 修改人
     */
    protected String modifyer;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    /**
     * 数据库记录字段赋值，新添加记录时调用
     *
     * @param domain 子类对象
     * @return com.dyenigma.backend.entity.BaseDomain
     * @author dingdongliang
     * @date 2018/4/18 14:31
     */
    public static void createLog(BaseDomain domain) {
        createLog(domain, true);
    }

    /**
     * 数据库记录字段赋值，修改记录时调用
     *
     * @param domain 子类对象
     * @return com.dyenigma.backend.entity.BaseDomain
     * @author dingdongliang
     * @date 2018/4/18 14:31
     */
    public static void updateLog(BaseDomain domain) {
        createLog(domain, false);
    }

    /**
     * 数据库记录字段赋值，统一处理方法,TODO 这里包含测试数据userId，需要更改
     *
     * @param domain 子类对象
     * @param flag   true为新数据，false为修改数据
     * @return com.dyenigma.backend.entity.BaseDomain
     * @author dingdongliang
     * @date 2018/4/18 14:32
     */
    private static void createLog(BaseDomain domain, boolean flag) {

        Session session = SecurityUtils.getSubject().getSession();
        JSONObject userInfo = (JSONObject) session.getAttribute(SystemConstant.SESSION_USER_INFO);
        String userId;
        if (userInfo == null) {
            userId = "9045b033e3ad42b0bf7819a228dd50ee";
        } else {
            userId = userInfo.getString("userId");
        }

        if (flag) {
            domain.setCreated(new Date());
            domain.setLastmod(new Date());
            domain.setCreater(userId);
            domain.setModifyer(userId);
        } else {
            domain.setLastmod(new Date());
            domain.setModifyer(userId);
        }

    }
}
