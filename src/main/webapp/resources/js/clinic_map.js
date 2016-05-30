/**
 * Created by user on 30.05.2016.
 */
var map;

var markers = [];
function initMap(clinics) {
    var initThisFuckingMup = function (position) {
        var userLatLng = {
            lat: position.coords.latitude,
            lng: position.coords.longitude
        };
        map = new google.maps.Map(document.getElementById('map'), {
            center: userLatLng,
            zoom: 12

        });
        markers.push(new google.maps.Marker({
            map: map,
            position: userLatLng,
            title: "Вы здесь))",
            icon: "http://iconizer.net/files/PICOL/orig/user_full_32.png"
        }));
        clinics.forEach(function (clinic) {
            markers.push(new google.maps.Marker({
                map: map,
                position: clinic.latLng,
                title: clinic.title
            }));
        });
    };
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(initThisFuckingMup);
    }


}
