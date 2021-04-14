<template>
  <el-row>
    <div>
      <span>
        <el-input placeholder="请输入用户名" v-model="user.username" style="width:200px" clearable=""></el-input>
        <el-button @click="getUsers">查询</el-button>
        <el-button @click="openDialog">新增</el-button>
      </span>
    </div>
  <div>   
    <el-table
    align=center
      :data="users"
      style="width: 100%">
      <el-table-column placeholder="工号" prop="number" label="工号" width="180" align=center> </el-table-column>
      <el-table-column placeholder="用户名" prop="username" label="用户名" width="180" align=center> </el-table-column>
      <el-table-column placeholder="密码" prop="password" label="密码" width="180" show-password align=center> </el-table-column>
      <el-table-column placeholder="职务" prop="zhiwu" label="职务" width="180" align=center> </el-table-column>
      <el-table-column placeholder="头像" prop="icon" label="头像" width="180" align=center><template slot-scope="scope">
      <img width="100px" height="50px" :src="scope.row.icon">
    </template> </el-table-column>

      <el-table-column label="操作" width="200" align=center >
        <template slot-scope="scope">
          <el-button @click="openUpdate(scope.row)">修改</el-button>
          <el-button @click="delUser(scope.row.number)">删除</el-button>
        </template>
        
      </el-table-column>
     <!--<pagination v-show="total>0" :total="total" :page.sync="page" :limit.sync="limit" @pagination="getList" />-->
    </el-table>
 
  </div>
    <el-dialog title="新增用户" :visible.sync="dialogFormVisible">
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
        
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="updateUser">修改</el-button>
        <el-button type="primary" @click="addUser">新增</el-button>
      </div>
    </el-dialog>

  </el-row>
  
</template>

<script>

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
        icon:''
      },
          msg: 8888,
				list: [],
				pagesize: 10,
				currpage: 1,

      //对话框是否可见
      dialogFormVisible: false,
      formLabelWidth: '120px',
    }

  },
  created(){
    this.getUsers();
       
    },
  methods: {
   /*getList(){
     var self = this;
      //调用ajax
      var url = "http://localhost:8888/schoolms/tbook/pageInfo";
      var alistQuery = {
        pageNum:self.page,
        pageSize:self.limit
      };
      this.axios.post(url,  alistQuery).then(function(resp) {
       // alert(resp.data);
        self.total=resp.data.total;
        self.list=resp.data.list;
      });
    },*/
   /*  getlist(){
         var start= (this.page - 1) * this.limit;
         var url = "http://localhost:8888/schoolms/tbook/pageInfo";
      var self=this;
     var end   = this.page * this.limit;
      this.$axios.get(url + '?pageNum=' + start + '&pageSize=' + end)
        .then(response => {
              self.list = response.data.items;
                
        });
     },*/ 

 
     

    getUsers() {
    
      var self = this;
      //调用ajax
      var url = "http://localhost:8888/schoolms/tusers/getusersj";
      var locuser = {
      
        zhiwu:'教师',
     
  
      };
      this.axios.post(url, locuser).then(function(resp) {
        //console.log(resp.data);
        self.users = resp.data;
      });
    },
    delUser(number) {
      var self = this;
      //调用ajax
      var url = "http://localhost:8888/schoolms/tusers/delete";
      var user = {
        number:number
      };
      this.axios.post(url, user).then(function(resp) {
        //console.log(resp.data);
        if(resp.data.code=="0") {
          alert(resp.data.msg);
          //重新查询数据，更新页面
          self.getUsers();
        } else {
          alert("删除失败");
        }
      });
    },
    openDialog() {
      this.dialogFormVisible = true;
      this.user = {};
    },
    addUser() {
      var self = this;
      if(self.user.username == "") {
        alert("用户名不允许为空");
        return;
      }
      if(self.user.password == "") {
        alert("密码不允许为空");
        return;
      }

      var url = "http://localhost:8888/schoolms/tusers/add";
      var user = {
          number:self.user.number,
        username:self.user.username,
        password:self.user.password,
        zhiwu:self.user.zhiwu,
     
        
      };
      this.axios.post(url, user).then(function(resp) {
        var obj = resp.data;
        if(obj.code == "0") {
          self.getUsers();
          self.dialogFormVisible = false;
        } else {
          alert("添加失败");
        }
      });
    },
    openUpdate(user) {
      this.dialogFormVisible = true;
      this.user = user;
    },
    updateUser() {
       var self = this;
      var url = "http://localhost:8888/schoolms/tusers/update";
      var locUser = {
           number:self.user.number,
        username:self.user.username,
        password:self.user.password,
        zhiwu:self.user.zhiwu,
      
     
      };
      this.axios.post(url, locUser).then(function(resp) {
       var obj = resp.data;
        if(obj.code == "0") {
          self.getUsers();
         self.dialogFormVisible = false;
        alert("test");
        } else {
          alert("修改失败");
        }
      });
    }
  }
}
</script>

<style scoped>
</style>
