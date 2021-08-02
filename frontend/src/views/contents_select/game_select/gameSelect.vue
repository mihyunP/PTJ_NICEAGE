<template>
  <el-row class="main-content">
    <el-col class="left-content" :span="12">
      <el-row class="main-content" justify="center" align="middle">
        <el-col :span="24">
          <el-row justify="center"><div class="main-image"></div></el-row>
          <div class="explanation">원하시는 게임을 클릭하시면 
해당 게임 페이지로 가실 수 있습니다.</div>
          <span class="iconify" data-inline="false" data-icon="el:speaker" style="font-size: 100px;"></span>
        </el-col>
      </el-row>
    </el-col>

      <el-col class="right-content" :span="12">
      <el-row class="main-content" justify="center" align="middle">
        <el-col :span="24">
          <div class="question">게임을 선택해 주세요.</div>
          
            <el-row justify="center">
          <el-button @click="clickSeniorCenter">
            <span class="iconify" data-inline="false" data-icon="emojione:world-map" style="font-size: 114px;"></span>
            <div class="select-button">바둑</div>
          </el-button>
          <el-button @click="clickSeniorCenter">
            <span class="iconify" data-inline="false" data-icon="fxemoji:dancer" style="font-size: 142px;"></span>
            <div class="select-button">화투</div>
          </el-button>
            </el-row>

           <el-row justify="center">
          <el-button @click="clickSeniorCenter">
            <span class="iconify" data-inline="false" data-icon="emojione:world-map" style="font-size: 114px;"></span>
            <div class="select-button">장기</div>
          </el-button>
          <el-button @click="clickSeniorCenter">
            <span class="iconify" data-inline="false" data-icon="fxemoji:dancer" style="font-size: 142px;"></span>
            <div class="select-button">윷놀이</div>
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
  name: 'HealthSelect',

    setup() {
        const router = useRouter()

        const clickSeniorCenter = () => {
          router.push({
            name: 'SeniorCenter',
          })
        }
        
   

        return {clickSeniorCenter}
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
    width: 160px;
    height: 160px;
    background: #EBC86F !important;
    border-radius: 40px !important;
  }
  .select-button {
    font-family: BlackHanSans;
    font-size: 36px;
  }
  .question {
    font-family: SangSangFlowerRoad;
    font-size: 68px;
    color: rgba(248, 141, 141, 1);
  }
  .explanation {
    font-family: SangSangFlowerRoad;
    font-size: 38px;
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
</style>