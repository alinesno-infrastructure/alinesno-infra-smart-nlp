<template>
   <div class="app-container">
      <el-row :gutter="20">
         <!--项目数据-->
         <el-col :span="24" :xs="24">
            <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="100px">
               <el-form-item label="项目名称" prop="projectName">
                  <el-input v-model="queryParams.projectName" placeholder="请输入项目名称" clearable style="width: 240px" @keyup.enter="handleQuery" />
               </el-form-item>
               <el-form-item label="项目描述" prop="projectDesc">
                  <el-input v-model="queryParams['condition[projectDesc|like]']" placeholder="请输入项目描述" clearable style="width: 240px" @keyup.enter="handleQuery" />
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

            <el-table v-loading="loading" :data="ProjectList" @selection-change="handleSelectionChange">
               <el-table-column type="selection" width="50" align="center" />
               <el-table-column label="图标" align="center" width="70" key="icon" v-if="columns[0].visible">
                  <template #default="scope">
                     <span style="font-size:25px;color:#3b5998">
                        <i class="fa-solid fa-file-word" />
                     </span>
                  </template>
               </el-table-column>

               <!-- 业务字段-->
               <el-table-column label="项目名称" align="left" key="projectName" prop="projectName" v-if="columns[1].visible" />
               <el-table-column label="项目描述" align="left" key="projectDesc" prop="projectDesc" v-if="columns[2].visible" />
               <el-table-column label="项目代码" align="center" width="200" key="projectCode" prop="projectCode" v-if="columns[3].visible" :show-overflow-tooltip="true" />
<!--               <el-table-column label="配置文档" align="center" width="200" key="openService" prop="openService" v-if="columns[4].visible" :show-overflow-tooltip="true" />-->

              <el-table-column label="开通服务" align="center" width="200" key="openService" prop="openService" v-if="columns[4].visible" :show-overflow-tooltip="true" >
                <template #default="scope">
                  <el-button type="primary" bg text @click="handleConfigType(scope.row.id , scope.row.openService)"> <i class="fa-solid fa-link"></i> 开通服务 </el-button>
                </template>
              </el-table-column>

               <el-table-column label="关闭" align="center" width="100" key="hasStatus" prop="hasStatus" v-if="columns[5].visible" :show-overflow-tooltip="true" >
                  <template #default="scope">
                     <el-switch
                        v-model="scope.row.hasStatus"
                        :active-value="1"
                        :inactive-value="0"
                        @change="handleChangStatusField('hasStatus' , scope.row.hasStatus, scope.row.id)"
                     />
                  </template>
               </el-table-column>

               <el-table-column label="添加时间" align="center" prop="addTime" v-if="columns[6].visible" width="160">
                  <template #default="scope">
                     <span>{{ parseTime(scope.row.addTime) }}</span>
                  </template>
               </el-table-column>

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

      <!-- 添加或修改应用配置对话框 -->
      <el-dialog :title="title" v-model="open" width="900px" append-to-body>
         <el-form :model="form" :rules="rules" ref="databaseRef" label-width="100px">
            <el-row>
<!--               <el-col :span="24">
                  <el-form-item label="应用图标" prop="icon">
                    <el-input v-model="form.icon" placeholder="请选择应用图标" maxlength="50" />


                  </el-form-item>
               </el-col>-->
               <el-col :span="24">
                  <el-form-item label="项目名称" prop="projectName">
                     <el-input v-model="form.projectName" placeholder="请输入项目名称" maxlength="50" />
                  </el-form-item>
               </el-col>
            </el-row>
            <el-row>
               <el-col :span="24">
                  <el-form-item label="项目描述" prop="projectDesc">
                     <el-input v-model="form.projectDesc" type="textarea" placeholder="请输入项目描述" maxlength="255" />
                  </el-form-item>
               </el-col>
            </el-row>
            <el-row>
               <el-col :span="24">
                  <el-form-item label="项目代码" prop="projectCode">
                     <el-input v-model="form.projectCode" placeholder="请输入项目代码" maxlength="255" />
                  </el-form-item>
               </el-col>

<!--               <el-col :span="24">
                 <el-form-item label="配置文档" prop="openService">
                   <el-input v-model="form.openService" placeholder="请输入配置文档" maxlength="255" />
                 </el-form-item>
               </el-col>-->
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
      <el-dialog :title="title" v-model="openopenServiceDialog" width="1024px" append-to-body>

