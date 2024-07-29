<template>
  <div class="app-container">
    <el-row :gutter="20">
      <!--应用数据-->
      <el-col :span="24" :xs="24">
        <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="100px">
          <el-form-item label="服务名称" prop="serviceName">
            <el-input v-model="queryParams.serviceName" placeholder="请输入服务名称" clearable style="width: 240px" @keyup.enter="handleQuery" />
          </el-form-item>
          <el-form-item label="服务描述" prop="serviceDesc">
            <el-input v-model="queryParams['condition[serviceDesc|like]']" placeholder="请输入服务描述" clearable style="width: 240px" @keyup.enter="handleQuery" />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
            <el-button icon="Refresh" @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-form>

<!--        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" plain icon="Plus" @click="handleAdd">新增</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate">修改</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete">删除</el-button>
          </el-col>
          <right-toolbar v-model:showSearch="showSearch" @queryTable="getList" :columns="columns"></right-toolbar>
        </el-row>-->

        <el-table v-loading="loading" :data="TypeList" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column type="index" width="50" align="center" />
          <el-table-column label="图标" align="center" width="70" key="icon" v-if="columns[0].visible">
            <template #default="scope">
                     <span style="font-size:25px;color:#3b5998">
                        <i class="fa-solid fa-file-word" />
                     </span>
            </template>
          </el-table-column>

          <!-- 业务字段-->
          <el-table-column label="服务名称" align="center" width="150" key="serviceName" prop="serviceName" v-if="columns[1].visible" />
          <el-table-column label="服务描述" align="left" key="serviceDesc" prop="serviceDesc" style="width: 30px" v-if="columns[2].visible" />
          <el-table-column label="是否开启" align="center" width="200" key="isOpen" prop="isOpen"  style="width: 20px" v-if="columns[3].visible" :show-overflow-tooltip="true" >
            <template #default="scope">
              <el-switch
                  v-model="scope.row.isOpen"
                  :active-value="1"
                  :inactive-value="0"
                  @change="handleChangStatusField('isOpen' , scope.row.isOpen, scope.row.id)"
              />
            </template>
          </el-table-column>
          <el-table-column label="是否限流" align="center" width="200" key="isRateLimited" prop="isRateLimited" style="width: 20px" v-if="columns[4].visible" :show-overflow-tooltip="true" >
            <template #default="scope">
              <el-switch
                  v-model="scope.row.isRateLimited"
                  :active-value="1"
                  :inactive-value="0"
                  @change="handleChangStatusField('isRateLimited' , scope.row.isRateLimited, scope.row.id)"
              />
            </template>
          </el-table-column>
          <el-table-column label="请求次数" align="center" width="300" key="requestCount" prop="requestCount" v-if="columns[5].visible" :show-overflow-tooltip="true">
          </el-table-column>



<!--          &lt;!&ndash; 操作字段  &ndash;&gt;
          <el-table-column label="操作" align="center" width="100" class-name="small-padding fixed-width">
            <template #default="scope">
              <el-tooltip content="配置" placement="top" v-if="scope.row.id !== 1">
                <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['system:Type:edit']"></el-button>
              </el-tooltip>
            </template>

          </el-table-column>-->
        </el-table>
        <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
      </el-col>
    </el-row>

    <!-- 添加或修改应用配置对话框 -->
    <el-dialog :title="title" v-model="open" width="900px" append-to-body>
      <el-form :model="form" :rules="rules" ref="databaseRef" label-width="100px">
        <el-row>
          <!--               <el-col :span="24">
                           <el-form-item label="服务图标" prop="icon">


                             <el-upload action="#" list-type="picture-card" :auto-upload="false">
                               <el-icon><Plus /></el-icon>

                               <template #file="{ file }">
                                 <div>
                                   <img class="el-upload-list__item-thumbnail" :src="file.url" alt="" />
                                   <span class="el-upload-list__item-actions">
                                           <span
                                               class="el-upload-list__item-preview"
                                               @click="handlePictureCardPreview(file)"
                                           >
                                              <el-icon><zoom-in /></el-icon>
                                           </span>
                                           <span
                                               v-if="!disabled"
                                               class="el-upload-list__item-delete"
                                               @click="handleDownload(file)"
                                           >
                                              <el-icon><Download /></el-icon>
                                           </span>
                                           <span
                                               v-if="!disabled"
                                               class="el-upload-list__item-delete"
                                               @click="handleRemove(file)"
                                           >
                                              <el-icon><Delete /></el-icon>
                                           </span>
                                        </span>
                                 </div>
                               </template>
                             </el-upload>
                           </el-form-item>
                         </el-col>-->
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="服务名称" prop="serviceName">
              <el-input v-model="form.serviceName" placeholder="请输入服务名称" maxlength="50" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-col :span="24">
          <el-form-item label="服务描述" prop="serviceDesc">
            <el-input v-model="form.serviceDesc" placeholder="请输入服务描述" maxlength="50" />
          </el-form-item>
        </el-col>
        <el-row>
          <el-col :span="24">
            <el-form-item label="是否开启" prop="isOpen">
              <el-radio-group v-model="form.isOpen" class="myradiogroup"  @change="$forceUpdate()">
                <el-radio :label=1>开启</el-radio>
                <el-radio :label=0>不开启</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="是否限流" prop="isRateLimited">
              <el-radio-group v-model="form.isRateLimited" class="myradiogroup"  @change="$forceUpdate()">
                <el-radio :label=1>限流</el-radio>
                <el-radio :label=0>不限流</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>

  </div>
