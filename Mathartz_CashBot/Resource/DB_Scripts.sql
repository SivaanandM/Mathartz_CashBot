
--Script Search Table (Get Script details from presto and store here for internal purpose)
Create table TBL_Master_Contracts  (id bigint auto_increment, SECID varchar(50) not null, SYMBOL varchar(50) not null, 
EXCHANGE varchar(50) not null, INSTRUMENT varchar(50) not null, LOTSIZE varchar(50) not null, TICKSIZE varchar(50) not null, 
EXPDD varchar(50) not null,EXPMONTHYEAR varchar(50) not null, OPTTYPE varchar(50) not null, STRIKE varchar(50) not null);

--DashBoard View 
Create table TBL_BEAST_VIEW  (id bigint,  HEADDISPLAY varchar(50) not null, 
 F1POINT double, F1PL double,  F2POINT double, F2PL double, F3POINT double, 
 F3PL double, F4POINT double ,F4PL double,  F5POINT double ,F5PL double,  
 F6POINT double, F6PL double);

--Trade Line (Scripts going to play in market)
Create table TBL_Trade_Line  (id bigint auto_increment, HEADID varchar(50) not null, HEADDISPLAY varchar(50) not null, 
HEADSYMBOL varchar(50) not null, EXCHANGE varchar(50), INSTRUMENT varchar(50) , 
LOTSIZE varchar(50), TICKSIZE varchar(50) , EXPDD varchar(50) ,
EXPMONTHYEAR varchar(50), OPTTYPE varchar(50) , STRIKE varchar(50) );

--FORMULA DATA
CREATE TABLE TBL_FORMULA_DATA (ID bigint,Fname varchar(10), X double, Y double, ST varchar(25), MT varchar(25),
ET varchar(25), LCOUNT int, ROUND int, QTY int, TRADESWITCH Bool, ISEND Bool );

--TRADE INFO
CREATE TABLE TBL_TRADE_INFO (ID BIGINT, FNAME VARCHAR(25), 
ORDERTYPE VARCHAR(25), WAY VARCHAR(25) , FST VARCHAR(50), ORDERID VARCHAR(50),  
PRICE DOUBLE);
