<?php
    include "connect.php";
    $page = $_GET['page'];
    $idCategory = $_POST['categoryid'];
    $space = 5;
    $limit = ($page - 1) * $space; #Determine the first item position of each page
    $productArray = array();
    $query = "SELECT * FROM product WHERE idCategory = $idCategory LIMIT $limit, $space";
    $dataset = $conn->query($query);#execute the query

    while($row = $dataset->fetch_assoc()){#Fetch into every row of dataset
        #Push data to productArray
        array_push($productArray, new Product($row['id'], $row['productName'], $row['productPrice']
                                    , $row['productImage'], $row['productDescript'], $row['idCategory']));
    }

    echo json_encode($productArray);
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