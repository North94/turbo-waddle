insert into categories (id, name)
values
    (gen_random_uuid(), 'Zdrowie'),
    (gen_random_uuid(), 'Turystyka'),
    (gen_random_uuid(), 'Hobby'),
    (gen_random_uuid(), 'Gry'),
    (gen_random_uuid(), 'Biznes'),
    (gen_random_uuid(), 'Edukacja'),
    (gen_random_uuid(), 'Uroda'),
    (gen_random_uuid(), 'Styl'),
    (gen_random_uuid(), 'Zwierzęta'),
    (gen_random_uuid(), 'Zakupy'),
    (gen_random_uuid(), 'Praca'),
    (gen_random_uuid(), 'Wakacje'),
    (gen_random_uuid(), 'Sport'),
    (gen_random_uuid(), 'Rozrywka'),
    (gen_random_uuid(), 'Zabawy'),
    (gen_random_uuid(), 'Praca'),
    (gen_random_uuid(), 'Lekcje'),
    (gen_random_uuid(), 'Dzieci'),
    (gen_random_uuid(), 'Przyszłość'),
    (gen_random_uuid(), 'Jakość'),
    (gen_random_uuid(), 'Leki'),
    (gen_random_uuid(), 'aaa'),
    (gen_random_uuid(), 'sss'),
    (gen_random_uuid(), 'qqq'),
    (gen_random_uuid(), 'www'),
    (gen_random_uuid(), 'eee'),
    (gen_random_uuid(), 'rrr'),
    (gen_random_uuid(), 'ttt'),
    (gen_random_uuid(), 'yyy'),
    (gen_random_uuid(), 'uuu'),
    (gen_random_uuid(), 'iii'),
    (gen_random_uuid(), 'ooo'),
    (gen_random_uuid(), 'ppp'),
    (gen_random_uuid(), 'ddd'),
    (gen_random_uuid(), 'fff'),
    (gen_random_uuid(), 'ggg'),
    (gen_random_uuid(), 'hhh'),
    (gen_random_uuid(), 'jjj'),
    (gen_random_uuid(), 'jjj'),
    (gen_random_uuid(), 'kkk'),
    (gen_random_uuid(), 'lll'),
    (gen_random_uuid(), 'zzz'),
    (gen_random_uuid(), 'xxx'),
    (gen_random_uuid(), 'ccc');


insert into questions (id, name, category_id)
values  (gen_random_uuid(), 'Gdzie najlepiej spędzić kultur w Polsce', (select id from categories where name = 'Gry')),
        (gen_random_uuid(), 'Gdzie najlepiej spędzić wakacj w Polsce', (select id from categories where name = 'Turystyka'));

insert into questions (id, name, category_id)
values  (gen_random_uuid(), 'Gdzie najlepiej spędzić kultre w Polsce', (select id from categories where name = 'Edukacja')),
        (gen_random_uuid(), 'Gdzie najlepiej spędzić wakcje w Polsce', (select id from categories where name = 'Edukacja'));


