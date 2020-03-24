<?php
    include "connect.php";
    $customerName = $_POST['name'];
    $customerPhone = $_POST['phone'];
    $customerEmail = $_POST['email'];

    if(strlen($customerName) > 0 && strlen($customerEmail) > 0 && strlen($customerPhone) > 0){
        $dataset = $conn->query("INSERT INTO receipt(id, customerName, customerPhone, customerEmail) 
                                    VALUES(null, '$customerName', '$customerPhone', '$customerEmail')");
        if($dataset){
            $id = $conn->insert_id;
            echo $id;
        }else{
            echo "INSERT FAILED: " .$conn->error;
        }
    }else{
        echo "Missing Data!";
    }
    $conn->close()
?>