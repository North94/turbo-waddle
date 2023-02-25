--liquibase formatted sql

--changeset Northo:001_1
DROP table if exists answers CASCADE;
DROP table if exists questions CASCADE;
DROP table if exists categories CASCADE ;

create table categories (
                                          category_id   uuid not null
                                          primary key,
                                          name varchar(255)
    );
create table questions (
                                         question_id          uuid not null
                                         primary key,
                                         created     timestamp,
                                         modified    timestamp,
                                         name        varchar(255),
    category_id uuid
        references categories
    );
create table answers (
                                       answer_id          uuid not null
                                       primary key,
                                       name        varchar(255),
    question_id uuid
        references questions
    );
--changeset Northo:001_2
insert into categories (category_id, name) values
                                      (gen_random_uuid(), 'Zdrowie'),
                                      (gen_random_uuid(), 'Zwierzęta'),
                                      (gen_random_uuid(), 'Turystyka'),
                                      (gen_random_uuid(), 'Uroda i Styl'),
                                      (gen_random_uuid(), 'Kultura'),
                                      (gen_random_uuid(), 'Edukacja'),
                                      (gen_random_uuid(), 'Gry'),
                                      (gen_random_uuid(), 'Hobby'),
                                      (gen_random_uuid(), 'Dom i Ogród'),
                                      (gen_random_uuid(), 'Biznes'),
                                      (gen_random_uuid(), 'Finanse'),
                                      (gen_random_uuid(), 'Kulinaria'),
                                      (gen_random_uuid(), 'Komputery'),
                                      (gen_random_uuid(), 'Osobiste'),
                                      (gen_random_uuid(), 'Motoryzacja'),
                                      (gen_random_uuid(), 'Polityka'),
                                      (gen_random_uuid(), 'Praca'),
                                      (gen_random_uuid(), 'Prezenty'),
                                      (gen_random_uuid(), 'Zakupy'),
                                      (gen_random_uuid(), 'Elektronika'),
                                      (gen_random_uuid(), 'Rozrywka'),
                                      (gen_random_uuid(), 'Sex'),
                                      (gen_random_uuid(), 'Związki'),
                                      (gen_random_uuid(), 'Inne');

insert into questions (question_id, name, category_id) values
    (gen_random_uuid(), 'Jakie są najzdrowsze warzywa?', (select category_id from categories where name = 'Zdrowie'));

insert into questions (question_id, name, category_id) values
(gen_random_uuid(), 'Dlaczego warto uczyć się programowania', (select category_id from categories where name = 'Edukacja')),
(gen_random_uuid(), 'Dlaczego Java jest dobrym językiem na start', (select category_id from categories where name = 'Edukacja'));

insert into questions (question_id, name, category_id) values
(gen_random_uuid(), 'Gdzie najlepiej spędzić wakacje z Polsce', (select category_id from categories where name = 'Turystyka')),
(gen_random_uuid(), 'Gdzie najlepiej spędzić wakacje z Europie', (select category_id from categories where name = 'Turystyka'));


insert into answers (answer_id, name, question_id) values
(gen_random_uuid(), 'Marchewka', (select question_id from questions where name = 'Jakie są najzdrowsze warzywa?')),
(gen_random_uuid(), 'Brokuł', (select question_id from questions where name = 'Jakie są najzdrowsze warzywa?')),
(gen_random_uuid(), 'Dynia', (select question_id from questions where name = 'Jakie są najzdrowsze warzywa?')),
(gen_random_uuid(), 'Groch', (select question_id from questions where name = 'Jakie są najzdrowsze warzywa?'));

insert into answers (answer_id, name, question_id) values
(gen_random_uuid(), 'Gdańsk', (select question_id from questions where name = 'Gdzie najlepiej spędzić wakacje z Polsce')),
(gen_random_uuid(), 'Bieszczady', (select question_id from questions where name = 'Gdzie najlepiej spędzić wakacje z Polsce')),
(gen_random_uuid(), 'Mazury', (select question_id from questions where name = 'Gdzie najlepiej spędzić wakacje z Polsce'));



