package com.thank.cuttlefish.user.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.thank.cuttlefish.base.service.impl.MyServiceImpl;
import com.thank.cuttlefish.base.utils.WebUtil;
import com.thank.cuttlefish.common.constant.CuttlefishRedisConstant;
import com.thank.cuttlefish.common.constant.WechatConstants;
import com.thank.cuttlefish.pojo.User;
import com.thank.cuttlefish.dto.UserDto;
import com.thank.cuttlefish.dto.WechatLoginParam;
import com.thank.cuttlefish.user.config.WxMaConfiguration;
import com.thank.cuttlefish.user.mapper.UserMapper;
import com.thank.cuttlefish.user.service.UserService;
import com.thank.cuttlefish.user.utils.JwtHelper;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class UserServiceImpl extends MyServiceImpl<User> implements UserService {

    @Value("${cuttlefish.wx.miniapp.appid}")
    private String appid;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public UserDto login(WechatLoginParam loginAuthParamVO) throws WxErrorException {
        final WxMaService wxService = WxMaConfiguration.getMaService(appid);
        // 获取微信用户唯一标识
        WxMaJscode2SessionResult sessionInfo = wxService.getUserService().getSessionInfo(loginAuthParamVO.getCode());
        String wechatId = sessionInfo.getOpenid();

        // 从缓存获取用户信息
        UserDto userDto = (UserDto) redisTemplate.opsForValue().get(CuttlefishRedisConstant.REDIS_KEY_USER_PREFIX + wechatId);

        User user = new User();
        if (userDto == null){
            WechatLoginParam.UserInfoX.UserInfo userInfo = loginAuthParamVO.getUserInfo().getUserInfo();
            // 用户首次登录
            String token = JwtHelper.createJWT("wechat", wechatId, WechatConstants.JWT_TTL);
            user.setToken(token);
            user.setNickname(userInfo.getNickName());
            user.setPassword("");
            user.setCreateTime(new Date());
            user.setIpAddress(WebUtil.getInstance().getIpAddress());
            user.setPhone("");
            user.setWechatId(sessionInfo.getOpenid());
            user.setAvatarImgUrl(userInfo.getAvatarUrl());
            user.setSex(userInfo.getGender());
            user.setNickname(userInfo.getNickName());
            userMapper.insert(user);
            userDto = new UserDto();
            BeanUtils.copyProperties(user, userDto);
            userDto.setViewCount(0);
            userDto.setThumbUpCount(0);
            // token加入缓存
            redisTemplate.opsForValue().set(CuttlefishRedisConstant.REDIS_KEY_TOKEN_PREFIX + wechatId, token);
            redisTemplate.expire(CuttlefishRedisConstant.REDIS_KEY_TOKEN_PREFIX + wechatId, CuttlefishRedisConstant.REDIS_KEY_TOKEN_TIMEOUT, TimeUnit.SECONDS);
        }else{
            BeanUtils.copyProperties(userDto, user);
            // 用户非首次登录，需校验token是否过期
            Long tokenValidTime = redisTemplate.getExpire(CuttlefishRedisConstant.REDIS_KEY_TOKEN_PREFIX + wechatId);
            if (tokenValidTime < 0){
                // 过期需要重新生成token
                String token = JwtHelper.createJWT("wechat", wechatId, WechatConstants.JWT_TTL);
                user.setToken(token);
                // token加入缓存
                redisTemplate.opsForValue().getAndSet(CuttlefishRedisConstant.REDIS_KEY_TOKEN_PREFIX + wechatId, token);
                redisTemplate.expire(CuttlefishRedisConstant.REDIS_KEY_TOKEN_PREFIX + wechatId, CuttlefishRedisConstant.REDIS_KEY_TOKEN_TIMEOUT, TimeUnit.SECONDS);
            }
            // 更新用户信息
            user.setUpdateTime(new Date());
            user.setIpAddress(WebUtil.getInstance().getIpAddress());
            userMapper.updateById(user);
            BeanUtils.copyProperties(user, userDto);
        }
        // 更新用户缓存
        redisTemplate.opsForValue().getAndSet(CuttlefishRedisConstant.REDIS_KEY_USER_PREFIX + wechatId, userDto);

        return userDto;
    }


}
