package com.dyenigma.sharing.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * backend/com.dyenigma.sharing.entity
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/4/9 11:38
 */
@Getter
@Setter
public class SysRole extends BaseDomain {
    private String roleId;

    private String roleName;

    private String roleDesc;

    private String status;

    private String isDefault;

}