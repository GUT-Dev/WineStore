INSERT INTO land (name)
VALUES ('Ukraine'),
       ('France'),
       ('German'),
       ('Italy'),
       ('Brazil'),
       ('Spain');

INSERT INTO brand (name)
VALUES ('Cavicchioli'),
       ('Castelnuovo'),
       ('Familie Bougrier'),
       ('Dominio de Punctum'),
       ('Шабо');

INSERT INTO wine (name, descriptions, type, sweetness, strength, sugar_amount, ean, image_url, price, land_id, region,
                  brand_id, created_at)
VALUES ('Cavicchioli Lambrusco Bianco',
        'Солодкий з легкою ігристістю та приємними фруктовими нотами смак та свіжий фруктовий аромат.', 3, 2, 7.5, 50,
        09948205, null, 17700, 4, 'Емілія Романья', 1, '2022-03-24 18:23:34'),
       ('Cavicchioli Lambrusco Rosato',
        'Солодкий та гармонійний. Свіжий аромат з квітковими нотами аромат.', 3, 2, 7.5, 50,
        13382376, null, 17700, 4, 'Емілія Романья', 1, '2022-03-24 18:23:34'),
       ('Cavicchioli Lambrusco Rosso',
        'Солодкий та гармонійний. Свіжий аромат з квітковими нотами аромат.', 3, 2, 7.5, 50,
        64618002, null, 17700, 4, 'Емілія Романья', 1, '2022-03-24 18:23:34'),
       ('Castelnuovo Vino Bianco',
        'Солодкий, збалансований смак з освіжаючою кислотністю смак. Освіжаючий букет тропічних фруктів з нотками персику аромат.',
        0, 2, 11.0, 35,
        10369403, null, 14900, 4, 'Венето', 2, '2022-03-24 18:23:34'),
       ('Castelnuovo Vino Rosso',
        'Солодкий, збалансований смак з освіжаючою кислотністю смак. Освіжаючий букет тропічних фруктів з нотками персику аромат.',
        1, 2, 11.0, 35,
        80629411, null, 14900, 4, 'Венето', 2, '2022-03-24 18:23:34'),
       ('Bougrier Rose d’Anjou',
        'М''який, гармонійний смак. Фруктовий, з нотками кавуна, полуниці.',
        2, 1, 11.0, 16,
        09384833, null, 36700, 2, 'Долина Лаури', 3, '2022-03-24 18:23:34'),
       ('Dominio de Punctum Finca Fabian Tempranillo',
        'У смаку свіжість і гармонійність з довгим післясмаком. У букеті - червона ягода і вишня.',
        1, 0, 13.5, 2,
        15055377, null, 25200, 6, 'Кастіліа-Ла Манча', 4, '2022-03-24 18:23:34'),
       ('Shabo Gold Brut',
        'Освіжаючий, з цитрусовими нотами смак та яскравий аромат з нотами персиків і абрикосівю',
        3, 2, 11.0, null,
        14839152, null, 16700, 2, 'деська область', 5, '2022-03-24 18:23:34'),
       ('Shabo Chardonnay Reserve',
        'Округлий маслянистий, але в той же час - свіжий смак. Аромат 	яскравий, з нотами персика, екзотичних фруктів з легкими тонами цитрусових, меду та ванілі.',
        0, 0, 13.5, null,
        06975928, null, 29700, 2, 'Одеська область', 5, '2022-03-24 18:23:34');

INSERT INTO address (post_code, land, city, street, home_number)
VALUES ('87345', 'Україна', 'Чернівці', 'Головна', '13/8'),
       ('17544', 'Україна', 'Київ', 'Обїзна', '11/8 кв.6');

INSERT INTO users (first_name, last_name, password, phone_number, email, enabled, baned, ban_reason, address_id,
                   created_at, api_key)
VALUES ('admin', 'admin', '$2a$10$WtKmWu9R.e1schV8NwPL8OrZoQt2dEi9tdokkuuAbNN37Jo5aE9dq', '+380950067602',
        'admin@gmail.com', true, false, null, null,
        '2022-03-24 18:23:34', 'admin'),
       ('general', 'manager', '$2a$10$J2BKR6TI0sr2cBVkGhYQLuUA/yTFqbBi5bMo4yokQWyX.yAakxGVy', '+380981167613',
        'manager@gmail.com', true, false, null, 1,
        '2022-03-24 18:23:34', 'manager'),
       ('Валерій', 'лобода', '$2a$10$YN0xiAvy60PXhiCMHHD.9eeE4actd5QJ/FqgpwhFnsNkZMesPR056', '+380661068442',
        'valera.sloboda@gmail.com', true, false, null, 2,
        '2022-03-24 18:23:34', 'valera'),
       ('Антон', 'Геращенко', '$2a$10$5UVaue5reFplTvbvgNdcIel0D3g3ePuDzkp/TlvQl0BbW3KtzS7FK', '+3809512365501',
        'anton78@gmail.com', true, false, null, null,
        '2022-03-24 18:23:34', 'anton'),
       ('Ігор', 'Тополяк', '$2a$10$XzCRb20EgFRj4EkS/V7Yw.8djy2ooqhUloz3PtTqUr0vBPwHZaf2q', '+3809512111000',
        'ihortopola@gmail.com', false, false, null, null,
        '2022-03-24 18:23:34', 'ihor'),
       ('Степан', 'Валерійчук', '$2a$10$qz5/ZLQkQjkOM6u4LZKjfeRoKC2F/KCpmMOQE1hd.F8wDJVmbj.hu', '+3809511265511',
        'stepan.val13@gmail.com', true, true,
        'Мати в коментарях, фейкові замовлення', null, '2022-03-24 18:23:34', 'stepan');

INSERT INTO user_roles (user_id, role)
VALUES (1, 1),
       (1, 2),
       (2, 1);

INSERT INTO customer_review (user_id, wine_id, message, rating, confirm)
VALUES (1, 1, 'Дуже смачне вино', 5, true),
       (2, 1, 'Ну таке собі на 3', 3, true),
       (3, 1, 'Що за гівно', 1, false),
       (4, 2, 'Не погано, рекомендую', 4, true);

