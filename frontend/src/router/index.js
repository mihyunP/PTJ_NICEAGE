import { createRouter, createWebHistory } from "vue-router";
import Home from '@/views/home/home'
import Login from '@/views/user/login/login'
import Signup from '@/views/user/signup/signup'
import MainSelect from '@/views/contents_select/main_select/mainSelect'
import SeniorCenterSelect from '@/views/contents_select/senior_center_select/senior_centerSelect'
import FriendSelect from '@/views/contents_select/friend_select/friendSelect'
import HealthSelect from '@/views/contents_select/health_select/healthSelect'
import GameSelect from '@/views/contents_select/game_select/gameSelect'
import Board from '@/views/contents/community/board'
import Mypage from '@/views/user/mypage/myPage'

import Dementia from '@/views/contents/health/dementia/dementia'
import Stretching from '@/views/contents/health/stretching/stretching'

import Map from '@/views/map/map'
import SeniorCenter from '@/views/contents/senior_center/seniorCenter'
import FriendMatching from '@/views/contents/friend_matching/friendMatching'

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
    path: "/seniorcenterselect",
    name: "SeniorCenterSelect",
    component: SeniorCenterSelect,
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
  {
    path: "/dementia",
    name: "Dementia",
    component: Dementia,
  },
  {
    path: "/stretching",
    name: "Stretching",
    component: Stretching,
  },
  {
    path: "/seniorcenter",
    name: "SeniorCenter",
    component: SeniorCenter,
  },
  {
    path: "/map",
    name: "Map",
    component: Map,
  },
  {
    path: "/friendmatching",
    name: "FriendMatching",
    component: FriendMatching,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
