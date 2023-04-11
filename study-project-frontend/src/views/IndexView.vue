<template>
  <div>
      欢迎{{store.auth.user.username}}进入到学习平台
  </div>
  <div>
      <el-button @click="logout()" type="danger" plain>退出登录</el-button>
  </div>
</template>

<script setup>
import {get} from "@/net";
import {ElMessage} from "element-plus";
import router from "@/router";
import {useStore} from "@/stores";

const store = useStore()

const logout = () => {
    get('/api/auth/logout', (message) => {
        ElMessage.success(message)
        store.auth.user = null
        router.push('/')
    })
}
</script>

<style scoped>

</style>