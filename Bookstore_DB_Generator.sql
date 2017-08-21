drop Database BookStore
Create Database BookStore
go

Use BookStore
go

create table Author (
	AuthorID int identity primary key,
	FirstName varchar(50) not null,
	LastName varchar(50) not null,
	Initials varchar(10))
go

create index ixAuthor
	on author(FirstName, LastName)
go

create table Addresses (
	AddressID int identity primary key,
	StreetAddress varchar(100) not null,
	City varchar(50) not null,
	StateProvence varchar(50) not null,
	Country varchar(50) default ('USA'),
	ZipCode int not null
		check ([ZipCode]>0))
go

create index ixAddresses
	on Addresses(AddressID)
go

create table BookStore(
	BookStoreID int identity primary key,
	BookStoreName varchar(200) not null,
	AddressID int not null references Addresses,
	PhoneNumber varchar(20))
go

create table Genre(
	GenreID int identity primary key,
	Genre varchar(20))
go

create unique index UQ__Genre__F1410CF37D1984AB
	on Genre (Genre)
go

create table Publisher(
	PublisherID int identity primary key,
	PublisherName varchar(200) not null,
	AddressID int not null references Addresses)
go

create table Publisher_BookStore_Bridge (
	PublisherID int references Publisher,
	BookStoreID int references BookStore)
go

create table Book (
	BookID int identity primary key,
	BookTitle varchar(50) not null,
	ISBN varchar(20) unique not null
		check(ISBN like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),
	GenreID int references Genre(GenreID),
	ReleaseDate date,
	PublisherID int references Publisher(PublisherID),
	ListPrice float(53) default ((0.00)),
	AuthorID int references Author(AuthorID),
	BookDescription varchar(max))
go

create unique index Book_ISBN_uindex
	on Book (ISBN)
go

create unique index ID
	on Book (BookTitle)
go

