package pl.akademiakodu.reporting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.akademiakodu.reporting.model.User;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/admin/profile", method = RequestMethod.GET)
    public ModelAndView profile() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName",
                "Name: " + user.getName());
        modelAndView.addObject("userSurname",
                "Surname: " + user.getLastName());
        modelAndView.addObject("userEmail",
                "Email: " + user.getEmail());
        modelAndView.setViewName("admin/profile");
        return modelAndView;
    }
}
