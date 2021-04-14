<template>
<div>
<div class="table-rizhi">
  <el-table
    :data="tableDatas"
    border
    style="width: 100%">
    <el-table-column
      fixed
      prop="xueyuan"
      label="学院"
      width="120"
      align=center>
    </el-table-column>
    <el-table-column
      prop="grade"
      label="年级"
      width="80"
      align=center>
    </el-table-column>
    <el-table-column
      prop="zhuanye"
      label="专业"
      width="120"
      align=center>
    </el-table-column>
<el-table-column
      prop="stunumber"
      label="学生人数"
      width="120"
      align=center>
    </el-table-column>
    <el-table-column
      prop="classname"
      label="班级名称"
      width="120"
      align=center>
    </el-table-column>
         <el-table-column
      prop="yqianzi"
      label="院长签名"
      width="120"
      align=center>
       <template slot-scope="scope">
      <img width="100px" height="50px" :src="scope.row.yqianzi">
    </template>
    </el-table-column>
     <el-table-column
      prop="zqianzi"
      label="主任签名"
      width="120"
      align=center>
     
 <template slot-scope="scope">
      <img width="100px" height="50px" :src="scope.row.zqianzi">
    </template>
    </el-table-column>
    <el-table-column
      prop="xueshi"
      label="学时"
      width="50"
      align=center>
    </el-table-column>
    <el-table-column
      prop="teacher"
      label="任课教师"
      width="120"
      align=center>
    </el-table-column>
    <el-table-column
      prop="file"
      label="文件"
      width="120"
      align=center>
      
 <img width="50px" height="60px"  :src="imggg">
    </el-table-column>
        <el-table-column
      prop="state"
      label="状态"
      width="120"
      align=center>
    </el-table-column>
      <el-table-column
      prop="shangchuanzhe"
      label="上传者"
      width="120"
      align=center>
    </el-table-column>
    <el-table-column
      fixed="right"
      label="操作"
      width="100"
      align=center>
      <template slot-scope="scope">
             <el-button width="10px" @click="yulan(scope.row.file)"  type="text" size="small">文件预览</el-button>
        <el-button  @click="updatescore(scope.row.file)"  type="text" size="small">提交</el-button>
       <el-button @click="deletecourse(scope.row.file)"  type="text" size="small">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
   <el-button @click="getcourse" >查询</el-button>
      <el-button @click="openDialog" >上传文件</el-button>       

 
</div>
<el-dialog title="添加文件" :visible.sync="dialogFormVisible">
      <el-form>
        <el-form-item label="学院" :label-width="formLabelWidth">
          <el-input v-model="tableData.xueyuan" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="年级" :label-width="formLabelWidth">
          <el-input v-model="tableData.grade" autocomplete="off"></el-input>
        </el-form-item>
                <el-form-item label="专业" :label-width="formLabelWidth">
          <el-input v-model="tableData.zhuanye" autocomplete="off"></el-input>
        </el-form-item>
            <el-form-item label="课程名称" :label-width="formLabelWidth">
          <el-input v-model="tableData.coursename" autocomplete="off"></el-input>
        </el-form-item>
    
        <el-form-item label="教师" :label-width="formLabelWidth">
          <el-input v-model="tableData.teacher" autocomplete="off"></el-input>
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
   <img v-if="imageUrl" :src="imageUrl" class="avatar">
  <el-button size="small" type="primary">点击上传</el-button>
  <div slot="tip" class="el-upload__tip">只能上传word文件</div>
</el-upload>
        </el-form-item>
      </el-form>
      
      <div slot="footer" class="dialog-footer">
        <el-button  @click="dialogFormVisible = false">取消</el-button>  
        <el-button type="primary" @click="addscore">添加文件</el-button>
      </div>
    </el-dialog>
     <el-dialog :visible.sync="dialogVisible">
        <img width="100%" :src="dialogImageUrl" alt="">
    </el-dialog>
</div>
</template>

