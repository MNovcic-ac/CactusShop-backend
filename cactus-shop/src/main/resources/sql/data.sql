insert into roles values('2881691c-14d1-4200-99d4-5d39b19b5be0', 'admin role', 'admin'),
                        ('2881691c-14d1-4200-99d4-5d39b19b5be1', 'basic role', 'basic');

insert into users values('4623abfd-430c-47cf-958e-14611dc7208b', 'admin@admin.com', '100000', 'admin', 'admin', '$2a$10$N0Ux7Mt7jYkUAPdcAP4Zc.cTCOeiEJD3N1pYi/bxX8BWp4ywXnSny', '2881691c-14d1-4200-99d4-5d39b19b5be0'),
                        ('4623abfd-430c-47cf-958e-14611dc7208c', 'temp@temp.com', '100001', 'temp', 'temp', '$2a$10$N0Ux7Mt7jYkUAPdcAP4Zc.cTCOeiEJD3N1pYi/bxX8BWp4ywXnSny', '2881691c-14d1-4200-99d4-5d39b19b5be1');

insert into addresses values('ea02ea8a-a397-4738-9cc1-57477d8b4705', '21220', 'street name','19','town1');

insert into suppliers values('22a1d142-0179-41e1-bd74-12b62e4b5191', 'supplier@email.com', '99999', 'supplier', 'ea02ea8a-a397-4738-9cc1-57477d8b4705');

insert into product_types values('97cd54bb-803a-4b02-9335-c9978d02482d', 'type 1');

insert into products values('1','https://cgaxisimages.fra1.cdn.digitaloceanspaces.com/2014/07/05copy_0.jpg', 10, 100, 'prod desc', 'prod 1', 100, '22a1d142-0179-41e1-bd74-12b62e4b5191', '97cd54bb-803a-4b02-9335-c9978d02482d'),
('2','https://cgaxisimages.fra1.cdn.digitaloceanspaces.com/2014/07/05copy_0.jpg', 10, 100, 'prod desc', 'prod 2', 100, '22a1d142-0179-41e1-bd74-12b62e4b5191', '97cd54bb-803a-4b02-9335-c9978d02482d'),
('3','https://cgaxisimages.fra1.cdn.digitaloceanspaces.com/2014/07/05copy_0.jpg', 10, 100, 'prod desc', 'prod 3', 100, '22a1d142-0179-41e1-bd74-12b62e4b5191', '97cd54bb-803a-4b02-9335-c9978d02482d'),
('4','https://cgaxisimages.fra1.cdn.digitaloceanspaces.com/2014/07/05copy_0.jpg', 10, 100, 'prod desc', 'prod 4', 100, '22a1d142-0179-41e1-bd74-12b62e4b5191', '97cd54bb-803a-4b02-9335-c9978d02482d');
