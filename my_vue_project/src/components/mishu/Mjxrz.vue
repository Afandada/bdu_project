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
    </el-table-column>
     <el-table-column
      prop="zqianzi"
      label="主任签名"
      width="120"
      align=center>
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
         imggg:'./static/word.jpg',

      }



    },
   
     methods: {
        openDialog() {
      this.dialogFormVisible = true;
      this.tableData = {};
      
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
     
      
      getcourse() {
      var self = this;
       const cc=db.get('edit',cc); 
      //调用ajax
      var url = "http://localhost:8888/schoolms/jxrz/getjxrzshenhe";
      var locjrili = {
      
       state:"审核完成",
    
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
