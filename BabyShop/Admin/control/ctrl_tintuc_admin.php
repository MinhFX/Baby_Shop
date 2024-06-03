<?php
    session_start();
?>

<?php
include_once("../control/connect_admin.php");
$connect_tintuc_admin = new connect_admin;
$link = $connect_tintuc_admin->getlink();

// Thêm tin tức //
if (isset($_POST["them_mon"])) {
    // Lấy vị trí và chép hình vào thư mục
    $thu_muc = "../../ImageNews/";
    $ten_file = $thu_muc . $_FILES["hinh_mon"]["name"];
    move_uploaded_file($_FILES["hinh_mon"]["tmp_name"], $ten_file);

    $id_tenmonan = $_POST["ten_mon"];
    $id_mota = $_POST["mota_mon"];
    $id_hinhanh = $_FILES["hinh_mon"]["name"];
    $id_trangthai = $_POST["trangthai"];
    $id_staff = $_SESSION["idstaff"];

    date_default_timezone_set("Asia/Ho_Chi_Minh");
    $ngay = date(DATE_W3C);

    $them_taikhoan = "INSERT INTO news (Title, Content, ImageNews, Status, Date, StaffID) VALUES ('$id_tenmonan', '$id_mota', '$id_hinhanh', $id_trangthai, '$ngay', '$id_staff')";

    if ($link->query($them_taikhoan)) {
        header("location:../admin.php?pid=9");
    } else {
        echo "<script> alert('Thêm không thành công') </script>";
    }
}

// Cập nhật tin tức //
if (isset($_POST["cap_nhat_mon"])) {
    $id = $_GET["id"];
    $thu_muc = "../../ImageNews/";
    $ten_file = $thu_muc . $_FILES["hinh_mon"]["name"];
    move_uploaded_file($_FILES["hinh_mon"]["tmp_name"], $ten_file);

    $id_tenmonan = $_POST["ten_mon"];
    $id_mota = $_POST["mota_mon"];
    $id_trangthai = $_POST["trangthai"];
    $id_staff = $_SESSION["idstaff"];

    date_default_timezone_set("Asia/Ho_Chi_Minh");
    $ngay = date(DATE_W3C);

    if ($_FILES["hinh_mon"]["name"] == "") {
        $cap_nhat_mon = "UPDATE news SET Content = '$id_mota', Status = $id_trangthai, Date = '$ngay' WHERE NewsID = '$id'";
    } else {
        $id_hinhanh = $_FILES["hinh_mon"]["name"];
        $cap_nhat_mon = "UPDATE news SET Title = '$id_tenmonan', Content = '$id_mota', ImageNews = '$id_hinhanh', Status = $id_trangthai, StaffID = '$id_staff' WHERE NewsID = '$id'";
    }

    if ($link->query($cap_nhat_mon)) {
        header("location:../admin.php?pid=9");
    } else {
        echo "<script> alert('Cập nhật không thành công') </script>";
    }
}

// Xóa tin tức //
if (isset($_GET["xoa_mon"])) {
    $id = $_GET["id"];
    $xoa_mon = "DELETE FROM news WHERE NewsID = '$id'";
    if ($link->query($xoa_mon)) {
        header("location:../admin.php?pid=9");
    } else {
        echo "<script> alert('Xóa không thành công') </script>";
    }
}
?>
