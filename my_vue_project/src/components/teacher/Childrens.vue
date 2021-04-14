<template>
  <el-container>
    <el-header>欢迎来到教师界面</el-header>
    <el-container>
      <el-aside>
        <el-menu
          default-active="5"
          class="el-menu-vertical-demo"
          router
        > <el-submenu index="7">
            <template slot="title">
              <i class="el-icon-menu"></i>
              <span>作业管理</span>
            </template>
            <el-menu-item index="/childrens/pingshichengji">平时成绩</el-menu-item>
            <el-menu-item index="/childrens/kaohe"  >考核作业</el-menu-item>
            <el-menu-item index="/childrens/kaocha" >考查作业 </el-menu-item>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title">
              <i class="el-icon-menu"></i>
              <span>资料管理</span>
            </template>
            <el-menu-item index="/childrens/jiaoxuerili"  >教学日历</el-menu-item>
            <el-menu-item index="/childrens/jiaoxuedagang" >教学大纲 </el-menu-item>
            <el-menu-item index="/childrens/jiaoxuerizhi">教学日志</el-menu-item>
          </el-submenu>
        <el-submenu index="6">
            <template slot="title">
            <i class="el-icon-menu"></i>
            <span>个人主页</span>
               </template>
                <el-menu-item index="/childrens/people">个人主页</el-menu-item> 
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
//import Jiaoxuedagang from './Jiaoxuedagang.vue'

//import jxdg from './Jiaoxuedagang.vue'
export default {
  

 //components:{
 //  Jiaoxuedagang
  
 // } 
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
