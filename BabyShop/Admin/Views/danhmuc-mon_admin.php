<?php
    include_once("control/ctrl_frm_admin.php");
    $ctrl_danhmuc_monan = new ctrl_frm_admin();
    $kq_theloai_mon = $ctrl_danhmuc_monan->lay_danh_sach_loai_mon_an_1();
?>

<div class = "header-infor">
    <h1> Danh mục món ăn </h1>
</div>

<div class = "infor-wrapper" id = "tick-danhmuc-mon">
<?php
    while($row = $kq_theloai_mon->fetch_assoc())
    {
?>
<div class = "card-categories">
<a href="admin.php?pid=2&theloai=<?php echo $row["MaLoai"] ?>">
    <div class = "card-header-categories">
        <img src="../assets/img_menu/<?php echo $row["HinhAnh"] ?>" alt="" width="360" height="260">
    </div>
    <div class ="card-body-categories">
        <h1> <?php echo $row["TenLoai"] ?> </h1>
        <div>
        <p> <?php echo $row["GiaThapNhat"] ?> đến <?php echo $row["GiaCaoNhat"] ?> </p>
        <span> <?php echo $row["SoLuongLoai"] ?> món </span>
        </div>
    </div>
</a>

</div>

<?php        
    }
?>


</div>        