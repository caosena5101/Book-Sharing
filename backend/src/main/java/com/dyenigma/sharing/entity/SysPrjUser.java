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
public class SysPrjUser extends BaseDomain{
    private String puId;

    private String prjId;

    private String userId;

    private String status;

}