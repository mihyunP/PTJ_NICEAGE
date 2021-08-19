<template>
  <el-row class="main-content">
    <el-col class="left-content" :span="12">
      <el-row class="main-content" justify="center" align="middle">
        <el-col :span="24">
          <el-row justify="center"><div class="main-image"></div></el-row>
        </el-col>
      </el-row>
    </el-col>
    <BackButton/>
    <el-col class="right-content" :span="12">
      <el-row class="main-content" justify="center" align="middle">
      </el-row>
    </el-col>
  </el-row>
  <el-container class="admin-container">
    <el-header>
      <div class="info-font">Admin</div>
    </el-header>
    <el-main>
      <el-tabs tab-position="left" style="height: 70vh; width:130vh;">
        <el-tab-pane label="회원관리">
          <el-row justify="start">
            <el-select
              v-model="value"
              filterable
              remote
              reserve-keyword
              placeholder="사용자 ID를 입력하세요."
              :remote-method="remoteMethod"
              :loading="state.loading">
              <el-option
                v-for="item in state.options"
                :key="item.value"
                :label="item.label"
                :value="item.value"
                @click="clickOption(item.value)">
              </el-option>
            </el-select>
          </el-row>
          <el-table
            :data="state.userList"
            height="55vh"
            style="width: 130vh"
            @row-click="clickUser">
            <el-table-column
              prop="userId"
              label="ID"
              width="150">
            </el-table-column>
            <el-table-column
              prop="userName"
              label="이름"
              width="150">
            </el-table-column>
            <el-table-column
              prop="userPhone"
              label="연락처"
              width="180">
            </el-table-column>
            <el-table-column
              prop="userAddress"
              label="주소"
              width="400">
            </el-table-column>
            <el-table-column
              label="탈퇴">
              <el-button @click="clickDeleteUser" type="danger">탈퇴</el-button>
            </el-table-column>
          </el-table>
        </el-tab-pane>
        <el-tab-pane label="신고 목록">
          <el-table
            :data="state.reportList"
            height="55vh"
            style="width: 100%">
            <el-table-column
              prop="reporterId"
              label="신고한 ID"
              width="180">
            </el-table-column>
            <el-table-column
              prop="reporterTargetId"
              label="신고된 ID"
              width="180">
            </el-table-column>
            <el-table-column
              prop="reportDate"
              label="신고 시간">
            </el-table-column>
          </el-table>
        </el-tab-pane>
        <!-- <el-tab-pane label="도움 요청 목록">
          <el-table
            :data="state.reportList"
            height="55vh"
            style="width: 100%">
            <el-table-column
              prop="reporterId"
              label="신고한 ID"
              width="180">
            </el-table-column>
            <el-table-column
              prop="reporterTargetId"
              label="신고된 ID"
              width="180">
            </el-table-column>
            <el-table-column
              prop="roportDate"
              label="신고 시간">
            </el-table-column>
          </el-table>
        </el-tab-pane> -->
      </el-tabs>
    </el-main>
  </el-container>
  <el-dialog
    v-model="state.centerDialogVisible"
    width="30%"
    center>
    <div style="font-size: 15px;">{{state.clickedUser.userId}}님을 탈퇴시키겠습니까?</div>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="state.centerDialogVisible = false">취소</el-button>
        <el-button type="warning" @click="deleteUser">탈퇴</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script>
import { reactive, onMounted } from 'vue'
import { useStore } from 'vuex'
import BackButton from '@/views/components/BackButton'

