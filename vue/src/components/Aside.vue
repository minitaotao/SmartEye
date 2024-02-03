<template>
  <el-menu :default-openeds="['1', '3']" style="min-height: 100%;,overflow-x:hidden;"
           background-color="rgb(45,65,86)"
           text-color="#fff"
           active-text-color="#ffd04b"
           :collapse-transition="false"
           :collapse="isCollapse"
           router="true"
  >
    <div style="height:60px;line-height:60px;text-align:center">
      <img src="../assets/logo1.png" alt="" style="width:20px;position:relative;top:5px;margin-right: 5px" >
      <b style="color:white" v-show="logoTextShow">智能工厂管理系统</b>
    </div>
    <el-menu-item index=home >
        <i class="el-icon-menu"></i>
        <sapn slot="title" v-show="logoTextShow">总体看板</sapn>
    </el-menu-item>
    <el-menu-item index=route1>
        <i class="el-icon-s-custom"></i>
        <sapn slot="title" v-show="logoTextShow">车间概况</sapn>
    </el-menu-item>
    <el-menu-item index=route2>
        <i class="el-icon-video-camera-solid"></i>
        <sapn slot="title" v-show="logoTextShow">视频分析</sapn>
    </el-menu-item>
    <el-menu-item index=route0>
      <i class="el-icon-s-claim"></i>
      <sapn slot="title" v-show="logoTextShow">处理详情</sapn>
    </el-menu-item>
    <el-menu-item index=route4>
      <i class="el-icon-s-custom"></i>
      <sapn slot="title" v-show="logoTextShow">员工考核</sapn>
    </el-menu-item>
    <el-menu-item index=route3>
      <template slot="title">
        <i class="el-icon-s-data"></i>
        <sapn slot="title" v-show="logoTextShow">数据汇总</sapn>
      </template>
    </el-menu-item>
  </el-menu>
</template>
<script>
export default ({
  name: "Aside",
  props:{
    isCollapse:Function,
    logoTextShow:Boolean
  }
})
</script>
<style>

</style>