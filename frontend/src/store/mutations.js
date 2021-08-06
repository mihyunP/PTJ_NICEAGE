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