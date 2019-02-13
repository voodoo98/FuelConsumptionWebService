DELETE FROM cars;
DELETE FROM gasstations;
DELETE FROM fuels;

INSERT INTO cars VALUES (1, 'EWJ-999', 'Opel Astra', 'White');
INSERT INTO cars VALUES (2, 'LEZ-036', 'Opel Astra', 'Grey');
INSERT INTO cars VALUES (3, 'KJM-710', 'VW Passat 6', 'Black Metal');
INSERT INTO cars VALUES (4, 'IJK-264', 'VW Polo 4', 'Black Magia Metal');

INSERT INTO gasstations VALUES (1, 47.189191, 19.191919, 'Hernad OUT', '2376, Hernád Köztársaság út 47');
INSERT INTO gasstations VALUES (2, 48.123451, 19.000111, 'Dabas MOL', '2370, Dabas');
INSERT INTO gasstations VALUES (3, 46.123451, 19.122222, 'Dabas MOL', '2378, Pusztavacs');

INSERT INTO fuels VALUES (1, 38.2, 650, 321, 2, 1, '2007-07-19 11:23:54');
INSERT INTO fuels VALUES (2, 43.3, 625, 311, 3, 2, '2004-11-29 10:23:54');
INSERT INTO fuels VALUES (3, 33.6, 614.3, 351, 1, 3, '2013-10-09 12:23:54');
INSERT INTO fuels VALUES (4, 44.2, 599, 321, 4, 1, '2016-11-19 10:23:54');
INSERT INTO fuels VALUES (5, 40.1, 589.99, 310, 2, 1, '2016-01-19 13:23:54');
INSERT INTO fuels VALUES (6, 30.26, 604.2, 400, 2, 3, '2016-02-19 23:24:54');


SELECT SETVAL('cars_id_seq', COALESCE(MAX(id), 1) ) FROM cars;
SELECT SETVAL('gasstations_id_seq', COALESCE(MAX(id), 1) ) FROM gasstations;
SELECT SETVAL('fuels_id_seq', COALESCE(MAX(id), 1) ) FROM fuels;
