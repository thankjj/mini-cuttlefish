package com.thank.cuttlefish.content.pojo;


import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "t_content")
public class Content {

  @Id
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




}
