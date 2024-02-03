<template>
  <div class="cell">
    <div class="cell-player">
      <div :class="cellClass(i)" v-for="i in cellCount" :key="i">
        <video :src="videoInfo[i].url" controls autoplay loop class="responsive-video" @ended="restartVideo(i)"></video>
        <p>{{ videoInfo[i].name }}</p>
      </div>
    </div>
    <div class="cell-tool">
      <div class="bk-button-group">
        <el-button @click="cellCount = 1" size="small">1</el-button>
        <el-button @click="cellCount = 4" size="small">4</el-button>
        <el-button @click="cellCount = 9" size="small">9</el-button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'dashboard',
  data() {
    return {
      videoInfo: [
        { url: require('@/assets/004.mp4'), index: 0, name: "车间0" },
        { url: require('@/assets/010.mp4'), index: 1, name: "车间1" },
        { url: require('@/assets/005.mp4'), index: 2, name: "车间2" },
        { url: require('@/assets/004.mp4'), index: 3, name: "车间3" },
        { url: require('@/assets/007.mp4'), index: 10, name: "车间4" },
      ],
      cellCount: 4,
      showDialog:false
    };
  },
  computed: {
    cellClass() {
      return function (index) {
        switch (this.cellCount) {
          case 1:
            alert("切换场景为运输车间")
            return ['cell-player-1'];
          case 4:
            return ['cell-player-4'];
          default:
            break;
        }
      };
    },
  },
  methods: {
    restartVideo(index) {
      const videoElement = document.querySelectorAll('video')[index];
      videoElement.currentTime = 0;
      videoElement.play();
    },
  },
};
</script>

<style>
.cell-tool {
  height: 40px;
  line-height: 30px;
  padding: 0 7px;
}
.cell-player {
  flex: 1;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
}
.cell-player-4 {
  width: 50%;
  height: 50% !important;
  box-sizing: border-box;
}

.cell-player-1 {
  width: 100%;
  box-sizing: border-box;
}

.cell-player-6-1 {
  width: 66.66%;
  height: 66.66% !important;
  box-sizing: border-box;
}
.cell-player-6-2 {
  width: 33.33%;
  height: 66.66% !important;
  box-sizing: border-box;
  display: flex;
  flex-direction: column;
}
.cell-player-6-none {
  display: none;
}
.cell-player-6-2-cell {
  width: 100%;
  height: 50% !important;
  box-sizing: border-box;
}
.cell-player-6 {
  width: 33.33%;
  height: 33.33% !important;
  box-sizing: border-box;
}
.cell-player-9 {
  width: 33.33%;
  height: 33.33% !important;
  box-sizing: border-box;
}
.cell-player-16 {
  width: 25%;
  height: 25% !important;
  box-sizing: border-box;
}
.cell {
  display: flex;
  flex-direction: column;
  height: 100%;
}

.responsive-video {
  max-width: 100%;
  max-height: 100%;
}
.custom-font-size {
  font-size: 30px;
}
</style>