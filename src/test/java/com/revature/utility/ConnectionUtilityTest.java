package com.revature.utility;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionUtilityTest {


    @Test
    public void TestConnectivityWorks() throws SQLException {

        ConnectionUtility.getConnection();

    }

}