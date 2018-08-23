INSERT INTO cafe(id, created_date, modified_date, deleted, imageurl, name) values (1, 20180909081828, null, false, "test/test.html", "starbucks");

INSERT INTO menu(id, created_date, modified_date, calories, category, deleted, description, en_name, kr_name, total_ratings, cafe_id) values (1, 20180909081828, null, 238, "coffee", false, "basic_coffee", "americano", "아메리카노", 0.0, 1);
INSERT INTO menu(id, created_date, modified_date, calories, category, deleted, description, en_name, kr_name, total_ratings, cafe_id) values (2, 20180909081828, null, 238, "coffee", false, "basic_coffee", "latte", "라떼", 0.0, 1);
INSERT INTO menu(id, created_date, modified_date, calories, category, deleted, description, en_name, kr_name, total_ratings, cafe_id) values (3, 20180909081828, null, 238, "coffee", false, "basic_coffee", "cappuchino", "카푸치노", 0.0, 1);
INSERT INTO menu(id, created_date, modified_date, calories, category, deleted, description, en_name, kr_name, total_ratings, cafe_id) values (4, 20180909081828, null, 238, "non-coffee", false, "basic_coffee", "chocolet", "초콜렛", 0.0, 1);
INSERT INTO menu(id, created_date, modified_date, calories, category, deleted, description, en_name, kr_name, total_ratings, cafe_id) values (5, 20180909081828, null, 238, "non-coffee", false, "basic_coffee", "milk", "우유", 0.0, 1);

INSERT INTO cafe_category_list(cafe_id, category_name) values (1, "coffee");
INSERT INTO cafe_category_list(cafe_id, category_name) values (1, "non-coffee");