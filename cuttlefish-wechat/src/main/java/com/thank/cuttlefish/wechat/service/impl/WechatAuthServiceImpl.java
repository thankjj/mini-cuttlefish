package com.thank.cuttlefish.wechat.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.thank.cuttlefish.pojo.User;
import com.thank.cuttlefish.user.api.UserApi;
import com.thank.cuttlefish.wechat.config.WxMaConfiguration;
import com.thank.cuttlefish.wechat.constants.WechatConstants;
import com.thank.cuttlefish.wechat.service.WechatAuthService;
import com.thank.cuttlefish.wechat.utils.JsonUtils;
import com.thank.cuttlefish.wechat.utils.JwtHelper;
import com.thank.cuttlefish.pojo.vo.LoginAuthParamVO;
import com.thank.cuttlefish.pojo.vo.LoginAuthResultVO;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WechatAuthServiceImpl implements WechatAuthService {

    @Value("${cuttlefish.wx.miniapp.appid}")
    private String appid;

    @Autowired
    private UserApi userApi;

    @Override
    public LoginAuthResultVO login(LoginAuthParamVO loginAuthParamVO) throws WxErrorException {
        final WxMaService wxService = WxMaConfiguration.getMaService(appid);
        WxMaJscode2SessionResult sessionInfo = wxService.getUserService().getSessionInfo(loginAuthParamVO.getCode());
        LoginAuthParamVO.UserInfoX.UserInfo userInfo = loginAuthParamVO.getUserInfo().getUserInfo();
        //根据openId查询用户是否已经注册
//        User user = userApi.queryOneByCriteria(Criteria.of(User.class).andEqualTo(User::getWechatId, sessionInfo.getOpenid())).getData();
        User userCondition = new User();
        userCondition.setWechatId(sessionInfo.getOpenid());
        User user = userApi.selectOne(userCondition).getData();
        if (user == null) {
            //注册
            user = new User();
//            user.setId(Integer.parseInt(IdGenerator.INSTANCE.nextId()));
            user.setNickname(userInfo.getNickName());
            user.setPassword("");
            user.setCreateTime(new Date());
//            user.setIpAddress(WebUtil.getInstance().getIpAddress());
            user.setPhone("");
            user.setWechatId(sessionInfo.getOpenid());
            user.setAvatarImgUrl(userInfo.getAvatarUrl());
            user.setSex(userInfo.getGender());
            user.setNickname(userInfo.getNickName());
//            userApi.create(user);
            userApi.insert(user);
        }else {
            //查询用户信息
//            User newUser = userApi.queryOneByCriteria(Criteria.of(User.class).andEqualTo(User::getWechatId, sessionInfo.getOpenid())).getData();
            user.setUpdateTime(new Date());
//        newUser.setLastLoginIp(WebUtil.getInstance().getIpAddress());
            //更新登陆信息
            userApi.updateById(user);
        }
        //生成token
        String token = JwtHelper.createJWT("wechat", JsonUtils.toJson(user), WechatConstants.JWT_TTL);
        return new LoginAuthResultVO(token, user);
    }
}
