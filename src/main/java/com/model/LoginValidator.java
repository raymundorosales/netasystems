package com.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class LoginValidator implements Validator {

	public boolean supports(Class clazz) {
		return Login.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		//Declaro la expresion regular para validar una direccion de email
		String emailPattern = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@" +
			      "[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$";
		Pattern pattern = Pattern.compile(emailPattern);
		
		//Si los campos te texto de login estan vacios, pasara el listado de errores encontrados
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "required.id", "El id es requerido");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required.password", "El password es requerido");

        Login login = (Login) target;
        
        //Valido que el id capturado sea un correo valido, en caso de que no lo sea tambien pasara el mensaje de error
        if(login.getId() != null) {
        	Matcher matcher = pattern.matcher(login.getId());
        	if (matcher.matches()) {
        		System.out.println("Email valido");
        	}else
        		errors.reject("invalid.id", "El nombre de usuario debe ser un email valido");
        }
	}

}