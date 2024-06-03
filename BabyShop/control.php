<?php
include("connect.php");
class control extends connect
{
    public $data;

    function getVoucher()
    {
        $respone = array();
        $respone["Vouchers"] = array();
        $sql = "select * from Vouchers LIMIT 5";
        $kq = $this->link->query($sql);
        while ($row = $kq->fetch_array()) {
            $t = array();
            $t["VoucherID"] = $row["VoucherID"];
            $t["VoucherName"] = $row["VoucherName"];
            $t["Points"] = $row["Points"];
            $t["DiscountPrice"] = $row["DiscountPrice"];
            $t["Category_ID"] = $row["Category_ID"];
            $t["Quantity"] = $row["Quantity"];
            $t["Status"] = $row["Status"];
            array_push($respone["Vouchers"], $t);
        }
        header('Content-Type: application/json');
        echo json_encode($respone);
    }

    function getProduct()
    {
        $respone = array();
        $respone["products"] = array();
        $sql = "select * from products";
        $kq = $this->link->query($sql);
        while ($row = $kq->fetch_array()) {
            $t = array();
            $t["ProductID"] = $row["ProductID"];
            $t["Productname"] = $row["Productname"];
            $t["ImageProduct"] = $row["ImageProduct"];
            $t["Describes"] = $row["Describes"];
            $t["Price"] = $row["Price"];
            $t["Quantity"] = $row["Quantity"];
            $t["Status"] = $row["Status"];
            $t["Category_ID"] = $row["Category_ID"];
            array_push($respone["products"], $t);
        }
        header('Content-Type: application/json');
        echo json_encode($respone);
    }


    function prepareData($data)
    {
        return mysqli_real_escape_string($this->link, stripslashes(htmlspecialchars($data)));
    }

    function signUp($table, $Username, $Password)
    {
        $Username = $this->prepareData($Username);
        $Password = $this->prepareData($Password);

        $Password = password_hash($Password, PASSWORD_DEFAULT);
        $kq = $this->getAccount($Username);
        if ($kq->num_rows == 0) {
            $kq = "INSERT INTO " . $table . " (Username, Password,Points) VALUES ('" . $Username . "','" . $Password . "','0' )";
            if ($this->link->query($kq)) {
                return "Đăng ký thành công";
            } else
                return "Lỗi kết nối !!";
        } else {
            return "Tài khoản đã tồn tại";
        }
    }


    
    //Phần Dũng
    function getCategoriesHome()
    {
        $respone = array();
        $respone["categorieshome"] = array();
        $sql = "select * from categorieshome";
        $kq = $this->link->query($sql);
        while ($row = $kq->fetch_array()) {
            $t = array();
            $t["CategoryID"] = $row["CategoryID"];
            $t["CategoryName"] = $row["CategoryName"];
            $t["Image"] = $row["Image"];
            array_push($respone["categorieshome"], $t);
        }
        header('Content-Type: application/json');
        echo json_encode($respone);
    }

    function getSliderBanner()
    {
        $respone = array();
        $respone["sliderimage"] = array();
        $sql = "select * from sliderimage";
        $kq = $this->link->query($sql);
        while ($row = $kq->fetch_array()) {
            $t = array();
            $t["ID_banner"] = $row["ID_banner"];
            $t["Image"] = $row["Image"];
            $t["Status"] = $row["Status"];
            array_push($respone["sliderimage"], $t);
        }
        header('Content-Type: application/json');
        echo json_encode($respone);
    }

    function getProductBestSale()
    {
        $respone = array();
        $respone["products"] = array();
        $sql = "select * from products where Category_ID = '2'";
        $kq = $this->link->query($sql);
        while ($row = $kq->fetch_array()) {
            $t = array();
            $t["ProductID"] = $row["ProductID"];
            $t["Productname"] = $row["Productname"];
            $t["ImageProduct"] = $row["ImageProduct"];
            $t["Describes"] = $row["Describes"];
            $t["Price"] = $row["Price"];
            $t["Quantity"] = $row["Quantity"];
            $t["Status"] = $row["Status"];
            $t["Category_ID"] = $row["Category_ID"];
            array_push($respone["products"], $t);
        }
        header('Content-Type: application/json');
        echo json_encode($respone);
    }

