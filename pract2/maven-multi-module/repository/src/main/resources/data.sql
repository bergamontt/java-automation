INSERT INTO person (name, surname, age, address)
VALUES
    ('John', 'Smith', 18 + FLOOR(RAND() * 50), 'Kyiv, Street '),
    ('Anna', 'Johnson', 18 + FLOOR(RAND() * 50), 'Lviv, Street '),
    ('Mike', 'Brown', 18 + FLOOR(RAND() * 50), 'Odesa, Street '),
    ('Sophie', 'Taylor', 18 + FLOOR(RAND() * 50), 'Kharkiv, Street ' ),
    ('Alex', 'Wilson', 18 + FLOOR(RAND() * 50), 'Dnipro, Street ');