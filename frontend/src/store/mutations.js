export function updateToken (state, token) {
  state.accessToken = token
}

export function deleteToken (state) {
  state.accessToken = ''
}

export function loadingOn (state) {
  state.loading = true
}
export function loadingOff (state) {
  state.loading = false
}

export function updateMyId(state, userId) {
  state.myId = userId
}

export function deleteMyId(state) {
  state.myId = ''
}