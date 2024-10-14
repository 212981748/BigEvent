import './assets/main.scss'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import router from '@/router'   
import { createPinia } from 'pinia'
import { createApp } from 'vue'
import App from './App.vue'
import { createPersistedState } from 'pinia-persistedstate-plugin'
import zhCn from 'element-plus/es/locale/lang/zh-cn'
const app = createApp(App)
const pinia = createPinia()
const persist = createPersistedState()
pinia.use(persist)
app.use(ElementPlus, { locale: zhCn })
app.use(pinia)

app.use(ElementPlus)
app.use(router)

app.mount('#app')

