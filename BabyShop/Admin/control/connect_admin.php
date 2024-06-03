<?php
require_once("config_admin.php");

class connect_admin
{
    protected $link;
    function __construct(){
        $this->link = new mysqli(servername,username,password,databasename);
        $this->link->set_charset("utf8mb4");
    }

    function getlink()
    {
        return $this->link;
    }
}
?>