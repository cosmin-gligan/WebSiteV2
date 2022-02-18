package siit.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import siit.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Product> getAll() {
        String sql = "SELECT products.* FROM products";
        return jdbcTemplate.query(sql, this::productMapper);
    }

    public Product getById(int productId) {
        try {
            String sql = "SELECT products.* FROM products WHERE products.id = ?";
            return jdbcTemplate.queryForObject(sql, this::productMapper, productId);
        } catch (DataAccessException e) {
            throw new EmptyResultDataAccessException("Nu am gasit produsul cu ID-ul " + productId, 1);
        }
    }

    private Product productMapper(ResultSet rs, int rowNumber) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setName(rs.getString("name"));
        product.setPrice(rs.getDouble("price"));
        product.setWeight(rs.getDouble("weight"));
        return product;
    }

    public List<Product> getByName(String name) {
        String sql = "SELECT products.* FROM products WHERE products.name ~* ?";
        return jdbcTemplate.query(sql, this::productMapper, name);
    }
}
