package manager;

import db.DBConnectionProvider;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductManager {


    private final Connection CONNECTION = DBConnectionProvider.getInstance().getConnection();
    private final CategoryManger CATEGORY_MANAGER = new CategoryManger();


    public void add(Product product) {

        try {
            PreparedStatement preparedStatement = CONNECTION.prepareStatement
                    ("INSERT into product (name,description, price, quantity, category_id) VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getQuantity());
            preparedStatement.setInt(5, product.getCategory().getId());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                product.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void update(Product product) {
        try {
            PreparedStatement preparedStatement = CONNECTION.prepareStatement("UPDATE product set name = ?,description = ?, price = ?, quantity = ?, category_id = ? WHERE id = ?");
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getQuantity());
            preparedStatement.setInt(5, product.getCategory().getId());
            preparedStatement.setInt(6, product.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }




    public List<Product> getAll() {
        List<Product> productList = new ArrayList<>();
        try (Statement statement = CONNECTION.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * from product;");
            while (resultSet.next()) {
                productList.add(getProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public Product getById(int id) {
        try {
            PreparedStatement preparedStatement = CONNECTION.prepareStatement("SELECT * FROM product where id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getProductFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private Product getProductFromResultSet(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt(1));
        product.setName(resultSet.getString(2));
        product.setDescription(resultSet.getString(3));
        product.setPrice(resultSet.getDouble(4));
        product.setQuantity(resultSet.getInt(5));
        product.setCategory(CATEGORY_MANAGER.getCategoryById(resultSet.getInt(6)));
        return product;
    }


    public void deleteById(int id) {
        try {
            PreparedStatement preparedStatement = CONNECTION.prepareStatement("DELETE from product WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public double getSum() {
        double sum = 0;
        try {
            Statement statement = CONNECTION.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT SUM(price) from product");
            if (resultSet.next()) {
                sum = resultSet.getDouble(1);
            }
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
        return sum;
    }


    public double getMaxPrice() {
        double maxPrice = 0;
        try {
            Statement statement = CONNECTION.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT MAX(price) from product");
            if (resultSet.next()) {
                maxPrice = resultSet.getDouble(1);
            }
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
        return maxPrice;
    }

    public double getMinPrice() {
        double minPrice = 0;
        try {
            Statement statement = CONNECTION.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT MIN(price) from product");
            if (resultSet.next()) {
                minPrice = resultSet.getDouble(1);
            }
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
        return minPrice;
    }

    public double getAvgPrice() {
        double avgPrice = 0;
        try {
            Statement statement = CONNECTION.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT AVG(price) from product");
            if (resultSet.next()) {
                avgPrice = resultSet.getDouble(1);
            }
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
        return avgPrice;
    }


}
