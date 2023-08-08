--liquibase formatted sql
--changeset Kirill:book_data_1
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM books
INSERT INTO books (title, author, year_of_publishing) VALUES ('Война и мир', 'Лев Толстой', 1869);
INSERT INTO books (title, author, year_of_publishing) VALUES ('1984', 'Джордж Оруэлл', 1949);
INSERT INTO books (title, author, year_of_publishing) VALUES ('Гордость и предубеждение', 'Джейн Остин', 1813);
INSERT INTO books (title, author, year_of_publishing) VALUES ('Унесенные ветром', 'Маргарет Митчелл', 1936);
INSERT INTO books (title, author, year_of_publishing) VALUES ('Великий Гэтсби', 'Фрэнсис Скотт Фицджеральд', 1925);
INSERT INTO books (title, author, year_of_publishing) VALUES ('Мастер и Маргарита', 'Михаил Булгаков', 1967);
INSERT INTO books (title, author, year_of_publishing) VALUES ('Преступление и наказание', 'Фёдор Достоевский', 1866);
INSERT INTO books (title, author, year_of_publishing) VALUES ('Алиса в Зазеркалье', 'Льюис Кэрролл', 1871);
INSERT INTO books (title, author, year_of_publishing) VALUES ('Властелин колец: Братство Кольца', 'Дж. Р. Р. Толкин', 1954);
INSERT INTO books (title, author, year_of_publishing) VALUES ('Гарри Поттер и философский камень', 'Дж. К. Роулинг', 1997);
INSERT INTO books (title, author, year_of_publishing) VALUES ('Анна Каренина', 'Лев Толстой', 1877);
INSERT INTO books (title, author, year_of_publishing) VALUES ('Тихий Дон', 'Михаил Шолохов', 1928);
INSERT INTO books (title, author, year_of_publishing) VALUES ('Гарри Поттер и узник Азкабана', 'Дж. К. Роулинг', 1999);
INSERT INTO books (title, author, year_of_publishing) VALUES ('Сто лет одиночества', 'Габриэль Гарсиа Маркес', 1967);
INSERT INTO books (title, author, year_of_publishing) VALUES ('Десять негритят', 'Агата Кристи', 1939);
INSERT INTO books (title, author, year_of_publishing) VALUES ('Ветры зимы', 'Джордж Р. Р. Мартин', 2011);
INSERT INTO books (title, author, year_of_publishing) VALUES ('Маленькие женщины', 'Луиза Мэй Олкотт', 1868);
INSERT INTO books (title, author, year_of_publishing) VALUES ('Убийство в "Восточном экспрессе"', 'Агата Кристи', 1934);
INSERT INTO books (title, author, year_of_publishing) VALUES ('Дракула', 'Брэм Стокер', 1897);
INSERT INTO books (title, author, year_of_publishing) VALUES ('Шум и ярость', 'Уильям Фолкнер', 1929);
