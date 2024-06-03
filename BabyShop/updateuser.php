<?php
    include_once("control.php");
    $ctrl_update = new control();

    $Username = $_POST["Username"];
    $Password = $_POST["Password"];

    $Email = $_POST["Email"];
    
    $Fullname = $_POST["Fullname"];
    
    $Birthday = $_POST["Birthday"];

    $Gender = $_POST["Gender"];

    $Phone = $_POST["Phone"];

    $Address = $_POST["Address"];
    

    
    $kq = $ctrl_update->updateUser($Username, $Password, $Email, $Fullname, $Birthday, $Gender, $Phone, $Address);
    if(isset($kq))
    {
        echo $kq;
    }
?>