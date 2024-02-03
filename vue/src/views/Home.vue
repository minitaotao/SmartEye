<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="14">
        <div class="grid-content bg-purple">
          <!-- 首页user信息 -->
          <el-card shadow= 'hover'>
            <div class="userCard">
              <el-avatar :size="150" :src=imgUrl></el-avatar>
              <div class="userInfo">
                <p class="important-font">{{user.username}}</p>
                <p class="secondary-font">{{user.role}}</p>
              </div>
            </div>
            <div class="login-info">
              <p>上次登录时间: 2023/08/30 18:16</p>
            </div>
          </el-card>
          <!-- 首页表格 -->
          <el-card shadow= 'hover' class="tableInfo" >
            <div slot="header">
              <span class="important-font">近期查看异常记录</span>
            </div>
            <div>
              <el-table :data="tableData" stripe border height="450px" style="width: 100%">
                <el-table-column
                    prop="time"
                    label="时间"
                    width="185">
                </el-table-column>
                <el-table-column
                    prop="place"
                    label="地点"
                    width="170">
                </el-table-column>
                <el-table-column
                    prop="kind"
                    label="预测异常"
                    width="170">
                </el-table-column>
                <el-table-column prop="state" label="处理状态" width="170">
                  <template slot-scope="scope">
                    <span :class="{'red-text': scope.row.state === '未处理'}">{{ scope.row.state }}</span>
                  </template>
                </el-table-column>
                <el-table-column>
                  <template slot-scope="scope" >
                    <el-button type="success" >立即处理</el-button>
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
            </div>
          </el-card>
        </div>
      </el-col>
      <el-col :span="5">
        <el-card class="box-card">
          <h2>工人总数</h2>
          <div>
            <i class="el-icon-s-custom" style="color: purple"></i>
            <span style="font-size: 24px;">480</span>
          </div>
        </el-card>
      </el-col>
      <el-col :span="5">
        <el-card class="box-card">
          <h2>车间总数</h2>
          <div>
            <i class="el-icon-s-home" style="color: green"></i>
            <span style="font-size: 24px;">360</span>
          </div>
        </el-card>
      </el-col>
      <el-col :span="5">
        <el-card class="box-card">
          <h2>异常总数</h2>
          <div>
            <i class="el-icon-warning" style="color: red"></i>
            <span style="font-size: 24px;">32</span>
          </div>
        </el-card>
      </el-col>
      <el-col :span="5">
        <el-card class="box-card">
          <h2>未处理事件</h2>
          <div>
            <i class="el-icon-question" style="color: blue"></i>
            <span style="font-size: 24px;">8</span>
          </div>
        </el-card>
      </el-col>
      <el-col :span="10">
        <el-card class="box-card" style="height: 150px">
          <h2>7日内新增工人</h2>
          <div>
            <i class="el-icon-s-custom" style="color: blue"></i>
            <span style="font-size: 36px;">24</span>
          </div>
        </el-card>
      </el-col>
      <el-col :span="10">
        <el-card class="box-card" style="height: 150px">
          <h2>3日内机器故障</h2>
          <div>
            <i class="el-icon-error" style="color: blue"></i>
            <span style="font-size: 36px;">38</span>
          </div>
        </el-card>
      </el-col>
      <el-col :span="10">
        <el-card class="box-card" style="height: 150px">
          <h2>工厂运行状态</h2>
          <div>
            <i class="el-icon-loading" style="color: blue"></i>
            <span style="font-size: 36px;">良好</span>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import * as echarts from 'echarts';

export default {
  name: "Index",
  data() {
    return {
      imgUrl: require('../assets/001.jpg'),
      value: new Date(),
      tableData: [],
      total:0,
      pageNum:1,
      pageSize:5,
      state:"",
      user: {},
    };
  },
  created() {
    if (this.$route.query.user) {
      this.user = JSON.parse(this.$route.query.user);
    }
    this.load()
  },
  methods:{
    load(){
      this.request.get("http://localhost:9090/abnormal/page",{
        params:{
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          state: this.state,
        }
      }).then(res=>{
        console.log(res)
        this.tableData=res.data
        this.total=res.total})
    },
  }
}


</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="less" scoped>
.el-card__body {
  padding: 10px;
}
.userCard{
  height: 180px;
  display: flex;
  border-bottom: 2px solid #DCDFE6;
  border-radius: 2px;
}
.userInfo{
  width: auto;
  padding: 6% 0 0 6%;
}
.important-font{
  font-weight: 900;
  font-size: 25px;
}
.secondary-font{
  color: #909399;
}
.login-info{
  height: 40px;
  text-align: left;
  color: #909399;
}
.tableInfo{
  margin: 20px 0 0 0;
}
.OrderCard{
  margin: 0 0 30px 30px;
  border: #DCDFE6 1px solid;
  border-radius: 10px;
  i{
    width: 30%;
    line-height: 120px;
    font-size: 30px;
    color:#fff
  }
  div{
    width: 300px;
  }
}
.el-card{
  border: none;
}
.num{
  display: flex;
  flex-wrap: wrap;
}
.graph{
  margin: 15px 0 0 0;
}
.el-calendar{
  height: 265px ;
}
.red-text {
  color: red;
}
</style>