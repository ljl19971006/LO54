create table location
(
    id   bigint auto_increment
        primary key,
    city varchar(255) null
);

INSERT INTO reservation_system.location (id, city) VALUES (1, 'Belfort');
INSERT INTO reservation_system.location (id, city) VALUES (2, 'Montbeliard');
INSERT INTO reservation_system.location (id, city) VALUES (3, 'Sevenans');