--liquibase formatted sql
--changeset Kirill:person_data_1
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM people
INSERT INTO people (name, date_of_birth) VALUES ('Иван Иванов', '15.05.1990');
INSERT INTO people (name, date_of_birth) VALUES ('Екатерина Петрова', '20.10.1985');
INSERT INTO people (name, date_of_birth) VALUES ('Алексей Сидоров', '08.03.1992');
INSERT INTO people (name, date_of_birth) VALUES ('Мария Кузнецова', '03.07.1988');
INSERT INTO people (name, date_of_birth) VALUES ('Андрей Васильев', '25.11.1995');
INSERT INTO people (name, date_of_birth) VALUES ('Анна Николаева', '12.09.1991');
INSERT INTO people (name, date_of_birth) VALUES ('Дмитрий Лебедев', '18.04.1987');
INSERT INTO people (name, date_of_birth) VALUES ('Ольга Морозова', '28.02.1993');
INSERT INTO people (name, date_of_birth) VALUES ('Павел Романов', '07.08.1998');
INSERT INTO people (name, date_of_birth) VALUES ('Елена Смирнова', '10.12.1984');

