Insert INTO users(emailAddress, username)
  VALUES("bob@email.com","bob123");

INSERT INTO expenses(user_id, amount, expenseName)
  VALUES((SELECT id FROM users where username = "bob123"), 18.00, "food");

INSERT INTO expenses(user_id, amount, expenseName, createdOn)
  VALUES((SELECT id FROM users where username = "bob123"), 18.00, "food", '2018-10-1 13:17:17');


INSERT INTO expenses(user_id, amount, expenseName, createdOn)
  VALUES((SELECT id FROM users where username = "bob123"), 30.00, "gas bill", '2018-10-2 13:17:17');


INSERT INTO expenses(user_id, amount, expenseName, createdOn)
  VALUES((SELECT id FROM users where username = "bob123"), 27.00, "water bill", '2018-10-2 13:17:17');


INSERT INTO expenses(user_id, amount, expenseName, createdOn)
  VALUES((SELECT id FROM users where username = "bob123"), 20.00, "witcher 3", '2018-10-3 13:17:17');


INSERT INTO expenses(user_id, amount, expenseName, createdOn)
  VALUES((SELECT id FROM users where username = "bob123"), 1.25, "soda", '2018-10-3 13:17:17');