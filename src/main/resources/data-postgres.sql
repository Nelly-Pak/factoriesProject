DELETE FROM factory;
INSERT INTO factory(id, name, exportid, password) VALUES(0, 'Factory One', 0, '$2y$16$6tbZ.1cbSZyvPfS7KBjf1eDRYD5aiGUxSfijdkCqeJWaZj1aWnYR2'); /*pF1*/
INSERT INTO factory(id, name, exportid, password) VALUES(1, 'Factory Two', 0,'$2y$16$.oidnx809LReNTaR6P/3ouqW4btmNIAud8dwFzPUBfzXLHqfu180O');/*pF2*/
INSERT INTO factory(id, name, exportid, password) VALUES(2, 'Factory Three', 0,'$2y$16$InrdRWShm1FmUmWTL0Rifu8ziY8s5mzYbkFIA/xJ2bP6PaeGMXZ9G');/*pF3*/
INSERT INTO factory(id, name, exportid, password) VALUES(3, 'Factory Four', 0,'$2y$16$IV/xkYW2uIGerGm9PbA6kuC1sqydtJ2z95ptaqeFIn6Shx99JTO1i');/*pF4*/
INSERT INTO factory(id, name, exportid, password) VALUES(4, 'Factory Five', 0,'$2y$16$tmGNZur/mo4i/WhoOTR40OjRIoLDQMQD.AYvb51dHbT.1cPc2ojDm');/*pF5*/

DELETE FROM product;
INSERT INTO product(id, name, price_per_unit) VALUES(0, 'Wood', 10);
INSERT INTO product(id, name, price_per_unit) VALUES(1, 'Iron', 15);
INSERT INTO product(id, name, price_per_unit) VALUES(2, 'Electrical components', 20);
INSERT INTO product(id, name, price_per_unit) VALUES(3, 'Car', 100);
INSERT INTO product(id, name, price_per_unit) VALUES(4, 'Furniture', 50);

DELETE FROM transport_operator;
INSERT INTO transport_operator(id, name, price_per_unit, password) VALUES(0, 'CCC1', 0,'$2y$16$rNQMZBVxYeKuqp.0qpujSOjO0tg6yzSME9DV4VxXbDu0Dom/YTH2a'); /*pT1*/
INSERT INTO transport_operator(id, name, price_per_unit, password) VALUES(1, 'CCC2', 0,'$2y$16$.NP7.fYZSBCe9osNOIJjJOgsulr77qOle3ievVx7wne/FcgOIwA9q');/*pT2*/
INSERT INTO transport_operator(id, name, price_per_unit, password) VALUES(2, 'CCC3', 0,'$2y$16$QeN/My.qOPCJ.F0izfE7FefTka6C7rq8YixYqAXu9/uIUb0Jp0sn2');/*pT3*/
INSERT INTO transport_operator(id, name, price_per_unit, password) VALUES(3, 'CCC4', 0,'$2y$16$zcpleK/gEHiG5zhy1MHvZOAja3uBaWgdt1OsBCx2Qm2PC4.d1h4/2');/*pT4*/
INSERT INTO transport_operator(id, name, price_per_unit, password) VALUES(4, 'CCC5', 0,'$2y$16$IDh1wV7Fk..Yvx..UGXtdeRAtXz7dYXPBfGgZaiyJQ0gOFq5UAAwK');/*pT5*/

DELETE FROM ordering;
INSERT INTO ordering(id, id_factory, id_product) VALUES(0, 0, 1);
INSERT INTO ordering(id, id_factory, id_product) VALUES(1, 0, 2);
INSERT INTO ordering(id, id_factory, id_product) VALUES(2, 1, 0);
INSERT INTO ordering(id, id_factory, id_product) VALUES(3, 2, 4);
INSERT INTO ordering(id, id_factory, id_product) VALUES(4, 3, 1);
INSERT INTO ordering(id, id_factory, id_product) VALUES(5, 4, 1);
INSERT INTO ordering(id, id_factory, id_product) VALUES(6, 4, 2);
INSERT INTO ordering(id, id_factory, id_product) VALUES(7, 2, 1);

