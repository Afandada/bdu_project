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
       <el-button width="10px" @click="yulan(scope.row.file)"  type="text" size="small">文件预览</el-button>
         <el-button @click="downLoad(scope.row.file)"  type="text" size="small">下载</el-button>
         <el-button @click="deletecourse(scope.row.file)"  type="text" size="small">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
   <el-button @click="getcourse" >查询</el-button>
    
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
        ff:'',
        fileList: [],
  dialogFormVisible: false,
 formLabelWidth: '120px',
 imageUrl:'',
          response:'',
           imggg:'./static/word.jpg',

      }
    },
  created(){
   
      this.getcourse()
     
    },
     methods: {
 
       //文件下载
      downLoad(file) {
      var path = file;
      // path例如：D:\home\upload\da975319173641669a6fca212ffd272e\夕阳.jpg
      window.open("http://localhost:8888/schoolms/upload/api/documents/download?path=" + encodeURI(path));
    },

   //文件预览
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
      //删除方法
      deletecourse(file) {
     
         var self = this;
         
      //调用ajax
      var url = "http://localhost:8888/schoolms/jrili/delete";
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
      //查询方法
      getcourse() {
      var self = this;
      // self.$store.state.number=JSON.parse(self.$route.query.number)
    
      //调用ajax
      var url = "http://localhost:8888/schoolms/jrili/getjrilishenhe";
      var locjrili = {

       state: '审核完成',
   
      };  
       //console.log(cc);
      this.axios.post(url, locjrili).then(function(resp) {
      // alert(resp.data);
        self.tableDatas = resp.data; 
      });
    },
     
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
