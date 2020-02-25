package com.example.dtstore.utilities;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Server {
    public static String localhost = "192.168.1.62";
    public static String pathLoaiSanPham = "http://" + localhost + "/php/getloaisanpham.php";
    public static String LastestProductUrl = "http://" + localhost + "/php/get_lastest_product.php";
    public static String ProductURL = "http://" + localhost + "/php/getProduct.php?page=";

    public static String getIPv4Address(){
        String ipv4 = "";
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            ipv4 = inetAddress.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return ipv4;
    }
}
