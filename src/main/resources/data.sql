create user user1234 identified by user1234 quota unlimited on users;
grant connect, resource to user1234;

INSERT INTO DIR (id, PARENT_ID, NAME) VALUES(1, null, '음식');
INSERT INTO DIR (id, PARENT_ID, NAME) VALUES(2, 1, '한식');
INSERT INTO DIR (id, PARENT_ID, NAME) VALUES(3, 2, '콩나물국밥');
INSERT INTO DIR (id, PARENT_ID, NAME) VALUES(4, 2, '비빔밥');
INSERT INTO DIR (id, PARENT_ID, NAME) VALUES(5, 1, '양식');
INSERT INTO DIR (id, PARENT_ID, NAME) VALUES(6, 5, '치킨');
INSERT INTO DIR (id, PARENT_ID, NAME) VALUES(7, 5, '햄버거');
INSERT INTO DIR (id, PARENT_ID, NAME) VALUES(8, 1, '일식');
INSERT INTO DIR (id, PARENT_ID, NAME) VALUES(9, 8, '라멘');
INSERT INTO DIR (id, PARENT_ID, NAME) VALUES(10, 8, '돈까스');