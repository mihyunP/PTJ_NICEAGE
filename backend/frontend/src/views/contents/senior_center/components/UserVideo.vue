<template>
<div class="video-frame" v-if="streamManager">
  <el-popover
    trigger="hover"
    :content="`${clientData}님`"
  >
    <template #reference>
			<ov-video :stream-manager="streamManager"/>
    </template>
  </el-popover>
</div>
</template>

<script>
import OvVideo from './OvVideo';

export default {
	name: 'UserVideo',

	components: {
		OvVideo,
	},

	props: {
		streamManager: Object,
	},

	computed: {
		clientData () {
			const { clientData } = this.getConnectionData();
			return clientData;
		},
	},

	methods: {
		getConnectionData () {
			const { connection } = this.streamManager.stream;
			return JSON.parse(connection.data);
		},
	},
};
</script>

<style scoped>
.video-frame {
	margin: 4px;
	filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25));
}
</style>