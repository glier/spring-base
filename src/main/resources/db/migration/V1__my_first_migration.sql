create table products (
    id          bigserial primary key,
    title       varchar(255),
    cost        int,
    category_id bigserial,
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);
create table categories (
    id          bigserial primary key,
    title       varchar(255),
    created_at  timestamp,
    updated_at  timestamp
);
create table categories_products (
    category_id bigserial not null,
    products_id bigserial not null
);
alter table categories_products
    add constraint UK_rnlo43ssc3pd408u62he9udsb
        unique (products_id);
alter table categories_products
    add constraint FKad19ea8ca86lh5f3wmgg83vmj
        foreign key (products_id)
            references products;
alter table categories_products
    add constraint FK2a3u5mbtmtq3d4s5abajhhksf
        foreign key (category_id)
            references categories;
alter table products
    add constraint FKog2rp4qthbtt2lfyhfo32lsw9
        foreign key (category_id)
            references categories;

insert into categories (title) values
('Fruits'),
('Vegetables');

insert into products (cost, title, category_id)
values
(11,  'Grapes', 1),
(16,  'Lime', 1),
(15,  'Lemon', 1),
(15,  'Cherry', 1),
(20,  'Blueberry', 1),
(13,  'Watermelon', 1),
(25,  'Peach', 1),
(27,  'Pineapple', 1),
(30,  'Strawberry', 1),
(30,  'Coconut', 1),
(30,  'Pear', 1),
(30,  'Apricot', 1),
(150, 'Avocado', 1),
(69,  'Blackberry', 1),
(36,  'Grapefruit', 1),
(78,  'Kiwi', 1),
(79,  'Mango', 1),
(81,  'Plum', 1),
(30,  'Potatoes', 2),
(27,  'Cucumber', 2),
(27,  'Tomatoes', 2),
(110,  'Eggplant', 2),
(110,  'Pumpkin', 2),
(35,  'Carrot', 2),
(79,  'Bow', 2);

insert into categories_products (category_id, products_id) values
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 7),
(1, 8),
(1, 9),
(1, 10),
(1, 11),
(1, 12),
(1, 13),
(1, 14),
(1, 15),
(1, 16),
(1, 17),
(1, 18),
(2, 19),
(2, 20),
(2, 21),
(2, 22),
(2, 23),
(2, 24),
(2, 25);

