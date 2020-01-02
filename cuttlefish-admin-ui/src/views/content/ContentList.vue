/**
 * 内容管理菜单——内容管理
 */
<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>内容管理</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 搜索筛选 -->
    <el-form :inline="true" :model="formInline" class="user-search">
      <el-form-item label="作者名称：">
        <el-input size="small" v-model="formInline.nickname" placeholder="请输入作者名称"></el-input>
      </el-form-item>
      <el-form-item label="内容标题：">
        <el-input size="small" v-model="formInline.title" placeholder="请输入内容标题"></el-input>
      </el-form-item>
      <el-form-item label="审核状态：">
        <el-select v-model="formInline.auditStatus" :clearable="true" placeholder="请选择">
            <el-option
              v-for="item in auditStatusList"
              :key="item.auditStatus"
              :label="item.auditStatusName"
              :value="item.auditStatus">
              <span style="float: left">{{ item.auditStatusName }}</span>
              <span style="float: right; color: #8492a6; font-size: 13px">{{ item.auditStatus }}</span>
            </el-option>
          </el-select>
      </el-form-item>
      <el-form-item>
        <el-button size="small" type="primary" icon="el-icon-search" @click="search">搜索</el-button>
        <el-button size="small" type="primary" icon="el-icon-plus" @click="handleEdit('add')">添加</el-button>
        <el-button size="small" type="primary" icon="el-icon-plus" @click="importPageVisible = true">批量导入</el-button>
        <el-button size="small" type="warning" icon="el-icon-tickets" @click="dialogBatchCheckVisible = true">批量审核</el-button>
      </el-form-item>
    </el-form>
    <el-dialog title="批量审核" :visible.sync="dialogBatchCheckVisible">
      <el-table :data="multipleSelection" height="400">
        <el-table-column property="index" label="序号" width="50"></el-table-column>
        <el-table-column property="content" label="内容" width="850"></el-table-column>
      </el-table>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogBatchCheckVisible = false">取 消</el-button>
        <el-button size="small" type="danger" :loading="loading" class="title" @click="checkContent('', 0)">审核拒绝</el-button>
        <el-button size="small" type="primary" :loading="loading" class="title" @click="checkContent('', 1)">审核通过</el-button>
      </div>
    </el-dialog>
    <!--列表-->
    <el-table size="small" @selection-change="handleSelectionChange" stripe :data="listData" highlight-current-row v-loading="loading" border element-loading-text="拼命加载中" style="width: 100%;">
      <el-table-column type="selection" width="55">
      </el-table-column>
      <!-- <el-table-column align="center" type="index" label="序号" width="50">
      </el-table-column> -->
      <el-table-column prop="nickname" label="作者" width="80">
      </el-table-column>
      <el-table-column prop="categoryName" label="所属分类" width="80">
      </el-table-column>
      <el-table-column prop="auditStatus" label="审核状态" width="80" :formatter="formatAuditStatus">
      </el-table-column>
      <el-table-column prop="title" label="内容标题" width="150" show-overflow-tooltip>
      </el-table-column>
      <el-table-column prop="content" label="内容详情" width="600">
        <template slot-scope="scope">
          <el-popover trigger="click" placement="top" width="500">
            <p>{{ scope.row.content }}</p>
            <div slot="reference" class="name-wrapper">
              {{scope.row.content|hideSomeContent}}
              <el-tag size="tiny" v-show="scope.row.content.length > 100">更多</el-tag>
            </div>
          </el-popover>
          
        </template>
      </el-table-column>
      <el-table-column prop="validStatus" label="内容状态" width="100" :formatter="formatValidStatus"></el-table-column>
      <el-table-column sortable prop="createTime" label="发表时间" width="140">
        <template slot-scope="scope">
          <div>{{scope.row.createTime|timestampToTime}}</div>
        </template>
      </el-table-column>
      <el-table-column label="操作" min-width="100">
        <template slot-scope="scope">
          <!-- <el-button size="mini" type="info" @click="handleEdit('info', scope.row)">详情</el-button> -->
          <el-button size="mini" type="primary" @click="handleEdit('edit', scope.row)">编辑</el-button>
          <el-button size="mini" type="warning" @click="handleEdit('check', scope.row)" v-if="scope.row.auditStatus == '0'" >审核</el-button>
          <el-button size="mini" type="danger" @click="changeValidStatus(0, scope.row.id)" v-if="scope.row.validStatus == '1'" >禁用</el-button>
          <el-button size="mini" type="success" @click="changeValidStatus(1, scope.row.id)" v-if="scope.row.validStatus == '0'" >启用</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <Pagination v-bind:child-msg="pageparm" @callFather="callFather"></Pagination>
    <!-- 编辑界面 -->
    <el-dialog :title="title" :visible.sync="editFormVisible" width="50%" @click="closeDialog">
      <el-form label-width="120px" :model="editForm" :rules="rules" ref="editForm" :disabled = "(buttonType == 'edit' || buttonType == 'add' ? false : true)">
        <el-input size="small" v-model="editForm.id" placeholder="内容ID" v-show="1 == 2"></el-input>
        <el-form-item label="内容标题" prop="title">
          <el-input size="small" v-model="editForm.title" placeholder="请输入内容标题"></el-input>
        </el-form-item>
        <el-form-item label="内容分类" prop="categoryId">
          <el-select v-model="editForm.categoryId" placeholder="请选择">
            <el-option
              v-for="item in categoryList"
              :key="item.categoryId"
              :label="item.categoryName"
              :value="item.categoryId">
              <span style="float: left">{{ item.categoryName }}</span>
              <span style="float: right; color: #8492a6; font-size: 13px">{{ item.categoryId }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <!-- <el-form-item label="内容分类xx" prop="categoryId">
          <van-picker :columns="columns" @change="onChange" />
        </el-form-item> -->
        <el-form-item label="内容来源" prop="contentFrom">
          <el-select v-model="editForm.contentFrom" placeholder="请选择">
            <el-option
              v-for="item in contentFromList"
              :key="item.contentFromId"
              :label="item.contentFromName"
              :value="item.contentFromId">
              <span style="float: left">{{ item.contentFromName }}</span>
              <span style="float: right; color: #8492a6; font-size: 13px">{{ item.contentFromId }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="内容关键词" prop="keyWords">
          <el-input size="small" v-model="editForm.keyWords" placeholder="请输入内容关键词"></el-input>
        </el-form-item>
        <el-form-item label="内容" prop="content">
          <el-input type="textarea" :rows="10" v-model="editForm.content" placeholder="请输入内容"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer" v-show = "(buttonType == 'edit' || buttonType == 'add' ? true : false)">
        <el-button size="small" @click="closeDialog">取消</el-button>
        <el-button size="small" type="primary" :loading="loading" class="title" @click="submitForm('editForm')">保存</el-button>
      </div>
      <div slot="footer" class="dialog-footer" v-show = "buttonType == 'check'">
        <el-button size="small" @click="closeDialog">取消</el-button>
        <el-button size="small" type="danger" :loading="loading" class="title" @click="checkContent(editForm.id, 0)">审核拒绝</el-button>
        <el-button size="small" type="primary" :loading="loading" class="title" @click="checkContent(editForm.id, 1)">审核通过</el-button>
      </div>
    </el-dialog>
    <!-- 文件导入界面 -->
    <el-dialog title="批量导入" :visible.sync="importPageVisible" width="30%">
      <el-upload
        class="upload-demo"
        ref="upload"
        action="http://localhost:8888/cuttlefish/content/resolveUploadFile"
        accept=".csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel"
        :auto-upload="false"
      >
        <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
        <el-button
          style="margin-left: 10px;"
          size="small"
          type="success"
          @click="submitUpload"
        >导入</el-button>
        <div slot="tip" class="el-upload__tip">只能上传Excel文件，且不超过xxxkb</div>
      </el-upload>
    </el-dialog>
  </div>
</template>

<script>
import { contentList, changeValidStatus, contentCategoryList, saveOrUpdateContent, checkContent, resolveUploadFile } from '../../api/contentMG'
import Pagination from '../../components/Pagination'
// const citys = {
//   '浙江': ['杭州', '宁波', '温州', '嘉兴', '湖州'],
//   '福建': ['福州', '厦门', '莆田', '三明', '泉州']
// };
export default {
  data() {
    return {
      // columns: [
      //   {
      //     values: Object.keys(citys),
      //     className: 'column1'
      //   },
      //   {
      //     values: citys['0'],
      //     className: 'column2',
      //     // defaultIndex: 3
      //   }
      // ],
      nshow: true, //switch开启
      fshow: false, //switch关闭
      loading: false, //是显示加载
      editFormVisible: false, //控制编辑页面显示与隐藏
      importPageVisible: false, //控制导入页面显示与隐藏 
      title: '添加',
      editForm: {
        id: '',
        title: '',
        content: '',
        contentFrom: '',
        categoryId: '',
        keyWords: '',
        auditStatus: '',
      },
      // rules表单验证
      rules: {
        title: [
          { required: true, message: '请输入内容标题', trigger: 'blur' },
        ],
        content: [
          { required: true, message: '请输入内容', trigger: 'blur' },
        ],
        contentFrom: [
          { required: true, message: '请选择来源', trigger: 'blur' },
        ],
        categoryId: [
          { required: true, message: '请选择分类', trigger: 'blur' },
        ]
        
      },
      formInline: {
        currentPage: 1,
        pageSize: 10,
        title: '',
        nickname: ''
      },
      
      listData: [], //用户数据
      // 分页参数
      pageparm: {
        currentPage: 1,
        pageSize: 10,
        total: 10
      },

      dialogBatchCheckVisible: false,
      multipleSelection: [],

      categoryList: [],
      contentFromList: [{"contentFromId": 0, "contentFromName": "原创"},
        {"contentFromId": 1, "contentFromName": "引用"}
      ],
      auditStatusList: [{"auditStatus": 0, "auditStatusName": "待审核"},
        {"auditStatus": 1, "auditStatusName": "审核中"}, {"auditStatus": 2, "auditStatusName": "审核通过"},
        {"auditStatus": 3, "auditStatusName": "审核拒绝"}
      ],

      buttonType: '',
    }
  },
  // 注册组件
  components: {
    Pagination
  },
  /**
   * 数据发生改变
   */

  /**
   * 创建完毕
   */
  created() {
    this.getdata(this.formInline)
    this.getCategoryList()
  },

  /**
   * 里面的方法只有被调用才会执行
   */
  methods: {
    onChange(picker, values) {
      picker.setColumnValues(1, citys[values[0]]);
    },
    // 获取公司列表
    getdata(parameter) {
      this.loading = true
      /***
       * 调用接口，注释上面模拟数据 取消下面注释
       */
      contentList(parameter)
        .then(res => {
          this.loading = false
          if (res.code != 0) {
            this.$message({
              type: 'info',
              message: res.msg
            })
          } else {
            
            this.listData = res.result.records
            // 分页赋值
            this.pageparm.currentPage = this.formInline.page
            this.pageparm.pageSize = this.formInline.limit
            this.pageparm.total = res.result.total
          }
        })
        .catch(err => {
          this.loading = false
          this.$message.error('菜单加载失败，请稍后再试！')
        })
    },
    // 分页插件事件
    callFather(parm) {
      this.formInline.currentPage = parm.currentPage
      this.formInline.pageSize = parm.pageSize
      this.getdata(this.formInline)
    },
    // 搜索事件
    search() {
      this.getdata(this.formInline)
    },
    //显示编辑界面
    handleEdit: function(type, row) {
      this.buttonType = type
      this.editFormVisible = true
      if (row != undefined && row != 'undefined') {
        if(type == "edit") this.title = '修改'
        if(type == "info") this.title = '详情'
        if(type == "check") this.title = '审核'
        this.editForm.id = row.id
        this.editForm.title = row.title
        this.editForm.content = row.content
        this.editForm.categoryId = row.categoryId
        this.editForm.contentFrom = row.contentFrom
        this.editForm.keyWords = row.keyWords
      } else {
        this.title = '添加'
        this.editForm.id = ''
        this.editForm.title = ''
        this.editForm.content = ''
        this.editForm.categoryId = ''
        this.editForm.contentFrom = ''
        this.editForm.keyWords = ''
      }
    },
    // 编辑、增加页面保存方法
    submitForm(editData) {
      this.$refs[editData].validate(valid => {
        if (valid) {
          saveOrUpdateContent(this.editForm)
            .then(res => {
              this.editFormVisible = false
              this.loading = false
              if (res.code == 0) {
                this.$message({
                  type: 'success',
                  message: '操作成功！'
                })
                this.getdata(this.formInline)
              } else {
                this.$message({
                  type: 'info',
                  message: res.msg
                })
              }
            })
            .catch(err => {
              this.editFormVisible = false
              this.loading = false
              this.$message.error('操作失败，请稍后再试！')
            })
        } else {
          return false
        }
      })
    },
    // 更改内容有效状态
    changeValidStatus(type, id) {
      var validStatus = type
      var info = (type == 1 ? "确定将内容置为无效状态吗?" : "确定将内容置为有效状态吗?")
      var params = {}
      params.validStatus = validStatus
      params.id = id
      this.$confirm(info, '信息', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          // 删除
          changeValidStatus(params)
            .then(res => {
              if (res.code == 0) {
                this.$message({
                  type: 'success',
                  message: "操作成功"
                })
                this.getdata(this.formInline)
              } else {
                this.$message({
                  type: 'info',
                  message: res.msg
                })
              }
            })
            .catch(err => {
              this.loading = false
              this.$message.error('操作失败，请稍后再试！')
            })
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除！'
          })
        })
    },

    // 审核
    checkContent(id, auditStatus){
      // debugger
      var params = {}
      var ids = []
      if(id == ''){
        for(var i = 0;i < this.multipleSelection.length;i++){
          ids.push(this.multipleSelection[i].id)
        }
      }else{
        ids.push(id);
      }
      params.ids = ids
      params.auditStatus = auditStatus
      checkContent(params)
            .then(res => {
              if (res.code == 0) {
                this.editFormVisible = false
                this.dialogBatchCheckVisible = false
                this.getdata(this.formInline)
                this.$message({
                  type: 'success',
                  message: "操作成功"
                })
              } else {
                this.$message({
                  type: 'info',
                  message: res.msg
                })
              }
            })
            .catch(err => {
              this.loading = false
              this.$message.error('操作失败，请稍后再试！')
            })
    },

    // 文件解析
    submitUpload() {
      this.$refs.upload.submit();
      this.importPageVisible = false;
      this.getdata(this.formInline);
    },
    
    // 关闭编辑、增加弹出框
    closeDialog() {
      this.editFormVisible = false
    },

    handleSelectionChange(val) {
      var batchToCheckContent = [];
      var count = 1;
      for(var i =0;i < val.length;i++){
        if(val[i].auditStatus == 0 && val[i].validStatus == 1){
          val[i].index = count++
          batchToCheckContent.push(val[i])
        }
      }
      this.multipleSelection = batchToCheckContent
    },

    // 获取分类列表
    getCategoryList() {
      contentCategoryList()
        .then(res => {
          if (res.code == 0) {
            this.categoryList = res.result;
            this.$message.console.log('查询成功')
          } else {
            // console.log('查询失败，' + res.msg)
          }
        })
        .catch(err => {
          // console.log('查询失败，请稍后再试！' + err.msg)
        })
    },

    formatValidStatus(row, column) {
      if (row.validStatus == 0) {
        return "无效";
      } else {
        return "有效";
      }
    },
    formatAuditStatus(row, column) {
      if (row.auditStatus == 0) {
        return "待审核";
      } else if(row.auditStatus == 1){
        return "审核中";
      } else if(row.auditStatus == 2){
        return "审核通过";
      } else if(row.auditStatus == 3){
        return "审核拒绝";
      }

    }



  }
}
</script>

<style scoped>
.user-search {
  margin-top: 20px;
}
.userRole {
  width: 100%;
}
</style>

 
 