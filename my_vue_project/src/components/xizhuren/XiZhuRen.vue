<template>
  <el-container>
    <el-header>欢迎来到系主任界面</el-header>
    <el-container>
      <el-aside>
        <el-menu
          default-active="2"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
          router
        >
         
          <el-submenu index="10">
            <template slot="title">
            <i class="el-icon-menu"></i>
            <span>资料审批</span>
               </template>
            <el-menu-item index="/xizhuren/xjrili">教学日历</el-menu-item>
            <el-menu-item index="/xizhuren/xjxdg">教学大纲</el-menu-item>
            <el-menu-item index="/xizhuren/xjxrz">教学日志</el-menu-item>
          </el-submenu>
          <el-submenu index="9">
            <template slot="title">
            <i class="el-icon-menu"></i>
            <span>个人主页</span>
               </template>
               <el-menu-item index="/xizhuren/xpeople">个人信息</el-menu-item>
          </el-submenu>
        
        </el-menu>
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
}
</script>

<style scoped>

.el-container {
  height: 100%;
}

.el-header {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: #D3DCE6;
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
  line-height: 160px;
}
</style>
