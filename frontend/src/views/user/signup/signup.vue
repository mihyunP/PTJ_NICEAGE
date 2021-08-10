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
    <el-popover
      placement="top-start"
      :width="230"
      trigger="hover"
      content="뒤로 가려면 화살표를 클릭해주세요."
    >
      <template #reference>
        <el-button class="back-btn" @click="$router.go(-1)">
          <span class="iconify" data-inline="false" data-icon="akar-icons:arrow-back-thick-fill" style="color: #f88d8d; font-size: 100px;" ></span>
        </el-button>
      </template>
    </el-popover>
    <el-col class="right-content" :span="12">
      <el-row justify="center" align="middle">
        <el-col :span="20">
          <el-container class="signup-container">
            <el-header>
              <div class="info-font">회원가입</div>
            </el-header>
            <el-main>
              <el-form :model="state.signupForm" :rules="state.rules" ref="signupForm" label-position="top">
                <el-form-item prop="userId" label="아이디를 만들어주세요." :label-width="state.formLabelWidth" >
                  <el-row>
                    <el-col :span="18">
                      <el-input v-model="state.signupForm.userId" autocomplete="off"></el-input>
                    </el-col>
                    <el-col :span="6">
                      <el-button type="info" @click="clickOverlap">중복확인</el-button>
                    </el-col>
                  </el-row>
                </el-form-item>
                <el-form-item prop="userPassword" label="비밀번호를 만들어주세요." :label-width="state.formLabelWidth">
                  <el-input v-model="state.signupForm.userPassword" autocomplete="off" show-password @keyup="checkValidation"></el-input>
                </el-form-item>
                <el-form-item prop="userPasswordConfirmation" label="위의 비밀번호를 다시 입력해주세요." :label-width="state.formLabelWidth">
                  <el-input v-model="state.signupForm.userPasswordConfirmation" autocomplete="off" show-password></el-input>
                </el-form-item>
                <el-form-item prop="userName" label="성함을 입력해주세요." :label-width="state.formLabelWidth" >
                  <el-input v-model="state.signupForm.userName" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item prop="userGender" label="성별을 선택해주세요.">
                  <el-row justify="start">
                    <el-radio-group v-model="state.signupForm.userGender">
                      <el-radio label="M">남자</el-radio>
                      <el-radio label="F">여자</el-radio>
                    </el-radio-group>
                  </el-row>
                </el-form-item>
                <el-row justify="start">생년월일을 입력해주세요.</el-row>
                <br>
                <el-row>
                  <el-col :span="4">
                    <el-form-item prop="userBirthYear">
                      <el-input v-model="state.signupForm.userBirthYear" placeholder=""></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="1">년</el-col>
                  <el-col :span="3" :offset="1">
                    <el-form-item prop="userBirthMonth">
                      <el-input v-model="state.signupForm.userBirthMonth" placeholder=""></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="1">월</el-col>
                  <el-col :span="3" :offset="1">
                    <el-form-item prop="userBirthDay">
                      <el-input v-model="state.signupForm.userBirthDay" placeholder=""></el-input>
                    </el-form-item>
                  </el-col>
                  <el-col :span="1">일</el-col>
                </el-row>
                <el-form-item prop="userAddress" label="주소를 입력해주세요." :label-width="state.formLabelWidth">
                  <el-row justify="start">
                    <el-button @click="execDaumPostcode">주소 찾기</el-button>
                  </el-row>
                  <el-input v-model="state.signupForm.userAddress" placeholder="주소"></el-input>
                  <el-input id="detailAddress" v-model="state.signupForm.userDetailAddress" placeholder="상세주소"></el-input>
                </el-form-item>

                <el-form-item prop="userPhone" label="휴대폰 번호를 입력해주세요." :label-width="state.formLabelWidth">
                  <el-row>
                    <el-col :span="12">
                      <el-input v-model="state.signupForm.userPhone" placeholder="휴대폰 번호를 입력해주세요."></el-input>
                    </el-col>
                    <el-col :span="6">
                      <el-button type="info" @click="sendAuthenticationNumber">인증번호발송</el-button>
                    </el-col>
                  </el-row>
                  <el-row style="margin-top: 2px;">
                    <el-col :span="8">
                      <el-input v-model="state.signupForm.authenticationNumber" placeholder="인증번호 입력"></el-input>
                    </el-col>
                    <el-col :span="5">
                      <el-button type="info" @click="confirmAuthenticationNumber">인증번호확인</el-button>
                    </el-col>
                    <el-col :span="5" v-if="state.isAuthNumConfirmed">
                      인증완료
                    </el-col>
                  </el-row>
                </el-form-item>
                <el-form-item prop="userEmergency" label="비상연락망(가족, 보호자)을 입력해주세요." :label-width="state.formLabelWidth">
                  <el-input v-model="state.signupForm.userEmergency" autocomplete="off"></el-input>
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
import { useRouter } from 'vue-router'
export default {
  name: 'Signup',
  // setup() {
  //   const router = useRouter()

  // }
  setup(props) {
    const store = useStore()
    const router = useRouter()
    // 마운드 이후 바인딩 될 예정 - 컨텍스트에 노출시켜야함. <return>
    const signupForm = ref(null)
    /*
      // Element UI Validator
      // rules의 객체 키 값과 form의 객체 키 값이 같아야 매칭되어 적용됨
      //
    */
    const state = reactive({
      signupForm: {
        userId: '',
        userPassword: '',
        userPasswordConfirmation: '',
        userName: '',
        userGender: '',
        userBirth: '',
        userBirthYear: '',
        userBirthMonth: '',
        userBirthDay: '',
        userAddress: '',
        userDetailAddress: '',
        userPhone: '',
        userEmergency: "",
        authenticationNumber: "",
      },
      rules: {
        userId: [
          { required: true, message: '필수 입력 항목입니다.', trigger: 'blur' },
          { max: 16, message: '최대 16자까지 입력 가능합니다.', trigger: 'blur' }
        ],
        userPassword: [
          { required: true, message: '필수 입력 항목입니다.', trigger: 'blur' },
          { min: 9, message: '최소 9글자를 입력해야 합니다.', trigger: 'blur' },
          { max: 16, message: '최대 16글자까지 입력 가능합니다.', trigger: 'blur' },
        ],
        userPasswordConfirmation: [
          { required: true, message: '필수 입력 항목입니다.', trigger: 'blur' },
          { min: 9, message: '최소 9글자를 입력해야 합니다.', trigger: 'blur' },
          { max: 16, message: '최대 16글자까지 입력 가능합니다.', trigger: 'blur' },
        ],
        userName: [
          { required: true, message: '필수 입력 항목입니다.', trigger: 'blur' },
          { max: 30, message: '최대 30자까지 입력 가능합니다.', trigger: 'blur' }
        ],
        // userGender: [
        //   { required: true, message: '필수 입력 항목입니다.', trigger: 'blur' },
        // ],
        userBirthYear: [
          { required: true, message: '필수 입력 항목입니다.', trigger: 'blur' },
        ],
        userBirthMonth: [
          { required: true, message: '필수 입력 항목입니다.', trigger: 'blur' },
        ],
        userBirthDay: [
          { required: true, message: '필수 입력 항목입니다.', trigger: 'blur' },
        ],
        userAddress: [
          { required: true, message: '필수 입력 항목입니다.', trigger: 'blur' },
        ],
        userPhone: [
          { required: true, message: '필수 입력 항목입니다.', trigger: 'blur' },
        ],
        // emergency: [
        //   { required: false, message: '필수 입력 항목입니다.', trigger: 'blur' },
        // ],
      },
      dialogVisible: computed(() => props.open),
      formLabelWidth: '200px',
      isDisabled: true,
      isAuthNumConfirmed: false,
    })

    const clickSignup = function () {
      // 로그인 클릭 시 validate 체크 후 그 결과 값에 따라, 로그인 API 호출 또는 경고창 표시
      signupForm.value.validate((valid) => {
        if (valid) {
          store.commit('root/loadingOn')
          state.signupForm.userAddress = state.signupForm.userAddress + ' ' + state.signupForm.userDetailAddress
          state.signupForm.userBirth = state.signupForm.userBirthYear + '-' + state.signupForm.userBirthMonth + '-' + state.signupForm.userBirthDay
          console.log(state.signupForm)
          store.dispatch('root/requestSignup', state.signupForm)
          .then(function () {
            store.commit('root/loadingOff')
          })
          .then(() => {
            initSignupForm()
            router.push({
              name: 'Login'
            })
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
      const userId = state.signupForm.userId
      store.commit('root/loadingOn')
      store.dispatch('root/requestOverlapped', {userId: userId})
      .then((res) => {
        console.log(res)
        store.commit('root/loadingOff')
        if (res.data.data) {
          alert("이미 존재하는 아이디입니다.")
        } else {
          alert("사용가능한 아이디입니다.")
        }
      })
      .catch((err) => {
        store.commit('root/loadingOff')
        alert(err)
      })
    }

    const initSignupForm = function () {
      state.signupForm.userId = ''
      state.signupForm.userPassword = ''
      state.signupForm.userPasswordConfirmation = ''
      state.signupForm.userName = ''
      state.signupForm.userGender = ''
      state.signupForm.userBirth = ''
      state.signupForm.userBirthYear = ''
      state.signupForm.userBirthMonth = ''
      state.signupForm.userBirthDay = ''
      state.signupForm.userAddress = ''
      state.signupForm.userDetailAddress = ''
      state.signupForm.userPhone = ''
      state.signupForm.userEmergency = ''
    }

    const checkValidation = function () {
      signupForm.value.validate((valid) => {
        if (valid && state.isAuthNumConfirmed) {
          state.isDisabled = false
        } else {
          state.isDisabled = true
        }
      })
    }

    const execDaumPostcode = function() {
      new window.daum.Postcode({
        oncomplete: function(data) {
          // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

          var addr = ''; // 주소 변수
          // var extraAddr = ''; // 참고항목 변수

          //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
          if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
              addr = data.roadAddress;
          } else { // 사용자가 지번 주소를 선택했을 경우(J)
              addr = data.jibunAddress;
          }

          state.signupForm.userAddress = addr;
          // 커서를 상세주소 필드로 이동한다.
          document.getElementById("detailAddress").focus();
        }
      }).open();
    }


    // 휴대폰에 인증번호 요청
    const sendAuthenticationNumber = function() {
      const payload = {
        authValid: true,
        phoneNumber: state.signupForm.userPhone
      }
      store.commit('root/loadingOn')
      store.dispatch('root/requestAuthenticationNumber', payload)
      .then(res => {
        console.log(res)
        store.commit('root/loadingOff')
        alert('인증번호가 발송되었습니다.')
      }) 
      .catch(() => {
        store.commit('root/loadingOff')
        alert('인증번호 오류가 발생했습니다.')
      })
    }

    const confirmAuthenticationNumber = function() {
      const param = {
        phoneNumber: state.signupForm.userPhone,
        authNum: state.signupForm.authenticationNumber
      }
      store.commit('root/loadingOn')
      store.dispatch('root/requestConfirmAuthNum', param)
      .then(res => {
        console.log(res.data.data)
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

    return { signupForm, state, clickSignup, clickOverlap, checkValidation, sendAuthenticationNumber, execDaumPostcode, confirmAuthenticationNumber }
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
    background: rgba(255, 255, 255, 0.5);
    border-radius: 40px !important;
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
  .back-btn {
    position: absolute;
    bottom: 10px;
    left: 10px;
  }
</style>