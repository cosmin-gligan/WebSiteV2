package siit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siit.db.OrderDao;
import siit.model.Order;
import siit.model.OrderProduct;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderDao orderDao;
    @Autowired
    OrderProductService orderProductService;

    public List<Order> getByCustomerId(int customerId){
        List<Order> orderList = orderDao.getAllBy(customerId);
        for ( Order order : orderList){
            List<OrderProduct> orderProducts = orderProductService.getAllById(order.getId());
            order.setOrderProducts(orderProducts);
            double totalValue = orderProducts.stream().mapToDouble(item -> item.getValue()).reduce(0, (a, b)-> a + b);
            order.setValue(totalValue);
        }

        return orderList;
    }
}
