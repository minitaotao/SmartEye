<template>
  <div>
    <div style="padding: 10px 0">
      <el-input style="width:200px" placeholder=请输入员工编号 suffix-icon="el-icon-search" v-model="人员编号"></el-input>
      <el-button calss="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button calss="warning"  @click="reset">重置</el-button>
    </div>
    <el-table :data="tableData" border stripe :header-cell-class-name="headerBg">
      <el-table-column prop="人员编号" label="员工编号" width="80" ></el-table-column>
      <el-table-column prop="人员姓名" label="员工姓名" ></el-table-column>
      <el-table-column prop="违规操作频率" label="违规操作频率"></el-table-column>
      <el-table-column prop="最近一次违规操作" label="最近一次违规操作"></el-table-column>
      <el-table-column prop="违规操作时间" label="最近违规时间" width="160" ></el-table-column>
      <el-table-column prop="考核情况" label="考核情况"></el-table-column>
      <el-table-column prop="所属车间" label="所属车间"></el-table-column>
      <el-table-column >
        <template slot-scope="scope">
          <el-button type="success" @click="dialogVisible = true">查看违规操作</el-button>
        </template>
      </el-table-column>
      <el-table-column>
        <template slot-scope="scope">
          <el-button type="success" @click="handleEdit(scope.row)">发送提醒短信</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div style="padding:10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[2, 5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>
    <el-dialog title="短信提醒" :visible.sync="dialogFormVisible" >
      <el-form label-width="120px">
        <el-form-item label="员工编号" >
          <el-input  v-model="form.人员编号"></el-input>
        </el-form-item>
        <el-form-item label="员工姓名" >
          <el-input  v-model="form.人员姓名"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="warning" @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定 发 送</el-button>
      </div>
    </el-dialog>
    <el-dialog
        title="异常视频"
        :visible.sync="dialogVisible"
        width="90%"
        :before-close="handleClose">
      <div class="videoplayer1">
        <video-player  class="video-playervjs-custom-skin1" ref="videoPlayer1" :options="playerOption" :playsinline="true" @play="onPlayerPlay()" @pause="onPlayerPause()" @timeupdate="onPlayerChange($event)"></video-player>
        <video-player  class="video-playervjs-custom-skin1" ref="videoPlayer1" :options="playerOption" :playsinline="true" @play="onPlayerPlay()" @pause="onPlayerPause()" @timeupdate="onPlayerChange($event)"></video-player>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import {videoPlayer} from "vue-video-player";
import "videojs-flash";
export default ({
  name: "User",
  data(){
    return{
      tableData:[],
      options: [{
        value: '发送至手机',
        label: '发送至手机'
      }, {
        value: '未处理',
        label: '未处理'
      }],
      total:0,
      pageNum:1,
      人员编号:"",
      currentTime: 0,
      speech:null,
      dialog: false,
      speechText: '',
      lastPlaybackRate: "",
      videoUrl: "",
      innerWidth:100,
      pageSize:5,
      state:"",
      bianhao:"",
      form:{},
      dialogVisible : false,
      dialogFormVisible:false,
      multipleSelection:[],
      headerBg:'headerBg',
      playerOption: {
        sources: [
          {
            type: "video/mp4",
            src: require("@/assets/011.mp4"),
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
    }
  },
  components: {
    videoPlayer
  },
  mounted() {
    this.speechInit()
  },
  created() {
    this.load()
  },
  methods:{
    load(){
      this.request.get("http://localhost:9090/worker/page",{
        params:{
          人员编号:this.人员编号,
          pageNum: this.pageNum,
          pageSize: this.pageSize,
        }
      }).then(res=>{
        console.log(res)
        this.tableData=res.data
        this.total=res.total})
      // fetch( "http://localhost:9090/user/page?pageNum="+this.pageNum+"&pageSize="+this.pageSize+"&username="+this.username).then(res=>res.json()).then(res => {
      //   console.log(res)
      //   this.tableData=res.data
      //   this.total=res.total
      // })
    },
    save(){
      this.request.post("http://localhost:9090/worker",this.form).then(res=>{
        if(res){
          this.$message.success("已向员工手机发送提醒短信")
          this.dialogFormVisible=false
          this.load()
        }
        else{
          this.$message.error("处理失败")
          this.dialogFormVisible=false
        }
      })
    },
    del(id){
      this.request.delete("http://localhost:9090/user/"+id).then(res=>{
        if(res){
          this.$message.success("删除成功")
          this.load()
        }
        else{
          this.$message.error("删除失败")
        }
      })
    },
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
      that1.player.pause();
    },onPlayerChange(player) {
      if (this.videoUrl != this.playerOption.sources[0].src) {
        let that1 = this.$refs.videoPlayer1;
        that1.player.currentTime(0);
        that1.player.playbackRate(1.0);
        this.videoUrl = this.playerOption.sources[0].src;
      } else {
        this.currentTime = player.cache_.currentTime;
        // 调整播放速率
        if (this.lastPlaybackRate != player.cache_.lastPlaybackRate) {
          let that1 = this.$refs.videoPlayer1;
          that1.player.playbackRate(player.cache_.lastPlaybackRate);
          this.lastPlaybackRate = player.cache_.lastPlaybackRate;
        }
      }},
    handleEdit(row){
      this.form=row
      this.dialogFormVisible=true
    },
    handleAdd(){
      this.dialogFormVisible=true
      this.form={}
    },
    reset() {
      this.u = ""
      this.load()
    },
    handleSizeChange(pageSize)
    {
      console.log(pageSize)
      this.pageSize=pageSize
      this.load()
    },
    handleCurrentChange(pageNum)
    {
      console.log(pageNum)
      this.pageNum=pageNum
      this.load()
    }
  }
})
</script>
<style>
.headerBg{
  background-color: #ccc !important;
}
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
.red-text {
  color: red;
}
</style>