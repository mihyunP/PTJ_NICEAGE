<template>
  <el-row class="main-content">
    <el-col class="left-content" :span="12">
      <el-row class="main-content" justify="center" align="middle">
        <el-col :span="24">
          <el-row justify="center"><div class="main-image"></div></el-row>
          <div class="explanation">회원가입 때 입력했던 아이디와 비밀번호를 입력해주세요.</div>
          <span class="iconify" data-inline="false" data-icon="el:speaker" style="font-size: 100px;"></span>
        </el-col>
      </el-row>
    </el-col>
    <el-col class="right-content" :span="12">
      <el-container class="login-container">
        <el-header>
          <div class="info-font">로그인</div>
          <div>사진</div>
        </el-header>
        <el-main>
          <el-form :model="state.form" :rules="state.rules" ref="loginForm" :label-position="state.form.align">
            <el-form-item prop="id" label="아이디" :label-width="state.formLabelWidth" >
              <el-input v-model="state.form.id" @keyup="checkValidation" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item prop="password" label="비밀번호" :label-width="state.formLabelWidth">
              <el-input v-model="state.form.password" @keypress="checkValidation" autocomplete="off" show-password></el-input>
            </el-form-item>
          </el-form>
        </el-main>
        <el-footer>
          <el-button type="primary" :disabled="state.isDisabled" @click="clickLogin">로그인</el-button>
        </el-footer>
      </el-container>
    </el-col>
  </el-row>
</template>

<script>
// import { useRouter } from 'vue-router'
import { reactive, computed, ref } from 'vue'
import { useStore } from 'vuex'
export default {
  name: 'Login',

  // setup() {
  //   const router = useRouter()

  // }
  setup(props) {
    const store = useStore()
    // 마운드 이후 바인딩 될 예정 - 컨텍스트에 노출시켜야함. <return>
    const loginForm = ref(null)
    /*
      // Element UI Validator
      // rules의 객체 키 값과 form의 객체 키 값이 같아야 매칭되어 적용됨
      //
    */
    const state = reactive({
      form: {
        id: '',
        password: '',
        align: 'left'
      },
      rules: {
        id: [
          { required: true, message: '필수 입력 항목입니다.', trigger: 'blur' },
          { max: 16, message: '최대 16자까지 입력 가능합니다.', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '필수 입력 항목입니다.', trigger: 'blur' },
          { min: 9, message: '최소 9글자를 입력해야 합니다.', trigger: 'blur' },
          { max: 16, message: '최대 16글자까지 입력 가능합니다.', trigger: 'blur' },
          // { validator: 16, message: '비밀번호는 영문, 숫자, 특수문자가 조합되어야 합니다.', trigger: 'blur' }
        ]
      },
      dialogVisible: computed(() => props.open),
      formLabelWidth: '120px',
      isDisabled: true,
    })

    const clickLogin = function () {
      // 로그인 클릭 시 validate 체크 후 그 결과 값에 따라, 로그인 API 호출 또는 경고창 표시
      loginForm.value.validate((valid) => {
        if (valid) {
          console.log('submit')
          store.commit('root/loadingOn')
          store.dispatch('root/requestLogin', { id: state.form.id, password: state.form.password })
          .then(function (result) {
            const token = result.data.accessToken
            localStorage.setItem('access_token', token)
            store.commit('root/updateToken', token)
          })
          .then(() => {
            store.commit('root/loadingOff')
          })
          .catch(function (err) {
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

    return { loginForm, state, clickLogin, checkValidation}
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
    width: 270px;
    height: 270px;
    background: #EBC86F !important;
    border-radius: 40px !important;
  }
  .select-font {
    font-family: BlackHanSans;
    font-size: 36px;
  }
  .info-font {
    text-align: start;
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