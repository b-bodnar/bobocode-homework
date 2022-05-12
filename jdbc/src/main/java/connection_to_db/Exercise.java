package connection_to_db;

import lombok.SneakyThrows;
import org.postgresql.ds.PGSimpleDataSource;

/**
 * Main steps to connect to db wia jdbc:
 * - configure dataSource
 * - create connection
 * - create statement and send SQL
 * - get resultSet
 * - parse resultSet
 */

public class Exercise {

    public static final String GET_ALL_PRODUCT = "SELECT p.id, p.name FROM products p;";

    @SneakyThrows
    public static void main(String[] args) {
        var dataSource = new PGSimpleDataSource();
        dataSource.setURL("jdbc:postgresql://localhost:5433/study");
        dataSource.setUser("postgres");
        dataSource.setPassword("postgres");

        try (var connection = dataSource.getConnection()) {
            try (var statement = connection.createStatement()) {
                var resultSet = statement.executeQuery(GET_ALL_PRODUCT);
                while (resultSet.next()) {
                    var id = resultSet.getLong("id");
                    var name = resultSet.getString("name");
                    System.out.println(id + " " + name);
                }
            }
        }
    }
}
