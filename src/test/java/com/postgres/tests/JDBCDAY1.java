package com.postgres.tests;

import java.sql.*;

import org.junit.Test;

public class JDBCDAY1 {

    String url = "jdbc:postgresql://room-reservation-qa.cxvqfpt4mc2y.us-east-1.rds.amazonaws.com:5432/hr";
    String username = "hr";
    String password = "hr";

    @Test
    public void jdbcTest1() throws SQLException{
        //Now we are connecting to the database with java code and JDBC API
        Connection connection = DriverManager.getConnection(url,username,password);
        //We need to create a statement
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        //now we are going to run a query, so for that we need to create a result set
        ResultSet resultSet = statement.executeQuery("SELECT * FROM employees;");
        //we need to skip first record, because it starts from 0
        resultSet.next();
        //we are getting first record based on the column name
        String value = resultSet.getString("first_name");
        //just to output result
        System.out.println(value);
    }



}
