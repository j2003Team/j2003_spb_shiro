package com.qf.j2003.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class SysUser implements Serializable {

    private Integer userid;
    private String loginName;
    private  String password;
    private  Integer state;
    private Date creatTime;
    private String realname;


}
