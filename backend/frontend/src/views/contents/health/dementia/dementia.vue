<template>
  <el-row class="main-content">
    <el-col class="left-content" :span="12">
      <el-row class="main-content" justify="center" align="middle">
        <el-col :span="24">
          <el-row justify="center"><div class="main-image"></div></el-row>
          <div class="explanation">해당 하는 항목에 체크하시면 치매 진단 결과를 확인하실 수 있습니다.</div>
          <el-button type="text" @click="clickTTS">
            <span class="iconify" data-inline="false" data-icon="el:speaker" style="font-size: 100px;"></span>
          </el-button>
<back-button/>
        </el-col>
      </el-row>
    </el-col>
    <el-col class="right-content" :span="12">
        <el-row class="main-content" justify="center" align="middle">
        <el-col :span="24">
          <div class="question">치매 진단 검사</div>

         <div v-if="state.page==1">
          <el-container class="dementia-container">
              <table>
                <tr>
                  <td align="left" colspan="1">
                    <input id="check1" type="checkbox" name="chk_1" value="1" title="증상 체크" v-model="state.selectedValues">
                  </td>
                  <td align="left" colspan="7">
                    <label for="check1">전화번호나 사람 이름을 기억하기 힘들다.</label>
                  </td>
                </tr>
                <tr>
                  <td align="left" colspan="1">
                    <input id="check2" type="checkbox" name="chk_2" value="2" title="증상 체크" v-model="state.selectedValues">
                  </td> 
                  <td align="left" colspan="7">
                  <label for="check2">어떤 일이 언제 일어났는지 기억하지 못할 때가 있다.</label>
                  </td>
                </tr>
                <tr>
                  <td align="left" colspan="1">
                    <input id="check3" type="checkbox" name="chk_3" value="3" title="증상 체크" v-model="state.selectedValues">
                  </td>
                  <td align="left" colspan="7">
                    <label for="check3">책을 읽을 때 같은 문장을 여러번 읽어야 이해가 된다.</label>
                  </td>
                </tr>
                <tr>
                  <td align="left" colspan="1">
                    <input id="check4" type="checkbox" name="chk_4" value="4" title="증상 체크" v-model="state.selectedValues">
                  </td> 
                  <td align="left" colspan="7">
                  <label for="check4">물건을 어디에 두고 나중에 어디에 두었는지 몰라 찾게된다.</label>
                  </td>
                </tr>
                <tr>
                  <td align="left" colspan="1">
                    <input id="check5" type="checkbox" name="chk_5" value="5" title="증상 체크" v-model="state.selectedValues">
                  </td>
                  <td align="left" colspan="7">
                    <label for="check5">반복되는 일상 생활에 변화가 생겼을 때 금방 적응하기가 힘들다.</label>
                  </td>
                </tr>
                <tr>
                  <td align="left" colspan="1">
                    <input id="check6" type="checkbox" name="chk_6" value="6" title="증상 체크" v-model="state.selectedValues">
                  </td> 
                  <td align="left" colspan="7">
                  <label for="check6">본인에게 중요한 사항을 잊을 때가 있다(배우자 생일, 결혼기념일 등).</label>
                  </td>
                </tr>
                <tr>
                  <td align="left" colspan="1">
                  <input id="check7" type="checkbox" name="chk_7" value="7" title="증상 체크" v-model="state.selectedValues">
                  </td>
                  <td align="left" colspan="7">
                    <label for="check7">다른 사람에게 같은 이야기를 반복할 때가 있다.</label>
                  </td>
                </tr>
              </table>
          </el-container>
          
          <el-row justify="center">
            <el-button class="my-button" @click="state.page+=1">
              <div class="bottom-button">다음</div>
            </el-button>
          </el-row>
             
        </div>

      <div v-else-if="state.page==2">
         <el-container class="dementia-container">
            <table>
                <tr>
                  <td align="left" colspan="1">
                    <input id="check8" type="checkbox" name="chk_8" value="8" title="증상 체크" v-model="state.selectedValues">
                  </td>
                  <td align="left" colspan="7">
                   <label for="check8">어떤 일을 해놓고도 잊어버려 다시 반복할 때가 있다.</label>
                  </td>
                </tr>
                <tr>
                  <td align="left" colspan="1">
                    <input id="check9" type="checkbox" name="chk_9" value="9" title="증상 체크" v-model="state.selectedValues">
                  </td> 
                  <td align="left" colspan="7">
                   <label for="check9">약속을 해놓고 잊을 때가 있다.</label>
                  </td>
                </tr>
                <tr>
                  <td align="left" colspan="1">
                   <input id="check10" type="checkbox" name="chk_10" value="10" title="증상 체크" v-model="state.selectedValues">
                  </td>
                  <td align="left" colspan="7">
                       <label for="check10">이야기 도중 방금 자기가 무슨 이야기를 하고 있었는 지 잊을 때가 있다.</label>
                  </td>
                </tr>
                <tr>
                  <td align="left" colspan="1">
                    <input id="check11" type="checkbox" name="chk_11" value="11" title="증상 체크" v-model="state.selectedValues">
                  </td> 
                  <td align="left" colspan="7">
                  <label for="check11">약 먹는 시간을 놓지기도 한다.</label>
                  </td>
                </tr>
                <tr>
                  <td align="left" colspan="1">
                    <input id="check12" type="checkbox" name="chk_12" value="12" title="증상 체크" v-model="state.selectedValues">
                  </td>
                  <td align="left" colspan="7">
                    <label for="check12">여러가지 물건을 사러 갔다가 한두 가지를 빠뜨리기도 한다.</label>
                  </td>
                </tr>
                <tr>
                  <td align="left" colspan="1">
                   <input id="check13" type="checkbox" name="chk_13" value="13" title="증상 체크" v-model="state.selectedValues">
                  </td> 
                  <td align="left" colspan="7">
                   <label for="check13">가스 불을 끄는 것을 잊어버린 적이 있다. 또는 음식을 태운 적이 있다.</label>
                  </td>
                </tr>
                <tr>
                  <td align="left" colspan="1">
                  <input id="check14" type="checkbox" name="chk_14" value="14" title="증상 체크" v-model="state.selectedValues">
                  </td>
                  <td align="left" colspan="7">
                    <label for="check14">남에게 같은 질문을 반복한다.</label>
                  </td>
                </tr>
              </table>
          </el-container>  
          <el-row justify="center">
            <el-button class="my-button" @click="state.page+=1">
              <div class="bottom-button">다음</div>
            </el-button>
          </el-row>
      </div>

      <div v-else-if="state.page==3">
         <el-container class="dementia-container">
            <table>
                <tr>
                  <td align="left" colspan="1">
                    <input id="check15" type="checkbox" name="chk_15" value="15" title="증상 체크" v-model="state.selectedValues">
                  </td>
                  <td align="left" colspan="7">
                    <label for="check15">물건을 두고 다니거나 가지고 갈 물건을 놓고 간다.</label>
                  </td>
                </tr>
                <tr>
                  <td align="left" colspan="1">
                    <input id="check16" type="checkbox" name="chk_16" value="16" title="증상 체크" v-model="state.selectedValues">
                  </td> 
                  <td align="left" colspan="7">
                  <label for="check16">어떤 일을 해놓고도 했는지 안 했는지 몰라 다시 확인해야 한다.</label>
                  </td>
                </tr>
                <tr>
                  <td align="left" colspan="1">
                    <input id="check17" type="checkbox" name="chk_17" value="17" title="증상 체크" v-model="state.selectedValues">
                  </td>
                  <td align="left" colspan="7">
                    <label for="check17">하고싶은 말이나 표현이 금방 떠오르지 않는다.</label>
                  </td>
                </tr>
                <tr>
                  <td align="left" colspan="1">
                    <input id="check18" type="checkbox" name="chk_18" value="18" title="증상 체크" v-model="state.selectedValues">
                  </td> 
                  <td align="left" colspan="7">
                 <label for="check18">물건 이름이 금방 생각나지 않는다.</label>
                  </td>
                </tr>
                <tr>
                  <td align="left" colspan="1">
                    <input id="check19" type="checkbox" name="chk_19" value="19" title="증상 체크" v-model="state.selectedValues">
                  </td>
                  <td align="left" colspan="7">
                    <label for="check19">개인적인 편지나 사무적인 편지를 쓰기 힘들다.</label>
                  </td>
                </tr>
                <tr>
                  <td align="left" colspan="1">
                    <input id="check20" type="checkbox" name="chk_20" value="20" title="증상 체크" v-model="state.selectedValues">
                  </td> 
                  <td align="left" colspan="7">
                   <label for="check20">신문이나 잡지를 읽을 때 이야기 줄거리를 파악하지 못한다.</label>
                  </td>
                </tr>
                <tr>
                  <td align="left" colspan="1">
                  <input id="check21" type="checkbox" name="chk_21" value="21" title="증상 체크" v-model="state.selectedValues">
                  </td>
                  <td align="left" colspan="7">
                    <label for="check21">과거에 쓰던 기구 사용이 서툴러졌다.</label>
                  </td>
                </tr>
              </table>
          </el-container>
          
          <el-row justify="center">
            <el-button class="my-button" @click="state.page+=1">
              <div class="bottom-button" @click="submit()">제출하기</div>
            </el-button>
          </el-row>
      </div>

        </el-col>
        </el-row>   
     
    </el-col>
  </el-row>
