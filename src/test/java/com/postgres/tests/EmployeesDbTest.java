package com.postgres.tests;

import com.postgres.utilities.DBType;
import com.postgres.utilities.DBUtility;
import org.testng.annotations.Test;

import java.sql.SQLException;

import static org.testng.Assert.assertTrue;

public class EmployeesDbTest {

    @Test
    public void countTest() throws SQLException {
        //connect to postgres database
        //run following sql query
        //select * from employees where job_id = 'IT_PROG'
        //more than 0 records should be returned
        DBUtility.establishConnection(DBType.POSTGRESQL);
        int rowsCount = DBUtility.getRowsCount("select * from employees where job_id = 'IT_PROG';");
        assertTrue(rowsCount > 0);
        DBUtility.closeConnections();
    }
}
