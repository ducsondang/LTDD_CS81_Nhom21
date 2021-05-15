package com.example.appbanhoa.activity.model;

public class Loaih {
    public int Id;
    public String Tenloaih;
    public String Hinhanhloaih;

    public  Loaih(int id, String tenloaih, String hinhanhloaih) {
        Id = id;
        Tenloaih = tenloaih;
        Hinhanhloaih = hinhanhloaih;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTenloaih() {
        return Tenloaih;
    }

    public void setTenloaih(String tenloaih) {
        Tenloaih = tenloaih;
    }

    public String getHinhanhloaih() {
        return Hinhanhloaih;
    }

    public void setHinhanhloaih(String hinhanhloaih) {
        Hinhanhloaih = hinhanhloaih;
    }
}

