INSERT INTO employee(id, fk_branch_id, name, last_name, email, active) values (10, 1,'test', 'test','dasdsada@dsdada.com',1);
INSERT INTO employee(id, fk_branch_id, name, last_name, email, active) values (11, 2,'test1', 'test1','dasdsada@dsdada1.com',1);
INSERT INTO employee(id, fk_branch_id, name, last_name, email, active) values (12, 1,'test2', 'test2','dasdsada@dsdada2.com',0);
INSERT INTO employee(id, fk_branch_id, name, last_name, email, active) values (15, 1,'test3', 'test3','dasdsada@dsdada3.com',1);
INSERT INTO `branches` (`id`, `address`, `city`, `email`, `name`, `password`, `active`) VALUES (10000000001, 'TestAdresa', 'TestGrad', 'TestEmail', 'TestName', 'TestPaswword', '1');
INSERT INTO `branches` (`id`, `address`, `city`, `email`, `name`, `password`, `active`) VALUES (10000000002, 'TestAdresa1', 'TestGrad1', 'TestEmail1', 'TestName1', 'TestPaswword1', '0');
INSERT INTO `branches` (`id`, `address`, `city`, `email`, `name`, `password`, `active`) VALUES (10000000003, 'TestAdresa2', 'TestGrad2', 'TestEmail2', 'TestName2', 'TestPaswword2', '1');
INSERT INTO `branches` (`id`, `address`, `city`, `email`, `name`, `password`, `active`) VALUES (10000000004, 'TestAdresa3', 'TestGrad3', 'TestEmail3', 'TestName3', 'TestPaswword3', '1');
INSERT INTO `managers` (`id`, `name`, `last_name`, `email`, `password`, `fk_branch_id`, `active`) VALUES (10000000001, 'TestName', 'TestLastName', 'TestEmail', 'TestPaswword', '3', '1');
INSERT INTO `managers` (`id`, `name`, `last_name`, `email`, `password`, `fk_branch_id`, `active`) VALUES (10000000002, 'TestName1', 'TestLastName1', 'TestEmail1', 'TestPaswword1', '3', '1');
INSERT INTO `managers` (`id`, `name`, `last_name`, `email`, `password`, `fk_branch_id`, `active`) VALUES (10000000003, 'TestName2', 'TestLastName2', 'TestEmail2', 'TestPaswword2', '2', '1');
INSERT INTO `managers` (`id`, `name`, `last_name`, `email`, `password`, `fk_branch_id`, `active`) VALUES (10000000004, 'TestName3', 'TestLastName3', 'TestEmail3', 'TestPaswword3', '1', '0');
INSERT INTO `managers` (`id`, `name`, `last_name`, `email`, `password`, `fk_branch_id`, `active`) VALUES (10000000005, 'TestName4', 'TestLastName4', 'TestEmail4', 'TestPaswword4', '1', '1');