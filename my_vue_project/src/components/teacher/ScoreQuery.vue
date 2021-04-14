<template>
  <el-row>
    <div>
      <span>
        <el-input placeholder="请输入学号" v-model="score.number" style="width:200px" clearable=""></el-input>
        <el-button @click="getscore">查询</el-button>
    
      </span>
    </div>
   
    <el-table
      :data="scores"
      style="width: 100%">
      <el-table-column placeholder="请输入学号" prop="number" label="学号" width="180"  show-number align=center> </el-table-column>
      <el-table-column placeholder="请输入语文" prop="chinese" label="语文" width="180" show-chinese align=center> </el-table-column>
      <el-table-column placeholder="请输入数学" prop="math" label="数学" width="180" show-math align=center> </el-table-column>
      <el-table-column placeholder="请输入英语" prop="english" label="英语" width="180" show-english align=center> </el-table-column>
    </el-table>
    
<div>

        <el-upload
                action=""
                :auto-upload='false'
                :file-list="fileList"
                :on-change="handleChange"
        >
        
        <!-- action 我这里演示手动上传就不需要选择服务器地址,但是该参数必须要,不然会报错 -->
        <!-- :auto-upload='false' 改属性默认为 true 会默认直接提交文件 我这边手动提交所以选择 false-->
        <!-- :file-list="fileList" 官网上是上传的文件列表, 我这边为了单文件,实现第二次选择上传清空-->
        <!-- :on-change="handleChange" 文件状态改变时的钩子函数 -->
        <!-- 你可以自己测试完成之后,添加更多的样式 -->
        
        	<!--该按钮选取文件 是el-upload组件的一个插槽,不添加改方法你不能选取文件-->
            <el-button slot="trigger" type="primary" @click="delFile">选取文件</el-button>
            <!--单击上传到服务器(提交)按钮 -->
            <el-button type="success" @click="submitUpload">上传到服务器</el-button>
        </el-upload>
    </div>

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
fileList: []
    }
  },

  methods: {
    

/*重新选择的时候清楚原来的文件*/
           /* */
           delFile() {
                this.fileList = [];
            },
            /*文件状态改变时的钩子函数*/
            handleChange(file, fileList) {//该方法很重要,从你选择的fileList文件赋值给你定义的this.fileList
                this.fileList = fileList;
            },
            /*单击上传到服务器(提交)按钮*/
            submitUpload() {
                let formData = new FormData();//文件转换
                //fileList 对应你服务器接受名称
                formData.append("fileList", this.fileList[0].raw);
                //必须是post来请求,
                //formData请求带的数据
                //{"Content-Type": "multipart/form-data;charset=utf-8"}是文件上传必须要的
                //最后可以打印返回的服务器返回的数据,如果控制台报错接受你哪里没有写对
                this.$http.post('http://localhost:8888/schoolms/tscore/fileUpload', formData, {"Content-Type": "multipart/form-data;charset=utf-8"}).then(res => {
                    console.log(res)
                     this.$message({
          message: '上传成功！',
          type: 'success'
        });
                })
  
},  

    getscore() {
      var self = this;
      //调用ajax
      var url = "http://localhost:8888/schoolms/tscore/getscore";
      var locscore = {
        number:self.score.number,
        chinese:self.score.chinese,
        math:self.score.math,
        english: self.score.english,
      };
      this.axios.post(url, locscore).then(function(resp) {
      // alert(resp.data);
        self.scores = resp.data;
      });
    },
        
  
    }
  }

</script>

<style scoped>
</style>