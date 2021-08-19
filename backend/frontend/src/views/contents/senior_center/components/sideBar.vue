<template>
  <el-tabs type="card" @tab-click="handleClick">
    <el-tab-pane label="채팅">
      <div>
        <MessageList :msgs="msgs"/>
        <MessageForm @getMyMsg="getMyMsg"/>
      </div>
    </el-tab-pane>
    <el-tab-pane @click="clickOtherRoomInfo" label="다른방 정보">
      <el-row class="room-title">
        <el-col :span="6">방제목</el-col>
        <el-col :span="12">인원수</el-col>
        <el-col :span="6">입장</el-col>
      </el-row>
      <div
      v-for="(personnel, idx) in personnelList" 
      :key="idx" 
      class="room-element">
        <el-row>
          <el-col :span="6">
            <span>{{roomList[idx]}}방</span>
          </el-col>
          <el-col :span="12">
            <span>{{personnel}} / 9</span>
          </el-col>
          <el-col :span="6">
            <el-button type="text" @click="clickChangeCenter(idx, personnel)">
              <span style="font-family: SangSangFlowerRoad; font-size: 20px;">입장하기</span>
            </el-button>
          </el-col>
        </el-row>
        <el-divider></el-divider>
      </div>
    </el-tab-pane>
  </el-tabs>
</template>

<script>
import MessageForm from './messageForm';
import MessageList from './messageList';
export default {
  name: 'SideBar',
  components: {
    MessageForm,
    MessageList,
  },
  props: {
    msgs: {
      type: Array
    },
    personnelList: {
      type: Array
    },
    sessionIndex: {
      type: Number
    },
    myCenterName: {
      type: String
    },
    mySessionId: {
      type: String
    }
  },
  data() {
    return {
      roomList: ['개나리', '진달래', '장미', '매화', '해바라기']
    }

  },
  methods: {
    getMyMsg(message) {
      this.$emit('getMyMsg', message)
    },
    handleClick(tab) {
      if (tab.props.label == '다른방 정보') {
        this.$emit('handleClick')
      }
    },
    clickChangeCenter(idx, personnel) {
      if (idx == this.sessionIndex) {
        alert(`이미 ${this.roomList[idx]}방에 계십니다.`)
      } else {
        this.$emit('changeSession', {idx: idx, personnel: personnel})
      }
    }
  }
}
</script>

<style scoped>
  .room-title {
    font-family: 'SSShinb7';
    font-size: 30px;
    padding-bottom: 10px;
    margin-bottom: 20px;
    background: rgba(173, 203, 176, 0.2);
    border-radius: 20px !important;
  }
  .room-element {
    font-family: 'SangSangFlowerRoad';
    font-size: 25px;
  }
</style>