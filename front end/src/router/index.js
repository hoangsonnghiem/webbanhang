import { createWebHistory, createRouter } from "vue-router";
import login from './components/login.vue';
import test from "@/components/test.vue"
const routes = [
    {
      path: "/login",
      name: "Login",
      component: login,
    },
    {
      path: "/test",
      name: "test",
      component: test,
    },
];
const router = createRouter({
    history: createWebHistory(),
    routes,
  });
export default router;