export default {
  name: 'Admin',
  components: {
    BackButton
  },
  setup() {
    const store = useStore()

    const state = reactive({
      userList: [],
      reportList: [],
      clickedUser: {},
      centerDialogVisible: false,
      options: [],
      value: [],
      list: [],
      loading: false,
    })

    onMounted(() => {
      store.dispatch('root/requestUserList')
      .then(res => {
        state.userList = res.data.data
        state.list = state.userList.map(user => {
          return { value: `${user.userId}`, label: `${user.userId}` };
        });
      })
      .catch(err => {
        alert(err)
      })

      store.dispatch('root/requestReportList')
      .then(res => {
        state.reportList = res.data.data
      })
      .catch(err => {
        alert(err)
      })
    })

    const clickUser = function(row) {
      state.clickedUser = row
    }

    const clickDeleteUser = function() {

      state.centerDialogVisible = true
    }

    const deleteUser = function() {
      store.dispatch('root/requestDeleteAccount', state.clickedUser.userId)
      .then(() => {
        state.centerDialogVisible = false
        // state.userList를 삭제한 user 빼고 다시 만들어주기
        const userListTmp = []
        state.userList.forEach(user => {
          if (user.userId !== state.clickedUser.userId) {
            userListTmp.push(user)
          }
        });
        state.clickedUser = {}
        state.userList = userListTmp
        alert('탈퇴완료되었습니다.')
      })
      .catch(() => {
        alert('탈퇴실패')
      })
    }
    const remoteMethod = (query) => {
      if (query !== '') {
        state.loading = true;
        setTimeout(() => {
          state.loading = false;
          state.options = state.list.filter(item => {
            return item.label.toLowerCase()
              .indexOf(query.toLowerCase()) > -1;
          });
        }, 200);
      } else {
        state.options = [];
      }
    }
    const clickOption = (userId) => {

      const clickedUser = state.userList.filter(user => {
        return user.userId == userId
      })
      state.clickedUser = clickedUser[0]
      state.centerDialogVisible = true
    }
    return {state, clickUser, clickDeleteUser, deleteUser, remoteMethod, clickOption}
  }
}
</script>

<style scoped>
  .info-font {
    font-size: 40px;
    margin: 10px;
  }
  .main-content {
    height: 100%;
  }
  .left-content {
    opacity:0.2;
  }
  .right-content {
    background: rgba(173, 203, 176, 0.3);
    height: 100%;
    /* opacity: 0.5; */
  }
  .select-btn {
    width: 270px;
    height: 270px;
    background: #EBC86F !important;
    border-radius: 40px !important;
  }
  .select-font {
    font-family: BlackHanSans;
    font-size: 36px;
  }
  .question {
    font-family: SangSangFlowerRoad;
    font-size: 60px;
    color: rgba(248, 141, 141, 1);
  }
  .explanation {
    font-family: SangSangFlowerRoad;
    font-size: 48px;
    text-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  }
  .my-el-button {
    width: 160px;
    height: 160px;
    background: #EBC86F !important;
    border-radius: 25px !important;
  }
  .select-button {
    font-family: BlackHanSans;
    font-size: 21px;
  }
  .main-image {
    height: 442px;
    width: 700px;
    margin: 0 auto;
    background-size: contain;
    background-repeat: no-repeat;
    background-image: url('../../assets/images/main.png');
  }

  .seniorcenter-image {
    height: 98px;
    width: 98px;
    margin: 0 auto;
    background-size: contain;
    background-repeat: no-repeat;
    background-image: url('../../assets/images/button/seniorcenter.png');
  }
    .friendmatching-image {
    height: 98px;
    width: 98px;
    margin: 0 auto;
    background-size: contain;
    background-repeat: no-repeat;
    background-image: url('../../assets/images/button/friendmatching.png');
  }
    .mypage-image {
    height: 98px;
    width: 98px;
    margin: 0 auto;
    background-size: contain;
    background-repeat: no-repeat;
    background-image: url('../../assets/images/button/mypage.png');
  }
  .back-btn {
    position: absolute;
    bottom: 10px;
    left: 10px;
    background:rgba(255, 250, 250, 0.5);
  }
  .admin-container {
    background: white;
    opacity:0.9;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    border-radius: 20px !important;
    filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25));
  }

</style>