package com.dyenigma.backend.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * backend/com.dyenigma.backend.entity
 *
 * @Description : 角色权限映射
 * @Author : dingdongliang
 * @Date : 2018/4/25 11:18
 */
@Getter
@Setter
public class SysRolePmsn extends BaseDomain {
    private String rpId;

    private String roleId;

    private String pmsnId;

    private String status = "E";
}
