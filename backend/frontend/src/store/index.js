import { createStore } from "vuex";
//vuex 조립
import state from './state'
import * as getters from './getters'
import * as mutations from './mutations'
import * as actions from './actions' 
import * as actions2 from './KakaoAPI/actions' 

// import axios from 'axios'

// 액세스토큰이 있는 경우 헤더에 추가하여 요청을 보낸다.
// axios.interceptors.request.use(config => {
//   const accessToken = localStorage.getItem('access_token')
//   config.headers.common['Authorization'] = accessToken ? `Bearer ${accessToken}` : null
//   return config
// })


const root = {
  namespaced: true,
  state,
  getters,
  mutations,
  actions,
}

export default createStore({
  modules: { root },
  actions: actions2
});

// 원래
// export default createStore({
//   state: {},
//   mutations: {},
//   actions: {},
//   modules: {},
// });
