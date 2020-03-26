<?php
    include "connect.php";
    $json = $_POST['json'];

    $data = json_decode($json, true);
    foreach($data as $value){
        $receiptID = $value['receiptID'];
        $productID = $value['productID'];
        $productName = $value['productName'];
        $productPrice = $value['productPrice'];
        $productQuantity = $value['productQuantity'];

        $query = "INSERT INTO receiptdetail(id, receiptID, productID, productName, productPrice, productQuantity)
                    VALUES(null, '$receiptID', '$productID', '$productName', '$productPrice', '$productQuantity')";

        $dataset = $conn->query($query);    
    }

    if($dataset){
        echo "1";
    }else{
        echo $conn->error;
    }
?>