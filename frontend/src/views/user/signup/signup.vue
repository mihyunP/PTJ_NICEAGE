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
                  <input type="text" id="sample6_postcode" placeholder="우편번호">
                  <input type="button" @click="sample6_execDaumPostcode" value="우편번호 찾기"><br>
                  <input type="text" id="sample6_address" placeholder="주소"><br>
                  <input type="text" id="sample6_detailAddress" placeholder="상세주소">
                  <input type="text" id="sample6_extraAddress" placeholder="참고항목">
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

      const sample6_execDaumPostcode = function() {
          new window.daum.Postcode({
              oncomplete: function(data) {
                  // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
  
                  // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                  // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                  var addr = ''; // 주소 변수
                  var extraAddr = ''; // 참고항목 변수
  
                  //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                  if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                      addr = data.roadAddress;
                  } else { // 사용자가 지번 주소를 선택했을 경우(J)
                      addr = data.jibunAddress;
                  }
  
                  // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                  if(data.userSelectedType === 'R'){
                      // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                      // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                      if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                          extraAddr += data.bname;
                      }
                      // 건물명이 있고, 공동주택일 경우 추가한다.
                      if(data.buildingName !== '' && data.apartment === 'Y'){
                          extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                      }
                      // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                      if(extraAddr !== ''){
                          extraAddr = ' (' + extraAddr + ')';
                      }
                      // 조합된 참고항목을 해당 필드에 넣는다.
                      document.getElementById("sample6_extraAddress").value = extraAddr;
                  
                  } else {
                      document.getElementById("sample6_extraAddress").value = '';
                  }
  
                  // 우편번호와 주소 정보를 해당 필드에 넣는다.
                  document.getElementById('sample6_postcode').value = data.zonecode;
                  document.getElementById("sample6_address").value = addr;
                  // 커서를 상세주소 필드로 이동한다.
                  document.getElementById("sample6_detailAddress").focus();
              }
          }).open();
      }

    return { signupForm, state, clickSignup, clickOverlap, checkValidation, sendAuthenticationNumber, sample6_execDaumPostcode }
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