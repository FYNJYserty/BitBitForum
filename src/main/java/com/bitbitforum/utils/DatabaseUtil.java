package com.bitbitforum.utils;

import java.sql.*;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DatabaseUtil {
    private static String url;
    private static String username;
    private static String password;

    // Инициализация параметров из .env файла
    static {
        loadConfig();
    }

    private static void loadConfig() {
        try (InputStream input = new FileInputStream(".env")) {
            Properties prop = new Properties();
            prop.load(input);

            url = prop.getProperty("DB_URL");
            username = prop.getProperty("DB_USER");
            password = prop.getProperty("DB_USER_PASSWORD");

        } catch (IOException ex) {
            // Если файл .env не найден, пробуем получить из системных переменных
            url = System.getenv("DB_URL");
            username = System.getenv("DB_USER");
            password = System.getenv("DB_USER_PASSWORD");

            if (url == null) {
                throw new RuntimeException("Database configuration not found", ex);
            }
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

}