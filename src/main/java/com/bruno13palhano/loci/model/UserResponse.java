package com.bruno13palhano.loci.model;

public class UserResponse {
    private String uid;
    private String username;
    private String email;
    private byte[] photo;
    private String phone;
    private String address;
    private String city;
    private String timestamp;

    public UserResponse() {}

    public UserResponse(String uid, String username, String email, byte[] photo, String phone, String address,
                        String city, String timestamp) {
        this.uid = uid;
        this.username = username;
        this.email = email;
        this.photo = photo;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.timestamp = timestamp;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
