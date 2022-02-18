package siit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siit.db.ProductDao;
import siit.model.Product;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductDao productDao;

    public Product getById(int productId){
        return productDao.getById(productId);
    }

    public List<Product> getByName(String name){
        return productDao.getByName(name);
    }
}
