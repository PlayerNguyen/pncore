package com.github.playernguyen.pncore.jdbc.mysql;

import com.mysql.cj.jdbc.MysqlDataSource;

public class MySQLDriver {

    public static void register() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    public static MysqlDataSource createDatasource() {
        return new MysqlDataSource();
    }

}
