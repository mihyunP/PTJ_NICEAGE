<template>
    <el-form-item>
                  <el-input  type="textarea" placeholder="댓글을 작성해주세요." v-model="state.form.commentContents" :autosize="{ minRows: 2, maxRows: 3}"></el-input>
    </el-form-item>
    <el-form-item >
      <!-- <div>{{boardId}}</div> -->
    <el-button @click="clickWriteComment">댓글 달기</el-button>
    </el-form-item>

    
    <!-- <el-table
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
    
    <el-table-column v-if="state.commentList.userId=state.form.userId">
           
              <el-button @click="clickModifyComment" type="danger">수정</el-button>
            </el-table-column>
    <el-table-column v-if="state.commentList.userId=state.form.userId">
              <el-button @click="clickDeleteComment" type="danger">삭제</el-button>
    </el-table-column>

  </el-table>

      <el-pagination
      background
      :page-size="pageSize"
      layout=" prev, pager, next"
      :total="state.commentform.commentsDto.length"
      @current-change="setPage"> 
    </el-pagination> -->

 <!--state.commentform.commentsDto-->
<el-table
    :height="100"
    :show-header = false
    :data="state.commentform.commentsDto"
    style="width: 100%">
    <el-table-column
      label="userName"
      width="180">
      <template #default="scope">
        <!-- <i class="el-icon-time"></i> -->
        <span style="margin-left: 10px"><b>{{ scope.row.userName}}</b> 님 : </span>
      </template>
    </el-table-column>
     <el-table-column
      label="commentContents"
      width="180">
      <template #default="scope">
        <!-- <i class="el-icon-time"></i> -->
        <span style="margin-left: 10px">{{ scope.row.commentContents}}</span>
      </template>
    </el-table-column>
    <!-- <el-table-column
      label="Name"
      width="180">
      <template #default="scope">
        <el-popover effect="light" trigger="hover" placement="top">
          <template #default>
            <p>姓名: {{ scope.row.name }}</p>
            <p>住址: {{ scope.row.address }}</p>
          </template>
          <template #reference>
            <div class="name-wrapper">
              <el-tag size="medium">{{ scope.row.name }}</el-tag>
            </div>
          </template>
        </el-popover>
      </template> 
    </el-table-column> -->
    <el-table-column 
      label="Operations">
      <template #default="scope">
        <div v-if="scope.row.userId==state.form.userId">
        <el-button class="commentButton"
          size="mini"
          @click="handleEdit(scope.$index, scope.row)">수정</el-button>
        <el-button class="commentButton" 
          size="mini"
          type="danger"
          @click="handleDelete(scope.$index, scope.row)" style="background: #d11111 !important;">삭제</el-button>
        </div>
      </template>
    </el-table-column>
  </el-table>
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

      // page: 1,
      // pageSize: 4,
      // currentRow: null,
      
      

      commentList :props.commentsDto, // 댓글 받아온거 (배열임)
      commentWriter : props.commentsDto.userId, // 댓글 작성자 (배열임)
      commentId :'',
      form : {
       userId : store.getters['root/getMyId'],
       boardId : props.boardId,
       commentContents : '', // v-model로 입력받음
       commentId :'', // 자동 증가?

      }, // 댓글 입력시 보낼 입력폼
      commentform : {
        commentsDto:[],
        user:{},
      }, // 댓글 받아오는 폼


      Data :computed(() => state.commentform.commentsDto,
      ),
    })

      




      // 댓글 불러오기
      store.dispatch('root/requestReadBoard',{boardId : state.form.boardId }) // #{state.boardId} // userId : userId, //  `{boardId}?boardId=${state.boardId}`
     .then(res => {
      // console.log(res.data);
      // console.log(res.data.data);
      state.commentform = res.data.data; // form에다가 백에서 받아온 값들 넣어주자.
      // console.log(state.form.user.userName);
      // console.log(state.form.boardTitle);
      // console.log(state.form.boardContents);
      // console.log(state.form.boardDate);
      // console.log(state.nowuserId);
      // console.log(state.form.user.userId);
     
      })
      .catch((err) => {
        console.log(err);
      })
  
  // console.log(props.title);

  // console.log(props.boardId);
  // console.log(state.form.boardId);
  // console.log("커멘트디티오");
  // console.log(props.commentsDto); 
  // console.log(state.commentList); 
  // console.log(props.commentsDto.userName);
  // console.log("타이틀");
  // console.log(props.titles);
  //  console.log("댓글 작성자");
  // console.log(props.commentsDto.userId); 


    // 댓글 작성하기
    const clickWriteComment= () => {
      store.dispatch('root/requestSubmitComment', state.form)
      .then(() =>{

      // 댓글 다시 불러오기
      store.dispatch('root/requestReadBoard',{boardId : state.form.boardId }) // #{state.boardId} // userId : userId, //  `{boardId}?boardId=${state.boardId}`
     .then(res => {
   
      state.commentform = res.data.data; // form에다가 백에서 받아온 값들 넣어주자.
      // console.log(state.commentform.commentsDto); 
      })
      .catch((err) => {
        console.log(err);
      })

          state.form.commentContents='';
          state.commentList = props.commentsDto; // 

        //   router.push({       
        //   name: 'BoardDetail',
        // })
      })
       .catch((err) => {
            store.commit('root/loadingOff')
            alert(err)
          })
    }

      // 댓글 수정하기
      const handleEdit= (index, row) =>{
       state.form.userId = store.getters['root/getMyId'],
       state.form.boardId = props.boardId,
      //  state.form.commentContents
       state.form.commentId = row.commentId;
        store.dispatch('root/requestUpdateComment',state.form) // state.form에 넣을 것 : ~~~
       .then(result =>{
        console.log(result)


      // 댓글 다시 불러오기
      store.dispatch('root/requestReadBoard',{boardId : state.form.boardId }) // #{state.boardId} // userId : userId, //  `{boardId}?boardId=${state.boardId}`
     .then(res => {
   
      state.commentform = res.data.data; // form에다가 백에서 받아온 값들 넣어주자.
      })
      .catch((err) => {
        console.log(err);
      })


      })

      }

      // 댓글 삭제하기
      const handleDelete= (index, row) =>{
        console.log("삭제");
        console.log(index, row);
        const userId = store.getters['root/getMyId']
        state.commentId = row.commentId;
         store.dispatch('root/requestDeleteComment',{userId : userId, boardId : state.form.boardId, commentId : state.commentId}) // `{boardId}?boardId=${state.form.boardId}` // 
       .then(result =>{
        console.log(result)

      // 댓글 다시 불러오기
      store.dispatch('root/requestReadBoard',{boardId : state.form.boardId }) // #{state.boardId} // userId : userId, //  `{boardId}?boardId=${state.boardId}`
     .then(res => {
   
      state.commentform = res.data.data; // form에다가 백에서 받아온 값들 넣어주자.
      console.log(state.commentform.commentsDto); 
      })
      .catch((err) => {
        console.log(err);
      })

        
      })
      }

  return {state,clickWriteComment,handleEdit,handleDelete}
}
}
</script>

<style scoped>
  .el-button {
    font-family: BlackHanSans;
    font-size: 25px;
    margin: 15px;
    width: 150px;
    height: 50px;
    background: #EBC86F !important;
    border-radius: 25px !important;
  }
    .commentButton{
    font-family: BlackHanSans;
    font-size: 10px;
    color : black !important;
    /* margin: 15px; */
    width: 60px;
    height: 30px;
    border-radius: 25px !important;
  }
</style>