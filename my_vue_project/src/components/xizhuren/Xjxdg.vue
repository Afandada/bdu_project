<template>
<div>
<div class="table-jxdg">
  <el-table
    :data="tableDatas"
    border
    style="width: 100%" height="600px">
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
          <el-button @click="openDialog(scope.row.file)"  type="text" size="small">审批</el-button>
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
        cc:0,
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
        imageUrl: '',
       imggg:'./static/word.jpg',
         fileList: [],
        currentPage:1,
        size:10,
        total:10,
      formLabelWidth: '120px',
      ff:'',
      }
    },
    created(){
       this.getcourse()
      
    //this.findByPage(this.currentPage, this.size);
    },
     methods: {
       
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
     openDialog(file) {
          this.ff=file;
      this.dialogFormVisible = true;
      this.tableData = {};
   
    },
    //审核
      handleAvatarSuccess(res, file) {
         console.log(file.response);
        this.imageUrl =file.response;
      },
    
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
      getcourse() {
      var self = this;
       const cc=db.get('edit',cc); 
      //调用ajax
      var url = "http://localhost:8888/schoolms/jxdg/getjxdgshenhe";
      var locjrili = {
   
       state: '待系主任审核',
      
      };
      
      this.axios.post(url, locjrili).then(function(resp) {
      
        self.tableDatas = resp.data;
      });
    },  
   
      //审核
     
      bohui() {
      var self = this;
      console.log(self.ff)
       this.dialogFormVisible = false;
      var url = "http://localhost:8888/schoolms/jxdg/update";
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
     
       console.log(self.ff)
      var url = "http://localhost:8888/schoolms/jxdg/update";
      var locscore = {
        zqianzi:self.imageUrl,
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
 
  }
</script>
<style scoped>



.table-xdg {
 height: 60px;
}


</style>
