<template>
  <el-container class="main-wrapper">
    <main-header class="main-header"/>
    <el-container>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
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

.main-wrapper {
  background:rgba(255, 250, 250, 0.5);
  height: 100rem;
  display: block !important;
}

.main-header {
  width: 100%;
  height: 5rem;
}

</style>