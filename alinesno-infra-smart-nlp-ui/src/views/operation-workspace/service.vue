<template>
  <div>
    <el-row class="acp-dashboard-panel" :gutter="20">

      <el-col :span="19">

        <div class="grid-content">
          <div class="panel-header">
            <div class="header-title"><i class="fa-solid fa-user-nurse"></i> 服务类别</div>
          </div>
          <div class="panel-body acp-height-auto" style="padding-left: 5px;padding-right: 5px;">
            <div class="box-header">
              <el-row>
                <el-col :span="24" style="column-count: 2;">

                  <div v-for="(item, index) in services" :key="index" class="item-box">
                    <img :src="item.imgSrc" :alt="item.altText">
                    <div class="content">
                      <div class="item-title">{{ item.title }}</div>
                      <div class="item-desc">{{ item.desc }}</div>
                    </div>
                  </div>

                </el-col>
              </el-row>
            </div>
          </div>
        </div>

      </el-col>

      <el-col :span="5">
        <div class="grid-content">
          <div class="panel-header">
            <div class="header-title"><i class="fa-solid fa-user-nurse"></i> 词库管理统计</div>
          </div>
          <div class="panel-body acp-height-auto">
            <ul class="panel-item-text">
              <li style="width:calc(50% - 20px);margin:10px;padding:10px;" v-for="item in sensitiveWords"
                :key="item.id">
                <div class="item-health-box">
                  <div class="item-health-title">{{ item.title }}</div>
                  <div class="item-health-count">{{ item.count }}</div>
                </div>
              </li>
            </ul>
          </div>
        </div>
      </el-col>

    </el-row>
  </div>
</template>

<script setup>

// import * as echarts from "echarts";

/// 声明定义一下echart
// const echart = echarts;

const sensitiveWords = ref([
  { id: '1', title: '政治敏感词', count: 45 },
  { id: '2', title: '色情敏感词', count: 145 },
  { id: '3', title: '暴力敏感词', count: 65 },
  { id: '5', title: '恐怖主义敏感词', count: 45 },
  { id: '6', title: '涉暴恐敏感词', count: 45 },
]);

const services = [
  {
    imgSrc: "https://img.alicdn.com/imgextra/i2/O1CN01GgGQxH1aMmB6RjJTh_!!6000000003316-0-tps-550-550.jpg",
    altText: "基础文本图片",
    title: "基础文本",
    desc: "提供词法、句法、篇章分析等API中的通用能力，包含分词、词性标注、命名实体识别、文本纠错等。"
  },
  {
    imgSrc: "https://img.alicdn.com/imgextra/i1/O1CN01pdJXYx24mMmIHXj9L_!!6000000007433-0-tps-551-550.jpg",
    altText: "医疗行业图片",
    title: "医疗行业",
    desc: "提供医疗行业所需的场景能力，针对医疗文本支持对应的分析能力，包含医学实体识别、病历查重、诊断归一等。"
  },
  {
    imgSrc: "https://img.alicdn.com/imgextra/i3/O1CN01UFYmby1oAK1hvJdt2_!!6000000005184-0-tps-551-550.jpg",
    altText: "电商行业图片",
    title: "电商行业",
    desc: "提供电商行业所需的多个场景能力，包含智能商品发布、商品评价解析、电商客服对话抽取等。"
  },
  {
    imgSrc: "https://img.alicdn.com/imgextra/i2/O1CN01DONpDU1S5MBYYNQ0X_!!6000000002195-0-tps-551-550.jpg",
    altText: "通用行业图片",
    title: "通用行业",
    desc: "提供多个行业所需的通用场景能力，包含招中标解析、简历抽取、裁判文书解析等。"
  }
];

// const resources = ref([
//   {
//     icon: "fas fa-microchip",
//     title: "请求统计",
//     total: "4",
//     usage: "0.64",
//     usagePre: "12%",
//   },
//   {
//     icon: "fas fa-memory",
//     title: "文本统计",
//     total: "7.68",
//     usage: "4.81",
//     usagePre: "12%",
//   },
//   {
//     icon: "fas fa-hdd",
//     title: "月统计",
//     total: "21.57",
//     usage: "207.71",
//     usagePre: "12%",
//   },
//   {
//     icon: "fab fa-docker",
//     title: "异常统计",
//     total: "220",
//     usage: "28",
//     usagePre: "12%",
//   },
// ]);


// onMounted(() => {
// });

// onUnmounted(() => {
//   echart.dispose;
// });


</script>

<style lang="scss" scoped>
.item-health-title {
  margin-bottom: 5px !important;
}

.item-health-count {
  margin-bottom: 5px;
}


.item-box {
  ul {
    list-style: none;
    padding: 0px;
    margin: 0px;

    .server-desc {
      font-size: 1.6rem;
      color: #3b5998;
    }

    .active {
      background: #3b5998 !important;
      color: #fff !important;

      .server-desc {
        color: #fff !important;
      }
    }

    li.item-box-info {
      padding: 8px;
      float: left;
      width: 100%;
      background: #f9fbfd;
      border-radius: 5px;
      color: #222;
      margin-bottom: 5px;

      .item-status {
        float: left;
      }

      .status-info {
        float: left;
        margin-left: 20px;

        .item-text {
          margin-bottom: 5px;
          font-size: 12px;
          font-weight: 600;
          line-height: 1.67;
        }

        .item-num {
          height: 22px;
          line-height: 22px;
          font-size: 18px;
          font-weight: 600;

          .total-num {
            font-size: 14px;
          }
        }
      }
    }
  }
}

.sidecard-bar {
  padding: 10px;
}

.item-list {
  .item-icon {
    img {
      width: 94px;
      height: 20px;
    }
  }
}

.box-header {
  .title {
    position: relative;
    height: 20px;
    margin-bottom: 20px;
    font-size: 14px;
    font-weight: 600;
    line-height: 1.43;
    zoom: 1;
  }

  .box-body {
    // padding: 20px;
    float: left;
    width: 100%;
    border-radius: 5px;
    background: #f9fbfd;

    ul {
      list-style: none;
      padding: 0px;
      margin: 0px;

      li.item-list {
        float: left;
        width: calc(33% - 10px);
        margin-right: 10px;
        background: #f9fbfd;
        padding: 10px;
        border-radius: 5px;
        margin-bottom: 10px;

        .item-icon {
          float: left;
        }

        .item-label {
          float: right;
        }
      }
    }

    .sidecard-pie {
      float: left;
      margin-left: 30px;
    }

    .sidecard {
      float: left;
      display: flex;
      flex-direction: column;
      justify-content: center;
      margin-left: 45px;
      margin-top: 40px;

      .box-title {
        font-size: 16px;
        font-weight: 600;
        line-height: 1.43;
        margin-bottom: 5px;
      }
    }
  }
}

.item-box {

  display: flex;
  align-items: flex-start;
  padding: 10px;
  border: 1px solid #e8e8e8 ;
  margin-bottom: 10px;
  border-radius: 3px;

  img {
    width: 100px;
  }

  .content{
    padding: 10px 0;
    margin-left:10px;
    display: flex;
    flex-wrap: nowrap;
    flex-direction: column;
    gap: 8px;

    .item-title {
      font-size: 14px;
      font-weight: bold;
    }

    .item-desc {
      font-size: 13px;
    }
  }
}
</style>