package com.github.playernguyen.pncore.jdbc.mysql;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;


public class MySQLDriverTest {

    @Test
    public void hasDriver() {
        Assertions.assertDoesNotThrow(MySQLDriver::register);
    }

    @Test
    public void successfullyEstablishConnection(){
        MySQLConfigPropertiesTest config = new MySQLConfigPropertiesTest();

        Assertions.assertDoesNotThrow(() -> {
            MySQLDriver.register();
            Connection conn = DriverManager.getConnection(String.format(
                            "jdbc:mysql://%s:%s/%s",
                            config.host, config.port, config.database
                    ),
                    config.username,
                    config.password
            );
            conn.close();
        });
    }

    @Test
    public void createDatasourceNotNull() {
        MySQLConfigPropertiesTest config = new MySQLConfigPropertiesTest();
        Assertions.assertNotNull(MySQLDriver.createDatasource());

        Assertions.assertDoesNotThrow(() -> {
            MysqlDataSource dataSource = MySQLDriver.createDatasource();
            // Config the data source
            dataSource.setUrl(String.format(
                    "jdbc:mysql://%s:%s/%s",
                    config.host, config.port, config.database
            ));
            dataSource.setPort(Integer.parseInt(config.port));

            // Establish a test connection
            Connection connection = dataSource.getConnection(config.username, config.password);
            connection.close();
        });
    }

    static class MySQLConfigPropertiesTest {
        // Load test environment
        public String host = System.getenv("MYSQL_HOST");
        public String port = System.getenv("MYSQL_PORT");
        public String database = System.getenv("MYSQL_DATABASE");
        public String username = System.getenv("MYSQL_USERNAME");
        public String password = System.getenv("MYSQL_PASSWORD");

        public MySQLConfigPropertiesTest() {
            Assertions.assertNotNull(host, "MySQL host environment variable is not existed");

            if (port == null) this.port = String.valueOf(3306);
            Assertions.assertNotNull(database, "MySQL database environment variable is not existed");
            Assertions.assertNotNull(username, "MySQL username environment variable is not existed");
            Assertions.assertNotNull(password, "MySQL password environment variable is not existed");
        }
    }

}
