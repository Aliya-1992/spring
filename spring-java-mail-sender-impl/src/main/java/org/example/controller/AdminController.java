package org.example.controller;

import org.example.entity.Role;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class AdminController {


    @Autowired
    UserRepository userRepository;

    @PreAuthorize("hasAuthority('secure')")
    @GetMapping("/admin")

    public String admin(Model model){
       // Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", userRepository.findAll());
        return "admin";


    }

    @GetMapping ("/admin/edit/{id}")
    public String headPageInformationEdit(@PathVariable(value = "id") Long id,  Model model) {
        if(!userRepository.existsById(id)){
            System.out.println(1234);
            return "redirect:/headpage";
        }
        else{
            Optional<User> user=userRepository.findById(id);
            ArrayList<User> us = new ArrayList<>();
            user.ifPresent(us::add);
            model.addAttribute("user", us);
            System.out.println(123);
            return "admin-edit";
        }

    }
    @PostMapping("/admin/edit/{id}")
    public String edit(@PathVariable("id") Long id, @RequestParam String username, @RequestParam String email1, @RequestParam String password, @RequestParam String country, @RequestParam String role, Model model){
        User user = userRepository.findById(id).orElseThrow();
        model.addAttribute("user", user);
        System.out.println(user.getPassword());
        System.out.println(";;;"+password);
        user.setUsername(username);
        user.setEmail(email1);
        if(password.isEmpty()){user.setPassword(user.getPassword());}
        else
        {PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
            user.setPassword(passwordEncoder.encode(password));}

        user.setCountry(country);
        user.setRole(Role.valueOf(role));
        userRepository.save(user);
        return "admin-edit";
    }
    @PostMapping ("/admin/delete/{id}")
    public String delete(@PathVariable(value = "id") Long id,  Model model) {
        User user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
            return "redirect:/admin";
        }

    }
