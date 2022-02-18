package siit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siit.db.OrderProductDao;
import siit.model.OrderProduct;

import java.util.List;

@Service
public class OrderProductService {
    @Autowired
    OrderProductDao orderProductDao;
    @Autowired
    ProductService productService;

    public List<OrderProduct> getAllById(int orderId){
        List<OrderProduct> orderProducts = orderProductDao.getOrderProductsById(orderId);
        for ( OrderProduct orderProduct : orderProducts){
            orderProduct.setProduct(productService.getById(orderProduct.getProductId()));
        }

        return  orderProducts;
    }

}
