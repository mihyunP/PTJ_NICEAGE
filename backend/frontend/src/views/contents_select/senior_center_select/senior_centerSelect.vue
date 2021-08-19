<template>
  <el-row class="main-content">
    <el-col class="left-content" :span="12">
      <el-row class="main-content" justify="center" align="middle">
        <el-col :span="24">
          <el-row justify="center"><div class="main-image"></div></el-row>
          <div class="explanation">‘지도로 보기’를 선택하시면 지도에서 주변 경로당을 직접 선택하실 수 있습니다. ‘아무데나 갈래’를 선택하시면 
자동으로 경로당에 입장할 수 있습니다.</div>
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
          <div class="question">경로당을 선택해주세요.</div>
            <el-row justify="center">
          <el-button @click="clickMap">
            <span class="iconify" data-inline="false" data-icon="emojione:world-map" style="font-size: 114px;"></span>
            <div class="select-button">지도로 보기</div>
          </el-button>
          <el-button @click="clickSeniorCenter">
            <span class="iconify" data-inline="false" data-icon="fxemoji:dancer" style="font-size: 142px;"></span>
            <div class="select-button">아무데나 갈래</div>
          </el-button>
            </el-row>
        </el-col>
      </el-row>
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
</template>

<script>
import { useRouter } from 'vue-router'
import { reactive } from 'vue'
import { useStore } from 'vuex'
import axios from 'axios';
import BackButton from '@/views/components/BackButton'
axios.defaults.headers.post['Access-Control-Allow-Origin'] = 'http://i5b202.p.ssafy.io';
axios.defaults.headers.post['Content-Type'] = 'application/json';
const OPENVIDU_SERVER_URL = "https://i5b202.p.ssafy.io:4443"
const OPENVIDU_SERVER_SECRET = "ssafy"
export default {
  name: 'HealthSelect',
  components: {
    BackButton
  },
    setup() {
        const router = useRouter()
        const store = useStore()
        const state = reactive({
          targetSession: undefined,
          myCenterName: undefined,
          dialogVisible: false,
        })
        const clickMap = () => {
          router.push({
            name: 'Map',
          })
        }

        const onLoading = () => {
          state.dialogVisible = true
          const mainContent = document.querySelector('.main-content')
          mainContent.classList.add('background-loading')

        }
        const offLoading = () => {
          state.dialogVisible = false
          const mainContent = document.querySelector('.main-content')
          mainContent.classList.remove('background-loading')
        }

        const clickSeniorCenter = () => {
          onLoading()
          axios
            .get(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions`,{
              auth: {
                username: 'OPENVIDUAPP',
                password: OPENVIDU_SERVER_SECRET
              }
            })
            .then(res => {
              console.log(res.data)
              const sessions = res.data.content

              if (sessions.length) {

                const myId = store.getters['root/getMyId']
                const index = Math.floor(Math.random() * res.data.numberOfElements)
                state.targetSession = sessions[index]
                const seniorId = state.targetSession.id.split('-')[0]
                console.log('1124124214124',state.targetSession.id)
                store.dispatch('root/requestSeniorCenter', seniorId)
                .then(res => {
                  if (res.data.data !== null) {
                    state.myCenterName = res.data.data.seniorName
                  }
                  store.dispatch('root/requestMyDetail', myId)
                  .then((res) => {
                    offLoading()
                    const myName = res.data.data.userName
                    router.push({
                      name: 'SeniorCenter',
                      params: {mySessionId: state.targetSession.id, myUserName: myName, myCenterName: state.myCenterName}
                    })
                  })
                  .catch(() => {
                    offLoading()
                    alert('이름을 불러올 수 없습니다.')
                  })
                })
              } else {
                offLoading()
                alert('현재 생성된 경로당이 없습니다.')
              }
            })
            .catch((err) => {
              offLoading()
              console.log('여기로 와야되느네', err)
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

        return {state, clickMap, clickSeniorCenter, clickTTS, onLoading, offLoading}
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
  .dialog-question {
    font-family: SangSangFlowerRoad;
    font-size: 40px;
    color: rgba(248, 141, 141, 1);
  }
</style>