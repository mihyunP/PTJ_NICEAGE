<template>
    <el-row class="map-container">
        <el-col :span="7">
            
                <el-tabs type="border-card" @tab-click="handleTabClick">
                <el-tab-pane label="전체 보기">
                    <el-scrollbar height="650px">
                <!-- <div v-if="!state.dialogVisible">{{ state.dialogVisible }}</div> -->
                <!-- <div v-if="!state.dialogVisible">{{ state.SeniorCenterInfo[0].seniorId }}</div> --> 
                    <!-- <table>
                        <thead></thead>
                        <tbody>
                    <tr v-bind:key="s" v-for="s in state.SeniorCenterInfo" @click="loadModal(s)">
                    <td>
                        <img src="https://kr.seaicons.com/wp-content/uploads/2015/06/house-icon.png" alt="My Image" width="80">
                    </td>
                    <td>
                            이름 : {{ s.seniorName }}<br>
                            주소 : {{ s.seniorAddress }}<br>
                    </td>
                    <el-divider></el-divider>
                    </tr>
                        </tbody>
                    </table> -->
                <el-card
                :key="s" v-for="s in state.SeniorCenterInfo" @click="loadModal(s)" shadow="hover" style="margin: 5px;">
                    <el-row>
                    <el-col :span="6">
                        <img src="https://kr.seaicons.com/wp-content/uploads/2015/06/house-icon.png" alt="My Image" width="90">
                    </el-col>
                    <el-col :span="16" :offset="1">
                        <el-row justify="start" align="middle">
                            <div>
                                <span style="font-family: SSShinb7; font-size: 30px;">{{ s.seniorName }}</span></div>
                            <el-divider style="margin: 12px;"></el-divider>
                            <span style="font-size: 14px;">{{ s.seniorAddress }}</span>
                        </el-row>
                    </el-col>
                    </el-row>
                </el-card>
            </el-scrollbar>
                </el-tab-pane>

                <el-tab-pane label="자주 가는 순">
                    <el-scrollbar height="650px">
                <el-card
                :key="s" v-for="s in state.FrequenceSeniorCenterInfo" @click="loadModal(s)" shadow="hover" style="margin: 5px;">
                    <el-row>
                    <el-col :span="6">
                        <img src="https://kr.seaicons.com/wp-content/uploads/2015/06/house-icon.png" alt="My Image" width="90">
                    </el-col>
                    <el-col :span="16" :offset="1">
                        <el-row justify="start" align="middle">
                            <div>
                                <span style="font-family: SSShinb7; font-size: 30px;">{{ s.seniorName }}</span></div>
                            <el-divider style="margin: 12px;"></el-divider>
                            <span style="font-size: 14px;">{{ s.seniorAddress }}</span>
                        </el-row>
                    </el-col>
                    </el-row>
                </el-card>
            </el-scrollbar>
                </el-tab-pane>
                <el-tab-pane label="인원수 많은 순">
                    <el-scrollbar height="650px">
                    <div v-if="state.mostPeopleCenterInfo.length">
                        <el-card
                        :key="s" v-for="s in state.mostPeopleCenterInfo" @click="loadModal(s)" shadow="hover" style="margin: 5px;">
                            <el-row>
                            <el-col :span="6">
                                <img src="https://kr.seaicons.com/wp-content/uploads/2015/06/house-icon.png" alt="My Image" width="90">
                            </el-col>
                            <el-col :span="16" :offset="1">
                                <el-row justify="start" align="middle">
                                    <div>
                                        <span style="font-family: SSShinb7; font-size: 30px;">{{ s.seniorName }}</span></div>
                                    <el-divider style="margin: 12px;"></el-divider>
                                    <span style="font-size: 14px;">{{ s.seniorAddress }}</span>
                                </el-row>
                            </el-col>
                            </el-row>
                        </el-card>
                    </div>
                    <div v-else>
                        <span><span style="font-family: SSShinb7; font-size: 30px; color: #26730B;">내 나이가 어때서</span>  의 첫번째 손님입니다. ❤</span>
                    </div>
                    </el-scrollbar>
                </el-tab-pane>
            </el-tabs>


        </el-col>
        <BackButton/>
        <el-col :span="17">
            <div id="map" style="z-index:0"></div>
        </el-col>
    </el-row>

    <!--모달창 -->
    <SeniorCenterModal
    :centerInfo="state.ClickedSeniorCenter"
    :visible="state.dialogVisible"
    @closeCenterDialog="onCloseCenterDialog"
    :personnelList="state.personnelList"
    />

