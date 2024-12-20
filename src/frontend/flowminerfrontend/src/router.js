import { createRouter, createWebHistory } from 'vue-router'
import Base from './views/Base.vue'
import Dashboard from './views/Dashboard.vue'
import Analytics from './views/Analytics.vue';
import Processfiles from './views/Processfiles.vue';
import Demo from './views/Demo.vue';




const routes = [
  {
    path: '/home',
    name: 'base',
    component: Base,
  },
  {
    path: '/dashboard',
    name: 'dashboard',
    component: Dashboard,
  },

  {
    path: '/analytics',
    name: 'analytics',
    component: Analytics,
  },
  {
    path: '/process-files',
    name: 'process-files',
    component: Processfiles,
  },
  {
    path: '/demo',
    name: 'demo',
    component: Demo,
  },
  
];

const router = createRouter({
  history: createWebHistory("/"),
  routes,
});

export default router;