<!--        <TypeList :openServiceIds="openServiceIds"  @TypeSelected="TypeSelected" />-->
        <TypeList   @TypeSelected="TypeSelected" />

         <template #footer>
            <div class="dialog-footer">
               <el-button type="primary" @click="submitopenServiceForm">确 定</el-button>
               <el-button @click="openopenServiceDialog = false">取 消</el-button>
            </div>
         </template>
      </el-dialog>

   </div>
</template>

<script setup name="Project">

import {
   listProject,
   delProject,
   getProject,
   updateProject,
   addProject,
   changStatusField,
   updateOpenService
} from "@/api/smart/nlp/project";
import TypeList from './openserviceList.vue';


/*import * as Icons from '@element-plus/icons-vue';*/


const router = useRouter();
const { proxy } = getCurrentInstance();

// 定义变量
const ProjectList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const projectNames = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");
const dateRange = ref([]);

const projectId = ref("");
const openServiceIds = ref("");
const openServiceSelected = ref([]);


// 是否打开配置文档
const openopenServiceDialog = ref(false);

// 列显隐信息
const columns = ref([
   { key: 0, label: `图标`, visible: true },
   { key: 1, label: `项目名称`, visible: true },
   { key: 2, label: `项目描述`, visible: true },
   { key: 3, label: `项目代码`, visible: true },
   { key: 4, label: `配置文档`, visible: true },
   { key: 5, label: `关闭`, visible: true },
   { key: 6, label: `添加时间`, visible: true }
]);

const data = reactive({
   form: {},
   queryParams: {
      pageNum: 1,
      pageSize: 10,
      projectName: undefined,
      projectDesc: undefined
   },
   rules: {
      icon: [{ required: false, message: "图标不能为空", trigger: "blur" }] ,
      projectName: [{ required: true, message: "项目名称不能为空", trigger: "blur" }],
      projectDesc: [{ required: true, message: "项目描述不能为空", trigger: "blur" }] ,
      projectCode: [{ required: true , message: "项目代码不能为空", trigger: "blur"}],
      openService: [{ required: true, message: "配置文档不能为空", trigger: "blur" }]
   }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询应用列表 */
function getList() {
   loading.value = true;
   listProject(proxy.addDateRange(queryParams.value, dateRange.value)).then(res => {
      loading.value = false;
      ProjectList.value = res.rows;
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
   const ProjectIds = row.id || ids.value;
   const projectNamesTmp = row.projectName || projectNames.value;
   proxy.$modal.confirm('是否确认删除项目名称为"' + projectNamesTmp + '"的数据项？').then(function () {
      return delProject(ProjectIds);
   }).then(() => {
      getList();
      proxy.$modal.msgSuccess("删除成功");
   }).catch(() => { });
};

/** 选择条数  */
function handleSelectionChange(selection) {
   ids.value = selection.map(item => item.id);
   projectNames.value = selection.map(item => item.projectName);
   single.value = selection.length != 1;
   multiple.value = !selection.length;
};

/** 重置操作表单 */
function reset() {
   form.value = {
      id: undefined,
      icon: undefined,
      projectName: undefined,
      projectDesc: undefined,
      projectCode: undefined,
      openService: undefined
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
   title.value = "添加项目";
};

/** 修改按钮操作 */
function handleUpdate(row) {
   reset();
   const ProjectId = row.id || ids.value;
   getProject(ProjectId).then(response => {
      form.value = response.data;
      open.value = true;
      title.value = "修改项目";
   });
};

/** 提交按钮 */
function submitForm() {
   proxy.$refs["databaseRef"].validate(valid => {
      if (valid) {
         if (form.value.id != undefined) {
            updateProject(form.value).then(response => {
               proxy.$modal.msgSuccess("修改成功");
               open.value = false;
               getList();
            });
         } else {
            addProject(form.value).then(response => {
               proxy.$modal.msgSuccess("新增成功");
               open.value = false;
               getList();
            });
         }
      }
   });
};

/*配置文档*/
function handleConfigType(id , config){
   projectId.value = id ;
   openServiceIds.value = config ;
   openopenServiceDialog.value = true ; 
};

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
function submitopenServiceForm(){
  updateOpenService(projectId.value, openServiceSelected.value.toString())
  openopenServiceDialog.value = false ;
}

function TypeSelected(val){
  openServiceSelected.value = val
}




getList();

</script>
