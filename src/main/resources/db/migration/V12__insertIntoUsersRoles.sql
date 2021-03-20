INSERT INTO users_role (user_id,name)
VALUES ((SELECT u.id FROM users u where u.name='user'),'ROLE_USER');

INSERT INTO users_role (user_id,name)
VALUES ((SELECT u.id FROM users u where u.name='user'),'ROLE_ADMIN');