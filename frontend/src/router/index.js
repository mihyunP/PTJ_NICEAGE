import { createRouter, createWebHistory } from "vue-router";
import Home from '@/views/home/home'
import Login from '@/views/user/login/login'
import Signup from '@/views/user/signup/signup'
import MainSelect from '@/views/contents_select/main_select/mainSelect'
import FriendSelect from '@/views/contents_select/friend_select/friendSelect'
import GameSelect from '@/views/contents_select/game_select/gameSelect'
import HealthSelect from '@/views/contents_select/health_select/healthSelect'

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/login",
    name: "Login",
    component: Login,
  },
  {
    path: "/signup",
    name: "Signup",
    component: Signup,
  },
  {
    path: "/mainselect",
    name: "MainSelect",
    component: MainSelect,
  },
  {
    path: "/friendselect",
    name: "FriendSelect",
    component: FriendSelect,
  },
  {
    path: "/gameselect",
    name: "GameSelect",
    component: GameSelect,
  },
  {
    path: "/healthselect",
    name: "HealthSelect",
    component: HealthSelect,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
