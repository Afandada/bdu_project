<template>
  <el-row>
    <div>
      <span>
        <el-input placeholder="请输入学号" v-model="score.number" style="width:200px" clearable=""></el-input>
        <el-button @click="getscore">查询</el-button>
        <el-button @click="openDialog">新增</el-button>
      </span>
    </div>
    <el-table
      :data="scores"
      style="width: 100%">
      <el-table-column placeholder="请输入学号" prop="number" label="学号" width="180" align=center> </el-table-column>
      <el-table-column placeholder="请输入语文" prop="chinese" label="语文" width="180" show-chinese align=center> </el-table-column>
      <el-table-column placeholder="请输入数学" prop="math" label="数学" width="180" show-math align=center> </el-table-column>
      <el-table-column placeholder="请输入英语" prop="english" label="英语" width="180" show-english align=center> </el-table-column>
      <el-table-column label="管理" width="200" align=center>
        <template slot-scope="scope">
          <el-button @click="openUpdate(scope.row)" type="text" size="small">修改</el-button>
          <el-button @click="delscore(scope.row.number)"  type="text" size="small">删除成绩</el-button>
        </template>
      </el-table-column>
    </el-table>

       <!--对话框-->
    <el-dialog title="成绩管理" :visible.sync="dialogFormVisible">
      <el-form>
        <el-form-item label="学号" :label-width="formLabelWidth">
          <el-input v-model="score.number" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="语文" :label-width="formLabelWidth">
          <el-input v-model="score.chinese" autocomplete="off"></el-input>
        </el-form-item>
         <el-form-item label="数学" :label-width="formLabelWidth">
          <el-input v-model="score.math" autocomplete="off"></el-input>
        </el-form-item>
         <el-form-item label="英语" :label-width="formLabelWidth">
          <el-input v-model="score.english" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button  @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="updatescore">修改</el-button>
        <el-button type="primary" @click="addscore">加入成绩</el-button>
      </div>
    </el-dialog>

  </el-row>
</template>

<script>

export default {
  data() {
    return {
      scores: [],
      score: {
        number: '',
        chinese: '',
        math:'',
        english:'',
      },
      listQuery: {
        page: 1,
        limit: 10,
        
      },
      //对话框是否可见
      dialogFormVisible: false,
     
      formLabelWidth: '120px',
      
    }
  },
  methods: {
    getscore() {
      var self = this;
      //调用ajax
      var url = "http://localhost:8888/schoolms/tscore/getscore";
      var locscore = {
        number:self.score.number,
        chinese:self.score.chinese,
        math: self.score.math,
        english: self.score.english,
      };
      this.axios.post(url, locscore).then(function(resp) {
        //alert(resp.data);
        self.scores = resp.data;
      });
    },
         pagechange(){

         },
    delscore(number) {
      var self = this;
      //调用ajax
      var url = "http://localhost:8888/schoolms/tscore/delete";
      var score = {
        number:number
      };
      this.axios.post(url, score).then(function(resp) {
        //console.log(resp.data);
        if(resp.data.code=="0") {
          alert(resp.data.msg);
          //重新查询数据，更新页面
          self.getscore();
        } else {
          alert("删除失败");
        }
      });
    },
    openDialog() {
      this.dialogFormVisible = true;
      this.score = {};
      
    },
    addscore() {
      var self = this;
       this.dialogFormVisible = false;
      var url = "http://localhost:8888/schoolms/tscore/add";
      var locscore = {
        number:self.score.number,
        chinese:self.score.chinese,
        math: self.score.math,
        english: self.score.english,
      };
      this.axios.post(url, locscore).then(function(resp) {
       var obj = resp.data;
        if(obj.code == "0") {
         
          self.getscore();
          
        alert("test");
        } else {
          this.$message.error('添加失败');
        }
      });
    },
    openUpdate(score) {
      this.dialogFormVisible = true;
      this.score = score;
    },
    updatescore() {
       var self = this;
       this.dialogFormVisible = false;
      var url = "http://localhost:8999/schoolms/tscore/update";
      var locscore = {
        number:self.score.number,
        chinese:self.score.chinese,
        math: self.score.math,
        english: self.score.english,
      };
      this.axios.post(url, locscore).then(function(resp) {
       var obj = resp.data;
        if(obj.code == "0") {
         
          self.getscore();
          
       self.$message({
          message: '修改成功',
          type: 'success'
        });
        } else {
          this.$message.error('修改失败');
        }
      });
    },
  
    }
  }

</script>

<style scoped>
</style>