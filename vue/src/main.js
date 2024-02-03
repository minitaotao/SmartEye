import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './assets/gloable.css'
import request from "@/utlis/request";
import Video from 'video.js';
import 'video.js/dist/video-js.css';
Vue.prototype.$video = Video;
import hls from 'videojs-contrib-hls';
import * as voicePromptFun from 'D:\\个人桌面\\智能工厂管理系统\\vue\\src\\utlis\\voicePrompt.js'
Vue.prototype.voicePrompt = voicePromptFun.voicePrompt
Vue.use(hls);
import echarts from 'echarts'
Vue.prototype.$echarts = echarts

Vue.config.productionTip = false

Vue.use(ElementUI);
Vue.prototype.request=request
new Vue({
  router,
  render: h => h(App)
}).$mount('#app')

