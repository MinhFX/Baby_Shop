<?php
require "DataBase.php";
$db = new DataBase();
$query = "SELECT * FROM Vouchers";
class Voucher
{
    function Voucher($VoucherID, $VoucherName, $Points, $DiscountPrice, $Category_ID, $Quantity, $Status)
    {
        $this->VoucherID = $VoucherID;
        $this->VoucherName = $VoucherName;
        $this->Points = $Points;
        $this->DiscountPrice = $DiscountPrice;
        $this->Category_ID = $Category_ID;
        $this->Quantity = $Quantity;
        $this->Status = $Status;
    }
}

$data = mysqli_query($db->dbConnect(), $query);
$mangVoucher = array();
while ($row = mysqli_fetch_assoc($data)) {
    array_push($mangVoucher, new Voucher($row['VoucherID'], $row['VoucherName'], $row['Points'], $row['DiscountPrice'], $row['Category_ID'], $row['Quantity'], $row['Status']));
    echo "1:";
}

echo json_encode($mangVoucher);
