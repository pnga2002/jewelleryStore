create database jewelryStore
--STATUS
CREATE TABLE Status (
    idStatus INT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(50)
);
INSERT INTO Status (name) VALUES ('Doing');
INSERT INTO Status (name) VALUES ('Pending');
INSERT INTO Status (name) VALUES ('Done');
INSERT INTO Status (name) VALUES ('Cancel');

--CATEGORY
CREATE TABLE Category (
    idCategory INT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(50)
);
INSERT INTO Status (name) VALUES ('Nh?n');
INSERT INTO Status (name) VALUES ('Vòng tay');
INSERT INTO Status (name) VALUES ('Dây chuy?n');
INSERT INTO Status (name) VALUES ('Bông tai');

--PRODUCT
CREATE TABLE Product (
    idProduct INT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(255),
	description NVARCHAR(255),
	price float,
	imageUrl VARCHAR(255),
	idCategory int
);
ALTER TABLE Product
ADD CONSTRAINT FK_Product_Category FOREIGN KEY (idCategory) REFERENCES Category(idCategory);

INSERT INTO Product (name, description, price, imageUrl, idCategory) 
VALUES 
('Product 1', 'Description for Product 1', 10, 'http://example.com/product1.jpg', 1),
('Product 2', 'Description for Product 2', 19, 'http://example.com/product2.jpg', 2),
('Product 3', 'Description for Product 3', 29, 'http://example.com/product3.jpg', 1),
('Product 4', 'Description for Product 4', 14, 'http://example.com/product4.jpg', 3);

--APPUSER
CREATE TABLE AppUser (
    idUser INT IDENTITY(1,1) PRIMARY KEY,
    username NVARCHAR(50) NOT NULL,
    password NVARCHAR(20) NOT NULL,
    email NVARCHAR(50),
    role VARCHAR(20),
    phoneNumber VARCHAR(11),
    address NVARCHAR(MAX)
);
INSERT INTO AppUser (username, password, email, role, phoneNumber, address)
VALUES 
('Johndoe', 'password123', 'johndoe@example.com', 'admin', '1234567890', '123 Main St, Anytown, USA'),
('Tom', 'password123', 'tom@example.com', 'admin', '1234567890', '123 Main St, Anytown, USA'),
('Peter', 'password123', 'peter@example.com', 'admin', '1234567890', '123 Main St, Anytown, USA');

--ORDER
CREATE TABLE Orders (
    idOrder INT IDENTITY(1,1) PRIMARY KEY,
    idUser INT NOT NULL,
    orderDate DATE,
    idStatus INT,
    phoneNumber NVARCHAR(20),
    address NVARCHAR(MAX),
    FOREIGN KEY (idUser) REFERENCES AppUser(idUser),
    FOREIGN KEY (idStatus) REFERENCES Status(idStatus),
)

--ORDER DETAIL
CREATE TABLE OrderDetail (
    idDetailOrder INT IDENTITY(1,1) PRIMARY KEY,
    idOrder INT NOT NULL,
    idProduct INT NOT NULL,
    quantity INT,
    price FLOAT,
    FOREIGN KEY (idOrder) REFERENCES Orders(idOrder),
    FOREIGN KEY (idProduct) REFERENCES Product(idProduct)  -- Assuming there is a Products table
);

--CART
CREATE TABLE Carts (
    idCart INT IDENTITY(1,1) PRIMARY KEY,
    idUser INT,
    dateCreate DATE,
    CONSTRAINT FK_User FOREIGN KEY (idUser) REFERENCES AppUser(idUser)
);

--CART DETAIL
CREATE TABLE CartDetail (
    idCartDetail INT IDENTITY(1,1) PRIMARY KEY,
    idCart INT,
    idProduct INT,
    quantity INT,
    CONSTRAINT FK_Cart FOREIGN KEY (idCart) REFERENCES Carts(idCart),
    CONSTRAINT FK_Product FOREIGN KEY (idProduct) REFERENCES Product(idProduct)
);

