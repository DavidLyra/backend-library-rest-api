package com.library.api.controller;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;

import com.library.api.config.security.JWTUtil;
import com.library.api.model.dto.TokenDto;
import com.library.api.model.form.LoginForm;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Api(tags = "Authentication Controller", value = "Backend Library REST API - Lampiris")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JWTUtil jwtUtil;

	@PostMapping
	@PermitAll
	public ResponseEntity<TokenDto> authenticate(@RequestBody @Valid LoginForm form) {
		UsernamePasswordAuthenticationToken dataLogin = form.converter();
		
		try {
			Authentication authentication = authManager.authenticate(dataLogin);
			String token = jwtUtil.generateToken((UserDetails) authentication.getPrincipal());
			return ResponseEntity.ok(new TokenDto(token));
		} catch (AuthenticationException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}

}
