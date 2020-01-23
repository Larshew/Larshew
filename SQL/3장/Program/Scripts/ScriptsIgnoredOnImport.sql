
DROP TABLE IF EXISTS mst_users;
GO

INSERT INTO mst_users
VALUES
    ('U001', '2016-08-26', 1)
  , ('U002', '2016-08-26', 2)
  , ('U003', '2016-08-27', 3)
;
GO

DROP TABLE IF EXISTS access_log ;
GO

INSERT INTO access_log 
VALUES
    ('2016-08-26 12:02:00', 'http://www.other.com/path1/index.php?k1=v1&k2=v2#Ref1', 'http://www.example.com/video/detail?id=001')
  , ('2016-08-26 12:02:01', 'http://www.other.net/path1/index.php?k1=v1&k2=v2#Ref1', 'http://www.example.com/video#ref'          )
  , ('2016-08-26 12:02:01', 'https://www.other.com/'                               , 'http://www.example.com/book/detail?id=002' )
;
GO

DROP TABLE IF EXISTS access_log ;
GO

INSERT INTO access_log 
VALUES
    ('2016-08-26 12:02:00', 'http://www.other.com/path1/index.php?k1=v1&k2=v2#Ref1', 'http://www.example.com/video/detail?id=001')
  , ('2016-08-26 12:02:01', 'http://www.other.net/path1/index.php?k1=v1&k2=v2#Ref1', 'http://www.example.com/video#ref'          )
  , ('2016-08-26 12:02:01', 'https://www.other.com/'                               , 'http://www.example.com/book/detail?id=002' )
;
GO

DROP TABLE IF EXISTS purchase_log_with_coupon;
GO

INSERT INTO purchase_log_with_coupon
VALUES
    ('10001', 3280, NULL)
  , ('10002', 4650,  500)
  , ('10003', 3870, NULL)
;
GO

DROP TABLE IF EXISTS quarterly_sales;
GO

INSERT INTO quarterly_sales
VALUES
    (2015, 82000, 83000, 78000, 83000)
  , (2016, 85000, 85000, 80000, 81000)
  , (2017, 92000, 81000, NULL , NULL )
;
GO

DROP TABLE IF EXISTS advertising_stats;
GO

INSERT INTO advertising_stats
VALUES
    ('2017-04-01', '001', 100000,  3000)
  , ('2017-04-01', '002', 120000,  1200)
  , ('2017-04-01', '003', 500000, 10000)
  , ('2017-04-02', '001',      0,     0)
  , ('2017-04-02', '002', 130000,  1400)
  , ('2017-04-02', '003', 620000, 15000)
;
GO

DROP TABLE IF EXISTS location_1d;
GO

INSERT INTO location_1d
VALUES
    ( 5 , 10)
  , (10 ,  5)
  , (-2 ,  4)
  , ( 3 ,  3)
  , ( 0 ,  1)
;
GO

DROP TABLE IF EXISTS location_2d;
GO

INSERT INTO location_2d
VALUES
    (0, 0, 2, 2)
  , (3, 5, 1, 2)
  , (5, 3, 2, 1)
;
GO

DROP TABLE IF EXISTS mst_users_with_dates;
GO

INSERT INTO mst_users_with_dates
VALUES
    ('U001', '2016-02-28 10:00:00', '2000-02-29')
  , ('U002', '2016-02-29 10:00:00', '2000-02-29')
  , ('U003', '2016-03-01 10:00:00', '2000-02-29')
;
GO

DROP TABLE IF EXISTS review;
GO

INSERT INTO review
VALUES
    ('U001', 'A001', 4.0)
  , ('U001', 'A002', 5.0)
  , ('U001', 'A003', 5.0)
  , ('U002', 'A001', 3.0)
  , ('U002', 'A002', 3.0)
  , ('U002', 'A003', 4.0)
  , ('U003', 'A001', 5.0)
  , ('U003', 'A002', 4.0)
  , ('U003', 'A003', 4.0)
;
GO

DROP TABLE IF EXISTS popular_products;
GO

INSERT INTO popular_products
VALUES
    ('A001', 'action', 94)
  , ('A002', 'action', 81)
  , ('A003', 'action', 78)
  , ('A004', 'action', 64)
  , ('D001', 'drama' , 90)
  , ('D002', 'drama' , 82)
  , ('D003', 'drama' , 78)
  , ('D004', 'drama' , 58)
;
GO

DROP TABLE IF EXISTS daily_kpi;
GO

INSERT INTO daily_kpi
VALUES
    ('2017-01-01', 'impressions', 1800)
  , ('2017-01-01', 'sessions'   ,  500)
  , ('2017-01-01', 'users'      ,  200)
  , ('2017-01-02', 'impressions', 2000)
  , ('2017-01-02', 'sessions'   ,  700)
  , ('2017-01-02', 'users'      ,  250)
;
GO

DROP TABLE IF EXISTS purchase_detail_log;
GO

