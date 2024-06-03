<?php
    include_once("control/ctrl_frm_admin.php");
    $ctrl_hoadon_details = new ctrl_frm_admin();
    $id = $_GET["id"];
    $kq = $ctrl_hoadon_details->lay_chi_tiet_hoa_don($id);
    $kq_1 = $ctrl_hoadon_details->lay_thong_tin_hoa_don($id);
?>

<div class = "header-infor">
                    <h1> Hóa đơn - Chi tiết</h1>
                </div>

<div class = "infor-wrapper">
    <div class = "setting-table">
        <div class = "add-user">
            <a href="admin.php?pid=6" style = "background-color: var(--colormain)"> Trở về </a>
        </div>
    </div>

    <div class = "hoadon-details-header">
        <?php
            while($row = $kq_1->fetch_assoc())
            {
        ?>
            <p> Mã hóa đơn: #<?php echo $row["MaHoaDon"] ?> </p>
            <p> Số điện thoại: 0<?php echo $row["Phone"] ?> </p>
            <p> Ngày lập đơn: <?php echo $row["NgayLapDon"] ?> </p>
        <?php        
            }
        ?>

    </div>

    <div class = "main-table" id = "tick-hoadon-details_admin" >
    <table>
    <thead>
        <tr>
            <th  style="width: 10%; text-align: center;"> Mã món ăn </th>
            <th style="width: 15%; text-align: center;"> Loại món </th>
            <th style="width: 10%; text-align: center;"> Số lượng </th>
            <th style="width: 10%; text-align: center;"> Đơn giá </th>
            <th style="width: 10%; text-align: center;"> Tổng </th>
        </tr>
    </thead>
    <tbody >
        
        <?php
            while ($row = $kq->fetch_assoc())
        {
        ?>
        <tr >
            <td style = "text-align: center;"><a href="" > <?php echo $row["MaHoaDon"]; ?></a></td>
            <td style = "text-align: center;"><a href="" ><?php echo $row["TenLoai"]; ?></a></td>
            <td style = "text-align: center;"><a href="" ><?php echo $row["SoLuongMon"]; ?></a></td>

            <td style = "text-align: center;"><a href="" ><?php echo $row["DonGia"]; ?></a>
            <td style = "text-align: center;"><a href="" ><?php echo $row["Tong"]; ?></a>

        </tr>
        
        <?php
        }
        ?>
    </tbody>
</table>    
    </div>
    

</div>