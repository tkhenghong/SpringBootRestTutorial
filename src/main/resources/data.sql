-- JPA Step 6: Insert some data after the server restarted
-- This file will be run automatically when server started
-- If you want to insert some data into the DB, you can create a sql script file (this file) in the /resources folder***
-- localhost:8080/h2-console
-- JDBC URL change to: jdbc:h2:mem:testdb . Leave the other default/blank
insert into user values(10001, sysdate(), 'AB');
insert into user values(10002, sysdate(), 'Jack');
insert into user values(10003, sysdate(), 'Jill');

-- JPA Table Relationship: Create Post from User
insert into post values(11001, 'My First Post', 10001); -- Post id, Post description, userId
insert into post values(11002, 'My Second Post', 10001);