import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements Repository {
    private String url1;
    private String user;
    private String password;

    public ProductDAO() {
        this.url1 = "jdbc:mysql://localhost:3306/productmanagement)";
        this.user = "root";
        this.password = "";
        try {
            Connection connection = DriverManager.getConnection(url1, user, password);
            connection.close();
        } catch (Exception ex) {
        }

        try {
            Connection conn = (Connection) DriverManager.getConnection(url1, user, password);
            String a = "DROP TABLE IF EXISTS product";
            String sql = "CREATE TABLE product (id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY, name VARCHAR(30) NOT NULL, price INT(6) NOT NULL, year VARCHAR(30) NOT NULL)";

            Statement stm = (Statement) conn.createStatement();
            stm.executeUpdate(a);
            stm.executeUpdate(sql);
            stm.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Object add(Object item) {

        try {
            Connection conn = null;
            conn = (Connection) DriverManager.getConnection(url1, user, password);
            String insert = "INSERT INTO product (name, price, year) VALUES (?, ?, ?)";
            PreparedStatement ptm = (PreparedStatement) conn.prepareStatement(insert);

            Product product = (Product) item;

            ptm.setInt(1, product.getId());
            ptm.setString(2, product.getName());
            ptm.setInt(3, product.getPrice());
            ptm.setString(4, product.getYear());

            ptm.executeUpdate();
            ptm.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List readAll() {

        Connection conn = null;
        try {
            conn = (Connection) DriverManager.getConnection(url1, user, password);
            String sqlSelect = "SELECT * FROM product";
            Statement stm = (Statement) conn.createStatement();
            ResultSet data = stm.executeQuery(sqlSelect);
            List<Product> productList = new ArrayList<>();
            while (data.next()) {
                int id = data.getInt(1);
                String name = data.getString(2);
                int price = data.getInt(3);
                String year = data.getString(4);

                Product product = new Product(id, name, price, year);
                productList.add(product);
            }

            for (Product product : productList) {
                System.out.println(product.toString());
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public Object read(Object id) {
        Connection conn = null;
        try {
            conn = (Connection) DriverManager.getConnection(url1, user, password);
            String sqlSelect = "SELECT * FROM product WHERE id = ?";
            PreparedStatement stm = (PreparedStatement) conn.createStatement();
            stm.setInt(1, (int) id);
            ResultSet data = stm.executeQuery(sqlSelect);
            data.toString();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public boolean update(Object item) {
        Connection conn = null;
        try {
            conn = (Connection) DriverManager.getConnection(url1, user, password);
            Product product = (Product) item;
            String sqlSelect = "UPDATE product SET name = ?, price = ?, year = ? WHERE id = ?";
            PreparedStatement stm = (PreparedStatement) conn.createStatement();
            stm.setString(1, product.getName());
            stm.setInt(2, product.getPrice());
            stm.setString(3, product.getYear());
            stm.setInt(4, product.getId());
            stm.executeUpdate();
            stm.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    @Override
    public boolean delete(Object id) {
        Connection conn = null;
        try {
            conn = (Connection) DriverManager.getConnection(url1, user, password);
            String sqlSelect = "DELETE FROM product WHERE id = ?";
            PreparedStatement stm = (PreparedStatement) conn.createStatement();
            stm.setInt(1, (int) id);
            stm.executeUpdate();
            stm.close();
            conn.close();
            return true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
