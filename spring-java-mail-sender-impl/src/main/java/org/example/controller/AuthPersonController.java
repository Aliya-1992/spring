package org.example.controller;

import org.example.entity.User;
import org.example.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;



@Controller
public class AuthPersonController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserRepository userRepository1;


    @GetMapping("/headpage")
    public String foo(Model model) {
        return "headpage";
    }


    @GetMapping ("/headpage-details/{email}")
    public String headPageInfo(@PathVariable(value = "email") String email,  Model model) {
        Optional<User> user=userRepository.findByEmail(email);
        ArrayList<User> us = new ArrayList<>();
        user.ifPresent(us::add);
        model.addAttribute("user", us);
        return "headpage-details";
    }

    @GetMapping ("/headpage-details/{id}/edit")
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
            return "headpage-edit";
        }}



        @PostMapping("/headpage-details/{id}/edit")

        public String headPageInfoq(@PathVariable(value = "id") Long id, @RequestParam String username, @RequestParam String email1, @RequestParam String password, @RequestParam String country, Model model) {

            User user=userRepository.findById(id).orElseThrow();
                System.out.println(email1);
                System.out.println(country);
                System.out.println(password);
                user.setUsername(username);
                user.setEmail(email1);

            if(password.isEmpty()){user.setPassword(user.getPassword());}
            else
            {PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
                user.setPassword(passwordEncoder.encode(password));}
                user.setCountry(country);
                user.setRole(user.getRole());
                userRepository.save(user);
                return "redirect:/headpage-details/"+email1;


    }






  /*  @GetMapping ("/headpage-details/{id}/edit")
    public String headPageInformationEdit(@PathVariable(value = "id") long id,  Model model) {
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
            return "headpage-edit";
        }
    }




    /*@PostMapping ("/headpage-details/{id}/edit")
    public String headPageInfoq(@PathVariable(value = "id") Long id, @RequestParam String username, @RequestParam String email1, @RequestParam String password, @RequestParam String country, Model model) {
        User user=userRepository.findById(id).orElseThrow();
        System.out.println(email1);
        System.out.println(country);
        System.out.println(password);
        user.setUsername(username);
        user.setEmail(email1);
        if(password!=null){
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
            user.setPassword(passwordEncoder.encode(password));
        }
        else{
            user.setPassword(user.getPassword());
        }
        user.setRole(user.getRole());
        userRepository.save(user);
        return "redirect:/headpage";
    }*/



    @GetMapping("/first-fact")
    public String firstFact(){
        return "first-fact";
    }
    @GetMapping("/second-fact")
    public String secondFact(){
        return "second-fact";
    }
    @GetMapping("/third-fact")
    public String thirdFact(){
        return "third-fact";
    }
    @GetMapping("/fourth-fact")
    public String fourthFact(){
        return "fourth-fact";
    }
    @GetMapping("/fifth-fact")
    public String fifthFact(){
        return "fifth-fact";
    }
}

