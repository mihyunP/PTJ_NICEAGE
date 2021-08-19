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

export function requestUserList({ state }) {
  console.log('requestUserList', state)
  const url = `/user`
  return axios.get(url)
}

export function requestReportList({ state }) {
  console.log('requestReportList', state)
  const url = `/user/report/all`
  return axios.get(url)
}

export function requestReportUser({ state }, payload) {
  console.log('requestReportList', state)
  const url = `/user/report`
  const body = payload
  return axios.post(url, body)
}

export function requestEnter ({ state }, payload) {
  console.log('requestEnter', state, payload)
  const url = `/enter/enter`
  let body = payload
  return axios.post(url, body)
}

export function requestDementiaResult ({ state }, param) {
  console.log('requestDementiaResult', state, param)
  const url = `/dementia/dementia/${param.userId}/${param.result}`
  return axios.get(url)
  }

  // board
  // 게시판 불러오기
  export function requestBoardContent ({ state }) {
    console.log('requestBoardContent', state)
    const url = `/board/list`
    return axios.get(url)
  }
  // 게시판 글 읽기
  export function requestReadBoard ({ state },params) {
    console.log('requestReadBoard', state)
    const url = `/board/read/${params.boardId}` // ${params.userId}/
    return axios.get(url)
  }
  // 게시판 글 작성
  export function requestSubmitWrite ({ state }, payload) {
    console.log('requestSubmitWrite', state, payload)
    const url = `/board/create`
    let body = payload
    return axios.post(url, body)
  }
  // 게시판 글 수정
  export function requestUpdateWrite ({ state }, payload) {
    console.log('requestUpdateWrite', state, payload)
    const url = `/board/update`
    const body = payload
    return axios.put(url, body)
  }
  // 게시판 글 삭제
  export function requestDeleteWrite({ state }, param) {
    console.log('requestDeleteWrite', state, param)
    const url = `/board/delete/${param.userId}/${param.boardId}` 
    return axios.delete(url)
  }
  // comment
  // 댓글 작성
  export function requestSubmitComment ({ state }, payload) {
    console.log('requestSubmitComment', state, payload)
    const url = `/comment/create`
    let body = payload
    return axios.post(url, body)
  }
  // 댓글 삭제
  export function requestDeleteComment({ state }, param) {
    console.log('requestDeleteComment', state, param)
    const url = `/comment/delete/${param.userId}/${param.boardId}/${param.commentId}` 
    return axios.delete(url)
  } 
  // 댓글 수정
  export function requestUpdateComment ({ state }, payload) {
    console.log('requestUpdateComment', state, payload)
    const url = `/comment/update`
    const body = payload
    return axios.put(url, body)
  }


export function requestSeniorCenter ({ state }, userId) {
  console.log('requestSeniorCenter', state, userId)
  const url = `/senior/random/${userId}`
  return axios.get(url)
}

export function requestFriendMatching ({ state }, payload) {
  console.log('requestFriendMatching', state, payload)
  const url = '/match/match'
  const body = payload
  return axios.post(url,body)
}

export function requestSOS ({ state }, userId) {
  console.log('requestFriendMatching', state, userId)
  const url = `/sos119/${userId}`
  return axios.get(url)
}

export function requestCancelFriendMatching ({ state }, userId) {
  console.log('requestCancelFriendMatching', state, userId)
  const url = `/match/cancel/${userId}`
  return axios.get(url)
}
