INSERT INTO "category_table" (category_title) VALUES  ('funny');
INSERT INTO "category_table" (category_title) VALUES  ('automobiles');
INSERT INTO "category_table" (category_title) VALUES  ('traveling');
INSERT INTO "category_table" (category_title) VALUES  ('coding');
INSERT INTO "category_table" (category_title) VALUES  ('nature');
INSERT INTO "category_table" (category_title) VALUES  ('selfies');


INSERT INTO "album_table" (album_title, album_user_referenceid, category_id) VALUES ('Night coding', '1234', '4');

INSERT INTO "image_table" (image_name, image_src, album_id) VALUES ('having fun', 'http://www.error.com/', 1)


-- docker run -d -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=user -p 5432:5432 postgres:10.5