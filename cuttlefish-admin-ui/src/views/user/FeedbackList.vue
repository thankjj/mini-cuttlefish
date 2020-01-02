/**
 * 用户管理菜单——用户反馈管理
 */
<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>用户反馈管理</el-breadcrumb-item>
    </el-breadcrumb>
    <!-- 搜索筛选 -->
    <el-form :inline="true" :model="formInline" class="user-search">
      <el-form-item label="搜索：">
        <el-input
          size="small"
          :clearable="true"
          v-model="formInline.nickname"
          placeholder="请输入用户名称"
        ></el-input>
      </el-form-item>
      
      <el-form-item>
        <el-button size="small" type="primary" icon="el-icon-search" @click="search">搜索</el-button>
        <!-- <el-button size="small" type="primary" icon="el-icon-plus" @click="handleEdit()">添加</el-button> -->
      </el-form-item>
    </el-form>
    <!--列表-->
    <el-table
      size="small"
      :data="listData"
      highlight-current-row
      v-loading="loading"
      border
      element-loading-text="拼命加载中"
      style="width: 100%;"
    >
      <el-table-column align="center" type="index" label="序号" width="80"></el-table-column>
      <el-table-column prop="nickname" label="反馈人" width="100" show-overflow-tooltip></el-table-column>
      <el-table-column sortable prop="feedbackStatus" label="反馈状态" width="100" :formatter="formatFeedbackStatus"></el-table-column>
      <el-table-column prop="feedbackContent" label="反馈内容" width="500"></el-table-column>
      <el-table-column prop="feedbackResult" label="反馈结果说明" width="400"></el-table-column>
      <el-table-column sortable prop="createTime" label="反馈时间" width="140">
        <template slot-scope="scope">
          <div>{{scope.row.createTime|timestampToTime}}</div>
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作" min-width="200">
        <template slot-scope="scope">
          <el-button size="mini" type="info" @click="getDetail(scope.row)">详情</el-button>
          <el-button size="mini" type="danger" @click="changeStatus(scope.row)" 
              v-if="scope.row.feedbackStatus == '0' || scope.row.feedbackStatus == '1'" >处理</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <Pagination v-bind:child-msg="pageparm" @callFather="callFather"></Pagination>
    <!-- 详情界面 -->
    <el-dialog :title="title" :visible.sync="detailFormVisible" width="40%" @click="closeDialog">
      <el-form status-icon :model="detailForm" :rules="rules" ref="detailForm" >
        <el-input size="small" v-model="detailForm.id" placeholder="反馈ID" v-show="1 == 2"></el-input>
        <el-form-item label="用户昵称：">
          <span>{{detailForm.nickname}}</span>
        </el-form-item>
        <el-form-item label="反馈内容：">
          <span>{{detailForm.feedbackContent}}</span>
        </el-form-item>

        <el-form-item label="处理结果：" prop="feedbackStatus">
          <el-select v-model="detailForm.feedbackStatus" placeholder="请选择" :disabled="!isdeal">
            <el-option
              v-for="item in feedbackStatusList"
              :key="item.feedbackStatus"
              :label="item.feedbackStatusName"
              :value="item.feedbackStatus">
              <span style="float: left">{{ item.feedbackStatusName }}</span>
              <span style="float: right; color: #8492a6; font-size: 13px">{{ item.feedbackStatus }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="内容结果说明：" prop="feedbackResult">
          <el-input type="textarea" :disabled="!isdeal" :rows="3" v-model="detailForm.feedbackResult" placeholder="请输入内容结果说明"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer" v-show = "isdeal">
        <el-button size="small" @click="closeDialog">取消</el-button>
        <el-button size="small" type="primary" :loading="loading" class="title" @click="submitForm('detailForm')">保存</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { feedbackList, updateFeedbackStatus } from "../../api/userMG";
import Pagination from "../../components/Pagination";
export default {
  data() {
    return {
      nshow: true, //switch开启
      fshow: false, //switch关闭
      loading: false, //是显示加载

      detailFormVisible: false, //详情编辑页面显示与隐藏
      detailForm: {
        id: "",
        feedbackStatus: "",
        feedbackResult: "",
      },

      isdeal: true,
      title: "",
      feedbackStatusList: [
        {"feedbackStatus": 0, "feedbackStatusName": "已提交"},
        {"feedbackStatus": 1, "feedbackStatusName": "处理中"},
        {"feedbackStatus": 2, "feedbackStatusName": "已处理"}
      ],
      // rules表单验证
      rules: {
        feedbackStatus: [
          { required: true, message: '请选择处理状态', trigger: 'blur' },
        ],
        
      },
      
      formInline: {
        currentPage: 1,
        pageSize: 10,
        nickname: "",
        userStatus: ""
      },

      listData: [], //用户数据
      // 分页参数
      pageparm: {
        currentPage: 1,
        pageSize: 10,
        total: 10
      }
    };
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
    this.getdata(this.formInline);
  },

  /**
   * 里面的方法只有被调用才会执行
   */
  methods: {
    // 获取公司列表
    getdata(parameter) {
      this.loading = true;
      /***
       * 调用接口，注释上面模拟数据 取消下面注释
       */
      feedbackList(parameter)
        .then(res => {
          this.loading = false;
          if (res.code != 0) {
            this.$message({
              type: "info",
              message: res.msg
            });
          } else {
            this.listData = res.result.records;
            // 分页赋值
            this.pageparm.currentPage = this.formInline.page;
            this.pageparm.pageSize = this.formInline.limit;
            this.pageparm.total = res.result.total;
          }
        })
        .catch(err => {
          this.loading = false;
          this.$message.error("菜单加载失败，请稍后再试！");
        });
    },
    // 分页插件事件
    callFather(parm) {
      this.formInline.currentPage = parm.currentPage;
      this.formInline.pageSize = parm.pageSize;
      this.getdata(this.formInline);
    },
    // 搜索事件
    search() {
      this.getdata(this.formInline);
    },
    
    //显示详情界面
    getDetail: function(row) {
      this.title = "详情";
      this.isdeal = false;
      this.detailFormVisible = true;
      this.detailForm.nickname = row.nickname;
      this.detailForm.feedbackContent = row.feedbackContent;
      this.detailForm.feedbackStatus = row.feedbackStatus;
      this.detailForm.feedbackResult = row.feedbackResult;
    },

    changeStatus: function(row) {
      this.title = "处理";
      this.isdeal = true;
      this.detailFormVisible = true;
      this.detailForm.id = row.id;
      this.detailForm.nickname = row.nickname;
      this.detailForm.feedbackContent = row.feedbackContent;
      if(row.feedbackStatus == 0){
        this.feedbackStatusList = [
          {"feedbackStatus": 1, "feedbackStatusName": "处理中"},
          {"feedbackStatus": 2, "feedbackStatusName": "已处理"}
        ]
      }else{
        this.feedbackStatusList = [
          {"feedbackStatus": 2, "feedbackStatusName": "已处理"}
        ]
      }
      
    },

    // 编辑、增加页面保存方法
    submitForm(editData) {
      this.$refs[editData].validate(valid => {
        if (valid) {
          updateFeedbackStatus(this.detailForm)
            .then(res => {
              this.detailFormVisible = false
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
              this.detailFormVisible = false
              this.loading = false
              this.$message.error('操作失败，请稍后再试！')
            })
        } else {
          return false
        }
      })
    },
    

    // 关闭编辑、增加弹出框
    closeDialog() {
      this.detailFormVisible = false;
    },

    // 0-反馈已提交  1-反馈处理中 2-反馈处理结果
    formatFeedbackStatus(row, column) {
      if (row.feedbackStatus == 0) {
        return "已提交";
      } else if (row.feedbackStatus == 1) {
        return "处理中";
      }else {
        return "已处理";
      }
    },

    
  }
};
</script>

<style scoped>
.user-search {
  margin-top: 20px;
}
.userRole {
  width: 100%;
}
</style>

 
 