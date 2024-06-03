<?php
    include_once("control.php");
    $ctrl_orderdetail = new control();
    if (isset($_POST["ProductID"]) && isset($_POST["Price"]) && isset($_POST["Quantity"]))
    {
        $kq = $ctrl_orderdetail->createOrderDetail($_POST["ProductID"], $_POST["Price"], $_POST["Quantity"]);
        echo $kq;
    }
?>