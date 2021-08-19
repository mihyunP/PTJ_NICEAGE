<template>
  <el-tabs type="card" @tab-click="handleClick">
    <el-tab-pane label="채팅">
      <div>
        <MessageList :msgs="msgs"/>
        <MessageForm @getMyMsg="getMyMsg"/>
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