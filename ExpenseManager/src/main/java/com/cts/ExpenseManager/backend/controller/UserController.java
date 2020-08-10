package com.cts.ExpenseManager.backend.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.cts.ExpenseManager.backend.model.*;
import com.cts.ExpenseManager.backend.dao.*;
import com.cts.ExpenseManager.backend.dto.*;


@Controller
@RequestMapping("/registration")
public class UserController {

    @Autowired
    private UserDao userService;

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "user/registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto,
                                      BindingResult result){

        User existing = userService.findByEmail(userDto.getEmail());
        if (existing != null){
            result.rejectValue("email", null, "There is already an account registered with that email");
        }

        if (result.hasErrors()){
            return "user/registration";
        }

        userService.save(userDto);
        return "redirect:user/registration?success";
    }
	/*
	 * @RequestMapping(value = { "/home/home"},method = RequestMethod.GET) public
	 * ModelAndView home() { ModelAndView model = new ModelAndView(); Authentication
	 * auth = SecurityContextHolder.getContext().getAuthentication(); User user =
	 * userService.findUserByEmail(auth.getName());
	 * 
	 * model.addObject("userName", user.getFirstName() + " " + user.getLastName());
	 * model.setViewName("home/home"); return model; }
	 */

    @RequestMapping("/home")
    public ModelAndView homepage() {
      ModelAndView mv = new ModelAndView();
      mv.setViewName("home/home");
      return mv;
    }

}