</template>

<script setup name="OpenService">

import {
  listOpenService,
  delOpenService,
  getOpenService,
  updateOpenService,
  addOpenService ,
  changStatusField
} from "@/api/smart/nlp/openservice";

const props = defineProps({
  //子组件接收父组件传递过来的值
  openServiceIds: String,
})
//使用父组件传递过来的值
const {openServiceIds} =toRefs(props)


// 使用defineEmits创建名称，接受一个数组
const emit = defineEmits(['TypeSelected'])

const router = useRouter();
const { proxy } = getCurrentInstance();

// 定义变量
const TypeList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const serviceNames = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const dateRange = ref([]);

// 列显隐信息
const columns = ref([
  { key: 0, label: `图标名称`, visible: true },
  { key: 1, label: `服务名称`, visible: true },
  { key: 2, label: `服务描述`, visible: true },
  { key: 3, label: `是否开启`, visible: true },
  { key: 4, label: `是否限流`, visible: true },
  { key: 5, label: `请求次数`, visible: true }
]);

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    /*id:openServiceIds.value,*/
    serviceName: undefined,
    serviceDesc: undefined
  },
  rules: {
    icon: [{ required: false, message: "图标不能为空", trigger: "blur" }] ,
    serviceName: [{ required: true, message: "服务名称不能为空", trigger: "blur" }],
    serviceDesc: [{ required: true, message: "服务描述不能为空", trigger: "blur" }] ,
    isOpen: [{ required: true , message: "是否开启不能为空", trigger: "blur"}],
    isRateLimited: [{ required: true, message: "是否限流不能为空", trigger: "blur" }] ,
    requestCount: [{ required: false, message: "请求次数不能为空", trigger: "blur" }]
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询应用列表 */
function getList() {
  loading.value = true;
  listOpenService(proxy.addDateRange(queryParams.value, dateRange.value)).then(res => {
    loading.value = false;
    TypeList.value = res.rows;
    total.value = res.total;
  });
};

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
};

/** 重置按钮操作 */
function resetQuery() {
  dateRange.value = [];
  proxy.resetForm("queryRef");
  handleQuery();
};
/** 删除按钮操作 */
function handleDelete(row) {
  const IdTmps = row.id || ids.value;
  const serviceNamesTmp = row.serviceName || serviceNames.value;
  proxy.$modal.confirm('是否确认删除服务名称为"' + serviceNamesTmp + '"的数据项？').then(function () {
    return delOpenService(IdTmps);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => { });
};

/** 选择条数  */
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.id);
  serviceNames.value = selection.map(item => item.serviceName);
  single.value = selection.length != 1;
  multiple.value = !selection.length;

  //传递给父组件
  emit('TypeSelected',ids.value )
};

/** 重置操作表单 */
function reset() {
  form.value = {
    id: undefined,
    icon: undefined,
    serviceName: undefined,
    serviceDesc: undefined,
    isOpen: undefined,
    isRateLimited: undefined,
    requestCount: "0"
  };
  proxy.resetForm("databaseRef");
};
/** 取消按钮 */
function cancel() {
  open.value = false;
  reset();
};

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加开通服务";
};

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const id = row.id || ids.value;
  getOpenService(id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改开通服务";
  });
};

/** 提交按钮 */
function submitForm() {
  proxy.$refs["databaseRef"].validate(valid => {
    if (valid) {
      if (form.value.id != undefined) {
        updateOpenService(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addOpenService(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
  });
};

const handleChangStatusField = async(field , value , id) => {
  // 判断tags值 这样就不会进页面时调用了
  const res = await changStatusField({
    field: field,
    value: value?1:0,
    id: id
  }).catch(() => { })
  if (res && res.code == 200) {
    // 刷新表格
    getList()
  }
}

getList();

</script>
