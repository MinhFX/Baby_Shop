<?php
    include_once("control/ctrl_frm_admin.php");
    $ctrl_themmon_section = new ctrl_frm_admin();
    $kq_loai = $ctrl_themmon_section->lay_danhsachloai();

    if (isset($_GET["them_mon"]))
    {
?>

<div class = "header-infor">
    <h1> Thêm món </h1>
</div>
<div class = "infor-wrapper">
    <div class = "themmon-wrapper">
    <form action="control/ctrl_monan_admin.php" enctype = "multipart/form-data" method = "post">
    <div class = "themmon-layer1">
        <div class = "themmon-layer2">
            <label for=""> Tên món ăn </label>
            <br>
            <input type="text" name = "ten_mon" placeholder="Nhập tên món..." required>
        </div>
        <div class = "themmon-layer2">
            <label for=""> Thể loại </label>
            <br>
            <select name="loai_mon" id="" required>
                <?php
                    while($row = $kq_loai->fetch_assoc())
                    {
                ?>
                <option value="<?php echo $row["MaLoai"]; ?>"> <?php echo $row["TenLoai"]; ?> </option>
                <?php
                    }
                ?>
               
            </select>
        </div>
    </div>

    <div class = "themmon-layer1">
        <div class = "themmon-layer2">
            <label for=""> Giá </label>
            <br>
            <input type="number" name = "gia_mon" placeholder="Nhập giá..." required>
        </div>
        <div class = "themmon-layer2">
            <label for=""> Trạng thái </label>
            <br>
            <input type="radio" name="trangthai" id="trangthai" value = "1" required>
            <label for="trangthai" id="label-trang-thai1"> Mở bán </label>
            <input type="radio" name="trangthai" id="trangthai" value = "0" required>
            <label for="trangthai" id="label-trang-thai2"> Ẩn món </label>
        </div>
    </div>

    <div class = "themmon-layer1">
        <div class = "themmon-layer2">
            <label for=""> Mô tả món ăn </label>
            <br>
            <textarea name="mota_mon" id="" rows="10" placeholder = "Nhập mô tả món..."></textarea>
            <!-- <input type="text" name="" id="" > -->
        </div>
    </div>

    <div class = "themmon-layer1">
        <div class = "themmon-layer2">
            <label for=""> Thêm hình ảnh </label>
            <br>
            <input type="file" name="hinh_mon" id="">
        </div>
    </div>

    <input type="submit" value = "Lưu / Cập Nhật" name = "them_mon">
    <a href="admin.php?pid=2"> Thoát </a>
    </form>
    </div>
    <div class = "previous-table">
    </div>
</div>
<?php
    }
    if (isset($_GET["cap_nhat"]))
    {
        $kq_menu = $ctrl_themmon_section->chinh_sua_mon_an($_GET["id"]);

        while($row = $kq_menu->fetch_assoc())
    {

?>

<?php
?>
 <div class = "header-infor">
    <h1> Chi tiết món  </h1>
</div>
<div class = "infor-wrapper">
    <div class = "themmon-wrapper">
    <form action="control/ctrl_monan_admin.php?id=<?php echo $row["MaMonAn"]?>" enctype = "multipart/form-data" method = "post">
    <div class = "themmon-layer1">
        <div class = "themmon-layer2">
            <label for=""> Tên món ăn </label>
            <br>
            <input type="text" name = "ten_mon" value="<?php echo $row["TenMonAn"] ?>">
        </div>
        <div class = "themmon-layer2">
            <label for=""> Thể loại </label>
            <br>
            <select name="loai_mon" id="">
                <?php
                    while($row1 = $kq_loai->fetch_assoc())
                    {
                ?>
                <option value="<?php echo $row1["MaLoai"]; ?>"> <?php echo $row1["TenLoai"]; ?> </option>
                <?php
                    }
                ?>
               
            </select>
        </div>
    </div>

    <div class = "themmon-layer1">
        <div class = "themmon-layer2">
            <label for=""> Giá </label>
            <br>
            <input type="number" name = "gia_mon" value="<?php echo $row["Gia"] ?>">
        </div>
        <div class = "themmon-layer2">
            <label for=""> Trạng thái </label>
            <br>
            <input type="radio" name="trangthai" id="trangthai" value = "1" checked>
            <label for="trangthai" id="label-trang-thai1"> Công khai </label>
            <input type="radio" name="trangthai" id="trangthai" value = "0" <?php $ctrl_themmon_section->checked_trang_thai_mon($row["TrangThaiMenu"]) ?> >
            <label for="trangthai" id="label-trang-thai2"> Ẩn món </label>
        </div>
    </div>

    <div class = "themmon-layer1">
        <div class = "themmon-layer2">
            <label for=""> Mô tả món ăn </label>
            <br>
            <textarea name="mota_mon" id="" rows="10"> <?php echo $row["MoTa"] ?> </textarea>
        </div>
    </div>

    <div class = "themmon-layer1">
        <div class = "themmon-layer2">
            <label for=""> Hình ảnh món </label>
            <br>
            <img src="../assets/img_menu/<?php echo $row["HinhAnh"] ?>" alt="" width="200">
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
        <a href="admin.php?pid=2"> Thoát </a>
    </div>
    <a id = "them-mon-xoa" href="control/ctrl_monan_admin.php?xoa_mon&id=<?php echo $row["MaMonAn"] ?>"> Xóa </a>
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