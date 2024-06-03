<?php
    if (!isset($_GET["page"]))
    {
        $page = 1;
    }
    else $page = intval($_GET["page"]);
    $hienthi = 15;
?>
<?php
    include_once("control/ctrl_frm_admin.php");
    $ctrl_hoadon_admin = new ctrl_frm_admin();
    $kq = $ctrl_hoadon_admin->lay_hoa_don($hienthi,$page);
    if(isset($_GET["thongke"]))
    {  
        
        if(!isset($_POST["date1"]) && !isset($_POST["date1"]))
        {
?>
<div class = "header-infor">
                    <h1> Hóa đơn - Thống kê</h1>
                </div>

                <div class = "infor-wrapper">
                    <div class = "setting-table">
                        <div class = "add-user">
                            <a href="admin.php?pid=6&thongke"> Thống kê </a>
                            <a href="admin.php?pid=6" style = "background-color: var(--colormain)"> Trở về </a>

                        </div>

                        <div class="search">
                            <!-- <form action="admin.php?pid=11" method="post">
                                <label for="lsearch"> Tìm kiếm </label>
                                <input type="text" id = "lseacrh" name="input_search">
                            </form> -->
                        </div>
                    </div>
                    <form action="admin.php?pid=6&thongke" method = "post">
                    <div class ="thongke-section">
                        <div>
                            <div class = "thongke-thoigian">
                                    <p> Từ ngày </p>
                                    <input type="date" name = "date1">
                                    <p> đến ngày </p>
                                    <input type="date" name = "date2">
                            </div>
                            <input type="submit" value = "Truy xuất">
                        </div>
                        <div class ="body-thongke">
                            <h1> Tổng doanh thu đạt được: </h1>
                        </div>
                        
                    </div>
                    </form>

                    <div class = "main-table" id = "tick-hoadon_admin" >
                        <table>
                            <thead>
                                <tr>
                                    <th  style="width: 10%; text-align: center;"> Mã hóa đơn </th>
                                    <th style="width: 15%; text-align: center;"> Ngày lập đơn </th>
                                    <th style="width: 10%; text-align: center;"> Số điện thoại </th>
                                    <th style="width: 15%; text-align: center;" class = "arrange"> <a href=""> Tổng tiền <span>&#8593</span> <span>&#8595</span> </a> </th>
                                </tr>
                            </thead>
                            <tbody >     
                                <tr >
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class = "previous-table">
                    </div>
                </div>
<?php 
        } else {
            $date1 = $_POST["date1"];
            $date2 = $_POST["date2"];
            $kq_hoa_don_thong_ke = $ctrl_hoadon_admin->lay_hoa_don_thong_ke($date1, $date2);
            $kq_hoa_don_thong_ke_tongtien = $ctrl_hoadon_admin->lay_hoa_don_thong_ke_tongtien($date1, $date2);
?>

<div class = "header-infor">
                    <h1> Hóa đơn - Thống kê</h1>
                </div>

                <div class = "infor-wrapper">
                    <div class = "setting-table">
                        <div class = "add-user">
                            <a href="admin.php?pid=6&thongke"> Thống kê </a>
                            <a href="admin.php?pid=6" style = "background-color: var(--colormain)"> Trở về </a>
                        </div>

                        <div class="search">
                            <form action="admin.php?pid=11" method="post">
                                <label for="lsearch"> Tìm kiếm </label>
                                <input type="text" id = "lseacrh" name="input_search">
                                <!-- <input type="submit" value = "Send"> -->
                            </form>
                        </div>
                    </div>
                    <form action="admin.php?pid=6&thongke" method="post">
                    <div class ="thongke-section">
                        <div>
                            <div class = "thongke-thoigian">
                                    <p> Từ ngày </p>
                                    <input type="date" name = "date1">
                                    <p> đến ngày </p>
                                    <input type="date" name = "date2">
                            </div>
                            <input type="submit" value = "Truy xuất">
                        </div>
                        <div class ="body-thongke">
                            
                            <?php
                                while ($row = $kq_hoa_don_thong_ke_tongtien->fetch_assoc())
                                {
                            ?>
                            <h1> Tổng doanh thu đạt được: <span> <?php echo $row["Tong"] ?> </span>  </h1>
                            <?php        
                                }
                            ?>
                            
                        </div>
                        
                    </div>
                    </form>

                    <div class = "main-table" id = "tick-hoadon_admin" >
                        <table>
                            <thead>
                                <tr>
                                    <th  style="width: 10%; text-align: center;"> Mã hóa đơn </th>
                                    <th style="width: 15%; text-align: center;"> Ngày lập đơn </th>
                                    <th style="width: 10%; text-align: center;"> Số điện thoại </th>
                                    <th style="width: 15%; text-align: center;" class = "arrange"> <a href=""> Tổng tiền <span>&#8593</span> <span>&#8595</span> </a> </th>
                                </tr>
                            </thead>
                            <tbody >
                                
                                <?php
                                    while ($row = $kq_hoa_don_thong_ke->fetch_assoc())
                                {
                                ?>
                                <tr >
                                    <td style = "text-align: center;"><a href="admin.php?pid=66&id=<?php echo $row["MaHoaDon"]?>" > <?php echo $row["MaHoaDon"]; ?></a></td>
                                    <td style = "text-align: center;"><a href="admin.php?pid=66&id=<?php echo $row["MaHoaDon"]?>" ><?php echo $row["NgayLapDon"]; ?></a></td>
                                    <td style = "text-align: center;"><a href="admin.php?pid=66&id=<?php echo $row["MaHoaDon"]?>" ><?php if($row["Phone"] == null){echo "Không";} else echo "0".$row["Phone"]; ?></a></td>
                                    <td style = "text-align: center;"><a href="admin.php?pid=66&id=<?php echo $row["MaHoaDon"]?>" ><?php echo $row["Tong"]; ?></a></td>

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
?>
<?php
    }
    else {
?>

        <div class = "header-infor">
                    <h1> Hóa đơn </h1>
                </div>

                <div class = "infor-wrapper">
                    <div class = "setting-table">
                        <div class = "add-user">
                            <a href="admin.php?pid=6&thongke"> Thống kê </a>
                        </div>

                        <div class="search">
                            <!-- <form action="admin.php?pid=11" method="post">
                                <label for="lsearch"> Tìm kiếm </label>
                                <input type="text" id = "lseacrh" name="input_search">
                            </form> -->
                        </div>
        
                    </div>
                    <div class = "main-table" >
                        <table>
                            <thead>
                                <tr>
                                    <th  style="width: 10%; text-align: center;"> Mã hóa đơn </th>
                                    <th style="width: 15%; text-align: center;"> Ngày lập đơn </th>
                                    <th style="width: 10%; text-align: center;"> Số điện thoại </th>
                                    <th style="width: 15%; text-align: center;" class = "arrange"> <a href=""> Tổng tiền <span>&#8593</span> <span>&#8595</span> </a> </th>
                                </tr>
                            </thead>
                            <tbody >
                                
                                <?php
                                    while ($row = $kq->fetch_assoc())
                                {
                                ?>
                                <tr >
                                    <td style = "text-align: center;"><a href="admin.php?pid=66&id=<?php echo $row["MaHoaDon"]?>" > <?php echo $row["MaHoaDon"]; ?></a></td>
                                    <td style = "text-align: center;"><a href="admin.php?pid=66&id=<?php echo $row["MaHoaDon"]?>" ><?php echo $row["NgayLapDon"]; ?></a></td>
                                    <td style = "text-align: center;"><a href="admin.php?pid=66&id=<?php echo $row["MaHoaDon"]?>" ><?php if($row["Phone"] == null){echo "Không";} else echo "0".$row["Phone"]; ?></a></td>
                                    <td style = "text-align: center;"><a href="admin.php?pid=66&id=<?php echo $row["MaHoaDon"]?>" ><?php echo $row["TongTien"]; ?></a></td>

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
                                $ctrl_hoadon_admin->phantrang_hoadon($hienthi, $page);
                            ?>
                        </div>
                    </div>
                </div>
<?php        
    }
?>