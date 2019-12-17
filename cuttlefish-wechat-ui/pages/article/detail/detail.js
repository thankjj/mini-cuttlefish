const app = getApp()
var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');

var time = 0, touchDot = 0, interval = 0
var flag = true
var sorted = "" // 是否是分类列表标志

Page({

  onLoad: function (options) {
    var content = JSON.parse(decodeURIComponent(options.content))
    sorted = content.sorted
    this.setData({
      title: content.title,
      nickname: content.nickname,
      avatarImgUrl: content.avatarImgUrl,
      updateTime: content.updateTime,
      content: content.content,
      contentCoverUrl: content.contentCoverUrl,

    })
  },

  
  // 触摸开始事件 
  touchStart: function (e) {
    touchDot = e.touches[0].pageX; // 获取触摸时的原点 
    // 使用js计时器记录时间  
    interval = setInterval(function () {
    time++;
    }, 100);
  },
  // 触摸移动事件 
  touchMove: function (e) {
    var touchMove = e.touches[0].pageX;
    // 向左滑动  
    if (touchMove - touchDot < -40 && time < 10 && flag) {
      var contentList = []
      contentList = util.isEmpty(sorted) ? app.globalData.contentList : app.globalData.sortedContentList
      var content = contentList.pop()
      this.setData({
        title: content.title,
        nickname: content.nickname,
        avatarImgUrl: content.avatarImgUrl,
        updateTime: content.updateTime,
        content: content.content,
        contentCoverUrl: content.contentCoverUrl,

      })
      if(contentList.length == 0){
        this.request(util.isEmpty(sorted) ? "" : sorted)
      }
      flag = false
    }
    // 向右滑动 
    // if (touchMove - touchDot >= 40 && time < 20 && flag) {
    //   console.log('向右滑动');
    //   flag = false
    // }
  },
  // 触摸结束事件 
  touchEnd: function (e) {
    clearInterval(interval); // 清除setInterval 
    time = 0;
    flag = true // 恢复滑动事件
  }, 

  request: function (sorted) {
    util.request(api.ContentList, { categoryId: sorted },
      'GET').then(res => {
        if (res.code == 200) {
          var data = res.data
          for (var item in data) {
            data[item].updateTime = util.formatTime(data[item].updateTime).replace(/-/g, '/')
            data[item].avatarImgUrl = util.isEmpty(data[item].avatarImgUrl) ?
              api.Constant_user_default_imag : data[item].avatarImgUrl
            data[item].contentCoverUrl = util.isEmpty(data[item].contentCoverUrl) ?
              api.Constant_content_cover_default_imag : data[item].contentCoverUrl
            data[item].nickname = util.isEmpty(data[item].nickname) ? api.Constant_user_default_nickname : data[item].nickname
            data[item].categoryName = util.isEmpty(data[item].categoryName) ?
              api.Constant_content__default_category : data[item].categoryName
          }
          if (util.isEmpty(sorted)){
            app.globalData.contentList =data
          }else{
            app.globalData.sortedContentList = data
          }
        }
      })
  },






})
