import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import ElementPlus from 'element-plus';
import 'element-plus/lib/theme-chalk/index.css';
import 'element-plus/lib/theme-chalk/display.css';
import IconifyIcon from '@iconify/vue';


// createApp(App).use(store).use(router).mount("#app");
const app = createApp(App)
app.use(store)
app.use(router)
app.use(ElementPlus)
app.component('IconifyIcon', IconifyIcon)
app.mount('#app')
