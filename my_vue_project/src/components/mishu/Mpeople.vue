<template>
  <el-row>
<h3>个人基本资料</h3>
  <div>   
    <el-table
    align=center
      :data="users"
      style="width:100%">
      <el-table-column placeholder="工号" prop="number" label="工号" width="160" align=center> </el-table-column>
      <el-table-column placeholder="本人姓名" prop="username" label="本人姓名" width="160" align=center> </el-table-column>
      <el-table-column placeholder="密码" prop="password" label="密码" width="160" show-password align=center> </el-table-column>
      <el-table-column placeholder="职务" prop="zhiwu" label="职务" width="160" align=center> </el-table-column>
      
      <el-table-column placeholder="头像" prop="icon" label="头像" width="160" align=center> <template slot-scope="scope">
      <img width="100px" height="50px" :src="scope.row.icon">
    </template> </el-table-column>
    
      <el-table-column label="操作" width="200" align=center >
        <template slot-scope="scope">
          <el-button @click="openUpdate(scope.row)">修改个人信息</el-button>
        </template>
      </el-table-column>
     <!--<pagination v-show="total>0" :total="total" :page.sync="page" :limit.sync="limit" @pagination="getList" />-->
    </el-table>
  </div>
    <el-dialog title="修改个人信息" :visible.sync="dialogFormVisible">
            <el-form>
        <el-form-item label="工号" :label-width="formLabelWidth">
          <el-input v-model="user.number" autocomplete="off"></el-input>
        </el-form-item>
         </el-form>
      <el-form>
        <el-form-item label="用户名" :label-width="formLabelWidth">
          <el-input v-model="user.username" autocomplete="off"></el-input>
        </el-form-item>
         </el-form>
         <el-form>
        <el-form-item label="密码" :label-width="formLabelWidth">
          <el-input v-model="user.password" autocomplete="off"></el-input>
        </el-form-item>
         </el-form>
         <el-form>
        <el-form-item label="职务" :label-width="formLabelWidth">
          <el-input v-model="user.zhiwu" autocomplete="off"></el-input>
        </el-form-item>
         </el-form>
         <el-form>
        <el-form-item label="头像" :label-width="formLabelWidth">
          <el-input v-model="user.icon" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
         <el-form>
       <el-upload
         action="http://localhost:8888/schoolms/upload/nativeshenhe.do"
          list-type="picture-card"
          :show-file-list="false"
           :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload">
        <img v-if="imageUrl" :src="imageUrl"  width="100px" height="50px" class="avatar">
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="updateUser">修改</el-button>
      
      </div>
    </el-dialog>

  </el-row>
  
</template>

<script>
import db from '@/components/localstorage'
//import Pagination from '@/components/Pagination';
export default {
  // components: { Pagination },
   
  data() {
    return {
      users: [],
      user: {
        number:'',
        username: '',
        password: '',
        zhiwu:'',
        icon:'',
        department:''
      },
          msg: 8888,
				list: [],
				pagesize: 10,
				currpage: 1,
imageUrl:'',
      //对话框是否可见
      dialogFormVisible: false,
      formLabelWidth: '120px',
    }

  }, created(){
       this.getUsers()
      
    //this.findByPage(this.currentPage, this.size);
    },
  methods: {
    
  
         // 文件上传
 handleAvatarSuccess(res, file) {
   console.log(file.response);
   if(file.response){
   var self=this;
        self.imageUrl = file.response;
      console.log(self.imageUrl);

       
   }
      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt10M = file.size / 1024 / 1024 < 10;

       if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt10M) {
          this.$message.error('上传头像图片大小不能超过 10MB!');
       }
        return isJPG &&  isLt10M; //
      },
    
getUsers() {
    
      var self = this;
       const cc=db.get('edit',cc); 
      //调用ajax
      var url = "http://localhost:8888/schoolms/tusers/getusersm";
      var locuser = {
        number:cc,
        username:self.user.username,
        password:self.user.password,
        zhiwu:'秘书',
        icon:self.user.icon,
      };
      this.axios.post(url, locuser).then(function(resp) {
        //console.log(resp.data);
        self.users = resp.data;
      });
    },
  
    openDialog() {
      this.dialogFormVisible = true;
      this.user = {};
    },
    
    openUpdate(user) {
      this.dialogFormVisible = true;
      this.user = user;
    },
    updateUser() {
       var self = this;
        const cc=db.get('edit',cc); 
      var url = "http://localhost:8888/schoolms/tusers/update";
      var locUser = {
           number:cc,
        username:self.user.username,
        password:self.user.password,
        zhiwu:self.user.zhiwu,
      
        icon:self.imageUrl,
       
      };
      this.axios.post(url, locUser).then(function(resp) {
       var obj = resp.data;
        if(obj.code == "0") {
          self.getUsers();
         self.dialogFormVisible = false;
         self.$message({
          message: '修改成功！',
          type: 'success'
        });
        } else {
          self.$message({
          message: '修改失败！',
          type: 'error'
        });
        }
      });
    }
  }
}
</script>

<style scoped>
</style>
