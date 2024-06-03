<div class = "header-infor">
                    <h1> Thực đơn </h1>
                </div>
        <div class = "infor-wrapper">
                    <div class = "setting-table">
                    <div class = "add-user">
                            <a href="admin.php?pid=22&them_mon"> Thêm thực đơn </a>
                            <a style="background-color: #42cfb7;" href="admin.php?pid=222"> Danh mục </a>
                    </div>
                    <div class="search">
                            <form action="admin.php?pid=2&search" method = "post">
                                <label for="lsearch"> Tìm kiếm </label>
                                <input type="text" id = "lseacrh" name="tim_kiem">
                            </form>
                        </div>
                    </div>
                    <div class = "main-table" id = "main-table-thucdon-section">
                        <table>
                            <thead>
                                <tr id = "center-menu">
                                    <th style="width: 10%;"> Mã món </th>
                                    <th style="width: 20%;"> Tên món </th>
                                    <th style="width: 15%;"> Giá </th>
                                    <th style="width: 10%;"> Loại </th>
                                    <th style="width: 25%;"> Hình ảnh </th>
                                    <th style="width: 10%;"> Trạng thái </th>
                                </tr>
                            </thead>
                            <tbody>
                                <?php
                                    include_once("control/ctrl_frm_admin.php");
                                    $ctrl_laydtmenu = new ctrl_frm_admin();
                                    if(isset($_GET["theloai"]))
                                    {
                                        if(isset($_POST["tim_kiem"]))
                                        {
                                            $kq_theloai_id = $ctrl_laydtmenu->lay_the_loai_theo_id_tim($_GET["theloai"],$_POST["timkiem"]);
                                        }
                                        else
                                        {
                                            $kq_theloai_id = $ctrl_laydtmenu->lay_the_loai_theo_id($_GET["theloai"]);
                                        }
                                        while($row = $kq_theloai_id->fetch_assoc())
                                        {
                                ?>
                                <tr id = "center-menu">
                                    <td id = "padding-menu1"> <a href="admin.php?pid=22&cap_nhat&id=<?php echo $row["MaMonAn"] ?>"><?php echo $row["MaMonAn"]; ?></a></td>
                                    <td id = "padding-menu1"> <a href="admin.php?pid=22&cap_nhat&id=<?php echo $row["MaMonAn"] ?>"><?php echo $row["TenMonAn"]; ?></a> </td>
                                    <td id = "padding-menu1"> <a href="admin.php?pid=22&cap_nhat&id=<?php echo $row["MaMonAn"] ?>"><?php echo $row["Gia"]; ?></a> </td>
                                    <td id = "padding-menu1"> <a href="admin.php?pid=22&cap_nhat&id=<?php echo $row["MaMonAn"] ?>"><?php echo $row["TenLoai"]; ?></a> </td>
                                    <td id = "padding-menu2"> <a href="admin.php?pid=22&cap_nhat&id=<?php echo $row["MaMonAn"] ?>"><img src="../assets/img_menu/<?php echo $row["HinhAnh"]?>" alt="" width="120" height="70px"></a> </td>
                                    <td id = "padding-menu3"> <a href="admin.php?pid=22&cap_nhat&id=<?php echo $row["MaMonAn"] ?>" <?php $ctrl_laydtmenu->hien_thi_mau_trang_thai_mon($row["TrangThaiMenu"]) ?>> <?php $ctrl_laydtmenu->hien_thi_trang_thai_mon($row["TrangThaiMenu"]); ?> </a></td>
                                </tr>
                                <?php            
                                        }
                                    } else{

                                        if(isset($_POST["tim_kiem"]))
                                        {
                                            $kq = $ctrl_laydtmenu->lay_dtmenu_tim($_POST["tim_kiem"]);
                                        }
                                        else
                                        {
                                            $kq = $ctrl_laydtmenu->lay_dtmenu();
                                        }

                                        while ($row = $kq->fetch_assoc())
                                        {
                                ?>
                                <tr id = "center-menu">
                                    <td id = "padding-menu1"> <a href="admin.php?pid=22&cap_nhat&id=<?php echo $row["MaMonAn"] ?>"><?php echo $row["MaMonAn"]; ?></a></td>
                                    <td id = "padding-menu1"> <a href="admin.php?pid=22&cap_nhat&id=<?php echo $row["MaMonAn"] ?>"><?php echo $row["TenMonAn"]; ?></a> </td>
                                    <td id = "padding-menu1"> <a href="admin.php?pid=22&cap_nhat&id=<?php echo $row["MaMonAn"] ?>"><?php echo $row["Gia"]; ?></a> </td>
                                    <td id = "padding-menu1"> <a href="admin.php?pid=22&cap_nhat&id=<?php echo $row["MaMonAn"] ?>"><?php echo $row["TenLoai"]; ?></a> </td>
                                    <td id = "padding-menu2"> <a href="admin.php?pid=22&cap_nhat&id=<?php echo $row["MaMonAn"] ?>"><img src="../assets/img_menu/<?php echo $row["HinhAnh"]?>" alt="" width="120" height="70px"></a> </td>
                                    <td id = "padding-menu3"> <a href="admin.php?pid=22&cap_nhat&id=<?php echo $row["MaMonAn"] ?>" <?php $ctrl_laydtmenu->hien_thi_mau_trang_thai_mon($row["TrangThaiMenu"]) ?>> <?php $ctrl_laydtmenu->hien_thi_trang_thai_mon($row["TrangThaiMenu"]); ?> </a></td>
                                </tr>
                                <?php        
                                        }
                                    }
                                ?>
                            </tbody>
                        </table>
                    </div>
                    <div class = "previous-table">
                    </div>
                </div>
            </div>