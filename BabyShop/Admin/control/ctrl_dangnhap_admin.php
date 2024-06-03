<?php
    include_once("ctrl_frm_admin.php");
    $ctrl_kiemtra_admin = new ctrl_frm_admin();

    $id_sdt = $_POST["username"];
    $id_matkhau = $_POST["password"];

    echo $id_sdt, $id_matkhau;

    $kiemtra = $ctrl_kiemtra_admin->kiemtra($id_sdt, $id_matkhau);
    
    if ($num_rows = $kiemtra->num_rows == 1)
    {   

        session_start();
        while($row = $kiemtra->fetch_assoc())
        {
            $_SESSION["idstaff"] = $row["StaffID"];
            $_SESSION["username"] = $row["Staffname"];
            $_SESSION["HinhNV"] = $row["ImageStaff"];
            $_SESSION["MaNV"] = $row["StaffID"];
            echo '<script>alert("Hello from Albert!");</script>';

        }

        header("location:../admin.php");

    }
    else
    {
        echo "Đăng nhập không thành công !";
    }
?>