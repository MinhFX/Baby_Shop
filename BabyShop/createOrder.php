<?php
    include_once("control.php");
    $ctrl_order = new control();
    if (isset($_POST["MaxID"]))
    {
        $kq = $ctrl_order->getMaxOrderID();
        echo $kq+1;
    }
    if (isset($_POST["Username"]) && isset($_POST["Total"]) && isset($_POST["Fullname"]) && isset($_POST["Phone"]) && isset($_POST["Address"]))
    {
        $kq = $ctrl_order->createOrder($_POST["Username"], $_POST["Total"], $_POST["Fullname"], $_POST["Phone"], $_POST["Address"]);
        echo $kq;
    }
?>