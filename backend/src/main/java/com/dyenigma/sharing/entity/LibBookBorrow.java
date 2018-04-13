package com.dyenigma.sharing.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * backend/com.dyenigma.sharing.entity
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/4/9 11:38
 */
@Getter
@Setter
public class LibBookBorrow extends BaseDomain {
    private String borrowId;

    private String userId;

    private String bookId;

    private String statusId;

    private Date borrowTime;

    private Date returnTime;

}