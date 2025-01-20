SET search_path TO stdn;

insert into stdn.students (id, firstname, lastname, email, age)
values (nextval('students_seq'),'Jose', 'Cano', 'jskno@gmail.com', 48);

insert into stdn.students (id, firstname, lastname, email, age)
values (nextval('students_seq'),'Alvaro', 'Cano', 'alvaro@gmail.com', 9);

insert into stdn.students (id, firstname, lastname, email, age)
values (nextval('students_seq'),'Eva', 'Cano', 'eve@gmail.com', 5);
