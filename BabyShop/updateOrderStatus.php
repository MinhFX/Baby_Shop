<?php
    include_once("control.php");
    $ctrl_orderstatus = new control();
    if (isset($_POST["OrderID"]) && isset($_POST["Status"]))
    {
        $kq = $ctrl_orderstatus->updateOrderStatus($_POST["OrderID"], $_POST["Status"]);
        echo $kq;
    }
?>