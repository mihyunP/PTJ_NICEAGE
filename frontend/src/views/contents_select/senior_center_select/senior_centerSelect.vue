<template>
  <el-row class="main-content">
    <el-col class="left-content" :span="12">
      <el-row class="main-content" justify="center" align="middle">
        <el-col :span="24">
          <el-row justify="center"><div class="main-image"></div></el-row>
          <div class="explanation">‘지도로 보기’를 선택하시면 지도에서 주변 경로당을 직접 선택하실 수 있습니다. ‘아무데나 갈래’를 선택하시면 
자동으로 경로당에 입장할 수 있습니다.</div>
          <span class="iconify" data-inline="false" data-icon="el:speaker" style="font-size: 100px;"></span>
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
</template>

<script>
import { useRouter } from 'vue-router'
import { reactive } from 'vue'
import { useStore } from 'vuex'
import axios from 'axios';
import BackButton from '@/views/components/BackButton'
const OPENVIDU_SERVER_URL = "https://" + location.hostname + ":4443";
const OPENVIDU_SERVER_SECRET = "MY_SECRET";
export default {
  name: 'HealthSelect',
  components: {
    BackButton
  },
    setup() {
        const router = useRouter()
        const store = useStore()
        const state = reactive({
          targetSession: undefined
        })
        const clickMap = () => {
          router.push({
            name: 'Map',
          })
        }

        const clickSeniorCenter = () => {
          store.commit('root/loadingOn')
          axios
            .get(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions`,{
              auth: {
                username: 'OPENVIDUAPP',
                password: OPENVIDU_SERVER_SECRET
              }
            })
            .then(res => {
              store.commit('root/loadingOff')
              console.log(res.data)
              const sessions = res.data.content

              if (sessions.length) {

                const myId = store.getters['root/getMyId']
                const index = Math.floor(Math.random() * res.data.numberOfElements)
                console.log('세션과 랜덤인덱스 확인',sessions, index)
                state.targetSession = sessions[index]
                store.dispatch('root/requestMyDetail', myId)
                .then((res) => {
                  const myName = res.data.data.userName
                  router.push({
                    name: 'SeniorCenter',
                    params: {mySessionId: state.targetSession.id, myUserName: myName, myCenterName: '경로당이름'}
                  })
                })
              } else {
                alert('현재 생성된 경로당이 없습니다.')
              }
            })
            .catch((err) => {
              store.commit('root/loadingOff')
              console.log('여기로 와야되느네', err)
            })
          

        }
      

        return {state, clickMap, clickSeniorCenter}
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
</style>