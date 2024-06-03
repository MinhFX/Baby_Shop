<?php
include_once("control.php");
$ctrl_login = new control();
if (isset($_POST['username']) && isset($_POST['password'])) {
    $kq = $ctrl_login->logIn("users", $_POST['username'], $_POST['password']);
    if (isset($kq)) {
        echo $kq;
    }
} 
else echo "Vui lòng điền đủ thông tin !!";

?>
