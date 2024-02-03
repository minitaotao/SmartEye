<template>
  <div style="width: auto;height: 185px" id="echarts3">
  </div>
</template>

<script>
import echarts from 'echarts'
export default {
  name: 'echarts3',
  data () {
    return {
      // option配置
      option: {
        legend: {
          data: ['车间发生异常占比', '车间机器故障占比']
        },
        radar: {
          // shape: 'circle',
          indicator: [
            { name: '车间1', max: 6500 },
            { name: '车间2', max: 16000 },
            { name: '车间3', max: 30000 },
            { name: '车间4', max: 38000 },
            { name: '车间5', max: 52000 },
            { name: '车间6', max: 25000 }
          ]
        },
        series: [
          {
            name: 'Budget vs spending',
            type: 'radar',
            data: [
              {
                value: [4200, 3000, 20000, 35000, 50000, 18000],
                name: '车间发生异常占比'
              },
              {
                value: [5000, 14000, 28000, 26000, 42000, 21000],
                name: '车间机器故障占比'
              }
            ]
          }
        ]
      }
    }
  },
  mounted () {
    this.echartsInit()
  },
  methods: {
    echartsInit () {
      // 在生命周期中挂载
      echarts.init(document.getElementById('echarts3')).setOption(this.option)
    }
  }
}
</script>

<style scoped>

</style>