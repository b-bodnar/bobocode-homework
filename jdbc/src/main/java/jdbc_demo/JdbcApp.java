package jdbc_demo;

import jdbc_demo.model.Product;
import lombok.SneakyThrows;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 * If we use SELECT query we can use Statement, but if we have parameters we should use PreparedStatement.
 * <p>
 * Statement has two methods:
 * - executeQuery - use for SELECT
 * - executeUpdate - use for INSERT, DELETE, UPDATE
 * <p>
 * But in this case we should use PreparedStatement, thus we save us from sql injection
 */

public class JdbcApp {

    private static final String GET_ALL_PRODUCTS = "SELECT * FROM products;";
    private static final String INSERT_PRODUCTS = "INSERT INTO products(name, price, added_by) VALUES (?, ?, ?);";
    private static int BUTCH_SIZE = 50;
    private static int PRODUCT_AMOUNT = 1_000_000;
    private static DataSource dataSource;

    @SneakyThrows
    public static void main(String[] args) {
        initializeDataSource();
        var addedBy = System.getProperty("user.name");

        var start = System.nanoTime();
        try (var connection = dataSource.getConnection()) {
            try (var insertStatement = connection.prepareStatement("INSERT INTO products(name, price, added_by) VALUES (?, ?, ?);")) {

                for (int i = 1; i <= PRODUCT_AMOUNT; i++) {
                    insertStatement.setString(1, "name" + i);
                    insertStatement.setDouble(2, ThreadLocalRandom.current().nextDouble(1000));
                    insertStatement.setString(3, addedBy);

                    //every time send query to db
                    //insertStatement.executeUpdate();

                    insertStatement.addBatch();
                    if (i % BUTCH_SIZE == 0) {
                        insertStatement.executeBatch();
                    }
                }
                insertStatement.executeBatch();
            }
        }
        var end = System.nanoTime();
        System.out.println("It's took " + (end - start) / 1_000_000 + " millis");
    }

    private static void initializeDataSource() {
        PGSimpleDataSource pgSimpleDataSource = new PGSimpleDataSource();
        pgSimpleDataSource.setURL("jdbc:postgresql://localhost:5433/study");
        pgSimpleDataSource.setUser("postgres");
        pgSimpleDataSource.setPassword("postgres");
        dataSource = pgSimpleDataSource;
    }
}
