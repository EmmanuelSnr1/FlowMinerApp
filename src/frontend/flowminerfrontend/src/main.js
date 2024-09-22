import { createApp } from 'vue'
import { createPinia } from 'pinia';
import 'flowbite/dist/flowbite.js'


// import './assets/index.css'

import './assets/index.css'
import App from './App.vue'
import router from './router'

const app = createApp(App);
const pinia = createPinia();

app.use(router);
app.use(pinia);
app.mount('#app');

