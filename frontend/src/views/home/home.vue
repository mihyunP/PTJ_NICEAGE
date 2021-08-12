<template>
  <el-row class="main-content">
    <el-col class="left-content" :span="12">
      <el-row class="main-content" justify="center" align="middle">
        <el-col :span="24">
          <el-row justify="center"><div class="main-image"></div></el-row>
          <div class="explanation">코로나 19 시대의 어르신들을 위한 화상 미팅 서비스입니다.</div>
          <span class="iconify" data-inline="false" data-icon="el:speaker" style="font-size: 100px;"></span>
        </el-col>
      </el-row>
    </el-col>
    <el-col class="right-content" :span="12">
      <el-row class="main-content" justify="center" align="middle">
        <el-col v-if="!state.isLoggedIn" :span="24">
          <div class="question">처음 오셨나요?</div>
          <el-row justify="center">
            <el-button class="select-btn" @click="clickLogin">
              <span class="iconify" data-inline="false" data-icon="noto:man-raising-hand" style="font-size: 142px;"></span>
              <div class="select-font">나 자주 와아~</div>
            </el-button>
            <el-button class="select-btn" @click="clickSignup">
              <span class="iconify" data-inline="false" data-icon="emojione:handshake-medium-light-skin-tone" style="font-size: 142px;"></span>
              <div class="select-font">나 처음 와아~</div>
            </el-button>
          </el-row>
        </el-col>
        <el-col v-else :span="24">
          <div class="question">메뉴를 선택해 주세요.</div>
          <el-row justify="center">
            
            <el-button class="my-el-button" @click="clickSeniorCenterSelect">
            <div class="seniorcenter-image"></div>
              <div class="select-button">경로당 서비스</div>
            </el-button>

            <el-button class="my-el-button" @click="clickFriendSelect">
            <div class="friendmatching-image"></div>
              <div class="select-button">친구 매칭</div>
            </el-button>

            <el-button class="my-el-button" @click="clickHealthSelect">
            <span class="iconify" data-inline="false" data-icon="noto:health-worker-light-skin-tone" style="font-size: 98px;"></span>
              <div class="select-button">건강관리 서비스</div>
            </el-button>
          </el-row>
          <br>
          <br>
          <br>
          <el-row justify="center">
            <el-button class="my-el-button" @click="clickGameSelect">
              <span class="iconify" data-inline="false" data-icon="fxemoji:videogame" style="font-size: 98px;"></span>
              <div class="select-button">게임</div>
            </el-button>

            <el-button class="my-el-button" @click="clickBoard">
              <span class="iconify" data-inline="false" data-icon="noto:newspaper" style="font-size: 98px;"></span>
              <div class="select-button">게시판</div>
            </el-button>

            <el-button class="my-el-button" @click="clickMypage">
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
import { reactive, computed } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'

export default {
  name: 'home',

  setup() {
    const store = useStore()
    const router = useRouter()

    const state = reactive({
      isLoggedIn: computed(() => store.getters['root/getIsLoggedIn']),
    })

    const clickLogin = () => {
      router.push({
        name: 'Login',
      })
    }
    
    const clickSignup = () => {
      router.push({
        name: 'Signup',
      })
    }
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
    return {state, clickLogin, clickSignup, clickSeniorCenterSelect, clickFriendSelect, clickHealthSelect, clickGameSelect, clickBoard, clickMypage}
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
  .select-btn {
    width: 270px;
    height: 270px;
    background: #EBC86F !important;
    border-radius: 40px !important;
  }
  .select-font {
    font-family: BlackHanSans;
    font-size: 36px;
  }
  .question {
    font-family: SangSangFlowerRoad;
    font-size: 60px;
    color: rgba(248, 141, 141, 1);
  }
  .explanation {
    font-family: SangSangFlowerRoad;
    font-size: 48px;
    text-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  }
  .my-el-button {
    width: 160px;
    height: 160px;
    background: #EBC86F !important;
    border-radius: 25px !important;
  }
  .select-button {
    font-family: BlackHanSans;
    font-size: 21px;
  }
  .main-image {
    height: 442px;
    width: 700px;
    margin: 0 auto;
    background-size: contain;
    background-repeat: no-repeat;
    background-image: url('../../assets/images/main.png');
  }

  .seniorcenter-image {
    height: 98px;
    width: 98px;
    margin: 0 auto;
    background-size: contain;
    background-repeat: no-repeat;
    background-image: url('../../assets/images/button/seniorcenter.png');
  }
    .friendmatching-image {
    height: 98px;
    width: 98px;
    margin: 0 auto;
    background-size: contain;
    background-repeat: no-repeat;
    background-image: url('../../assets/images/button/friendmatching.png');
  }
    .mypage-image {
    height: 98px;
    width: 98px;
    margin: 0 auto;
    background-size: contain;
    background-repeat: no-repeat;
    background-image: url('../../assets/images/button/mypage.png');
  }

</style>