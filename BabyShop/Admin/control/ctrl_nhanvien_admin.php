<?php
include_once("../control/connect_admin.php");
$connect_nhanvien_admin = new connect_admin;
$link = $connect_nhanvien_admin->getlink();
$id = $_GET["id"];

if (isset($_POST["xoa"])) {
    // Xóa thông tin nhân viên và các liên kết khác trong cơ sở dữ liệu
    $xoanv_tintuc = "UPDATE news SET StaffID = NULL WHERE StaffID = '$id'";
    $xoakh_qr = "DELETE FROM staff WHERE StaffID='$id'";
    
    if ($link->query($xoanv_tintuc) && $link->query($xoakh_qr)) {
        header("location:../admin.php?pid=4");
    } else {
        echo "<script> alert('Xóa không thành công') </script>";
    }
} else if(isset($_POST["capnhat"])) {
    // Cập nhật thông tin của nhân viên
    $id_staffname = $_POST["staffname"];
    $id_password = $_POST["password"];
    $id_fullname = $_POST["fullname"];
    $id_birthday = $_POST["birthday"];
    $id_gender = $_POST["gender"];
    $id_phone = $_POST["phone"];
    $id_address = $_POST["address"];
    $id_imagestaff= $_POST["imagestaff"];
    $id_role = $_POST["role"];

    $update_query = "UPDATE staff SET 
                        Staffname = '$id_staffname',
                        Password = '$id_password',
                        Fullname = '$id_fullname',
                        Birthday = '$id_birthday',
                        Gender = '$id_gender',
                        Phone = '$id_phone',
                        Address = '$id_address',
                        ImageStaff = '$id_imagestaff',
                        Role = '$id_role'
                    WHERE StaffID = '$id'";

    if ($link->query($update_query)) {
        header("location:../admin.php?pid=44&id=$id");
    } else {
        echo "<script> alert('Cập nhật không thành công') </script>";
    }
} else if($_POST["them_nv"]) {
    // Thêm thông tin của nhân viên mới vào cơ sở dữ liệu
    $id_staffname = $_POST["staffname"];
    $id_password = $_POST["password"];
    $id_fullname = $_POST["fullname"];
    $id_birthday = $_POST["birthday"];
    $id_gender = $_POST["gender"];
    $id_phone = $_POST["phone"];
    $id_address = $_POST["address"];
    $id_imagestaff = $_POST["imagestaff"];
    $id_role = $_POST["role"];

    $them_taikhoan = "INSERT INTO staff (Staffname, Password, Fullname, Birthday, Gender, Phone, Address, ImageStaff, Role)
                    VALUES ('$id_staffname','$id_password','$id_fullname','$id_birthday','$id_gender','$id_phone','$id_address','$id_imagestaff','$id_role')";

    if ($link->query($them_taikhoan)) {
        header("location:../admin.php?pid=4");
    } else {
        echo "<script> alert('Thêm không thành công') </script>";
    }
}
?>
