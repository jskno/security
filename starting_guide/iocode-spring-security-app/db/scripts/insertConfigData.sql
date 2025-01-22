SET search_path TO stdn;

insert into stdn.students (id, firstname, lastname, email, age)
values (nextval('students_seq'),'Jose', 'Cano', 'jskno@gmail.com', 48);

insert into stdn.students (id, firstname, lastname, email, age)
values (nextval('students_seq'),'Alvaro', 'Cano', 'alvaro@gmail.com', 9);

insert into stdn.students (id, firstname, lastname, email, age)
values (nextval('students_seq'),'Eva', 'Cano', 'eve@gmail.com', 5);

SET search_path TO sec;

INSERT INTO sec.users
(firstname, lastname, "password", u_id, username, roles)
VALUES('Alvaro', 'Cano', '$2a$10$O4PSd97ISzaL97HtJpE/ru..kZUepCS7YwPqOnDs9.RZF/OSOjI/W', 'bde3eb10-9371-498c-a526-160e6fd70e2b', 'alvkno', '{USER,ADMIN}');
