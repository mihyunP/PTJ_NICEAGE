<template>
  <el-row class="main-content">
    <el-col class="left-content" :span="12">
      <el-row class="main-content" justify="center" align="middle">
        <el-col :span="24">
          <el-row justify="center"><div class="main-image"></div></el-row>
          <div class="explanation">자유롭게 글을 작성하거나 다른 사람들의 글을 볼 수 있습니다.</div>
          <span class="iconify" data-inline="false" data-icon="el:speaker" style="font-size: 100px;"></span>
<back-button/>
        </el-col>
      </el-row>
    </el-col>

    <el-col class="right-content" :span="12">
        <el-row class="main-content" justify="center" align="middle">
        <el-col :span="24">

          <div class="question">글을 수정해 보세요.</div>
          <el-row justify="cneter">


            <el-container class="board-container">
              <el-row justify="center">
            <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">
            <el-form-item label="제목" prop="pass">
              <el-input id="input-title" placeholder="제목을 입력해주세요." v-model="state.form.boardTitle"></el-input>
            </el-form-item>
            <el-form-item  label="내용" prop="checkPass">
                 <el-input
                      id="input-contents"
                      type="textarea"
                       :autosize="{ minRows: 20, maxRows: 20}"
                      placeholder="내용을 입력해주세요."
                      v-model="state.form.boardContents">
                    </el-input>
            </el-form-item>
            <el-form-item>
              <el-row justify="center" align="middle">
                <el-button @click="clickModify">수정</el-button>
                <el-button @click="clickDelete">삭제</el-button>
              </el-row>
            </el-form-item>
            </el-form>
              </el-row>
            </el-container>
          </el-row>
         
        </el-col>
        </el-row>   
     
    </el-col>
  </el-row>
</template>

<script>
  import { reactive } from 'vue'
  import { useStore } from 'vuex'
  import { useRouter } from 'vue-router'
import BackButton from '../../components/BackButton.vue'
 

export default {
  components: { BackButton },
  name: 'BoardModify',
   props: {
            id: {
                type: Number,
                default: 0
            },
            title: {
                type: String,
                default : ''
            },
            contents:{
                 type: String,
                 default : ''
            },
        },
   setup(props) {
    const store = useStore()
    const router = useRouter()
    const state = reactive({
      form :{
      userId: store.getters['root/getMyId'],
      boardTitle :props.title,
      boardContents :props.contents,
      boardId : props.id,
      },
    })
    
    console.log("md보드아이디"+state.form.boardId);

    const clickModify= () => {
      store.dispatch('root/requestUpdateWrite',state.form)
       .then(result =>{
        console.log(result)
        
        router.push({
          name : 'Board'
        })
      })
    }

    const clickDelete = () => {
        const userId = store.getters['root/getMyId']
         store.dispatch('root/requestDeleteWrite',{userId : userId, boardId : `{boardId}?boardId=${state.form.boardId}`}) // state.form.boardId
       .then(result =>{
        console.log(result)
        
        router.push({
          name : 'Board'
        })
      })
    }


    console.log(props.title);
    return { state, clickModify, useStore, clickDelete }
  }
}
</script>

<style scoped>
  .demo-ruleForm{
    width: 700px;
  }
  .el-form-item{
    font-family: BlackHanSans;
    font-size: 50px !important;
    text-align: left;
  }
  .el-button {
    font-family: BlackHanSans;
    font-size: 25px;
    margin: 15px;
    width: 150px;
    height: 50px;
    background: #EBC86F !important;
    border-radius: 25px !important;
  }
  .main-content {
    height: 100%;
  }
  .right-content {
    position: relative;
    background: rgba(173, 203, 176, 0.7);
    height: 100%;
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
.board-container {
    /* width : 100%; */
    height: 670px;
    padding: 20px;
    margin: 50px;
    background: #EFDEDE !important;
    line-height: 300%;
  }
</style>
