package siit.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import siit.service.UserService;

import javax.naming.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET)
//    @GetMapping
    protected ModelAndView displayLoginForm() {
        ModelAndView mav = new ModelAndView("login");
        return mav;

    }

    @RequestMapping(method = RequestMethod.POST)
    protected ModelAndView performLogin(HttpSession session, @RequestParam("user") String userName, @RequestParam String password) {
        ModelAndView mav = new ModelAndView();

        try {
            session.setAttribute("logged_user", userService.getUserByNameAndPassword(userName, password).getName());
            mav.setViewName("redirect:/customers");
        } catch (AuthenticationException e){
            mav.addObject("error", e.getMessage());
            mav.setViewName("login");
        }

        return mav;
    }
}
