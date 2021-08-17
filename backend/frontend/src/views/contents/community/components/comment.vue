<template>
    <el-form-item>
                  <el-input placeholder="댓글을 작성해주세요." v-model="state.form.commentContents"></el-input>
    </el-form-item>
    <el-form-item >
    <el-button @click="clickWriteComment"> 댓글 달기</el-button>
    </el-form-item>
    <br>
     <!--  :data="" -->
    <el-table
    :show-header =false
    :data="state.commentList"
    style="width: 100%"
    @current-change="handleCurrentChange"
    >
    <el-table-column
      prop="작성자"
      
      width="180">
    </el-table-column>
    <el-table-column
      prop="댓글내용"
      width="180">
    </el-table-column>
    <el-table-column
            > <!--v-if 사용해서 댓글쓴사람 == 현재 로그인된 사람 일때만 보여주기-->
              <el-button @click="clickModifyComment" type="danger">수정</el-button>
            </el-table-column>
    <el-table-column
            >
              <el-button @click="clickDeleteComment" type="danger">삭제</el-button>
            </el-table-column>
  </el-table>
 
      <!--<el-pagination
      background
      :page-size="pageSize"
      layout=" prev, pager, next"
      :total="state.boardList.length"
      @current-change="setPage"> 
    </el-pagination> -->

</template>

<script>
  import { reactive } from 'vue'
import { useStore } from 'vuex'

export default {
props: {
      id : {
        type : String,
        default : '',
      }
  // comment : {
  //   type : Array,
  // }


  // title : {
  //   type: String,
  //   default : '댓글창입니다.'
  // }
},

setup(props) {
      const store = useStore()
    const state = reactive({
      commentList : [], // 댓글 받아온거
      form : {
       boardId : props.boardId,
       commentContents : '', // v-model로 입력받음
       // commentId :'', 자동 증가?

     
      } // 댓글 입력시 보낼 입력폼
    })
  console.log(props.title);

    const clickWriteComment= () => {
      store.dispatch('root/requestSubmitWrite', state.form)
      .then(result =>{
        console.log(result)

      })
       .catch((err) => {
            store.commit('root/loadingOff')
            alert(err)
          })
    }
 const clickModifyComment= () =>{
    store.dispatch('root/requestUpdateComment',state.form) // stat.form에 넣을 것 : ~~~
       .then(result =>{
        console.log(result)

      })
 }

 const clickDeleteComment= () =>{
      const userId = store.getters['root/getMyId']
         store.dispatch('root/requestDeleteWrite',{userId : userId, boardId : state.form.boardId, commentId : state.commentId,}) // `{boardId}?boardId=${state.form.boardId}`
       .then(result =>{
        console.log(result)
        
      })
 }

  return {state,clickWriteComment, clickModifyComment, clickDeleteComment}
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