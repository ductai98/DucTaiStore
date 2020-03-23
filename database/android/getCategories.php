<?php
    include "connect.php";
    $query = "SELECT * FROM category";
    $data = $conn->query($query);
    $categoryArray = array();

    while($row = $data->fetch_assoc()){
        array_push($categoryArray, new Category($row['id'], $row['categoryName'], $row['categoryImage']));
    }

    echo json_encode($categoryArray);
    
    class Category{
        function Category($id, $categoryName, $categoryImage){
            $this->id = $id;
            $this->categoryName = $categoryName;
            $this->categoryImage = $categoryImage;
        }
    }
?>