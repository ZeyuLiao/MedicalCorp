

create table CommunityList
(communityId INT(5) AUTO_INCREMENT PRIMARY key,
community_name VARCHAR(50) UNIQUE,
city_name VARCHAR(50) not null DEFAULT 'Toronto'
);

INSERT into communityList (community_name)
VALUES('North York'),  
('Toronto Downtown'),
('Markham'),
('Scarborough');


create table HospitalList(
hospital_id INT PRIMARY KEY AUTO_INCREMENT,
hospital_name VARCHAR(50) not null unique,
hospital_communityname VARCHAR(50) not null ,
CONSTRAINT fk_HospitalList_communityname FOREIGN KEY (hospital_communityname) REFERENCES communityList(community_name)  ON UPDATE CASCADE ON DELETE CASCADE
);

INSERT into HospitalList (hospital_name, hospital_communityname)
VALUES('North York Hospital','North York'),
('Downtown Hospital','Toronto Downtown'),
('Toronto Hospital','Toronto Downtown'),
('Markham Hospital','Markham'),
('Medical Examination Center','Scarborough');




CREATE TABLE encounter (
	encounter_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	doctor_id INT NOT NULL,
	patient_id INT NOT NULL,
	state boolean,
	symptom VARCHAR ( 100 ),
	diagnosis VARCHAR ( 100 ),
	start_date DATE,
	end_date DATE, 
	blood_pressure INT,
	heart_beat_rate INT,
	blood_sugar DOUBLE,
	white_blood_cells DOUBLE
);

create TABLE login(
logid int PRIMARY KEY not NULL AUTO_INCREMENT,
user_name VARCHAR(255) UNIQUE,
role VARCHAR(255), #Patient, HospitalAdmin,doctor, PharmacyAdmin, DeliveryAdmin, DeliveryMan, Sysadmin
pwd VARCHAR(255)
);

INSERT into login(user_name,role,pwd) VALUES
('SysAdmin','SysAdmin',MD5(12345)),
('CommunityAdmin','CommunityAdmin',MD5(12345)),
('HospitalAdmin','HospitalAdmin',MD5(12345)),
('PharmacyAdmin','PharmacyAdmin',MD5(12345)),
('DeliveryAdmin','DeliveryAdmin',MD5(12345)),
('InventoryAdmin','GoodsAdmin',MD5(12345)),
('Courier1','DHL',MD5('c123')),
('Courier2','FedEx',MD5('c123')),
('Courier3','Express',MD5('c123')),
('Courier4','UPS',MD5('c123')), #1-10前六管理员 后四快递员

('Alex','Patient',MD5(123)), #11
('Bob','Patient',MD5(123)),
('Cathy','Patient',MD5(123)),
('Dana','Patient',MD5(123)),
('Emma','Patient',MD5(123)),

('Aaron Hendler','Doctor',MD5('d123')), #16
('Stephen Herman','Doctor',MD5('d123'));

CREATE TABLE patient (
	patient_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR ( 50 ) NOT NULL,
	phone_number VARCHAR ( 50 ),#DOB YEAR(date)
	DOB VARCHAR ( 5 ),
	community_name VARCHAR ( 50 ),
	CONSTRAINT fk_communityname FOREIGN KEY (community_name) REFERENCES CommunityList(community_name) ON UPDATE CASCADE ON DELETE CASCADE,
	logID int not null,
	CONSTRAINT fk_patient_logID FOREIGN KEY (logID) REFERENCES login(logid) ON UPDATE CASCADE ON DELETE CASCADE,
	address VARCHAR(255) DEFAULT null
);
INSERT INTO patient ( `name`, phone_number, DOB, community_name, logID)
VALUES
	( 'Alex', '6477823360', '1990','Toronto Downtown', 11),
	( 'Bob', '1211111111', '1980','Toronto Downtown', 12 ),
	( 'Cathy', '1131111111', '1970','Markham', 13),
	( 'Dana', '1114111111', '1960','Scarborough',14),
	( 'Emma', '1111511111', '2000','North York' , 15);
	
	create table DoctorList(
doctor_id INT PRIMARY KEY AUTO_INCREMENT,
doctor_name VARCHAR(50) not null,
hospital_name VARCHAR(50) not null,
CONSTRAINT fk_hospitalName FOREIGN KEY (hospital_name) REFERENCES HospitalList(hospital_name) ON UPDATE CASCADE ON DELETE CASCADE,
department VARCHAR(50) not null,
phone_number VARCHAR(50) not null,
photo_address VARCHAR(50) not null,
logID int not null,
CONSTRAINT fk_doctor_logID FOREIGN KEY (logID) REFERENCES login(logid) ON UPDATE CASCADE ON DELETE CASCADE

);

