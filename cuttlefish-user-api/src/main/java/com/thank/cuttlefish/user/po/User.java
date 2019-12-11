package com.thank.cuttlefish.user.po;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "t_user")
public class User {

  @Id
  private String id;
  private String password;
  private String qqId;
  private String wechatId;
  private String sinaId;
  private String nickname;
  private String avatarImgUrl;
  private Integer sex;
  private Integer age;
  private String phone;
  private String address;
  private String vocation;
  private String interest;
  private Integer userStatus;
  private Date createTime;
  private Date updateTime;
  private String ipAddress;
  private String signature;
  private Integer clientType;


}
