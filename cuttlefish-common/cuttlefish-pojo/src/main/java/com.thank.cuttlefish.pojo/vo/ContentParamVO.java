package com.thank.cuttlefish.pojo.vo;

import com.thank.cuttlefish.pojo.Content;
import lombok.Data;

@Data
public class ContentParamVO extends Content {

    /**
     * 页码，从1开始
     */
    private int pageNum;
    /**
     * 页面大小
     */
    private int pageSize;


}
