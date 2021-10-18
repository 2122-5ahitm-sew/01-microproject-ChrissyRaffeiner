insert into Teacher(id, firstname, lastname, email, age, salary) values (100, 'Hans', 'Moser', 'hans.moser@gmail.com', 24, 1200.00);
insert into Teacher(id, firstname, lastname, email, age, salary) values (101, 'Lara', 'Mittermayer', 'l.mayer@gmail.com', 32, 1400.00);

insert into Student(id, firstname, lastname, email, age) values (100, 'Christine', 'Raffeiner', 'c.raffeiner@gmail.com', 20);
insert into Student(id, firstname, lastname, email, age) values (101, 'Vanessa', 'Primetzhofer', 'primetzvan@gmail.com', 20);
insert into Student(id, firstname, lastname, email, age) values (102, 'Marah', 'Steigersdorfer', 'm.steigersdorfer@gmail.com', 18);
insert into Student(id, firstname, lastname, email, age) values (103, 'Sara', 'Feichtiger', 's.f@gmail.com', 18);
insert into Student(id, firstname, lastname, email, age) values (104, 'Nina', 'Weisengruber', 'weisengruber@gmail.com', 18);

insert into Lesson(id, name,  date, durationUnits, starttime, costs, teacher_id, type) values (100, 'Tanzkurs 1', current_date, 2.5, '12 Uhr', 20.0, 100, 2);
insert into Lesson(id, name, date, durationUnits, starttime, costs, teacher_id, type) values (101, 'Tanzkurs 2', current_date, 4.5, '17 Uhr', 22.0, 100, 3);