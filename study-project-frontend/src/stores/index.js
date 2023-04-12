import {reactive} from 'vue'
import { defineStore } from 'pinia'

export const useStore = defineStore('store', () => {
  const auth = reactive({
    user: null
  })
  return { auth }
})
