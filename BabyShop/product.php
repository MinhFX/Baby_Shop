<?php
    include_once("control.php");
    $ctrl_product = new control();
    if (isset($_GET["proid"]))
        $proid = $_GET["proid"];
    if (isset($proid))
    {
        $ctrl_product->getProductWithID($proid);
    }
    else
    {
        $ctrl_product->getProduct();
    }
?>