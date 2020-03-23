-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th3 20, 2020 lúc 02:38 PM
-- Phiên bản máy phục vụ: 10.4.11-MariaDB
-- Phiên bản PHP: 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `dtstore`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `categoryName` varchar(2000) NOT NULL,
  `categoryImage` varchar(2000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `category`
--

INSERT INTO `category` (`id`, `categoryName`, `categoryImage`) VALUES
(1, 'Điện thoại', 'https://cdn.tgdd.vn/Products/Images/42/194955/realme-3-pro-blue-400x460.png'),
(2, 'Laptop', 'https://cdn.tgdd.vn/Products/Images/44/213570/dell-inspiron-5593-i5-1035g1-8gb-256gb-2gb-mx230-w-1-600x600.jpg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `productName` varchar(2000) NOT NULL,
  `productPrice` int(11) NOT NULL,
  `productImage` varchar(2000) NOT NULL,
  `productDescript` varchar(2000) NOT NULL,
  `idCategory` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `product`
--

INSERT INTO `product` (`id`, `productName`, `productPrice`, `productImage`, `productDescript`, `idCategory`) VALUES
(1, 'Samsung Galaxy S20 Ultra', 19990000, 'https://cdn.tgdd.vn/Products/Images/42/217937/samsung-galaxy-s20-ultra-400x460-1-400x460.png', 'Samsung Galaxy S20 Ultra siêu phẩm công nghệ hàng đầu của Samsung mới ra mắt với nhiều đột phá công nghệ, màn hình tràn viền không khuyết điểm, hiệu năng đỉnh cao, camera độ phân giải siêu khủng 108 MP cùng khả năng zoom 100X thách thức mọi giới hạn smartphone', 1),
(2, 'OPPO A31', 4990000, 'https://cdn.tgdd.vn/Products/Images/42/217856/oppo-a31-2020-128gb-trang-460x400-400x460.png', 'Chiếc điện thoại OPPO A31 2020 4GB/128 GB nổi bật với thiết kế màn hình giọt nước tràn viền, bộ ba camera ấn tượng đi kèm hiệu năng tốt với mức giá bán cực kỳ phải chăng.', 1),
(3, 'Samsung Galaxy S10 Lite', 15990000, 'https://cdn.tgdd.vn/Products/Images/42/194251/samsung-galaxy-s10-lite-blue-chi-tiet-400x460.png', 'Samsung Galaxy S10 Lite là một phiên bản khác của dòng Galaxy S10 đã ra mắt trước đó nhưng mang trong mình khá nhiều khác biệt về ngoại hình cũng như bên trong.', 1),
(4, 'iPhone 11 64GB', 25990000, 'https://cdn.tgdd.vn/Products/Images/42/153856/iphone-11-red-2-400x460-400x460.png', 'Sau bao nhiêu chờ đợi cũng như đồn đoán thì cuối cùng Apple đã chính thức giới thiệu bộ 3 siêu phẩm iPhone 11 mạnh mẽ nhất của mình vào tháng 9/2019. Có mức giá rẻ nhất nhưng vẫn được nâng cấp mạnh mẽ như chiếc iPhone Xr năm ngoái, đó chính là phiên bản iPhone 11 64GB.', 1),
(5, 'OPPO A91', 15990000, 'https://cdn.tgdd.vn/Products/Images/42/217287/oppo-a91-trang-400x460-400x460.png', 'OPPO A91 là một mẫu smartphone tầm trung mới ra mắt vào cuối năm 2019 của OPPO với nhiều tính năng thú vị, cấu hình ổn cùng giá bán phải chăng.', 1),
(6, 'Xiaomi Redmi 8', 3000000, 'https://cdn.tgdd.vn/Products/Images/42/212212/xiaomi-redmi-8-64gb-red-400x460.png', 'Với nhiều ưu điểm vượt trội so với các đối thủ, Xiaomi Redmi 8 4GB/64GB hứa hẹn là một con bài chiến lược của Xiaomi trong phân khúc smartphone giá rẻ, hiện đang rất sôi động hiện nay.', 1),
(7, 'Vsmart Joy 3', 2700000, 'https://cdn.tgdd.vn/Products/Images/42/217920/vsmart-joy-3-tim-400x460-400x460.png', 'Chiếc điện thoại Vsmart Joy 3 3GB/32GB mang trong mình thiết kế trẻ trung, hiện đại, hiệu năng tốt cùng thời lượng pin lớn với giá bán hấp dẫn phù hợp với đại đa số người dùng. Đây sẽ là chiếc điện thoại gây nhiều chú ý nhất trong thời gian sắp tới.', 1),
(8, 'Realme C3', 2990000, 'https://cdn.tgdd.vn/Products/Images/42/218361/realme-c3-do-400x460-1-400x460.png', 'Realme C3 là chiếc điện thoại giá rẻ với nhiều nâng cấp về thiết kế cấu hình và camera đi kèm mức giá bán cực kỳ cạnh tranh.', 1),
(9, 'Samsung Galaxy Fold', 50000000, 'https://cdn.tgdd.vn/Products/Images/42/198158/samsung-galaxy-fold-black-400x460.png', 'Sau rất nhiều chờ đợi thì Samsung Galaxy Fold - chiếc smartphone màn hình gập đầu tiên của Samsung cũng đã chính thức trình làng với thiết kế mới lạ.', 1),
(10, 'iPhone 11 Pro Max 512GB', 42990000, 'https://cdn.tgdd.vn/Products/Images/42/210654/iphone-11-pro-max-512gb-gold-400x460.png', 'Để tìm kiếm một chiếc smartphone có hiệu năng mạnh mẽ và có thể sử dụng mượt mà trong 2-3 năm tới thì không có chiếc máy nào xứng đang hơn chiếc iPhone 11 Pro Max 512GB mới ra mắt trong năm 2019 của Apple.', 1),
(11, 'iPhone 11 Pro Max 256GB', 35890000, 'https://cdn.tgdd.vn/Products/Images/42/210653/iphone-11-pro-max-256gb-black-400x460.png', 'iPhone 11 Pro Max 256GB là chiếc iPhone cao cấp nhất trong bộ 3 iPhone Apple giới thiệu trong năm 2019 và quả thực chiếc smartphone này mang trong mình nhiều trang bị xứng đáng với tên gọi \"Pro\".', 1),
(12, 'Samsung Galaxy Z Flip', 36000000, 'https://cdn.tgdd.vn/Products/Images/42/213022/samsung-galaxy-z-flip-chitiet-2-788x544.png', 'Cuối cùng sau bao nhiêu thời gian chờ đợi, chiếc điện thoại Samsung Galaxy Z Flip đã được Samsung ra mắt tại sự kiện Unpacked 2020. Siêu phẩm với thiết kế màn hình gập vỏ sò độc đáo, hiệu năng tuyệt đỉnh cùng nhiều công nghệ thời thượng, dẫn dầu 2020.', 1),
(13, 'iPhone 11 Pro Max 64GB', 31890000, 'https://cdn.tgdd.vn/Products/Images/42/200533/iphone-11-pro-max-black-400x460.png', 'Trong năm 2019 thì chiếc smartphone được nhiều người mong muốn sở hữu trên tay và sử dụng nhất không ai khác chính là iPhone 11 Pro Max 64GB tới từ nhà Apple.', 1),
(14, 'iPhone Xs Max 256GB', 31990000, 'https://cdn.tgdd.vn/Products/Images/42/190322/iphone-xs-max-256gb-white-400x460.png', 'Sau 1 năm mong chờ, chiếc smartphone cao cấp nhất của Apple đã chính thức ra mắt mang tên iPhone Xs Max 256 GB. Máy các trang bị các tính năng cao cấp nhất từ chip A12 Bionic, dàn loa đa chiều cho tới camera kép tích hợp trí tuệ nhân tạo.', 1),
(15, 'Asus VivoBook X509FJ i7 8565U', 18990000, 'https://cdn.tgdd.vn/Products/Images/44/207680/asus-vivobook-x509f-i7-8565u-8gb-mx230-win10-ej13-5-2-1-2-1-600x600.jpg', 'Laptop Asus Vivobook X509FJ (EJ133T) là chiếc máy tính xách tay mang đến hiệu năng mạnh mẽ cùng hình ảnh chân thực đáp ứng mọi nhu cầu cho dù là học tập văn phòng hay giải trí.', 2),
(16, 'HP 348 G5 i3 7020U', 9990000, 'https://cdn.tgdd.vn/Products/Images/44/210172/hp-348-g5-i3-7020u-4gb-256gb-win10-7xj62pa-210172-600x600.jpg', 'Với hiệu năng ổn định khi xử lí tác các tác vụ cơ bản như lướt web, xem phim, làm việc văn phòng Word, Excel, Powerpoint,... laptop HP 348 G5 7XJ62PA là lựa chọn phù hợp cho công việc văn phòng, học tập.', 2),
(17, 'Lenovo IdeaPad S145 15IIL i3 1005G1', 10990000, 'https://cdn.tgdd.vn/Products/Images/44/216292/lenovo-ideapad-s145-15iil-i3-1005g1-4gb-256gb-win1-18-600x600.jpg', 'Laptop Lenovo IdeaPad S145 15IIL i3 (81W8001XVN) thuộc phân khúc laptop học tập văn phòng cơ bản với mức giá tốt. Máy có cấu hình ổn, đủ chạy các ứng dụng văn phòng phổ biến, điểm nổi bật của Lenovo IdeaPad S145 là ổ cứng SSD siêu nhanh, thiết kế mỏng gọn, tinh tế.', 2),
(18, 'HP 15s du0116TU i3 7020U', 11190000, 'https://cdn.tgdd.vn/Products/Images/44/216822/hp-15s-du0116tu-i3-7020u-4gb-256gb-win10-8tw28pa-1-600x600.jpg', 'Laptop HP 15s du0116TU i3 (8TW28PA) là chiếc laptop học tập văn phòng có cấu hình trung bình, sử dụng văn phòng, giải trí, học tập ổn định. Điểm cộng của máy so với các laptop cùng phân khúc đó là ổ cứng SSD khởi động cực nhanh, thiết kế sang trọng, mỏng nhẹ dễ di chuyển.', 2),
(19, 'Lenovo Ideapad S540 15IWL i5 8265U', 16490000, 'https://cdn.tgdd.vn/Products/Images/44/210054/lenovo-ideapad-s540-15iwl-i5-8265u-8gb-128gb-1tb-w-18-600x600.jpg', 'Laptop Lenovo Ideapad S540 có màn hình kích thước 15 inch cùng hiệu năng vô cùng mạnh mẽ, là lựa chọn không thể tuyệt vời hơn dành cho các bạn sinh viên hay nhân viên văn phòng đang tìm kiếm một trợ thủ công nghệ.', 2),
(20, 'Acer Aspire A315 54 36QY i3 10110U', 10990000, 'https://cdn.tgdd.vn/Products/Images/44/212625/acer-aspire-a315-54-36qy-i3-10110u-4gb-256gb-win10-1-600x600.jpg', 'Acer Aspire A315 54 36QY (NX.HM2SV.001) là chiếc laptop có thiết kế nhỏ gọn, hợp thời trang, màn hình chân thực, sắc nét cùng cấu hình ổn định đáp ứng nhu cầu học tập, làm việc văn phòng.', 2),
(21, 'Apple MacBook Air 2017 i5', 19990000, 'https://cdn.tgdd.vn/Products/Images/44/106875/apple-macbook-air-mqd32sa-a-i5-5350u-600x600.jpg', 'acBook Air 2017 i5 128GB là mẫu laptop văn phòng, có thiết kế siêu mỏng và nhẹ, vỏ nhôm nguyên khối sang trọng. Máy có hiệu năng ổn định, thời lượng pin cực lâu 12 giờ, phù hợp cho hầu hết các nhu cầu làm việc và giải trí.', 2),
(22, 'HP Pavilion 15 cs3014TU i5 1035G1', 15590000, 'https://cdn.tgdd.vn/Products/Images/44/216085/hp-pavilion-15-cs3014tu-i5-8qp20pa-600x600.jpg', 'Laptop HP Pavilion 15 cs3014TU i5 (8QP20PA) là chiếc máy tính xách tay học tập văn phòng có cấu hình khá, vận hành nhanh, thiết kế gọn nhẹ phù hợp với nhu cầu của người dùng trẻ cần di chuyển nhiều.', 2),
(23, 'Asus VivoBook X409FA i5 8265U', 13690000, 'https://cdn.tgdd.vn/Products/Images/44/206562/asus-vivobook-x409f-i5-8265u-8gb-1tb-win10-ek138t2-1-thumb-1-600x600.jpg', 'Laptop Asus Vivobook X409F (EK138T) là một trong những mẫu laptop mỏng nhẹ có hiệu năng tốt trong phân khúc phổ thông. Bản lề Ergolift thông minh nâng phím tự động, giúp tản nhiệt và khuếch đại âm thanh cũng là một điểm cộng cho sản phẩm này.', 2),
(24, 'Asus VivoBook A412FA i3 8145U', 12990000, 'https://cdn.tgdd.vn/Products/Images/44/203670/asus-vivobook-s412f-i3-8145u-4gb-512gb-ek342t3-1-1-600x600.jpg', 'Asus Vivobook A412F i3 8145U (EK342T) sở hữu hàng loạt các tính năng hiện đại như bảo mật vân tay, bản lề ErgoLift tự nâng phím thông minh. Đây là một trong những chiếc laptop nhỏ gọn phù hợp với đối tượng sinh viên, nhân viên văn phòng cần một chiếc máy tính xách tay xử lý tốt các tác vụ hàng ngày.', 2),
(25, 'Asus VivoBook A412FA i5 10210U', 16990000, 'https://cdn.tgdd.vn/Products/Images/44/217509/asus-vivobook-a412fa-i5-ek738t-600x600.jpg', 'Laptop ASUS VivoBook A412FA i5 (EK738T) là mẫu laptop văn phòng, sinh viên mỏng nhẹ, có cấu hình khỏe với CPU Intel thế hệ mới nhất. Ngoài ra máy còn sở hữu ổ cứng SSD 512 GB siêu nhanh và cảm biến vân tay giúp mở khóa tiện lợi, bảo mật cao.', 2),
(26, 'HP 15s du0072TX i3 7020U', 12290000, 'https://cdn.tgdd.vn/Products/Images/44/216813/hp-15s-du0072tx-i3-7020u-4gb-256gb-2gb-mx110-win10-1-600x600.jpg', 'Laptop HP 15s du0072TX (8WP16PA) là một chiếc laptop văn phòng có cấu hình ổn định cùng với màn hình lớn, viền mỏng hướng đến sinh viên và nhân viên văn phòng thường xuyên mang laptop bên mình.', 2),
(27, 'Acer Aspire A315 34 P3LC N5000', 7790000, 'https://cdn.tgdd.vn/Products/Images/44/211632/acer-aspire-a315-34-p3lc-n5000-4gb-256gb-win10-nx-18-600x600.jpg', 'Laptop Acer Aspire A315 34 P3LC N5000 (NX.HE3SV.004) là sản phẩm laptop học tập - văn phòng tầm trung, có cấu hình vừa phải và mức giá phải chăng. Bên cạnh đó laptop còn sở hữu màn hình 15.6 inch cho không gian hiển thị rộng, làm việc và giải trí tuyệt vời.', 2),
(28, 'Lenovo Ideapad S145 15IWL i3 8145U', 11290000, 'https://cdn.tgdd.vn/Products/Images/44/207798/lenovo-ideapad-s145-15iwl-i3-8145u-4gb-256gb-mx110-18-600x600.jpg', 'Lenovo Ideapad S145 15IWL i3 (81MV00SXVN) là mẫu laptop văn phòng nhỏ gọn, thiết kế đẹp với viền màn hình siêu mỏng. Máy đảm nhận tốt hầu hết các tác vụ văn phòng, thiết kế đồ họa nhờ cấu hình khá và card đồ họa rời MX110.', 2);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
