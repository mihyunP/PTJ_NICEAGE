<template>
  <el-container>
    <main-header/>
    <el-main>
      <router-view></router-view>
    </el-main>
    <main-footer/>
  </el-container>
</template>

<script>
import MainHeader from './components/main-header.vue'
import MainFooter from './components/main-footer.vue'

export default {
 name: 'Main',
 components: {
   MainHeader,
   MainFooter
 } 
}
</script>

<style>
  @import '../../common/css/common.css';
</style>