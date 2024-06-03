<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="utf-8">
        <title> BabyShop </title>
        <link rel="stylesheet" href="assets/css/style-admin.css">
        <link rel="icon" href="Image/PADLogo.png" sizes="16x16">
        <script src="assets/js/admin.js"> </script>
</head>
<body>
<?php
    session_start();
    if(isset($_SESSION["username"]))
    {
?>
    <div class = "container">
        <div class = "wrapper-all">
           
            <div class = Navbar-section>
                <div class = "logo">
                    <img id = "navbar-img" src="assets/images_admin/navbar.png" alt="" width="16" height="16" onclick="NavTrans()">
                    <img src="assets/images/LogoMain-admin.png" alt="" width="10" height="10">
                </div>
                <div class = "menu-all">
                    <p class = "Nav-Close"> Danh mục </p>
                    <ul>
                        <li> <img src="assets/images_admin/staff.png" alt="" width="22" height="22" > <a href="admin.php?pid=4"> Nhân viên </a> </li>

                        
                        <li> <img src="assets/images_admin/invoice.png" alt="" width="22" height="22" > <a href="admin.php?pid=5"> Đơn hàng </a> </li>

                        <li> <img src="assets/images_admin/news.png" alt="" width="22" height="22" > <a href="admin.php?pid=9"> Tin tức </a> </li>
                    </ul>
                    <p class = "Nav-Close"> Website </p>
                    <ul>
                        <li> <img src="assets/images_admin/setting.png" alt="" width="22" height="22" > <a href="admin.php?pid=10"> Tùy chỉnh </a> </li>

                    </ul>
                    

                    
                </div>    
            </div>
            
            <div class = "Main-screen">
                <div class = "header-section">
                    <div class = "nav">
                        <div class = "nav-layer1">
                            <a href="../index.php"> Trang chủ </a>
                            <div class = "button" onclick = "ButtonTrans()">
                                <small class = "circle-button"></small>
                            </div>
                        </div>
                        

                       
                        
                    </div> 
                    <div class = "card-admin">
                       <img src="assets/images_avatar/<?php echo $_SESSION["HinhNV"] ?>" alt="" width = "50">
                        <div>
                            <p> Hi, xin chào ! </p>
                            <h1> <?php echo $_SESSION["username"] ?> </h1>
                        </div>
                    </div>
                   
            
                </div>
                
                <!-- Khung làm việc -->
                
                <!-- Khung làm việc -->

                <?php
                if (!isset($_GET["pid"]))
                    include("Views/Dashboard-section_admin.php");
                else
                    switch($_GET["pid"])
                    {
                        case "1": include("Views/Dashboard-section_admin.php"); break;
                        case "2": include("Views/Thucdon-section_admin.php"); break;
                        case "3": if (!isset($_GET["id_khachhang"]))
                            // include("Views/Khachhang-edit_admin.php");
                            include("Views/Khachhang-section_admin.php");
                            else 
                                include("Views/Khachhang-details_admin.php");
                                break;
                        case "4":
                            if (!isset($_GET["view"]))
                                include("Views/Nhanvien-section_admin.php");
                            else
                                include("Views/Nhanvien-section2_admin.php");
                                break;
                        
                        case "5": include("Views/Datban-section_admin.php"); break;
                        case "9": include("Views/Tintuc_admin.php"); break;

                        case "11": include("Views/testsearch.php"); break;
                        case "22": include("Views/Them-mon-section_admin.php"); break;
                        case "44": include("Views/Nhanvien-details_admin.php"); break;

                        case "55": include("Views/Datban-details_admin.php"); break;
                        case "66": include("Views/Hoadon-details_admin.php"); break;
                        case "99": include("Views/Tintuc-details_admin.php"); break;

                      
                        case "222": include("Views/danhmuc-mon_admin.php"); break;


                        case "31": include("Views/Khachhang-edit_admin.php"); break;
                    }

                    
                    
                ?>
                
    </div>
    <?php        
    } else{
        header("location:Dangnhap_admin.php");
    }
?>
</body>
</html>