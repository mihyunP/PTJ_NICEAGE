// 백엔드 API 요청은 여기서
import axios from 'axios'

// const BASE_URL = '/api/v1'
const DEFAULT_ACCEPT_TYPE = 'application/json'

// axios.defaults.baseURL = BASE_URL
axios.defaults.headers['Content-Type'] = DEFAULT_ACCEPT_TYPE

export function requestLogin ({ state }, payload) {
  console.log('requestLogin', state, payload)
  const url = '/login'
  let body = payload
  return axios.post(url, body)
}

export function requestSignup ({ state }, payload) {
  console.log('requestSignup', state, payload)
  const url = '/user/join'
  let body = payload
  return axios.post(url, body)
}

export function requestAuthenticationNumber ({ state }, payload) {
  console.log('requestAuthenticationNumber', state, payload)
  const url = '/auth'
  let body = payload
  return axios.post(url, body)
}

export function requestConfirmAuthNum ({ state }, param) {
  console.log('requestConfirmAuthNum', state, param)
  const url = `/auth/${param.phoneNumber}/${param.authNum}`
  return axios.get(url)
}