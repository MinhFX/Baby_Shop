<?php
    include_once("control/ctrl_frm_admin.php");
    $ctrl_tintuc_detail = new ctrl_frm_admin();
    $kq_loai = $ctrl_tintuc_detail->lay_danhsachtintuc();

    if (isset($_GET["them_mon"]))
    {
?>

<div class="header-infor">
    <h1> Thêm tin tức </h1>
</div>
<div class="infor-wrapper">
    <div class="themmon-wrapper">
        <form action="control/ctrl_tintuc_admin.php" enctype="multipart/form-data" method="post">
            <div class="themmon-layer1">
                <div class="themmon-layer2">
                    <label for=""> Tiêu đề </label>
                    <br>
                    <input type="text" name="ten_mon" placeholder="Nhập tên tin tức..." required>
                </div>
            </div>

            <div class="themmon-layer1">
                <div class="themmon-layer2">
                    <label for=""> Trạng thái </label>
                    <br>
                    <input type="radio" name="trangthai" id="trangthai" value="1" required>
                    <label for="trangthai" id="label-trang-thai1"> Công khai </label>
                    <input type="radio" name="trangthai" id="trangthai" value="0" required>
                    <label for="trangthai" id="label-trang-thai2"> Ẩn tin </label>
                </div>
            </div>

            <div class="themmon-layer1">
                <div class="themmon-layer2">
                    <label for=""> Content </label>
                    <br>
                    <textarea name="mota_mon" id="" rows="10" placeholder="Nhập mô tả tin tức..."></textarea>
                </div>
            </div>

            <div class="themmon-layer1">
                <div class="themmon-layer2">
                    <label for=""> Thêm hình ảnh </label>
                    <br>
                    <input type="file" name="hinh_mon" id="">
                </div>
            </div>

            <input type="submit" value="Lưu / Cập Nhật" name="them_mon">
            <a href="admin.php?pid=9"> Thoát </a>
        </form>
    </div>
    <div class="previous-table">
    </div>
</div>

<?php
    }
    if (isset($_GET["cap_nhat"]))
    {
        $kq_menu = $ctrl_tintuc_detail->chitiet_lay_tintuc($_GET["id"]);

        while($row = $kq_menu->fetch_assoc())
    {

?>

<?php
?>
 <div class = "header-infor">
    <h1> Chi tiết tin tức  </h1>
</div>
<div class = "infor-wrapper">
    <div class = "themmon-wrapper">
    <form action="control/ctrl_tintuc_admin.php?id=<?php echo $row["NewsID"]?>" enctype = "multipart/form-data" method = "post">
    <div class = "themmon-layer1">
        <div class = "themmon-layer2">
            <label for=""> Tiêu đề </label>
            <br>
            <input type="text" name = "ten_mon" value="<?php echo $row["Title"] ?>">
        </div>
    </div>

    <div class = "themmon-layer1">
       
        <div class = "themmon-layer2">
            <label for=""> Trạng thái </label>
            <br>
            <input type="radio" name="trangthai" id="trangthai" value = "1" checked>
            <label for="trangthai" id="label-trang-thai1"> Công khai </label>
            <input type="radio" name="trangthai" id="trangthai" value = "0" <?php $ctrl_tintuc_detail->checked_trang_thai_tintuc($row["Status"]) ?> >
            <label for="trangthai" id="label-trang-thai2"> Ẩn tin </label>
        </div>
    </div>

    <div class = "themmon-layer1">
        <div class = "themmon-layer2">
            <label for=""> Content </label>
            <br>
            <textarea name="mota_mon" id="" rows="10"> <?php echo $row["Content"] ?> </textarea>
        </div>
    </div>

    <div class = "themmon-layer1">
        <div class = "themmon-layer2">
            <label for=""> Hình ảnh </label>
            <br>
            <img src="assets/images_tintuc/<?php echo $row["ImageNews"] ?>" alt="" width="200">
            <br>
        </div>
    </div>

    <div class = "themmon-layer1">    
        <div class = "themmon-layer2">
            <label for=""> Upload ảnh khác tại đây </label>
            <br>
            <input type="file" name="hinh_mon" id="" value = "">
        </div>
    </div>

    <div class = "them-mon-flex">
    <div>
        <input type="submit" value = "Lưu / Cập Nhật" name = "cap_nhat_mon">
        <a href="admin.php?pid=9"> Thoát </a>
    </div>
    <a id = "them-mon-xoa" href="control/ctrl_tintuc_admin.php?xoa_mon&id=<?php echo $row["NewsID"] ?>"> Xóa </a>
    </div>
    
    </form>
    </div>
    <div class = "previous-table">
    </div>
</div>   

<?php
    }
    }
?>