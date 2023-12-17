INSERT INTO todo(id, title, completed, ordering, url)
VALUES (1, 'Introduction to Quarkus', TRUE, 0, NULL);
INSERT INTO todo(id, title, completed, ordering, url)
VALUES (2, 'Hibernate with Panache', FALSE, 1, NULL);
INSERT INTO todo(id, title, completed, ordering, url)
VALUES (3, 'Visit Quarkus web site', FALSE, 2, 'https://quarkus.io');
INSERT INTO todo(id, title, completed, ordering, url)
VALUES (4, 'Star Quarkus project', FALSE, 3, 'https://github.com/quarkusio/quarkus/');
ALTER SEQUENCE todo_seq RESTART WITH 5;
