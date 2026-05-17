-- Filling table Users
INSERT INTO Users (login, passwd, username, email, age_usr, date_reg, avatar, role_usr) VALUES
('ivanov', '$2a$12$Vmf1n1CwFfwl8NYwximMWuWELdMsos.3S6xZ46ZuaxUqYgFUliqJ2', 'ivan', 'qwerty', 25, '2024-01-15 10:30:00', NULL, 'user'), --hashed_password_1
('petrov', '$2a$12$Kk9dcjgmMgwN64EIGq31PeXRepUNBfkiUsNIA6Y3he1X0F4nR0qnq', 'petya224', 'email', 30, '2024-01-20 14:20:00', NULL, 'user'), --hashed_password_2
('sidorova', '$2a$12$Q6Oujn.Gmy/S0vPHvvne4.l0JF35gqopf3MtJlebzZXHeJ5fGNCM.', 'alex', 'hime', 22, '2024-02-05 09:15:00', NULL, 'user'), --hashed_password_3
('admin', '$2a$12$A5d1VkAViR8QoPxIFVhrt.TW0bsdKDX/KXfyXMtw7MexYJbX24ee6', 'admin', 'qwectt', 35, '2024-01-01 08:00:00', NULL, 'admin'), --hashed_admin_pass
('smirnov', '$2a$12$M8A.HjtdO2dt/o.HXJaYauMvK3Cl2hdBZ2rIw.cV2ryCMQzEhaIz6', 'sanya', 'asjdw', 28, '2024-02-10 16:45:00', NULL, 'user'); --hashed_password_4

-- Filling table Discussion
INSERT INTO Discussion (theme, text_disc, answers_cnt, date_disc, author_id) VALUES
('Помощь с PostgreSQL', 'Как правильно настроить индексы в PostgreSQL для ускорения запросов?', 3, '2024-02-15 11:00:00', 1),
('Лучшие практики программирования', 'Какие вы используете code style и линтеры для своих проектов?', 2, '2024-02-16 14:30:00', 2),
('Обсуждение нового функционала', 'Предлагаю добавить возможность загрузки видео в комментарии', 1, '2024-02-17 10:15:00', 3);

-- Filling table DiscComment
INSERT INTO DiscComment (text_com, date_com, usr_id, disc_id) VALUES
('Рекомендую использовать BTREE индексы для большинства случаев', '2024-02-15 12:30:00', 2, 1),
('А есть ли разница между BTREE и HASH?', '2024-02-15 13:45:00', 3, 1),
('HASH лучше для точных совпадений, но BTREE универсальнее', '2024-02-15 14:20:00', 4, 1),
('Мы используем ESLint + Prettier для JavaScript проектов', '2024-02-16 15:45:00', 1, 2),
('А для Python советую Black и flake8', '2024-02-16 16:30:00', 4, 2),
('Отличная идея! Поддерживаю предложение', '2024-02-17 11:30:00', 5, 3);

-- Filling table Chat
INSERT INTO Chat (name_chat, password_chat) VALUES
('Общий чат', NULL),
('Технические вопросы', '$2a$12$V20ejieKDhmVbbUQlwGyuu6gI2LKtAHupyP2VIYxDrDvWQv79qa8e'), --tech123
('Администрация', '$2a$12$/zdNEVDmeFcwP918PSmIIuiuofTHP/2CIBwMCCZCHRet59LGuxlPG'); --admin_chat_secure

-- Filling table ChatComment
INSERT INTO ChatComment (text_com, date_com, chat_id, usr_id) VALUES
('Добро пожаловать в общий чат!', '2024-02-15 09:00:00', 1, 4),
('Привет всем! Рад присоединиться', '2024-02-15 09:05:00', 1, 1),
('Кто-нибудь сталкивался с проблемой deadlock в PostgreSQL?', '2024-02-16 10:20:00', 2, 2),
('Да, нужно проверять порядок блокировок', '2024-02-16 10:25:00', 2, 4),
('Обсуждаем планы на следующий релиз', '2024-02-17 08:30:00', 3, 4),
('Готовлю список новых фич', '2024-02-17 08:35:00', 3, 2);

-- Filling table DiscStatus
INSERT INTO DiscStatus (usr_id, disc_id, status) VALUES
(1, 1, 'admin'),
(1, 2, 'admin'),
(1, 3, 'user'),
(2, 1, 'admin'),
(2, 2, 'user'),
(3, 1, 'admin'),
(4, 1, 'admin'),
(4, 2, 'admin'),
(4, 3, 'admin');

-- Filling table ChatStatus
INSERT INTO ChatStatus (chat_id, usr_id, status) VALUES
(1, 1, 'admin'),
(1, 2, 'user'),
(1, 3, 'admin'),
(1, 4, 'admin'),
(2, 2, 'admin'),
(2, 4, 'admin'),
(3, 2, 'user'),
(3, 4, 'admin');