</template>

<script>
  // export default {
  //   name: 'Dementia',

  //   data () {
  //     return {
  //      page : 1,
  //      selectedValues : []
  //     };
  //   },
    
  //   methods: {
  //     submit(){
  //         console.log(this.selectedValues)
  //         console.log(this.selectedValues.length)
  //       // return this.selectedValues.length
  //       // const router = useRouter()
  //       if (this.selectedValues.length < 10) {
  //         console.log("under10");
  //           this.$router.push({
  //           name: 'DementiaResult1',
  //         })
  //       }else{
  //           this.$router.push({
  //           name: 'DementiaResult2',
  //         })
  //       }
  //     } 
  //   },
  // };

    import { useRouter } from 'vue-router'
    import { reactive } from 'vue'
    import { useStore } from 'vuex'
import BackButton from '../../../components/BackButton.vue'
    export default {
  components: { BackButton },
    name: 'Dementia',

    setup() {
    const store = useStore()
    const router = useRouter()

    const state = reactive({
        page : 1,
        selectedValues : [],
        result : undefined,
    })

    const submit= () => {
          state.result = state.selectedValues.length        
          console.log(state.selectedValues) // 배열? 각방 요소별 값 찍힘
          console.log(state.selectedValues.length)
        // return this.selectedValues.length

      
    
      
      const userId = store.getters['root/getMyId']
      console.log(userId)
      store.dispatch('root/requestDementiaResult', {userId : userId, result : state.result}) //`{result}?result=2`
      .then(res => {
        console.log("백->프론트 응답"+res);
        
        if (state.selectedValues.length < 10) {
          console.log("under10");
            router.push({
            name: 'DementiaResult1',
          })
        }else{
            router.push({
            name: 'DementiaResult2',
          })
        }
      })
      .catch( err => {
        console.log("에러"+err);    
        
        //   if (state.selectedValues.length < 10) {
        //   console.log("under10");
        //     router.push({
        //     name: 'DementiaResult1',
        //   })
        // }else{
        //     router.push({
        //     name: 'DementiaResult2',
        //   })
        // }

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
    }

    return { state, submit, clickTTS}
    }
  }

</script>

<style scoped>
  input[type="checkbox"]{
    width: 30px; /*Desired width*/
    height: 30px; /*Desired height*/
  }
  label{
    font-family: BlackHanSans;
    font-size: 30px;
    text-align: left;
  }
  .dementia-container {
    /* width : 100%; */
    height: 670px;
    padding: 20px;
    margin: 25px;
    background: #EFDEDE !important;
    line-height: 300%;
  }

  .my-button {
    margin: 15px;
    width: 150px;
    height: 50px;
    background: #EBC86F !important;
    border-radius: 25px !important;
  }
  .bottom-button {
    font-family: BlackHanSans;
    font-size: 30px;
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
  .question {
    font-family: SangSangFlowerRoad;
    font-size: 44px;
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
    background-image: url('../../../../assets/images/main.png');
  }
  /* 이전 페이지로 돌아가기 css */
  .previouspage {
    font-family: SangSangFlowerRoad;
    font-size: 45px;
   color: rgba(248, 141, 141, 1);
  }
</style>
