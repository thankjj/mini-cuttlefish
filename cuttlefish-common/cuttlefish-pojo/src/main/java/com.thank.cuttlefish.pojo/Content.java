package com.thank.cuttlefish.pojo;

import com.baomidou.mybatisplus.annotations.TableName;
import com.thank.cuttlefish.pojo.base.BaseEntity;
import lombok.Data;

@Data
@TableName("t_content")
public class Content extends BaseEntity {

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
  private String contentCoverUrl;




}
