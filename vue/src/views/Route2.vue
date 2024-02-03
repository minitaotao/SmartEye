<template>

  <div class="videop" >
    <div class="text1" >
      <el-upload
          class="upload-demo"
          action="http://localhost:9090/upload"
          :on-preview="handlePreview"
          :on-remove="handleRemove"
          :before-remove="beforeRemove"
          multiple
          :limit="3"
          :on-exceed="handleExceed"
          :file-list="fileList">
        <el-button size="small" type="primary">点击上传</el-button>
        <div slot="tip" class="el-upload__tip">只能上传MP4文件</div>
      </el-upload>
      <p class="p1">请上传待检测视频</p>
      <el-divider></el-divider>
      <el-button type="primary" @click="dialogVisible = true">开始检测</el-button>
      <el-button type="success" @click="speakTtsSpeech">语音消息提醒</el-button>
      <el-dialog :visible.sync="dialog" title="异常行为提醒" @close="dialog = false" center>
        <p class="my-paragraph">{{ speechText }}</p>
        <div slot="footer" class="dialog-footer">
          <el-button type="warning" @click="dialog = false">稍后处理</el-button>
          <el-button type="primary" @click="handle">点击前往处理详情界面</el-button>
        </div>
      </el-dialog>
      <el-dialog
          title="检测画面对比"
          :visible.sync="dialogVisible"
          width="85%"
          :before-close="handleClose">
        <div class="videoplayer1">
          <!-- 视频 1 -->
          <video-player  class="video-playervjs-custom-skin1" ref="videoPlayer1" :options="playerOption" :playsinline="true" @play="onPlayerPlay()" @pause="onPlayerPause()" @timeupdate="onPlayerChange($event)"></video-player>
          <!-- 视频 2 -->
          <video-player  class="video-playervjs-custom-skin2" ref="videoPlayer2" :options="playerOption2" :playsinline="true" @play="onPlayerPlay()" @pause="onPlayerPause()" @timeupdate="onPlayerChange($event)"></video-player>
        </div>
      </el-dialog>
    </div>

  </div>

</template>

<script>

import { videoPlayer } from "vue-video-player";
import "videojs-flash";
import Speech from "speak-tts";

export default {
  data() {
    return{
      imageUrl: '@/assets/fall.jpg',
      dialogImageUrl: '',
      dialogVisible: false,
      currentTime: 0,
      speech:null,
      dialog: false,
      speechText: '',
      lastPlaybackRate: "",
      videoUrl: "",
      innerWidth:100,
      playerOption: {
        sources: [
          {
            type: "video/mp4",
            src: require("@/assets/010.mp4"),
          },
        ],
        poster:'',
        autoplay: false, // 如果true，浏览器准备好时开始回放
        muted: true, // 默认情况下将会消除任何音频
        loop: true, // 是否一结束就重新开始播放
        language: "zh-CN",
        playbackRates: [0.7, 1.0, 1.5, 2.0], // 播放速度
        controls: true,
        notSupportedMessage: "此视频暂无法播放，请稍后再试", // 允许覆盖Video.js无法播放媒体源时显示的默认信息
        controlBar: {
          timeDivider: true,
          durationDisplay: true,
          remainingTimeDisplay: false,
          fullscreenToggle: true, // 全屏按钮
        },
      },
      playerOption2: {
        sources: [
          {
            type: "video/mp4",
            src: require("@/assets/008.mp4"),
          },
        ],
        autoplay: false, // 如果true，浏览器准备好时开始回放
        muted: true, // 默认情况下将会消除任何音频
        loop: true, // 是否一结束就重新开始播放
        language: "zh-CN",
        playbackRates: [0.7, 1.0, 1.5, 2.0], // 播放速度
        controls: true,
        notSupportedMessage: "此视频暂无法播放，请稍后再试", // 允许覆盖Video.js无法播放媒体源时显示的默认信息
        controlBar: {
          timeDivider: true,
          durationDisplay: true,
          remainingTimeDisplay: false,
          fullscreenToggle: true, // 全屏按钮
        },
      }

    }
  },
  components: {
    videoPlayer
  },
  mounted() {
    this.speechInit()
  },
  methods: {
    // 点击播放
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePictureCardPreview(file) {
        this.dialogImageUrl = file.url;
        this.dialogVisible = true;
      },
    onPlayerPlay() {
      var that1 = this.$refs.videoPlayer1;
      var that2 = this.$refs.videoPlayer2;
      that1.player.play();
      that2.player.play();
      // 调整进度条
      that1.player.currentTime(this.currentTime);
      that2.player.currentTime(this.currentTime);
    },
    // 点击暂停
    onPlayerPause() {
      var that1 = this.$refs.videoPlayer1;
      var that2 = this.$refs.videoPlayer2;
      that1.player.pause();
      that2.player.pause();
    },
    handle(){
      this.$router.push('/route0')
    },
    speechInit() {
      this.speech = new Speech()
      this.speech.setLanguage('zh-CN')
      this.speech.init().then(() => {
      })
    },
    speakTtsSpeech() {
      let msg = '九号车间发生异常行为，系统检测为打瞌睡'
      this.speechText = msg; // 将语音播报内容保存到数据变量 speechText
      this.dialog= true;//打开弹窗
      this.speech.speak({text: msg}).then(() => {
        console.log('播报成功')
      })
    },
    // 当前播放位置发生变化时触发
    onPlayerChange(player) {
      if (this.videoUrl != this.playerOption.sources[0].src) {
        let that1 = this.$refs.videoPlayer1;
        let that2 = this.$refs.videoPlayer2;
        that1.player.currentTime(0);
        that2.player.currentTime(0);
        that1.player.playbackRate(1.0);
        that2.player.playbackRate(1.0);
        this.videoUrl = this.playerOption.sources[0].src;
      } else {
        this.currentTime = player.cache_.currentTime;
        // 调整播放速率
        if (this.lastPlaybackRate != player.cache_.lastPlaybackRate) {
          let that1 = this.$refs.videoPlayer1;
          let that2 = this.$refs.videoPlayer2;
          that1.player.playbackRate(player.cache_.lastPlaybackRate);
          that2.player.playbackRate(player.cache_.lastPlaybackRate);
          this.lastPlaybackRate = player.cache_.lastPlaybackRate;
        }
      }
    }
  }
}
</script>

<style>
.my-paragraph {
  font-size: 36px;
  color: #FC466B;
  text-align: center;
  margin-bottom: 20px;
}
.videop{
  display: block;
}
.video-playervjs-custom-skin1{
  box-flex: 1;/*灵活度*/
  -webkit-box-flex: 1; /* Safari and Chrome */
  -moz-box-flex: 1; /* Firefox */
  -webkit-flex: 1;
  -ms-flex: 1;
  flex: 1;

}
.video-playervjs-custom-skin2{
  box-flex: 1;/*灵活度*/
  -webkit-box-flex: 1; /* Safari and Chrome */
  -moz-box-flex: 1; /* Firefox */
  -webkit-flex: 1;
  -ms-flex: 1;
  flex: 1;

}
.videop{
  width: 1200px;
  height: 300px;
  margin: 10px;
}
.vjs_video_3-dimensions{
  width: 600px;
  height: 400px;
}
.vjs_video_433-dimensions{
  width: 600px;
  height: 400px;
}
.p1{
  font-size: 30px;
}
.text1{
  text-align: center;
}
.videoplayer1{
  display: flex;
}
</style>
