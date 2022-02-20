INSERT INTO currencies (id, uuid, name, short_name, exchange_rate)
VALUES (nextval('currency_id_seq'), '6ff05fd1-2b77-4d20-a09a-e1328ee450b1', 'European Euro', 'EUR', 1.132292),
       (nextval('currency_id_seq'), '6ff05fd1-2b77-4d90-a05a-e1328ee450b2', 'U.S. Dollar', 'USD', 1.0),
       (nextval('currency_id_seq'), '6ff05fd1-2b77-4d90-a09a-e1327ee450b3', 'Ukrainian hryvnia', 'UAH', 0.035192);

INSERT INTO accounts (id, uuid, type, balance, currency_id, owner_id)
VALUES (nextval('account_id_seq'), '6ff05fd1-2b77-4d90-a09a-e1328ee460b7', 'Non-Resident Ordinary Accounts', 10050.1231, (SELECT id FROM currencies WHERE short_name = 'EUR'), (SELECT id FROM users WHERE email = 'ivan.zlatan@gmail.com')),
       (nextval('account_id_seq'), '6ff05fd1-2b77-4d90-a09a-e1328ee460b9', 'Resident Foreign Currency Accounts', 1050.1231, (SELECT id FROM currencies WHERE short_name = 'USD'), (SELECT id FROM users WHERE email = 'ivan.zlatan@gmail.com')),
       (nextval('account_id_seq'), '6ff05fd1-2b77-4d90-a09a-e1328ee460b8', 'Non-Resident External Rupee Accounts', 4050.1231, (SELECT id FROM currencies WHERE short_name = 'UAH'), (SELECT id FROM users WHERE email = 'ivan.zlatan@gmail.com'));

