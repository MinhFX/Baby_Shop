<?php
    include_once("control.php");
    $strl_banner = new control();
    if(isset($_GET["Search"]))
    {
        $strl_banner->getProductWithSearch($_GET["Search"]);
    }
?>