create view vDisplay as(
select b.BookID, b.BookTitle, a.FirstName, a.LastName, a.Initials
	,g.Genre, b.ISBN, p.PublisherName, b.ReleaseDate, b.listprice, b.BookDescription
from Book as b
inner join Author as a
on a.AuthorID = b.AuthorID
inner join Genre as g
on g.GenreID = b.GenreID
inner join Publisher as p
on b.PublisherID = p.PublisherID)
go
-------------------------------------------------------------------
-- Adding initial Data to the Database
-------------------------------------------------------------------
--Address Data
INSERT INTO BookStore.dbo.Addresses (StreetAddress, City, StateProvence, Country, ZipCode) VALUES ('4601 26th Ave NE', 'Seattle', 'WA', 'USA', 98105);
INSERT INTO BookStore.dbo.Addresses (StreetAddress, City, StateProvence, Country, ZipCode) VALUES ('626 106th Ave NE', 'Bellevue', 'WA', 'USA', 98004);
INSERT INTO BookStore.dbo.Addresses (StreetAddress, City, StateProvence, Country, ZipCode) VALUES ('14700 E Indiana Ave', 'Spokane Valley', 'WA', 'USA', 99216);
INSERT INTO BookStore.dbo.Addresses (StreetAddress, City, StateProvence, Country, ZipCode) VALUES ('4027 Tacoma Mall Blvd', 'Tacoma', 'WA', 'USA', 98409);
INSERT INTO BookStore.dbo.Addresses (StreetAddress, City, StateProvence, Country, ZipCode) VALUES ('1005 W Burnside St. ', 'Portland', 'OR', 'USA', 97209);
INSERT INTO BookStore.dbo.Addresses (StreetAddress, City, StateProvence, Country, ZipCode) VALUES ('1754 Pacific Ave', 'Tacoma', 'WA', 'USA', 98402);
INSERT INTO BookStore.dbo.Addresses (StreetAddress, City, StateProvence, Country, ZipCode) VALUES ('208 Garfield St S #101', 'Tacoma', 'WA', 'USA', 98444);
INSERT INTO BookStore.dbo.Addresses (StreetAddress, City, StateProvence, Country, ZipCode) VALUES ('Wheelock Student Center, N 15th St', 'Tacoma', 'WA', 'USA', 98406);
INSERT INTO BookStore.dbo.Addresses (StreetAddress, City, StateProvence, Country, ZipCode) VALUES ('375 Hudson Street', 'New York', 'NY', 'USA', 10014);
INSERT INTO BookStore.dbo.Addresses (StreetAddress, City, StateProvence, Country, ZipCode) VALUES ('557 Broadway', 'New York', 'NY', 'USA', 10012);
INSERT INTO BookStore.dbo.Addresses (StreetAddress, City, StateProvence, Country, ZipCode) VALUES ('330 Hudson ', 'New York', 'NY', 'USA', 10013);
INSERT INTO BookStore.dbo.Addresses (StreetAddress, City, StateProvence, Country, ZipCode) VALUES ('1230 Avenue of the Americas', 'New York', 'NY', 'USA', 10020);
--Author Data
INSERT INTO BookStore.dbo.Author (FirstName, LastName, Initials) VALUES ('Lewis', 'Carroll', null);
INSERT INTO BookStore.dbo.Author (FirstName, LastName, Initials) VALUES ('Joseph', 'Conrad', null);
INSERT INTO BookStore.dbo.Author (FirstName, LastName, Initials) VALUES ('Herman', 'Melville', null);
INSERT INTO BookStore.dbo.Author (FirstName, LastName, Initials) VALUES ('Fyodor', 'Dostoevsky', null);
INSERT INTO BookStore.dbo.Author (FirstName, LastName, Initials) VALUES ('Victor', 'Hugo', null);
INSERT INTO BookStore.dbo.Author (FirstName, LastName, Initials) VALUES ('William', 'Shakespeare', null);
INSERT INTO BookStore.dbo.Author (FirstName, LastName, Initials) VALUES ('Bram', 'Stoker', null);
INSERT INTO BookStore.dbo.Author (FirstName, LastName, Initials) VALUES ('Mary', 'Shelly', null);
--Bookstore Data
INSERT INTO BookStore.dbo.BookStore (BookStoreName, AddressID, PhoneNumber) VALUES ('Amazon', 1, '2065240715');
INSERT INTO BookStore.dbo.BookStore (BookStoreName, AddressID, PhoneNumber) VALUES ('Barnes and Noble', 2, '2062640156');
INSERT INTO BookStore.dbo.BookStore (BookStoreName, AddressID, PhoneNumber) VALUES ('Borders', 3, '5098923907');
INSERT INTO BookStore.dbo.BookStore (BookStoreName, AddressID, PhoneNumber) VALUES ('Half Price Books', 4, '2535661238');
INSERT INTO BookStore.dbo.BookStore (BookStoreName, AddressID, PhoneNumber) VALUES ('Powell''s', 5, '5032284651');
INSERT INTO BookStore.dbo.BookStore (BookStoreName, AddressID, PhoneNumber) VALUES ('UW Book Store', 6, '2536924300');
INSERT INTO BookStore.dbo.BookStore (BookStoreName, AddressID, PhoneNumber) VALUES ('PLU Book Store', 7, '2535357665');
INSERT INTO BookStore.dbo.BookStore (BookStoreName, AddressID, PhoneNumber) VALUES ('UPS Book Store', 8, '2538793270');
--Publisher Data
INSERT INTO BookStore.dbo.Publisher (PublisherName, AddressID) VALUES ('Penguin Books', 9);
INSERT INTO BookStore.dbo.Publisher (PublisherName, AddressID) VALUES ('Scholastic', 10);
INSERT INTO BookStore.dbo.Publisher (PublisherName, AddressID) VALUES ('Pearson', 11);
INSERT INTO BookStore.dbo.Publisher (PublisherName, AddressID) VALUES ('Simon and Schuster', 12);
--Pub/BS Bridge
INSERT INTO BookStore.dbo.Publisher_BookStore_Bridge (PublisherID, BookStoreID) VALUES (1, 2);
INSERT INTO BookStore.dbo.Publisher_BookStore_Bridge (PublisherID, BookStoreID) VALUES (1, 3);
INSERT INTO BookStore.dbo.Publisher_BookStore_Bridge (PublisherID, BookStoreID) VALUES (1, 4);
INSERT INTO BookStore.dbo.Publisher_BookStore_Bridge (PublisherID, BookStoreID) VALUES (1, 5);
INSERT INTO BookStore.dbo.Publisher_BookStore_Bridge (PublisherID, BookStoreID) VALUES (2, 1);
INSERT INTO BookStore.dbo.Publisher_BookStore_Bridge (PublisherID, BookStoreID) VALUES (2, 2);
INSERT INTO BookStore.dbo.Publisher_BookStore_Bridge (PublisherID, BookStoreID) VALUES (2, 3);
INSERT INTO BookStore.dbo.Publisher_BookStore_Bridge (PublisherID, BookStoreID) VALUES (3, 6);
INSERT INTO BookStore.dbo.Publisher_BookStore_Bridge (PublisherID, BookStoreID) VALUES (3, 7);
INSERT INTO BookStore.dbo.Publisher_BookStore_Bridge (PublisherID, BookStoreID) VALUES (3, 8);
INSERT INTO BookStore.dbo.Publisher_BookStore_Bridge (PublisherID, BookStoreID) VALUES (4, 4);
INSERT INTO BookStore.dbo.Publisher_BookStore_Bridge (PublisherID, BookStoreID) VALUES (4, 5);
INSERT INTO BookStore.dbo.Publisher_BookStore_Bridge (PublisherID, BookStoreID) VALUES (4, 6);
--Genre Data
INSERT INTO BookStore.dbo.Genre (Genre) VALUES ('Biography');
INSERT INTO BookStore.dbo.Genre (Genre) VALUES ('Education');
INSERT INTO BookStore.dbo.Genre (Genre) VALUES ('Fiction');
INSERT INTO BookStore.dbo.Genre (Genre) VALUES ('Graphic Novels');
INSERT INTO BookStore.dbo.Genre (Genre) VALUES ('Horror');
INSERT INTO BookStore.dbo.Genre (Genre) VALUES ('Non-Fiction');
INSERT INTO BookStore.dbo.Genre (Genre) VALUES ('Sci-Fi/Fantasy');
--Book Data
INSERT INTO BookStore.dbo.Book (BookTitle, ISBN, GenreID, ReleaseDate, PublisherID, ListPrice, AuthorID, BookDescription) VALUES ('Alice in Wonderland', '9781533345455', 3, '1865-11-26', 1, 24.99, 1, 'Girl goes down rabiit hole');
INSERT INTO BookStore.dbo.Book (BookTitle, ISBN, GenreID, ReleaseDate, PublisherID, ListPrice, AuthorID, BookDescription) VALUES ('Heart of Darkness', '9780061992889', 2, '1889-12-31', 3, 34.56, 2, 'Be aware of mans greed');
INSERT INTO BookStore.dbo.Book (BookTitle, ISBN, GenreID, ReleaseDate, PublisherID, ListPrice, AuthorID, BookDescription) VALUES ('Moby Dick', '9781535277181', 4, '1851-12-31', 3, 45.88, 3, 'Thar she blows');
INSERT INTO BookStore.dbo.Book (BookTitle, ISBN, GenreID, ReleaseDate, PublisherID, ListPrice, AuthorID, BookDescription) VALUES ('Crime and Punishment', '9781533369043', 2, '1866-06-24', 3, 24.99, 4, 'One cant escape ones own conviction');
INSERT INTO BookStore.dbo.Book (BookTitle, ISBN, GenreID, ReleaseDate, PublisherID, ListPrice, AuthorID, BookDescription) VALUES ('The Hunchback of Notre Dame', '9780061947629', 4, '1969-12-31', 2, 34.56, 5, 'Longing for the outside world');
INSERT INTO BookStore.dbo.Book (BookTitle, ISBN, GenreID, ReleaseDate, PublisherID, ListPrice, AuthorID, BookDescription) VALUES ('Les Miserables', '9781535250714', 2, '1969-12-31', 2, 45.88, 5, 'Ive dreamed a dream of time gone by');
INSERT INTO BookStore.dbo.Book (BookTitle, ISBN, GenreID, ReleaseDate, PublisherID, ListPrice, AuthorID, BookDescription) VALUES ('Hamlet', '9781533300643', 1, '1865-11-26', 4, 24.99, 6, 'To be or not to be, that is the question');
INSERT INTO BookStore.dbo.Book (BookTitle, ISBN, GenreID, ReleaseDate, PublisherID, ListPrice, AuthorID, BookDescription) VALUES ('Othello', '9780061987995', 4, '1969-12-31', 4, 34.56, 6, 'Beware the green eyed monster of jealousy');
INSERT INTO BookStore.dbo.Book (BookTitle, ISBN, GenreID, ReleaseDate, PublisherID, ListPrice, AuthorID, BookDescription) VALUES ('King Lear', '9781535232286', 4, '1969-12-31', 4, 45.88, 6, 'I am a Man, More sinned against than sinning');
INSERT INTO BookStore.dbo.Book (BookTitle, ISBN, GenreID, ReleaseDate, PublisherID, ListPrice, AuthorID, BookDescription) VALUES ('Romeo and Juliet', '9781533309876', 4, '1865-11-26', 4, 24.99, 6, 'O Romeo, Romeo, Wherefore art thou');
INSERT INTO BookStore.dbo.Book (BookTitle, ISBN, GenreID, ReleaseDate, PublisherID, ListPrice, AuthorID, BookDescription) VALUES ('Dracula', '9780061989898', 5, '1969-12-31', 1, 34.56, 7, 'Welcome to Transylvania');
INSERT INTO BookStore.dbo.Book (BookTitle, ISBN, GenreID, ReleaseDate, PublisherID, ListPrice, AuthorID, BookDescription) VALUES ('Frankenstein', '9781535299999', 5, '1969-12-31', 2, 45.88, 8, 'Its Alive');

