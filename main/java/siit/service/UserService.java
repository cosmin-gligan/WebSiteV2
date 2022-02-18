package siit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siit.db.UserDao;
import siit.model.User;

import javax.naming.AuthenticationException;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public User getUserByNameAndPassword(String name, String password) throws AuthenticationException {
        if ( name == null || name.length() == 0) throw new AuthenticationException("Nu ati completat nume utilizator!");
        if ( password == null || password.length() == 0) throw new AuthenticationException("Nu ati completat parola!");

        return userDao.getUserByNameAndPassword(name, password);
    }

}
