<?php
    include_once("control/ctrl_frm_admin.php");
    $ctrl_khachang_section = new ctrl_frm_admin();
    if (isset($_POST["tim_kiem"]))
    {
        $kq = $ctrl_khachang_section->lay_dtkh_tim($_POST["tim_kiem"]);
    }
    else{
        $kq = $ctrl_khachang_section->lay_dtkh();
    }
?>
        <div class = "header-infor">
                    <h1> Khách hàng </h1>
                </div>

                <div class = "infor-wrapper">
                    <div class = "setting-table">
                        <div class = "add-user">
                            <a href=""> Thêm tài khoản </a>
                        </div>

                        <div class="search">
                            <form action="admin.php?pid=3" method="post">
                                <label for="lsearch"> Tìm kiếm </label>
                                <input type="text" id = "lseacrh" name="tim_kiem">
                                <!-- <input type="submit" value = "Send"> -->
                            </form>
                        </div>
        
                    </div>
                    <div class = "main-table">
                        <table>
                            <thead>
                                <tr>
                                    <th style="width: 25%"> Họ tên </th>
                                    <th style="width: 15%"> Số điện thoại </th>
                                    <th style="width: 10%"> Giới tính </th>
                                    <th style="width: 15%;" class = "arrange"> <a href=""> Điểm tích lũy <span>&#8593</span> <span>&#8595</span> </a> </th>
                                    <th style="width: 35%"> Địa chỉ </th>
                                </tr>
                            </thead>
                            <tbody>
                                
                                <?php
                                    while ($row = $kq->fetch_assoc())
                                {
                                ?>
                                <tr>
                                    <td><a href="admin.php?pid=3&id_khachhang=<?php echo $row["Phone"]?>" > <?php echo $row["HoTen"]; ?></a></td>
                                    <td><a href="admin.php?pid=3&id_khachhang=<?php echo $row["Phone"]?>" >0<?php echo $row["Phone"]; ?></a></td>
                                    <td><a href="admin.php?pid=3&id_khachhang=<?php echo $row["Phone"]?>" >
                                            <?php 
                                        if ($row["GioiTinh"] == 1)
                                        {
                                            echo "Nữ";
                                        } else 
                                        {
                                            echo "Nam";
                                        }
                                        ?> 
                                        </a>
                                   </td>
                                    <td><a href="admin.php?pid=3&id_khachhang=<?php echo $row["Phone"]?>" ><?php echo $row["Diem"]; ?></a></td>
                                    <td><a href="admin.php?pid=3&id_khachhang=<?php echo $row["Phone"]?>" ><?php echo $row["DiaChi"] ?></a></td>
                                </tr>
                                
                                <?php
                                }
                                ?>
                            </tbody>
                        </table>
                    </div>
                    <div class = "previous-table">
                    </div>
                </div>