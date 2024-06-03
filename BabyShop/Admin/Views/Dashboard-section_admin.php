<?php
    include_once("control/ctrl_frm_admin.php");
    $ctrl_dashboard_section = new ctrl_frm_admin();
    $thangHienTai = date("m");
    $kq_top_5_mon = $ctrl_dashboard_section->lay_top_5_mon_trong_thang($thangHienTai);
    $kq_tong_doanh_thu = $ctrl_dashboard_section->lay_tong_doanh_thu_thang($thangHienTai);
    $kq_tong_don_huy = $ctrl_dashboard_section->lay_tong_don_huy($thangHienTai);
    $kq_tong_don_xac_nhan = $ctrl_dashboard_section->lay_tong_don_xac_nhan($thangHienTai);
    $kq_top_5_kh_diem_cao = $ctrl_dashboard_section->top_5_kh_diem_cao_nhat_thang($thangHienTai);

?>

<div class = "Dashboard-section">
    <div class = "thongke-thang">
        <div class = "thongke-card" id = "thongke-card1">
            <div class="background-thongke">
                <img src="assets/images_admin/money.png" alt="" width="70" height="70">
                <?php while($row = $kq_tong_doanh_thu->fetch_assoc())
                    { 
                        $formatted_tien = number_format($row["TongDoanhThu"], 0, ",", ".") . "đ";
                    ?>
                <div>
                    <h1> Tổng doanh thu tháng <?php echo ltrim(date("m"),0);  ?> </h1>
                    <p> <?php echo $formatted_tien ?> </p>
                    <span id ="phantram1"> + 20% (30 Days) </span>
                </div>
                <?php
                    }
                    ?>
            </div>
        </div>

        <div class = "thongke-card" id = "thongke-card2">
            <div class="background-thongke">
            <img src="assets/images_admin/bell.png" alt="" width="80" height="80">
            <?php
                while($row = $kq_tong_don_xac_nhan->fetch_assoc())
                {
            ?>

            <div>
                <h1> Tổng đơn đặt bàn tháng <?php echo ltrim(date("m"),0);  ?> </h1>
                <p> <?php echo $row["Tong"] ?> </p>
                <span id ="phantram2"> + 10% (30 Days) </span>
            </div>

            <?php        
                }
            ?>
            </div>
        </div>

        <div class = "thongke-card" id = "thongke-card3">
            <div class="background-thongke">
            <img src="assets/images_admin/file.png" alt="" width="70" height="70">
            <?php
                while($row = $kq_tong_don_huy->fetch_assoc())
                {

            ?>
            <div>
                <h1> Tổng đơn hủy bàn tháng <?php echo ltrim(date("m"),0);  ?> </h1>
                <p> <?php echo $row["Tong"] ?> </p>
                <span id ="phantram3"> + 5% (30 Days) </span>
            </div>
            <?php
                }
            ?>
            </div>
        </div>    
    </div>

<div class = "Dashboard-infor-container">
<div class = "Dashboard-infor-all">

    <div class="infor-wrapper" id = "tick-Dashboard-section_admin">
        <div class = "thongke-monan-header">
            <h1> Món ăn bán chạy trong tháng </h1>
        </div>

        <?php
            while($row = $kq_top_5_mon->fetch_assoc())
            {   
                if (!isset($i))
                $i = 0;
        ?>
        <div class = "thongke-monan-body">
            <div class = "thongke-monan-body1"> 
                <h2> #<?php echo ++$i; ?> </h2>
                <img src="../assets/img_menu/<?php echo $row["HinhAnh"] ?>" alt="" width="105" height = "65">
                <div class = thongke-monan-body2>
                    <h1> <?php echo $row["TenMonAn"] ?> </h1>
                    <div class = "thongke-monan-body3">
                        <p> <?php echo $row["Gia"] ?> </p>
                        <span> <?php echo $row["TenLoai"] ?> </span>
                    </div>
                </div>
            </div>

            <div id = "tick-thongke-monan-body1">
                <h1> <?php echo $row["TongSoLuong"] ?> </h1>
                <p> + 10% </p>
            </div>
        </div>
        
        <?php
            }
        ?>


        
        </div>

    <div class="infor-wrapper" id = "tick-Dashboard-section_admin1">
        <div class = "thongke-monan-header">
            <h1> Khách hàng có điểm thưởng cao nhất </h1>
        </div>
        <div class = "thongke-body-header">
            <?php
                while($row = $kq_top_5_kh_diem_cao->fetch_assoc())
                {

            ?>
            <a href="admin.php?pid=3&id_khachhang=<?php echo $row["Phone"] ?>" class = "thongke-card-kh-moi-wrapper">
            <div class = "thongke-card-kh-moi">

                <img src="assets/images_avatar/user.png" alt="" width = "64" height = "64">
                <div>
                    <div class ="thongke-card-kh-moi1">
                        <h1> <?php echo $row["HoTen"] ?> </h1>
                        <span> <?php echo $row["TongDiem"] ?> Điểm </span>
                    </div>
                    <p> <?php echo $row["DiaChi"] ?> </p>
                </div>
            </div>
            </a>
            <?php
                }
            ?>
            
            

        </div>
    </div>
    </div>

    <!-- </div>
    <div class="infor-wrapper" id = "tick-Dashboard-section_admin2">
        <div class = "thongke-monan-header">
            <h1> Top 3 Khách hàng có số điểm cao trong tháng </h1>
        </div>
    </div>
    </div> -->
</div>
