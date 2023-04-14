package manager;

import db.DBConnectorProvider;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductManager {
    private Connection connection = DBConnectorProvider.getInstance().getConnection();

    public void saveProductDB(Product product) {
        String sql = "INSERT INTO product(name,description,price,quantity,category_id) VALUES (?,?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setInt(3, product.getPrice());
            ps.setInt(4, product.getQuantity());
            ps.setInt(5, product.getCategoryId());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                product.setId(generatedKeys.getInt(1));
            }
            System.out.println("Category inserted into DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Product> getAllProduct() {
        List<Product> productList = new ArrayList<>();
        String sql = "SELECT * FROM product";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                productList.add(getProductFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public Product getByIdProduct(int id) {
        String sql = "SELECT * FROM product WHERE id = " + id;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return getProductFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Product getProductFromResultSet(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt("id"));
        product.setName(resultSet.getString("name"));
        product.setDescription(resultSet.getString("description"));
        product.setPrice(resultSet.getInt("price"));
        product.setQuantity(resultSet.getInt("quantity"));
        return product;
    }

    public void removeById(int id) {
        String sql = "DELETE FROM product WHERE id = " + id;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Product product) {
        String sql = "UPDATE product SET name = ?, description = ?, price = ?, quantity = ?, category_id = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setInt(3, product.getPrice());
            ps.setInt(4, product.getQuantity());
            ps.setInt(5, product.getCategoryId());
            ps.setInt(6, product.getId());
            ps.executeUpdate();
            System.out.println("Product update into DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void sum() {
        String sql = "SELECT SUM(price) AS total_price FROM product";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                double totalPrice = rs.getDouble("total_price");
                System.out.println("Total price of all products: " + totalPrice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Max() {
        String sql = "SELECT MAX(price) AS max_price FROM product";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                double maxPrice = rs.getDouble("max_price");
                System.out.println("Maximum price of all products: " + maxPrice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Min() {
        String sql = "SELECT MIN(price) AS min_price FROM product";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                double minPrice = rs.getDouble("min_price");
                System.out.println("Minimum price of all products: " + minPrice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Avg() {
        String sql = "SELECT AVG(price) AS avg_price FROM product";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                double avgPrice = rs.getDouble("avg_price");
                System.out.println("Average price of all products: " + avgPrice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}