<?php
    include_once("control.php");
    $ctrl_userpoints = new control();
    if (isset($_POST["Username"]) && isset($_POST["Points"]))
    {
        $kq = $ctrl_userpoints->minusPoint($_POST["Username"], $_POST["Points"]);
        echo $kq;
    }
?>