package com.dyenigma.backend.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * backend/com.dyenigma.backend.entity
 *
 * @Description : 系统用户角色类，继承BaseDomain
 * @Author : dingdongliang
 * @Date : 2018/4/17 10:25
 */
@Getter
@Setter
public class SysUserRole extends BaseDomain {
    private String urId;

    private String roleId;

    private String userId;

    private String status = "E";

}