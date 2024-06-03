<?php
    include_once("control.php");
    $ctrl_order = new control();
    if (isset($_POST["Username"]))
    {
        $ctrl_order->getOrder($_POST["Username"]);
    }
?>