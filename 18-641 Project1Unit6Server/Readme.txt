Please setup the JDBC parameters first in Database.properties to run the test
including URL, preferred dabase name, user name and password.

Make sure that the MySQL is in service before running the driver program.

SQL commands used in this project are stored in SQL.properties to improce modifiability.
There are three tables that will be created during the process:
Automobiles( 
	A_Id int NOT NULL AUTO_INCREMENT, 
	Model varchar(50) NOT NULL, 
	Make varchar(50) NOT NULL, 
	Baseprice int NOT NULL, 
	PRIMARY KEY (A_Id) 
);
OptionSets( 
	S_Id int NOT NULL AUTO_INCREMENT,
	SetsName varchar(50) NOT NULL, 
	Auto_Id int, PRIMARY KEY(S_Id), 
	FOREIGN KEY(Auto_Id) REFERENCES Automobiles(A_Id) ON DELETE CASCADE ON UPDATE CASCADE 
);
AutoOptions ( 
	O_Id int NOT NULL AUTO_INCREMENT, 
	OptionName varchar(50) NOT NULL, 
	Value int NOT NULL, 
	Set_Id int NOT NULL, 
	PRIMARY KEY(O_Id), 
	FOREIGN KEY(Set_Id) REFERENCES OptionSets(S_Id) ON DELETE CASCADE ON UPDATE CASCADE 
);

They were designed to be normalized.
