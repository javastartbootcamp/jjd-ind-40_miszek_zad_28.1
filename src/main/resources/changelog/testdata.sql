INSERT INTO category (name, description)
VALUES
    ('Elektornika', 'Bardzo fajne przedmioty'),
    ('Moto', 'Samochody, skutery i ciężarówki'),
    ('Meble', 'Meble użytku domowego');

INSERT INTO offer (title, description, img_url, price, category_id)
VALUES
    ('Telewizor', 'Super telewizor o przekątnej 55 cali', 'http://blabla2.jpg', 1299, 1),
    ('Kino domowe', 'Wypasione kino domowe firmy Sony, gra tak, że można robić festyn', 'http://blabla3.jpg', 699, 1),
    ('Stół drewniany', 'Idealny stół drewniany dla rodziny, 6 krzeseł gratis', 'http://blabla4.jpg', 2699, 3),
    ('Tesla model X', 'samochód elektryczny', 'http://blabla5.jpg', 170000, 2),
    ('Kia Carens', 'Idealny mini van dla 5cio osobowej rodziny', 'http://blabla5.jpg', 2699, 2);