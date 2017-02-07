-- to truncate data from a table which has foreign key constraints
SET FOREIGN_KEY_CHECKS = 0; 
TRUNCATE table1; 
SET FOREIGN_KEY_CHECKS = 1;