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
  <el-row>
    <el-col :span="12" style="text-align:right">
          <div>
          <span class="question">{{state.form.boardTitle}}</span>
          </div>
    </el-col>
    <el-col :span="12">
<div v-if="state.nowuserId==state.form.user.userId">
          <el-button round @click="clickModify" >수정</el-button>
</div>
    </el-col>
  </el-row>
          <el-row justify="cneter">

            <el-container class="board-container">
              <el-header>
                
               
                  
                  <span class="text" style="text-align:left"> 작성자 : {{state.form.user.userName}}</span>
              
                 <span class="text"  style="text-align:right"> 작성일자 :{{state.form.boardDate}} </span>
              
              </el-header>
              <el-main>
                  <p class="text" style="text-align:left">{{state.form.boardContents}}</p>
              </el-main>
              <el-main>
                <comment :boardId="state.boardId" :commentsDto="state.form.commentsDto" :titles="state.titles" /> <!-- boardId="부모컴포넌트에서 던져준 보드 아이디" -->
              </el-main>
            </el-container>
          </el-row>
      
        </el-col>
        </el-row>      
    </el-col>
  </el-row>
</template>

<script>
  import comment from './components/comment.vue'
  import BackButton from '../../components/BackButton.vue'
  // import { defineComponent } from 'vue'

  import { reactive } from 'vue'
  import { useStore } from 'vuex'
  import { useRouter } from 'vue-router'
 
export default ({ 
  name: 'BoardDetail',

        props: {
            id: {
                type: Number,
                default: 0
            },
            // title: {
            //     type: String,
            //     default : ''
            // },
            // writer :{
            //     type: String,
            //     default : ''
            // },
            // date :{
            //      type: Date,
            //      default : ''
            // },
            // contents:{
            //      type: String,
            //      default : ''
            // },
        },
  components: {
   comment, BackButton,
  },
  setup(props) { // props.으로 props에 선언한 변수에 접근가능함
    const store = useStore()
    const router = useRouter()
    const state = reactive({
      titles : [1,3,5,7],
      boardId : props.id,
      nowuserId : store.getters['root/getMyId'],
      form :{
        commentsDto:[],
        user:{},
        boardTitle :'', 
        boardContents : '',
        boardDate :'',
      },
      // commentDto:[],
    })
    console.log(state.nowuserId);
   

    // console.log("props방식"+state.boardId); // props방식으로 넘어오는지

    // state.form.boardId = 0;
    // console.log("초기화"+state.form.boardId);
    // console.log(router.params);
    // this.info.id = this.$route.params.id;
    // state.form.boardId = router.params.id;
    // state.form.boardId = router.params.bId; // router => params방식으로 변수 넘기기
    // state.form.boardId = this.$route.params.id;
    // state.form.boardId = router.params.id;
    // console.log("params방식"+state.form.boardId); // params방식으로 넘어오는지
    
    console.log(props.id);
    console.log(state.boardId); 

    //  const userId = store.getters['root/getMyId']
     store.dispatch('root/requestReadBoard',{boardId : state.boardId }) // #{state.boardId} // userId : userId, //  `{boardId}?boardId=${state.boardId}`
     .then(res => {
      // console.log(res.data);
      // console.log(res.data.data);
      state.form = res.data.data; // form에다가 백에서 받아온 값들 넣어주자.
      // console.log(state.form.user.userName);
      // console.log(state.form.boardTitle);
      // console.log(state.form.boardContents);
      // console.log(state.form.boardDate);
      console.log(state.form.commentsDto); // 잘찍힘
      // console.log(state.nowuserId);
      // console.log(state.form.user.userId);
     
      })
      .catch((err) => {
        console.log(err);
      })

  
   


    const clickModify= () => {
      router.push({
        name: 'BoardModify',
         params: {
            id:state.boardId,
            title:state.form.boardTitle,
            contents:state.form.boardContents,
            // writer:val.boardWriter,
        }
      })
      // params : 제목, 콘텐츠 넘겨주기
    }
    return {state, clickModify }
  }
})

</script>

<style scoped>
.text{
    font-family: BlackHanSans;
    font-size: 30px;
}
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
    text-align: center;
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
