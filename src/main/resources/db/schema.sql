--Deleting tables
DROP TABLE IF EXISTS Attachment;
DROP TABLE IF EXISTS DiscStatus;
DROP TABLE IF EXISTS ChatStatus;
DROP TABLE IF EXISTS ChatComment;
DROP TABLE IF EXISTS DiscComment;
DROP TABLE IF EXISTS Discussion;
DROP TABLE IF EXISTS Chat;
DROP TABLE IF EXISTS Users;

--Creating new tables
--Users table
CREATE TABLE Users (
    id_usr SERIAL PRIMARY KEY, --Id
    login VARCHAR(50) NOT NULL UNIQUE, --Login
    passwd VARCHAR(255) NOT NULL, --Password
    username VARCHAR(255) NOT NULL, --User's nickname
    email VARCHAR(255) NOT NULL UNIQUE, --User's email
    age_usr INTEGER CHECK (age_usr >= 0), --Age
    date_reg TIMESTAMP DEFAULT CURRENT_TIMESTAMP, --Date of registration
    avatar VARCHAR(255), --Avatar
    role_usr VARCHAR(20) DEFAULT 'user' --Role of user in app (admin/user)
);
--Discussion table
CREATE TABLE Discussion (
    id_disc SERIAL PRIMARY KEY, --Id of discussion
    theme VARCHAR(200) NOT NULL, --Name of theme
    text_disc TEXT NOT NULL, --Text of discussion
    answers_cnt INTEGER DEFAULT 0, --Count of answers
    date_disc TIMESTAMP DEFAULT CURRENT_TIMESTAMP, --Date of creation
    author_id INTEGER REFERENCES Users(id_usr) ON DELETE CASCADE --Id of creator
);
--Discussion's comments table
CREATE TABLE DiscComment (
    id_disc_comment SERIAL PRIMARY KEY, --Id of comment under the discussion
    text_com TEXT NOT NULL, --Text of comment
    date_com TIMESTAMP DEFAULT CURRENT_TIMESTAMP, --Date of creation
    usr_id INTEGER REFERENCES Users(id_usr) ON DELETE CASCADE, --The commentator's id
    disc_id INTEGER REFERENCES Discussion(id_disc) ON DELETE CASCADE --Id of discussion
);
--Chat table
CREATE TABLE Chat (
    id_chat SERIAL PRIMARY KEY, --Id of chat
    name_chat VARCHAR(100) NOT NULL, --Name of chat
    password_chat VARCHAR(255) --Password of chat
);
--Chat's comment table
CREATE TABLE ChatComment (
    id_com SERIAL PRIMARY KEY, --Id of chat comment
    text_com TEXT NOT NULL, --Text of comment
    date_com TIMESTAMP DEFAULT CURRENT_TIMESTAMP, --Date of creation
    chat_id INTEGER REFERENCES Chat(id_chat) ON DELETE CASCADE, --Id of chat
    usr_id INTEGER REFERENCES Users(id_usr) ON DELETE CASCADE --Id of creator
);
--Attachment table
CREATE TABLE Attachment (
    id_att SERIAL PRIMARY KEY, --Id of attachment
    mime_type VARCHAR(100) NOT NULL, --Mime type
    file BYTEA NOT NULL, --File
    filename VARCHAR(255) NOT NULL, --Filename
    chatcom_id INTEGER REFERENCES ChatComment(id_com) ON DELETE CASCADE, --Id of chat
    disccom_id INTEGER REFERENCES DiscComment(id_disc_comment) ON DELETE CASCADE, --Id of discussion
    usr_id INTEGER REFERENCES Users(id_usr) ON DELETE CASCADE --Id of creator
);
--User's status in discussion table
CREATE TABLE DiscStatus (
    usr_id INTEGER REFERENCES Users(id_usr) ON DELETE CASCADE, --Id of user
    disc_id INTEGER REFERENCES Discussion(id_disc) ON DELETE CASCADE, --Id of discussion
    status VARCHAR(20) DEFAULT 'user', --User's status
    PRIMARY KEY (usr_id, disc_id)
);
--User's status in chat table
CREATE TABLE ChatStatus (
    chat_id INTEGER REFERENCES Chat(id_chat) ON DELETE CASCADE, --Id of chat
    usr_id INTEGER REFERENCES Users(id_usr) ON DELETE CASCADE, --Id of user
    status VARCHAR(20) DEFAULT 'user', --User's status
    PRIMARY KEY (chat_id, usr_id)
);