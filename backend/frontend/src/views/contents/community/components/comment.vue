<template>
    <el-form-item>
                  <el-input placeholder="댓글을 작성해주세요." v-model="state.form.commentContents"></el-input>
    </el-form-item>
    <el-form-item >
      <div>{{boardId}}</div>
    <el-button @click="clickWriteComment"> 댓글 달기</el-button>
    </el-form-item>
    <br>
    
    <!--   state.commentform.commentsDto   -->
    <el-table
    :show-header =false
    :data="pagedTableData" 
    style="width: 100%"
    @current-change="handleCurrentChange">
    <el-table-column
      prop="userName"
      width="180">
    </el-table-column>
    <el-table-column
      prop="commentContents"
      width="180">
    </el-table-column>
     <!-- <div v-if="state.commentList.userId=state.form.userId"> -->
    <el-table-column v-if="state.commentList.userId=state.form.userId"> <!--v-if 사용해서 댓글쓴사람 == 현재 로그인된 사람 일때만 보여주기-->
           
              <el-button @click="clickModifyComment" type="danger">수정</el-button>
            </el-table-column>
    <el-table-column v-if="state.commentList.userId=state.form.userId">
              <el-button @click="clickDeleteComment" type="danger">삭제</el-button>
    </el-table-column>
    <!-- </div> -->
  </el-table>
 
   <!-- 페이지네이션
      <el-pagination
      background
      :page-size="pageSize"
      layout=" prev, pager, next"
      :total="state.commentform.commentsDto.length"
      @current-change="setPage"> 
    </el-pagination>
     페이지네이션 끝 -->

</template>

<script>
import { reactive, computed } from 'vue'
import { useStore } from 'vuex'
// import { useRouter } from 'vue-router'


export default {
props: {
      boardId : {
        type : Number,
        default : 0,
      },
      commentsDto : { // 객체 배열
        type : Array,
      },
      titles : { // 객체 배열
        type : Array,
      }

},

setup(props) {
    // const router = useRouter()
    const store = useStore()
    const state = reactive({

      page: 1,
      pageSize: 4,
      currentRow: null,
      
      

      commentList :props.commentsDto, // 댓글 받아온거 (배열임)
      commentWriter : props.commentsDto.userId, // 댓글 작성자 (배열임)
      commentId :'',
      form : {
       userId : store.getters['root/getMyId'],
       boardId : props.boardId,
       commentContents : '', // v-model로 입력받음
       // commentId :'', 자동 증가?

      }, // 댓글 입력시 보낼 입력폼
      commentform : {
        commentsDto:[],
        user:{},
      }, // 댓글 받아오는 폼


      pagedTableData :computed(() => state.commentform.commentsDto.slice(state.pageSize * state.page - state.pageSize, state.pageSize * state.page),
      ),
    })

      

      console.log("보드 아이디");
      console.log(state.form.boardId);
      


       const setPage= function (val) {
        state.page = val
    }

     const handleCurrentChange = function (val) {
        state.currentRow = val;
   
          state.commentId = val.commentId 
    }


    console.log("PTD");
      console.log(state.pagedTableData);



      store.dispatch('root/requestReadBoard',{boardId : state.form.boardId }) // #{state.boardId} // userId : userId, //  `{boardId}?boardId=${state.boardId}`
     .then(res => {
      // console.log(res.data);
      // console.log(res.data.data);
      state.commentform = res.data.data; // form에다가 백에서 받아온 값들 넣어주자.
      // console.log(state.form.user.userName);
      // console.log(state.form.boardTitle);
      // console.log(state.form.boardContents);
      // console.log(state.form.boardDate);
      console.log(state.commentform.commentsDto); 
      // console.log(state.nowuserId);
      // console.log(state.form.user.userId);
     
      })
      .catch((err) => {
        console.log(err);
      })
  
  // console.log(props.title);

  // console.log(props.boardId);
  // console.log(state.form.boardId);
  console.log("커멘트디티오");
  console.log(props.commentsDto); 
  console.log(state.commentList); 
  console.log(props.commentsDto.userName);
  console.log("타이틀");
  console.log(props.titles);
   console.log("댓글 작성자");
  console.log(props.commentsDto.userId); 


  
    const clickWriteComment= () => {
      store.dispatch('root/requestSubmitComment', state.form)
      .then(result =>{
        console.log(result)
          state.form.commentContents='';
          state.commentList = props.commentsDto;
           console.log("댓글 입력후"); 
          console.log(state.commentList); 

        //   router.push({       
        //   name: 'BoardDetail',
        // })
        console.log()
      })
       .catch((err) => {
            store.commit('root/loadingOff')
            alert(err)
          })
    }
 const clickModifyComment= () =>{
    store.dispatch('root/requestUpdateComment',state.form) // state.form에 넣을 것 : ~~~
       .then(result =>{
        console.log(result)

      })
 }


 const clickDeleteComment= () =>{
      const userId = store.getters['root/getMyId']
         store.dispatch('root/requestDeleteComment',{userId : userId, boardId : state.form.boardId, commentId : state.commentId}) // `{boardId}?boardId=${state.form.boardId}` // 
       .then(result =>{
        console.log(result)
        
      })
 }

  return {state,clickWriteComment, clickModifyComment, clickDeleteComment,setPage,handleCurrentChange}
}
}
</script>

<style scoped>
  .el-button {
    margin: 15px;
    width: 150px;
    height: 50px;
    background: #EBC86F !important;
    border-radius: 25px !important;
  }
</style>