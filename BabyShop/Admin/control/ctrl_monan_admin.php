<?php
    include_once("../control/connect_admin.php");
    $connect_monan_admin = new connect_admin;
    $link=$connect_monan_admin->getlink();


    // Thêm món ăn //

    if (isset($_POST["them_mon"]))
    {
    // Lấy vị trí
    $thu_muc = "../../assets/img_menu/";
    $ten_file = $thu_muc . $_FILES["hinh_mon"]["name"];
    
    // Chép hình vào file
    move_uploaded_file($_FILES["hinh_mon"]["tmp_name"], $ten_file);


    $id_maloai = $_POST["loai_mon"];
    $id_tenmonan = $_POST["ten_mon"];
    $id_gia = $_POST["gia_mon"];
    $id_mota = $_POST["mota_mon"];
    $id_hinhanh = $_FILES["hinh_mon"]["name"];
    $id_trangthai = $_POST["trangthai"];

    $them_taikhoan = "INSERT INTO table_menu (MaLoai, TenMonAn, Gia, MoTa, HinhAnh, TrangThaiMenu) VALUES ('$id_maloai','$id_tenmonan','$id_gia', '$id_mota','$id_hinhanh',$id_trangthai)";
    $cap_nhat_stt1 = "SET @row_number = 0";
    $cap_nhat_stt2 = "UPDATE table_menu SET MaMonAn = (@row_number := @row_number + 1)";

    if ($link->query($them_taikhoan) && $link->query($cap_nhat_stt1) && $link->query($cap_nhat_stt2)) {
        header("location:../admin.php?pid=2");
    }
    else 
    {
        echo "<script> alert('Thêm không thành công') </script>";
    }
    }

    // Cập nhật món //

    if (isset($_POST["cap_nhat_mon"]))
    {
        $id = $_GET["id"];
        // Lấy vị trí

        $thu_muc = "../../assets/img_menu/";
        $ten_file = $thu_muc . $_FILES["hinh_mon"]["name"];

        // Chép vào file
        move_uploaded_file($_FILES["hinh_mon"]["tmp_name"], $ten_file);

        $id_maloai = $_POST["loai_mon"];
        $id_tenmonan = $_POST["ten_mon"];
        $id_gia = $_POST["gia_mon"];
        $id_mota = $_POST["mota_mon"];
        $id_trangthai = $_POST["trangthai"];

        if ($_FILES["hinh_mon"]["name"] == "")
        {
            $cap_nhat_mon = "UPDATE table_menu SET TenMonAn = '$id_tenmonan', Gia = '$id_gia', MoTa = '$id_mota', TrangThaiMenu = $id_trangthai WHERE MaMonAn = '$id'";
        } else
        {
            $id_hinhanh = $_FILES["hinh_mon"]["name"];
            $cap_nhat_mon = "UPDATE table_menu SET TenMonAn = '$id_tenmonan', Gia = '$id_gia', MoTa = '$id_mota', HinhAnh = '$id_hinhanh', TrangThaiMenu = $id_trangthai WHERE MaMonAn = '$id'";
        }

        echo "CapNhat";
        if ($link->query($cap_nhat_mon))
        {
            header("location:../admin.php?pid=2");
        }
        else 
        {
            echo "<script> alert('Cập nhật không thành công') </script>";
        }
    }

    if (isset($_GET["xoa_mon"]))
    {
        $id = $_GET["id"];
        $xoa_mon = "DELETE FROM table_menu WHERE MaMonAn = '$id'";
        if ($link->query($xoa_mon))
        {
            header("location:../admin.php?pid=2");
        }
        else
        {
            echo "<script> alert('Xóa không thành công') </script>";
        }
    }

    
?>