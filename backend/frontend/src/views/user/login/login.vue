<template>
  <el-row class="main-content">
    <el-col class="left-content" :span="12">
      <el-row class="main-content" justify="center" align="middle">
        <el-col :span="24">
          <el-row justify="center"><div class="main-image"></div></el-row>
          <div class="explanation">회원가입 때 입력했던 아이디와 비밀번호를 입력해주세요.</div>
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
          <div class="info-font">로그인</div>
          <div>사진</div>
        </el-header>
        <el-main>
          <el-form :model="state.form" :rules="state.rules" ref="loginForm" label-position="top">
            <el-form-item prop="userId" label="아이디" :label-width="state.formLabelWidth">
              <el-input v-model="state.form.userId" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item prop="userPassword" label="비밀번호" :label-width="state.formLabelWidth">
              <el-input v-model="state.form.userPassword" autocomplete="off" show-password></el-input>
            </el-form-item>
          </el-form>
        </el-main>
        <div class="info-font">다 입력 하셨나요?</div>
        <el-row justify="center">
          <el-button class="select-btn" @click="clickLogin">
            <span class="iconify" data-inline="false" data-icon="noto:man-gesturing-ok" style="font-size: 80px;"></span>
            <div class="select-font">다 입력했어~</div>
          </el-button>
          <el-button class="select-btn" @click="$router.push('/password')">
          <span class="iconify" data-inline="false" data-icon="emojione:person-gesturing-no" style="font-size: 80px;"></span>
            <div class="select-font">잊어버렸어~</div>
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
  name: 'Login',
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
    const loginForm = ref(null)
    /*
      // Element UI Validator
      // rules의 객체 키 값과 form의 객체 키 값이 같아야 매칭되어 적용됨
      //
    */
    const state = reactive({
      form: {
        userId: '',
        userPassword: '',
      },
      rules: {
        userId: [
          { required: true, message: '필수 입력 항목입니다.', trigger: 'blur' },
          { min: 4, message: '최소 4글자를 입력해야합니다.', trigger: 'blur' },
          { max: 15, message: '최대 15자까지 입력 가능합니다.', trigger: 'blur' }
        ],
        userPassword: [
          { required: true, message: '필수 입력 항목입니다.', trigger: 'blur' },
          { min: 8, message: '최소 8글자를 입력해야 합니다.', trigger: 'blur' },
          { max: 15, message: '최대 15글자까지 입력 가능합니다.', trigger: 'blur' },
          // { validator: 16, message: '비밀번호는 영문, 숫자, 특수문자가 조합되어야 합니다.', trigger: 'blur' }
        ]
      },
      dialogVisible: computed(() => props.open),
      formLabelWidth: '100px',
      isDisabled: true,
    })

    const clickLogin = function () {
      // 로그인 클릭 시 validate 체크 후 그 결과 값에 따라, 로그인 API 호출 또는 경고창 표시
      loginForm.value.validate((valid) => {
        if (valid) {
          store.commit('root/loadingOn')
          store.dispatch('root/requestLogin', state.form)
          .then(result => {
            store.commit('root/loadingOff')
            const token = result.data.auth_token
            if (token) {
              localStorage.setItem('access_token', token)
              localStorage.setItem('my_id', result.data.user.userId)
              store.commit('root/updateToken', token)
              store.commit('root/updateMyId', result.data.user.userId)
              router.push({
                name: 'Home'
              })
            } else {
              alert('아이디와 비밀번호를 다시 입력해주세요.')
            }
          })
          .catch((err) => {
            store.commit('root/loadingOff')
            alert(err)
          })
        } else {
          alert('Validate error!')
        }
      });
    }

    const checkValidation = function () {
      // loginForm 부분이 이해가 안됨
      loginForm.value.validate((valid) => {
        if (valid) {
          state.isDisabled = false
        } else {
          state.isDisabled = true
        }
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

    return { loginForm, state, clickLogin, checkValidation, clickTTS}
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
</style>