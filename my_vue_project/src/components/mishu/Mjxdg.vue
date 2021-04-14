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
    </el-table-column>
    <el-table-column
      prop="yqianzi"
      label="院长签字"
      width="300"
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
        <el-button @click="deletecourse(scope.row.file)"  type="text" size="small">删除</el-button>
          <el-button @click="downLoad(scope.row.file)"  type="text" size="small">下载</el-button>
      </template>
    </el-table-column>
  </el-table>
   <el-button @click="getcourse" >查询</el-button>
</div>       
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

 
 //删除方法
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
    /*查询方法 */
//查询方法
      getcourse(shangchuanzhe) {
      var self = this;
      // self.$store.state.number=JSON.parse(self.$route.query.number);  
     const cc=db.get('edit',cc); 
      //调用ajax
      var url = "http://localhost:8888/schoolms/jxdg/getjxdgshenhe";
      var locjrili = {
   coursename:self.tableData.coursename,
       state:"审核完成"
      };
      this.axios.post(url, locjrili).then(function(resp) {
      // alert(resp.data);
        self.tableDatas = resp.data;
      });
    },}
     }
</script>
<style scoped>



.table-xdg {
 height: 60px;
}


</style>
