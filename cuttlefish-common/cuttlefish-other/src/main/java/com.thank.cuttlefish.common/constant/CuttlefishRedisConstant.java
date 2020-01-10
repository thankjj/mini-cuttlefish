package com.thank.cuttlefish.common.constant;

public class CuttlefishRedisConstant {

    public static final String REDIS_KEY_CONTENT_CATEGORY_LIST = "key_content_category_list"; // 分类缓存key

    public static final String REDIS_KEY_USER_PREFIX = "key_user_"; // 用户key
    public static final String REDIS_KEY_TOKEN_PREFIX = "key_token_"; // 用户token前缀key

    public static final String REDIS_KEY_USER_THUMBUP_PREFIX = "key_user_thumbup_up_"; // 点赞缓存key
    public static final String REDIS_KEY_USER_THUMBUP_CANCEL_PREFIX = "key_user_thumbup_cancel_"; // 点赞取消key

    public static final String REDIS_KEY_CONTENT_VIEW_COUNT_PREFIX = "key_content_view_count_"; // 文章浏览总数缓存key
    public static final String REDIS_KEY_CONTENT_VIEW_RECORD_PREFIX = "key_content_view_record_"; // 文章浏览用户缓存key


    public static final Integer REDIS_KEY_TOKEN_TIMEOUT = 30; // 用户token有效时间，单位秒
    public static final Integer REDIS_KEY_CONTENT_VIEW_RECORD_TIMEOUT = 60; // 文章再次浏览约定时间，单位秒




}
