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
          <div>
          <span class="question">{{state.form.boardTitle}}</span>
          </div>
<div v-if="state.nowuserId==state.form.user.userId">
          <el-button round @click="clickModify" >수정</el-button>
</div>
          <el-row justify="cneter">

            <el-container class="board-container">
              <el-header>
                <el-row>
                <!-- <el-col span="12" style="inline-block"> -->
                  <span class="text" style="margin-right: 10% text-align:left"> 작성자 : {{state.form.user.userName}}</span>
                <!-- </el-col> -->
                <!-- <el-col span="12" style="inline-block"> -->
                 <span class="text"> 작성일자 :{{state.form.boardDate}} </span>
                <!-- </el-col> -->
                </el-row>
              </el-header>
              <el-main>
                  <p class="text">{{state.form.boardContents}}</p>
              </el-main>
              <el-main>
                <comment title="부모컴포넌트에서 던져준 페이지 타이틀" />
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
   comment,
  },
  setup(props) { // props.으로 props에 선언한 변수에 접근가능함
    const store = useStore()
    const router = useRouter()
    const state = reactive({
      boardId : props.id,
      nowuserId : store.getters['root/getMyId'],
      form :{
        comments:[],
        user:{},
        boardTitle :'',
        boardContents : '',
        boardDate :'',
      }
    
    })
    console.log(state.nowuserId);
   

    console.log("props방식"+state.boardId); // props방식으로 넘어오는지

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
    // console.log(state.id); 

    //  const userId = store.getters['root/getMyId']
     store.dispatch('root/requestReadBoard',{boardId : state.boardId }) // #{state.boardId} // userId : userId, //  `{boardId}?boardId=${state.boardId}`
     .then(res => {
      console.log(res.data);
      console.log(res.data.data);
      state.form = res.data.data;
      console.log(state.form.user.userName);
      console.log(state.form.boardTitle);
      console.log(state.form.boardContents);
      console.log(state.form.boardDate);

      console.log(state.nowuserId);
      console.log(state.form.user.userId);
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
  input[type="checkbox"]{
    width: 30px; /*Desired width*/
    height: 30px; /*Desired height*/
  }
  .text{
    font-family: BlackHanSans;
    font-size: 30px;
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
