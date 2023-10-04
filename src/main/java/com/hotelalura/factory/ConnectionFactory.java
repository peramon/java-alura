package com.hotelalura.factory;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private DataSource dataSource;
    public ConnectionFactory(){
        var pooledDataSource = new ComboPooledDataSource();
        pooledDataSource.setJdbcUrl("hotel");
        pooledDataSource.setUser("peramon");
        pooledDataSource.setPassword("pwdperamon");
        pooledDataSource.setMaxPoolSize(10);
        this.dataSource = pooledDataSource;
    }
    public Connection recuperaConexion(){
        try {
            return this.dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
