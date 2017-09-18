-- number of stocks  by exchange
select exchange, count(*) count, count(*)*100/(select count(*) from tickers) percentage from tickers group by exchange order by count desc;

-- number of stocks in each sector
select distinct sector, count(*) count, count(*)*100/(select count(*) from tickers) percentage from tickers group by sector order by count desc;

-- number of stocks by sector and industry combination
select distinct sector, industry,  count(*) count from tickers group by sector,industry order by sector, industry, count desc;


-- Gains from Certain Date 
Select 
a.SYMBOL, 
((a.lasttrade - b.lasttrade)/b.LASTTRADE)*100  gain_perc, 
a.lasttrade, 
b.lasttrade, 
a.comapnyname, 
a.MARKETCAP/1000000 MarketCap, 
a.TOTALVOLUME/1000000 TotalVolume, 
a.FIFTYTWOWEEKLOW, 
a.FIFTYTWOWEEKHIGH, 
a.TRADEDATE 
from test_stock_details a, test_stock_details b 
where a.SYMBOL=b.SYMBOL 
and a.tradedate > b.tradedate 
and a.TRADEDATE = date_sub(curdate(), INTERVAL 1 DAY) 
and b.tradedate='2012-01-03' 
and ((a.lasttrade - b.lasttrade)/b.LASTTRADE)*100 > 0 
and a.marketcap > 1000000000 
order by ((a.lasttrade - b.lasttrade)/b.LASTTRADE)*100 desc


-- loosers from certain date
Select 
a.SYMBOL, 
((a.lasttrade - b.lasttrade)/b.LASTTRADE)*100  gain_perc, 
a.lasttrade, 
a.comapnyname, 
a.MARKETCAP/1000000 MarketCap, 
a.TOTALVOLUME/1000000 TotalVolume, 
a.FIFTYTWOWEEKLOW, 
a.FIFTYTWOWEEKHIGH, 
a.TRADEDATE 
from test_stock_details a, test_stock_details b 
where a.SYMBOL=b.SYMBOL 
and a.tradedate > b.tradedate 
and a.TRADEDATE = date_sub(curdate(), INTERVAL 0 DAY) 
and b.tradedate='2012-01-03' 
and ((a.lasttrade - b.lasttrade)/b.LASTTRADE)*100 < 0 
and a.marketcap > 1000000000 
order by ((a.lasttrade - b.lasttrade)/b.LASTTRADE)*100 asc


-- potential under valued stocks
Select 
SYMBOL,
fiftytwoweekhigh/fiftytwoweeklow ratio1,
lasttrade/fiftytwoweeklow ratio2, 
LASTTRADE, 
comapnyname, 
MARKETCAP/1000000 MarketCap, 
TOTALVOLUME/1000000 TotalVolume, 
FIFTYTWOWEEKLOW, 
FIFTYTWOWEEKHIGH, 
TRADEDATE 
from stock_details 
where 
TRADEDATE = date_sub(curdate(), INTERVAL 1 DAY)
and marketcap > 1000000000
and fiftytwoweeklow > 0
and (fiftytwoweekhigh/fiftytwoweeklow) >2 
and (lasttrade/fiftytwoweeklow) < 2
order by (lasttrade/fiftytwoweeklow) asc

//create new table from test_Stock_Details
create table `finance`.`test_stock_details` as select * from stock_Details group by symbol, tradedate


// finding duplicates
SELECT symbol, count(*) FROM `finance`.`test_stock_details` 
where tradedate='2012-01-13'
group by symbol
having count(*) > 1;




//SECTORS
Information Technology
Materials
Health Care
Financials
Consumer Staples
Utilities
Telecommunications Services
Consumer Discretionary
Energy
Industrials

//MarketCap by SECTORS
SELECT tradedate, count(*), sum(marketcap)/1000000000000 'MarketCap(Trillion)' from 
(SELECT * FROM test_stock_details where SYMBOL in (select TICKER from test_tickers where sector='Information Technology')) as table1
group by tradedate order by tradedate desc;

SELECT tradedate, count(*), sum(marketcap)/1000000000000 'MarketCap(Trillion)' from 
(SELECT * FROM test_stock_details where SYMBOL in (select TICKER from test_tickers where sector='Materials')) as table1
group by tradedate order by tradedate desc;

SELECT tradedate, count(*), sum(marketcap)/1000000000000 'MarketCap(Trillion)' from 
(SELECT * FROM test_stock_details where SYMBOL in (select TICKER from test_tickers where sector='Health Care')) as table1
group by tradedate order by tradedate desc;

SELECT tradedate, count(*), sum(marketcap)/1000000000000 'MarketCap(Trillion)' from 
(SELECT * FROM test_stock_details where SYMBOL in (select TICKER from test_tickers where sector='Financials')) as table1
group by tradedate order by tradedate desc;

SELECT tradedate, count(*), sum(marketcap)/1000000000000 'MarketCap(Trillion)' from 
(SELECT * FROM test_stock_details where SYMBOL in (select TICKER from test_tickers where sector='Consumer Staples')) as table1
group by tradedate order by tradedate desc;

SELECT tradedate, count(*), sum(marketcap)/1000000000000 'MarketCap(Trillion)' from 
(SELECT * FROM test_stock_details where SYMBOL in (select TICKER from test_tickers where sector='Utilities')) as table1
group by tradedate order by tradedate desc;

SELECT tradedate, count(*), sum(marketcap)/1000000000000 'MarketCap(Trillion)' from 
(SELECT * FROM test_stock_details where SYMBOL in (select TICKER from test_tickers where sector='Telecommunications Services')) as table1
group by tradedate order by tradedate desc;

SELECT tradedate, count(*), sum(marketcap)/1000000000000 'MarketCap(Trillion)' from 
(SELECT * FROM test_stock_details where SYMBOL in (select TICKER from test_tickers where sector='Consumer Discretionary')) as table1
group by tradedate order by tradedate desc;

SELECT tradedate, count(*), sum(marketcap)/1000000000000 'MarketCap(Trillion)' from 
(SELECT * FROM test_stock_details where SYMBOL in (select TICKER from test_tickers where sector='Energy')) as table1
group by tradedate order by tradedate desc;

SELECT tradedate, count(*), sum(marketcap)/1000000000000 'MarketCap(Trillion)' from 
(SELECT * FROM test_stock_details where SYMBOL in (select TICKER from test_tickers where sector='Industrials')) as table1
group by tradedate order by tradedate desc;