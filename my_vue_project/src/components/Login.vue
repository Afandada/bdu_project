<template>
 <div class="login-box">
   <el-form ref="form" :model="form" :rules="rules" label-width="80px">
     <h3 class="login-title">欢迎登录</h3>
      <el-form-item label="用户名" prop="number">
    <el-input v-model="form.number" placeholder="请输入用户名"></el-input>
  </el-form-item>
   <el-form-item label="密码" prop="password">
    <el-input v-model="form.password" placeholder="请输入密码" show-password></el-input>
  </el-form-item>
   <el-form-item >
     <el-button type="primary" @click="onSubmit('form')">登录</el-button>
   </el-form-item>
    <el-form-item >
     <el-button type="primary" @click="onSubmit1('form')">秘书</el-button>
   </el-form-item>
   <el-form-item >
     <el-button type="primary" @click="onSubmit2('form')">系主任</el-button>
   </el-form-item>
   </el-form>
 
 </div>


</template>

<script>

import db from './localstorage'
  export default {
    name: 'Login',
    data () {
      return {
         users: [],
        form:{
          number:'',
          password:''
        },
        obj:'',
        rules:{
           number: [
            { required: true, message: '请输入用户名', trigger: 'blur' },
            { min: 1, max:20, message: '长度在 1到 20 个字符', trigger: 'blur' }
          ],
          password:[
            {required: true,$message:'请输入密码', trigger:'blur' },
            {min:1,max:20, $message:'长度在1到20个字符',  trigger:'blur'}
              
          ],
        } 
      };
    },
    methods: {
      /*
      login(){
         this.$router.replace({ path: "/mymain" });
       var url = "http://localhost:8888/schoolms/tusers/login";
      var locUser = {
        number:this.form.number,
        //password:this.form.password,
      };
      this.axios.post(url, locUser).then(function(resp) {
       var obj = resp.data;
        if(obj.code == "0") {
        this.$router.replace({ path: "/mymain" });
        alert("test");
        } else {
          alert("修改失败");
        }
      });
    },
      */
      
              //query:{number:JSON.stringify(self.form.number)}

    onSubmit(formName) {
      var self = this;
     // self.$router.replace({ path: "/mymain" });
        //先判断是否为管理员
      
       self.$refs[formName].validate((valid) => {
         
          
            
             
             var url = "http://localhost:8888/schoolms/tusers/login";
        this.axios.post(url, self.form).then(function(resp) {
        var obj = resp.data;
        var obj5=obj.code
        console.log(self.form.number)
       if(obj5=="1"){
         var url1 = "http://localhost:8888/schoolms/tusers/getusers";
           console.log(self.form.number)
        self.axios.post(url1,self.form).then(function(resp1) {
          var obj3=resp1.data
        
         console.log("__________________)))(*(())")
          
         console.log(obj3)
         switch(obj3){
             case "院长": self.$router.push({
                name:"jrili",//值是在配置路由规则时给路由的命名，相当于别名
　　　　         query:{number:self.form.number}
                     //query:{number:JSON.stringify(self.form.number)}
                //给变量edit赋值保存到db中
            }), db.save('edit',self.form.number) ;break;
            case "教师": self.$router.push({
                name:"jiaoxuerili",//值是在配置路由规则时给路由的命名，相当于别名
　　　　         query:{number:self.form.number}
                     //query:{number:JSON.stringify(self.form.number)}
                //给变量edit赋值保存到db中
            }), db.save('edit',self.form.number) ;break;
            case "系主任": self.$router.push({
                name:"xjrili",//值是在配置路由规则时给路由的命名，相当于别名
　　　　         query:{number:self.form.number}
                     //query:{number:JSON.stringify(self.form.number)}
                //给变量edit赋值保存到db中
            }), db.save('edit',self.form.number) ;break;
             case "秘书": self.$router.push({
                name:"mjrili",//值是在配置路由规则时给路由的命名，相当于别名
　　　　         query:{number:self.form.number}
                     //query:{number:JSON.stringify(self.form.number)}
                //给变量edit赋值保存到db中
            }), db.save('edit',self.form.number) ;break;
         }
       })
       }else{
                   self.$message.error('用户名或密码不正确');
       }
        });

   
        });

      },
      resetForm(formName) {
        self .$refs[formName].resetFields();
      }
      
     
    }
    }
  
</script>
<style scoped>
 .login-box{
   text-align:center;
     width:500px;
     height: 300px;
     border:1px solid #DCDFE6;
     margin: 150px 150px 100px 350px;
     padding:20px 50px 20px 30px;
     border-radius: 20px;
     box-shadow:0px 0px 20px #DCDFE6;
 }
 .login-title{
   text-align:center;
   margin-bottom:40px;
 }

</style>