<template>
  <el-container
    v-loading="loading"
    class="main-wrapper">
    <main-header class="main-header"/>
    <el-container class="main-container">
      <el-main class="main">
        <router-view :key="$route.fullPath"></router-view>
      </el-main>
    </el-container>
    <main-footer/>
  </el-container>
</template>

<script>
import MainHeader from './components/main-header.vue'
import MainFooter from './components/main-footer.vue'
import { useStore } from 'vuex'
import { computed } from 'vue'

export default {
  name: 'Main',
  components: {
    MainHeader,
    MainFooter,
  },
  setup() {
  const store = useStore()
  const loading = computed(() => store.getters['root/getLoading'])

  return { loading }
  } 
}
</script>

<style>
  @import '../../common/css/common.css';


/* @media (max-width: 700px) {
  .main-container {
    height: calc(100% - 275px) !important;
  }
} */

#app, .main-wrapper {
  height: 100%;
  background:rgba(255, 250, 250, 0.5);
  display: block !important;
}

.main-container {
  height: calc(100% - 4rem) !important;
}

.main {
  padding: 0 !important;
}

.main-header {
  width: 100%;
  height: 4rem;
  background: white;
}

</style>