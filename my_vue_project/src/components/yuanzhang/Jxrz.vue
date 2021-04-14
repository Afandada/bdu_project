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
      <img width="100px" height="50px"   :src="scope.row.yqianzi">
    </template>
    </el-table-column>
     <el-table-column
      prop="zqianzi"
      label="主任签名"
      width="120"
      align=center>
      <template slot-scope="scope">
      <img width="100px" height="50px"   :src="scope.row.zqianzi">
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
       <el-button @click="openUpdate(scope.row.file)"  type="text" size="small">审批</el-button>
         <el-button width="10px" @click="yulan(scope.row.file)"  type="text" size="small">文件预览</el-button>
      </template>
    </el-table-column>
  </el-table>
   <el-button @click="getcourse" >查询</el-button>
</div>
<el-dialog title="资料审批" :visible.sync="dialogFormVisible">   
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
</template>

<script>
import db from '@/components/localstorage'
  export default {
   

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
      ff:'',
        fileList: [],
  dialogFormVisible: false,
        imggg:'./static/word.jpg',
 formLabelWidth: '120px',
 imageUrl:''
      }



    },
   
     methods: {
      
     handleAvatarSuccess(res, file) {
         console.log(file.response);
        this.imageUrl =file.response;
      },
     addscore() {
      var self = this;
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
        file: self.tableData.file,
       state: self.tableData.state,
        
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
      self.getcourse()
    },
       //文件上传
 handleRemove(file, fileList) {
        //console.log(file, fileList);
        alert(file, fileList);
      },
      handlePreview(file) {
        console.log(file);
         alert(file);
      },
      handleExceed(files, fileList) {
        this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
      },
      beforeRemove(file, fileList) {
        return this.$confirm(`确定移除 ${ file.name }？`);
      },

      handleClick(row) {
        console.log(row);
      },
      getcourse() {
      var self = this;
       const cc=db.get('edit',cc); 
      //调用ajax
      var url = "http://localhost:8888/schoolms/jxrz/getjxrzshenhe";
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
       state: '待院长审核',
        shangchuanzhe: self.tableData.shangchuanzhe
      };
      
      this.axios.post(url, locjrili).then(function(resp) {
      
        self.tableDatas = resp.data;
      });
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
    // 文件上传

  handleAvatarSuccess(res,file) {
    //this.tableData.file=res.data.url
    this.imageUrl=file.response;
    console.log(this.imageUrl);
   console.log("________________________")

      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
      },
      //审核
      openUpdate(file) {
         this.ff=file;
      this.dialogFormVisible = true;
      this.tableData = {};
      console.log(ff)
    },
      bohui() {
      var self = this;
      console.log(self.ff)
       this.dialogFormVisible = false;
      var url = "http://localhost:8888/schoolms/jxrz/update";
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
      var url = "http://localhost:8888/schoolms/jxrz/update";
      var locscore = {
        yqianzi:self.imageUrl,
        file: self.ff,
       state:'审核完成',
         
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
