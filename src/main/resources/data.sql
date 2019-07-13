-- JPA Step 6: Insert some data after the server restarted
-- This file will be run automatically when server started
-- If you want to insert some data into the DB, you can create a sql script file (this file) in the /resources folder***
-- localhost:8080/h2-console
-- JDBC URL change to: jdbc:h2:mem:testdb . Leave the other default/blank
insert into user values(1, sysdate(), 'AB');
insert into user values(2, sysdate(), 'Jack');
insert into user values(3, sysdate(), 'Jill');