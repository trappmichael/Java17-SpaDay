package org.launchcode.controllers;

import org.launchcode.data.UserData;
import org.launchcode.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user")
public class UserController {
    @GetMapping("/add")
    public String displayAddUserForm() {
        return "user/add";
    }

    @PostMapping("/add")
    public String processAddUserForm(Model model, @ModelAttribute User user, @RequestParam String verify) {
        model.addAttribute("user",user);
//        model.addAttribute("verify",verify);

        if (user.getPassword().equals(verify)) {
            System.out.println("Password Match");
            UserData.add(user);
            model.addAttribute("users", UserData.getAll());
            return "user/index";
        } else {
            System.out.println("Password Does Not Match");
            model.addAttribute("error", "Passwords do not match");
            model.addAttribute("username",user.getUsername());
            model.addAttribute("email",user.getEmail());
            return "user/add";
        }
    }

    @GetMapping("/details/{id}")
    public String displayUserDetails(@PathVariable int id, Model model) {
        model.addAttribute("user",UserData.getById(id));
        return "/user/details";
    }
}