INSERT into DoctorList (doctor_name, hospital_name,department,phone_number,photo_address,logid)
VALUES ('Aaron Hendler','Downtown Hospital','Emergency Clinic','4378861089','src//photo//Aaron_Hendler.jpg',16),
('Stephen Herman','North York Hospital','pharmacy','6477879723','src//photo//Stephen_Herman.jpg',17);
	
	
	CREATE table goods(
	goods_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	goods_name VARCHAR ( 50 ) NOT NULL UNIQUE,
	goods_status int not null DEFAULT(0) #0表示有效，1表示无效
	
	);
	
	
INSERT INTO goods (goods_name) VALUES
('Amoxicillin'),#阿莫西林
('Azithromycin'),#阿奇霉素
('ibuprofen'),#布洛芬
('melatonin'),#褪黑素
('glucose'); #葡萄糖
	


	
CREATE TABLE Store(
Store_id int PRIMARY KEY  not null AUTO_INCREMENT,
store_name VARCHAR(255) UNIQUE,
community VARCHAR(50) not null,
store_status int not null DEFAULT(0), #0表示有效，1表示无效
CONSTRAINT fk_store_community FOREIGN KEY (community) REFERENCES communityList(community_name) ON UPDATE CASCADE ON DELETE CASCADE
);

INSERT INTO Store (store_name, community)
VALUES ('Shoppers', 'North York'),
       ('Rexall', 'Toronto Downtown'),
       ('HealthCare', 'Markham'),
       ('drugMart', 'Scarborough');



	CREATE TABLE IF NOT EXISTS inventory (
	store_id INT NOT NULL,
	goods_id INT NOT NULL,
	selling_price DOUBLE not null,
	CONSTRAINT fk_store_id FOREIGN KEY (store_id) REFERENCES store(store_id) ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT fk_goods_id FOREIGN KEY (goods_id) REFERENCES goods(goods_id) ON UPDATE CASCADE ON DELETE CASCADE
);

INSERT INTO inventory (store_id, goods_id, selling_price)
VALUES (1, 1, 10.00),
       (1, 2, 20.00),
       (2, 3, 15.00),
       (2, 4, 25.00),
       (3, 5, 50.00);
	
	CREATE table if not EXISTS `ORDER`(
       idfororder int not null PRIMARY KEY AUTO_INCREMENT,
			 order_no VARCHAR(255) DEFAULT (date_format(now(), "%Y%c%d%H%i%S")),
			 store_id int not null,
			 CONSTRAINT fk_order_storeid FOREIGN KEY (store_id) REFERENCES Store(store_id) ON UPDATE CASCADE ON DELETE RESTRICT,
			 Patient_id int not null,
			 CONSTRAINT fk_order_Patientid FOREIGN KEY (Patient_id) REFERENCES Patient(patient_id) ON UPDATE CASCADE ON DELETE RESTRICT,
			 total_price DOUBLE, 
			 payment_time VARCHAR(50) DEFAULT (CURRENT_TIMESTAMP),
			 delivery_status VARCHAR(255)			
);



INSERT INTO `ORDER` (store_id, Patient_id, total_price, delivery_status)
VALUES (1, 1, 100.00, 'pending'),
       (1, 2, 50.00, 'pending'),
       (2, 3, 75.00, 'delievered'),
       (2, 4, 25.00, 'delievered');
			 
			 
			 
			 
CREATE TABLE order_detail (
      order_No int not null,
			goods_ID int not null,
			quantity int not null,
			CONSTRAINT fk_orderdetail_orderNo FOREIGN KEY (order_No) REFERENCES `order`(idfororder) ON UPDATE CASCADE ON DELETE RESTRICT,
			CONSTRAINT fk_orderdetail_goodsID FOREIGN KEY (goods_ID) REFERENCES goods(goods_id) ON UPDATE CASCADE ON DELETE RESTRICT
);


#########################################################
INSERT INTO order_detail (order_No, goods_ID, quantity)
VALUES (1, 1, 2),
       (2, 2, 3),
       (3, 3, 1),
       (4, 4, 4);

			 
CREATE TABLE Delivery (
  idfordelivery INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  delivery_NO VARCHAR(255) DEFAULT (CONCAT(date_format(now(), "%Y%c%d%H%i%S"),"DELIVER")),
  order_id INT NOT NULL,
  CONSTRAINT fk_delivery_orderid FOREIGN KEY (order_id) REFERENCES `order`(idfororder) ON UPDATE CASCADE,
  delivery_company VARCHAR(255) default ("Not Assigned"),
  delivery_status VARCHAR(255) DEFAULT "pending",
  Delivered_time VARCHAR(50) DEFAULT NULL
);

###################################
INSERT INTO Delivery (order_id, delivery_company, delivery_status) VALUES
      (1, 'DHL', 'pending'),
      (2, 'FedEx', 'pending'),
      (3, 'Express', 'delievered'),
      (4, 'UPS', 'delievered');
