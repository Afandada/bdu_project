<template>
  <el-container>
    <el-header>欢迎来到教学秘书界面</el-header>
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
  
            <el-menu-item index="/mishu/mjrili">教学日历</el-menu-item>
            <el-menu-item index="/mishu/mjxdg">教学大纲</el-menu-item>
            <el-menu-item index="/mishu/mjxrz">教学日志</el-menu-item>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title">
            <i class="el-icon-menu"></i>
            <span>个人主页</span>
               </template>
               <el-menu-item index="/mishu/mpeople">个人信息</el-menu-item>
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
