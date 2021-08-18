<template>
  <el-row class="main-content">
    <el-col class="left-content" :span="12">
      <el-row class="main-content" justify="center" align="middle">
        <el-col :span="24">
          <el-row justify="center"><div class="main-image"></div></el-row>
          <div class="explanation">원하시는 스트레칭을 클릭하시면 <br> 해당 동영상으로 이동할 수 있습니다.</div>
          <el-button type="text" @click="clickTTS" style="background: rgba(255, 250, 250, 0.5) !important;">
            <span class="iconify" data-inline="false" data-icon="el:speaker" style="font-size: 100px;"></span>
          </el-button>
        </el-col>
      </el-row>
    </el-col>
    <BackButton/>
      <el-col class="right-content" :span="12">
      <el-row class="main-content" justify="center" align="middle">
        <el-col :span="24">
          <div class="question">어떤 스트레칭을 하고 싶으신가요?</div>
          <el-row justify="center">
            <a href="https://www.youtube.com/watch?v=4iqWtFWDQrc" target="_blank">
              <el-button>
                <span class="iconify" data-icon="noto-v1:person-cartwheeling-light-skin-tone" data-height="100" data-flip="vertical"></span>
                <div class="select-button">누워서 스트레칭</div>
              </el-button>
            </a>

      
            <a href="https://www.youtube.com/watch?v=QamVrhEj95k" target="_blank">
              <el-button>
                <span class="iconify" data-icon="noto:person-in-lotus-position-light-skin-tone" data-height="100"></span>
                <div class="select-button">앉아서 스트레칭</div>
              </el-button>
            </a>
          </el-row>

          <el-row justify="center">
            <a href="https://www.youtube.com/watch?v=aSPIO3zGiDU" target="_blank">
              <el-button>
                <span class="iconify" data-icon="noto:person-kneeling-light-skin-tone" data-height="100"></span>ㄴ
                <div class="select-button">치매예방체조</div>
              </el-button>
            </a>

            <a href="https://www.youtube.com/watch?v=b5AW0FbiJ0I" target="_blank">
              <el-button>
                <span class="iconify" data-icon="openmoji:clapping-hands-light-skin-tone" data-height="100"></span>
                <div class="select-button">건강박수</div>
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
  name: 'Stretching',
  components: {
    BackButton
  },
  methods: {
    clickTTS() {
      const text = document.querySelector('.explanation').innerText
      let source; 
      let context; 
      context = new AudioContext();
      this.$store.dispatch('requestKakaoTTS', text)
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
    width: 200px;
    height: 200px;
    margin : 10px;
    background: #EBC86F !important;
    border-radius: 25px !important;
  }
  .select-button {
    margin-top: 10px;
    font-family: BlackHanSans;
    font-size: 28px;
  }
  .question {
    font-family: SangSangFlowerRoad;
    font-size: 50px;
    color: rgba(248, 141, 141, 1);
  }
  .explanation {
    font-family: SangSangFlowerRoad;
    font-size: 45px;
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
  /* .baduk-image {
    height: 120px;
    width: 120px;
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
  } */
</style>