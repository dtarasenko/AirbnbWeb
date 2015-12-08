//package com.gojava6.airbnb;
//
//import com.ibatis.common.jdbc.ScriptRunner;
//
//import java.io.*;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class MainSqlScriptRunner {
//
//    private static final String url = "jdbc:mysql://localhost:3306/";
//    private static final String user = "root";
//    private static final String password = "root";
//
//
//    public static void main(String[] args) {
//        executeSQL("create.sql");
//        executeSQL("data.sql");
//    }
//
//    private static void executeSQL(String fileName) {
//        try {
//            ScriptRunner scriptRunner = new ScriptRunner(getConnection(), false, false);
//            String path = getSqlScriptPath("sql/" + fileName);
//            Reader reader = new BufferedReader(new FileReader(path));
//            scriptRunner.runScript(reader);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static Connection getConnection() {
//        Connection connection = null;
//        try {
//            connection = DriverManager.getConnection(url, user, password);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return connection;
//    }
//
//    private static String getSqlScriptPath(String fileName) {
//        return ClassLoader.getSystemResource(fileName).getPath().replaceAll("%20", " ");
//    }
//
//
//}