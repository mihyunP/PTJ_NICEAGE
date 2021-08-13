<template>
	<div class="room-container" id="main-container">
		<!-- <div id="join" v-if="!session">
			<div id="join-dialog" class="jumbotron vertical-center">
				<h1>Join a video session</h1>
				<div class="form-group">
					<p>
						<label>Participant</label>
						<input v-model="myUserName" class="form-control" type="text" required>
					</p>
					<p>
						<label>Session</label>
						<input v-model="mySessionId" class="form-control" type="text" required>
					</p>
					<p class="text-center">
						<button class="btn btn-lg btn-success" @click="joinSession()">Join!</button>
					</p>
				</div>
			</div>
		</div> -->

		<div class="section" v-if="session">
      <el-row class="session-main">
        <el-col class="video-container" :span="18">
					<el-row id="session-title" justify="space-between">
						<!-- <div>{{ mySessionId }}</div> -->
						<div>{{myCenterName}}</div>
						<div>119 신고기능 추가예정</div>
					</el-row>
					<el-row>
						<el-col :span="8">
							<UserVideo :stream-manager="publisher" @click="updateMainVideoStreamManager(publisher)"/>
						</el-col>
						<el-col v-for="sub in subscribers" :key="sub.stream.connection.connectionId" :span="8">
							<UserVideo :stream-manager="sub" @click="updateMainVideoStreamManager(sub)"/>
						</el-col>
					</el-row>
        </el-col>
        <el-col class="session-sidebar" :span="6">
					<SideBar
						@handleClick="handleClick"
						@getMyMsg="getMyMsg"
						:msgs="msgs"/>
        </el-col>
      </el-row>
			<BottomBar
				:isAudioMuted="isAudioMuted"
				:isVideoMuted="isVideoMuted"
				@muteAudio="muteAudio"
				@muteVideo="muteVideo"
				@leaveSession="leaveSession"
			/>
		</div>
	</div>
</template>

<script>
import { useRouter } from 'vue-router';
import axios from 'axios';
import { OpenVidu } from 'openvidu-browser';
import UserVideo from './components/UserVideo';
import SideBar from './components/sideBar';
import BottomBar from './components/bottomBar';

axios.defaults.headers.post['Content-Type'] = 'application/json';
const OPENVIDU_SERVER_URL = "https://" + location.hostname + ":4443";
const OPENVIDU_SERVER_SECRET = "MY_SECRET";
export default {
	name: 'App',
	components: {
		UserVideo,
		BottomBar,
		SideBar
	},
	props: {
		mySessionId: {
			type: String
		},
		myUserName: {
			type: String
		},
		myCenterName: {
			type: String
		}
	},
	data () {
		return {
			OV: undefined,
			session: undefined,
			mainStreamManager: undefined,
			publisher: undefined,
			subscribers: [],
      msgs: [],
			messageSenderObj: undefined,
			isVideoMuted: false,
			isAudioMuted: false,
			activeName: 'first',
		}
	},

	mounted() {
		this.joinSession()
		console.log('12312312341241587342y379852hjkfedhnf', this.myCenterName)
	},

	methods: {
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
				// console.log("커넥션아이디: ", event.from)
				// console.log('123',JSON.parse(event.from.data).clientData)
				if (event.from.connectionId == this.publisher.stream.connection.connectionId) {
					isMe = true
				}
				const { clientData } = JSON.parse(event.from.data)
				console.log('메시지:', event)
        tmp.push({message: event.data, isMe: isMe, username: clientData})
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
			this.leave()
		},
		updateMainVideoStreamManager (stream) {
			if (this.mainStreamManager === stream) return;
			this.mainStreamManager = stream;
		},

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
	},
	setup() {
		const router = useRouter()

		const leave = () => {
			router.push({
				name: 'SeniorCenterSelect'
			})
		}

		return { leave }

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
		margin-bottom: 10px;
		border: 1px solid #bcbcbc;
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

	/* .chat-container {
		height: 100%;
		padding: 4px;
		filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25));
	} */

	.bottom-bar {
		margin-top: 15px;
	}
  .select-btn {
    font-family: BlackHanSans;
    font-size: 32px;
    /* background: 61ACF1 !important; */
    border-radius: 40px !important;
		filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25));
  }
</style>
