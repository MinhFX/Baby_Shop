<?php
include 'config.php';


if (isset($_POST['rating']) && isset($_POST['feedback'])) {

    $conn = new mysqli(servername, username, password, databasename);

    if ($conn->connect_error) {
        die("Kết nối đến cơ sở dữ liệu thất bại: " . $conn->connect_error);
    }

    $rating = $_POST['rating'];
    $feedback = $_POST['feedback'];

    $sql = "INSERT INTO ratings (rating, feedback) VALUES ('$rating', '$feedback')";

    $result = $conn->query($sql);
    if ($result === TRUE) {
        echo "Đánh giá và phản hồi đã được lưu.";
    } else {
        echo "Lỗi khi lưu đánh giá và phản hồi: " . $conn->error;
    }    

    $conn->close();
} else {
    echo "Không có đủ dữ liệu đánh giá và phản hồi được gửi từ ứng dụng Android.";
}
?>
