package siit.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import siit.model.OrderProduct;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OrderProductDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<OrderProduct> getOrderProductsById(int orderId){

        String sql = "SELECT orders_products.* FROM orders_products WHERE orders_products.order_id = ?";
        return jdbcTemplate.query(sql, this::mapOrderProduct, orderId);

    }

    private OrderProduct mapOrderProduct(ResultSet rs, int i) throws SQLException {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setId(rs.getInt("id"));
        orderProduct.setOrderId(rs.getInt("order_id"));
        orderProduct.setProductId(rs.getInt("product_id"));
        orderProduct.setQuantity(rs.getDouble("quantity"));
        return orderProduct;
    }


}