<script>
import db from '@/components/localstorage'
  export default {
    methods: {
      handleClick(row) {
        console.log(row);
      }
    },

    data() {
      return {
        tableDatas: [],
     tableData: {
        xueyuan: '',
        grade: '',
        zhuanye:'',
        stunumber: '',
        classname: '',
        yqianzi:'',
        zqianzi:'',
        xueshi: '',
        teacher: '',
        file:'',
         state:'',
         shangchuanzhe:''
      },
        fileList: [],
  dialogFormVisible: false,
      
            dialogVisible: false,
 formLabelWidth: '120px',
 imageUrl:'',
  imggg:'./static/word.jpg',
      }



    },
   
     methods: {
        openDialog() {
      this.dialogFormVisible = true;
      this.tableData = {};
      
    },
  
     addscore() {
      var self = this;
      const cc=db.get('edit',cc); 
       this.dialogFormVisible = false;
      var url = "http://localhost:8888/schoolms/jxrz/add";
      var locscore = {
        xueyuan:self.tableData. xueyuan,
         grade: self.tableData. grade,
       zhuanye:self.tableData.zhuanye,
          stunumber: self.tableData. stunumber,
          classname: self.tableData.classname,
         yqianzi: self.tableData. yqianzi,
        zqianzi: self.tableData.zqianzi,
          xueshi:self.tableData. xueshi,
          teacher:self.tableData. teacher,
        file: self.imageUrl,
       state: self.tableData.state,
       shangchuanzhe:cc
        
      };
      this.axios.post(url, locscore).then(function(resp) {
       var obj = resp.data;
      
        window.open(self.imageUrl);
        if(obj.code == "0") {      
            self.$message({
          message: '添加成功！',
          type: 'success'
        });
        } else {
         self.$message.error('添加失败，请必须上传文件');
        }
      });
      self.getcourse()
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
      updatescore(file) {
      var self = this;
         const cc=db.get('edit',cc);

       self.dialogFormVisible = false;
      var url = "http://localhost:8888/schoolms/jxrz/update";
      var locscore = {
        file:file,
       state: '待系主任审核',

      }; 
      this.axios.post(url, locscore).then(function(resp) {
       var obj = resp.data;
        if(obj.code == "0") {      
            self.$message({
          message: '提交成功！',
          type: 'success'  
        });
        } else {
          self.$message.error('提交失败');
        }
      });
      self.getcourse()
    },
       //文件上传
 handleAvatarSuccess(res, file) {
   console.log(file.response);
   
   if(file.response){
   var self=this;
        self.imageUrl = file.response;
      console.log(self.imageUrl);
    
        
   }
      },
      beforeAvatarUpload(file) {
       // const isJPG = file.type === '.doc/.docx';
        const isLt10M = file.size / 1024 / 1024 < 10;

      // if (!isJPG) {
      //    this.$message.error('上传文件只能是 doc/docx 格式!');
     //   }
        if (!isLt10M) {
          this.$message.error('上传文件大小不能超过 10MB!');
       }
        return   isLt10M; //isJPG &&
      },
      openUpdate(tableData) {
      this.dialogFormVisible = true;
      this.tableData = tableData;
    },
        openDialog() {
      this.dialogFormVisible = true;
      this.tableData = {};
      
    },
            
      handleClick(row) {
        console.log(row);
      },
      getcourse() {
      var self = this;
       const cc=db.get('edit',cc); 
      //调用ajax
      var url = "http://localhost:8888/schoolms/jxrz/getjxrzss";
      var locjrili = {
         xueyuan:self.tableData. xueyuan,
         grade: self.tableData. grade,
       zhuanye:self.tableData.zhuanye,
          stunumber: self.tableData. stunumber,
          classname: self.tableData.classname,
         yqianzi: self.tableData. yqianzi,
        zqianzi: self.tableData.zqianzi,
          xueshi:self.tableData. xueshi,
          teacher:self.tableData. teacher,
        file: self.tableData.file,
       state: self.tableData.state,
        shangchuanzhe: cc
      };
      
      this.axios.post(url, locjrili).then(function(resp) {
      
        self.tableDatas = resp.data;
      });
    },  
    //删除方法
      deletecourse(file) {
     
         var self = this;
          const cc=db.get('edit',cc);
      //调用ajax
      var url = "http://localhost:8888/schoolms/jxrz/delete";
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


    },

    


   
mounted () { //这个属性就可以，在里面声明初始化时要调用的方法即可
      // we can implement any method here like
      this.getcourse()
    }
  }
</script>
<style scoped>



.table-rizhi {
 height: 60px;
}


</style>
