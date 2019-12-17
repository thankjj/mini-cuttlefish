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
    content: '1',
    contentCategoryList: [],
    list: [{
      title: '内涵段子',
      img: 'https://image.weilanwl.com/color2.0/plugin/sylb2244.jpg',
      url: '/indexes/indexes'
    },
    {
      title: '三行情书',
      img: 'https://image.weilanwl.com/color2.0/plugin/wdh2236.jpg',
      url: '/animation/animation'
    },
    {
      title: '土味情话',
      img: 'https://image.weilanwl.com/color2.0/plugin/qpct2148.jpg',
      url: '/drawer/drawer'
    },
    {
      title: '心灵毒汤',
      img: 'https://image.weilanwl.com/color2.0/plugin/qpczdh2307.jpg',
      url: '/verticalnav/verticalnav'
    }
    ]

  },

  attached: function () {
    if (!app.globalData.contentCategoryList){
      util.request(api.ContentCategoryList, null, 'POST').then(res => {
        if (res.code == 200) {
          app.globalData.contentCategoryList = res.data
          this.setData({
            contentCategoryList: res.data
          })
        }
      })
    }else{
      console.log("is already cache the contentCategoryList")
      this.setData({
        contentCategoryList: app.globalData.contentCategoryList
      })
    } 
    
  },


  methods: {
    toCategoryArticleList(event) {
      var category = JSON.stringify(event.currentTarget.dataset.category)
      console.log(category)
      wx.navigateTo({
        url: '/pages/component/article/article?category=' + encodeURIComponent(category),
      })
    },
    


  }
  



})
