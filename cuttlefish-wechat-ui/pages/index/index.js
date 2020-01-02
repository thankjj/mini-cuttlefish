//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    // StatusBar: app.globalData.StatusBar,
    // CustomBar: app.globalData.CustomBar,
    // userInfo: {},
    // hasUserInfo: false,
    // canIUse: wx.canIUse('button.open-type.getUserInfo'),

    PageCur: 'basics'

  },
  NavChange(e) {
    this.setData({
      PageCur: e.currentTarget.dataset.cur
    })
  },

  onLoad: function () {
    
  },
  
  onShow: function () {
    var basics = this.selectComponent("#basics")
    // console.log("详情页面关闭->重新加载页面。。。。")
    if(basics){
      basics.request()
    }
    
  },

})
