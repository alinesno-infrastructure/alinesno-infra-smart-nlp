<template>
   <div class="app-container">
      <el-row :gutter="20">
         <!--监控管理数据-->
         <el-col :span="24" :xs="24">
            <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="100px">
               <el-form-item label="服务名称" prop="serviceName">
                  <el-input v-model="queryParams.serviceName" placeholder="请输入服务名称" clearable style="width: 240px" @keyup.enter="handleQuery" />
               </el-form-item>
               <el-form-item label="服务地址" prop="uri">
                  <el-input v-model="queryParams['condition[uri|like]']" placeholder="请输入服务地址" clearable style="width: 240px" @keyup.enter="handleQuery" />
               </el-form-item>
               <el-form-item>
                  <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
                  <el-button icon="Refresh" @click="resetQuery">重置</el-button>
               </el-form-item>
            </el-form>

            <el-row :gutter="10" class="mb8">

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
            </el-row>

           <el-table :data="MonitormanageList" style="margin-top: 18px;" @selection-change="handleSelectionChange">
             <el-table-column type="selection" width="50" align="center" />
             <el-table-column label="分组" prop="serviceGroup" :show-overflow-tooltip="true"  v-if="columns[0].visible" ></el-table-column>
             <el-table-column label="服务名称" prop="serviceName" :show-overflow-tooltip="true" v-if="columns[1].visible"></el-table-column>
<!--             <el-table-column label="服务名称" v-if="columns[1].visible">
               <template #default="scope">
                 <el-tag type="success">{{scope.row.serviceName}}</el-tag>
               </template>
             </el-table-column>-->
             <el-table-column label="服务地址" prop="uri" :show-overflow-tooltip="true" v-if="columns[2].visible"></el-table-column>
             <el-table-column label="断言路径" prop="path" v-if="columns[3].visible"></el-table-column>
             <el-table-column label="最近请求时间" prop="createTime" width="220" v-if="columns[4].visible"></el-table-column>
             <el-table-column label="今日请求总次数" prop="count" width="140" style="font-weight: bold;" v-if="columns[5].visible"></el-table-column>

             <!-- 操作字段  -->
             <el-table-column label="操作" align="center" width="150" class-name="small-padding fixed-width">
               <template #default="scope">
                 <el-tooltip content="修改" placement="top" v-if="scope.row.id !== 1">
                   <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)"
                              v-hasPermi="['system:Project:edit']"></el-button>
                 </el-tooltip>
                 <el-tooltip content="删除" placement="top" v-if="scope.row.id !== 1">
                   <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)"
                              v-hasPermi="['system:Project:remove']"></el-button>
                 </el-tooltip>
               </template>

             </el-table-column>

           </el-table>
            <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
         </el-col>
      </el-row>

      <!-- 添加或修改监控管理对话框 -->
      <el-dialog :title="title" v-model="open" width="900px" append-to-body>
         <el-form :model="form" :rules="rules" ref="databaseRef" label-width="100px">
            <el-row>
               <el-col :span="24">
                  <el-form-item label="分组" prop="serviceGroup">
                     <el-input v-model="form.serviceGroup" placeholder="请输入分组" maxlength="50" />
                  </el-form-item>
               </el-col>
            </el-row>
            <el-row>
               <el-col :span="24">
                  <el-form-item label="服务名称" prop="serviceName">
                     <el-input v-model="form.serviceName" type="textarea" placeholder="请输入服务名称" maxlength="255" />
                  </el-form-item>
               </el-col>
            </el-row>
            <el-row>
               <el-col :span="24">
                  <el-form-item label="服务地址" prop="uri">
                     <el-input v-model="form.uri" placeholder="请输入服务地址" maxlength="255" />
                  </el-form-item>
               </el-col>

               <el-col :span="24">
                 <el-form-item label="断言路径" prop="path">
                   <el-input v-model="form.path" placeholder="请输入断言路径" maxlength="255" />
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


      <!-- 文档列表 -->
      <el-dialog :title="title" v-model="openDocumentTypeDialog" width="1024px" append-to-body>

<!--         <TypeList />-->

         <template #footer>
            <div class="dialog-footer">
               <el-button type="primary" @click="submitDocumentTypeForm">确 定</el-button>
               <el-button @click="openDocumentTypeDialog = false">取 消</el-button>
            </div>
         </template>
      </el-dialog>

   </div>
</template>

<script setup name="Monitormanage">

import {
  listMonitormanage,
  delMonitormanage,
  getMonitormanage,
  updateMonitormanage,
  addMonitormanage,
  changStatusField
} from "@/api/smart/nlp/monitormanage";



const router = useRouter();
const { proxy } = getCurrentInstance();

// 定义变量
const MonitormanageList = ref([]);
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

// 是否打开配置文档
const openDocumentTypeDialog = ref(false);

// 列显隐信息
const columns = ref([
   { key: 0, label: `分组`, visible: true },
   { key: 1, label: `服务名称`, visible: true },
   { key: 2, label: `服务地址`, visible: true },
   { key: 3, label: `断言路径`, visible: true },
   { key: 4, label: `最近请求时间`, visible: true },
   { key: 5, label: `今日请求总次数`, visible: true }
]);

const data = reactive({
   form: {},
   queryParams: {
      pageNum: 1,
      pageSize: 10,
      serviceName: undefined,
      uri: undefined
   },
   rules: {
      serviceGroup: [{ required: false, message: "分组不能为空", trigger: "blur" }] ,
      serviceName: [{ required: true, message: "服务名称不能为空", trigger: "blur" }],
      uri: [{ required: true, message: "服务地址不能为空", trigger: "blur" }] ,
      path: [{ required: true , message: "断言路径不能为空", trigger: "blur"}]
   }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询应用列表 */
function getList() {
   loading.value = true;
   listMonitormanage(proxy.addDateRange(queryParams.value, dateRange.value)).then(res => {
      loading.value = false;
      MonitormanageList.value = res.rows;
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
   proxy.$modal.confirm('是否确认删除监控管理名称为"' + serviceNamesTmp + '"的数据项？').then(function () {
      return delMonitormanage(IdTmps);
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
};

/** 重置操作表单 */
function reset() {
   form.value = {
      id: undefined,
      group: undefined,
      serviceName: undefined,
      uri: undefined,
      path: undefined
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
   title.value = "添加监控管理";
};

/** 修改按钮操作 */
function handleUpdate(row) {
   reset();
   const ProjectId = row.id || ids.value;
   getMonitormanage(ProjectId).then(response => {
      form.value = response.data;
      open.value = true;
      title.value = "修改监控管理";
   });
};

/** 提交按钮 */
function submitForm() {
   proxy.$refs["databaseRef"].validate(valid => {
      if (valid) {
         if (form.value.id != undefined) {
            updateMonitormanage(form.value).then(response => {
               proxy.$modal.msgSuccess("修改成功");
               open.value = false;
               getList();
            });
         } else {
            addMonitormanage(form.value).then(response => {
               proxy.$modal.msgSuccess("新增成功");
               open.value = false;
               getList();
            });
         }
      }
   });
};

/** 配置文档 */
/*function handleConfigType(id , config){
   openDocumentTypeDialog.value = true ; 
}*/

/** 修改状态 */
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

/** 提交配置文档类型 */
function submitDocumentTypeForm(){
   // TODO 待保存应用文档类型
}

getList();

</script>
