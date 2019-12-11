package com.thank.cuttlefish.content.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "t_content_category")
public class ContentCategory {

  @Id
  private Integer categoryId;
  private String categoryName;
  private Integer parentId;
  private Integer depth;
  private Integer categoryStatus;
  private Integer priority;
  private String programUrl;
  private String programImageUrl;


}
