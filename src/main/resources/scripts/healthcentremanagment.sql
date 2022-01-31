create database clinicmanagement;
use clinicmanagement;

create table healthcentre
(
    healthcentreId int auto_increment
        primary key,
    kkfNumber      varchar(255) null,
    name           varchar(255) null
);

create table account
(
    id              int auto_increment
        primary key,
    accType         varchar(255) null,
    password        varchar(255) not null,
    username        varchar(255) not null,
    healthcentre_id int          not null,
    constraint UK_gex1lmaqpg0ir5g1f5eftyaa1
        unique (username),
    constraint FKg1hp02d2g8yuhyqpc10j55dse
        foreign key (healthcentre_id) references healthcentre (healthcentreId)
);

create table identification
(
    id     int auto_increment
        primary key,
    number varchar(255) not null,
    sex    varchar(255) not null,
    constraint UK_tl5y6i80v0dyiwdmntixt4xe9
        unique (number)
);

create table doctor
(
    doctorId          int auto_increment
        primary key,
    firstname         varchar(255) not null,
    lastname          varchar(255) not null,
    phone             varchar(255) null,
    specialty         varchar(255) null,
    healthcentre_id   int          not null,
    identification_id int          not null,
    constraint UK_osfypk7a9jwxka6yk1ahb5wvi
        unique (identification_id),
    constraint FKbqxndo7tw03o9j6c6eq35y1ea
        foreign key (healthcentre_id) references healthcentre (healthcentreId),
    constraint FKr6fuegs7sn1tqth5mvep1jnbf
        foreign key (identification_id) references identification (id)
);

create table employee
(
    employeeId        int auto_increment
        primary key,
    adress            varchar(255) null,
    firstname         varchar(255) not null,
    lastname          varchar(255) not null,
    salary            varchar(255) not null,
    healthcentre_id   int          not null,
    identification_id int          not null,
    constraint UK_535ifxup2plgdtpuanxmew923
        unique (identification_id),
    constraint FKak059xixxud894cax0t9aeg4r
        foreign key (identification_id) references identification (id),
    constraint FKp2k00k5l6579wexxir97jr8or
        foreign key (healthcentre_id) references healthcentre (healthcentreId)
);

create table medicine
(
    medicineId  int auto_increment
        primary key,
    brand       varchar(255) not null,
    description varchar(255) null,
    name        varchar(255) not null,
    stock       int          null
);

create table patient
(
    patientId         int auto_increment
        primary key,
    adress            varchar(255) null,
    contactnumber     varchar(255) not null,
    firstname         varchar(255) not null,
    lastname          varchar(255) not null,
    identification_id int          not null,
    constraint UK_iajq2gp0gan7oxcsufg3bg8ca
        unique (identification_id),
    constraint FK8o4h3babqpxusgiea4ubx303o
        foreign key (identification_id) references identification (id)
);

create table appointment
(
    appointmentId   int auto_increment
        primary key,
    appointmentdate date         null,
    appointmentTime time         null,
    section         varchar(255) null,
    healthcentre_id int          not null,
    patient_id      int          not null,
    constraint UK_jmehgyu5wgvc631ase47gynav
        unique (appointmentdate),
    constraint FK4apif2ewfyf14077ichee8g06
        foreign key (patient_id) references patient (patientId),
    constraint FK6vvevg9c2qrx7rsaglthkk1le
        foreign key (healthcentre_id) references healthcentre (healthcentreId)
);

create table task
(
    taskId      int auto_increment
        primary key,
    department  varchar(255) null,
    description varchar(255) not null,
    done        bit          not null
);

create table employee_task
(
    task_id     int not null,
    employee_id int not null,
    primary key (task_id, employee_id),
    constraint FK5hetq56ystowq0s2phuxk4mh4
        foreign key (employee_id) references employee (employeeId),
    constraint FK60hfkuxeb5wwd56luggd8b0nh
        foreign key (task_id) references task (taskId)
);

create table treatment
(
    treatmentId   int auto_increment
        primary key,
    description   varchar(255) null,
    treatmentdate date         null,
    doctor_id     int          null,
    patient_id    int          null,
    constraint FK7pfug4y8vnudjrq61qdi3h4dj
        foreign key (doctor_id) references doctor (doctorId),
    constraint FKjpqauh9f08891a82no3i8aq7o
        foreign key (patient_id) references patient (patientId)
);

create table prescription
(
    prescriptionId    int auto_increment
        primary key,
    dose              int not null,
    medicine_provided bit not null,
    medicine_id       int null,
    treatment_id      int not null,
    constraint UK_pddstcynbex6rqogm360t095m
        unique (treatment_id),
    constraint FK42wq6l9p044tmxv6emix0ejcq
        foreign key (medicine_id) references medicine (medicineId),
    constraint FKcl7r0qbuol6rm0qw6ouqk3tm1
        foreign key (treatment_id) references treatment (treatmentId)
);

insert into account(accType, password, username)
values ('admin', 'admin123', 'admin');

