create table course
(
    id    bigint auto_increment
        primary key,
    code  varchar(255) null,
    title varchar(255) null
);

INSERT INTO reservation_system.course (id, title, code) VALUES (1, 'JAVAEE ', 'lo54');
INSERT INTO reservation_system.course (id, title, code) VALUES (2, 'Base de donnee', 'bd50');