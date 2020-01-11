const app = getApp()
var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');

var time = 0, touchDot = 0, interval = 0
var flag = true
var sorted = "" // 是否是分类列表标志
var contentId = ""
var thumbUpCount = ""
var thumbUpStatus = ""

var currentContent = {}

var addOrUpdateViewRecordDelay = ""

var thumbUpIndex = ""

Page({

  onLoad: function (options) {
    var content = JSON.parse(decodeURIComponent(options.content))
    currentContent = content

    this.request(util.isEmpty(currentContent.sorted) ? "" : currentContent.sorted)

    this.setData({
      title: content.title,
      nickname: content.nickname,
      avatarImgUrl: content.avatarImgUrl,
      updateTime: content.updateTime,
      content: content.content,
      contentCoverUrl: content.contentCoverUrl,

      thumbUpStatus: content.thumbUpStatus,
      thumbUpCount: content.thumbUpCount,
      viewCount: content.viewCount,

    })
    addOrUpdateViewRecordDelay = setTimeout(this.addOrUpdateViewRecord, 2500) //延迟执行
  },

  // onUnload: function () {
  //   console.log("卸载页面。。。")
  //   clearTimeout(addOrUpdateViewRecordDelay) 
  //   // this.setData({
  //   //   contentCategoryList: app.globalData.contentCategoryList
  //   // })


  // },

  thumbUp: function (event) {
    if (app.globalData.userInfo) {
      // console.log("点赞........." + contentId + app.globalData.userInfo.id)
      util.request(api.ContentThumbUp,
        { id: currentContent.id, authorId: currentContent.authorId, operateId: app.globalData.userInfo.id, thumbUpStatus: currentContent.thumbUpStatus == 0 ? 1 : 0 },
        'GET').then(res => {
          if (res.code == 200) {
            console.log("点赞次数加一/减一：" + res.data)
            currentContent.thumbUpStatus = res.data
            currentContent.thumbUpCount = currentContent.thumbUpStatus == 1 ? 
                currentContent.thumbUpCount + 1 : currentContent.thumbUpCount - 1
            this.setData({
              thumbUpStatus: currentContent.thumbUpStatus,
              thumbUpCount: currentContent.thumbUpCount,
            })
            // 更新缓存数据
            var thumbUpIndex = this.getIndex(currentContent.id)
            if (util.isEmpty(currentContent.sorted)){
              app.globalData.contentList[thumbUpIndex].thumbUpStatus = currentContent.thumbUpStatus
              app.globalData.contentList[thumbUpIndex].thumbUpCount = currentContent.thumbUpCount
            }else{
              app.globalData.sortedContentList[thumbUpIndex].thumbUpStatus = currentContent.thumbUpStatus
              app.globalData.sortedContentList[thumbUpIndex].thumbUpCount = currentContent.thumbUpCount
            }
          }
        })
    }else{
      wx.showToast({
        title: '请先登录',
        icon: 'success',
        duration: 2000, 
        mask: true
      })
    }
    
  },


  addOrUpdateViewRecord: function () {
    // console.log("浏览........." + currentContent.id)
    var operateId = ""
    if (app.globalData.userInfo) {
      operateId =  app.globalData.userInfo.id
    }
    util.request(api.ContentView,
      { id: currentContent.id, authorId: currentContent.authorId, operateId: operateId },
      'GET').then(res => {
        if (res.code == 200) {
          console.log("浏览次数加一：" + res.data)
          this.setData({
            viewCount: res.data,
          })
          // 更新缓存数据
          var thumbUpIndex = this.getIndex(currentContent.id)
          if (util.isEmpty(currentContent.sorted) && thumbUpIndex > -1) {
            app.globalData.contentList[thumbUpIndex].viewCount = res.data
          } 
          if (!util.isEmpty(currentContent.sorted) && thumbUpIndex > -1) {
            app.globalData.sortedContentList[thumbUpIndex].viewCount = res.data
          }
        }
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
      clearTimeout(addOrUpdateViewRecordDelay)
      flag = false
      if (app.globalData.contentSwipeList.length == 1) {
        this.request(util.isEmpty(currentContent.sorted) ? "" : currentContent.sorted)
      }
      
      var contentSwipeList = []
      contentSwipeList = app.globalData.contentSwipeList
      console.log(app.globalData.contentSwipeList + "======================" + app.globalData.contentSwipeList.length)
      var content = contentSwipeList.pop()
      this.setData({
        title: content.title,
        nickname: content.nickname,
        avatarImgUrl: content.avatarImgUrl,
        updateTime: content.updateTime,
        content: content.content,
        contentCoverUrl: content.contentCoverUrl,
        contentId: content.id,

        viewCount: content.viewCount,
        thumbUpStatus: content.thumbUpStatus,
        thumbUpCount: content.thumbUpCount,

      })

      currentContent.id = content.id
      addOrUpdateViewRecordDelay = setTimeout(this.addOrUpdateViewRecord, 2500) //延迟执行

        
        
      
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
    console.log("获取swipelist数据。。。。。。")
    util.request(api.ContentList, { categoryId: sorted },
      'GET').then(res => {
        if (res.code == 200) {
          console.log("成功获取swipelist数据。。。。。。")
          var data = res.data
          for (var item in data) {
            data[item].updateTime = util.formatTime(data[item].updateTime).replace(/-/g, '/')
            data[item].avatarImgUrl = util.isEmpty(data[item].avatarImgUrl) ?
              api.Constant_user_default_imag_base64 : data[item].avatarImgUrl
            data[item].contentCoverUrl = util.isEmpty(data[item].contentCoverUrl) ?
              api.Constant_content_cover_default_imag_base64 : data[item].contentCoverUrl
            data[item].nickname = util.isEmpty(data[item].nickname) ? api.Constant_user_default_nickname : data[item].nickname
            data[item].categoryName = util.isEmpty(data[item].categoryName) ?
              api.Constant_content__default_category : data[item].categoryName
          }
          // if (util.isEmpty(sorted)){
            app.globalData.contentSwipeList =data
          // }else{
          //   app.globalData.sortedContentSwipeList = data
          // }
        }
      })
  },


  getIndex: function (contentId){
    var contentList = []
    contentList = util.isEmpty(currentContent.sorted) ? app.globalData.contentList : app.globalData.sortedContentList
    thumbUpIndex = contentList.map(o => o.id).indexOf(contentId)
    console.log(contentId + "第几个元素：" + thumbUpIndex)
    return thumbUpIndex
  },



})
