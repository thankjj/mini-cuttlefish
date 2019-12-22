const app = getApp()
var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');

var categoryId = 0

Page({
  data: {
    contentList: []
  },
  
  onLoad: function (options) {
    var category = JSON.parse(decodeURIComponent(options.category))
    this.setData({
      categoryName: category.categoryName,
    })
    categoryId = category.categoryId
    // console.log(categoryId+"=========================")
    this.request(categoryId)
  },



  request: function (categoryId) {
    // if (!app.globalData.sortedContentList) {
      wx.showLoading({
        title: '加载中',
        mask: true,
      })
      util.request(api.ContentList, { categoryId: categoryId },
        'GET').then(res => {
          if (res.code == 200) {
            var data = res.data
            for (var item in data) {
              data[item].updateTime = (util.formatTime(data[item].updateTime) + "").replace(/-/g, '/')
              data[item].avatarImgUrl = util.isEmpty(data[item].avatarImgUrl) ?
                api.Constant_user_default_imag_base64 : data[item].avatarImgUrl
              data[item].contentCoverUrl = util.isEmpty(data[item].contentCoverUrl) ?
                api.Constant_content_cover_default_imag_base64 : data[item].contentCoverUrl
              data[item].nickname = util.isEmpty(data[item].nickname) ? api.Constant_user_default_nickname : data[item].nickname
              data[item].categoryName = util.isEmpty(data[item].categoryName) ?
                api.Constant_content__default_category : data[item].categoryName
            }
            app.globalData.sortedContentList = data
            this.setData({
              contentList: data
            })
            wx.hideLoading();
          }
        })
    // } else {
    //   console.log("is already cache the sortedContentList")
    //   this.setData({
    //     contentList: app.globalData.sortedContentList
    //   })
    // }
  },


  toArticleDetail: function(event) {
    event.currentTarget.dataset.content.sorted = categoryId
    var content = JSON.stringify(event.currentTarget.dataset.content)
    // console.log(content)
    wx.navigateTo({
      url: '/pages/article/detail/detail?content=' + encodeURIComponent(content),
    })
    },



})
