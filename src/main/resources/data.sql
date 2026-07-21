INSERT INTO author (name)
VALUES ('John Doe'),
       ('Jane Smith'),
       ('Alice Johnson'),
       ('Bob Brown'),
       ('Charlie Davis'),
       ('Emily Wilson'),
       ('Frank Miller');

INSERT INTO book (id, name, fk_author_id, quantity, price)
VALUES (gen_random_uuid(), 'The Great Adventure', 1, 10, 19.99),
       (gen_random_uuid(), 'Mystery of the Night', 2, 5, 14.99),
       (gen_random_uuid(), 'Science and Technology', 3, 8, 29.99),
       (gen_random_uuid(), 'Historical Tales', 4, 12, 24.99),
       (gen_random_uuid(), 'Fantasy World', 5, 7, 17.99),
       (gen_random_uuid(), 'Romantic Escapades', 6, 9, 21.99),
       (gen_random_uuid(), 'Thriller Chronicles', 7, 6, 18.99);