</template>

<script>
import { reactive, onMounted} from 'vue'
import { useStore } from 'vuex'
import SeniorCenterModal from './components/seniorCenterModal'
import BackButton from '@/views/components/BackButton'
import axios from 'axios';
axios.defaults.headers.post['Access-Control-Allow-Origin'] = 'http://i5b202.p.ssafy.io';
axios.defaults.headers.post['Content-Type'] = 'application/json';
const OPENVIDU_SERVER_URL = "https://i5b202.p.ssafy.io:4443"
const OPENVIDU_SERVER_SECRET = "ssafy"
export default {
    name: 'Map',
    // data() {
    //     return {  
    //         dialogVisible: false,
    //         SeniorCenterInfo : [],
    //     };
    // },
    components: {
        SeniorCenterModal,
        BackButton
    },
    setup() {
        const store = useStore()
        const state = reactive({
            
            dialogVisible: false,
            SeniorCenterInfo : [], 
            FrequenceSeniorCenterInfo : [],
            mostPeopleCenterInfo : [],
            ClickedSeniorCenter : {},
            enterInfo :{
                seniorId : 0,
                userId : '',
            },
            personnelList: [],
        })




        const clickDialogVisible = () => {
            state.dialogVisible = !state.dialogVisible
        }

        const initMap = () => {
            // console.log('123',state.dialogVisible)
            // console.log(1+" "+window.kakao);
            var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
            mapOption = {
                center: new window.kakao.maps.LatLng(33.450701, 126.570667),
                level: 3 // 지도의 확대 레벨
            };  

            // 지도를 생성합니다    
            var map = new  window.kakao.maps.Map(mapContainer, mapOption); 


            // 다른 이미지로 마커 생성하기
            var imageSrc = "https://kr.seaicons.com/wp-content/uploads/2015/06/house-icon.png", // 마커이미지의 주소입니다
            imageSize = new window.kakao.maps.Size(64, 69), // 마커이미지의 크기입니다
            imageOption = {offset: new window.kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

            // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
            var markerImage = new window.kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
                // markerPosition = new window.kakao.maps.LatLng(37.54699, 127.09598); // 마커가 표시될 위치입니다

            // 주소-좌표 변환 객체를 생성합니다
            var geocoder = new  window.kakao.maps.services.Geocoder();

            // 반복문 시작
            for (var i = 0; i < state.SeniorCenterInfo.length; i++) {
            //  console.log(state.SeniorCenterInfo.length);
            // console.log(state.SeniorCenterInfo[i].seniorAddress);
            // console.log(state.SeniorCenterInfo[i].seniorName);
            // 주소로 좌표를 검색합니다
            let address = state.SeniorCenterInfo[i].seniorAddress; // i번방에 들어있는 경로당 주소
            let name = state.SeniorCenterInfo[i].seniorName; // i번방에 들어있는 경로당 이름
            let roomInfo = state.SeniorCenterInfo[i];
            

            
            geocoder.addressSearch(address, function(result, status) {

                // console.log(name);
                // 정상적으로 검색이 완료됐으면 
                if (status ===  window.kakao.maps.services.Status.OK) {

                    var coords = new window.kakao.maps.LatLng(result[0].y,result[0].x);

                    // 결과값으로 받은 위치를 마커로 표시합니다
                    var marker = new window.kakao.maps.Marker({
                        map: map,
                        position: coords,
                        // position: markerPosition, 
                        image: markerImage, // 마커이미지 설정
                        clickable: true 
                    });
                
                    window.myMarker = marker
                    // 마커가 지도 위에 표시되도록 설정합니다.
                    marker.setMap(map);

                    // 마커에 커서가 오버됐을 때 마커 위에 표시할 인포윈도우를 생성합니다
                    // var iwContent = <div style="padding:5px;">${name}</div>; // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
                    
                    

                    // 인포윈도우를 생성 합니다
                    var infowindow = new window.kakao.maps.InfoWindow({
                        content : `<div style="width:150px;text-align:center;padding:6px 0;">${name}</div>`, // 백틱(`)
                        // content : iwContent
                    });

                    // 마커에 마우스오버 이벤트를 등록합니다
                    window.kakao.maps.event.addListener(marker, 'mouseover', function() {
                    // 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
                        infowindow.open(map, marker);
                    });

                    // 마커에 마우스아웃 이벤트를 등록합니다
                    window.kakao.maps.event.addListener(marker, 'mouseout', function() {
                        // 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다
                        infowindow.close();
                    });
                    // 마커에 클릭이벤트를 등록합니다
                    window.kakao.maps.event.addListener(marker, 'click', function() {
                        marker.fa.addEventListener("click", function() {
                            state.ClickedSeniorCenter = roomInfo;
                            getNumOfPeople()
                    })
                    });
                
                // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
                    map.setCenter(coords);
                } 
            });
        }
        }

        const getNumOfPeople = () => {
            const personnelListTmp = [0, 0, 0, 0, 0]
            store.commit('root/loadingOn')
            axios
                .get(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions`,{
                    auth: {
                        username: 'OPENVIDUAPP',
                        password: OPENVIDU_SERVER_SECRET
                    }
                })
                .then(res => {
                    store.commit('root/loadingOff')
                    const sessions = res.data.content
                    const sessionId = String(state.ClickedSeniorCenter.seniorId)
                    sessions.forEach(session => {
                        const split_str = session.id.split('-')
                        if (sessionId == split_str[0]) {
                            const index = parseInt(split_str[1])
                            personnelListTmp[index] = session.connections.numberOfElements
                        }
                    }); 
                    state.personnelList = personnelListTmp
                    state.dialogVisible = true
                })
                .catch((err) => {
                    store.commit('root/loadingOff')
                    alert(err)
                })
        }

        onMounted(() => {
            const userId = store.getters['root/getMyId']
            store.dispatch('root/requestSeniorCenterInfo', {userId : userId})
            .then(result => {
                // console.log(result);
                state.SeniorCenterInfo = result.data.data;
                    if (window.kakao && window.kakao.maps) {
                initMap();
            } else {
                const script = document.createElement('script');
                
                script.onload = () => window.kakao.maps.load(initMap);
                script.src = 'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=7c87159e377575ded609810fe7d11d0a&libraries=services';
                document.head.appendChild(script);
            }
            })
            .catch(() => {
                alert('지도를 불러오지 못했습니다.')
            })


            store.dispatch('root/requestFrequenceSeniorCenterInfo', {userId : userId})
            .then(result => {
                // console.log(result);
                state.FrequenceSeniorCenterInfo = result.data.data;
            })
        })
        const onCloseCenterDialog = () => {
            state.dialogVisible = false
        }

        const loadModal = (centerObject) => {
            state.ClickedSeniorCenter = centerObject;
            getNumOfPeople()
        }
        const clickMostPeople = () => {
            const sessionPeopleObject = {}
            store.commit('root/loadingOn')
            axios
            .get(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions`,{
                auth: {
                    username: 'OPENVIDUAPP',
                    password: OPENVIDU_SERVER_SECRET
                }
            })
            .then(res => {
                store.commit('root/loadingOff')
                const sessions = res.data.content
                sessions.forEach(session => {
                    const sessionId = session.id.split('-')[0]
                    if (!sessionPeopleObject[sessionId]) {
                        sessionPeopleObject[sessionId] = 0
                    }
                    sessionPeopleObject[sessionId] += session.connections.numberOfElements
                }); 
                const sortedList = []
                for(let id in sessionPeopleObject) {
                    sortedList.push([id, sessionPeopleObject[id]])
                }
                sortedList.sort((a, b) => {
                    return a[1] - b[1]
                })
                const mostPeopleCenterTmp = []
                sortedList.forEach(centerId => {
                    let id = parseInt(centerId[0])

                    for(let i = 0; i < state.SeniorCenterInfo.length; i++) {
                        if (state.SeniorCenterInfo[i]['seniorId'] == id) {
                            mostPeopleCenterTmp.push(state.SeniorCenterInfo[i])
                            break
                        }
                    }
                })
                state.mostPeopleCenterInfo = mostPeopleCenterTmp
            })
            .catch((err) => {
                store.commit('root/loadingOff')
                alert(err)
            })
        }
        const handleTabClick = (tab) => {
            if (tab.props.label == '인원수 많은 순') {
                clickMostPeople()
            }
        }

        return{state, clickDialogVisible, initMap, onCloseCenterDialog, loadModal, clickMostPeople, handleTabClick}

    },
    }
</script>

<style>
#map {
    width: 100%;
    height: 100%;
}
.map-container{
    height:  100%;
}
</style>