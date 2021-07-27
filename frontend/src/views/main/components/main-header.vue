<template>
  <div>
    <el-row justify="space-between">
      <h1>내 나이가 어때서</h1>
      <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
        <el-submenu index="2">
          <template #title>메뉴</template>
          <el-menu-item index="2-1">item one</el-menu-item>
          <el-menu-item index="2-2">item two</el-menu-item>
          <el-menu-item index="2-3">item three</el-menu-item>
          <el-submenu index="2-4">
            <template #title>item four</template>
            <el-menu-item index="2-4-1">item one</el-menu-item>
            <el-menu-item index="2-4-2">item two</el-menu-item>
            <el-menu-item index="2-4-3">item three</el-menu-item>
          </el-submenu>
        </el-submenu>
      </el-menu>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'main-header'
}
</script>

<style>
  h1 {
    font-family: SangSangShinb7;
    font-style: normal;
    font-weight: normal;
    font-size: 48px;
    line-height: 32px;
    color: #26730B;
    margin: 0;
  }
</style>