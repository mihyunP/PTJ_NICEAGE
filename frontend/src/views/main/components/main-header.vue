<template>
  <div>
    <el-row justify="space-between">
      <h1  @click="$router.push('/')">내 나이가 어때서</h1> <!-- mainselect page로 돌아가기 -->
      <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
        <el-menu-item index="1" v-if="state.isLoggedIn" @click="clickLogout">로그아웃</el-menu-item>
        <el-menu-item index="2" v-if="state.myId =='ssafy'" @click="clickAdmin">Admin</el-menu-item>
        <!-- <el-submenu index="2">
          <template #title>메뉴</template>
          <el-menu-item index="2-1" @click="clickLogout">로그아웃</el-menu-item>
          <el-menu-item index="2-2">item two</el-menu-item>
          <el-menu-item index="2-3">item three</el-menu-item>
        </el-submenu> -->
      </el-menu>
    </el-row>
  </div>
</template>

<script>
import { reactive, computed } from 'vue'
import { useStore } from 'vuex'
import { useRouter } from 'vue-router'
export default {
  name: 'main-header',
  setup() {
    const store = useStore()
    const router = useRouter()
    const state = reactive({
      isLoggedIn: computed(() => store.getters['root/getIsLoggedIn']),
      myId: computed(() => store.getters['root/getMyId'])
    })

    const clickLogout = () => {
      localStorage.removeItem('access_token')
      localStorage.removeItem('my_id')
      store.commit('root/deleteMyId')
      store.commit('root/deleteToken')
      router.push({
        name: 'Home'
      })
    }

    const clickAdmin = () => {
      router.push({
        name: 'Admin'
      })
    }

    return { state, clickLogout, useStore, clickAdmin }
  }
}
</script>

<style>
  h1 {
    font-family: SangSangShinb7;
    font-style: normal;
    font-weight: normal;
    font-size: 48px;
    line-height: 4rem;
    color: #26730B;
    margin: 0;
    margin-left: 2rem;
    text-align: center;
  }
</style>