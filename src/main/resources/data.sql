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

INSERT INTO category (name)
VALUES ('Adventure'),
       ('Mystery'),
       ('Science'),
       ('History'),
       ('Fantasy'),
       ('Romance'),
       ('Thriller');

INSERT INTO book_category (fk_book_id, fk_category_id)
SELECT b.id, c.id FROM book b, category c WHERE b.name = 'The Great Adventure' AND c.name = 'Adventure'
UNION ALL
SELECT b.id, c.id FROM book b, category c WHERE b.name = 'Mystery of the Night' AND c.name = 'Mystery'
UNION ALL
SELECT b.id, c.id FROM book b, category c WHERE b.name = 'Science and Technology' AND c.name = 'Science'
UNION ALL
SELECT b.id, c.id FROM book b, category c WHERE b.name = 'Historical Tales' AND c.name = 'History'
UNION ALL
SELECT b.id, c.id FROM book b, category c WHERE b.name = 'Fantasy World' AND c.name = 'Fantasy'
UNION ALL
SELECT b.id, c.id FROM book b, category c WHERE b.name = 'Romantic Escapades' AND c.name = 'Romance'
UNION ALL
SELECT b.id, c.id FROM book b, category c WHERE b.name = 'Thriller Chronicles' AND c.name = 'Thriller';