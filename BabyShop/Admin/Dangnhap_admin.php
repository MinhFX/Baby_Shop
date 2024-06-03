<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" charset="utf-8">
        <title> Night Light </title>
        <link rel="stylesheet" href="assets/css/style-admin.css">
        <script src="assets/js/admin.js"> </script>
</head>
<body>
<!DOCTYPE html>
<html>
<head>
  <title>Form Đăng nhập</title>
  <link rel="stylesheet" type="text/css" href="assets/css/style.css">
</head>
<body>

<div class="login-container">
    <h2>Đăng nhập</h2>

    <form action="control/ctrl_dangnhap_admin.php" method="post" enctype="multipart/form-data">
      <div class="form-group">
        <label for="username">Tên đăng nhập:</label>
        <input type="text" id="username" name="username" required>
      </div>
      <div class="form-group">
        <label for="password">Mật khẩu:</label>
        <input type="password" id="password" name="password" required>
      </div>
      <div class="form-group">
        <input type="submit" value="Đăng nhập">
      </div>
    </form>
  </div>
    <style>
        body {
  font-family: Arial, sans-serif;
  background-color: #e2c36d;
  background-image: url(assets/img_menu/monchinh.jpg);
  padding: 20px;
}

.login-container {
  max-width: 400px;
  margin: 0 auto;
  background-color: #fff;
  padding: 20px;
  border-radius: 5px;
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.3);
}

.login-container label{
    margin-top: 10px;
    margin-bottom: 10px;
}

h2 {
  text-align: center;
}

.form-group {
  margin-bottom: 15px;
}

label {
  display: block;
  font-weight: bold;
}

input[type="text"],
input[type="password"] {
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

input[type="submit"] {
  background-color: #e2c36d;
  color: white;
  padding: 10px 15px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  width: 100%;

}

input[type="submit"]:hover {
  background-color: #dc3545;
}

    </style>
</body>
</html>