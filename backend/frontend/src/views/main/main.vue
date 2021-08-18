<template>
<<<<<<< HEAD:frontend/src/views/main/main.vue
  <el-container>
    <main-header/>
    <el-main>
      <router-view></router-view>
    </el-main>
=======
  <el-container
    v-loading="loading"
    class="main-wrapper">
    <main-header class="main-header"/>
    <el-container class="main-container">
      <el-main class="main">
        <router-view :key="$route.fullPath"></router-view>
      </el-main>
    </el-container>
>>>>>>> develope:backend/frontend/src/views/main/main.vue
    <main-footer/>
  </el-container>
</template>

<script>
import MainHeader from './components/main-header.vue'
import MainFooter from './components/main-footer.vue'
import { useStore } from 'vuex'
import { computed } from 'vue'

export default {
<<<<<<< HEAD:frontend/src/views/main/main.vue
 name: 'Main',
 components: {
   MainHeader,
   MainFooter
 } 
=======
  name: 'Main',
  components: {
    MainHeader,
    MainFooter,
  },
  mounted() {
    console.log(this.loading)
  },
  setup() {
  const store = useStore()
  const loading = computed(() => store.getters['root/getLoading'])

  return { loading }
  } 
>>>>>>> develope:backend/frontend/src/views/main/main.vue
}
</script>

<style>
  @import '../../common/css/common.css';
</style>