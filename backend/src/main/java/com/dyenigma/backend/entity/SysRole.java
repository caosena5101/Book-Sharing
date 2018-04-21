package com.dyenigma.backend.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * backend/com.dyenigma.backend.entity
 *
 * @Description : 权限角色实体类，继承BaseDomain
 * @Author : dingdongliang
 * @Date : 2018/4/9 11:38
 */
@Getter
@Setter
public class SysRole extends BaseDomain {
    private String roleId;

    private String roleName;

    private String roleDesc;

    private String status = "E";

    private String isDefault = "N";

}