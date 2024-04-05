create user user1234 identified by user1234 quota unlimited on users;
grant connect, resource to user1234;

INSERT INTO DIR (ID, PARENT_ID, NAME) VALUES(0, null, '음식');

INSERT INTO DIR (ID, PARENT_ID, NAME) VALUES(1, 0, '한식');

INSERT INTO DIR (ID, PARENT_ID, NAME) VALUES(2, 1, '콩나물국밥');

INSERT INTO DIR (ID, PARENT_ID, NAME) VALUES(3, 1, '비빔밥');