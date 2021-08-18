import axios from 'axios'

const KAKAO_TTS_API_KEY = "3c0313753fd2e0eacded067594c45184"
axios.defaults.headers.common['Authorization'] = `KakaoAK ${KAKAO_TTS_API_KEY}`

const url = 'https://kakaoi-newtone-openapi.kakao.com/v1/synthesize'

export function requestKakaoTTS ({ state }, text) {
  console.log('requestKakaoTTS', state, text)
  const config = {
    headers: {
      'content-type': 'application/xml'
    },      
    responseType: 'arraybuffer'
  }
  const data = `<speak>${text}</speak>`
  return axios.post(url, data, config)
}