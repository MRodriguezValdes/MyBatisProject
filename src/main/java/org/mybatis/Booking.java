package org.mybatis;

import java.util.Date;

public class Booking {
    private int id;
    private int location_number;
    private int id_client;
    private String client;
    private int id_agency;
    private String agency;
    private double price;
    private String id_type;
    private String room;
    private int id_hotel;
    private String hotel;
    private Date check_in;
    private int room_nights;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLocation_number() {
        return location_number;
    }

    public void setLocation_number(int location_number) {
        this.location_number = location_number;
    }

    public int getClientId() {
        return id_client;
    }

    public void setClientId(int clientId) {
        this.id_client = clientId;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public int getId_agency() {
        return id_agency;
    }

    public void setId_agency(int id_agency) {
        this.id_agency = id_agency;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getId_type() {
        return id_type;
    }

    public void setId_type(String id_type) {
        this.id_type = id_type;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getId_hotel() {
        return id_hotel;
    }

    public void setId_hotel(int id_hotel) {
        this.id_hotel = id_hotel;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public Date getCheck_in() {
        return check_in;
    }

    public void setCheck_in(Date check_in) {
        this.check_in = check_in;
    }

    public int getRoom_nights() {
        return room_nights;
    }

    public void setRoom_nights(int room_nights) {
        this.room_nights = room_nights;
    }
}
