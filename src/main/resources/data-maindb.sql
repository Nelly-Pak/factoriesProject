SET foreign_key_checks = 0;

DELETE FROM maindb.admin;
INSERT INTO maindb.admin(id, username, password) VALUES(0, 'admin1', '$2y$16$OTrK1xF9ZVaIGAEwKZ6rieag3kTLAj.hU6qUZAHhLaHog3XcGuv0m'); /*pA1*/


DELETE FROM maindb.factory;
INSERT INTO maindb.factory(id, name, exportid, password) VALUES(1, 'Factory One', 5, '$2y$16$6tbZ.1cbSZyvPfS7KBjf1eDRYD5aiGUxSfijdkCqeJWaZj1aWnYR2'); /*pF1*/
INSERT INTO maindb.factory(id, name, exportid, password) VALUES(2, 'Factory Two', 1, '$2y$16$.oidnx809LReNTaR6P/3ouqW4btmNIAud8dwFzPUBfzXLHqfu180O'); /*pF2*/
INSERT INTO maindb.factory(id, name, exportid, password) VALUES(3, 'Factory Three', 3, '$2y$16$InrdRWShm1FmUmWTL0Rifu8ziY8s5mzYbkFIA/xJ2bP6PaeGMXZ9G'); /*pF3*/
INSERT INTO maindb.factory(id, name, exportid, password) VALUES(4, 'Factory Four', 2, '$2y$16$IV/xkYW2uIGerGm9PbA6kuC1sqydtJ2z95ptaqeFIn6Shx99JTO1i'); /*pF4*/
INSERT INTO maindb.factory(id, name, exportid, password) VALUES(5, 'Factory Five', 4, '$2y$16$tmGNZur/mo4i/WhoOTR40OjRIoLDQMQD.AYvb51dHbT.1cPc2ojDm'); /*pF5*/
INSERT INTO maindb.factory(id, name, exportid, password) VALUES(6, 'Factory Six', 4, '$2y$16$pBbYyhNygPtd7yi5R7O3peU2OEii3P.5vs1RAT7t07D9WQWlZ.CG6'); /*pF6*/
INSERT INTO maindb.factory(id, name, exportid, password) VALUES(7, 'Factory Seven', 4, '$2y$16$ffNTyB0qs9boR1OaDgmoe.WgMvuCrT.M62mOV9Va.utMV7ogqiXj2'); /*pF7*/
INSERT INTO maindb.factory(id, name, exportid, password) VALUES(8, 'Factory Eight', 4, '$2y$16$jyf3OGb8Fshd8NG8tlSIJevebLqoocNfIXyjfezhhv38Px29G/6AC'); /*pF8*/

DELETE FROM maindb.product;
INSERT INTO maindb.product(id, name) VALUES(1, 'Wood');
INSERT INTO maindb.product(id, name) VALUES(2, 'Iron');
INSERT INTO maindb.product(id, name) VALUES(3, 'Electrical components');
INSERT INTO maindb.product(id, name) VALUES(4, 'Car');
INSERT INTO maindb.product(id, name) VALUES(5, 'Furniture');

DELETE FROM maindb.transporter;
INSERT INTO maindb.transporter(id, name, password) VALUES(1, 'CCC1', '$2y$16$rNQMZBVxYeKuqp.0qpujSOjO0tg6yzSME9DV4VxXbDu0Dom/YTH2a'); /*pT1*/
INSERT INTO maindb.transporter(id, name, password) VALUES(2, 'CCC2', '$2y$16$.NP7.fYZSBCe9osNOIJjJOgsulr77qOle3ievVx7wne/FcgOIwA9q'); /*pT2*/
INSERT INTO maindb.transporter(id, name, password) VALUES(3, 'CCC3', '$2y$16$QeN/My.qOPCJ.F0izfE7FefTka6C7rq8YixYqAXu9/uIUb0Jp0sn2'); /*pT3*/
INSERT INTO maindb.transporter(id, name, password) VALUES(4, 'CCC4', '$2y$16$zcpleK/gEHiG5zhy1MHvZOAja3uBaWgdt1OsBCx2Qm2PC4.d1h4/2'); /*pT4*/
INSERT INTO maindb.transporter(id, name, password) VALUES(5, 'CCC5', '$2y$16$IDh1wV7Fk..Yvx..UGXtdeRAtXz7dYXPBfGgZaiyJQ0gOFq5UAAwK'); /*pT5*/

DELETE FROM maindb.ordering;
INSERT INTO maindb.ordering(id, id_factory, id_product, id_transporter, price, accepted, id_factory_from,distance) VALUES(1, 1, 2, 1, 3000, true, 4,6);
INSERT INTO maindb.ordering(id, id_factory, id_product, id_transporter, price, accepted, id_factory_from,distance) VALUES(2, 1, 3, 2, 3000, true, 3,4);
INSERT INTO maindb.ordering(id, id_factory, id_product, id_transporter, price, accepted, id_factory_from,distance) VALUES(3, 1, 1, 2, 4000, false, 2,1);
INSERT INTO maindb.ordering(id, id_factory, id_product, id_transporter, price, accepted, id_factory_from,distance) VALUES(4, 3, 5, 3, 6000, true, 1,4);
INSERT INTO maindb.ordering(id, id_factory, id_product, id_transporter, price, accepted, id_factory_from,distance) VALUES(5, 4, 4, 2, 3500, false, 5,8);
INSERT INTO maindb.ordering(id, id_factory, id_product, id_transporter, price, accepted, id_factory_from,distance) VALUES(6, 5, 2, 5, 7000, true, 4,8);
INSERT INTO maindb.ordering(id, id_factory, id_product, id_transporter, price, accepted, id_factory_from,distance) VALUES(7, 5, 3, 4, 1000, false, 3,1);
INSERT INTO maindb.ordering(id, id_factory, id_product, id_transporter, price, accepted, id_factory_from,distance) VALUES(8, 3, 2, 1, 2500, true, 4,2);


DELETE FROM maindb.roads_backing;
INSERT INTO maindb.roads_backing(origid, destid, weight) VALUES (1,2,1), (2,3,3), (3,4,2), (4,5,8), (2,4,8), (2,5,7),(3,5,1),(5,6,5),(6,7,9),(7,8,1),(8,1,10),(7,1,7),(7,2,3),(5,7,4),(2,1,1), (3,2,3), (4,3,2), (5,4,8), (4,2,8), (5,2,7),(5,3,1),(6,5,5),(7,6,9),(8,7,1),(1,8,10),(1,7,7),(2,7,3),(7,5,4);