    function getProductWithSearch($txtSearch)
    {
        $respone = array();
        $respone["products"] = array();
        $sql = "select * from products where Productname LIKE '%$txtSearch%' ";
        $kq = $this->link->query($sql);
        while ($row = $kq->fetch_array()) {
            $t = array();
            $t["ProductID"] = $row["ProductID"];
            $t["Productname"] = $row["Productname"];
            $t["ImageProduct"] = $row["ImageProduct"];
            $t["Describes"] = $row["Describes"];
            $t["Price"] = $row["Price"];
            $t["Quantity"] = $row["Quantity"];
            $t["Status"] = $row["Status"];
            $t["Category_ID"] = $row["Category_ID"];
            array_push($respone["products"], $t);
        }
        header('Content-Type: application/json');
        echo json_encode($respone);
    }

    function getProductBestSale2()
    {
        $respone = array();
        $respone["products"] = array();
        $sql = "select * from products where Category_ID = '9'";
        $kq = $this->link->query($sql);
        while ($row = $kq->fetch_array()) {
            $t = array();
            $t["ProductID"] = $row["ProductID"];
            $t["Productname"] = $row["Productname"];
            $t["ImageProduct"] = $row["ImageProduct"];
            $t["Describes"] = $row["Describes"];
            $t["Price"] = $row["Price"];
            $t["Quantity"] = $row["Quantity"];
            $t["Status"] = $row["Status"];
            $t["Category_ID"] = $row["Category_ID"];
            array_push($respone["products"], $t);
        }
        header('Content-Type: application/json');
        echo json_encode($respone);
    }


    // Thêm vào
    function getProCategory()
    {
        $respone = array();
        $respone["procategory"] = array();
        $sql = "select * from productcategory";
        $kq = $this->link->query($sql);
        while($row = $kq->fetch_array())
        {
            $t = array();
            $t["Category_ID"] = $row["Category_ID"];
            $t["Category_name"] = $row["Category_name"];
            array_push($respone["procategory"], $t);
        }
        header('Content-Type: application/json');
        echo json_encode($respone);
    }

    function getProductWithID($id)
    {
        $respone = array();
        $respone["products"] = array();
        $sql = "select * from products where Category_ID = '$id'";
        $kq = $this->link->query($sql);
        while($row = $kq->fetch_array())
        {
            $t = array();
            $t["ProductID"] = $row["ProductID"];
            $t["Productname"] = $row["Productname"];
            $t["ImageProduct"] = $row["ImageProduct"];
            $t["Describes"] = $row["Describes"];
            $t["Price"] = $row["Price"];
            $t["Quantity"] = $row["Quantity"];
            $t["Status"] = $row["Status"];
            $t["Category_ID"] = $row["Category_ID"];
            array_push($respone["products"], $t);
        }
        header('Content-Type: application/json');
        echo json_encode($respone);
    }

    function getNews()
    {
        $respone = array();
        $respone["news"] = array();
        $sql = "select news.*, staff.Staffname 
        from news 
        INNER JOIN staff ON news.StaffID = staff.StaffID 
        WHERE Status = 1
        ORDER BY news.NewsID DESC";
        $kq = $this->link->query($sql);
        while($row = $kq->fetch_array())
        {
            $t = array();
            $t["NewsID"] = $row["NewsID"];
            $t["StaffID"] = $row["StaffID"];
            $t["Title"] = $row["Title"];
            $t["Content"] = $row["Content"];
            $t["Status"] = $row["Status"];
            $t["Date"] = $row["Date"];
            $t["ImageNews"] = $row["ImageNews"];
            $t["Staffname"] = $row["Staffname"];
            array_push($respone["news"], $t);
        }
        header('Content-Type: application/json');
        echo json_encode($respone);
    }


    // function getAccount($username)
    // {
    //     $sql = "select * from users where Username = '$username'";
    //     $kq = $this->link->query($sql);
    //     return $kq;
    // }


    //Thêm mới
    function getOrder($username)
    {
        $respone = array();
        $respone["orders"] = array();
        $sql = "select * from orders where Username = '$username' ORDER BY OrderID DESC";
        $kq = $this->link->query($sql);
        while($row = $kq->fetch_array())
        {
            $t = array();
            $t["OrderID"] = $row["OrderID"];
            $t["StaffID"] = $row["StaffID"];
            $t["Username"] = $row["Username"];
            $t["Fullname"] = $row["Fullname"];
            $t["Phone"] = $row["Phone"];
            $t["Address"] = $row["Address"];
            $t["Total"] = $row["Total"];
            $t["OrderDate"] = $row["OrderDate"];
            $t["TimeOrder"] = $row["TimeOrder"];
            $t["Status"] = $row["Status"];
            array_push($respone["orders"], $t);
        }
        header('Content-Type: application/json');
        echo json_encode($respone);
    }

