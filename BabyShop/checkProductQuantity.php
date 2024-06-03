<?php
    include_once("control.php");
    $ctrl_checkproduct = new control();
    if (isset($_POST["ProductID"]) && isset($_POST["Quantity"]))
    {
        $kq = $ctrl_checkproduct->checkProductQuantity($_POST["ProductID"], $_POST["Quantity"]);
        echo $kq;
    }
?>