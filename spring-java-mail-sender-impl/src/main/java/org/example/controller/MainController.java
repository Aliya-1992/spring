package org.example.controller;


import org.example.entity.RegUser;
import org.example.entity.User;
import org.example.repository.UserRepository;
import org.example.service.UserDetailsServiceImpl;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.postgresql.util.PSQLException;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.security.core.Authentication;




@Controller
public class MainController {

	@RequestMapping("/")
	public String root() {
		return "redirect:/index";
	}


	@Autowired
	private UserRepository userRepository;



	@RequestMapping("/index")
	public String index() {
		return "index";
	}




	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login";
	}

	//@PreAuthorize("hasAuthority('unsecure')")
	//@RequestMapping("/user/index")
	//public String userFirstPage() {
	//	return "user/index";
	//}



	@RequestMapping ("/register")
	public String registration(Model model){
		return "register";
	}

	@RequestMapping("/error-registration")
	public String errorRegisration(){
		return "error-registration";
	}
	@Autowired

	private UserService userService;

	@PostMapping("/register")
	public String registerUser(@RequestParam String username, @RequestParam String email, @RequestParam String password, @RequestParam String country, Model model) {
    //сделали столбец username уникальным, во избежание ошибки заворачиваю в try catch
		// ALTER TABLE имя_таблицы ADD UNIQUE (имя_столбца);
           try{
			   PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12); //создала экземляр класса чтобы закодировать пароль
			RegUser regUser = new RegUser(username, email, passwordEncoder.encode(password), country, "USER"); // кодируем пароль
			System.out.println(regUser.getUsername());
			System.out.println(regUser.getPassword());
			System.out.println(regUser.getEmail());
			System.out.println(regUser.getCountry());
			userService.createUser(regUser);
			return "redirect:/index";}
		   catch (Exception e) {
			return "redirect:/error-registration";
		   }
	}}