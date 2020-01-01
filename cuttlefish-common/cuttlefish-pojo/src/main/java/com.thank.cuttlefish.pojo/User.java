package com.thank.cuttlefish.pojo;

import com.baomidou.mybatisplus.annotations.TableName;
import com.thank.cuttlefish.pojo.base.BaseEntity;
import lombok.Data;

@Data
@TableName("t_user")
public class User extends BaseEntity {
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


}
