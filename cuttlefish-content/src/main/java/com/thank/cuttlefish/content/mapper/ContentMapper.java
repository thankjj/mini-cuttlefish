package com.thank.cuttlefish.content.mapper;

import com.thank.cuttlefish.common.utils.MyMapper;
import com.thank.cuttlefish.content.dto.ContentDto;
import com.thank.cuttlefish.content.pojo.Content;

import java.util.List;
import java.util.Map;

public interface ContentMapper extends MyMapper<Content> {
    List<ContentDto> queryListByRand(ContentDto contentDto);

    // ========================= 内容浏览 ===============================
    // 是否存在用户浏览记录
    Integer isViewRecordExist(Map<String, Object> map);
    // 新增浏览记录
    Integer addViewRecord(Map<String, Object> map);
    // 更改浏览数据
    Integer updateViewRecord(Map<String, Object> map);

    // ========================= 内容浏览 ===============================
    // 用户是否点赞
//    Integer getThumbUpStatus(Map<String, Object> map);
    // 是否存在用户点赞记录
    Integer isThumbUpExist(Map<String, Object> map);
    // 新增点赞记录
    Integer addThumbUp(Map<String, Object> map);
    // 更改浏览数据
    Integer updateThumbUp(Map<String, Object> map);

}