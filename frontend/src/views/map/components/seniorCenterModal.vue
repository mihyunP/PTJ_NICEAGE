<template>
  <el-dialog
    :title="centerInfo.seniorName"
    v-model="dialogVisible"
    width="30%">
    <span>This is a message</span>
    <button @click="clickEnter">입장</button>
    <template #footer>
        <span class="dialog-footer">
        <el-button @click="dialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="state.dialogVisible = false">Confirm</el-button>
        </span>
    </template>
  </el-dialog>
</template>

<script>

export default {
  name: 'SeniorCenterModal',
  props: {
    centerInfo: {
      type: Object
    },
    visible: {
      type: Boolean
    }
  },
  data() {
    return {
      dialogVisible: this.visible
    }
  },

  setup() {
  },
}
</script>

<style>

</style>