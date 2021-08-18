<template>
	<div class="room-container" id="main-container">
		<div id="join" v-if="!isMatched">
			<div id="join-dialog" class="jumbotron vertical-center">
				<h1>대기 페이지</h1>
				<div class="form-group">
					<p>
						<label>Participant</label>
						<input v-model="myUserName" class="form-control" type="text" required>
					</p>
					<p class="text-center">
						<button class="btn btn-lg btn-success" @click="clickButton">Join!</button>
					</p>
				</div>
			</div>
		</div>

		<div class="section" v-if="isMatched">
      <el-row class="session-main">
        <el-col class="video-container" :span="18">
					<el-row id="session-title" justify="space-between">
						<div>{{ mySessionId }}</div>
						<div>119 신고기능 추가예정</div>
					</el-row>
					<!-- <input class="btn btn-large btn-danger" type="button" id="buttonLeaveSession" @click="leaveSession" value="Leave session"> -->
          <!-- <el-row id="session-header" justify="space-between">
						<el-col :span="5">
						</el-col>
						<el-col :span="5">
							<el-button>
								<span class="iconify" data-inline="false" data-icon="twemoji:ambulance" style="font-size: 40px;"></span>
								<span style="font-size: 25px;">119에 신고하기</span>
							</el-button>
						</el-col>
          </el-row> -->
          <!-- <div id="main-video">
            <user-video :stream-manager="mainStreamManager"/>
          </div> -->
					<el-row style="height: 100%;" align="middle">
						<el-col :span="12">
							<user-video :stream-manager="publisher" @click="updateMainVideoStreamManager(publisher)"/>
						</el-col>
						<el-col :span="12" v-for="sub in subscribers" :key="sub.stream.connection.connectionId">
							<user-video :stream-manager="sub" @click="updateMainVideoStreamManager(sub)"/>
						</el-col>
					</el-row>
        </el-col>
        <el-col class="session-sidebar" :span="6">
					<el-tabs type="card" @tab-click="handleClick">
						<el-tab-pane label="채팅">
							<div>
								<MessageList :msgs="msgs" :senderObj="messageSenderObj" :me="publisher"/>
								<MessageForm @getMyMsg="getMyMsg"/>
							</div>
						</el-tab-pane>
					</el-tabs>
        </el-col>
      </el-row>
			<el-row class="bottom-bar" justify="center" align="middle">
				<el-col :span="8">
					<el-button class="select-btn" round @click="muteAudio">
						<div v-if="isAudioMuted">
							<span class="iconify" data-inline="false" data-icon="ant-design:audio-muted-outlined" style="color: #E25353; font-size: 48px;"></span>
							소리 켜기
						</div>
						<div v-else>
							<span class="iconify" data-inline="false" data-icon="ant-design:audio-filled" style="color: #2ec02e; font-size: 48px;"></span>
							<span>소리 끄기</span>
						</div>
					</el-button>
				</el-col>
				<el-col :span="8">
					<el-button class="select-btn" round @click="muteVideo">
						<div v-if="isVideoMuted">
							<span class="iconify" data-inline="false" data-icon="bx:bxs-video-off" style="color: #e25353; font-size: 48px;"></span>
							<span>비디오 켜기</span>
						</div>
						<div v-else>
							<span class="iconify" data-inline="false" data-icon="bx:bxs-video" style="color: #2ec02e; font-size: 48px;"></span>
							<span>비디오 끄기</span>
						</div>
					</el-button>
				</el-col>
				<el-col :span="8">
					<el-button class="select-btn" round @click="leaveSession">
						<span class="iconify" data-inline="false" data-icon="icomoon-free:exit" style="color: #e25353; font-size: 48px;"></span>
						나가기
					</el-button>
				</el-col>
			</el-row>
		</div>
	</div>
</template>

<script>
import axios from 'axios';
import { OpenVidu } from 'openvidu-browser';
import UserVideo from './components/UserVideo';
import MessageForm from './components/messageForm';
import MessageList from './components/messageList';

