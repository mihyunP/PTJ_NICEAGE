<template>
  <el-row class="bottom-bar" justify="center" align="middle">
    <el-col :span="6">
      <el-button class="select-btn" round @click="muteAudio">
        <div v-if="isAudioMuted">
          <span class="iconify" data-inline="false" data-icon="ant-design:audio-muted-outlined" style="color: #E25353; font-size: 48px;"></span>
          마이크 켜기
        </div>
        <div v-else>
          <span class="iconify" data-inline="false" data-icon="ant-design:audio-filled" style="color: #2ec02e; font-size: 48px;"></span>
          <span>마이크 끄기</span>
        </div>
      </el-button>
    </el-col>
    <el-col :span="6">
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
    <el-col :span="6">
      <el-button class="select-btn" round @click="leaveSession">
        <span class="iconify" data-inline="false" data-icon="icomoon-free:exit" style="color: #e25353; font-size: 48px;"></span>
        나가기
      </el-button>
    </el-col>
    <el-col :span="6">
      <el-button class="select-btn" type="danger" round @click="reportUser">
        상대방 신고하기
      </el-button>
    </el-col>
  </el-row>
</template>

<script>
export default {
  name: 'BottomBar',
  props: {
    isAudioMuted: {
      type: Boolean,
    },
    isVideoMuted: {
      type: Boolean,
    }
  },
  methods: {
    muteAudio() {
      this.$emit('muteAudio')
    },
    muteVideo() {
      this.$emit('muteVideo')
    },
    leaveSession() {
      this.$emit('leaveSession')
    },
    reportUser() {
      this.$emit('reportUser')
    }
  }
}
</script>

<style scoped>
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