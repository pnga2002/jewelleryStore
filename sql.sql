USE [master]
GO
/****** Object:  Database [jewelryStore]    Script Date: 6/30/2024 8:02:58 PM ******/
CREATE DATABASE [jewelryStore]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'jewelryStore', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\jewelryStore.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'jewelryStore_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\jewelryStore_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [jewelryStore] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [jewelryStore].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [jewelryStore] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [jewelryStore] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [jewelryStore] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [jewelryStore] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [jewelryStore] SET ARITHABORT OFF 
GO
ALTER DATABASE [jewelryStore] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [jewelryStore] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [jewelryStore] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [jewelryStore] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [jewelryStore] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [jewelryStore] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [jewelryStore] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [jewelryStore] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [jewelryStore] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [jewelryStore] SET  ENABLE_BROKER 
GO
ALTER DATABASE [jewelryStore] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [jewelryStore] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [jewelryStore] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [jewelryStore] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [jewelryStore] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [jewelryStore] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [jewelryStore] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [jewelryStore] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [jewelryStore] SET  MULTI_USER 
GO
ALTER DATABASE [jewelryStore] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [jewelryStore] SET DB_CHAINING OFF 
GO
ALTER DATABASE [jewelryStore] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [jewelryStore] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [jewelryStore] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [jewelryStore] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [jewelryStore] SET QUERY_STORE = ON
GO
ALTER DATABASE [jewelryStore] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [jewelryStore]
GO
/****** Object:  Table [dbo].[CartDetail]    Script Date: 6/30/2024 8:02:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CartDetail](
	[idCartDetail] [int] IDENTITY(1,1) NOT NULL,
	[idCart] [int] NULL,
	[idProduct] [int] NULL,
	[quantity] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[idCartDetail] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Carts]    Script Date: 6/30/2024 8:02:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Carts](
	[idCart] [int] IDENTITY(1,1) NOT NULL,
	[idUser] [int] NULL,
	[dateCreate] [date] NULL,
PRIMARY KEY CLUSTERED 
(
	[idCart] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Category]    Script Date: 6/30/2024 8:02:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Category](
	[idCategory] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[idCategory] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrderDetail]    Script Date: 6/30/2024 8:02:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderDetail](
	[idDetailOrder] [int] IDENTITY(1,1) NOT NULL,
	[idOrder] [int] NOT NULL,
	[idProduct] [int] NOT NULL,
	[quantity] [int] NULL,
	[price] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[idDetailOrder] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Orders]    Script Date: 6/30/2024 8:02:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[idOrder] [int] IDENTITY(1,1) NOT NULL,
	[idUser] [int] NOT NULL,
	[orderDate] [date] NULL,
	[idStatus] [int] NULL,
	[phoneNumber] [nvarchar](20) NULL,
	[address] [nvarchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[idOrder] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Product]    Script Date: 6/30/2024 8:02:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[idProduct] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](255) NULL,
	[description] [nvarchar](max) NULL,
	[price] [float] NULL,
	[imageUrl] [varchar](255) NULL,
	[idCategory] [int] NULL,
 CONSTRAINT [PK__Product__5EEC79D128A935E2] PRIMARY KEY CLUSTERED 
(
	[idProduct] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Status]    Script Date: 6/30/2024 8:02:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Status](
	[idStatus] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[idStatus] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[User]    Script Date: 6/30/2024 8:02:58 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[User](
	[idUser] [int] IDENTITY(1,1) NOT NULL,
	[username] [nvarchar](50) NOT NULL,
	[password] [nvarchar](20) NOT NULL,
	[email] [nvarchar](50) NULL,
	[role] [varchar](20) NULL,
	[phoneNumber] [varchar](11) NULL,
	[address] [nvarchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[idUser] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[CartDetail] ON 

INSERT [dbo].[CartDetail] ([idCartDetail], [idCart], [idProduct], [quantity]) VALUES (1, 1, 1, 6)
INSERT [dbo].[CartDetail] ([idCartDetail], [idCart], [idProduct], [quantity]) VALUES (2, 1, 3, 3)
INSERT [dbo].[CartDetail] ([idCartDetail], [idCart], [idProduct], [quantity]) VALUES (6, 1, 11, 3)
INSERT [dbo].[CartDetail] ([idCartDetail], [idCart], [idProduct], [quantity]) VALUES (11, 2, 11, 4)
SET IDENTITY_INSERT [dbo].[CartDetail] OFF
GO
SET IDENTITY_INSERT [dbo].[Carts] ON 

INSERT [dbo].[Carts] ([idCart], [idUser], [dateCreate]) VALUES (1, 1, CAST(N'1970-01-01' AS Date))
INSERT [dbo].[Carts] ([idCart], [idUser], [dateCreate]) VALUES (2, 2, CAST(N'2024-06-22' AS Date))
INSERT [dbo].[Carts] ([idCart], [idUser], [dateCreate]) VALUES (3, 3, CAST(N'2024-06-30' AS Date))
SET IDENTITY_INSERT [dbo].[Carts] OFF
GO
SET IDENTITY_INSERT [dbo].[Category] ON 

INSERT [dbo].[Category] ([idCategory], [name]) VALUES (1, N'Nhẫn')
INSERT [dbo].[Category] ([idCategory], [name]) VALUES (2, N'Vòng tay')
INSERT [dbo].[Category] ([idCategory], [name]) VALUES (3, N'Dây chuyền')
INSERT [dbo].[Category] ([idCategory], [name]) VALUES (4, N'Bông tai')
INSERT [dbo].[Category] ([idCategory], [name]) VALUES (5, N'Khuyên tai')
SET IDENTITY_INSERT [dbo].[Category] OFF
GO
SET IDENTITY_INSERT [dbo].[OrderDetail] ON 

INSERT [dbo].[OrderDetail] ([idDetailOrder], [idOrder], [idProduct], [quantity], [price]) VALUES (1, 1, 1, 1, 800)
INSERT [dbo].[OrderDetail] ([idDetailOrder], [idOrder], [idProduct], [quantity], [price]) VALUES (2, 2, 3, 1, 1500)
INSERT [dbo].[OrderDetail] ([idDetailOrder], [idOrder], [idProduct], [quantity], [price]) VALUES (3, 2, 1, 2, 800)
INSERT [dbo].[OrderDetail] ([idDetailOrder], [idOrder], [idProduct], [quantity], [price]) VALUES (4, 13, 6, 9, 430)
INSERT [dbo].[OrderDetail] ([idDetailOrder], [idOrder], [idProduct], [quantity], [price]) VALUES (5, 14, 4, 5, 525)
INSERT [dbo].[OrderDetail] ([idDetailOrder], [idOrder], [idProduct], [quantity], [price]) VALUES (6, 14, 7, 4, 530)
INSERT [dbo].[OrderDetail] ([idDetailOrder], [idOrder], [idProduct], [quantity], [price]) VALUES (7, 15, 5, 3, 430)
INSERT [dbo].[OrderDetail] ([idDetailOrder], [idOrder], [idProduct], [quantity], [price]) VALUES (8, 16, 4, 3, 525)
INSERT [dbo].[OrderDetail] ([idDetailOrder], [idOrder], [idProduct], [quantity], [price]) VALUES (9, 17, 3, 3, 890)
INSERT [dbo].[OrderDetail] ([idDetailOrder], [idOrder], [idProduct], [quantity], [price]) VALUES (10, 18, 4, 1, 525)
INSERT [dbo].[OrderDetail] ([idDetailOrder], [idOrder], [idProduct], [quantity], [price]) VALUES (11, 18, 9, 1, 981)
SET IDENTITY_INSERT [dbo].[OrderDetail] OFF
GO
SET IDENTITY_INSERT [dbo].[Orders] ON 

INSERT [dbo].[Orders] ([idOrder], [idUser], [orderDate], [idStatus], [phoneNumber], [address]) VALUES (1, 1, CAST(N'2024-06-09' AS Date), 2, N'0944057053', N'112 cao thắng')
INSERT [dbo].[Orders] ([idOrder], [idUser], [orderDate], [idStatus], [phoneNumber], [address]) VALUES (2, 1, CAST(N'2024-06-09' AS Date), 4, N'0944057053', N'112 cao thắng')
INSERT [dbo].[Orders] ([idOrder], [idUser], [orderDate], [idStatus], [phoneNumber], [address]) VALUES (13, 1, CAST(N'2024-06-16' AS Date), 2, N'1234567890', N'123 Main St, Anytown, USA')
INSERT [dbo].[Orders] ([idOrder], [idUser], [orderDate], [idStatus], [phoneNumber], [address]) VALUES (14, 1, CAST(N'2024-06-17' AS Date), 4, N'1234567890', N'123 Main St, Anytown, USA')
INSERT [dbo].[Orders] ([idOrder], [idUser], [orderDate], [idStatus], [phoneNumber], [address]) VALUES (15, 2, CAST(N'2024-06-22' AS Date), 2, N'1234567890', N'123 Main St, Anytown, USA')
INSERT [dbo].[Orders] ([idOrder], [idUser], [orderDate], [idStatus], [phoneNumber], [address]) VALUES (16, 2, CAST(N'2024-06-22' AS Date), 2, N'1234567890', N'123 Main St, Anytown, USA')
INSERT [dbo].[Orders] ([idOrder], [idUser], [orderDate], [idStatus], [phoneNumber], [address]) VALUES (17, 3, CAST(N'2024-06-30' AS Date), 2, N'0944057053', N'112 cao thắng')
INSERT [dbo].[Orders] ([idOrder], [idUser], [orderDate], [idStatus], [phoneNumber], [address]) VALUES (18, 2, CAST(N'2024-06-30' AS Date), 1, N'1234567890', N'123 Main St, Anytown, USA')
SET IDENTITY_INSERT [dbo].[Orders] OFF
GO
SET IDENTITY_INSERT [dbo].[Product] ON 

INSERT [dbo].[Product] ([idProduct], [name], [description], [price], [imageUrl], [idCategory]) VALUES (1, N'Dây Chuyền Bạc Nữ Đính Đá CZ Cá Tiên LILI_831944', N'Bạn sẽ không chỉ thêm phần xinh xắn và thanh lịch khi diện em dây chuyền bạc với thiết kế cá tiên độc đáo này, mà còn thể hiện gu thẩm mỹ rất riêng đấy nhé. Hãy tưởng tượng bạn sẽ duyên dáng và thu hút làm sao khi bạn diện chiếc vòng cổ này đi làm, đi hẹn hò hay đi chơi với bạn bè. Em nó được làm từ bạc 925 chuyên dụng , điểm nhấn bởi những viên đá Cubic Zirconia cao cấp và được chế tác hết sức tỉ mỉ bởi những nghệ nhân lành nghề. Cùng em nó ra ngoài và tỏa sáng thôi nào !!', 818, N'https://lili.vn/wp-content/uploads/2021/12/Day-chuyen-bac-nu-phong-cach-co-trang-CZ-LILI_831944_1.jpg', 2)
INSERT [dbo].[Product] ([idProduct], [name], [description], [price], [imageUrl], [idCategory]) VALUES (3, N'Dây Chuyền Đôi Bạc Tình Yêu Tình Bạn Thân BFF Đính Đá CZ Forever Love LILI_528145', N'Một trong số những thiết kế trang sức đôi tuyệt vời của trang sức LiLi, dây chuyền cặp đôi LILI_528145 được làm từ bạc 92.5% nguyên chất đính đá Cubic Zirconia cao cấp. Với thiết kế lấy cảm hứng từ biển cả, tượng trưng cho một tình yêu vĩnh cửu, dây chuyền đôi này là sự lựa chọn tuyệt vời cho những cặp đôi đang yêu nhau như một món kỷ vật theo các bạn đi cùng năm tháng, cùng tình yêu dài lâu. Em nó cũng có thể là món quà tuyệt vời mà chàng hay nàng dành cho nhau. Các bạn trông sẽ thật hạnh phúc và tỏa sáng đó !!', 890, N'https://lili.vn/wp-content/uploads/2021/08/Day-chuyen-doi-bac-hinh-ca-heo-hong-Forever-Love-LILI_528145_1.jpg', 1)
INSERT [dbo].[Product] ([idProduct], [name], [description], [price], [imageUrl], [idCategory]) VALUES (4, N'NHẪN THẦN THÚ THAO THIẾT', N'Mộc sự kết hợp hoàn hảo giữa đơn giản, phong cách và cá tính. Với thiết kế tối giản nhưng không kém phần cuốn hút, Mộc mang đến cho bạn sự tự do trong việc thể hiện phong cách riêng của mình. Sản phẩm được chế tác từ Titanium mix Bạc 925, tạo nên một vẻ đẹp độc đáo và bền bỉ. Sự kết hợp giữa Titanium và Bạc 925 tạo nên một hiệu ứng tương phản độc đáo, thể hiện sự cân bằng giữa sự cứng cáp và vẻ mềm mại.', 525, N'https://content.pancake.vn/2/s1800x2250/fwebp/2023/8/8/3c31f3f20e8762f8f45a44a3495cb9c8e8e39311.jpg', 1)
INSERT [dbo].[Product] ([idProduct], [name], [description], [price], [imageUrl], [idCategory]) VALUES (5, N'Khuyên tai SKULL', N'Sản phẩm được làm từ chất liệu Titanium mix Bạc 925, đã được xử lý kích ứng nên không gây dị ứng khi đeo và rất an toàn cho da.', 430, N'https://content.pancake.vn/2/s1800x2250/fwebp/2022/9/21/9ee2e4295ae0d76a1de29528cc572489de0e3886.jpg', 5)
INSERT [dbo].[Product] ([idProduct], [name], [description], [price], [imageUrl], [idCategory]) VALUES (6, N'Khuyên tai Wings', N'Sản phẩm được làm từ chất liệu Titanium mix Bạc 925, đã được xử lý kích ứng nên không gây dị ứng khi đeo và rất an toàn cho da.', 430, N'https://content.pancake.vn/2/s1800x2250/fwebp/2022/9/21/6e7f0e95a773e7c7a11ed53166cecd9b43ce7e5d.jpg', 5)
INSERT [dbo].[Product] ([idProduct], [name], [description], [price], [imageUrl], [idCategory]) VALUES (7, N'Khuyên tai ROMAN NUMERALS EARRING', N'Sản phẩm được làm từ chất liệu Titanium mix Bạc 925, đã được xử lý kích ứng nên không gây dị ứng khi đeo và rất an toàn cho da.', 530, N'https://content.pancake.vn/2/s1800x2250/fwebp/2023/5/23/6cbc49ae364407a6f0a9120ccb8003140a1a6e56.jpg', 5)
INSERT [dbo].[Product] ([idProduct], [name], [description], [price], [imageUrl], [idCategory]) VALUES (8, N'Dây Chuyền Bạc Nữ Dạng Lồng Đóng Mở Hình Động Vật Chứa Đá - Mèo', N'Bạn sẽ không chỉ thêm phần xinh xắn và thanh lịch khi diện em dây chuyền dạng lồng này, mà còn thể hiện gu thẩm mỹ rất riêng đó. Đặc biệt nếu bạn là người yêu quý động vật. Hãy tưởng tượng bạn sẽ duyên dáng và thu hút làm sao khi bạn diện chiếc vòng cổ này đi làm, đi hẹn hò hay đi chơi với bạn bè. Dây chuyền bạc nữ dạng lồng đóng mở hình động vật chứa đá CZ LILI_975577 được làm từ bạc 925 chuyên dụng, điểm nhấn bởi viên đá Cubic Zirconia cao cấp bên trong và được chế tác hết sức tỉ mỉ bởi những nghệ nhân lành nghề. Bạn cũng có thể dễ dàng tự thay đổi viên đá CZ bên trong bằng ngọc trai hoặc đá quý khác mà bạn. Cùng em nó ra ngoài và tỏa sáng thôi nào !!', 981, N'https://lili.vn/wp-content/uploads/2021/12/Day-chuyen-bac-nu-dang-long-dong-mo-hinh-Con-meo-LILI_975577_6-768x768.jpg', 3)
INSERT [dbo].[Product] ([idProduct], [name], [description], [price], [imageUrl], [idCategory]) VALUES (9, N'Dây Chuyền Bạc Nữ Dạng Lồng Đóng Mở Hình Động Vật Chứa Đá - Voi', N'Bạn sẽ không chỉ thêm phần xinh xắn và thanh lịch khi diện em dây chuyền dạng lồng này, mà còn thể hiện gu thẩm mỹ rất riêng đó. Đặc biệt nếu bạn là người yêu quý động vật. Hãy tưởng tượng bạn sẽ duyên dáng và thu hút làm sao khi bạn diện chiếc vòng cổ này đi làm, đi hẹn hò hay đi chơi với bạn bè. Dây chuyền bạc nữ dạng lồng đóng mở hình động vật chứa đá CZ LILI_975577 được làm từ bạc 925 chuyên dụng, điểm nhấn bởi viên đá Cubic Zirconia cao cấp bên trong và được chế tác hết sức tỉ mỉ bởi những nghệ nhân lành nghề. Bạn cũng có thể dễ dàng tự thay đổi viên đá CZ bên trong bằng ngọc trai hoặc đá quý khác mà bạn. Cùng em nó ra ngoài và tỏa sáng thôi nào !!', 981, N'https://lili.vn/wp-content/uploads/2021/12/Day-chuyen-bac-nu-dang-long-dong-mo-hinh-Voi-con-LILI_975577_7-768x768.jpg', 3)
INSERT [dbo].[Product] ([idProduct], [name], [description], [price], [imageUrl], [idCategory]) VALUES (10, N'Dây Chuyền Bạc Nữ Dạng Lồng Đóng Mở Hình Động Vật Chứa Đá - Cá nhỏ', N'Bạn sẽ không chỉ thêm phần xinh xắn và thanh lịch khi diện em dây chuyền dạng lồng này, mà còn thể hiện gu thẩm mỹ rất riêng đó. Đặc biệt nếu bạn là người yêu quý động vật. Hãy tưởng tượng bạn sẽ duyên dáng và thu hút làm sao khi bạn diện chiếc vòng cổ này đi làm, đi hẹn hò hay đi chơi với bạn bè. Dây chuyền bạc nữ dạng lồng đóng mở hình động vật chứa đá CZ LILI_975577 được làm từ bạc 925 chuyên dụng, điểm nhấn bởi viên đá Cubic Zirconia cao cấp bên trong và được chế tác hết sức tỉ mỉ bởi những nghệ nhân lành nghề. Bạn cũng có thể dễ dàng tự thay đổi viên đá CZ bên trong bằng ngọc trai hoặc đá quý khác mà bạn. Cùng em nó ra ngoài và tỏa sáng thôi nào !!', 981, N'https://lili.vn/wp-content/uploads/2021/12/Day-chuyen-bac-nu-dang-long-dong-mo-hinh-Ca-nho-LILI_975577_9.jpg', 3)
INSERT [dbo].[Product] ([idProduct], [name], [description], [price], [imageUrl], [idCategory]) VALUES (11, N'Dây Chuyền Bạc Nữ Dạng Lồng Đóng Mở Hình Động Vật Chứa Đá - Cú mèo', N'Bạn sẽ không chỉ thêm phần xinh xắn và thanh lịch khi diện em dây chuyền dạng lồng này, mà còn thể hiện gu thẩm mỹ rất riêng đó. Đặc biệt nếu bạn là người yêu quý động vật. Hãy tưởng tượng bạn sẽ duyên dáng và thu hút làm sao khi bạn diện chiếc vòng cổ này đi làm, đi hẹn hò hay đi chơi với bạn bè. Dây chuyền bạc nữ dạng lồng đóng mở hình động vật chứa đá CZ LILI_975577 được làm từ bạc 925 chuyên dụng, điểm nhấn bởi viên đá Cubic Zirconia cao cấp bên trong và được chế tác hết sức tỉ mỉ bởi những nghệ nhân lành nghề. Bạn cũng có thể dễ dàng tự thay đổi viên đá CZ bên trong bằng ngọc trai hoặc đá quý khác mà bạn. Cùng em nó ra ngoài và tỏa sáng thôi nào !!', 981, N'https://lili.vn/wp-content/uploads/2021/12/Day-chuyen-bac-nu-dang-long-dong-mo-hinh-Cu-meo-LILI_975577_8.jpg', 3)
SET IDENTITY_INSERT [dbo].[Product] OFF
GO
SET IDENTITY_INSERT [dbo].[Status] ON 

INSERT [dbo].[Status] ([idStatus], [name]) VALUES (1, N'Pendingg')
INSERT [dbo].[Status] ([idStatus], [name]) VALUES (2, N'Doing')
INSERT [dbo].[Status] ([idStatus], [name]) VALUES (3, N'Done')
INSERT [dbo].[Status] ([idStatus], [name]) VALUES (4, N'Cancel')
SET IDENTITY_INSERT [dbo].[Status] OFF
GO
SET IDENTITY_INSERT [dbo].[User] ON 

INSERT [dbo].[User] ([idUser], [username], [password], [email], [role], [phoneNumber], [address]) VALUES (1, N'Johndoe', N'password123', N'johndoe@example.com', N'admin', N'12345678902', N'123 Main St, Anytown, USA')
INSERT [dbo].[User] ([idUser], [username], [password], [email], [role], [phoneNumber], [address]) VALUES (2, N'Tom', N'password123', N'tom@example.com', N'admin', N'1234567890', N'123 Main St, Anytown, USA')
INSERT [dbo].[User] ([idUser], [username], [password], [email], [role], [phoneNumber], [address]) VALUES (3, N'Peter', N'password123', N'peter@example.com', N'user', N'1234567890', N'123 Main St, Anytown, USA')
INSERT [dbo].[User] ([idUser], [username], [password], [email], [role], [phoneNumber], [address]) VALUES (4, N'pngaa', N'111111', N'pnga@gmail.com', N'employ', N'0944057053', N'112 cao thắng quận 3 HCM')
SET IDENTITY_INSERT [dbo].[User] OFF
GO
ALTER TABLE [dbo].[CartDetail]  WITH CHECK ADD  CONSTRAINT [FK_Cart] FOREIGN KEY([idCart])
REFERENCES [dbo].[Carts] ([idCart])
GO
ALTER TABLE [dbo].[CartDetail] CHECK CONSTRAINT [FK_Cart]
GO
ALTER TABLE [dbo].[CartDetail]  WITH CHECK ADD  CONSTRAINT [FK_Product] FOREIGN KEY([idProduct])
REFERENCES [dbo].[Product] ([idProduct])
GO
ALTER TABLE [dbo].[CartDetail] CHECK CONSTRAINT [FK_Product]
GO
ALTER TABLE [dbo].[Carts]  WITH CHECK ADD  CONSTRAINT [FK_User] FOREIGN KEY([idUser])
REFERENCES [dbo].[User] ([idUser])
GO
ALTER TABLE [dbo].[Carts] CHECK CONSTRAINT [FK_User]
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD FOREIGN KEY([idOrder])
REFERENCES [dbo].[Orders] ([idOrder])
GO
ALTER TABLE [dbo].[OrderDetail]  WITH CHECK ADD  CONSTRAINT [FK__OrderDeta__idPro__571DF1D5] FOREIGN KEY([idProduct])
REFERENCES [dbo].[Product] ([idProduct])
GO
ALTER TABLE [dbo].[OrderDetail] CHECK CONSTRAINT [FK__OrderDeta__idPro__571DF1D5]
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD FOREIGN KEY([idStatus])
REFERENCES [dbo].[Status] ([idStatus])
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD FOREIGN KEY([idUser])
REFERENCES [dbo].[User] ([idUser])
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD  CONSTRAINT [FK_Product_Category] FOREIGN KEY([idCategory])
REFERENCES [dbo].[Category] ([idCategory])
GO
ALTER TABLE [dbo].[Product] CHECK CONSTRAINT [FK_Product_Category]
GO
USE [master]
GO
ALTER DATABASE [jewelryStore] SET  READ_WRITE 
GO
