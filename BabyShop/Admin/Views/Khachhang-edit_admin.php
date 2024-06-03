<?php
    include_once("control/ctrl_frm_admin.php");
    $ctrl_editkh_edit = new ctrl_frm_admin();
    $id_kh = $_GET["id_khachhang"];
    $kq_edit = $ctrl_editkh_edit->chinhsua_khachhang($id_kh);
?>

                <?php 
                    while($row = $kq_edit->fetch_assoc())
                    {
                ?>
                <div class = "header-infor">
                    <h1> Khách hàng </h1>
                </div>

                <div class = "Khachhang-edit-section">
                    <div class = "infor-wrapper">
                        <div class = "setting-table" id = "id_setting-table">
                            <span> Chỉnh sửa - <?php echo $row["HoTen"] ?> </span>
                            <a href="admin.php?pid=3&id_khachhang=<?php echo $row["Phone"] ?>"> Quay lại </a>
                        </div>
                        <form action="control/ctrl_chinhsua_admin.php" id = "form-khachhang" method="post" enctype="multipart/form-data">

                        <div class = "Avatar-edit-infor" id = "id_Avatar-edit-infor">
                                <div class = "Avatar-infor">
                                    <label for="name"> Họ tên </label>
                                    <br>
                                    <input type="text" name="hoten" id="name" value="<?php echo $row["HoTen"]?>">
                                </div>

                                <div class = "Avatar-infor">
                                    <label for="name"> Điểm </label>
                                    <br>
                                    <input type="text" name="diem" id="name" value="<?php echo $row["Diem"]?>">
                                </div>

                                <div class = "Avatar-infor">
                                    <label for="name"> Số điện thoại </label>
                                    <br>
                                    <input type="text" name="sdt" id="name" value="0<?php echo $row["Phone"]?>">
                                </div>
                                
                                <div class = "Avatar-infor">
                                    <label for="name"> Giới tính </label>
                                    <br>                                    
                                    <select name="gioitinh" id="name">
                                        <option value="0" <?php if ($row["GioiTinh"]== 0) echo "selected" ?> > Nam </option>
                                        <option value="1" <?php if ($row["GioiTinh"]== 1) echo "selected" ?> > Nữ </option>
                                    </select>
                                </div>

                                <div class = "Avatar-infor">
                                    <label for="name"> Địa chỉ </label>
                                    <br>
                                    <input type="text" name="diachi" id="name" value="<?php echo $row["DiaChi"]?>">
                                </div>

                                <div class = "Avatar-infor" id = "id_Avatar-infor">
                                    <label for="name"> Ảnh đại diện </label>
                                    <br>
                                    <input type="file" name="HinhKH" id="HinhKH" value="asdsa" >
                                </div>

                                <input type="submit" value="Cập nhật">
                            </div>
                            </form>
                    </div>
                    
                </div>

                <?php
                    }
                ?>