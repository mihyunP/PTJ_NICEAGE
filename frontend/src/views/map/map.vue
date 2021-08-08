<template>
  <div id="app">
    <div id="map"></div>
  </div>
</template>

<script>
export default {
    mounted() {
        if (window.kakao && window.kakao.maps) {
            this.initMap();
        } else {
            const script = document.createElement('script');
            /* global kakao */
            script.onload = () => kakao.maps.load(this.initMap);
            script.src = 'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=7c87159e377575ded609810fe7d11d0a';
            document.head.appendChild(script);
        }
    },
    methods: {
        initMap() {
            var container = document.getElementById('map'); // 지도를 표시할 div 
            var options = {
              center: new kakao.maps.LatLng(36.35111, 127.38500), // 지도의 중심좌표
              level: 3 // 지도의 확대 레벨
            };

            var map = new kakao.maps.Map(container, options);
            map.setMapTypeId(kakao.maps.MapTypeId.normal); // 지도 타입 선택 ex. terrain, HYBRID

        }
    }
}
</script>

<style>
#map {
    width: 100%;
    height: 100%;
}
</style>