package pl.akademiakodu.reporting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.akademiakodu.reporting.model.entities.User;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/admin/profile")
    public ModelAndView profile() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", "Name: " + user.getName());
        modelAndView.addObject("userSurname", "Surname: " + user.getLastName());
        modelAndView.addObject("userEmail", "Email: " + user.getEmail());
        modelAndView.setViewName("admin/profile");
        return modelAndView;
    }

  /* @RequestMapping(value = "/reports/new", method = RequestMethod.GET)
    public ModelAndView report() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userFullName", user.getName() + user.getLastName());
        modelAndView.setViewName("reports/new");
        return modelAndView;
    }*/
}
