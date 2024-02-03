<template>
  <div>
    <!--块类统计-->
    <el-row :gutter="20">
      <el-col :span="6">
        <el-card class="box-card">
          <h3>工人总数</h3>
          <div>
            <i class="el-icon-s-shop" style="color: purple"></i>
            <span>480</span>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="box-card">
          <h3>车间总数</h3>
          <div>
            <i class="el-icon-user-solid" style="color: green"></i>
            <span>360</span>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="box-card">
          <h3>异常总数</h3>
          <div>
            <i class="el-icon-s-flag" style="color: red"></i>
            <span>32</span>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="box-card">
          <h3>未处理事件</h3>
          <div>
            <i class="el-icon-star-on" style="color: blue"></i>
            <span>8</span>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <!--acharts统计图-->
    <el-row :gutter="20">
      <el-col :span="16">
        <el-card class="box-card">
          <echarts1 />
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card class="box-card">
          <echarts2 />
        </el-card>
        <el-card class="box-card">
          <echarts3 />
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import echarts1 from '@/ECharts/echarts1'
import echarts2 from '@/ECharts/echarts2'
import echarts3 from '@/ECharts/echarts3'

export default {
  name: 'home',
  components: {
    echarts1,
    echarts2,
    echarts3
  }
}
</script>

<style scoped>
.el-card{
  margin-bottom: 20px;
  text-align: center;
}

</style>