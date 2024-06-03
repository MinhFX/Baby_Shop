<div class = "header-infor">
                    <h1> Tin tức </h1>
                </div>
        <div class = "infor-wrapper">
                    <div class = "setting-table">
                    <div class = "add-user">
                            <a href="admin.php?pid=99&them_mon"> Thêm tin tức </a>
                    </div>
                    <div class="search">
                            <form action="admin.php?pid=9" method = "post">
                                <label for="lsearch"> Tìm kiếm </label>
                                <input type="text" id = "lseacrh" name="tim_kiem">
                            </form>
                        </div>
                    </div>
                    <div class = "main-table" id = "main-table-thucdon-section">
                        <table>
                            <thead>
                                <tr id = "center-menu">
                                    <th style="width: 10%;"> Mã ID </th>
                                    <th style="width: 20%;"> Tiêu đề </th>
                                    <th style="width: 15%;"> Ngày đăng </th>
                                    <th style="width: 10%;"> Người đăng </th>
                                    <th style="width: 10%;"> Hình ảnh </th>
                                    <th style="width: 10%;"> Trạng thái </th>
                                </tr>
                            </thead>
                            <tbody>
                                <?php
                                    include_once("control/ctrl_frm_admin.php");
                                    $ctrl_tintuc_admin = new ctrl_frm_admin();
                                    $kq = $ctrl_tintuc_admin->lay_tintuc();
                                    
                                    if (isset($_POST["tim_kiem"]))
                                    {
                                        $kq = $ctrl_tintuc_admin->lay_tintuc_tim($_POST["tim_kiem"]);
                                    }
                                    else{
                                        $kq = $ctrl_tintuc_admin->lay_tintuc();
                                    }

                                    while($row = $kq->fetch_assoc())
                                    {
                                ?>

                                <tr id = "center-menu">
                                    <td id = "padding-menu1"> <a href="admin.php?pid=99&cap_nhat&id=<?php echo $row["NewsID"] ?>"><?php echo $row["NewsID"]; ?></a></td>
                                    <td id = "padding-menu1"> <a href="admin.php?pid=99&cap_nhat&id=<?php echo $row["NewsID"] ?>"><?php echo $row["Title"]; ?></a> </td>
                                    <td id = "padding-menu1"> <a href="admin.php?pid=99&cap_nhat&id=<?php echo $row["NewsID"] ?>"><?php echo $row["Date"]; ?></a> </td>
                                    <td id = "padding-menu1"> <a href="admin.php?pid=99&cap_nhat&id=<?php echo $row["NewsID"] ?>">#<?php echo $row["StaffID"]; ?></a> </td>
                                    <td id = "padding-menu2"> <a href="admin.php?pid=99&cap_nhat&id=<?php echo $row["NewsID"] ?>"><img src="../ImageNews/<?php echo $row["ImageNews"]?>" alt="" width="120" height="70px"></a> </td>
                                    <td id = "padding-menu3"> <a href="admin.php?pid=99&cap_nhat&id=<?php echo $row["NewsID"] ?>" <?php $ctrl_tintuc_admin->hien_thi_mau_trang_thai_tintuc($row["Status"]) ?>> <?php $ctrl_tintuc_admin->hien_thi_trang_thai_tintuc($row["Status"]); ?> </a></td>
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
            </div>