axios.defaults.headers.post['Access-Control-Allow-Origin'] = 'http://i5b202.p.ssafy.io';
axios.defaults.headers.post['Content-Type'] = 'application/json';
const OPENVIDU_SERVER_URL = "https://i5b202.p.ssafy.io:4443"
const OPENVIDU_SERVER_SECRET = "ssafy"
export default {
	name: 'App',
	components: {
		UserVideo,
    MessageForm,
    MessageList
	},
	props: {
		mySessionId: {
			type: String
		},
	},
	data () {
		return {
			OV: undefined,
			session: undefined,
			mainStreamManager: undefined,
			publisher: undefined,
			subscribers: [],
			myUserName: '',
      msgs: [],
			messageSenderObj: undefined,
			isVideoMuted: true,
			isAudioMuted: true,
			inChat: true,
			activeName: 'first',
			isMatched: false,
		}
	},
	mounted() {
		this.$store.dispatch('root/requestMyDetail', this.$store.getters['root/getMyId'])
		.then((res) => {
			this.myUserName = res.data.data.userName
			this.joinSession()
		})
	},
	methods: {
		clickButton() {
			this.muteAudio()
			this.muteVideo()
			this.isMatched = !this.isMatched
		},
		handleClick(tab, event) {
			console.log(tab, event);
		},
		muteAudio() {
			this.publisher.publishAudio(this.isAudioMuted)
			this.isAudioMuted = !this.isAudioMuted
		},
		muteVideo() {
			this.publisher.publishVideo(this.isVideoMuted)
			this.isVideoMuted = !this.isVideoMuted
		},
    getMyMsg (myMsg) {
      this.session.signal({
        data: myMsg,
        to: [],
        type: 'my-chat'
      })
      .then(() => {
        console.log('메시지성공적으로 보냄')
      })
      .catch(error => {
        console.err(error)
      })
    },
		joinSession () {
			// --- Get an OpenVidu object ---
			this.OV = new OpenVidu();
			// --- Init a session ---
			this.session = this.OV.initSession();
			// --- Specify the actions when events take place in the session ---
			// On every new Stream received...
			this.session.on('streamCreated', ({ stream }) => {
				const subscriber = this.session.subscribe(stream);
				this.subscribers.push(subscriber);
			});
			// On every Stream destroyed...
			this.session.on('streamDestroyed', ({ stream }) => {
				const index = this.subscribers.indexOf(stream.streamManager, 0);
				if (index >= 0) {
					this.subscribers.splice(index, 1);
				}
			});
			// On every asynchronous exception...
			this.session.on('exception', ({ exception }) => {
				console.warn(exception);
			});
			// 메세지가 도착 시: 메시지 배열에 {message: 메시지내용, isMe: true/false} 형태로 저장
      this.session.on('signal', (event) => {
        const tmp = this.msgs.slice()
				let isMe = false
				// console.log('11', this.publisher)
				// console.log("사용자이름: ",this.publisher.stream.connection.data)
				// console.log("커넥션아이디: ",this.publisher.stream.connection.connectionId)
				// console.log("커넥션아이디: ", event.from.connectionId)
				// console.log('123',JSON.parse(event.from.data).clientData)
				if (event.from.connectionId == this.publisher.stream.connection.connectionId) {
					isMe = true
				}
        tmp.push({message: event.data, isMe: isMe})
				this.msgs = tmp
				this.messageSenderObj = event.from
      })

			this.session.on('connectionCreated', (event) => {
				console.log("이벤트 커넥션 발생", event.connection)
			})

			// --- Connect to the session with a valid user token ---
			// 'getToken' method is simulating what your server-side should do.
			// 'token' parameter should be retrieved and returned by your own backend
			this.getToken(this.mySessionId).then(token => {
				this.session.connect(token, { clientData: this.myUserName })
					.then(() => {
						// --- Get your own camera stream with the desired properties ---
						let publisher = this.OV.initPublisher(undefined, {
							audioSource: undefined, // The source of audio. If undefined default microphone
							videoSource: undefined, // The source of video. If undefined default webcam
							publishAudio: true,  	// Whether you want to start publishing with your audio unmuted or not
							publishVideo: true,  	// Whether you want to start publishing with your video enabled or not
							resolution: '640x480',  // The resolution of your video
							frameRate: 30,			// The frame rate of your video
							insertMode: 'APPEND',	// How the video is inserted in the target element 'video-container'
							mirror: false       	// Whether to mirror your local video or not
						});
						this.mainStreamManager = publisher;
						this.publisher = publisher;
						// --- Publish your stream ---
						this.session.publish(this.publisher);
					})
					.catch(error => {
						console.log('There was an error connecting to the session:', error.code, error.message);
					});
			});
			window.addEventListener('beforeunload', this.leaveSession)
		},
		leaveSession () {
			// --- Leave the session by calling 'disconnect' method over the Session object ---
			if (this.session) this.session.disconnect();
			this.session = undefined;
			this.mainStreamManager = undefined;
			this.publisher = undefined;
			this.subscribers = [];
			this.OV = undefined;
			window.removeEventListener('beforeunload', this.leaveSession);
			this.$router.push({
				name: 'Home'
			})
		},
		updateMainVideoStreamManager (stream) {
			if (this.mainStreamManager === stream) return;
			this.mainStreamManager = stream;
		},
		/**
		 * --------------------------
		 * SERVER-SIDE RESPONSIBILITY
		 * --------------------------
		 * These methods retrieve the mandatory user token from OpenVidu Server.
		 * This behavior MUST BE IN YOUR SERVER-SIDE IN PRODUCTION (by using
		 * the API REST, openvidu-java-client or openvidu-node-client):
		 *   1) Initialize a Session in OpenVidu Server	(POST /openvidu/api/sessions)
		 *   2) Create a Connection in OpenVidu Server (POST /openvidu/api/sessions/<SESSION_ID>/connection)
		 *   3) The Connection.token must be consumed in Session.connect() method
		 */
		getToken (mySessionId) {
			return this.createSession(mySessionId).then(sessionId => this.createToken(sessionId));
		},
		// See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-openviduapisessions
		createSession (sessionId) {
			return new Promise((resolve, reject) => {
				axios
					.post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions`, JSON.stringify({
						customSessionId: sessionId,
					}), {
						auth: {
							username: 'OPENVIDUAPP',
							password: OPENVIDU_SERVER_SECRET,
						},
					})
					.then(response => response.data)
					.then(data => resolve(data.id))
					.catch(error => {
						if (error.response.status === 409) {
							resolve(sessionId);
						} else {
							console.warn(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`);
							if (window.confirm(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`)) {
								location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
							}
							reject(error.response);
						}
					});
			});
		},
		// See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-openviduapisessionsltsession_idgtconnection
		createToken (sessionId) {
			return new Promise((resolve, reject) => {
				axios
					.post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`, {}, {
						auth: {
							username: 'OPENVIDUAPP',
							password: OPENVIDU_SERVER_SECRET,
						},
					})
					.then(response => response.data)
					.then(data => resolve(data.token))
					.catch(error => reject(error.response));
			});
		},
	}
}
</script>

<style scoped>
	/* #session-title {
		text-align: center;
	} */
	.room-container {
		background: white;
		height: 100%;
		padding-left: 5px;
	}
	.section {
		position: relative;
		height: 100%;
	}
	
	.session-main {
		height: 83vh;
	}

	.video-container {
    height: 100%;
		margin-bottom: 10px;
		border: 2px solid #bcbcbc;
		/* filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25)); */
	}

	.session-sidebar {
		padding: 5px;
		border-radius: 32px;
		height: 83vh;
		/* filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25)); */
	}

	.sidebar-title {
		height: 3vh;
	}

	.bottom-bar {
		margin-top: 15px;
	}

  .select-btn {
    font-family: BlackHanSans;
    font-size: 32px;
    /* background: 61ACF1 !important; */
    border-radius: 40px !important;
		/* filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25)); */
  }
</style>