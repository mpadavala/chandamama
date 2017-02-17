CREATE TABLE STOCK_DATA
(
  TICKER             VARCHAR(10),
  LASTTRADE          FLOAT(15,5),
  TRADEDATE          DATE,
  GAINORLOSS         FLOAT(15,5),
  OPENEDAT           FLOAT(15,5),
  DAYSHIGH           FLOAT(15,5),
  DAYSLOW            FLOAT(15,5),
  TOTALVOLUME        FLOAT(25),
  MARKETCAP          FLOAT(25),
  PREVIOUSCLOSE      FLOAT(15,5),
  PERCENTGAINORLOSS  FLOAT(10,5),
  FIFTYTWOWEEKLOW    FLOAT(15,5),
  FIFTYTWOWEEKHIGH   FLOAT(15,5),
  EPS                FLOAT(15,5),
  PE                 FLOAT(15,5),
  COMPANYNAME        VARCHAR(100),
  CREATIONDATE       DATE,
  PRIMARY KEY (TICKER, TRADEDATE)
);	

CREATE TABLE TICKERS
(
  TICKER         VARCHAR(10),
  EXCHANGE		 VARCHAR(10),
  COUNTRY		 VARCHAR(10),
  NAME 			 VARCHAR(100),
  IPOYEAR		 INT,
  SECTOR		 VARCHAR(100),
  INDUSTRY		 VARCHAR(100),
  SUMMARYURL	 VARCHAR(200),
  CREATIONDATE   DATE,
  MODIFIEDDATE   DATE,
  PRIMARY KEY (TICKER, EXCHANGE, COUNTRY)
);
CREATE INDEX SECTOR_INDEX ON TICKERS(SECTOR);
CREATE INDEX INDUSTRY_INDEX ON TICKERS(INDUSTRY);

CREATE TABLE WATCHLIST
(
  TICKER         VARCHAR(10),
  STOCKEXCHANGE  VARCHAR(50),
  GAIN           FLOAT(5),
  LOSS           FLOAT(5),
  CREATIONDATE   VARCHAR(25),
  MODIFIEDDATE   VARCHAR(25),
  PRIMARY KEY (TICKER, CREATIONDATE),
  CONSTRAINT FK_TICKER FOREIGN KEY (TICKER) REFERENCES TICKERS(TICKER)
);

CREATE TABLE STOCK_INDEXES
(
  TICKER		VARCHAR(10),
  INDEXNAME		VARCHAR(20),
  COUNTRY		VARCHAR(10),
  STATUS		VARCHAR(10),
  CREATIONDATE	DATE NOT NULL,
  INDEXENDDATE  DATE,
  PRIMARY KEY(TICKER, INDEXNAME, COUNTRY)
);
CREATE INDEX INDEXNAME_INDEX ON STOCK_INDEXES(INDEXNAME);
CREATE INDEX STATUS_INDEX ON STOCK_INDEXES(STATUS);