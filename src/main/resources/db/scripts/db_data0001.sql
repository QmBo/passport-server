create table if not exists passport(
    id serial primary key,
    serial bigint unique not null,
    end_date date
);
insert into passport(serial, end_date) values
(1234567890, '2021-12-12'),
(9999999999, '2021-11-12'),
(1, '2021-2-12'),
(2, '2021-3-12'),
(3, '2021-4-12'),
(4, '2222-12-12'),
(5, '2222-12-12'),
(6, '2222-12-12'),
(7, '2222-12-12'),
(8, '2222-12-12')
