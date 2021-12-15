package jdbc_demo.util;

import lombok.SneakyThrows;
import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class PooledDataSource extends PGSimpleDataSource {
    // TODO: 15.12.2021 create a collection that will store connections  
    // TODO: 15.12.2021 initialize 10 connections at the beginning
    // TODO: 15.12.2021 override method getConnection so it returns a connection from a pool

    private Queue<Connection> connectionPool;
    private final int POOL_SIZE = 10;

    @SneakyThrows
    public PooledDataSource(String url, String username, String password) {
        super();
        setURL(url);
        setUser(username);
        setPassword(password);
        connectionPool = new ConcurrentLinkedDeque<>();
        for (int i = 0; i < POOL_SIZE; i++) {
            var physicalConnection = super.getConnection();
            var connection = new ConnectionProxy(physicalConnection, connectionPool);
            connectionPool.add(connection);
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return connectionPool.poll();
    }
}
