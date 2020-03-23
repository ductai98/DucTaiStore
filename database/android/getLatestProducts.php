<?php
    include "connect.php";
    $latestProductArray = array();
    $query = "SELECT * FROM product ORDER BY ID DESC LIMIT 6";
    $data = $conn->query($query); #execute the query


    while($row = $data->fetch_assoc()){ #Fetch into every row of dataset
        #Push data to latestProductArray
        array_push($latestProductArray, new Product($row['id'], $row['productName'], $row['productPrice']
                                            , $row['productImage'], $row['productDescript'], $row['idCategory']));
    }
    echo json_encode($latestProductArray); ##return json to webservice
    class Product{
        function Product($id, $productName, $productPrice, $productImage, $productDescript, $idCategory){
            $this->id = $id;
            $this->productName = $productName;
            $this->productPrice = $productPrice;
            $this->productImage = $productImage;
            $this->productDescript = $productDescript;
            $this->idCategory = $idCategory;
        }
    }
?>