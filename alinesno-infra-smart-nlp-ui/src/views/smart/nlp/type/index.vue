<template>
   <div class="app-container">
      <el-row :gutter="20">
         <!--应用数据-->
         <el-col :span="24" :xs="24">
            <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="100px">
               <el-form-item label="应用名称" prop="typeName">
                  <el-input v-model="queryParams.typeName" placeholder="请输入应用名称" clearable style="width: 240px" @keyup.enter="handleQuery" />
               </el-form-item>
               <el-form-item label="应用名称" prop="typeName">
                  <el-input v-model="queryParams['condition[typeName|like]']" placeholder="请输入应用名称" clearable style="width: 240px" @keyup.enter="handleQuery" />
               </el-form-item>
               <el-form-item>
                  <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
                  <el-button icon="Refresh" @click="resetQuery">重置</el-button>
               </el-form-item>
            </el-form>

            <el-row :gutter="10" class="mb8">

               <!-- <el-col :span="1.5">
                  <el-button type="primary" plain icon="Plus" @click="handleAdd">新增</el-button>
               </el-col> -->

               <el-col :span="1.5">
                  <el-button type="success" plain icon="Edit" :disabled="single" @click="handleUpdate">修改</el-button>
               </el-col>

               <!-- <el-col :span="1.5">
                  <el-button type="danger" plain icon="Delete" :disabled="multiple" @click="handleDelete">删除</el-button>
               </el-col> -->

               <right-toolbar v-model:showSearch="showSearch" @queryTable="getList" :columns="columns"></right-toolbar>
            </el-row>

            <el-table v-loading="loading" :data="TypeList" @selection-change="handleSelectionChange">
               <el-table-column type="index" width="50" align="center" />
               <el-table-column label="图标" align="center" width="70" key="icon" v-if="columns[5].visible">
                  <template #default="scope">
                     <span style="font-size:25px;color:#3b5998">
                        <i :class="scope.row.icon" />
                     </span>
                  </template>
               </el-table-column>

               <!-- 业务字段-->
               <el-table-column label="类型名称" align="center" width="150" key="typeName" prop="typeName" v-if="columns[0].visible" />
               <el-table-column label="类型描述" align="left" key="typeDesc" prop="typeDesc" v-if="columns[0].visible" />
               <el-table-column label="是否开启" align="center" width="200" key="isOpen" prop="isOpen" v-if="columns[1].visible" :show-overflow-tooltip="true" >
                  <template #default="scope">
                     <el-switch
                        v-model="scope.row.isOpen"
                        active-value="0"
                        inactive-value="1"
                     />
                  </template>
               </el-table-column>
               <el-table-column label="请求次数" align="center" width="300" key="requestCount" prop="requestCount" v-if="columns[2].visible" :show-overflow-tooltip="true">
                  <template #default="scope">
                     <i class="fa-solid fa-link"></i> 100次/每分钟
                  </template>
               </el-table-column>

               <el-table-column label="限流" align="center" width="100" key="isRateLimited" prop="isRateLimited" v-if="columns[3].visible" :show-overflow-tooltip="true" >
                  <template #default="scope">
                     <el-switch
                        v-model="scope.row.isRateLimited"
                        active-value="false"
                        inactive-value="true"
                     />
                  </template>
               </el-table-column>

               <!-- 操作字段  -->
               <el-table-column label="操作" align="center" width="100" class-name="small-padding fixed-width">
                  <template #default="scope">
                     <el-tooltip content="配置" placement="top" v-if="scope.row.id !== 1">
                        <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['system:Type:edit']"></el-button>
                     </el-tooltip>
                  </template>

               </el-table-column>
            </el-table>
            <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />
         </el-col>
      </el-row>

      <!-- 添加或修改应用配置对话框 -->
      <el-dialog :title="title" v-model="open" width="900px" append-to-body>
         <el-form :model="form" :rules="rules" ref="databaseRef" label-width="100px">
            <el-row>
               <el-col :span="24">
                  <el-form-item label="类型名称" prop="typeName">
                     <el-input v-model="form.typeName" placeholder="请输入应用名称" maxlength="50" />
                  </el-form-item>
               </el-col>
            </el-row>
            <el-row>
               <el-col :span="24">
                  <el-form-item label="类型描述" prop="typeDesc">
                     <el-input v-model="form.typeDesc" placeholder="请输入类型描述" maxlength="256" />
                  </el-form-item>
               </el-col>
            </el-row>
            <el-row>
               <el-col :span="24">
                  <el-form-item label="图标" prop="icon">
                     <el-input v-model="form.icon" placeholder="请输入图标icon" maxlength="128" />
                  </el-form-item>
               </el-col>
               <el-col :span="24">
                  <el-form-item label="是否限流" prop="isRateLimited">
                     <el-input v-model="form.isRateLimited" placeholder="请输入类型" maxlength="50" />
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

