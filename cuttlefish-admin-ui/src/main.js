// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

// 引入element UI
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

// 引入icon
import './assets/icon/iconfont.css'

import axios from 'axios';
Vue.prototype.$axios = axios;

Vue.config.productionTip = false

// 使用element UI
Vue.use(ElementUI);




import { Picker } from 'vant';
Vue.use(Picker);

// 过滤器
import * as custom from './utils/util'
Object.keys(custom).forEach(key => {
    Vue.filter(key, custom[key])
})


/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>',
  data: {
    // 空的实例放到根组件下，所有的子组件都能调用
    Bus: new Vue()
}
})
