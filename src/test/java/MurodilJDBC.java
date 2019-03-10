import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MurodilJDBC {
    String url = "jdbc:postgresql://room-reservation-qa.cxvqfpt4mc2y.us-east-1.rds.amazonaws.com:5432/hr";
    String username = "hr";
    String password = "hr";

    @Test
    public void postgresqlJDBC() throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
       // Statement statement = connection.createStatement();
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ResultSet resultSet = statement.executeQuery("Select * from countries;");


        resultSet.last();
        int rowsCount = resultSet.getRow();
        System.out.println("Number of rows: " + rowsCount);


        resultSet.beforeFirst();
        while(resultSet.next()) {
            System.out.println(resultSet.getString(1)+
                                "-"+ resultSet.getString("country_name") +
                                "-"+ resultSet.getInt("region_id"));
        }

        

//        resultSet.next();
//        System.out.println(resultSet.getRow()); // --> 1
//
//        resultSet.previous();//all next 3 actions will not work FORWARD_ONLY resultset.
//        resultSet.first();
//        resultSet.last();
//        System.out.println(resultSet.getRow());

        resultSet.close();
        statement.close();
        connection.close();
    }


    @Test
    public void JDBCTEST2() throws SQLException{
        Connection connection = DriverManager.getConnection(url, username, password);
        // Statement statement = connection.createStatement();
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        String sql = "Select employee_id, last_name, job_id, salary from employees;";
       // String sql = "Select * from employees;";

        ResultSet resultSet = statement.executeQuery(sql);

        //Database metadata
        DatabaseMetaData dbMetadata = connection.getMetaData();
        System.out.println("USER: " + dbMetadata.getUserName());
        System.out.println("DATABASE TYPE: " + dbMetadata.getDatabaseProductName());


        //resultSet metadata
        ResultSetMetaData rsMetadata = resultSet.getMetaData();
        System.out.println("COLUMNS COUNT: " + rsMetadata.getColumnCount());
        System.out.println(rsMetadata.getColumnName(1));

        //print all column names using a loop
        for(int i=1; i<= rsMetadata.getColumnCount(); i++){
            System.out.println(i + " -> " + rsMetadata.getColumnName(i));
        }


        //throw resultset into a list of maps
        //create a list of maps
        List<Map<String,Object>> list = new ArrayList<>();
        ResultSetMetaData rsMdata = resultSet.getMetaData();

        int colCount = rsMdata.getColumnCount();

        while(resultSet.next()){
            Map<String,Object> rowMap = new HashMap<>();
            for(int col = 1; col <= colCount; col++){
                rowMap.put(rsMdata.getColumnName(col), resultSet.getObject(col));
            }

            list.add(rowMap);

        }


//        resultSet.absolute(rowNum);

        //print all employee ids from a list of maps
        for (Map<String,Object> emp : list){
            System.out.print(emp.get("employee_id") + " ");
    }



        resultSet.close();
        statement.close();
        connection.close();

    }
}
