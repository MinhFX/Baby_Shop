<?php
    include_once("control.php");
    $ctrl_product = new control();

    if(isset($_GET["best"]))
    {
        if($_GET["best"] == 1)
        {
            $ctrl_product->getProductBestSale();

        }
        else{
            $ctrl_product->getProductBestSale2();
        }
    }
?>