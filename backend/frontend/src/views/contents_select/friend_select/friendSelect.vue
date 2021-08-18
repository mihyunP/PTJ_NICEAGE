<template>
  <el-row class="main-content">
    <el-col class="left-content" :span="12">
      <el-row class="main-content" justify="center" align="middle">
        <el-col :span="24">
          <el-row justify="center"><div class="main-image"></div></el-row>
          <div class="explanation">오른쪽 3가지 질문에 대한 대답을 
            선택하시면 친구를 만나실 수 있습니다.</div>
          <el-button type="text" @click="clickTTS" style="background: rgba(255, 250, 250, 0.5) !important;">
            <span class="iconify" data-inline="false" data-icon="el:speaker" style="font-size: 100px;"></span>
          </el-button>
        </el-col>
      </el-row>
    </el-col>
    <BackButton/>
      <el-col class="right-content" :span="12">
  <div id="app">
<div v-if="status==1">
      <el-row class="main-content" justify="center" align="middle">
        <el-col :span="24">
          <div class="question">친구를 소개해 드릴까요?</div>

          <el-row justify="center">
          <el-button style="margin: 20px; width: 200px; height: 200px;" @click="getSelection(1)">
            <span class="iconify" data-inline="false" data-icon="bx:bx-wifi" style="font-size: 148px;"></span>
            <div class="select-button">동네친구</div>
          </el-button>
          <el-button style="margin: 20px; width: 200px; height: 200px;" @click="getSelection(2)">
          <span class="iconify" data-inline="false" data-icon="noto:person-dark-skin-tone-bald" style="font-size: 156px;"></span>
            <div class="select-button">다른 지역 친구</div>
          </el-button>
          </el-row>
            </el-col>
      </el-row>
</div>

<div v-else-if="status==2">
   <el-row class="main-content" justify="center" align="middle">
        <el-col :span="24">
          <div class="question">친구의 성별을 선택해주세요.</div>

           <el-row justify="center">
          <el-button  @click="getSelection(1)">
            <span class="iconify" data-inline="false" data-icon="noto:people-holding-hands-light-skin-tone" style="font-size: 107px;"></span>
            <div class="select-button">동성 친구</div>
          </el-button>
          <el-button  @click="getSelection(2)">
            <span class="iconify" data-inline="false" data-icon="fxemoji:couple" style="font-size: 107px;"></span>
            <div class="select-button">이성 친구</div>
          </el-button>
          <el-button  @click="getSelection(3)">
           <div class="random-image"></div>
            <div class="select-button">상관 없음</div>
          </el-button>
           </el-row>
              </el-col>
      </el-row>
</div>

<div v-else-if="status==3">
   <el-row class="main-content" justify="center" align="middle">
        <el-col :span="24">
          <div class="question">취미를 선택해 주세요.</div>

  <el-row justify="center">
      <el-button @click="getSelection(1)">
            <div class="baduk-image"></div>
              <div class="select-button">바둑</div>
            </el-button>

            <el-button @click="getSelection(2)">
            <div class="janggi-image"></div>
              <div class="select-button">장기</div>
            </el-button>

            <el-button @click="getSelection(3)">
            <span class="iconify" data-inline="false" data-icon="emojione:flower-playing-cards" style="font-size: 77px;"></span>
              <div class="select-button">화투</div>
            </el-button>

          </el-row>
         
            <el-row justify="center">

          <el-button @click="getSelection(4)">
           <span class="iconify" data-inline="false" data-icon="fxemoji:running" style="font-size: 93px;"></span>
            <div class="select-button">조깅</div>
          </el-button>

          <el-button class="select-button" @click="getSelection(5)">
           <span class="iconify" data-inline="false" data-icon="noto:mountain" style="font-size: 92px;"></span>
            <div>등산</div>
          </el-button>

          <el-button @click="getSelection(6)">
    <div class="random-image"></div>
            <div class="select-button">상관없음</div>
          </el-button>
            </el-row>

              </el-col>
      </el-row>  
