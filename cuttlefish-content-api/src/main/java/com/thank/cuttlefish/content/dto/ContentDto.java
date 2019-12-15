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

  private String categoryName;
  private String nickname;
  private String avatarImgUrl;



}
