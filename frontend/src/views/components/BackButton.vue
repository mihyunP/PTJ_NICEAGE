<template>
  <el-popover
    placement="top-start"
    :width="230"
    trigger="hover"
    content="뒤로 가려면 화살표를 클릭해주세요."
  >
    <template #reference>
      <el-button class="back-btn" type="text" @click="$router.go(-1)">
        <span class="iconify" data-inline="false" data-icon="akar-icons:arrow-back-thick-fill" style="color: #f88d8d; font-size: 100px;" ></span>
      </el-button>
    </template>
  </el-popover>
</template>

<script>
export default {
  name: 'BackButton'
}
</script>

<style scoped>
  .back-btn {
    position: absolute;
    bottom: 10px;
    left: 10px;
  }
</style>