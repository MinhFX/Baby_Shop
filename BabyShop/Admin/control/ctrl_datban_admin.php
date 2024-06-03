<?php
    session_start();
?>

<?php
include_once("../control/ctrl_frm_admin.php");
$connect_datban_admin = new connect_admin;
$ctrl_frm = new ctrl_frm_admin;
$link = $connect_datban_admin->getlink();
$data = $_POST["data"];

if (isset($_POST["cap_nhat"])) {
    $id = $_GET["id"];
    $trangthai = $_POST["trangthai"];
    $staffid = $_SESSION["idstaff"];
    $sql = "UPDATE orders SET Status = '$trangthai', StaffID = '$staffid' WHERE OrderID = '$id'";


    $kq_kiemtra = $ctrl_frm->kiem_tra_don_huy($id);
    while ($row = $kq_kiemtra->fetch_assoc()) {
        $trangthai_hientai = $row["Status"];
        if ($trangthai_hientai == 0)
        {
            echo "<script> alert('Đơn hàng đã hủy không thể đổi trạng thái.'); history.back(); </script>";
        }
        else if ($trangthai_hientai == $trangthai) {
            header("location:../admin.php?pid=5");
        } else {
            if ($link->query($sql)) {
                foreach ($data as $item) {
                    $quantity = $item['TongSoLuong'];
                    $id = $item['ProductID'];
                    if ($_POST["trangthai"] == 0) {
                        $query = "UPDATE products SET Quantity = Quantity + '$quantity' WHERE ProductID = '$id';";
                        $link->query($query);
                        header("location:../admin.php?pid=5");
                    }
                }
                if ($_POST["trangthai"] == 4)
                {
                    $total = $row["Total"];
                    $points = (($total-10000)/1000);
                    $kq_them_diem = $ctrl_frm->plusPoint($row["Username"],$points);
                }
                header("location:../admin.php?pid=5");
            } else {
                echo "Có lỗi, không thể cập nhật đơn hàng !!";
            }
        }
    }
}