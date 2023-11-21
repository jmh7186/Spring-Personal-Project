<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script async src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBicq2L-tbKrWepZ7ltFJrXrxxV3OtM9BA&callback=initMap"></script>
<div id="maps" style="text-align: center;">
	<h1>キャル株式会社</h1>
	<div id="googlemap" style="width: 70%; height: 500px; margin: 0 auto;"></div>
	<h3>〒542-0081<br>大阪府大阪市中央区南船場4丁目４番２１号<br>TODA BUILDING 心斎橋 １２F</h3>
</div>
<script>
	function initMap() {
		const myLatlng = { lat: 34.675820, lng: 135.4999 };
		const map = new google.maps.Map(document.getElementById("googlemap"), {
			center : myLatlng,
			zoom : 18
		});
		new google.maps.Marker({
		    position: myLatlng,
		    map,
		    title:"キャル株式会社"
		});
	}
	
	window.initMap = initMap;
</script>