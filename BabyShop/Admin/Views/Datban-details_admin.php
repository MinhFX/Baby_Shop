<?php
    include_once("control/ctrl_frm_admin.php");
    $ctrl_datban_details = new ctrl_frm_admin();
    $id = $_GET["id_donhang"];
    $kq_hoa_don = $ctrl_datban_details->lay_hoa_don_theo_id($id);
    $kq_nhat_ky = $ctrl_datban_details->lay_nhat_ky_theo_id($id);
    $mau = "";
    $mau1 = "";
    $mau2 = "";
    $mau3 = "";
    $mau4 = "";
    $i = 0;
?>
<div class = "datban-details-section">
<div class = "header-infor">
                    <h1> Đơn hàng </h1>
                </div>
        <?php
            while($row = $kq_nhat_ky->fetch_assoc())
            {
        ?>
    <form action="control/ctrl_datban_admin.php?id=<?php echo $row["OrderID"] ?>" method="post">
    <div class ="infor-wrapper" id = "tick-datban-details-section">
        <div class = "datban-details-1">
            <div class = "datban-details-1-layer1">
                <img src="assets/images_avatar/user.png" alt="" width="64" height="64">
                <div class = "datban-details-1-1">
                    <h1> <?php echo $row["Fullname"]; ?> </h1>
                    <span> Khách nhận </span>
                </div>
            </div>
           
            <div class = "radio-trangthai-don">
                <div>
                    <h1> <span> Mã đơn #<?php echo $row["OrderID"] ?> </span>  </h1>
                </div>
                <div>
                    <?php
                        if ($row["Status"] == 0)
                        {
                            $mau = "checked";
                            $mau1 = "";
                            $mau2 = "";
                            $mau3 = "";
                            $mau4 = "";
                        }
                        else if ($row["Status"] == 1)
                        {
                            $mau = "";
                            $mau1 = "checked";
                            $mau2 = "";
                            $mau3 = "";
                            $mau4 = "";
                        }
                        else if ($row["Status"] == 2)
                        {
                            $mau = "";
                            $mau1 = "";
                            $mau2 = "checked";
                            $mau3 = "";
                            $mau4 = "";
                        }
                        else if($row["Status"] == 3)
                        {
                            $mau = "";
                            $mau1 = "";
                            $mau2 = "";
                            $mau3 = "checked";
                            $mau4 = "";
                        }
                        else if($row["Status"] == 4)
                        {
                            $mau = "";
                            $mau1 = "";
                            $mau2 = "";
                            $mau3 = "";
                            $mau4 = "checked";
                        }              
                    ?>
                    <input type="radio" value="1" id = "radio1-trangthai-don" name = "trangthai" <?php echo $mau1; ?> >
                    <label for="radio1-trangthai-don" id = "trangthai1-don"> Đơn mới </label>

                    <input type="radio" value="2" id = "radio1-trangthai-don" name = "trangthai" <?php echo $mau2; ?> >
                    <label for="radio1-trangthai-don" id = "trangthai4-don"> Đã duyệt </label>

                    <input type="radio" value="3" id = "radio2-trangthai-don" name = "trangthai" <?php echo $mau3; ?> >
                    <label for="radio2-trangthai-don" id = "trangthai2-don"> Đang giao </label>
                    
                    <input type="radio" value="4" id = "radio3-trangthai-don" name = "trangthai" <?php echo $mau4; ?> >
                    <label for="radio3-trangthai-don" id = "trangthai3-don"> Hoàn thành </label>

                    <input type="radio" value="0" id = "radio-trangthai-don" name = "trangthai" <?php echo $mau; ?> >
                    <label for="radio-trangthai-don" id = "trangthai-don"> Hủy bỏ </label>
                </div>
               
            </div>
        </div>

        <div class = "datban-details-2">
            <div>
                <img src="assets/images_admin/phone.png" alt="" width = "22" height = "22">
                <h1> 0<?php echo $row["Phone"] ?> </h1>
            </div>
            
            <div>
                <img src="assets/images_admin/clock.png" alt="" width = "22" height = "22">
                <h1  id = "datban-details-2-div-2"> <?php echo $row["OrderDate"] ?> <span> <?php echo $row["TimeOrder"] ?> </span> </h1>
            </div>
            
        </div>

        <div class = "datban-details-3">
            
        </div>

        <div class = "datban-details-4">
           
        </div>

    </div>
    <?php        
            }
        ?>
    <div class ="infor-wrapper" id = "tick2-datban-details-section">
        <div class = overflow-datban-details>
        <table>
            <thead>
                <tr>
                    <th style="width: 10%"> Hình ảnh </th>
                    <th style="width: 25%"> Loại sản phẩm </th>
                    <th style="width: 25%"> Sản phẩm </th>
                    <th > Đơn giá </th>
                    <th > Số lượng </th>
                    <th > Tổng </th>
                    <!-- <th > Thao tác  </th> -->
                </tr>
            </thead>
            <tbody>
                <?php
                    while($row = $kq_hoa_don->fetch_assoc())
                    {
                        $i++;
                ?>
                <tr>
                    <td style="width: 10%"> <img src="../ImageProduct/<?php echo $row["ImageProduct"] ?>" alt="" width="80" height="80">  </td>
                    <td> <?php echo $row["Category_name"] ?> </td>
                    <td> <?php echo $row["Productname"] ?> </td>
                    <td> <?php echo $row["Price"] ?> </td>
                    <td> <?php echo $row["TongSoLuong"] ?> </td>
                    <td> <?php echo $row["Price"] * $row["TongSoLuong"] ?> </td>
                    <input type="hidden" name="data[<?php echo $i ?>][ProductID]" value="<?php echo $row["ProductID"] ?>">
                    <input type="hidden" name="data[<?php echo $i ?>][TongSoLuong]" value="<?php echo $row["TongSoLuong"] ?>">
                    <input type="hidden" name="data[<?php echo $i ?>][TongSoTien]" value="<?php echo $row["Price"] * $row["TongSoLuong"] ?>">
                </tr>
                <?php                      
                    }                
                ?>

                    
            </tbody>
        </table>
        </div>

        <div class = "tick2-datban-details-section-footer">
                
        </div>
    </div>

    <div id = "tick3-datban-details-section">
        <input type="submit" value="Xác nhận" name = "cap_nhat">
        <a href="admin.php?pid=5"> Quay lại </a>
    </div>

    

    </form>


</div>