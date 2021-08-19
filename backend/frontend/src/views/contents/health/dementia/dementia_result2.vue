<template>
  <el-row class="main-content">
    <el-col class="left-content" :span="12">
      <el-row class="main-content" justify="center" align="middle">
        <el-col :span="24">
          <el-row justify="center"><div class="main-image"></div></el-row>
          <div class="explanation">진단 결과를 확인해주세요.</div>
          <el-button type="text" @click="clickTTS">
            <span class="iconify" data-inline="false" data-icon="el:speaker" style="font-size: 100px;"></span>
          </el-button>
<back-button/>
        </el-col>
      </el-row>
    </el-col>
    <el-col class="right-content" :span="12">
        <el-row class="main-content"  align="middle"> <!--justify="center" -->
        <el-col :span="24">
          <div class="question">치매 진단 검사 결과</div>

         <el-container class="dementia-container">
          체크한 수가 10개 이상이 넘으셨습니다. 치매 초기 증상이라 할 수 있니다.
          </el-container>
<br>

           <el-container class="dementia-container">
              이상 징후가 보인다면 가까운 병원에 치매 클리닉을 찾아 상담을 받아 보아야합니다. 하지만 10년전과 비교 했을 때 자신의 기억력과 인지 능력이 별 차이가 없으면 단순한 건망증이라고 볼 수 있습니다.
          </el-container>
          
        </el-col>
        </el-row>   
     
    </el-col>
  </el-row>
</template>
<script>
import BackButton from '../../../components/BackButton.vue'
import { useStore } from 'vuex'
export default {
  components: { BackButton },
  setup() {
    const store = useStore()
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
    return { clickTTS }
  }
}
</script>

<style scoped>

  .dementia-container {
    /* width : 100%; */
    padding: 50px;
    margin: 50px;
    background: #EFDEDE !important;
    line-height: 150%;
    font-family: BlackHanSans;
    font-size: 30px;
    text-align: left;
  }
  .main-content {
    height: 100%;
  }
  .right-content {
    position: relative;
    background: rgba(173, 203, 176, 0.7);
    height: 100%;
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
    background-image: url('../../../../assets/images/main.png');
  }
  /* 이전 페이지로 돌아가기 css */
  .previouspage {
    font-family: SangSangFlowerRoad;
    font-size: 45px;
   color: rgba(248, 141, 141, 1);
  }
</style>