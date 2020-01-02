/**
 * 用户管理菜单——用户管理
 */
<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>用户管理</el-breadcrumb-item>
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
        <el-select
          size="small"
          :clearable="true"
          v-model="formInline.userStatus"
          placeholder="请选择用户状态"
        >
          <el-option
            v-for="type in userStatus"
            :label="type.key"
            :value="type.value"
            :key="type.value"
          ></el-option>
        </el-select>
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
      <el-table-column prop="nickname" label="用户名称" width="200" show-overflow-tooltip></el-table-column>
      <el-table-column prop="loginType" label="授权登陆方式" width="100" :formatter="formatLoginType"></el-table-column>
      <el-table-column sortable prop="validContentCount" label="有效内容数" width="120"></el-table-column>
      <el-table-column sortable prop="fansCount" label="用户粉丝数" width="120"></el-table-column>
      <el-table-column sortable prop="starCount" label="用户关注数" width="120"></el-table-column>
      <el-table-column prop="userStatus" label="用户状态" width="100" :formatter="formatUserStatus"></el-table-column>
      <el-table-column sortable prop="createTime" label="注册时间" width="140">
        <template slot-scope="scope">
          <div>{{scope.row.createTime|timestampToTime}}</div>
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作" min-width="100">
        <template slot-scope="scope">
          <!-- <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button> -->
          <el-button size="mini" type="info" @click="getUserDetail(scope.row)">详情</el-button>
          <el-button size="mini" type="danger" @click="changeUserStatus(0, scope.row.id)" v-if="scope.row.userStatus == '1'" >禁用</el-button>
          <el-button size="mini" type="success" @click="changeUserStatus(1, scope.row.id)" v-if="scope.row.userStatus == '0'" >启用</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <Pagination v-bind:child-msg="pageparm" @callFather="callFather"></Pagination>
    <!-- 编辑界面 -->
    <!-- <el-dialog :title="title" :visible.sync="editFormVisible" width="30%" @click="closeDialog">
      <el-form label-width="120px" :model="editForm" :rules="rules" ref="editForm">
        <el-form-item label="内容标题" prop="title">
          <el-input size="small" v-model="editForm.title" auto-complete="off" placeholder="请输入内容标题"></el-input>
        </el-form-item>
        <el-form-item label="内容" prop="content">
          <el-input
            size="small"
            v-model="editForm.nickname"
            auto-complete="off"
            placeholder="请输入内容"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="closeDialog">取消</el-button>
        <el-button
          size="small"
          type="primary"
          :loading="loading"
          class="title"
          @click="submitForm('editForm')"
        >保存</el-button>
      </div>
    </el-dialog> -->
    <!-- 详情界面 -->
    <el-dialog :title="title" :visible.sync="detailFormVisible" width="30%" @click="closeDialog">
      <el-form status-icon :model="detailForm" >
        <el-form-item label="用户昵称：">
          <span>{{detailForm.nickname}}</span>
        </el-form-item>
        <el-form-item label="个性签名：">
          <span>{{detailForm.nickname}}</span>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { userList,changeUserStatus } from "../../api/userMG";
import Pagination from "../../components/Pagination";
export default {
  data() {
    return {
      nshow: true, //switch开启
      fshow: false, //switch关闭
      loading: false, //是显示加载
      title: "添加",
      userStatus: [{ key: "启用", value: 1 }, { key: "禁用", value: 0 }],

      editFormVisible: false, //控制编辑页面显示与隐藏
      editForm: {},

      detailFormVisible: false, //详情编辑页面显示与隐藏
      detailForm: {},

      // rules表单验证
      rules: {
        // title: [
        //   { required: true, message: '请输入内容标题', trigger: 'blur' },
        // ],
        // content: [
        //   { required: true, message: '请输入内容', trigger: 'blur' },
        // ],
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
      userList(parameter)
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
    //显示编辑界面
    // handleEdit: function(index, row) {
    //   this.editFormVisible = true
    //   if (row != undefined && row != 'undefined') {
    //     this.title = '修改'
    //     this.editForm.title = row.title
    //     this.editForm.content = row.content
    //   } else {
    //     this.title = '添加'
    //     this.editForm.title = ''
    //     this.editForm.content = ''
    //   }
    // },
    //显示详情界面
    getUserDetail: function(row) {
      this.detailFormVisible = true;
      this.title = "详情";
      this.detailForm.nickname = row.nickname;
    },
    // 编辑、增加页面保存方法
    submitForm(editData) {
      this.$refs[editData].validate(valid => {
        if (valid) {
          // deptSave(this.editForm)
          //   .then(res => {
          //     this.editFormVisible = false
          //     this.loading = false
          //     if (res.success) {
          //       this.getdata(this.formInline)
          //       this.$message({
          //         type: 'success',
          //         message: '公司保存成功！'
          //       })
          //     } else {
          //       this.$message({
          //         type: 'info',
          //         message: res.msg
          //       })
          //     }
          //   })
          //   .catch(err => {
          //     this.editFormVisible = false
          //     this.loading = false
          //     this.$message.error('公司保存失败，请稍后再试！')
          //   })
        } else {
          return false;
        }
      });
    },
    // 禁用/启用用户
    changeUserStatus(type, id) {
      var userStatus = type
      var info = (type == 1 ? "确定启用该用户吗?" : "确定禁用该用户吗?")
      var params = {}
      params.userStatus = userStatus
      params.id = id
      this.$confirm(info, '信息', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          // 删除
          changeUserStatus(params)
            .then(res => {
              if (res.code == 0) {
                this.$message({
                  type: 'success',
                  message: (type == 1 ? "该用户已启用" : "该用户已删除")
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

    // 关闭编辑、增加弹出框
    closeDialog() {
      this.editFormVisible = false;
    },

    formatLoginType(row, column) {
      if (row.qqId != null) {
        return "QQ";
      }
      if (row.wechatId != null) {
        return "微信";
      }
      if (row.sinaId != null) {
        return "新浪微博";
      } else {
        return "其他";
      }
    },
    formatUserStatus(row, column) {
      if (row.userStatus == 0) {
        return "禁用";
      } else {
        return "启用";
      }
    }
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

 
 