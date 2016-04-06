package com.luckypets.entity;

import javax.persistence.*;

@Entity
@Table(name = "lat_lng")
public class LatLng {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "lat")
    private double lat;//latitude - широта
    @Column(name = "lng")
    private double lng;//longitude - долгота

    public LatLng() {
    }

    public LatLng(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    /**
     * @link https://ru.wikipedia.org/wiki/%D0%9E%D1%80%D1%82%D0%BE%D0%B4%D1%80%D0%BE%D0%BC%D0%B8%D1%8F
     */
    private static final double l = 111.1;//kilometres

    public double distance(LatLng that) {
        double delta = Math.acos(Math.sin(lat) * Math.sin(that.lat) +
                Math.cos(lat) * Math.cos(that.lat) * Math.cos(lng - that.lng));
        return l * delta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LatLng latLng = (LatLng) o;

        if (id != latLng.id) return false;
        if (Double.compare(latLng.lat, lat) != 0) return false;
        if (Double.compare(latLng.lng, lng) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        temp = Double.doubleToLongBits(lat);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lng);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "LatLng{" +
                //"id=" + id +
                "lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}
