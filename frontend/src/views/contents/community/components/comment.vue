<template>
{{title}}
    <el-form-item label="댓글"> <!--  prop="pass"-->
                  <el-input placeholder="댓글을 작성해주세요." v-model="state.commentContents"></el-input>
    </el-form-item>
    <el-button @click="clickWriteComment"> 댓글달기</el-button>
    
     <!--  :data="" -->
    <el-table
    style="width: 100%"
    @current-change="handleCurrentChange"
    >
    <el-table-column
      prop="작성자"
      label="작성자"
      width="180">
    </el-table-column>
    <el-table-column
      prop="댓글내용"
      label="내용"
      width="180">
    </el-table-column>
    <el-table-column
              label="수정"> <!--v-if 사용해서 댓글쓴사람 == 현재 로그인된 사람 일때만 보여주기-->
              <el-button @click="clickModifyComment" type="danger">수정</el-button>
            </el-table-column>
    <el-table-column
              label="삭제">
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
  // comment : {
  //   type : Array,

  // }
  // 댓글 내용
  // 작성자 아이디

  title : {
    type: String,
    default : '댓글창입니다.'
  }
},

setup(props) {
      const store = useStore()
    const state = reactive({
    //   form : {
    //    boardId : 0,
    //    commentContents : '',
    //    commentId :'', // 
    //    userId : '', // id 번호?
    //  }
      newComment : '',
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

<style>
  .el-button {
    margin: 15px;
    width: 150px;
    height: 50px;
    background: #EBC86F !important;
    border-radius: 25px !important;
  }
</style>