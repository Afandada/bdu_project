<template>
<div>
<div class="table-jxdg">
  <el-table
    :data="tableDatas"
    border
    style="width: 100%" >
    <el-table-column
      fixed
      prop="coursename"
      label="课程名称(全称)"
      width="150"
      align=center>
    </el-table-column>
    <el-table-column
      prop="teacher"
      label="任课教师"
      width="120"
      align=center>
    </el-table-column>
   
    <el-table-column
      prop="zhuanye"
      label="专业"
      width="120"
      align=center>
    </el-table-column>
    <el-table-column
      prop="zqianzi"
      label="主任签字"
      width="120"
      align=center>
       <template slot-scope="scope">
      <img width="100px" height="50px" :src="scope.row.zqianzi">
    </template>
    </el-table-column>
    <el-table-column
      prop="yqianzi"
      label="院长签字"
      width="300"
      align=center>
       <template slot-scope="scope">
      <img width="100px" height="50px" :src="scope.row.yqianzi">
    </template>
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
  
     <el-button @click="openDialog" >添加文件</el-button>
   <el-button @click="getcourse" >查询</el-button>
   
</div>
 <el-dialog title="添加文件" :visible.sync="dialogFormVisible">
      <el-form>
        <el-form-item label="课程" :label-width="formLabelWidth">
          <el-input v-model="tableData.coursename" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="教师" :label-width="formLabelWidth">
          <el-input v-model="tableData.teacher" autocomplete="off"></el-input>
        </el-form-item>
         <el-form-item label="专业" :label-width="formLabelWidth">
          <el-input v-model="tableData.zhuanye" autocomplete="off"></el-input>
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

           
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page.sync="currentPage"
      :page-sizes="[10, 20, 30, 40]"
      :page-size="size"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total">
    </el-pagination>
 
</div>
</template>

<script>
import db from '@/components/localstorage'
  export default {   
    name:"Jiaoxuedagang",
    data() {
      return {
        
      tableDatas: [],
       tableData: {
        coursename: '',
        teacher: '',
        zhuanye:'',
        zqianzi:'',
        yqianzi:'',
        file:'',
         state:'',
         shangchuanzhe:''
      },  
       dialogFormVisible: false,
        dialogImageUrl: '',
        dialogVisible: false,
         fileList: [],
        currentPage:1,
        size:10,
        total:10,
        imageUrl:'',
      formLabelWidth: '120px',
      imggg:'./static/word.jpg',
      }
    },
    created(){
       this.getcourse()
      
    //this.findByPage(this.currentPage, this.size);
    },
     methods: {
     
       //分页查询
      findByPage(currentPage, size) {
          var self = this;
        this.axios.get('http://localhost:8888/schoolms/jxdg/pageInfo.do?pageNum='+this.currentPage+'&rows='+this.size).then(function(resp) {
        self.tableDatas=resp.data.rows;
        self.total=resp.data.total;    
      });
       }, //pageSize 改变时触发的函数
        handleSizeChange(val) {
            this.findByPage(this.currentPage, val);
        },
        //当前页改变时触发的函数
        handleCurrentChange(val) {
            this.findByPage(val, this.size);
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
       
            
      handleClick(row) {
        console.log(row);
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
      deletecourse(file) {
     
         var self = this;
         
      //调用ajax
      var url = "http://localhost:8888/schoolms/jxdg/delete";
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
//更新方法
 updatescore(file) {
      var self = this;
     
       self.dialogFormVisible = false;
      var url = "http://localhost:8888/schoolms/jxdg/update";
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
    /*查询方法 */
//查询方法
      getcourse(shangchuanzhe) {
      var self = this;
      // self.$store.state.number=JSON.parse(self.$route.query.number);  
     const cc=db.get('edit',cc); 
      //调用ajax
      var url = "http://localhost:8888/schoolms/jxdg/getjxdgss";
      var locjrili = {
   coursename:self.tableData.coursename,
        teacher:self.tableData. teacher,
        zhuanye: self.tableData.zhuanye,
        zqianzi: self.tableData.zqianzi,
        yqianzi: self.tableData. yqianzi,
        file: self.tableData.file,
       state: self.tableData.state,
      shangchuanzhe:cc
      };
      this.axios.post(url, locjrili).then(function(resp) {
      // alert(resp.data);
        self.tableDatas = resp.data;
      });
    },
     openDialog() {
      this.dialogFormVisible = true;
      this.tableData = {}; 
    },
     
     addscore() {
      var self = this;
        const cc=db.get('edit',cc); 
       this.dialogFormVisible = false;
      var url = "http://localhost:8888/schoolms/jxdg/add";
      var locscore = {
        coursename:self.tableData.coursename,
        teacher:self.tableData. teacher,
        zhuanye: self.tableData.zhuanye,
        zqianzi: self.tableData.zqianzi,
        yqianzi: self.tableData. yqianzi,
        file: self.imageUrl,
       shangchuanzhe:cc
      };
      this.axios.post(url, locscore).then(function(resp) {
       var obj = resp.data;
        if(obj.code == "0") {      
            this.$message({
          message: '添加成功！',
          type: 'success'
        });
        } else {
          this.$message.error('添加失败');
        }
      });
      self.getcourse(shangchuanzhe)
    },
    },
 
  }
</script>
<style scoped>



.table-xdg {
 height: 60px;
}


</style>
