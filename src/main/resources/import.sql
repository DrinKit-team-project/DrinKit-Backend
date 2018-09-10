INSERT INTO cafe(id, created_date, modified_date, deleted, imageurl, name) values (1, 20180909081828, null, false, 'test/test.html', 'starbucks');
INSERT INTO cafe(id, created_date, modified_date, deleted, imageurl, name) values (2, 20180909081828, null, false, 'test/test2.html', 'Ediya');
INSERT INTO cafe(id, created_date, modified_date, deleted, imageurl, name) values (3, 20180909081828, null, false, 'test/test3.html', 'twoSomePlace');
INSERT INTO cafe(id, created_date, modified_date, deleted, imageurl, name) values (4, 20180909081828, null, false, 'test/test4.html', 'codesquad');
INSERT INTO cafe(id, created_date, modified_date, deleted, imageurl, name) values (5, 20180909081828, null, false, 'test/test5.html', 'koo’s coffee');

INSERT INTO menu(id, created_date, modified_date, calories, category, deleted, description, en_name, kr_name, review_count, total_ratings, cafe_id) values (1, 20180809081828, null, 0, 'coffee', false, '기본 아메리카노', 'americano', '아메리카노', 1, 0.0, 1);
INSERT INTO menu(id, created_date, modified_date, calories, category, deleted, description, en_name, kr_name, review_count, total_ratings, cafe_id) values (2, 20180810081828, null, 238, 'coffee', false, '우유가 섞인 라떼', 'latte', '라떼', 2, 0.0, 1);
INSERT INTO menu(id, created_date, modified_date, calories, category, deleted, description, en_name, kr_name, review_count, total_ratings, cafe_id) values (3, 20180813081828, null, 400, 'coffee', false, '달콤 쌉싸름한 카푸치노', 'cappuchino', '카푸치노', 3, 3.0, 1);
INSERT INTO menu(id, created_date, modified_date, calories, category, deleted, description, en_name, kr_name, review_count, total_ratings, cafe_id) values (4, 20180730081828, null, 800, 'non-coffee', false, '달다못해 꿀맛이 나는 초콜릿', 'chocolet', '초콜렛', 4, 4.0, 1);
INSERT INTO menu(id, created_date, modified_date, calories, category, deleted, description, en_name, kr_name, review_count, total_ratings, cafe_id) values (5, 20180504081828, null, 50, 'non-coffee', false, '기본 우유', 'milk', '우유', 5, 5.0, 1);

INSERT INTO cafe_category_list(cafe_id, category_name) values (1, 'coffee');
INSERT INTO cafe_category_list(cafe_id, category_name) values (1, 'non-coffee');

INSERT INTO tag(id, tag_name, search_count) values (1, '존맛없', 1);
INSERT INTO tag(id, tag_name, search_count) values (2, '개꿀맛', 2);
INSERT INTO tag(id, tag_name, search_count) values (3, 'americano', 3);
INSERT INTO tag(id, tag_name, search_count) values (4, '라떼', 4);
INSERT INTO tag(id, tag_name, search_count) values (5, '태그', 10);
INSERT INTO tag(id, tag_name, search_count) values (6, '태그2', 20);

INSERT INTO menu_and_tag(menu_id, tag_id) values (1, 1);
INSERT INTO menu_and_tag(menu_id, tag_id) values (1, 2);
INSERT INTO menu_and_tag(menu_id, tag_id) values (1, 3);
INSERT INTO menu_and_tag(menu_id, tag_id) values (2, 4);

INSERT INTO menu_price_per_sizes(menu_id, cost, size) values (1, 2000, 'small');
INSERT INTO menu_price_per_sizes(menu_id, cost, size) values (1, 2500, 'medium');
INSERT INTO menu_price_per_sizes(menu_id, cost, size) values (1, 3000, 'large');
INSERT INTO menu_price_per_sizes(menu_id, cost, size) values (2, 4000, 'free-size');
INSERT INTO menu_price_per_sizes(menu_id, cost, size) values (3, 5000, 'venti');

INSERT INTO menu_imageurls(menu_id, imageurls) values (1, 'www.menu1.com');
INSERT INTO menu_imageurls(menu_id, imageurls) values (1, 'www.menu11.com');
INSERT INTO menu_imageurls(menu_id, imageurls) values (2, 'www.menu2.com');
INSERT INTO menu_imageurls(menu_id, imageurls) values (3, 'www.menu3.com');
