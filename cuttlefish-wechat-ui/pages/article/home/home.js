const app = getApp()
var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');

Component({
  options: {
    addGlobalClass: true,
  },
  data: {
    // StatusBar: app.globalData.StatusBar,
    // CustomBar: app.globalData.CustomBar,
    isCard: 1,
    mask: false,//抢单遮罩

    swiperList: [{
      id: 0,
      type: 'image',
      url: 'https://ossweb-img.qq.com/images/lol/web201310/skin/big84000.jpg'
    }, {
      id: 1,
      type: 'image',
      url: 'https://ossweb-img.qq.com/images/lol/web201310/skin/big84001.jpg',
    }, {
      id: 2,
      type: 'image',
      url: 'https://ossweb-img.qq.com/images/lol/web201310/skin/big39000.jpg'
    }, {
      id: 3,
      type: 'image',
      url: 'https://ossweb-img.qq.com/images/lol/web201310/skin/big10001.jpg'
    },
    ],

    contentList: []
    

  },


  attached: function () {
    var authorId = ''
    var authorId = ''
    if (wx.getStorageSync("userInfo")) {
      app.globalData.userinfo = wx.getStorageSync("userInfo")
      authorId = wx.getStorageSync("userInfo").id
    }
    this.request(authorId)
  },
  
  methods: {
    show: function () {
      console.log("show........")
    },

    isCard(e) {
      this.setData({
        isCard: e.detail.value
      })
      // 刷新数据
      app.globalData.contentList = null
      var authorId = ''
      if (wx.getStorageSync("userInfo")) {
        authorId = wx.getStorageSync("userInfo").id
      }
      this.request(authorId)
    },
    toArticleDetail(event) {
      var content = JSON.stringify(event.currentTarget.dataset.content)
      console.log(content)
      wx.navigateTo({
        // 微信小程序带参数，数组，对象跳转页面传递，数据量太大导致问题解决encodeURIComponent
        url: '/pages/article/detail/detail?content=' + encodeURIComponent(content),
      })
    },
    request: function (authorId) {
      if (!app.globalData.contentList) {
        wx.showLoading({
          title: '加载中',
          mask: true,
        })
        util.request(api.ContentList, { 'authorId': authorId},
          'GET').then(res => {
            if (res.code == 200) {
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
              app.globalData.contentList = data
              this.setData({
                contentList: data
              })
              wx.hideLoading();
            }
          })
      } else {
        console.log("is already cache the contentList")
        this.setData({
          contentList: app.globalData.contentList
        })
      } 
    },


  }



})
