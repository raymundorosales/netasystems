package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.model.Login;
import com.model.LoginValidator;
import com.service.UserDao;

@Controller
@RequestMapping("/login")
@SessionAttributes("user")
public class LoginFormDBController {
	private UserDao userDao;
	private LoginValidator loginValidator;

	@Autowired
	public LoginFormDBController(LoginValidator loginValidator, UserDao userDao) {
		this.loginValidator = loginValidator;
		this.userDao = userDao;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String setupForm(@RequestParam(required = false, value = "id") String id, Model model) {
		Login login = new Login();//Creamos el objeto de login con el que vamos a interactuar en la vista
		login.setId(id);//Si el usuario escribe en la url el valor de id lo agregamos al modelo
		model.addAttribute("login", login);//Agregamos en objeto login al modelo
		return "login";//Direccionamos la navegacion a la pagin logn.jsp
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String submitForm(@ModelAttribute("login") Login login, BindingResult result, SessionStatus status, Model model) {
		String destino = "login";//Direccion de destino por default
		
		loginValidator.validate(login, result);//Validamos que los campos del form no esten vacios ni contengan espacios
		if (result.hasErrors()) //Si hubo errores en el formulario
			model.addAttribute("login", login);
		else {//Si el formulario no tiene errores
			Login log = userDao.query(login);
			if (login.getId().equals(log.getId())) {
				model.addAttribute("user", log);
				destino = "bienvenido";
			} else
				model.addAttribute("error", "Por favor verifique sus datos de ingreso");//Mandamos mensaje de error al usuario
		}
		return destino;//Tomara el valor del destino de acuerdo a la ejecucion del método
	}

}