<?php
    include_once("../control/connect_admin.php");
    $connect_chinhsua_admin = new connect_admin;
    $link = $connect_chinhsua_admin->getlink();

    $id_sodienthoai = $_GET["id_khachhang"];

    
    if (isset($_GET["id_khachhang"]))
    {
        $xoa_khachhang_hoadon = "UPDATE table_hoadon SET Phone = NULL WHERE Phone = '$id_sodienthoai'";
        $xoakh_qr = "DELETE FROM table_khachhang WHERE Phone='$id_sodienthoai'";
        if ($link->query($xoakh_qr) && $link->query($xoa_khachhang_hoadon))
        {
            header("location:../admin.php?pid=3");
        }
        else 
        {
            echo "<script> alert('Xóa không thành công') </script>";
        }
    }
    else
    {
// Vị trí lưu
        $thu_muc = "../assets/images_avatar/";
        $ten_file = $thu_muc . $_FILES["HinhKH"]["name"];
// Chép hình vào thư mục
        move_uploaded_file($_FILES["HinhKH"]["tmp_name"], $ten_file);

//---------------------------------
        $id_hoten = $_POST["hoten"];
        $id_diem = $_POST["diem"];
        $id_sdt = $_POST["sdt"];
        $id_sdt_fix = substr($id_sdt,1);
        $id_gioitinh = $_POST["gioitinh"];
        $id_diachi = $_POST["diachi"];
        $id_avt = $_FILES["HinhKH"]["name"];
        $capnhatkh_qr = "UPDATE table_khachhang SET Hoten = '$id_hoten', Diem = '$id_diem', GioiTinh = '$id_gioitinh', DiaChi = '$id_diachi', HinhKH = '$id_avt' WHERE Phone = '$id_sdt'";
        if ($link->query($capnhatkh_qr))
        {
            header("location:../admin.php?pid=3&id_khachhang=$id_sdt_fix");
        }
        else 
        {
            echo "<script> alert('Cập nhật không thành công') </script>";
        }
    }
?>