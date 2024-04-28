insert into Course (id, title, credits)
    values('DEV1', 'Developpement I', 10),
        ('DEV2', 'Developpement II', 10),
        ('MIC2', 'Microprocesseurs II', 3);

insert into Student (name, gender, section)
    values('Youssef', 'MALE', 'GESTION'),
        ('Tareq', 'MALE', 'RESEAU'),
        ('Azad', 'MALE', 'GESTION');

insert into STUDENT_COURSES (STUDENTS_ID, COURSES_ID)
    values(1, 'DEV1'),
        (2, 'DEV2');
