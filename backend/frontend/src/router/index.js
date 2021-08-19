import { createRouter, createWebHistory } from "vue-router";
import Home from '@/views/home/home'
import Login from '@/views/user/login/login'
import Signup from '@/views/user/signup/signup'
import Password from '@/views/user/password/password'
import Password2 from '@/views/user/password/password2'
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

import DementiaResult1 from '@/views/contents/health/dementia/dementia_result1'
import DementiaResult2 from '@/views/contents/health/dementia/dementia_result2'

import Admin from '@/views/admin/admin'

import BoardDetail from '@/views/contents/community/boardDetail'
import BoardWrite from '@/views/contents/community/boardWrite'
import BoardModify from '@/views/contents/community/boardModify'
// import BoardComment from '@/views/contents/community/boardComment'
const allowOnlyAuth = function(to, from, next) {
  if (localStorage.getItem('access_token')) {
    next()
  } else {
    next(from)
  }
}
const allowNotAuth = function(to, from, next) {
  if (!localStorage.getItem('access_token')) {
    next()
  } else {
    next(from)
  }  
}
const allowOnlyAdmin = function(to, from, next) {
  if (localStorage.getItem('my_id') == 'ssafy') {
    next()
  } else {
    next(from)
  }  
}

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
    beforeEnter: allowNotAuth,
  },
  {
    path: "/signup",
    name: "Signup",
    component: Signup,
    beforeEnter: allowNotAuth,
  },
  {
    path: "/password",
    name: "Password",
    component: Password,
    beforeEnter: allowNotAuth,
  },
  {
    path: "/password/2",
    name: "Password2",
    component: Password2,
    props: true,
    beforeEnter: allowNotAuth,
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
    beforeEnter: allowOnlyAuth,
  },
  {
    path: "/friendselect",
    name: "FriendSelect",
    component: FriendSelect,
    beforeEnter: allowOnlyAuth,
  },
  {
    path: "/healthselect",
    name: "HealthSelect",
    component: HealthSelect,
    beforeEnter: allowOnlyAuth,
  },
  {
    path: "/gameselect",
    name: "GameSelect",
    component: GameSelect,
    beforeEnter: allowOnlyAuth,
  },
  {
    path: "/board",
    name: "Board",
    component: Board,
    beforeEnter: allowOnlyAuth,
  },
  {
    path: "/mypage",
    name: "Mypage",
    component: Mypage,
    beforeEnter: allowOnlyAuth,
  },
  {
    path: "/dementia",
    name: "Dementia",
    component: Dementia,
    beforeEnter: allowOnlyAuth,
  },
  {
    path: "/stretching",
    name: "Stretching",
    component: Stretching,
    beforeEnter: allowOnlyAuth,
  },
  {
    path: "/seniorcenter",
    name: "SeniorCenter",
    component: SeniorCenter,
    props: true,
    beforeEnter: function(to, from, next) {
      console.log('!!!')
      if (to == from) {
        console.log('같은 페이지1!!')
      } else {
        console.log('다른페이지!!!')
      }
      next()
    },
  },
  {
    path: "/map",
    name: "Map",
    component: Map,
    beforeEnter: allowOnlyAuth,
  },
  {
    path: "/friendmatching",
    name: "FriendMatching",
    component: FriendMatching,
    props: true,
    beforeEnter: allowOnlyAuth,
  },
  {
    path: "/dementiaresult1",
    name: "DementiaResult1",
    component: DementiaResult1,
    beforeEnter: allowOnlyAuth,
  },
  {
    path: "/dementiaresult2",
    name: "DementiaResult2",
    component: DementiaResult2,
    beforeEnter: allowOnlyAuth,
  },
  {
    path: "/admin",
    name: "Admin",
    component: Admin,
    beforeEnter: allowOnlyAdmin,
  },
  {
    path: "/boarddetail/:id",
    name: "BoardDetail",
    component: BoardDetail,
    props: true,
  },
  {
    path: "/boardwrite",
    name: "BoardWrite",
    component: BoardWrite,
  },
  {
    path: "/boardmodify/:id",
    name: "BoardModify",
    component: BoardModify,
    props: true,
  },
  // {
  //   path: "/comment",
  //   name: "Comment",
  //   component: Comment,
  // },
];

const router = createRouter({
  mode: 'history',
  history: createWebHistory(process.env.BASE_URL),
  routes,
});


export default router;
