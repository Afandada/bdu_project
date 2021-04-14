<template>
<div>
  <meta http-equiv="Content-Type" content="multipart/form-data;         charset=utf-8" />

<div class="table-rili">
  <el-table
    :data="tableDatas"
    border
   
    style="width: 100%">
    <el-table-column
      fixed
      prop="coursename"
      label="课程名称(全称)"
      width="150"
       height="120"
      align=center>
    </el-table-column>
    <el-table-column
      prop="classname"
      label="班级名称"
      width="120"
       height="120"
      align=center>
    </el-table-column>
    <el-table-column
      prop="file"
      label="文件"
      width="120"
       height="120"
      align=center>
     
 <img width="50px" height="60px"  :src="imggg">
    </el-table-column>
     <el-table-column
      prop="shangchuanzhe"
      label="上传者"
      width="120"
       height="120"
      align=center>
    </el-table-column>
    <el-table-column
      fixed="right"
      label="操作"
      width="100"
       height="120"
      align=center>
      <template slot-scope="scope">
        <el-button @click="deletecourse(scope.row.file)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
<el-dialog title="添加平时成绩文件" :visible.sync="dialogFormVisible">
      <el-form>
        <el-form-item label="课程名称" :label-width="formLabelWidth">
          <el-input v-model="tableData.coursename" autocomplete="off"></el-input>
        </el-form-item>
          <el-form-item label="班级名称" :label-width="formLabelWidth">
          <el-input v-model="tableData.classname" autocomplete="off"></el-input>
        </el-form-item>
         <el-form-item label="文件" :label-width="formLabelWidth">
            <el-upload
  class="upload-demo"
  action="http://localhost:8888/schoolms/upload/nativeDown.do"
           :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
  multiple
  :limit="3"
  :file-list="fileList">
 
  <el-button size="small" type="primary" :src="imageUrl" >点击上传</el-button>
  <div slot="tip" class="el-upload__tip">只能上传word文件</div>
       </el-upload>
 </el-form-item>
</el-form>
      
      <div slot="footer" class="dialog-footer">
        <el-button  @click="dialogFormVisible = false">取消</el-button>  
        <el-button type="primary" @click="addscore">添加文件</el-button>
      </div>
    </el-dialog>
  <el-button @click="openDialog" >添加文件</el-button>
</div>
</div>
</template>
<script>

import db from '@/components/localstorage'
  export default {
   name: "test", 
    data() {
    
      return {
         
   tableDatas: [],
     tableData: {
        coursename: '',
    
        classname:'',
        file:'',
     shangchuanzhe:''
      },
        fileList: [],
         imageUrl: '',
          dialogFormVisible: false,
          formLabelWidth: '120px',
          response:'',
       imggg:'./static/word.jpg',
      }
    },
  created(){
   
      this.getcourse()
     
    },
     methods: {
        addscore() {
      var self = this;
      const cc=db.get('edit',cc); 
       this.dialogFormVisible = false;
      var url = "http://localhost:8888/schoolms/pingshi/add";
      var locscore = {
            coursename:self.tableData.coursename,
          classname: self.tableData.classname,
        
        file: self.imageUrl,
      
       shangchuanzhe:cc
        
      };
      this.axios.post(url, locscore).then(function(resp) {
       var obj = resp.data;
      console.log(self.imageUrl)
        window.open(self.imageUrl);
        if(obj.code == "0") {      
            self.$message({
          message: '添加成功！',
          type: 'success'
        });
        } else {
         self.$message.error('添加失败');
        }
      });
      self.getcourse()
    },  
         // 文件上传
 handleAvatarSuccess(res, file) {
   console.log(file.response);
   if(file.response){
   var self=this;
        this.imageUrl = file.response;
      console.log(self.imageUrl);
   }
      },
      beforeAvatarUpload(file) {
        
        const isLt10M = file.size / 1024 / 1024 < 50;

     
        if (!isLt10M) {
          this.$message.error('上传文件大小不能超过 50MB!');
       }
        return  isLt10M; //
      },
   
        openDialog() {
      this.dialogFormVisible = true;
      this.tableData = {};
      
    },
            
      handleClick(row) {
        console.log(row);
      },

      //查询方法
      getcourse(shangchuanzhe) {
      var self = this;
      // self.$store.state.number=JSON.parse(self.$route.query.number);
     
   const cc=db.get('edit',cc);
      
      //调用ajax
      var url = "http://localhost:8888/schoolms/pingshi/getpscjss";
      var locjrili = {

      shangchuanzhe:cc
      }; 
       //console.log(cc);
      this.axios.post(url, locjrili).then(function(resp) {
      // alert(resp.data);
        self.tableDatas = resp.data; 
      });
    },
    //删除方法
      deletecourse(file) {
     
         var self = this;
      //调用ajax
      var url = "http://localhost:8888/schoolms/pingshi/delete";
      var score = {
       file:file
      };
      this.axios.post(url, score).then(function(resp) {
        //console.log(resp.data);
        if(resp.data.code=="0") {
             self.$message({
          message: '删除成功！',
          type: 'success'
        });
          //重新查询数据，更新页面
        
        } else {
          alert("删除失败");
        }
      });
       self.getcourse();
    },
   updatescore(file) {
      var self = this;
         const cc=db.get('edit',cc);
       self.dialogFormVisible = false;
      var url = "http://localhost:8888/schoolms/pingshi/update";
      var locscore = {
       file:file,
      
      };
       
      this.axios.post(url, locscore).then(function(resp) {
       var obj = resp.data;
        if(obj.code == "0") {      
            self.$message({
          message: '提交成功！',
          type: 'success'
          
        });
        } else {
          self.$message.error('添加失败');
        }
      });
      self.getcourse()
    
    },
    },
 mounted: function () {
      var vm = this
      vm.getcourse()
    },

  }
</script>
<style scoped>


.table-rili {
 height: 10px;
}

  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }


</style>
