const app = getApp()
var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');

Component({
  options: {
    addGlobalClass: true,
  },
  data: {
    StatusBar: app.globalData.StatusBar,
    CustomBar: app.globalData.CustomBar,
    starCount: 0,
    forksCount: 0,
    visitTotal: 0,

    
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo')
  },

  attached: function () {
    if (app.globalData.userInfo) {
      this.setData({
        userInfo: app.globalData.userInfo,
        hasUserInfo: true
      })
    } 
  },

  methods: {
    getUserInfo: function (e) {
      util.login().then((res) => {
        //登录远程服务器
        util.request(api.AuthLoginByWeixin, {
          code: res,
          userInfo: e.detail
        }, 'POST').then(res => {
          if (res.code == 200) {
            //存储用户信息
            wx.setStorageSync('userInfo', res.data.userInfo);
            wx.setStorageSync('token', res.data.token);
            //返回上一页面
            // wx.redirectTo({
            //   url: backUrl + '?' + backParams,
            // })
            console.log(res.data.userInfo.nickname)
            app.globalData.userInfo = res.data.userInfo
            this.setData({
              userInfo: res.data.userInfo,
              hasUserInfo: true
            })
          }
        })
      });
      
    },



  }
  
  

})