<template>
  <el-row class="main-content">
    <el-col class="left-content" :span="12">
      <el-row class="main-content" justify="center" align="middle">
        <el-col :span="24">
          <el-row justify="center"><div class="main-image"></div></el-row>
          <div class="explanation">자유롭게 글을 작성하거나 다른 사람들의 글을 볼 수 있습니다.</div>
          <span class="iconify" data-inline="false" data-icon="el:speaker" style="font-size: 100px;"></span>
<br>
<br>
<br>
<br>
<br>
<br>
<!-- 전 페이지로 돌아가기 시작 -->
<el-row @click="$router.go(-1)">
            <span class="iconify" data-inline="false" data-icon="akar-icons:arrow-back-thick-fill" style="color: #f88d8d; font-size: 111px;" ></span>
            <span class="previouspage">전 페이지로 돌아가기</span>
</el-row>
<!-- 전 페이지로 돌아가기 끝 -->
        </el-col>
      </el-row>
    </el-col>

    <el-col class="right-content" :span="12">
        <el-row class="main-content" justify="center" align="middle">
        <el-col :span="24">
          <div class="question">글을 작성해 보세요.</div>
          <el-row justify="cneter">


            <el-container class="board-container">
            <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">
            <el-form-item label="제목" prop="pass">
                  <el-input placeholder="제목을 입력해 주세요." v-model="state.form.boardTitle"></el-input>
            </el-form-item>
            <el-form-item label="내용" prop="checkPass">
                      <el-input
                      type="textarea"
                      :rows="2"
                      placeholder="내용을 입력해 주세요."
                      v-model="state.form.boardContents">
                    </el-input>
            </el-form-item>
            <el-form-item>
                <el-button round @click="clickSubmit">등록</el-button> <!--  @click="submitForm('ruleForm')" -->
                <el-button @click="$router.push('/board')">취소</el-button> <!-- @click="resetForm('ruleForm')" -->
            </el-form-item>
            </el-form>
            </el-container>
          </el-row>
         
        </el-col>
        </el-row>   
     
    </el-col>
  </el-row>
</template>

<script>

  import { defineComponent } from 'vue'

  import { reactive } from 'vue'
  import { useStore } from 'vuex'
  import { useRouter } from 'vue-router'
 
export default defineComponent ({
  name: 'BoardWrite',
   setup() {
   
    const store = useStore()
    const router = useRouter()
    const state = reactive({
      form :{
      userId: store.getters['root/getMyId'],
      boardTitle :'',
      boardContents :'',
      

      },
    })
    console.log(state.form.user)
    console.log(state.form.boardTitle)
    console.log(state.form.boardContents)

    const clickSubmit= () => {
      store.dispatch('root/requestSubmitWrite', state.form)
      .then(result =>{
        console.log(result)
        
        router.push({
          name : 'Board'
        })
      })
       .catch((err) => {
            store.commit('root/loadingOff')
            alert(err)
          })
    }
    
    return { state, clickSubmit }
  }
})

</script>

<style scoped>
  input[type="checkbox"]{
    width: 30px; /*Desired width*/
    height: 30px; /*Desired height*/
  }
  label{
    font-family: BlackHanSans;
    font-size: 30px;
    text-align: left;
  }
  .dementia-container {
    /* width : 100%; */
    height: 670px;
    padding: 20px;
    margin: 50px;
    background: #EFDEDE !important;
    line-height: 300%;
  }

  .el-button {
    margin: 15px;
    width: 150px;
    height: 50px;
    background: #EBC86F !important;
    border-radius: 25px !important;
  }
  .bottom-button {
    font-family: BlackHanSans;
    font-size: 30px;
  }
  .main-content {
    height: 100%;
  }
  .right-content {
    position: relative;
    background: rgba(173, 203, 176, 0.7);
    height: 140%;
    /* opacity: 0.5; */
  }
  .question {
    font-family: SangSangFlowerRoad;
    font-size: 44px;
    color: rgba(248, 141, 141, 1);
  }
  .explanation {
    font-family: SangSangFlowerRoad;
    font-size: 48px;
    text-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  }
  .main-image {
    height: 442px;
    width: 700px;
    margin: 0 auto;
    background-size: contain;
    background-repeat: no-repeat;
    background-image: url('../../../assets/images/main.png');
  }
  /* 이전 페이지로 돌아가기 css */
  .previouspage {
    font-family: SangSangFlowerRoad;
    font-size: 45px;
   color: rgba(248, 141, 141, 1);
  }
.board-container {
    /* width : 100%; */
    height: 670px;
    padding: 20px;
    margin: 50px;
    background: #EFDEDE !important;
    line-height: 300%;
  }
</style>
