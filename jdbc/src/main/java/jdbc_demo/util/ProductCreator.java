package jdbc_demo.util;

import jdbc_demo.model.Product;
import lombok.experimental.UtilityClass;

import java.sql.ResultSet;
import java.sql.SQLException;

@UtilityClass
public class ProductCreator {

    public static Product createProduct(ResultSet resultSet) throws SQLException {
        var id = resultSet.getLong("id");
        var name = resultSet.getString("name");
        var price = resultSet.getDouble("price");
        var createdAt = resultSet.getDate("created_at");
        var addedBy = resultSet.getString("added_by");
        return new Product(id, name, price, createdAt, addedBy);
    }
}
