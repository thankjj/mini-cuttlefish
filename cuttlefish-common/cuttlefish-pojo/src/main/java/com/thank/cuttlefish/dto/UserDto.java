package com.thank.cuttlefish.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserDto implements Serializable {
  protected Integer id;

  protected Date createTime;
  protected Date updateTime;
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
  private String ipAddress;
  private String signature;
  private Integer clientType;
  private String token;

  private Integer thumbUpCount; // 点赞数
  private Integer viewCount; // 浏览数


}
