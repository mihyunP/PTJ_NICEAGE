// root state 변수 정의


export default {
  accessToken: localStorage.getItem('access_token') || '',
  loading: false
}