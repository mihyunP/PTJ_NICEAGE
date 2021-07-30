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
        <el-col :span="24">
          <div class="question">처음 오셨나요?</div>
          <el-button @click="clickLogin">
            <span class="iconify" data-inline="false" data-icon="noto:man-raising-hand" style="font-size: 142px;"></span>
            <div class="select-button">나 자주 와아~</div>
          </el-button>
          <el-button @click="clickSignup">
            <span class="iconify" data-inline="false" data-icon="emojione:handshake-medium-light-skin-tone" style="font-size: 142px;"></span>
            <div class="select-button">나 처음 와아~</div>
          </el-button>
        </el-col>
      </el-row>
    </el-col>
  </el-row>
</template>

<script>
import { useRouter } from 'vue-router'

export default {
  name: 'home',

  setup() {
    const router = useRouter()

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

    return {clickLogin, clickSignup}
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
    width: 270px;
    height: 270px;
    background: #EBC86F !important;
    border-radius: 40px !important;
  }
  .select-button {
    font-family: BlackHanSans;
    font-size: 36px;
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
    background-image: url('../../assets/images/main.png');
  }
</style>