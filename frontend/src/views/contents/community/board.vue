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
        <el-row class="main-content" justify="center" align="middle" >
        <el-col :span="24">
          <div class="question">자유롭게 글을 남겨 보세요.</div>
    <el-row justify="center" align="middle">
    <el-button round @click="clickWrite" >글작성</el-button>
      <el-input placeholder="aaa" v-model="state.keyword" style="display:inline; width : 30%;"></el-input>
    <el-button round @click="clickSearch"  placement="right-end" >검색</el-button> <!--@click="search"-->
    </el-row>
<el-row>
  <el-col :span="3"></el-col>
  <el-col :span="18">
    <!-- 테이블 -->
    <el-table
    :data="state.pagedTableData"
    border
    style="width: 100%"
    @current-change="handleCurrentChange"
    >

    <el-table-column
      prop="작성자"
      label="작성자"
      width="180">
    </el-table-column>
    <el-table-column
      prop="boardTitle"
      label="제목"
      width="180">
    </el-table-column>
    <el-table-column
      prop="boardDate"
      label="작성일자">
    </el-table-column>
     <!-- <el-table-column
     label="글 보기">
      <el-button @click="click" type="danger">탈퇴</el-button>
       <router-link :to="{ name: 'boardDetail', params: { boardList : state.boardList } }"></router-link>
    </el-table-column> -->
    <!-- </router-link> -->
  </el-table>
    <!-- 테이블 끝 -->
  </el-col>
  <el-col :span="3">
  </el-col>
</el-row>
<br>
<br>
<br>
    <!-- 페이지네이션 -->
      <el-pagination
      background
      :page-size="pageSize"
      layout=" prev, pager, next"
      :total="state.boardList.length"
      @current-change="setPage"> <!-- total(총 게시물 수) / page-size(페이지 당 게시물 수) : 총 페이지 수 -->
    </el-pagination>
    <!-- 페이지네이션 끝 -->
        </el-col>
          </el-row>
          
        </el-col>
        </el-row>
</template>

<script>
import {defineComponent, reactive, computed} from 'vue' //  , , 
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default defineComponent ({
  name: 'Board',
  
    setup() {
    const store = useStore()
    const router = useRouter()
    const state = reactive({
      boardList : [],
      keyword :'',
      page: 1,
      pageSize: 10,
      currentRow: null,
      pagedTableData :computed(() => state.boardList.slice(state.pageSize * state.page - state.pageSize, state.pageSize * state.page),
      )
    })
    
    console.log("data"+state.pagedTableData)

    const setPage= function (val) {
        state.page = val
    }

     const handleCurrentChange = function (val) {
        state.currentRow = val;
        
        router.push({       
          name: 'BoardDetail',
          params: {
            // userid:val.userId,
            id:val.boardId,
            // date:val.boardDate,
            // title:val.boardTitle,
            // contents:val.boardContents,
            // writer:val.boardWriter,
        }})

        // state.id = val.boardId;
        // router.push('/boarddetail/'+ state.id);
      
        console.log("title : "+val.boardTitle)
        console.log("id : "+val.boardId)
        console.log(this.id);
    }

    store.dispatch('root/requestBoardContent')
    .then(res => {
        console.log(res.data)
        state.boardList = res.data.data;
        console.log(state.boardList);
      })
      .catch((err) => {
        console.log(err);
      })
    
    const clickWrite = function () {
              router.push({
                name: 'BoardWrite'
              })
    };

    // 검색 기능 추가
    const clickSearch = function () {
    store.dispatch('root/requestBoardContent')
    .then(res => {
        // const word = state.keyword.toUpperCase(); // 키워드를 다 대문자로 바꾼것
        const word = state.keyword;
        let arr = res.data.data;
        console.log(res.data)
        state.boardList = arr.filter((v)=>{
          // const title = v.boardTitle.toUpperCase();
          // const writer = v.boardWriter.toUpperCase();
          // return title.includes(word) || writer.includes(word);
          const title = v.boardTitle;
          return title.includes(word)
        })
        console.log(state.boardList);
      })
      .catch((err) => {
        console.log(err);
      })
    };

   

    // const handleSizeChange = function (state.boardList.length/10) {
    //     console.log(`${val} items per page`);
    //   };
    
    // const handleCurrentChange= function(state.boardList.length/10) {
    //     console.log(`current page: ${val}`);
    //   };

     return { state, clickWrite, setPage, clickSearch,handleCurrentChange} //  setPage,

  }
}) // 1. 게시물 누르면 해당 글 상세 조회 페이지로 이동, 2. 작성자, 제목으로 필터, 3. 테이블-페이지네이션 열결, 페이지네이션 구현
</script>

<style scoped>
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
