<template>
  <el-tabs type="card" @tab-click="handleClick">
    <el-tab-pane label="채팅">
      <div>
        <MessageList :msgs="msgs"/>
        <MessageForm @getMyMsg="getMyMsg"/>
      </div>
    </el-tab-pane>
    <el-tab-pane label="다른방 정보">
      <el-table
        :data="tableData"
        stripe
        style="width: 100%">
        <el-table-column
          prop="name"
          label="방번호"
          width="180">
        </el-table-column>
        <el-table-column
          prop="number"
          label="인원수">
        </el-table-column>
      </el-table>
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
  },
  data() {
    return {
      tableData: [{
        name: '2번방',
        number: '3/10'
      }, {
        name: '3번방',
        number: '0/10'
      }, {
        name: '4번방',
        number: '0/10'
      }, {
        name: '5번방',
        number: '0/10'
      }]
    }
  },
  methods: {
    getMyMsg(message) {
      this.$emit('getMyMsg', message)
    },
    handleClick() {
      this.$emit('handleClick')
    }
  }
}
</script>

<style>

</style>