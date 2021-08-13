<template>
  <el-row class="main-content">
    <el-col class="left-content" :span="12">
      <el-row class="main-content" justify="center" align="middle">
        <el-col :span="24">
          <el-row justify="center"><div class="main-image"></div></el-row>
          <div class="explanation">원하시는 게임을 클릭하시면 
해당 게임 페이지로 가실 수 있습니다.</div>
          <span class="iconify" data-inline="false" data-icon="el:speaker" style="font-size: 100px;"></span>
<!-- 전 페이지로 돌아가기 끝 -->
        </el-col>
      </el-row>
    </el-col>
    <BackButton/>
      <el-col class="right-content" :span="12">
      <el-row class="main-content" justify="center" align="middle">
        <el-col :span="24">
          <div class="question">게임을 선택해 주세요.</div>
          
            <el-row justify="center">
              <a href="https://baduk.hangame.com/?utm_source=baduk&utm_medium=quick_2dp&utm_campaign=main" target="_blank">
          <el-button>
            <div class="baduk-image"></div>
            <div class="select-button">바둑</div>
          </el-button>
          </a>

          
          
            <a href="https://gostop.hangame.com/index.nhn?gameId=gostop&utm_source=gostop&utm_medium=quick_2dp&utm_campaign=main" target="_blank">
           <el-button>
           <span class="iconify" data-inline="false" data-icon="emojione:flower-playing-cards" style="font-size: 115px;"></span>
            <div class="select-button">화투</div>
           
          </el-button>
          </a>
            </el-row>

           <el-row justify="center">
           <a href="https://janggi.hangame.com/?utm_source=janggi&utm_medium=quick_2dp&utm_campaign=main" target="_blank">
          <el-button>
            <div class="janggi-image"></div>
            <div class="select-button">장기</div>
          </el-button>
             </a>

             <a href="https://game1.hangame.com/sinyutnori.nhn?utm_source=sinyutnori&utm_medium=quick_2dp&utm_campaign=main" target="_blank">
          <el-button>
            <div class="yutplay-image"></div>
            <div class="select-button">윷놀이</div>
          </el-button>
             </a>
            </el-row>

        </el-col>
      </el-row>
    </el-col>
  </el-row>
</template>

<script>
import BackButton from '@/views/components/BackButton'
export default {
  name: 'Login',
  components: {
    BackButton
  },
}

</script>

<style scoped>
/* a{
  text-decoration-line : none;
}
a:visited { color: black; }
  .main-content {
    height: 100%;
  } */
  .right-content {
    background: rgba(173, 203, 176, 0.7);
    height: 100%;
    /* opacity: 0.5; */
  }
  .el-button {
    margin:  20px;
    width: 200px;
    height: 200px;
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
   .baduk-image {
    height: 98px;
    width: 98px;
    margin: 0 auto;
    background-size: contain;
    background-repeat: no-repeat;
    background-image: url('../../../assets/images/button/baduk.png');
  }
   .janggi-image {
    height: 98px;
    width: 98px;
    margin: 0 auto;
    background-size: contain;
    background-repeat: no-repeat;
    background-image: url('../../../assets/images/button/janggi.png');
  }
   .yutplay-image {
    height: 98px;
    width: 98px;
    margin: 0 auto;
    background-size: contain;
    background-repeat: no-repeat;
    background-image: url('../../../assets/images/button/yutplay.png');
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