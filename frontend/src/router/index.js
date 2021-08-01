import { createRouter, createWebHistory } from "vue-router";
import Home from '@/views/home/home'
import Login from '@/views/user/login/login'
import Signup from '@/views/user/signup/signup'
import MainSelect from '@/views/contents_select/main_select/mainSelect'
import SeniorCenter from '@/views/contents/senior_center/seniorCenter'
import FriendSelect from '@/views/contents_select/friend_select/friendSelect'
import HealthSelect from '@/views/contents_select/health_select/healthSelect'
import GameSelect from '@/views/contents_select/game_select/gameSelect'
import Board from '@/views/contents/community/board'
import Mypage from '@/views/user/mypage/myPage'

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
    path: "/seniorcenter",
    name: "SeniorCenter",
    component: SeniorCenter,
  },
  {
    path: "/friendselect",
    name: "FriendSelect",
    component: FriendSelect,
  },
  {
    path: "/healthselect",
    name: "HealthSelect",
    component: HealthSelect,
  },
  {
    path: "/gameselect",
    name: "GameSelect",
    component: GameSelect,
  },
  {
    path: "/board",
    name: "Board",
    component: Board,
  },
  {
    path: "/mypage",
    name: "Mypage",
    component: Mypage,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
