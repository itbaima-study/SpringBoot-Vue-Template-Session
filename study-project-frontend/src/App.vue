<script setup>
import {get} from "@/net";
import {useStore} from "@/stores";
import router from "@/router";

const store = useStore()

if(store.auth.user == null) {
    get('/api/user/me', (message) => {
        store.auth.user = message
        router.push('/index')
    }, () => {
        store.auth.user = null
    })
}
</script>

<template>
  <router-view/>
</template>

<style scoped>

</style>
