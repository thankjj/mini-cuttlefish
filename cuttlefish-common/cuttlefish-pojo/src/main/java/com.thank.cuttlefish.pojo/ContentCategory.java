package com.thank.cuttlefish.pojo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.thank.cuttlefish.pojo.base.BaseEntity;
import lombok.Data;


@Data
@TableName("t_content_category")
public class ContentCategory extends BaseEntity {

//  private Integer categoryId;
  @TableField("category_name")
  private String categoryName;
  @TableField("parent_id")
  private Integer parentId;
  private Integer depth;
  @TableField("category_status")
  private Integer categoryStatus;
  private Integer priority;
  @TableField("program_url")
  private String programUrl;
  @TableField("program_image_url")
  private String programImageUrl;


}