</div>
  </div>
    </el-col>
  </el-row>
  <div v-if="dialogVisible" class="loading-container">
    <p class="center-title" style="margin-bottom: 0;">친구를 찾는 중입니다...</p>
    <el-row justify="center">
      <div class="pigeon-image"></div>
      <!-- <el-image src="https://media.giphy.com/media/COzggcvksIViw/giphy.gif"></el-image> -->
    </el-row>
    <div class="footer">
      <div class="dialog-question">친구매칭을 취소하시겠어요?</div>
      <el-button @click="handleClose">
        <span class="iconify" data-inline="false" data-icon="noto:man-gesturing-ok" style="font-size: 80px;"></span>
        <div class="custom-font">취소</div>
      </el-button>
    </div>
  </div>
  <!-- <el-dialog
  v-model="dialogVisible"
  width="70%"
  :before-close="handleClose"
  center>

</el-dialog> -->
</template>

<script>
import { useRouter } from 'vue-router'
import BackButton from '@/views/components/BackButton'
export default {

  name: 'FiendSelect',
  components: {
    BackButton
  },
  setup() {
    const router = useRouter()

    const clickFriendMatching = () => {
      router.push({
        name: 'FriendMatching',
      })
    }
    return {clickFriendMatching}
  },

  data() {
    return {
      select : {
        region : 0,
        gender : 0,
        hobby : 0,
      },
      dialogVisible: false,
      status : 1,
    }
  },

  methods: {
    getSelection(num) {
      if(this.status==1){
        this.select.region = num;
        console.log("region :"+ this.select.region);
        this.status++;
        console.log("status :"+ this.status);   
      }else if (this.status==2) {
        this.select.gender = num;
        console.log("gender :"+ this.select.gender);
        this.status++;
        console.log("status :"+ this.status);  
      }else if (this.status==3) {
        this.select.hobby = num;
        console.log("hobby :"+ this.select.hobby);
        this.onLoading()
        this.requestFriendMatching()
      }
    },
    onLoading() {
      this.dialogVisible = true
      const mainContent = document.querySelector('.main-content')
      mainContent.classList.add('background-loading')

    },
    offLoading() {
      this.dialogVisible = false
      const mainContent = document.querySelector('.main-content')
      mainContent.classList.remove('background-loading')
    },
    requestFriendMatching() {
      const payload = {
        firstChoice: this.select.region,
        secondChoice: this.select.gender,
        thirdChoice: this.select.hobby,
        userId: this.$store.getters['root/getMyId']
      }
      this.$store.dispatch('root/requestFriendMatching', payload)
      .then((res) => {
        this.offLoading()
        console.log(res)
        if (res.data.data) {
          this.$router.push({
            name: 'FriendMatching',
            params: {
              mySessionId: res.data.data, 
              myUserName: payload.userId
            }
          })
        } else {
          alert('친구를 찾지 못했습니다.')
        }
      })
      .catch(err => {
        console.log(err)
      })
    },
    handleClose() {
      this.offLoading()
    },
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
  },
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
    margin: 15px;
    width: 150px;
    height: 150px;
    background: #EBC86F !important;
    border-radius: 25px !important;
  } 
  .select-button {
    font-family: BlackHanSans;
    font-size: 21px;
  }
  .custom-button {
    /* background: white !important; */
    /* border-radius: 25px !important; */
    width: 200px !important;
  }
  .custom-font {
    font-family: BlackHanSans;
    font-size: 35px;
  }
  .question {
    font-family: SangSangFlowerRoad;
    font-size: 88px;
    color: rgba(248, 141, 141, 1);
  }
  .dialog-question {
    font-family: SangSangFlowerRoad;
    font-size: 40px;
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
      .random-image {
    height: 98px;
    width: 98px;
    margin: 0 auto;
    background-size: contain;
    background-repeat: no-repeat;
    background-image: url('../../../assets/images/button/random.png');
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
      .pigeon-image {
    height: 400px;
    width: 400px;
    margin: 0 auto;
    background-size: contain;
    background-repeat: no-repeat;
    background-image: url('../../../assets/images/pigeon2.gif');
  }
  .center-title {
    font-family: SangSangFlowerRoad;
    font-size: 48px;
    text-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);     
  }
  .loading-container {
    position: absolute;
    top: 50%;
    left: 50%;
    background: rgba(173, 203, 176, 0.6);
    transform: translate(-50%, -50%);
    width: 50vw;
    height: 70vh;
    border-radius: 40px !important;
    filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25));
  }
  .background-loading {
    opacity: 0.1 !important;
  }
  .footer {
    position: fixed;
    bottom: 10px;
    width: 100%;
  }
</style>