<script setup name="Type">

import {
   listType,
   delType,
   getType,
   updateType,
   addType
} from "@/api/smart/nlp/type";

const router = useRouter();
const { proxy } = getCurrentInstance();

// 定义变量
const TypeList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const dateRange = ref([]);
const postOptions = ref([]);
const roleOptions = ref([]);

// 列显隐信息
const columns = ref([
   { key: 0, label: `应用名称`, visible: true },
   { key: 1, label: `应用描述`, visible: true },
   { key: 2, label: `表数据量`, visible: true },
   { key: 3, label: `类型`, visible: true },
   { key: 4, label: `应用地址`, visible: true },
   { key: 5, label: `状态`, visible: true },
   { key: 6, label: `更新时间`, visible: true }
]);

const data = reactive({
   form: {},
   queryParams: {
      pageNum: 1,
      pageSize: 10,
      typeName: undefined,
      isOpen: undefined
   },
   rules: {
      typeName: [{ required: true, message: "名称不能为空", trigger: "blur" }] ,
      jdbcUrl: [{ required: true, message: "连接不能为空", trigger: "blur" }],
      isRateLimited: [{ required: true, message: "类型不能为空", trigger: "blur" }] ,
      dbUsername: [{ required: true , message: "用户名不能为空", trigger: "blur"}],
      dbPasswd: [{ required: true, message: "密码不能为空", trigger: "blur" }] ,
      isOpen: [{ required: true, message: "备注不能为空", trigger: "blur" }]
   }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询应用列表 */
function getList() {
   loading.value = true;
   listType(proxy.addDateRange(queryParams.value, dateRange.value)).then(res => {
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
   queryParams.value.deptId = undefined;
   proxy.$refs.deptTreeRef.setCurrentKey(null);
   handleQuery();
};
/** 删除按钮操作 */
function handleDelete(row) {
   const TypeIds = row.id || ids.value;
   proxy.$modal.confirm('是否确认删除应用编号为"' + TypeIds + '"的数据项？').then(function () {
      return delType(TypeIds);
   }).then(() => {
      getList();
      proxy.$modal.msgSuccess("删除成功");
   }).catch(() => { });
};

/** 选择条数  */
function handleSelectionChange(selection) {
   ids.value = selection.map(item => item.id);
   single.value = selection.length != 1;
   multiple.value = !selection.length;
};

/** 重置操作表单 */
function reset() {
   form.value = {
      id: undefined,
      deptId: undefined,
      TypeName: undefined,
      requestCount: undefined,
      password: undefined,
      phonenumber: undefined,
      status: "0",
      remark: undefined,
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
   title.value = "添加应用";
};

/** 修改按钮操作 */
function handleUpdate(row) {
   reset();
   const id = row.id || ids.value;
   getType(id).then(response => {
      form.value = response.data;
      open.value = true;
      title.value = "修改应用";
   });
};

/** 提交按钮 */
function submitForm() {
   proxy.$refs["databaseRef"].validate(valid => {
      if (valid) {
         if (form.value.id != undefined) {
            updateType(form.value).then(response => {
               proxy.$modal.msgSuccess("修改成功");
               open.value = false;
               getList();
            });
         } else {
            addType(form.value).then(response => {
               proxy.$modal.msgSuccess("新增成功");
               open.value = false;
               getList();
            });
         }
      }
   });
};

getList();

</script>
