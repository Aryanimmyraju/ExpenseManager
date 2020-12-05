package com.cts.ExpenseManager.backend.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.cts.ExpenseManager.backend.model. * ;
import com.cts.ExpenseManager.backend.dao. * ;

@Controller
public class UserController {

  @Autowired
  private UserDao userService;

  @GetMapping("/users")
  public List < User > retrieveAllUser() {
    return userService.getAllUser();
  }

  @GetMapping("/user/{userId}")
  public User retrieveUser(@PathVariable int userId) {
    User user = userService.getUser(userId);
    return user;
  }

  @PostMapping("/addUser")
  public String addUserDetails(@RequestBody User user) {
    userService.saveUser(user);
    return "User Details Added Successfully!!!";
  }

  @RequestMapping(value = {"/","/login"},
  method = RequestMethod.GET)
  public ModelAndView login() {
    ModelAndView model = new ModelAndView();

    model.setViewName("user/login");
    return model;
  }

  @RequestMapping(value = {
    "/signup"
  },
  method = RequestMethod.GET)
  public ModelAndView signup() {
    ModelAndView model = new ModelAndView();
    User user = new User();
    model.addObject("user", user);
    model.setViewName("user/signup");

    return model;
  }

  @RequestMapping(value = {
    "/signup"
  },
  method = RequestMethod.POST)
  public ModelAndView createUser(@Valid User user, BindingResult bindingResult) {
    ModelAndView model = new ModelAndView();
    User userExists = userService.findUserByEmail(user.getEmail());

    if (userExists != null) {
      bindingResult.rejectValue("email", "error.user", "This email already exists!");
    }
    if (bindingResult.hasErrors()) {
      model.setViewName("user/signup");
    } else {
      userService.saveUser(user);
      model.addObject("msg", "User has been registered successfully!");
      model.addObject("user", new User());
      model.setViewName("user/login");
    }

    return model;
  }

  @RequestMapping(value = { "/home/home"},method = RequestMethod.GET)
  public ModelAndView home() {
    ModelAndView model = new ModelAndView();
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User user = userService.findUserByEmail(auth.getName());

    model.addObject("userName", user.getFirstname() + " " + user.getLastname());
    model.setViewName("home/home");
    return model;
  }

  @RequestMapping("/home")
  public ModelAndView homepage() {
    ModelAndView mv = new ModelAndView();
    mv.setViewName("home");
    return mv;
  }

  @RequestMapping(value = {
    "/access_denied"
  },
  method = RequestMethod.GET)
  public ModelAndView accessDenied() {
    ModelAndView model = new ModelAndView();
    model.setViewName("errors/access_denied");
    return model;
  }

}