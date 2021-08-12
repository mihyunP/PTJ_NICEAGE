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

export function requestSeniorCenterInfo ({ state }, param) {
  console.log('requestSeniorCenterInfo', state, param)
  const url = `/senior/show/${param.userId}`
  return axios.get(url)
}

export function requestFrequenceSeniorCenterInfo ({ state }, param) {
  console.log('requestFrequenceSeniorCenterInfo', state, param)
  const url = `/senior/frequence/${param.userId}`
  return axios.get(url)
}

export function requestOverlapped ({ state }, param) {
  console.log('requestOverlapped', state, param)
  const url = `/user/check/${param.userId}`
  return axios.get(url)
}

export function requestCheckUser ({ state }, param) {
  console.log('requestCheckUser', state, param)
  const url = `/user/${param.userId}/${param.userPhone}`
  return axios.get(url)
}

export function requestChangeUserInfo ({ state }, payload) {
  console.log('requestChangeUserInfo', state, payload)
  const url = `/user/${payload.userId}`
  const body = payload
  return axios.put(url, body)
}

export function requestMyDetail({ state }, myId) {
  console.log('requestMyDetail', state, myId)
  const url = `/user/${myId}`
  return axios.get(url)
}

export function requestDeleteAccount({ state }, myId) {
  console.log('requestDeleteAccount', state, myId)
  const url = `/user/${myId}`
  return axios.delete(url)
}

export function requestEnter ({ state }, payload) {
  console.log('requestEnter', state, payload)
  const url = `/enter/enter`
  let body = payload
  return axios.post(url, body)
}