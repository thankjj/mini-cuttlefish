package com.thank.cuttlefish.content.dto;


import lombok.Data;

import java.util.Date;

@Data
public class ContentDto {

  private Integer id;
  private String content;
  private Integer categoryId;
  private String authorId;
  private String title;
  private Integer contentType;
  private Integer auditStatus;
  private String keyWords;
  private Integer contentFrom;
  private String fromDesc;
  private Integer highQuality;
  private Integer score;
  private Integer validStatus;
  private Date createTime;
  private Date updateTime;
  private String contentCoverUrl;

  private String categoryName; // 分类名称
  private String nickname; // 用户名称
  private String avatarImgUrl; // 用户头像地址

  private Integer thumbUpStatus; // 是否点赞
  private Integer thumbUpCount; // 点赞数
  private Integer viewCount; // 浏览数


}
