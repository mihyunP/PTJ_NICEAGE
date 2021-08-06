<template>
  <el-row class="main-content">
    <el-col class="left-content" :span="12">
      <el-row justify="center" align="middle">
        <el-col :span="24">
          <el-row justify="center"><div class="main-image"></div></el-row>
          <div class="explanation">회원가입에 필요한 정보를 입력하시면 서비스를 이용하실 수 있어요.</div>
          <span class="iconify" data-inline="false" data-icon="el:speaker" style="font-size: 100px;"></span>
        </el-col>
      </el-row>
    </el-col>
    <el-col class="right-content" :span="12">
      <el-row justify="center" align="middle">
        <el-col :span="20">
          <el-container class="signup-container">
            <el-header>
              <div class="info-font">회원가입</div>
              <div>사진</div>
            </el-header>
            <el-main>
              <el-form :model="state.signupForm" :rules="state.rules" ref="signupForm" label-position="top">
                <el-form-item prop="id" label="아이디를 만들어주세요." :label-width="state.formLabelWidth" >
                  <el-row>
                    <el-col :span="18">
                      <el-input v-model="state.signupForm.id" autocomplete="off"></el-input>
                    </el-col>
                    <el-col :span="6">
                      <el-button type="info" size="mini" @click="clickOverlap">중복확인</el-button>
                    </el-col>
                  </el-row>
                </el-form-item>
                <el-form-item prop="password" label="비밀번호를 만들어주세요." :label-width="state.formLabelWidth">
                  <el-input v-model="state.signupForm.password" autocomplete="off" show-password @keyup="checkValidation"></el-input>
                </el-form-item>
                <el-form-item prop="passwordConfirmation" label="위의 비밀번호를 다시 입력해주세요." :label-width="state.formLabelWidth">
                  <el-input v-model="state.signupForm.passwordConfirmation" autocomplete="off" show-password></el-input>
                </el-form-item>
                <el-form-item prop="name" label="성함을 입력해주세요." :label-width="state.formLabelWidth" >
                  <el-input v-model="state.signupForm.name" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="성별을 선택해주세요.">
                  <el-radio-group v-model="state.signupForm.gender">
                    <el-radio label="남자"></el-radio>
                    <el-radio label="여자"></el-radio>
                  </el-radio-group>
                </el-form-item>
                <el-form-item prop="birthday" label="생년월일을 입력해주세요." :label-width="state.formLabelWidth">
                  <el-input v-model="state.signupForm.birthday" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item prop="address" label="주소를 입력해주세요." :label-width="state.formLabelWidth">
                  <el-input v-model="state.signupForm.address" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item prop="phone" label="휴대폰 번호를 입력해주세요." :label-width="state.formLabelWidth">
                  <el-input v-model="state.signupForm.phone" autocomplete="off"></el-input>
                  <el-button type="info" size="mini" @click="sendAuthenticationNumber">인증번호발송</el-button>
                </el-form-item>
                <el-form-item prop="emergency" label="비상연락망(가족, 보호자)을 입력해주세요." :label-width="state.formLabelWidth">
                  <el-input v-model="state.signupForm.emergency" autocomplete="off"></el-input>
                </el-form-item>
              </el-form>
            </el-main>
            <el-footer>
              <el-button type="primary" :disabled="state.isDisabled" @click="clickSignup">회원가입</el-button>
            </el-footer>
          </el-container>
        </el-col>
      </el-row>
    </el-col>
  </el-row>
</template>

<script>
// import { useRouter } from 'vue-router'
import { reactive, computed, ref } from 'vue'
import { useStore } from 'vuex'
export default {
  name: 'Signup',

  // setup() {
  //   const router = useRouter()

  // }
  setup(props) {
    const store = useStore()
    // 마운드 이후 바인딩 될 예정 - 컨텍스트에 노출시켜야함. <return>
    const signupForm = ref(null)
    /*
      // Element UI Validator
      // rules의 객체 키 값과 form의 객체 키 값이 같아야 매칭되어 적용됨
      //
    */
    const state = reactive({
      signupForm: {
        id: '',
        password: '',
        passwordConfirmation: '',
        name: '',
        gender: '',
        birthday: '',
        address: '',
        phone: '',
        emergency: "",
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
        ],
        passwordConfirmation: [
          { required: true, message: '필수 입력 항목입니다.', trigger: 'blur' },
          { min: 9, message: '최소 9글자를 입력해야 합니다.', trigger: 'blur' },
          { max: 16, message: '최대 16글자까지 입력 가능합니다.', trigger: 'blur' },
        ],
        name: [
          { required: true, message: '필수 입력 항목입니다.', trigger: 'blur' },
          { max: 30, message: '최대 30자까지 입력 가능합니다.', trigger: 'blur' }
        ],
        // gender: [

        // ],
        // birthday: [

        // ],
        // address: [

        // ],
        // phone: [

        // ],
        // emergency: [

        // ],
      },
      dialogVisible: computed(() => props.open),
      formLabelWidth: '200px',
      isDisabled: true,
    })

    const sendAuthenticationNumber = function () {
      console.log(state.signupForm.phone)
      const ac = new AbortController()
      ac.abort()
      navigator.credentials
        .get({
          otp: {transport: ["sms"]},
          signal: ac.signal,
        })
        .then((otp) => {
          console.log(otp.code)
        })
    }

    const clickSignup = function () {
      // 로그인 클릭 시 validate 체크 후 그 결과 값에 따라, 로그인 API 호출 또는 경고창 표시
      console.log("회원가입클릭")
      signupForm.value.validate((valid) => {
        if (valid) {
          store.commit('root/loadingOn')
          store.dispatch('root/requestSignup', state.signupForm)
          .then(function () {
            store.commit('root/loadingOff')
          })
          .then(() => {
            handleClose()
            alert("회원 가입이 완료되었습니다.")
          })
          .catch(function (err) {
            store.commit('root/loadingOff')
            alert("회원 가입에 실패하였습니다.", err)
          })
        } else {
          alert('Validate error!')
        }
      });
    }

    const clickOverlap = function() {
      const userId = state.signupForm.id
      store.dispatch('root/requestOverlapped', {userId: userId})
      .then(() => {
        alert("이미 존재하는 아이디입니다.")
      })
      .catch(() => {
        alert("사용가능한 아이디입니다.")
      })
    }

    const handleClose = function () {
      state.signupForm.deaprtment = ''
      state.signupForm.position = ''
      state.signupForm.name = ''
      state.signupForm.id = ''
      state.signupForm.password = ''
      state.signupForm.passwordConfirmation = ''
    }

    const checkValidation = function () {
      signupForm.value.validate((valid) => {
        if (valid) {
          state.isDisabled = false
        } else {
          state.isDisabled = true
        }
      })
    }

    return { signupForm, state, clickSignup, clickOverlap, checkValidation, sendAuthenticationNumber}
  }
}

</script>

<style scoped>
  .el-form-item__label {
    font-size: 20px;
  }
  .signup-container {
    margin-top: 20px;
    margin-bottom: 20px;
    background: white;
  }
  .main-content {
    height: 100%;
  }
  .right-content {
    position: relative;
    background: rgba(173, 203, 176, 0.7);
    /* height: 100%; */
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