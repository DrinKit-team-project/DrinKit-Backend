INSERT INTO cafe(id, created_date, modified_date, deleted, imageurl, name) values (1, 20180909081828, null, false, "test/test.html", "starbucks");
INSERT INTO cafe(id, created_date, modified_date, deleted, imageurl, name) values (2, 20180909081828, null, false, "test/test2.html", "Ediya");
INSERT INTO cafe(id, created_date, modified_date, deleted, imageurl, name) values (3, 20180909081828, null, false, "test/test3.html", "twoSomePlace");
INSERT INTO cafe(id, created_date, modified_date, deleted, imageurl, name) values (4, 20180909081828, null, false, "test/test4.html", "codesquad");
INSERT INTO cafe(id, created_date, modified_date, deleted, imageurl, name) values (5, 20180909081828, null, false, "test/test5.html", "koo's coffee");

INSERT INTO menu(id, created_date, modified_date, calories, category, deleted, description, en_name, kr_name, total_ratings, cafe_id) values (1, 20180909081828, null, 238, "coffee", false, "basic_coffee", "americano", "아메리카노", 0.0, 1);
INSERT INTO menu(id, created_date, modified_date, calories, category, deleted, description, en_name, kr_name, total_ratings, cafe_id) values (2, 20180909081828, null, 238, "coffee", false, "basic_coffee", "latte", "라떼", 0.0, 1);
INSERT INTO menu(id, created_date, modified_date, calories, category, deleted, description, en_name, kr_name, total_ratings, cafe_id) values (3, 20180909081828, null, 238, "coffee", false, "basic_coffee", "cappuchino", "카푸치노", 3.0, 1);
INSERT INTO menu(id, created_date, modified_date, calories, category, deleted, description, en_name, kr_name, total_ratings, cafe_id) values (4, 20180909081828, null, 238, "non-coffee", false, "basic_coffee", "chocolet", "초콜렛", 4.0, 1);
INSERT INTO menu(id, created_date, modified_date, calories, category, deleted, description, en_name, kr_name, total_ratings, cafe_id) values (5, 20180909081828, null, 238, "non-coffee", false, "basic_coffee", "milk", "우유", 5.0, 1);

INSERT INTO cafe_category_list(cafe_id, category_name) values (1, "coffee");
INSERT INTO cafe_category_list(cafe_id, category_name) values (1, "non-coffee");

INSERT INTO tag(id, tag_name) values (1, "존맛없");
INSERT INTO tag(id, tag_name) values (2, "개꿀맛");
INSERT INTO tag(id, tag_name) values (3, "americano");
INSERT INTO tag(id, tag_name) values (4, "라떼");

INSERT INTO menu_and_tag(menu_id, tag_id) values (1, 1);
INSERT INTO menu_and_tag(menu_id, tag_id) values (1, 2);
INSERT INTO menu_and_tag(menu_id, tag_id) values (1, 3);
INSERT INTO menu_and_tag(menu_id, tag_id) values (2, 4);