USE [master]
GO
/****** Object:  Database [jewelryStore]    Script Date: 6/9/2024 2:40:44 PM ******/
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
/****** Object:  Table [dbo].[CartDetail]    Script Date: 6/9/2024 2:40:45 PM ******/
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
/****** Object:  Table [dbo].[Carts]    Script Date: 6/9/2024 2:40:45 PM ******/
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
/****** Object:  Table [dbo].[Category]    Script Date: 6/9/2024 2:40:45 PM ******/
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
/****** Object:  Table [dbo].[OrderDetail]    Script Date: 6/9/2024 2:40:45 PM ******/
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
/****** Object:  Table [dbo].[Orders]    Script Date: 6/9/2024 2:40:45 PM ******/
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
/****** Object:  Table [dbo].[Product]    Script Date: 6/9/2024 2:40:45 PM ******/
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
/****** Object:  Table [dbo].[Status]    Script Date: 6/9/2024 2:40:45 PM ******/
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
/****** Object:  Table [dbo].[User]    Script Date: 6/9/2024 2:40:45 PM ******/
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
