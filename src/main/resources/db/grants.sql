GRANT ALL ON DATABASE bitbitforum TO forum_user;
GRANT ALL ON SCHEMA public TO forum_user;
GRANT ALL ON ALL TABLES IN SCHEMA public TO forum_user;

ALTER USER forum_user WITH PASSWORD 'UserPassword12345';