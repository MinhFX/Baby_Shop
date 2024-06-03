<?php
    include_once("control.php");
    $ctrl_login = new control();
    $kq = $ctrl_login->signup();
    if (isset($kq))
    {
        if (is_string($kq))
        {
            echo $kq;
        }
        else
        {
            $row = $kq[0];
            if ($row)
            {
                echo $row;
            }
        }
    }
?>