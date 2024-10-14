import UserInfo from "@/views/user/UserInfo.vue";
import { defineStore } from "pinia";
import { ref } from "vue";

export const useUserInfoStore = defineStore('userInfo', {
  state: () => ({
      info: {}
    //   userInfo:{
    //     nickname: ref(''),
    //     username: ref(''),
    //     email: ref('')
    //   }
  }),
  actions: {
      setUserInfo(data) {
          this.info = data; // 更新用户信息
      }
  }
});