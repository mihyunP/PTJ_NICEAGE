<template>
  <el-row class="main-content">
    <el-col class="left-content" :span="12">
      <el-row class="main-content" justify="center" align="middle">
        <el-col :span="24">
          <el-row justify="center"><div class="main-image"></div></el-row>
          <div class="explanation">아이디와 휴대폰 번호를 입력하시면 비밀번호를 찾아드려요.</div>
          <el-button type="text" @click="clickTTS">
            <span class="iconify" data-inline="false" data-icon="el:speaker" style="font-size: 100px;"></span>
          </el-button>
        </el-col>
      </el-row>
    </el-col>
    <BackButton/>
    <el-col class="right-content" :span="12">
      <el-container class="login-container">
        <el-header>
          <div class="info-font">비밀번호 찾기</div>
          <div>사진</div>
        </el-header>
        <el-main>
          <el-form :model="state.passwordForm" :rules="state.rules" ref="passwordForm" label-position="top">
            <el-form-item prop="userId" label="아이디" :label-width="state.formLabelWidth">
              <el-input v-model="state.passwordForm.userId" autocomplete="off" @keyup="checkValidation"></el-input>
            </el-form-item>
            <el-form-item prop="userPhone" label="휴대폰 번호를 입력해주세요." :label-width="state.formLabelWidth">
              <el-row>
                <el-col :span="12">
                  <el-input v-model="state.passwordForm.userPhone" placeholder="휴대폰 번호를 입력해주세요."></el-input>
                </el-col>
                <el-col :span="6">
                  <el-button type="info" @click="sendAuthenticationNumber">인증번호발송</el-button>
                </el-col>
              </el-row>
              <el-row style="margin-top: 2px;">
                <el-col :span="8">
                  <el-input v-model="state.passwordForm.authenticationNumber" placeholder="인증번호 입력"></el-input>
                </el-col>
                <el-col :span="5">
                  <el-button type="info" @click="confirmAuthenticationNumber">인증번호확인</el-button>
                </el-col>
                <el-col :span="5" v-if="state.isAuthNumConfirmed">
                  인증완료
                </el-col>
              </el-row>
            </el-form-item>
          </el-form>
        </el-main>
        <div class="info-font">다 입력 하셨나요?</div>
        <el-row justify="center">
          <el-button class="select-btn" @click="clickCheckUser" :disabled="state.isDisabled">
            <span class="iconify" data-inline="false" data-icon="noto:man-gesturing-ok" style="font-size: 80px;"></span>
            <div class="select-font">다 입력했어~</div>
          </el-button>
        </el-row>
          <!-- <el-button type="primary" :disabled="state.isDisabled" @click="clickLogin">로그인</el-button> -->
      </el-container>
    </el-col>
  </el-row>
</template>

<script>
// import { useRouter } from 'vue-router'
import { reactive, computed, ref } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import BackButton from '@/views/components/BackButton'
export default {
  name: 'Password',
  components: {
    BackButton
  },
  // setup() {
  //   const router = useRouter()

  // }
  setup(props) {
    const store = useStore()
    const router = useRouter()
    // 마운드 이후 바인딩 될 예정 - 컨텍스트에 노출시켜야함. <return>
    const passwordForm = ref(null)
    /*
      // Element UI Validator
      // rules의 객체 키 값과 form의 객체 키 값이 같아야 매칭되어 적용됨
      //
    */
    const state = reactive({
      passwordForm: {
        userId: '',
        userPhone: '',
        authenticationNumber: '',
      },
      rules: {
        userId: [
          { required: true, message: '필수 입력 항목입니다.', trigger: 'blur' },
          { max: 16, message: '최대 16자까지 입력 가능합니다.', trigger: 'blur' }
        ],
        userPhone: [
          { required: true, message: '필수 입력 항목입니다.', trigger: 'blur' },
        ],
      },
      dialogVisible: computed(() => props.open),
      formLabelWidth: '100px',
      isDisabled: true,
      isAuthNumConfirmed: false,
    })

    const checkValidation = function () {
      passwordForm.value.validate((valid) => {
        if (valid && state.isAuthNumConfirmed) {
          state.isDisabled = false
        } else {
          state.isDisabled = true
        }
      })
    }

    const sendAuthenticationNumber = function() {
      const payload = {
        authValid: true,
        phoneNumber: state.passwordForm.userPhone
      }
      store.commit('root/loadingOn')
      store.dispatch('root/requestAuthenticationNumber', payload)
      .then(() => {
        // console.log(res)
        store.commit('root/loadingOff')
        alert('인증번호가 발송되었습니다.')
      }) 
      .catch(err => {
        store.commit('root/loadingOff')
        console.log(err)
      })
    }

    const confirmAuthenticationNumber = function() {
      const param = {
        phoneNumber: state.passwordForm.userPhone,
        authNum: state.passwordForm.authenticationNumber
      }
      store.commit('root/loadingOff')
      store.dispatch('root/requestConfirmAuthNum', param)
      .then(res => {
        store.commit('root/loadingOff')
        if (res.data.data == "true") {
          state.isAuthNumConfirmed = true
          checkValidation()
          alert('인증되셨습니다.')
        } else {
          alert('인증에 실패했습니다. 다시 인증해주세요.')
        }
      })
      .catch(err => {
        store.commit('root/loadingOff')
        console.log(err)
      })
    }

    const clickCheckUser = function() {
      store.commit('root/loadingOff')
      store.dispatch('root/requestCheckUser', {userId: state.passwordForm.userId, userPhone: state.passwordForm.userPhone})
      .then(res => {
        store.commit('root/loadingOff')
        if (res.data.status == "success") {
          router.push({
            name: 'Password2',
            params: {userId: state.passwordForm.userId}
          })
        } else {
          alert('해당하는 사용자가 존재하지 않습니다.')
        }
      })
      .catch(err => {
        store.commit('root/loadingOff')
        console.log(err)
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

    return { passwordForm, state, checkValidation, sendAuthenticationNumber, confirmAuthenticationNumber, clickCheckUser, clickTTS}
  }
}

</script>

<style scoped>
  .login-container {
    position: absolute;
    width: 80%;
    top: 50%;
    left: 50%;
    background: white;
    transform: translate(-50%, -50%);
    background: rgba(255, 255, 255, 0.5);
    border-radius: 40px !important;
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
  .select-btn {
    width: 180px;
    height: 180px;
    background: #EBC86F !important;
    border-radius: 40px !important;
  }
  .select-font {
    font-family: BlackHanSans;
    font-size: 24px;
  }
  .info-font {
    text-align: center;
    font-family: SangSangFlowerRoad;
    font-size: 36px;
    color: rgba(248, 141, 141, 1);  
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
    background-image: url('../../../assets/images/main.png');
  }
  .back-btn {
    position: absolute;
    bottom: 10px;
    left: 10px;
  }
</style>