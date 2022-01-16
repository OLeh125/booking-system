INSERT INTO users (id, uuid, name, surname, email, password)
VALUES ( nextval('user_id_seq'), '6ff05fd1-2b77-4d90-a09a-e1328ee460b1', 'Ivan', 'Zlatan', 'ivan.zlatan@gmail.com', '$2a$12$7g7HmvMBV1g/3cLicr5ZIuNulWV0FKHC9xxZ8OgDtrndID4nckhBq');

INSERT INTO events (id, uuid, place, date, description, tickets_number)
VALUES (90102, '6ff05fd1-2b77-4d90-a09a-e1328ee460b3', '86 West Court Melbourne, FL 32904', '2022-01-22 19:30:00', 'Some huge description', 15);

INSERT INTO tickets(id, uuid, number, additional_inf, price, event_id)
VALUES (nextval('ticket_id_seq'), '6ff05fd1-2b77-4d90-a09a-e1328ee460b3', 1, 'VIP ticket', 300, 90102),
       (nextval('ticket_id_seq'), '6ff05fd1-2b77-4d90-a09a-e1328ee460b5', 2, 'VIP ticket', 300, 90102),
       (nextval('ticket_id_seq'), '6ff05fd1-2b77-4d90-a09a-e1328ee460b6', 3, 'VIP ticket', 300, 90102),
       (nextval('ticket_id_seq'), '6ff05fd1-2b77-4d90-a09a-e1328ee460b7', 4, 'VIP ticket', 300, 90102),
       (nextval('ticket_id_seq'), '6ff05fd1-2b77-4d90-a09a-e1328ee460b8', 5, 'VIP ticket', 300, 90102),
       (nextval('ticket_id_seq'), '6ff05fd1-2b77-4d90-a09a-e1328ee460b9', 6, 'VIP ticket', 300, 90102),
       (nextval('ticket_id_seq'), '6ff05fd1-2b77-4d90-a09a-e1328ee450b1', 7, 'VIP ticket', 300, 90102),
       (nextval('ticket_id_seq'), '6ff05fd1-2b77-4d90-a09a-e1328ee440b2', 8, 'VIP ticket', 300, 90102),
       (nextval('ticket_id_seq'), '6ff05fd1-2b77-4d90-a09a-e1328ee440b3', 9, 'VIP ticket', 300, 90102),
       (nextval('ticket_id_seq'), '6ff05fd1-2b77-4d90-a09a-e1328ee450b4', 10, 'VIP ticket', 300, 90102),
       (nextval('ticket_id_seq'), '6ff05fd1-2b77-4d90-a09a-e1328ee450b5', 11, 'VIP ticket', 300, 90102),
       (nextval('ticket_id_seq'), '6ff05fd1-2b77-4d90-a09a-e1328ee450b6', 12, 'VIP ticket', 300, 90102),
       (nextval('ticket_id_seq'), '6ff05fd1-2b77-4d90-a09a-e1328ee450b7', 13, 'VIP ticket', 300, 90102),
       (nextval('ticket_id_seq'), '6ff05fd1-2b77-4d90-a09a-e1328ee450b8', 14, 'VIP ticket', 300, 90102),
       (nextval('ticket_id_seq'), '6ff05fd1-2b77-4d90-a09a-e1328ee450b9', 15, 'VIP ticket', 300, 90102);