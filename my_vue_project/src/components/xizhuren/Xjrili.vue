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
      prop="teacher"
      label="任课教师"
      width="120"
       height="120"
      align=center>
    </el-table-column>
  
    <el-table-column
      prop="zhicheng"
      label="职称"
      width="120"
       height="120"
      align=center>
    </el-table-column>
    <el-table-column
      prop="xueyuan"
      label="二级学院"
      width="120"
       height="120"
      align=center>
    </el-table-column>
    <el-table-column
      prop="y_qianzi"
      label="院长签字"
      width="300"
       height="120"
      align=center>
      <template slot-scope="scope">
      <img width="100px" height="50px" :src="scope.row.y_qianzi">
    </template>
    </el-table-column>
    <el-table-column
      prop="z_qianzi"
      label="主任签字"
      width="120"
       height="120"
      align=center>
          <template slot-scope="scope">
      <img width="100px" height="50px" :src="scope.row.z_qianzi">
    </template>
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
      prop="state"
      label="状态"
      width="120"
       height="120"
      align=center>
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
       <el-button @click="openUpdate(scope.row.file)"  type="text" size="small">审批</el-button>
         <el-button width="10px" @click="yulan(scope.row.file)"  type="text" size="small">文件预览</el-button>
      </template>
    </el-table-column>
  </el-table>
   <el-button @click="getcourse" >查询</el-button>
<el-dialog title="资料审批" :visible.sync="dialogFormVisible"  align=center>   
         <el-form>
     <el-form-item label="电子签名(点击上传)" align=center>         
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
        <el-button type="primary" @click="updatescore">通过</el-button>
         <el-button type="primary" @click="bohui">驳回</el-button>
      </div>
</el-dialog>

</div>
</div>
</template>
<script>
  export default {
   name: "test", 
    data() {
      return {
   tableDatas: [],
     tableData: {
        coursename: '',
        teacher: '',
        zhicheng:'',
        xueyuan:'',
        y_qianzi:'',
        z_qianzi:'',
        classname:'',
        file:'',
         state:'',
     shangchuanzhe:''
      },
        fileList: [],
         imageUrl: '',
          dialogFormVisible: false,
          formLabelWidth: '120px',
          response:'',
         ff:'',
          imggg:'./static/word.jpg',
      }
    },
  created(){
   
      this.getcourse()
     
    },
     methods: {
      yulan(file){
 //window.open ("http://view.xdocin.com/xdoc?_xdoc="+file)

  var self = this;
  //self.table=true;
  var loc=file;
    console.log(loc)
      var url = "http://localhost:8888/schoolms/yulan/yl";
    
      this.axios.post(encodeURI(url), loc).then(function(resp) {
     // this.axios.post(url, loc).then(function(resp) {
       var obj = encodeURI(resp.data);
         self.pdfUrl=encodeURI(resp.data);
           console.log("dddddddddddddddddddddddddddd")
         console.log(obj)
        window.open(`/static/web/viewer.html?file=${obj}`);
       
        // self.getNumPages()
        if(obj!="") {      
            self.$message({
          message: '预览成功！',
          type: 'success'
        });
        } else {
         self.$message.error('预览失败！');
        }
      });
    
},
         // 审批
 handleAvatarSuccess(res, file) {
   console.log(file.response);
   if(file.response){
   var self=this;
        this.imageUrl = file.response;
      console.log(this.imageUrl);

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
    
         openUpdate(file) {
         this.ff=file;
      this.dialogFormVisible = true;
      this.tableData = {};
      
    },
            
      //查询方法
      getcourse() {
      var self = this;
      // self.$store.state.number=JSON.parse(self.$route.query.number)
      
      //调用ajax
      var url = "http://localhost:8888/schoolms/jrili/getjrilishenhe";
      var locjrili = {
        coursename:self.tableData.coursename,
        teacher:self.tableData. teacher,
        zhicheng:self.tableData.zhicheng,
        xueyuan: self.tableData.xueyuan,
         y_qianzi: self.tableData. y_qianzi,
        z_qianzi: self.tableData.z_qianzi,
        classname: self.tableData.classname,
        file: self.tableData.file,
       state: '待系主任审核',
      shangchuanzhe:self.tableData.shuangchuanzhe,
      };  
       //console.log(cc);
      this.axios.post(url, locjrili).then(function(resp) {
      // alert(resp.data);
        self.tableDatas = resp.data; 
      });
    },
     bohui() {
      var self = this;
       this.dialogFormVisible = false;
      var url = "http://localhost:8888/schoolms/jrili/update";
      var locscore = {
        file: self.ff,
       state: '不通过，请重新上传',
          
      };
       
      this.axios.post(url, locscore).then(function(resp) {
       var obj = resp.data;
        if(obj.code == "0") {      
           self.$message({
          message: '已驳回！',
          type: 'success'
          
        });
        } else {
          self.$message.error('驳回失败');
        }
      });
      self.getcourse()
    
    },
  
   updatescore() {
      var self = this;
       this.dialogFormVisible = false;
      var url = "http://localhost:8888/schoolms/jrili/update";
      var locscore = {
         z_qianzi: self.imageUrl,
        file: self.ff,
       state:'待院长审核',
         
      };
       
      this.axios.post(url, locscore).then(function(resp) {
       var obj = resp.data;
        if(obj.code == "0") {      
            self.$message({
          message: '审核成功！',
          type: 'success'

        });
        } else {
          self.$message.error('审核失败');
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
