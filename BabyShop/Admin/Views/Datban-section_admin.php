<?php
    if (!isset($_GET["page"]))
    {
        $page = 1;
    }
    else $page = intval($_GET["page"]);
    $hienthi = 10;
?>


<?php
    include_once("control/ctrl_frm_admin.php");
    $ctrl_datban_section = new ctrl_frm_admin();
    $kq_soluong = $ctrl_datban_section->hienthi_don_dat_ban_hientai();

    if(isset($_GET["sap_toi"]))
    {
        
        if (isset($_POST["tim_kiem"]))
    {
        $kq_danhsach_1_gio_toi = $ctrl_datban_section->danhsach_don_dat_ban_hientai_tim($_POST["tim_kiem"]);
    }
    else{
        $kq_danhsach_1_gio_toi = $ctrl_datban_section->danhsach_don_dat_ban_hientai();
    }


?>
<div class = "header-infor">
    
                    <h1> <a href="admin.php?pid=5"> Đơn hàng </a>  -> Bàn nhận 1 giờ tới </h1>
                </div>
                <div class = "infor-wrapper">
                    <div class = "setting-table">


                        <div class="search">
                        <form action="admin.php?pid=5&sap_toi" method = "post">
                                <label for="lsearch"> Tìm kiếm </label>
                                <input type="text" id = "lseacrh" name="tim_kiem">
                            </form>
                        </div>
                        
                    </div>

                    <div class = "main-table" id = "tick-datban-section">
                        <table>
                            <thead>
                                <tr>
                                    <th style="width: 10%"> Mã đơn </th>
                                    <th style="width: 20%"> Tên khách hàng </th>
                                    <th style="width: 20%;" class = "arrange"> <a href=""> Thời gian <span>&#8593</span> <span>&#8595</span> </a> </th>
                                    <th style="width: 20%"> Địa chỉ </th>
                                    <th style="width: 20%"> Số điện thoại </th>
                                    <th style="width: 10%"> Trạng thái </th>
                                </tr>
                            </thead>
                            <tbody>
                                
                                <?php
                                    while ($row = $kq_danhsach_1_gio_toi->fetch_assoc())
                                {
                                ?>
                                <tr>
                                    <td><a href="admin.php?pid=55&id_donhang=<?php echo $row["OrderID"]?>" > #<?php echo $row["OrderID"]; ?></a></td>
                                    <td><a href="admin.php?pid=55&id_donhang=<?php echo $row["OrderID"]?>" > <?php echo $row["TenKhachHang"]; ?> </td>
                                    <td><a href="admin.php?pid=55&id_donhang=<?php echo $row["OrderID"]?>" > <?php echo $row["NgayDatCho"]." "; echo $row["ThoiGianNhanBan"]; ?></a></td>
                                    <td><a href="admin.php?pid=55&id_donhang=<?php echo $row["OrderID"]?>" > <?php echo $row["MaBan"]; ?></a></td>
                                    <td><a href="admin.php?pid=55&id_donhang=<?php echo $row["OrderID"]?>" > <?php echo $row["SoNguoi"] ?></a></td>
                                    <td><a href="admin.php?pid=55&id_donhang=<?php echo $row["OrderID"]?>" > 0<?php echo $row["SDTKhachHang"]; ?></a></td>
                                    <td id = "padding-menu4"><a href="admin.php?pid=5&id_donhang=<?php echo $row["MaNhatKy"]?>" <?php $ctrl_datban_section->hien_thi_mau_trang_thai_ban($row["TrangThai"]) ?> > <?php $ctrl_datban_section->hien_thi_trang_thai_ban($row["TrangThai"]) ?></a></td>

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
<?php
    }
    
    else
    {
        ;
        if (isset($_POST["tim_kiem"]))
            {
                $kq = $ctrl_datban_section->danhsach_don_dat_ban_tim($hienthi,$page,$_POST["tim_kiem"]);
            }
            else{
                $kq = $ctrl_datban_section->danhsach_don_dat_ban($hienthi,$page);
            }

?>
<div class = "header-infor">
                    <h1> <a href="admin.php?pid=5"> Đơn hàng </a> </h1>
                </div>
                <div class = "infor-wrapper">
                    <div class = "setting-table">
                        

                        <div class="search">
                        <form action="admin.php?pid=5" method = "post">
                                <label for="lsearch"> Tìm kiếm </label>
                                <input type="text" id = "lseacrh" name="tim_kiem">
                            </form>
                        </div>
                        
                    </div>
                    <div class = "main-table" id = "tick-datban-section">
                        <table>
                            <thead>
                                <tr>
                                <th style="width: 10%"> Mã đơn </th>
                                    <th style="width: 20%"> Tên khách hàng </th>
                                    <th style="width: 20%;" class = "arrange"> <a href=""> Thời gian <span>&#8593</span> <span>&#8595</span> </a> </th>
                                    <th style="width: 20%"> Địa chỉ </th>
                                    <th style="width: 20%"> Số điện thoại </th>
                                    <th style="width: 10%"> Trạng thái </th>
                                </tr>
                            </thead>
                            <tbody>
                                
                                <?php
                                    while ($row = $kq->fetch_assoc())
                                {
                                ?>
                                <tr>
                                    <td><a href="admin.php?pid=55&id_donhang=<?php echo $row["OrderID"]?>" > #<?php echo $row["OrderID"]; ?></a></td>
                                    <td><a href="admin.php?pid=55&id_donhang=<?php echo $row["OrderID"]?>" > <?php echo $row["Username"]; ?> </td>
                                    <td><a href="admin.php?pid=55&id_donhang=<?php echo $row["OrderID"]?>" > <?php echo $row["OrderDate"]." "; echo $row["TimeOrder"]; ?></a></td>
                                    <td><a href="admin.php?pid=55&id_donhang=<?php echo $row["OrderID"]?>" > <?php echo $row["Address"]; ?></a></td>
                                    <td><a href="admin.php?pid=55&id_donhang=<?php echo $row["OrderID"]?>" > 0<?php echo $row["Phone"]; ?></a></td>
                                    <td id = "padding-menu4"><a href="admin.php?pid=5&id_donhang=<?php echo $row["OrderID"]?>" <?php $ctrl_datban_section->hien_thi_mau_trang_thai_ban($row["Status"]) ?> > <?php $ctrl_datban_section->hien_thi_trang_thai_ban($row["Status"]) ?></a></td>

                                </tr>
                                
                                <?php
                                }
                                ?>
                            </tbody>
                        </table>
                    </div>
                    <div class = "previous-table">
                        <div class = phan-trang-wrapper>
                            <?php
                                $ctrl_datban_section->phantrang_datban($hienthi, $page);
                            ?>
                        </div>
                    </div>
                </div>
<?php
    }
?>