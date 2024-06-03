<?php
    include_once("control.php");
    $ctrl_user = new control();
    if (isset($_POST["Username"]))
        $username = $_POST["Username"];
    if (isset($username))
    {
        $ctrl_user->getUser($username);
    }
?>