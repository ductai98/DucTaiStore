package com.example.dtstore.models;

public class LoaiSanPham {
    private int id;
    private String tenLoaiSanPham;
    private String hinhLoaiSanPham;

    public LoaiSanPham(int id, String tenLoaiSanPham, String hinhLoaiSanPham) {
        this.id = id;
        this.tenLoaiSanPham = tenLoaiSanPham;
        this.hinhLoaiSanPham = hinhLoaiSanPham;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenLoaiSanPham() {
        return tenLoaiSanPham;
    }

    public void setTenLoaiSanPham(String tenLoaiSanPham) {
        this.tenLoaiSanPham = tenLoaiSanPham;
    }

    public String getHinhLoaiSanPham() {
        return hinhLoaiSanPham;
    }

    public void setHinhLoaiSanPham(String hinhLoaiSanPham) {
        this.hinhLoaiSanPham = hinhLoaiSanPham;
    }



}
