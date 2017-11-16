CREATE DATABASE FactoryManager;
USE FactoryManager;

CREATE TABLE Registration (
    RegistrationID INT NOT NULL AUTO_INCREMENT,  
    Username VARCHAR(30) NOT NULL,
    Email VARCHAR(30) NOT NULL,
    Password VARCHAR(50) NOT NULL,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    CompanyName VARCHAR(50) NOT NULL,
    Address VARCHAR(50) NOT NULL, 
    City VARCHAR(50) NOT NULL, 
    State VARCHAR(50) NOT NULL, 
    ZipCode INT NOT NULL,
    PRIMARY KEY (RegistrationID)
);

CREATE TABLE Importation (
    ImportationID INT NOT NULL AUTO_INCREMENT,
    RegistrationID INT NOT NULL,
    CompanyName VARCHAR(50) NOT NULL,
    PointOfContact VARCHAR(100) NOT NULL,
    CountryCode INT NOT NULL,
    PhoneNumber INT NOT NULL,
    Email VARCHAR(30) NOT NULL,
    Address VARCHAR(50) NOT NULL, 
    City VARCHAR(50) NOT NULL, 
    State VARCHAR(50) NOT NULL, 
    ZipCode INT NOT NULL,
    CurrentDate DATE NOT NULL,
    Product VARCHAR(100) NOT NULL,
    PricePerUnit DOUBLE NOT NULL,
    Quantity INT NOT NULL,
    FOREIGN KEY (RegistrationID) REFERENCES Registration (RegistrationID) ON DELETE CASCADE,
    PRIMARY KEY (ImportationID)
);


CREATE TABLE Exportation (
    ExportationID INT NOT NULL AUTO_INCREMENT,
    RegistrationID INT NOT NULL,
    CompanyName VARCHAR(50) NOT NULL,
    PointOfContact VARCHAR(100) NOT NULL,
    CountryCode INT NOT NULL,
    PhoneNumber INT NOT NULL,
    Email VARCHAR(30) NOT NULL,
    Address VARCHAR(50) NOT NULL, 
    City VARCHAR(50) NOT NULL, 
    State VARCHAR(50) NOT NULL, 
    ZipCode INT NOT NULL,
    CurrentDate DATE NOT NULL,
    Product VARCHAR(100) NOT NULL,
    PricePerUnit DOUBLE NOT NULL,
    Quantity INT NOT NULL,
    FOREIGN KEY (RegistrationID) REFERENCES Registration (RegistrationID) ON DELETE CASCADE,
    PRIMARY KEY (ExportationID)
);


CREATE TABLE Delivery (
    DeliveryID INT NOT NULL AUTO_INCREMENT,
    RegistrationID INT NOT NULL,
    ExportationID INT NOT NULL,
    CompanyName VARCHAR(50) NOT NULL,
    PointOfContact VARCHAR(100) NOT NULL,
    CountryCode INT NOT NULL,
    PhoneNumber INT NOT NULL,
    Email VARCHAR(30) NOT NULL,
    Address VARCHAR(50) NOT NULL, 
    City VARCHAR(50) NOT NULL, 
    State VARCHAR(50) NOT NULL, 
    ZipCode INT NOT NULL,
    CurrentDate DATE NOT NULL,
    ContainerPricePerUnit DOUBLE,
    ContainerQuantity INT,
    ShipmentPrice DOUBLE,
    FOREIGN KEY (RegistrationID) REFERENCES Registration (RegistrationID) ON DELETE CASCADE,
    FOREIGN KEY (ExportationID) REFERENCES Exportation (ExportationID) ON DELETE CASCADE,
    PRIMARY KEY (DeliveryID)
);
