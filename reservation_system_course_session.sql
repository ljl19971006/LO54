create table course_session
(
    id           bigint auto_increment
        primary key,
    courseCode   varchar(255) null,
    date         datetime     null,
    endHour      datetime     null,
    isfull       bit          null,
    locationCode varchar(255) null,
    max          int          null,
    startHour    datetime     null,
    course_id    bigint       null,
    constraint FKqp6t0vjo98oghqcp26ql0m22b
        foreign key (course_id) references course (id)
);

INSERT INTO reservation_system.course_session (id, isfull, max, course_id, location_id, end_date, start_date) VALUES (1, false, 40, 1, 1, null, '2021-05-08 18:15:00');