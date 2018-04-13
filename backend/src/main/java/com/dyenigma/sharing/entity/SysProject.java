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
public class SysProject extends BaseDomain {
    private String prjId;

    private String prjName;

    private String prjDesc;

    private String leader;

    private String status;

    private String coId;


}