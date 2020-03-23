-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th3 06, 2020 lúc 01:05 PM
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
-- Cơ sở dữ liệu: `thietbi`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaisanpham`
--

CREATE TABLE `loaisanpham` (
  `id` int(11) NOT NULL,
  `tenloaisanpham` varchar(200) NOT NULL,
  `hinhanh` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `loaisanpham`
--

INSERT INTO `loaisanpham` (`id`, `tenloaisanpham`, `hinhanh`) VALUES
(1, 'Điện thoại', 'https://cdn.tgdd.vn/Products/Images/42/211161/realme-5-128gb-blue-400x460.png'),
(2, 'Laptop', 'https://www.anphatpc.com.vn/media/product/29950_vivobook_a512da_ej406t_1.jpg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `id` int(11) NOT NULL,
  `productName` varchar(200) NOT NULL,
  `productPrice` int(15) NOT NULL,
  `productImage` varchar(200) NOT NULL,
  `productDescript` varchar(10000) NOT NULL,
  `categoryID` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`id`, `productName`, `productPrice`, `productImage`, `productDescript`, `categoryID`) VALUES
(1, 'Iphone 7 Plus 256GB', 19900000, 'https://cdn.tgdd.vn/Products/Images/42/78124/iphone-7-plus-32gb-gold-600x600-400x400.jpg', 'Điện thoại của Apple', 1),
(2, 'Galaxy S10', 21000000, 'https://cdn.tgdd.vn/Products/Images/42/179530/samsung-galaxy-s10-plus-black-600x600.jpg', 'Điện thoại của Samsung', 1),
(3, 'Điện thoại OPPO A5 (2020) 64GB', 3990000, 'https://cdn.tgdd.vn/Products/Images/42/210441/oppo-a5-2020-1-400x460.png', 'OPPO A5 (2020) 64GB là mẫu smartphone tầm trung với giá thành phải chăng nhưng được trang bị nhiều công nghệ hấp dẫn hứa hẹn sẽ \"lấy được lòng\" các bạn trẻ năng động, thời trang.', 1),
(4, 'Điện thoại Samsung Galaxy Note 10', 22990000, 'https://cdn.tgdd.vn/Products/Images/42/191276/samsung-galaxy-note-10-silver-400x460.png', 'Nếu như từ trước tới nay dòng Galaxy Note của Samsung thường ít được các bạn nữ sử dụng bởi kích thước màn hình khá lớn khiến việc cầm nắm trở nên khó khăn thì Samsung Galaxy Note 10 sẽ là chiếc smartphone nhỏ gọn, phù hợp với cả những bạn có bàn tay nhỏ.', 1),
(5, 'Điện thoại Samsung Galaxy A01', 2490000, 'https://cdn.tgdd.vn/Products/Images/42/214908/samsung-galaxy-a01-400x460-3-400x460.png', 'Samsung Galaxy A01 là một smartphone nhà Samsung mới được ra mắt vào đầu năm 2020. Chiếc điện thoại nổi bật với camera kép, màn hình Infinity-V tràn cạnh, chạy hệ điều hành Android 10 mới nhất đi kèm mức giá bán cực kỳ hấp dẫn.', 1),
(6, 'Điện thoại iPhone 11 64GB', 21990000, 'https://cdn.tgdd.vn/Products/Images/42/153856/iphone-11-red-2-400x460-400x460.png', 'Sau bao nhiêu chờ đợi cũng như đồn đoán thì cuối cùng Apple đã chính thức giới thiệu bộ 3 siêu phẩm iPhone 11 mạnh mẽ nhất của mình vào tháng 9/2019. Có mức giá rẻ nhất nhưng vẫn được nâng cấp mạnh mẽ như chiếc iPhone Xr năm ngoái, đó chính là phiên bản iPhone 11 64GB.', 1),
(7, 'Điện thoại OPPO A9 (2020)', 5990000, 'https://cdn.tgdd.vn/Products/Images/42/202028/oppo-a9-white-400x460.png', 'Kế thừa phiên bản OPPO A7 đã từng gây hot trước đó, OPPO A9 (2020) có nhiều sự cải tiến hơn về màn hình, camera và hiệu năng trải nghiệm.', 1),
(8, 'Điện thoại Vsmart Joy 3 (3GB/32GB)', 2690000, 'https://cdn.tgdd.vn/Products/Images/42/217920/vsmart-joy-3-tim-400x460-1-400x460.png', 'Chiếc điện thoại Vsmart Joy 3 3GB/32GB mang trong mình thiết kế trẻ trung, hiện đại, hiệu năng tốt cùng thời lượng pin lớn với giá bán hấp dẫn phù hợp với đại đa số người dùng. Đây sẽ là chiếc điện thoại gây nhiều chú ý nhất trong thời gian sắp tới.', 1),
(9, 'Điện thoại Xiaomi Redmi 8 (4GB/64GB)', 3390000, 'https://cdn.tgdd.vn/Products/Images/42/212212/xiaomi-redmi-8-64gb-red-1-400x460.png', 'Với nhiều ưu điểm vượt trội so với các đối thủ, Xiaomi Redmi 8 4GB/64GB hứa hẹn là một con bài chiến lược của Xiaomi trong phân khúc smartphone giá rẻ, hiện đang rất sôi động hiện nay.\r\n', 1),
(10, 'Điện thoại Realme 5s', 4590000, 'https://cdn.tgdd.vn/Products/Images/42/213588/realme-5s-red-400x460.png', 'Thêm 1 sự lựa chọn cho tầm giá dưới 5 triệu, chiếc điện thoại Realme 5s hội đủ nhiều phẩm chất cho một smartphone \"ngon\" phù hợp cho nhiều đối tượng sử dụng, đặc biệt nhất là yêu thích nhiếp ảnh.', 1),
(11, 'Điện thoại Samsung Galaxy Fold', 50000000, 'https://cdn.tgdd.vn/Products/Images/42/198158/samsung-galaxy-fold-black-400x460.png', 'Sau rất nhiều chờ đợi thì Samsung Galaxy Fold - chiếc smartphone màn hình gập đầu tiên của Samsung cũng đã chính thức trình làng với thiết kế mới lạ.', 1),
(12, 'Laptop HP 348 G5 i3 7020U', 9990000, 'https://cdn.tgdd.vn/Products/Images/44/210172/hp-348-g5-i3-7020u-4gb-256gb-win10-7xj62pa-210172-600x600.jpg', 'Với hiệu năng ổn định khi xử lí tác các tác vụ cơ bản như lướt web, xem phim, làm việc văn phòng Word, Excel, Powerpoint,... laptop HP 348 G5 7XJ62PA là lựa chọn phù hợp cho công việc văn phòng, học tập.', 2),
(13, 'Laptop Asus VivoBook X409U i3 7020U', 10790000, 'https://cdn.tgdd.vn/Products/Images/44/213862/asus-vivobook-x409u-i3-7020u-4gb-256gb-win10-ek20-1-600x600.jpg', 'Laptop Asus VivoBook X409U (EK205T) là chiếc laptop có thiết kế nhỏ gọn, hợp thời trang, màn hình chân thực, sắc nét cùng cấu hình ổn định đáp ứng nhu cầu học tập, làm việc văn phòng.  Đây là một sự lựa chọn dành cho sinh viên nhân viên văn phòng với nhu cầu học tập, văn phòng và giải trí cơ bản.', 2),
(14, 'Laptop Asus VivoBook X409FA i3 8145U', 12390000, 'https://cdn.tgdd.vn/Products/Images/44/212940/asus-vivobook-x409fa-i3-8145u-4gb-512gb-win10-ek3-15-600x600.jpg', 'Nếu bạn đang tìm kiếm một chiếc laptop học tập văn phòng có mức giá rẻ nhưng vẫn có một cấu hình đủ để giải quyết công việc cũng như giải trí hằng ngày thì ASUS VivoBook X409FA i3 (EK306T) chính là một lựa chọn hợp lí. Máy có kiểu dáng gọn nhẹ, hài hòa, hiệu năng ổn định và nhiều tính năng hữu ích.', 2),
(15, 'Laptop Asus VivoBook A412FA i5 8265U', 16490000, 'https://cdn.tgdd.vn/Products/Images/44/212657/asus-vivobook-a412fa-i5-8265u-8gb-32gb-512gb-win10-1-600x600.jpg', 'Laptop Asus VivoBook A412FA đáp ứng được nhu cầu văn phòng và đồ họa kĩ thuật. Với trọng lượng siêu nhẹ 1.5 kg, bạn thoải mái mang theo cả ngày mà chẳng thấy nặng tay.', 2),
(16, 'Laptop Apple MacBook Air 2017 i5 1.8GH', 19990000, 'https://cdn.tgdd.vn/Products/Images/44/106875/apple-macbook-air-mqd32sa-a-i5-5350u-600x600.jpg', 'MacBook Air 2017 i5 128GB là mẫu laptop văn phòng, có thiết kế siêu mỏng và nhẹ, vỏ nhôm nguyên khối sang trọng. Máy có hiệu năng ổn định, thời lượng pin cực lâu 12 giờ, phù hợp cho hầu hết các nhu cầu làm việc và giải trí.', 2),
(17, 'Laptop Lenovo IdeaPad S145 15IKB i3 7020U', 10290000, 'https://cdn.tgdd.vn/Products/Images/44/211882/lenovo-ideapad-15ikb-i3-7020u-4gb-256gb-win10-81v-1-600x600.jpg', 'Lenovo IdeaPad 15IKB (81VD0035VN) là mẫu laptop dành cho sinh viên, nhân viên văn phòng với thiết kế hiện đại, trẻ trung và cấu hình sử dụng mượt mà các tác vụ như soạn thảo văn bản, lướt web, nghe nhạc,...', 2),
(18, 'Laptop HP 15s du1039TX i7 10510U', 18990000, 'https://cdn.tgdd.vn/Products/Images/44/217270/hp-15s-du1039tx-i7-10510u-8gb-512gb-2gb-mx130-win1-600x600.jpg', 'Laptop HP 15s du1039TX (8RK39PA) là chiếc laptop đáp ứng tốt mọi nhu cầu từ giải trí đến làm việc nhờ có chip Core i7 thế hệ 10, ổ cứng SSD 512 GB cực nhanh. Chiếc laptop này sẽ là trợ thủ đắc lực cho các bạn học sinh, sinh viên theo học ngành thiết kế đồ hoạ.', 2),
(19, 'Laptop Lenovo Ideapad S145 15IWL i5 8265U', 15290000, 'https://cdn.tgdd.vn/Products/Images/44/207744/lenovo-ideapad-s145-15iwl-i5-8265u-8gb-256gb-mx110-19-600x600.jpg', 'Laptop Lenovo IdeaPad S145 15IWL (81MV00T9VN) vừa được ra mắt đem đến cho giới văn phòng, sinh viên có thêm sự lựa chọn tốt. Đây là chiếc laptop văn phòng được cài đặt  sẵn windown 10, máy có hiệu năng cao cùng các tính năng hiện đại giúp bạn hoàn thành tốt công việc mỗi ngày.', 2),
(20, 'Laptop Asus VivoBook X509FJ i5 8265U', 15990000, 'https://cdn.tgdd.vn/Products/Images/44/207846/asus-vivobook-x509fj-i5-8265u-8gb-16gb-1tb-2gb-mx2-17-600x600.jpg', 'Laptop ASUS VivoBook X509FJ i5 (EJ132T) vừa được ra mắt đem đến cho người dùng thêm sự lựa chọn cho những ai đang tìm kiếm một sản phẩm laptop văn phòng có hiệu năng mạnh mẽ, chạy mượt ứng dụng văn phòng, đồ họa và có thể giải trí với một số game phổ biến hiện nay.', 2),
(21, 'Laptop Lenovo Ideapad S145 15IWL i3 8145U', 11790000, 'https://cdn.tgdd.vn/Products/Images/44/207798/lenovo-ideapad-s145-15iwl-i3-8145u-4gb-256gb-mx110-18-600x600.jpg', 'Lenovo Ideapad S145 15IWL i3 (81MV00SXVN) là mẫu laptop văn phòng nhỏ gọn, thiết kế đẹp với viền màn hình siêu mỏng. Máy đảm nhận tốt hầu hết các tác vụ văn phòng, thiết kế đồ họa nhờ cấu hình khá và card đồ họa rời MX110.', 2);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `loaisanpham`
--
ALTER TABLE `loaisanpham`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `loaisanpham`
--
ALTER TABLE `loaisanpham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
