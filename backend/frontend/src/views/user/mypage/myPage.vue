<template>
  <el-row class="main-content">
    <el-col class="left-content" :span="12">
      <el-row justify="center" align="middle">
        <el-col :span="24">
          <el-row justify="center"><div class="main-image"></div></el-row>
          <div class="explanation">회원님의 정보를 수정하거나 탈퇴할 수 있어요.</div>
          <el-button type="text" @click="clickTTS">
            <span class="iconify" data-inline="false" data-icon="el:speaker" style="font-size: 100px;"></span>
          </el-button>
        </el-col>
      </el-row>
    </el-col>
    <BackButton/>
    <el-col class="right-content" :span="12">
      <el-row justify="center" align="middle">
        <el-col :span="20">
          <el-container class="signup-container">
            <el-header>
              <div class="info-font">나의 회원정보</div>
            </el-header>
            <el-main>
              <el-form :model="state.signupForm" :rules="state.rules" ref="signupForm" label-position="top">
                <el-form-item prop="userId" label="아이디" :label-width="state.formLabelWidth" >
                  <el-row>
                    <el-col :span="18">
                      <el-input disabled v-model="state.signupForm.userId" autocomplete="off"></el-input>
                    </el-col>
                    <!-- <el-col :span="6">
                      <el-button type="info" @click="clickOverlap">중복확인</el-button>
                    </el-col> -->
                  </el-row>
                </el-form-item>
                <el-form-item prop="userName" label="성함" :label-width="state.formLabelWidth" >
                  <el-input v-model="state.signupForm.userName" autocomplete="off"></el-input>
                </el-form-item>
                <!-- <el-form-item prop="userGender" label="성별">
                  <el-row justify="start">
                    <el-radio-group v-model="state.signupForm.userGender">
                      <el-radio label="M">남자</el-radio>
                      <el-radio label="F">여자</el-radio>
                    </el-radio-group>
                  </el-row>
                </el-form-item>
                <el-row justify="start">생년월일</el-row>
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
                </el-row> -->
                <el-form-item prop="userAddress" label="주소" :label-width="state.formLabelWidth">
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
                  </el-row>
                  <div v-if="state.isAuthNumConfirmed">
                    인증완료
                  </div>
                </el-form-item>
                <el-form-item prop="userEmergency" label="비상연락망(가족, 보호자)을 입력해주세요." :label-width="state.formLabelWidth">
                  <el-input v-model="state.signupForm.userEmergency" autocomplete="off"></el-input>
                </el-form-item>
              </el-form>
            </el-main>
            <div class="info-font">회원정보를 변경하거나 탈퇴하시겠어요?</div>
            <el-row justify="center">
              <el-button class="select-btn" @click="updateMyProfile" :disabled="state.isDisabled">
                <span class="iconify" data-inline="false" data-icon="noto:man-gesturing-ok" style="font-size: 80px;"></span>
                <div class="select-font">어~ 변경해~</div>
              </el-button>
              <el-button class="select-btn" @click="state.deleteDialogVisible = true">
                <span class="iconify" data-inline="false" data-icon="noto:man-gesturing-ok" style="font-size: 80px;"></span>
                <div class="select-font">어~ 탈퇴해~</div>
              </el-button>
            </el-row>
          </el-container>
        </el-col>
      </el-row>
    </el-col>
  </el-row>

<el-dialog
  title="Tips"
  v-model="state.updateDialogVisible"
  width="30%"
  :before-close="handleClose">
  <span>회원정보가 수정되었습니다.</span>
  <template #footer>
    <span class="dialog-footer">
      <el-button @click="$router.push('/')">닫기</el-button>
    </span>
  </template>
</el-dialog>
<el-dialog
  v-model="state.deleteDialogVisible"
  width="30%"
  :before-close="handleClose">
  <span>정말 탈퇴하시겠습니까?</span>
  <template #footer>
    <span class="dialog-footer">
      <el-button @click="state.deleteDialogVisible = false">취소</el-button>
      <el-button type="primary" @click="deleteMyAccount">탈퇴</el-button>
    </span>
  </template>
</el-dialog>
</template>

<script>
// import { useRouter } from 'vue-router'
import { reactive, computed, ref, onMounted } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
import BackButton from '@/views/components/BackButton'
export default {
  name: 'Signup',
  components: {
    BackButton
  },
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
        // userBirthYear: '',
        // userBirthMonth: '',
        // userBirthDay: '',
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
      isDisabled: false,
      isAuthNumConfirmed: false,
      myId: '',
      updateDialogVisible: false,
      deleteDialogVisible: false
    })

    onMounted(() => {
      state.myId = store.getters['root/getMyId']
      store.dispatch('root/requestMyDetail', state.myId)
      .then((res) => {
        if (res.data.status == "success") {
          state.signupForm.userId = res.data.data.userId
          state.signupForm.userPassword = res.data.data.userPassword
          state.signupForm.userName = res.data.data.userName
          state.signupForm.userGender = res.data.data.userGender
          state.signupForm.userAddress = res.data.data.userAddress
          state.signupForm.userPhone = res.data.data.userPhone
          state.signupForm.userEmergency = res.data.data.userEmergency
          state.signupForm.userBirth = res.data.data.userBirth
          // this.myInfo = res.data.data
          console.log(state.signupForm)
        } else {
          alert('개인정보를 불러올 수 없습니다.')
          router.push({
            name: 'Home'
          })
        }
      })
    })

    const updateMyProfile = () => {
      signupForm.value.validate((valid) => {
        if (valid) {
          store.commit('root/loadingOn')
          state.signupForm.userAddress = state.signupForm.userAddress + ' ' + state.signupForm.userDetailAddress
          state.signupForm.userBirth = state.signupForm.userBirthYear + '-' + state.signupForm.userBirthMonth + '-' + state.signupForm.userBirthDay
          store.dispatch('root/requestChangeUserInfo', state.signupForm)
          .then(function (res) {
            console.log(res)
            store.commit('root/loadingOff')
            initSignupForm()
            state.updateDialogVisible = true
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

    const deleteMyAccount = function() {
      store.dispatch('root/requestDeleteAccount', state.myId)
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
        state.valid = valid
        if (valid) {
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

    return { signupForm, state, updateMyProfile, checkValidation, sendAuthenticationNumber, execDaumPostcode, confirmAuthenticationNumber, deleteMyAccount, clickTTS }
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
    padding-bottom: 30px;
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