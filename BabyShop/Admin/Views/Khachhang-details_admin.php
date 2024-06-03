<?php
    include_once("control/ctrl_frm_admin.php");
    $ctrl_editkh = new ctrl_frm_admin();
    $id_kh = $_GET["id_khachhang"];
    $kq_edit = $ctrl_editkh->chinhsua_khachhang($id_kh);
?>
                

                <?php
                    while($row = $kq_edit->fetch_assoc())
                    {
                ?>
                    <div class = "header-infor">
                    <h1> <?php echo $row["HoTen"]?> </h1>
                </div>

                <div class = "Avatar-all">
                    <div class = Avatar-wrapper>
                        <div class = "Avatar-user">
                                <img src="assets/images_admin/profile-user.png" alt="" width= "130">
                                <h1> VIP++ </h1>
                                <p> <b> Ngày tham gia</b>  <br> <span> 10/20/2020 </span> </p>                            
                        </div>
                        <div class = "Avatar-edit">
                            <div class = Avatar-edit-header>
                                <a href="" id = "user-details"> Chi tiết </a>
                                <a href="admin.php?pid=31&id_khachhang=<?php echo $row["Phone"]?>"> Chỉnh sửa </a>
                                <a href="admin.php?pid=3"> Quay lại </a>
                                <a href="control/ctrl_chinhsua_admin.php?id_khachhang=<?php echo $row["Phone"]?>" id = "user-delete"> Xóa </a>
                            </div>
                            <div class = "Avatar-edit-infor">
                                <div class = "Avatar-infor">
                                    <label for="name"> Họ tên </label>
                                    <br>
                                    <input type="text" name="input_" id="name" readonly value="<?php echo $row["HoTen"]?>">
                                </div>

                                <div class = "Avatar-infor">
                                    <label for="name"> Điểm </label>
                                    <br>
                                    <input type="text" name="input_" id="name" readonly value="<?php echo $row["Diem"]?>">
                                </div>

                                <div class = "Avatar-infor">
                                    <label for="name"> Số điện thoại </label>
                                    <br>
                                    <input type="text" name="input_" id="name" readonly value="0<?php echo $row["Phone"]?>">
                                </div>
                                <!-- <div class = "Avatar-infor">
                                    <label for="name"> Password </label>
                                    <br>
                                    <input type="text" name="input_" id="name" readonly value="<?php echo $row["Pass"]?>">
                                </div> -->

                                <div class = "Avatar-infor">
                                    <label for="name"> Giới tính </label>
                                    <br>
                                    <input type="text" name="input_" id="name" readonly value="<?php
                                    if($row["GioiTinh"] == "1")
                                        echo "Nữ";
                                        else 
                                        echo "Nam";
                                    ?>">
                                </div>

                                <div class = "Avatar-infor">
                                    <label for="name"> Địa chỉ </label>
                                    <br>
                                    <input type="text" name="diachi" id="name" readonly value="<?php echo $row["DiaChi"]?>">
                                </div>
                            </div>
                        </div>
                        </div>
                </div>
                <?php
                    }
                ?>
                
