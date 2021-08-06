<template>
  <div class="msg-container">
    <div v-if="!msg.isMe" class="user-left">
      {{ msg.username }}
    </div>
    <br>
    <div v-if="msg.isMe" class="box1 sb1">
      {{ msg.message }}
    </div>
    <div v-else class="box2 sb2">
      {{ msg.message }}
    </div>
  </div>
</template>

<script>
export default {
  name: 'Message',
  props: {
    msg: {
      type: String
    },
    isMe: {
      type: Boolean
    }
  },
}
</script>

<style scoped>
  .msg-container {
    position: relative;
    height: 120px;
  }
  .user-left {
    position:absolute;
    left:20px;
  }
  .box1 {
    margin: auto;
    background: #F3F3F3;
    padding: 30px;
    text-align: center;
    font-weight: 900;
    color: black;
    font-size: 20px;
    position:absolute;
    right:20px;
  }
  .box2 {
    margin: auto;
    margin-top: 5px;
    background: #F3F3F3;
    padding: 30px;
    text-align: center;
    color: black;
    font-size: 20px;
    color: black;
    position:absolute;
    left:20px;
  }
  .sb1:before {
    content: "";
    width: 0px;
    height: 0px;
    position: absolute;
    border-left: 10px solid #F3F3F3;
    border-right: 10px solid transparent;
    border-top: 10px solid #F3F3F3;
    border-bottom: 10px solid transparent;
    right: -20px;
    top: 6px;
  }
  .sb2:before {
    content: "";
    width: 0px;
    height: 0px;
    position: absolute;
    border-left: 10px solid transparent;
    border-right: 10px solid #F3F3F3;
    border-top: 10px solid #F3F3F3;
    border-bottom: 10px solid transparent;
    left: -20px;
    top: 6px;
  }
</style>