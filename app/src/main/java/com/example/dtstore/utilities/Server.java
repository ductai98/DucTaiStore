package com.example.dtstore.utilities;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Server {
    public static String localhost = "https://lductaistore.000webhostapp.com";
    public static String CategoryUrl = localhost + "/getCategories.php";
    public static String LatestProductUrl = localhost + "/getLatestProducts.php";
    public static String ProductURL = localhost + "/getProducts.php?page=";
    public static String ReceiptURL = localhost + "/getCustomerInfo.php";
    public static String ReceiptDetailURL = localhost + "/receiptDetail.php";

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
