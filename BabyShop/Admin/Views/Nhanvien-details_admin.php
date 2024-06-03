<?php
include_once("control/ctrl_frm_admin.php");
$ctrl_nhanvien_details = new ctrl_frm_admin();

if(isset($_GET["id"])) {
    $id_nv = $_GET["id"];
    $kq_details = $ctrl_nhanvien_details->lay_nhanvien_details($id_nv);

    while($row = $kq_details->fetch_assoc()) {
?>
<div class="header-infor">
    <h1> Nhân viên </h1>
</div>

<div class="Khachhang-edit-section">
    <div class="infor-wrapper">
        <div class="setting-table" id="id_setting-table">
            <span> Thông tin nhân viên - <?php echo $row["Fullname"] ?> </span>
            <a href="admin.php?pid=4"> Quay lại </a>
        </div>
        <form action="control/ctrl_nhanvien_admin.php?id=<?php echo $row["StaffID"] ?>" id="form-khachhang" method="post" enctype="multipart/form-data">
            <div class="Avatar-edit-infor" id="id_Avatar-edit-infor">
                <div class="Avatar-infor">
                    <label for="staffID"> Mã nhân viên </label>
                    <br>
                    <input type="text" name="staffID" id="staffID" value="<?php echo $row["StaffID"] ?>" readonly>
                </div>

                <div class="Avatar-infor">
                    <label for="staffname"> Nickname </label>
                    <br>
                    <input type="text" name="staffname" id="staffname" value="<?php echo $row["Staffname"] ?>">
                </div>

                <div class="Avatar-infor">
                    <label for="fullname"> Họ tên </label>
                    <br>
                    <input type="text" name="fullname" id="fullname" value="<?php echo $row["Fullname"] ?>">
                </div>

                <div class="Avatar-infor">
                    <label for="password"> Mật khẩu </label>
                    <br>
                    <input type="password" name="password" id="password" value="<?php echo $row["Password"] ?>">
                </div>

                <div class="Avatar-infor">
                    <label for="birthday"> Ngày sinh </label>
                    <br>
                    <input type="date" name="birthday" id="birthday" value="<?php echo $row["Birthday"] ?>">
                </div>

                <div class="Avatar-infor">
                    <label for="gender"> Giới tính </label>
                    <br>                                    
                    <select name="gender" id="gender">
                        <option value="0" <?php if ($row["Gender"]== 0) echo "selected" ?> > Nam </option>
                        <option value="1" <?php if ($row["Gender"]== 1) echo "selected" ?> > Nữ </option>
                    </select>
                </div>

                <div class="Avatar-infor">
                    <label for="phone"> Số điện thoại </label>
                    <br>
                    <input type="text" name="phone" id="phone" value="0<?php echo $row["Phone"] ?>">
                </div>

                <div class="Avatar-infor">
                    <label for="address"> Địa chỉ </label>
                    <br>
                    <input type="text" name="address" id="address" value="<?php echo $row["Address"] ?>">
                </div>

                <div class="Avatar-infor">
                    <label for="role"> Vai trò </label>
                    <br>
                    <input type="text" name="role" id="role" value="<?php echo $row["Role"] ?>">
                </div>

                <div class="Avatar-infor" id="id_Avatar-infor">
                    <label for="ImageStaff"> Ảnh đại diện </label>
                    <br>
                    <input type="file" name="ImageStaff" id="ImageStaff" value="" >
                </div>

                <div class="button-nhanvien-details">
                    <input type="submit" value="Cập nhật" name="capnhat">
                    <input style="background-color: var(--red);" type="submit" value="Xóa" name="xoa" class="tick-button-nhanvien-details">
                </div>
            </div>
        </form>
    </div>
</div>

<?php
    }                                       
} else if(isset($_GET["them_nv"])) {
?>
<div class="header-infor">
    <h1> Nhân viên </h1>
</div>

<div class="Khachhang-edit-section">
    <div class="infor-wrapper">
        <div class="setting-table" id="id_setting-table">
            <span> Thêm nhân viên mới </span>
            <a href="admin.php?pid=4"> Quay lại </a>
        </div>
        <form action="control/ctrl_nhanvien_admin.php" id="form-khachhang" method="post" enctype="multipart/form-data">
            <div class="Avatar-edit-infor" id="id_Avatar-edit-infor">
                <!-- Thêm thông tin nhân viên mới -->
                <div class="Avatar-infor">
                    <label for="fullname"> Họ tên </label>
                    <br>
                    <input type="text" name="fullname" id="fullname" value="">
                </div>

                <div class="Avatar-infor">
                    <label for="password"> Mật khẩu </label>
                    <br>
                    <input type="password" name="password" id="password" value="">
                </div>

                <div class="Avatar-infor">
                    <label for="staffname"> Nickname </label>
                    <br>
                    <input type="text" name="staffname" id="staffname" value="">
                </div>

                <div class="Avatar-infor">
                    <label for="birthday"> Ngày sinh </label>
                    <br>
                    <input type="date" name="birthday" id="birthday" value="">
                </div>

                <div class="Avatar-infor">
                    <label for="gender"> Giới tính </label>
                    <br>                                    
                    <select name="gender" id="gender">
                        <option value="0"> Nữ </option>
                        <option value="1"> Nam </option>
                    </select>
                </div>

                <div class="Avatar-infor">
                    <label for="phone"> Số điện thoại </label>
                    <br>
                    <input type="text" name="phone" id="phone" value="">
                </div>

                <div class="Avatar-infor">
                    <label for="address"> Địa chỉ </label>
                    <br>
                    <input type="text" name="address" id="address" value="">
                </div>

                <div class="Avatar-infor">
                    <label for="role"> Vai trò </label>
                    <br>
                    <input type="text" name="role" id="role" value="">
                </div>

                <div class="Avatar-infor" id="id_Avatar-infor">
                    <label for="imagestaff"> Ảnh đại diện </label>
                    <br>
                    <input type="file" name="imagestaff" id="imagestaff" value="" >
                </div>

                <input type="submit" value="Thêm nhân viên" name="them_nv">
            </div>
        </form>
    </div>
</div>
<?php
}
?>
