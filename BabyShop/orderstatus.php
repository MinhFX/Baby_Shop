<?php
    include_once("control.php");
    $ctrl_orderstatus = new control();
    if (isset($_POST["Username"]) && isset($_POST["Status"]))
    {
        $ctrl_orderstatus->getOrderStatus($_POST["Username"], $_POST["Status"]);
    }
?>