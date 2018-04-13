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
public class SysDivision extends BaseDomain {
    private String divId;

    private String divName;

    private String manager;

    private String divPhone;

    private String divAdr;

    private String coId;

    private String divDesc;

    private String status;


}