<template>
  <el-scrollbar class="list-container">
    <Message v-for="(msg, index) in msgs" :key="index" :msg="msg"/>
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
  },
}
</script>

<style scoped>
.list-container {
  height: 65vh;
  background: rgba(173, 203, 176, 0.2);
  border-radius: 30px !important;
}
</style>