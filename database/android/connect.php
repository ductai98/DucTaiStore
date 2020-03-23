<?php
    $host = "localhost";
    $username = "root";
    $password = "";
    $database = "dtstore";

    $conn = mysqli_connect($host, $username, $password, $database);
    $conn->query("SET NAMES 'utf8'");
?>