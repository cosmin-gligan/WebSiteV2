package siit.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import siit.model.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OrderDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Order> getAllBy(int customerId) {
        String sql = "SELECT *  FROM orders WHERE orders.customer_id = ?";
        return jdbcTemplate.query(sql, this::orderMapper, customerId);
    }

    public Order orderMapper(ResultSet rs, int rowNumber) throws SQLException {
        Order order = new Order();
        order.setId(rs.getInt("id"));
        order.setCustomerId(rs.getInt("customer_id"));
        order.setNumber(rs.getString("number"));
        order.setPlaced(rs.getTimestamp("placed").toLocalDateTime());
        return order;
    }


}
