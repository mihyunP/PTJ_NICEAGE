<template>
  <el-scrollbar class="list-container">
    <Message v-for="(msg, index) in msgs" :key="index" :msg="msg" />
  </el-scrollbar>
  <!-- <div class="container">
    <Message v-for="(msg, index) in msgs" :key="index" :msg="msg" />
  </div> -->
</template>

<script>
import Message from './message'
export default {
  name: 'MessageList',
  components: {
    Message,
  },
  props: {
    msgs: {
      type: Array
    },
    senderObj: {
      type: Object
    },
    me: {
      type: Object
    }
  },
}
</script>

<style scoped>
.list-container {
  height: 60vh;
  background: white;
}
</style>