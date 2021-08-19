<template>
  <el-row class="main-content">
    <el-col class="left-content" :span="12">
      <el-row class="main-content" justify="center" align="middle">
        <el-col :span="24">
          <el-row justify="center"><div class="main-image"></div></el-row>
          <div class="explanation">자유롭게 글을 작성하거나 다른 사람들의 글을 볼 수 있습니다.</div>
          <el-button type="text" @click="clickTTS">
            <span class="iconify" data-inline="false" data-icon="el:speaker" style="font-size: 100px;"></span>
          </el-button>
  <el-popover
    placement="top-start"
    :width="230"
    trigger="hover"
    content="뒤로 가려면 화살표를 클릭해주세요."
  >
    <template #reference>
      <el-button class="back-btn" type="text" @click="$router.push('/')">
        <span class="iconify" data-inline="false" data-icon="akar-icons:arrow-back-thick-fill" style="color: #f88d8d; font-size: 100px;" ></span>
      </el-button>
    </template>
  </el-popover>

        </el-col>
      </el-row>
    </el-col>

    <el-col class="right-content" :span="12">
        <el-row class="main-content" justify="center" align="middle" >
        <el-col :span="24">
          <div class="question">자유롭게 글을 남겨 보세요.</div>
    <el-row justify="center" align="middle">
    <el-button class="my-button" round @click="clickWrite" >글작성</el-button>
      <el-input placeholder="검색어를 입력하세요." v-model="state.keyword" style="display:inline; width : 30%;"></el-input>
    <el-button class="my-button" round @click="clickSearch"  placement="right-end" >검색</el-button>
    </el-row>
<el-row>
  <el-col :span="3"></el-col>
  <el-col :span="18">
    <!-- 테이블 -->
    <el-table
    :data="state.pagedTableData"
    border
    style="width: 100%; font-family: BlackHanSans;"
    @current-change="handleCurrentChange"
    >
    <el-table-column
      prop="userName"
      label="작성자"
      width="200">
    </el-table-column>
    <el-table-column
      prop="boardTitle"
      label="제목"
      width="300">
    </el-table-column>
    <el-table-column
      prop="boardDate"
      label="작성일자"
      >
    </el-table-column>
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
      // reverseBoardList : [],
      keyword :'',
      page: 1,
      pageSize: 10,
      currentRow: null,
      pagedTableData :computed(() => state.boardList.slice(state.pageSize * state.page - state.pageSize, state.pageSize * state.page),
      ),
     
    })
    
    // state.reverseBoardList = state.boardList; 
    // console.log("aaa"+state.reverseBoardList.length);
    // for(var i=0; i<state.boardList.length;i++){
    //   state.reverseBoardList.push(state.boardList[state.boardList.length-1-i]);
    //   console.log(state.reverseBoardList[i]);
    // }
    


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

        console.log("var"+this.var);
        console.log("page"+state.page);
        console.log("currentRow"+state.currentRow);
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
        // .toUpperCase()
        const word = state.keyword;
        let arr = res.data.data;
        console.log(res.data)
        state.boardList = arr.filter((v)=>{
        
          const title = v.boardTitle;
          const writer = v.userName;
          return title.includes(word) || writer.includes(word);
        })
        console.log(state.boardList);
      })
      .catch((err) => {
        console.log(err);
      })
    };

     console.log("PTD");
    console.log(state.pagedTableData);

    // const handleSizeChange = function (state.boardList.length/10) {
    //     console.log(`${val} items per page`);
    //   };
    
    // const handleCurrentChange= function(state.boardList.length/10) {
    //     console.log(`current page: ${val}`);
    //   };
    const clickTTS = () => {
      const text = document.querySelector('.explanation').innerText
      let source; 
      let context; 
      context = new AudioContext();
      store.dispatch('requestKakaoTTS', text)
      .then(res => {
        context.decodeAudioData(res.data, function(buffer) {
            source = context.createBufferSource();
            source.buffer = buffer;
            source.connect(context.destination);
            source.start(); 
          });  
      })
    }

    return { state, clickWrite, setPage, clickSearch,handleCurrentChange, clickTTS} //  setPage,

  }
})
</script>

<style scoped>
.el-table{
   font-family: BlackHanSans;
    font-size: 16px;
}
  .my-button {
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
    /* position: relative; */
    background: rgba(173, 203, 176, 0.7);
    height: 100%;
    /* opacity: 0.5; */
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
  .question {
    font-family: SangSangFlowerRoad;
    font-size: 44px;
    color: rgba(248, 141, 141, 1);
  }
  .back-btn {
    position: absolute;
    bottom: 10px;
    left: 10px;
  }
</style>