INSERT INTO purchase_detail_log
VALUES
    (100001, 'A001', 3000)
  , (100001, 'A002', 4000)
  , (100001, 'A003', 2000)
  , (100002, 'D001', 5000)
  , (100002, 'D002', 3000)
  , (100003, 'A001', 3000)
;
GO

DROP TABLE IF EXISTS quarterly_sales;
GO

INSERT INTO quarterly_sales
VALUES
    (2015, 82000, 83000, 78000, 83000)
  , (2016, 85000, 85000, 80000, 81000)
  , (2017, 92000, 81000, NULL , NULL )
;
GO

DROP TABLE IF EXISTS purchase_log;
GO

INSERT INTO purchase_log
VALUES
    (100001, 'A001,A002,A003')
  , (100002, 'D001,D002')
  , (100003, 'A001')
;
GO

DROP TABLE IF EXISTS app1_mst_users;
GO

INSERT INTO app1_mst_users
VALUES
    ('U001', 'Sato'  , 'sato@example.com'  )
  , ('U002', 'Suzuki', 'suzuki@example.com')
;
GO

DROP TABLE IF EXISTS app2_mst_users;
GO

INSERT INTO app2_mst_users
VALUES
    ('U001', 'Ito'   , '080-xxxx-xxxx')
  , ('U002', 'Tanaka', '070-xxxx-xxxx')
;
GO

DROP TABLE IF EXISTS mst_categories;
GO

INSERT INTO mst_categories
VALUES
    (1, 'dvd' )
  , (2, 'cd'  )
  , (3, 'book')
;
GO

DROP TABLE IF EXISTS category_sales;
GO

INSERT INTO category_sales
VALUES
    (1, 850000)
  , (2, 500000)
;
GO

DROP TABLE IF EXISTS product_sale_ranking;
GO

INSERT INTO product_sale_ranking
VALUES
    (1, 1, 'D001', 50000)
  , (1, 2, 'D002', 20000)
  , (1, 3, 'D003', 10000)
  , (2, 1, 'C001', 30000)
  , (2, 2, 'C002', 20000)
  , (2, 3, 'C003', 10000)
;
GO

DROP TABLE IF EXISTS mst_users_with_card_number;
GO

INSERT INTO mst_users_with_card_number
VALUES
    ('U001', '1234-xxxx-xxxx-xxxx')
  , ('U002', NULL                 )
  , ('U003', '5678-xxxx-xxxx-xxxx')
;
GO

DROP TABLE IF EXISTS purchase_log;
GO

INSERT INTO purchase_log
VALUES
    (10001, 'U001', 200, '2017-01-30 10:00:00')
  , (10002, 'U001', 500, '2017-02-10 10:00:00')
  , (10003, 'U001', 200, '2017-02-12 10:00:00')
  , (10004, 'U002', 800, '2017-03-01 10:00:00')
  , (10005, 'U002', 400, '2017-03-02 10:00:00')
;
GO

DROP TABLE IF EXISTS product_sales;
GO

INSERT INTO product_sales
VALUES
    ('dvd' , 'D001', 50000)
  , ('dvd' , 'D002', 20000)
  , ('dvd' , 'D003', 10000)
  , ('cd'  , 'C001', 30000)
  , ('cd'  , 'C002', 20000)
  , ('cd'  , 'C003', 10000)
  , ('book', 'B001', 20000)
  , ('book', 'B002', 15000)
  , ('book', 'B003', 10000)
  , ('book', 'B004',  5000)
;
GO

-- ?꾩슂???뚯씠釉붿씠 ?곕줈 ?놁뒿?덈떎.

GO

-- ?꾩슂???뚯씠釉붿씠 ?곕줈 ?놁뒿?덈떎.

GO

-- ?꾩슂???뚯씠釉붿씠 ?곕줈 ?놁뒿?덈떎.


GO

DROP TABLE IF EXISTS mst_user_location;
CREATE TABLE mst_user_location (
    user_id   varchar(255)
  , pref_name varchar(255)
  , city_name varchar(255)
);

INSERT INTO mst_user_location
VALUES
    ('U001', '?쒖슱?밸퀎??, '媛뺤꽌援?)
  , ('U002', '寃쎄린?꾩닔?먯떆', '?μ븞援?  )
  , ('U003', '?쒖＜?밸퀎?먯튂??, '?쒓??ъ떆')
;


GO

--구문 오류: ? 근처의 구문이 잘못되었습니다.
--DROP TABLE IF EXISTS mst_user_location;
--CREATE TABLE mst_user_location (
--    user_id   varchar(255)
--  , pref_name varchar(255)
--  , city_name varchar(255)
--);
--
--INSERT INTO mst_user_location
--VALUES
--    ('U001', '?쒖슱?밸퀎??, '媛뺤꽌援?)
--  , ('U002', '寃쎄린?꾩닔?먯떆', '?μ븞援?  )
--  , ('U003', '?쒖＜?밸퀎?먯튂??, '?쒓??ъ떆')
--;
--



GO
