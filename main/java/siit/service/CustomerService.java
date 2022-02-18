package siit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siit.db.CustomerDao;
import siit.model.Customer;
import siit.utils.NumberUtils;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private OrderService orderService;

    public void update(Customer customer) {
//        phone number Validation -> phone sa contina doar cifre, un anumit numar de caractere
        /*
        I. Implement validations for customer edit page
             - phone number should start with 00 or + then followed by a minimum of 2 digits
            - throw an exception from the update method in the service (with an appropriate message)
            - catch the exception in controller and add the error to the model
         */

        phoneNoValidator(customer.getPhone());


        customerDao.update(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
    }

    public Customer getBy(int id) {
        Customer customer = customerDao.getBy(id);
        customer.setOrders(orderService.getByCustomerId(id));
        return customer;
    }


    private void phoneNoValidator(String phoneNo) {
        if (phoneNo == null || phoneNo.trim().length() == 0) {
            throw new IllegalArgumentException("Numarul de telefon nu a fost completat !");
        }

        char[] phoneNoDigits = phoneNo.toCharArray();
        for (int i = 0; i < phoneNoDigits.length; i++) {
            if (i == 0) {
                if (String.valueOf(phoneNoDigits[i]).equals("+"))
                    continue; // primul caracter e plus, trecem mai departe
            }
            NumberUtils.validateDigit(String.valueOf(phoneNoDigits[i]));
        }
    }

}
