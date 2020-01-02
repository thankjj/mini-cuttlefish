import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/views/login'
import Index from '@/views/index'

import ContentList from '@/views/content/ContentList'
import UserList from '@/views/user/UserList'
import FeedbackList from '@/views/user/FeedbackList'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: '登陆',
      component: Login
    },
    {
      path: '/content',
      name: '内容列表',
      component: Index,
      iconCls: 'el-icon-tickets',
      children: [{
        path: '/content/ContentList',
        name: '内容列表',
        component: ContentList,
        meta: {
          requireAuth: true
        }
      }]
    },
    {
      path: '/user',
      name: '用户列表',
      component: Index,
      iconCls: 'el-icon-tickets',
      children: [{
        path: '/user/UserList',
        name: '用户列表',
        component: UserList,
        meta: {
          requireAuth: true
        }
      },{
        path: '/user/FeedbackList',
        name: '用户反馈列表',
        component: FeedbackList,
        meta: {
          requireAuth: true
        }
      }]
    }





  ]
})