    function getOrderStatus($username, $status)
    {
        $respone = array();
        $respone["orders"] = array();
        $sql = "select * from orders where Username = '$username' and Status = '$status' ORDER BY OrderID DESC";
        $kq = $this->link->query($sql);
        while($row = $kq->fetch_array())
        {
            $t = array();
            $t["OrderID"] = $row["OrderID"];
            $t["StaffID"] = $row["StaffID"];
            $t["Username"] = $row["Username"];
            $t["Fullname"] = $row["Fullname"];
            $t["Phone"] = $row["Phone"];
            $t["Address"] = $row["Address"];
            $t["Total"] = $row["Total"];
            $t["OrderDate"] = $row["OrderDate"];
            $t["TimeOrder"] = $row["TimeOrder"];
            $t["Status"] = $row["Status"];
            array_push($respone["orders"], $t);
        }
        header('Content-Type: application/json');
        echo json_encode($respone);
    }

    function getProductOrder($orderID)
    {
        $respone = array();
        $respone["ordersdetail"] = array();
        $sql = "select ordersdetail.*, products.Productname, products.ImageProduct from ordersdetail 
        INNER JOIN products ON ordersdetail.ProductID = products.ProductID
        where OrderID = '$orderID'";
        $kq = $this->link->query($sql);
        while($row = $kq->fetch_array())
        {
            $t = array();
            $t["OrderID"] = $row["OrderID"];
            $t["ProductID"] = $row["ProductID"];
            $t["Price"] = $row["Price"];
            $t["Quantity"] = $row["Quantity"];
            $t["Productname"] = $row["Productname"];
            $t["ImageProduct"] = $row["ImageProduct"];
            array_push($respone["ordersdetail"], $t);
        }
        header('Content-Type: application/json');
        echo json_encode($respone);
    }

    function createOrder($username, $total, $fullname, $phone, $address)
    {
        date_default_timezone_set("Asia/Ho_Chi_Minh");
        $ngay = date(DATE_W3C);
        $gio = date("H:i:s");
        $sql = "INSERT INTO orders(StaffID, Username, Fullname, Phone, Address, Total, OrderDate, TimeOrder, Status) VALUES 
        (NULL, '$username', '$fullname', '$phone', '$address', '$total', '$ngay', '$gio', '1')";
        if ($this->link->query($sql))
        {
            return "success";
        }
        else
        {
            return "Lỗi";
        }
    }

    function getMaxOrderID()
    {
        $sql = "SELECT MAX(OrderID) AS 'OrderID' FROM orders";
        $kq = $this->link->query($sql);
        $row = $kq->fetch_assoc();
        return $row["OrderID"];
    }

    function getQuantityFromProduct($productID)
    {
        $sql = "SELECT Quantity FROM products WHERE ProductID = '$productID'";
        $kq = $this->link->query($sql);
        $row = $kq->fetch_assoc();
        return $row["Quantity"];
    }

    function checkProductQuantity($productID, $quantity)
    {
        $getCurrentQuantity = $this->getQuantityFromProduct($productID);
        if ($getCurrentQuantity - $quantity >= 0)
        {
            return "success";
        }
        else
        {
            return "Lỗi";
        }
    }

    function createOrderDetail($productID, $price, $quantity)
    {
        $id = $this->getMaxOrderID();
        $sql = "INSERT INTO ordersdetail(OrderID, ProductID, Price, Quantity) VALUES 
        ('$id', '$productID', '$price', '$quantity')";
        if ($this->link->query($sql))
        {
            $sql = "UPDATE products SET Quantity = Quantity - '$quantity' WHERE ProductID = '$productID'";
            if ($this->link->query($sql))
            {
                return "success";
            }
            else
            {
                return "Lỗi";
            }
        }
        else
        {
            return "Lỗi";
        }
    }

    function plusPoint($username, $point)
    {
        $sql = "UPDATE users SET Points = Points + '$point' WHERE Username = '$username'";
        if ($this->link->query($sql))
        {
            return "success";
        }
        else
        {
            return "Lỗi";
        }
    }

