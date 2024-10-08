import { createRouter, createWebHistory } from 'vue-router';
import SignIn from '../views/SignIn.vue';
import Dashboard from '../views/Dashboard.vue';

// 配置路由
const routes = [
  {
    path: '/',
    name: 'SignIn',
    component: SignIn,
  },
  {
    path: '/Dashboard',
    name: 'Dashboard',
    component: Dashboard,
    meta: { requiresAuth: true }, // 添加元信息，用于判断是否需要登录
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

// 添加全局导航守卫
router.beforeEach((to, from, next) => {
  // 判断目标页面是否需要登录
  if (to.matched.some(record => record.meta.requiresAuth)) {
    // 检查是否已登录
    const isAuthenticated = localStorage.getItem('user'); // 假设登录状态保存在 localStorage 中
    if (!isAuthenticated) {
      // 如果没有登录，重定向到登录页面
      next({ name: 'SignIn' });
    } else {
      // 已登录，继续导航
      next();
    }
  } else {
    next(); // 不需要登录，直接放行
  }
});

export default router;
