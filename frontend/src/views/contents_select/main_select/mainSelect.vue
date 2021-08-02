<template>
  <el-row class="main-content">
    <el-col class="left-content" :span="12">
      <el-row class="main-content" justify="center" align="middle">
        <el-col :span="24">
          <el-row justify="center"><div class="main-image"></div></el-row>
          <div class="explanation">원하시는 메뉴를 선택해 주세요.</div>
          <span class="iconify" data-inline="false" data-icon="el:speaker" style="font-size: 100px;"></span>
        </el-col>
      </el-row>
    </el-col>

    <el-col class="right-content" :span="12">
      <el-row class="main-content" justify="center" align="middle">
        <el-col :span="24">
          <div class="question">메뉴를 선택해 주세요.</div>
          <el-row justify="center">
            
            <el-button @click="clickSeniorCenterSelect">
            <div class="seniorcenter-image"></div>
              <div class="select-button">경로당 서비스</div>
            </el-button>

            <el-button @click="clickFriendSelect">
            <div class="friendmatching-image"></div>
              <div class="select-button">친구 매칭</div>
            </el-button>

            <el-button @click="clickHealthSelect">
            <span class="iconify" data-inline="false" data-icon="noto:health-worker-light-skin-tone" style="font-size: 98px;"></span>
              <div class="select-button">건강관리 서비스</div>
            </el-button>

          </el-row>
          <br>
          <br>
          <br>
            <el-row justify="center">

          <el-button @click="clickGameSelect">
            <span class="iconify" data-inline="false" data-icon="fxemoji:videogame" style="font-size: 98px;"></span>
            <div class="select-button">게임</div>
          </el-button>

          <el-button @click="clickBoard">
            <span class="iconify" data-inline="false" data-icon="noto:newspaper" style="font-size: 98px;"></span>
            <div class="select-button">게시판</div>
          </el-button>

          <el-button @click="clickMypage">
   <div class="mypage-image"></div>
            <div class="select-button">내정보</div>
          </el-button>
            </el-row>
        </el-col>
      </el-row>
    </el-col>
  </el-row>
</template>

<script>
import { useRouter } from 'vue-router'

export default {
 
  name: 'mainSelect',

  setup() {
    const router = useRouter()

    const clickSeniorCenterSelect = () => {
      router.push({
        name: 'SeniorCenterSelect',
      })
    }
    
    const clickFriendSelect = () => {
      router.push({
        name: 'FriendSelect',
      })
    }

      const clickHealthSelect = () => {
      router.push({
        name: 'HealthSelect',
      })
    }

    const clickGameSelect = () => {
      router.push({
        name: 'GameSelect',
      })
    }
    
    const clickBoard = () => {
      router.push({
        name: 'Board',
      })
    }

    const clickMypage = () => {
      router.push({
        name: 'Mypage',
      })
    }

    return {clickSeniorCenterSelect, clickFriendSelect, clickHealthSelect, clickGameSelect, clickBoard, clickMypage}
  }

}
</script>

<style scoped>
  .main-content {
    height: 100%;
  }
  .right-content {
    background: rgba(173, 203, 176, 0.7);
    height: 100%;
    /* opacity: 0.5; */
  }
  .el-button {
    width: 150px;
    height: 150px;
    background: #EBC86F !important;
    border-radius: 25px !important;
  }
  .select-button {
    font-family: BlackHanSans;
    font-size: 21px;
  }
  .question {
    font-family: SangSangFlowerRoad;
    font-size: 88px;
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

  .seniorcenter-image {
    height: 98px;
    width: 98px;
    margin: 0 auto;
    background-size: contain;
    background-repeat: no-repeat;
    background-image: url('../../../assets/images/button/seniorcenter.png');
  }
    .friendmatching-image {
    height: 98px;
    width: 98px;
    margin: 0 auto;
    background-size: contain;
    background-repeat: no-repeat;
    background-image: url('../../../assets/images/button/friendmatching.png');
  }
    .mypage-image {
    height: 98px;
    width: 98px;
    margin: 0 auto;
    background-size: contain;
    background-repeat: no-repeat;
    background-image: url('../../../assets/images/button/mypage.png');
  }
</style>