<!--<template>-->
<!--  <div>-->
<!--    <video ref="videoElement" autoplay></video>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--export default {-->
<!--  mounted() {-->
<!--    this.initializeCamera();-->
<!--  },-->
<!--  methods: {-->
<!--    async initializeCamera() {-->
<!--      try {-->
<!--        const videoElement = this.$refs.videoElement;-->

<!--        // 使用getUserMedia API获取摄像头的视频流-->
<!--        const stream = await navigator.mediaDevices.getUserMedia({ video: true });-->

<!--        // 将视频流绑定到video元素上-->
<!--        videoElement.srcObject = stream;-->
<!--      } catch (error) {-->
<!--        console.error("无法访问摄像头：", error);-->
<!--      }-->
<!--    }-->
<!--  }-->
<!--};-->
<!--</script>-->

<!--<style>-->
<!--</style>-->

<template>
  <div>
    <el-button type="success" @click="speakTtsSpeech">speak-tts语音播报</el-button>
    <el-dialog :visible="dialogVisible" title="语音播报内容" @close="dialogVisible = false">
      <p>{{ speechText }}</p>
    </el-dialog>
  </div>
</template>

<script>
import Speech from 'speak-tts'

export default {
  data() {
    return {
      speech: null,
      dialogVisible: false,
      speechText: '',
    }
  },
  mounted() {
    this.speechInit()
  },

  methods: {
    speechInit() {
      this.speech = new Speech()
      this.speech.setLanguage('zh-CN')
      this.speech.init().then(() => {
      })
    },
    speakTtsSpeech() {
      let msg = '一号车间发生异常行为，系统检测为摔倒'
      this.speechText = msg; // 将语音播报内容保存到数据变量 speechText
      this.dialogVisible = true; // 打开弹框显示语音播报内容
      this.speech.speak({ text: msg }).then(() => {
        console.log('播报成功')
      })
    },
  }
}
</script>
