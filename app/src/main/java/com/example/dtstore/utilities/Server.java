package com.example.dtstore.utilities;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Server {
    public static String localhost = "192.168.1.62";
    public static String CategoryUrl = "http://" + localhost + "/android/getCategories.php";
    public static String LatestProductUrl = "http://" + localhost + "/android/getLatestProducts.php";
    public static String ProductURL = "http://" + localhost + "/android/getProducts.php?page=";

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
