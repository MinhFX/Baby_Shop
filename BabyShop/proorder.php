<?php
    include_once("control.php");
    $ctrl_proorder = new control();
    if (isset($_POST["OrderID"]))
        $orderID = $_POST["OrderID"];
    if (isset($orderID))
    {
        $ctrl_proorder->getProductOrder($orderID);
    }
?>