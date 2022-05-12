package jdbc_demo;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.SneakyThrows;

import javax.sql.DataSource;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

public class JdbcHikariApp {
    private static DataSource dataSource;

    @SneakyThrows
    public static void main(String[] args) {
        initializeHikariCPDataSource();
        var addedBy = System.getProperty("user.name");

        var start = System.nanoTime();

        try (var connection = dataSource.getConnection()) {
            try (var prepareStatement = connection.prepareStatement("INSERT INTO products(name, price, added_by) VALUES (?, ?, ?)")) {
                for (int i = 0; i < 1_000_000; i++) {
                    prepareStatement.setString(1, "name" + i);
                    prepareStatement.setDouble(2, ThreadLocalRandom.current().nextDouble(1000));
                    prepareStatement.setString(3, addedBy);
                    prepareStatement.addBatch();

                    if (i % 50 == 0) {
                        prepareStatement.executeBatch();
                    }
                }
                prepareStatement.executeBatch();
            }
        }
        var end = System.nanoTime();
        System.out.println("It's took " + (end - start) / 1_000_000 + " millis");
    }

    @SneakyThrows
    private static void initializeHikariCPDataSource() {
        var properties = new Properties();
        var propFileInputStream = JdbcHikariApp.class.getClassLoader()
                .getResourceAsStream("application.properties");
        properties.load(propFileInputStream);
        var config = new HikariConfig(properties);
        dataSource = new HikariDataSource(config);

    }
}
