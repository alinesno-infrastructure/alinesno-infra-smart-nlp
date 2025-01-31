<template>
  <div>
    <el-row class="acp-dashboard-panel" :gutter="20">
      <el-col class="panel-col" :span="19">
        <div class="grid-content">
          <div class="panel-header">
            <div class="header-title"><i class="fa-solid fa-link"></i> 产品特点</div>
          </div>
          <div class="panel-body" style="height: auto;position: relative;">
              <div class="direct-box">
                <ul style="column-count: 3;">
                  <li class="box-item" style="width:100%" v-for="item in directContent" :key="item">
                    <div class="dire-panel">
                      <div class="panel-title">
                        <i class="dire-panel-icon" :class="item.icon" :alt="item.name" />
                        {{ item.name }}
                      </div>
                      <div class="panel-describe">{{ item.desc }}</div>
                      <!-- <div class="panel-tip">访问链接</div> -->
                    </div>
                  </li>
                </ul>
              </div>
          </div>
        </div>
      </el-col>
      <el-col :span="5">
        <div class="grid-content">
          <div class="panel-header">
            <div class="header-title"><i class="fa-solid fa-link"></i> 应用场景</div>
          </div>
          <div class="panel-body" style="height: auto;position: relative;">
            <div class="acp-app-list">
              <ul>
                <li class="app-items" style="width:100%" v-for="item in nlpBigDataApplications" :key="item">
                  <div class="app-info">
                    <div class="app-item-title">
                        <i :class="item.icon" :alt="item.name" />
                        {{ item.name }}
                    </div>
                    <div class="app-item desc">{{ item.desc }}</div>
                  </div>
                </li>
              </ul>
            </div>
          </div>

        </div>
      </el-col>
    </el-row>

  </div>
</template>

<script setup>

// const directContent = ref([
//   { icon: 'fa-brands fa-slack', name: '智能鉴黄', desc: '识别文本中的色情行为描述、色情资源链接、低俗交友、污秽文爱等涉黄内容' },
//   { icon: 'fa-solid fa-list-check', name: '违禁违规', desc: '识别暴力行为、恐怖描述、赌博、毒品、枪支弹药等违禁文字内容' },
//   { icon: 'fa-solid fa-at', name: '恶意推广', desc: '识别带有售卖意向的软文广告、微信/QQ/联系方式等违规广告，支持包含符号、图标等变异广告' },
//   { icon: 'fa-solid fa-list-check', name: '低俗辱骂', desc: '识别文本中的侮辱谩骂、人身攻击、消极宣泄等不良内容' },
//   { icon: 'fa-solid fa-file-word', name: '低质灌水', desc: '识别网络社区常见的乱码、水帖、刷屏等无意义的灌水信息' },
// ]);

const directContent = ref([
    {
        icon: 'fa-solid fa-sitemap',
        name: '多元服务体系',
        desc: '构建基础文本服务、电商行业能力、通用行业能力、医疗行业能力五大分类，全方位满足各行业及业务场景的多样化需求。'
    },
    {
        icon: 'fa-solid fa-code-branch',
        name: '便捷模型调用',
        desc: '整合原NLP自学习平台预训练模型至电商和通用行业能力，用户借助API即可快速调用行业专属服务能力，加速开发进程。'
    },
    {
        icon: 'fa-solid fa-desktop',
        name: '优化界面交互',
        desc: '采用全新管控台界面设计，极大简化服务管理流程，让用户轻松管理已创建服务，操作更加得心应手。'
    },
    {
        icon: 'fa-solid fa-wrench',
        name: '广泛功能覆盖',
        desc: '提供分词、词性标注、命名实体识别等基础文本处理功能；具有商品评价解析等电商特色功能；在通用行业、电商和医疗文本分析等丰富专业能力。'
    },
    {
        icon: 'fa-solid fa-face-smile',
        name: '精准情感分析',
        desc: '能够敏锐捕捉文本中的情感倾向，准确判断是正面、负面还是中性情感，助力舆情监测和用户反馈分析。'
    },
    {
        icon: 'fa-solid fa-search',
        name: '高效信息抽取',
        desc: '能从大量文本中快速提取关键信息，如实体、事件、关系等，将非结构化文本转化为结构化数据，方便存储、查询和分析。'
    }
]);

const nlpBigDataApplications = ref([
    {
        icon: 'fa-solid fa-database',
        name: '文本数据挖掘',
        desc: '在海量文本数据（如网页内容、文档库等）中挖掘有价值的信息，通过NLP技术提取关键词、实体和关系'
    },
    {
        icon: 'fa-solid fa-chart-line',
        name: '舆情监测与分析',
        desc: '对社交媒体、新闻媒体等大数据源进行实时监测,利用NLP的文本分类和情感分析技术'
    },
    {
        icon: 'fa-solid fa-filter',
        name: '数据清洗和预处理',
        desc: '在大数据处理流程中，文本数据往往是杂乱无章的，NLP技术可以用于清洗文本数据'
    },
    {
        icon: 'fa-solid fa-link',
        name: '知识图谱构建',
        desc: '结合大数据中的文本信息构建知识图谱，NLP在这个过程中起到了关键的知识抽取和整合作用。'
}
]);

</script>