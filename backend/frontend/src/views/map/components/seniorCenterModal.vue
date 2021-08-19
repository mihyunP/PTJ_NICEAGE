<template>
  <el-dialog
    v-model="state.dialogVisible"
    width="70%"
    :before-close="handleClose"
    top="5vh">
    <span class="center-title">{{ centerInfo.seniorName }}</span>
    <el-card class="card-container">
      <template #header class="card-header">
        <div>
        <el-row class="room-title">
          <el-col :span="4">방제목</el-col>
          <el-col :span="12">인원수</el-col>
          <el-col :span="8">입장</el-col>
        </el-row>
        </div>
      </template>
      <div
      v-for="(personnel, idx) in personnelList" 
      :key="idx" 
      class="room-element">
        <el-row>
          <el-col :span="4">
            <span>{{state.roomList[idx]}}방</span>
          </el-col>
          <el-col :span="12">
            <span>{{personnel}} / 9</span>
          </el-col>
          <el-col :span="8">
            <el-button class="select-btn" @click="clickSeniorCenter(idx, personnel)">입장하기</el-button>
          </el-col>
        </el-row>
        <el-divider></el-divider>
      </div>
    </el-card>
<!-- 
    <template #footer>
      <span class="dialog-footer">
      <el-button @click="handleClose">취소</el-button>
      </span>
    </template> -->
  </el-dialog>
</template>

<script>
import { reactive, computed } from 'vue'
import { useRouter } from 'vue-router'
import { useStore } from 'vuex'
export default {
  name: 'SeniorCenterModal',
  props: {
    centerInfo: {
      type: Object
    },
    visible: {
      type: Boolean
    },
    personnelList: {
      type: Array
    }
  },

  setup(props, {emit}) {
    const store = useStore()
    const router = useRouter()
    const state = reactive({
      dialogVisible: computed(() => props.visible),
      roomList: ['개나리', '진달래', '장미', '매화', '해바라기']
    })

    const handleClose = () => {
      emit('closeCenterDialog')
    }
    const clickSeniorCenter = (idx, personnel) => {
      if (personnel >= 9) {
        alert('정원초과로 입장할 수 없습니다.')
      } else {
        const sessionId = String(props.centerInfo.seniorId) + '-' + String(idx)
        const centerName = props.centerInfo.seniorName
        const myId = store.getters['root/getMyId']
        store.dispatch('root/requestEnter', {seniorId: props.centerInfo.seniorId, userId: myId})
        .then((res) => {
          console.log('자주가는 경로당 응답:', res)
          store.dispatch('root/requestMyDetail', myId)
          .then((res) => {
            const myName = res.data.data.userName
            console.log(sessionId, myName)
            router.push({
              name: 'SeniorCenter',
              params: {mySessionId: sessionId, myUserName: myName, myCenterName: centerName}
            })
          })
        })
        .catch((err)=> {
          alert(err)
        })
      }
    }
    return {state, handleClose, clickSeniorCenter}

  },
}
</script>

<style scoped>
  .center-title {
    font-family: SangSangFlowerRoad;
    font-size: 48px;
    text-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);     
  }
  .card-container {
    margin-top: 30px;
  }
  .card-header {
    margin: 0;
  }
  .room-title {
    font-family: BlackHanSans;
    font-size: 36px;
  }
  .room-element {
    font-family: BlackHanSans;
    font-size: 36px;
  }
  .select-btn {
    background: #EBC86F !important;
    border-radius: 20px !important;
    font-family: BlackHanSans;
    font-size: 36px;
  }
</style>