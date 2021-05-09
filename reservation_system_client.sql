create table client
(
    Id                int auto_increment,
    lastName          char(30)     null,
    firstName         char(30)     null,
    address           varchar(200) null,
    phone             int          null,
    email             varchar(50)  null,
    course_Session_Id int          null,
    constraint Client_Id_uindex
        unique (Id)
);

alter table client
    add primary key (Id);

