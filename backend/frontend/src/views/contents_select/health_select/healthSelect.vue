<template>
  <el-row class="main-content">
    <el-col class="left-content" :span="12">
      <el-row class="main-content" justify="center" align="middle">
        <el-col :span="24">
          <el-row justify="center"><div class="main-image"></div></el-row>
          <div class="explanation">치매 진단검사를 선택하시면 간단한 치매 진단을 하실 수 있습니다. 스트레칭 따라하기를 선택하시면 간단한 스트레칭 동작을 화면을 보시며 따라하실 수 있습니다.</div>
          <el-button type="text" @click="clickTTS" style="background: rgba(255, 250, 250, 0.5) !important;">
            <span class="iconify" data-inline="false" data-icon="el:speaker" style="font-size: 100px;"></span>
          </el-button>
          <br>
        </el-col>
      </el-row>
    </el-col>
    <BackButton/>
      <el-col class="right-content" :span="12">
      <el-row class="main-content" justify="center" align="middle">
        <el-col :span="24">
          <div class="question">건강관리 서비스를 선택해주세요.</div>
          <el-button @click="clickDementia">
            <span class="iconify" data-inline="false" data-icon="noto:woman-health-worker-light-skin-tone" style="font-size: 142px;"></span>
            <div class="select-button">치매<br>진단검사</div>
          </el-button>
          <el-button @click="clickStretching">
            <span class="iconify" data-inline="false" data-icon="fxemoji:dancer" style="font-size: 142px;"></span>
            <div class="select-button">스트레칭<br>따라하기</div>
          </el-button>
        </el-col>
      </el-row>
    </el-col>
  </el-row>
</template>

<script>
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'
import BackButton from '@/views/components/BackButton'
export default {
  name: 'HealthSelect',
  components: {
    BackButton
  },
    setup() {
        const store = useStore()
        const router = useRouter()

        const clickDementia = () => {
          router.push({
            name: 'Dementia',
          })
        }
        
        const clickStretching = () => {
          router.push({
            name: 'Stretching',
          })
        }

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
      .catch(err => {
        console.log(err)
      })
    }

        return {clickDementia, clickStretching, clickTTS}
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
    /* 이전 페이지로 돌아가기 css */
  .previouspage {
    font-family: SangSangFlowerRoad;
    font-size: 45px;
   color: rgba(248, 141, 141, 1);
  }
</style>