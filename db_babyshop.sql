-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 25, 2023 lúc 05:55 PM
-- Phiên bản máy phục vụ: 10.4.28-MariaDB
-- Phiên bản PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `db_babyshop`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `categorieshome`
--

CREATE TABLE `categorieshome` (
  `CategoryID` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `CategoryName` varchar(255) DEFAULT NULL,
  `Image` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `categorieshome`
--

INSERT INTO `categorieshome` (`CategoryName`, `Image`) VALUES
('Mẹ đi chợ', 'ic_3.jpg'),
('Khuyến mãi', 'ic_6.jpg'),
('Liên hệ tư vấn', 'ic_7.jpg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `news`
--

CREATE TABLE `news` (
  `NewsID` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `StaffID` int(11) DEFAULT NULL,
  `ImageNews` varchar(200) DEFAULT NULL,
  `Title` varchar(200) DEFAULT NULL,
  `Content` longtext DEFAULT NULL,
  `Status` bit(1) DEFAULT NULL,
  `Date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `news`
--

INSERT INTO `news` (`StaffID`, `ImageNews`, `Title`, `Content`, `Status`, `Date`) VALUES
(1, 'n1.jpg', 'News 1', 'Đây là cửa hàng đầu tiên tại HCM', b'1', '2023-11-08'),
(2, 'n2.jpg', 'News 2', 'Sữa Vinamilk - Hương Vị Tinh Khiết Từ Thiên Nhiên - Sữa Vinamill của chúng tôi là nguồn cung cấp dinh dưỡng tốt nhất cho bạn và gia đình. Với hương vị tinh khiết từ thiên nhiên và đầy đủ chất dinh dưỡng như canxi, protein và vitamin, sản phẩm giúp tăng cường sức đề kháng và phát triển toàn diện cho cả trẻ em và người lớn.', b'0', '2023-11-09'),
(3, 'n3.jpg', 'News 3', 'Thuộc thương hiệu tã cho bé hàng đầu Hoa Kỳ, Huggies không chỉ bao gồm nhiều loại ứng với từng giai đoạn phát triển của bé, mà còn đa dạng các dòng sản phẩm. Hôm nay, Con Cưng sẽ giúp mẹ tìm hiểu rõ hơn về các dòng tã cho bé này và tìm xem đâu là sản phẩm phù hợp nhất cho bé yêu của mình nhé.', b'1', '2023-11-10'),
(1, 'n4.jpg', 'News 4', 'Với khả năng kháng khuẩn cao, gạc rơ lưỡi Dr Papie nổi tiếng về công dụng làm sạch và bảo vệ khoang miệng của bé khỏi các tác nhân gây hại. Song, khoang miệng của bé sơ sinh lại rất mỏng và khá nhạy cảm. Đặc điểm này khiến nhiều ba mẹ vẫn băn khoăn không biết có nên dùng gạc rơ lưỡi Dr Papie cho bé sơ sinh không? Bài viết sau đây sẽ giúp ba mẹ có câu trả lời xác minh cho thắc mắc này. Mời ba mẹ tham khảo nhé!', b'1', '2023-11-11'),
(2, 'n5.jpg', 'News 5', 'SKĐS - Để trẻ có thể tăng trưởng và phát triển chiều cao tối ưu, cùng với chế độ dinh dưỡng cân bằng, lành mạnh, việc bổ sung đầy đủ vitamin...', b'0', '2023-11-12'),
(9, 'n6.jpg', 'News 6', 'Bột Sữa Pigeon - Lựa Chọn Hoàn Hảo Cho Bé Yêu - Bột sữa Pigeon là sự kết hợp hoàn hảo của chất lượng và dinh dưỡng. Với công thức dễ sử dụng và hương vị ngon, sản phẩm giúp bé yêu của bạn phát triển toàn diện. Cung cấp đầy đủ các chất dinh dưỡng cần thiết, bột sữa Pigeon là lựa chọn hàng đầu cho sức khỏe và sự phát triển của bé.', b'1', '2023-11-13'),
(9, 'n7.jpg', 'News 7', 'Bột Wako - Ăn Nhanh, Lớn Nhanh - Bột Wako là giải pháp hoàn hảo cho những người bận rộn muốn duy trì chế độ ăn uống lành mạnh. Được chế biến từ các nguyên liệu tươi ngon và giàu chất dinh dưỡng, sản phẩm giúp bạn lớn nhanh mà không cần phải lo lắng về chất lượng dinh dưỡng.', b'0', '2023-11-14'),
(1, 'n8.jpg', 'News 8', 'Khẩu Trang Chất Lượng Cao - Bảo vệ sức khỏe của bạn và gia đình khỏi các hạt bụi, vi khuẩn và virus với khẩu trang chất lượng cao của chúng tôi. Sản phẩm được làm từ vật liệu an toàn và thoáng khí, giúp bạn thở dễ dàng mà không lo lắng về các hạt bụi gây hại. Sự thoải mái và độ bền của sản phẩm làm cho nó trở thành lựa chọn hàng đầu cho mọi gia đình.', b'1', '2023-11-15'),
(1, 'n9.jpg', 'News 9', 'Bột Meiji - Giúp Bé Tăng Cân Đúng Cách - Bột Meiji chứa đựng sức mạnh của các thành phần dinh dưỡng cần thiết để giúp bé tăng cân đúng cách và khỏe mạnh. Với hương vị thơm ngon, sản phẩm không chỉ là nguồn cung cấp năng lượng mà còn giúp bé phát triển chiều cao và trí óc.', b'0', '2023-11-16'),
(2, 'n10.jpg', 'News 10', 'Bình sữa Moyuum là sản phẩm đang làm mưa làm gió trên thị trường bình sữa cho bé. Vậy đây là sản phẩm của nước nào, có bao nhiêu loại? Bình sữa Moyuum có thật sự tốt không, tại sao lại được nhiều ba mẹ ưa chuộng như vậy? Cùng Con Cưng khám phá những thông tin về dòng sản phẩm này trong bài viết dưới đây.', b'1', '2023-11-17');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `orders`
--

CREATE TABLE `orders` (
  `OrderID` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `StaffID` int(11) DEFAULT NULL,
  `Username` varchar(200) DEFAULT NULL,
  `Fullname` varchar(200) DEFAULT NULL,
  `Phone` int(11) DEFAULT NULL,
  `Address` varchar(200) DEFAULT NULL,
  `Total` int(11) DEFAULT NULL,
  `OrderDate` date DEFAULT NULL,
  `TimeOrder` time DEFAULT NULL,
  `Status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `orders`
--

INSERT INTO `orders` (`StaffID`, `Username`, `Fullname`, `Phone`, `Address`, `Total`, `OrderDate`, `TimeOrder`, `Status`) VALUES
(1, NULL, NULL, NULL, NULL, 1200000, '2022-09-11', '11:23:01', 1),
(2, NULL, NULL, NULL, NULL, 400000, '2022-09-11', '11:23:01', 1),
(3, NULL, NULL, NULL, NULL, 5900000, '2022-09-11', '11:23:01', 1),
(4, NULL, NULL, NULL, NULL, 12200000, '2022-09-11', '11:23:01', 1),
(5, NULL, NULL, NULL, NULL, 100000, '2022-09-11', '11:23:01', 1),
(6, NULL, NULL, NULL, NULL, 105000, '2022-09-11', '11:23:01', 1),
(7, NULL, NULL, NULL, NULL, 20000000, '2022-09-11', '11:23:01', 1),
(8, NULL, NULL, NULL, NULL, 921000, '2022-09-11', '11:23:01', 1),
(9, NULL, NULL, NULL, NULL, 3400000, '2022-09-11', '11:23:01', 1),
(10, NULL, NULL, NULL, NULL,140000, '2022-09-11', '11:23:01', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ordersdetail`
--

CREATE TABLE `ordersdetail` (
  `OrderID` int(11) DEFAULT NULL,
  `ProductID` int(11) DEFAULT NULL,
  `Price` int(11) DEFAULT NULL,
  `Quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `ordersdetail`
--

INSERT INTO `ordersdetail` (`OrderID`, `ProductID`, `Price`, `Quantity`) VALUES
(1, 1, 230000, 1),
(2, 2, 400000, 3),
(3, 2, 400000, 1),
(4, 10, 200000, 2),
(5, 2, 35000, 5),
(6, 1, 230000, 4),
(7, 2, 400000, 3),
(8, 2, 400000, 1),
(9, 10, 200000, 2),
(10, 2, 35000, 5);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `productcategory`
--

CREATE TABLE `productcategory` (
  `Category_ID` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `Category_name` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `productcategory`
--

INSERT INTO `productcategory` (`Category_name`) VALUES
('Tất cả'),
('Sữa'),
('Bỉm, tã'),
('Đồ dùng'),
('Sức khỏe'),
('Siro'),
('Quần áo'),
('Thực phẩm'),
('Bột ăn dặm'),
('Phụ kiện');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `products`
--

CREATE TABLE `products` (
  `ProductID` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `Productname` varchar(100) DEFAULT NULL,
  `ImageProduct` varchar(100) DEFAULT NULL,
  `Describes` longtext DEFAULT NULL,
  `Price` float DEFAULT NULL,
  `Quantity` int(11) DEFAULT NULL,
  `Status` bit(1) DEFAULT NULL,
  `Category_ID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `products`
--

INSERT INTO `products` (`Productname`, `ImageProduct`, `Describes`, `Price`, `Quantity`, `Status`, `Category_ID`) VALUES
('Muỗng pipo', '1.jpg', 'Muỗng cho em bé, nhựa an toàn', 20000, 100, b'1', 1),
('Thú bông Poba', '2.jpg', 'Thú bông Poba xuất xứ từ Úc', 100000, 300, b'1', 1),
('Khẩu trang', '3.jpg', 'khẩu trang chất lượng cao', 40000, 6000, b'1', 1),
('Bột sữa Pigeon', '4.jpg', 'Thú bông Poba xuất xứ từ Úc', 199000, 300, b'1', 2),
('Bột Meiji', '5.jpg', 'Thú bông Poba xuất xứ từ Úc', 189000, 300, b'1', 9),
('Bột Wako', '6.jpg', 'Thú bông Poba xuất xứ từ Úc', 209000, 300, b'1', 9),
('Bột Deilac', '7.jpg', 'Thú bông Poba xuất xứ từ Úc', 199000, 300, b'1', 2),
('Khăn vải', '8.jpg', 'Thú bông Poba xuất xứ từ Úc', 5000, 300, b'1', 1),
('Bình sữa loại 1', '9.jpg', 'Nhựa bình tốt nhất', 50000, 300, b'1', 1),
('Tăm bông cho bé', '10.jpg', 'Bông dịu nhẹ và mền mại', 30000, 300, b'1', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ratings`
--

CREATE TABLE `ratings` (
  `id` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `rating` float NOT NULL,
  `feedback` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sliderimage`
--

CREATE TABLE `sliderimage` (
  `ID_banner` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `Image` varchar(200) DEFAULT NULL,
  `Status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `sliderimage`
--

INSERT INTO `sliderimage` (`Image`, `Status`) VALUES
('banner1.jpg', 1),
('banner2.jpg', 1),
('banner3.png', 1),
('banner4.jpg', 1),
('banner5.jpg', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `staff`
--

CREATE TABLE `staff` (
  `StaffID` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `Staffname` varchar(200) DEFAULT NULL,
  `Password` varchar(255) DEFAULT NULL,
  `Fullname` varchar(200) DEFAULT NULL,
  `Birthday` date DEFAULT NULL,
  `Gender` bit(1) DEFAULT NULL,
  `Phone` int(11) DEFAULT NULL,
  `Address` varchar(200) DEFAULT NULL,
  `ImageStaff` varchar(200) DEFAULT NULL,
  `Role` int(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `staff`
--

INSERT INTO `staff` (`Staffname`, `Password`, `Fullname`, `Birthday`, `Gender`, `Phone`, `Address`, `ImageStaff`, `Role`) VALUES
('Minh', '111', 'Trí Minh', '2003-09-29', b'0', 992401293, '46/17/10', 'a1.jpg', 1),
('Dũng', '111', 'Trí Dũng', '2003-04-29', b'0', 996712935, '46/17/11', 'a2.jpg', 1),
('Phát', '111', 'Trí Phát', '2003-05-29', b'0', 992231209, '46/17/12', 'a3.jpg', 1),
('Trúc', '111', 'Trí Trúc', '2003-06-29', b'1', 992002344, '46/17/13', 'a4.jpg', 1),
('Mai', '111', 'Trí Mai', '2003-07-29', b'1', 992401202, '46/17/14', 'a5.jpg', 1),
('Khang', '111', 'Trí Khang', '2003-08-29', b'0', 992400444, '46/17/15', 'a6.jpg', 1),
('Lễ', '111', 'Trí Lễ', '2003-10-29', b'0', 921032667, '46/17/16', 'a7.jpg', 1),
('Ánh', '111', 'Trí Ánh', '2003-11-29', b'1', 910223450, '46/17/17', 'a8.jpg', 1),
('Vân', '111', 'Trí Vân', '2003-12-29', b'1', 994400550, '46/17/18', 'a9.jpg', 1),
('Bảo', '111', 'Trí Bảo', '2003-03-29', b'0', 900223233, '46/17/19', 'a10.jpg', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `Username` varchar(200) PRIMARY KEY NOT NULL,
  `Password` varchar(255) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `Fullname` varchar(200) DEFAULT NULL,
  `Birthday` date DEFAULT NULL,
  `Gender` bit(1) DEFAULT NULL,
  `Phone` int(11) DEFAULT NULL,
  `Address` varchar(200) DEFAULT NULL,
  `ImageUser` varchar(200) DEFAULT NULL,
  `Points` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`Username`, `Password`, `Email`, `Fullname`, `Birthday`, `Gender`, `Phone`, `Address`, `ImageUser`, `Points`) VALUES
('1234', '$2y$10$IiZ5uPUSANfYjDVDDeSGuO8t2PibhNSi6jJwvOTL85CJYAxeP4pyy', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0),
('12345', '$2y$10$9wwy9rPaJrOReVxCFfcyu.6iq3egr2INCZp5HdVItDLj00tZ21R82', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0),
('123456', '$2y$10$mETrU0vWqEj4292O43AojuNKXNSbJQiANMKPC6KjNCbnLRnZqcr5G', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0),
('1234567', '$2y$10$MNMj6.q7d6qeX9o63lgA6OwnGA5RqrcHbaEyK0kx6Wi59kYpkShZq', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0),
('callmebyyourname', 'callme123', 'callmebyyourname@gmail.com', 'Nguyễn Thanh Trúc', '1994-08-20', b'1', 789012345, '303 Maple St', 'user6.jpg', 75),
('enngochoang', 'vhl123', 'enngochoang@gmail.com', 'Ánh Ngọc Hoàng', '2003-11-29', b'1', 678901234, '202 Cedar St', 'user5.jpg', 300),
('giaboo', 'giaboo2607', 'volamgiaboo@gmail.com', 'Võ Lâm Gia Bảo', '2003-07-26', b'0', 901234567, '505 Walnut St', 'user8.jpg', 90),
('khangquachoffficial', 'khangdeptrai123', 'khangquach@gmail.com', 'Quách Dũy Khang', '2003-07-26', b'0', 890123456, '404 Birch St', 'user7.jpg', 220),
('khangvotri123', 'khang123', 'khang123@gmail.com', 'Võ Lâm Dũy Khang', '1998-07-18', b'0', 234567890, '707 Fir St', 'user10.jpg', 120),
('LCD', 'lychidungxalo', 'dunglychi771@gmail.com', 'Dũng Lý Chí', '2003-05-20', b'0', 567890123, '101 Pine St', 'user4.jpg', 50),
('Maiboa', 'anhmaideptria', 'cogaingay29@gmail.com', 'Lương Thị Thanh Mai', '2003-10-29', b'1', 345678901, '456 Elm St', 'user2.jpg', 150),
('MMB', 'mmbne', 'mmb123@gmail.com', 'Nguyễn Điển Trí Minh', '2003-09-29', b'0', 456789012, '789 Oak St', 'user3.jpg', 200),
('ThanhtrucNguyen', 'torucne', 'ngthtrc@gmail.com', 'Nguyễn Thị Thanh Trúc', '2003-11-01', b'1', 234567890, '123 Main St', 'user1.jpg', 100),
('vohieule', 'enxinhdep', 'vhl123@gmail.com', 'Võ Hiếu Lễ', '2003-04-15', b'1', 212345678, '606 Spruce St', 'user9.jpg', 180);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `vouchers`
--

CREATE TABLE `vouchers` (
  `VoucherID` int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `VoucherName` varchar(50) DEFAULT NULL,
  `Points` int(11) DEFAULT NULL,
  `DiscountPrice` float DEFAULT NULL,
  `Category_ID` int(11) DEFAULT NULL,
  `Quantity` int(11) DEFAULT NULL,
  `Status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `vouchers`
--

INSERT INTO `vouchers` (`VoucherName`, `Points`, `DiscountPrice`, `Category_ID`, `Quantity`, `Status`) VALUES
('Voucher1', 100, 10.5, 1, 50, 1),
('Voucher2', 150, 20, 2, 30, 1),
('Voucher3', 200, 15.75, 1, 25, 0),
('Voucher4', 120, 12, 3, 40, 1),
('Voucher5', 180, 25.5, 2, 20, 0);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `news`
--
ALTER TABLE `news`
  ADD KEY `StaffID` (`StaffID`);

--
-- Chỉ mục cho bảng `orders`
--
ALTER TABLE `orders`
  ADD KEY `StaffID` (`StaffID`),
  ADD KEY `Username` (`Username`);

--
-- Chỉ mục cho bảng `ordersdetail`
--
ALTER TABLE `ordersdetail`
  ADD KEY `ProductID` (`ProductID`),
  ADD KEY `OrderID` (`OrderID`);

--
-- Chỉ mục cho bảng `products`
--
ALTER TABLE `products`
  ADD KEY `Category_ID` (`Category_ID`);

--
-- Chỉ mục cho bảng `vouchers`
--
ALTER TABLE `vouchers`
  ADD KEY `Category_ID` (`Category_ID`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `orders`
--
ALTER TABLE `orders`
  MODIFY `OrderID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `ratings`
--
ALTER TABLE `ratings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `sliderimage`
--
ALTER TABLE `sliderimage`
  MODIFY `ID_banner` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `staff`
--
ALTER TABLE `staff`
  MODIFY `StaffID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `vouchers`
--
ALTER TABLE `vouchers`
  MODIFY `VoucherID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `categorieshome`
--
ALTER TABLE `categorieshome`
  MODIFY `CategoryID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `news`
--
ALTER TABLE `news`
  MODIFY `NewsID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `productcategory`
--
ALTER TABLE `productcategory`
  MODIFY `Category_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `products`
--
ALTER TABLE `products`
  MODIFY `ProductID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `news`
--
ALTER TABLE `news`
  ADD CONSTRAINT `news_ibfk_1` FOREIGN KEY (`StaffID`) REFERENCES `staff` (`StaffID`);

--
-- Các ràng buộc cho bảng `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`StaffID`) REFERENCES `staff` (`StaffID`),
  ADD CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`Username`) REFERENCES `users` (`Username`);

--
-- Các ràng buộc cho bảng `ordersdetail`
--
ALTER TABLE `ordersdetail`
  ADD CONSTRAINT `ordersdetail_ibfk_1` FOREIGN KEY (`ProductID`) REFERENCES `products` (`ProductID`),
  ADD CONSTRAINT `ordersdetail_ibfk_2` FOREIGN KEY (`OrderID`) REFERENCES `orders` (`OrderID`);

--
-- Các ràng buộc cho bảng `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`Category_ID`) REFERENCES `productcategory` (`Category_ID`);

--
-- Các ràng buộc cho bảng `vouchers`
--
ALTER TABLE `vouchers`
  ADD CONSTRAINT `vouchers_ibfk_1` FOREIGN KEY (`Category_ID`) REFERENCES `productcategory` (`Category_ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
