package siit.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import siit.service.CustomerService;
import siit.service.OrderService;

import java.lang.reflect.Method;

@Controller
@RequestMapping("")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    CustomerService customerService;

//    http://localhost:8080/customers/1/orders
    @RequestMapping(method = RequestMethod.GET, path ="/customers/{customerId}/orders" )
    public ModelAndView renderOrderPage(@PathVariable int customerId){
        ModelAndView orderPage = new ModelAndView("customer-orders");
        orderPage.addObject(customerService.getBy(customerId));
        return orderPage;
    }
}
