<template>
  <el-button @click="clickLogin">로그인</el-button>
  <el-button @click="clickSignup">회원가입</el-button>
</template>

<script>
import { useRouter } from 'vue-router'
export default {
  name: 'home',

  setup() {
    const router = useRouter()

    const clickLogin = () => {
      router.push({
        name: 'Login',
      })
    }
    
    const clickSignup = () => {
      router.push({
        name: 'Signup',
      })
    }

    return {clickLogin, clickSignup}
  }
}
</script>

<style>

</style>