    function minusPoint($username, $point)
    {
        $sql = "UPDATE users SET Points = Points - '$point' WHERE Username = '$username'";
        if ($this->link->query($sql))
        {
            return "success";
        }
        else
        {
            return "Lỗi";
        }
    }

    function checkOrderStatus($orderID)
    {
        $sql = "SELECT Status FROM orders WHERE OrderID = '$orderID'";
        $kq = $this->link->query($sql);
        $row = $kq->fetch_assoc();
        return $row["Status"];
    }

    function updateOrderStatus($orderid, $status)
    {
        $check = $this->checkOrderStatus($orderid);
        if ($check == 1)
        {
            $sql = "UPDATE orders SET Status = '$status' WHERE OrderID = '$orderid'";
            if ($this->link->query($sql))
            {
                $sql = "UPDATE products 
                INNER JOIN ordersdetail ON products.ProductID = ordersdetail.ProductID
                INNER JOIN orders ON ordersdetail.OrderID = orders.OrderID
                SET products.Quantity = products.Quantity + ordersdetail.Quantity
                WHERE orders.OrderID = '$orderid'";
                if ($this->link->query($sql))
                {
                    return "success";
                }
                else
                {
                    return "Lỗi";
                }
            }
            else
            {
                return "Lỗi";
            }
        }
        else
        {
            return "Lỗi";
        }
    }

    function getUser($username)
    {
        $respone = array();
        $respone["users"] = array();
        $sql = "select * from users where Username = '$username'";
        $kq = $this->link->query($sql);
        while($row = $kq->fetch_array())
        {
            $t = array();
            $t["Username"] = $row["Username"];
            $t["Password"] = $row["Password"];
            $t["Email"] = $row["Email"];
            $t["Fullname"] = $row["Fullname"];
            $t["Birthday"] = $row["Birthday"];
            $t["Gender"] = $row["Gender"];
            $t["Phone"] = $row["Phone"];
            $t["Address"] = $row["Address"];
            $t["ImageUser"] = $row["ImageUser"];
            $t["Points"] = $row["Points"];
            array_push($respone["users"], $t);
        }
        header('Content-Type: application/json');
        echo json_encode($respone);
    }

    function getAccount($username)
    {
        $sql = "select * from users where Username = '$username'";
        $kq = $this->link->query($sql);
        return $kq;
    }

    function getVoucherAll()
    {
        $respone = array();
        $respone["Vouchers"] = array();
        $sql = "select * from Vouchers";
        $kq = $this->link->query($sql);
        while ($row = $kq->fetch_array()) {
            $t = array();
            $t["VoucherID"] = $row["VoucherID"];
            $t["VoucherName"] = $row["VoucherName"];
            $t["Points"] = $row["Points"];
            $t["DiscountPrice"] = $row["DiscountPrice"];
            $t["Category_ID"] = $row["Category_ID"];
            $t["Quantity"] = $row["Quantity"];
            $t["Status"] = $row["Status"];
            array_push($respone["Vouchers"], $t);
        }
        header('Content-Type: application/json');
        echo json_encode($respone);
    }

    function logIn($table, $Username, $Password)
    {
        $Username = $this->prepareData($Username);
        $Password = $this->prepareData($Password);

        $kq = $this->getAccount($Username);
        if ($kq -> num_rows > 0) {
            $row = $kq->fetch_assoc();
            $dbusername = $row['Username'];
            $dbpassword = $row['Password'];
            if ($dbusername == $Username && password_verify($Password, $dbpassword)) {
                return "success";
            }
            else
            {
                return "Tài khoản hoặc mật khẩu có thể không đúng";
            } 
        }
        else return "Tài khoản không tồn tại";
    }
    

    function updateUser($username,$password,$email,$fullname,$birthday,$gender,$phone,$address)
    {
        $ketqua = $this->logIn('1',$username,$password);
        if($ketqua == "success")
        {
            $sql = "UPDATE users SET Email = '$email', Fullname = '$fullname',Birthday = '$birthday',Gender = $gender, Phone = '$phone', Address = '$address' WHERE Username = '$username'";
            if ($this->link->query($sql))
            {
                return "success";
            }
            else
            {
                return "Lỗi";
            }
        }
        else{
            return "Hãy sử dụng App BabyShop để sửa đổi thông tin !!";
        }
       
    }


}
