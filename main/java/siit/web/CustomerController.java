package siit.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import siit.model.Customer;
import siit.service.CustomerService;

@Controller
@RequestMapping(path = "/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView renderCustomerList() {
        ModelAndView mav = new ModelAndView("customer-list");
        mav.addObject("customers", customerService.getAllCustomers());
        return mav;
    }

    //    /customers/variabila/edit GET
    @RequestMapping(method = RequestMethod.GET, path = "/{customer_id}/edit")
    public ModelAndView renderCustomerEdit(@PathVariable("customer_id") int id) {
        ModelAndView mav = new ModelAndView("customer-edit");
        mav.addObject("customer", customerService.getBy(id));
        return mav;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/{id}/edit")
    public ModelAndView performCustomerEdit(@ModelAttribute Customer customer) {
        ModelAndView mav = new ModelAndView("customer-edit");
        try {
            customerService.update(customer);
            mav.setViewName("redirect:/customers");
        } catch (IllegalArgumentException e) {
            mav.addObject("error", e.getMessage());
            mav.setViewName("customer-edit");
        }

        return mav;
    }
}
