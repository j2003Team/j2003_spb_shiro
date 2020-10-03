package com.qf.j2003.po;

import lombok.Data;

import java.io.Serializable;
@Data
public class SysPerm implements Serializable {
    private Integer permId;
    private String permName;
    private String menuName;
    private String menuType;
    private String menuUrl;
    private String parentCode;
    private String permDesc;
    private Integer ifValid;


}
