package com.thank.cuttlefish.pojo.base;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public abstract class BaseEntity extends Model implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    protected Integer id;
    @TableField("create_time")
    protected Date createTime;
    @TableField("update_time")
    protected Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}