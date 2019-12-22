package com.thank.cuttlefish.content.service.impl;

import com.thank.cuttlefish.common.service.BaseService;
import com.thank.cuttlefish.common.utils.WebUtil;
import com.thank.cuttlefish.content.dto.ContentDto;
import com.thank.cuttlefish.content.mapper.ContentMapper;
import com.thank.cuttlefish.content.pojo.Content;
import com.thank.cuttlefish.content.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class ContentServiceImpl extends BaseService<Content> implements ContentService {
    @Autowired
    private ContentMapper contentMapper;

    @Override
    public List<ContentDto> queryListByRand(ContentDto contentDto) {
        return contentMapper.queryListByRand(contentDto);
    }

    @Override
    public Integer addOrUpdateViewRecord(ContentDto contentDto) {
        Date now = new Date();
        Map<String, Object> map = new HashMap<String, Object>();
        if ("".equals(contentDto.getAuthorId())){
            contentDto.setAuthorId(WebUtil.getInstance().getIpAddress());
        }
        map.put("userId", contentDto.getAuthorId());
        map.put("contentId", contentDto.getId());

        // 用户浏览记录总数
        Integer count = contentMapper.isViewRecordExist(map);
        if (count == 0){
            // 新增
            map.put("createTime", now);
            contentMapper.addViewRecord(map);
        }else {
            // 修改
            map.put("updateTime", now);
            contentMapper.updateViewRecord(map);
        }
        return contentMapper.getContentViewCount(contentDto.getId());

    }

    @Override
    public Integer addOrUpdateThumbUp(ContentDto contentDto) {
        Date now = new Date();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", contentDto.getAuthorId());
        map.put("contentId", contentDto.getId());

        // 是否存在用户点赞记录
        Integer count = contentMapper.isThumbUpExist(map);
        map.put("thumbUpStatus", contentDto.getThumbUpStatus());
        if (count == 0){
            // 新增
            map.put("createTime", now);
            contentMapper.addThumbUp(map);
        }else {
            // 修改
            map.put("updateTime", now);
            contentMapper.updateThumbUp(map);
        }
        return contentDto.getThumbUpStatus();
    }


}
