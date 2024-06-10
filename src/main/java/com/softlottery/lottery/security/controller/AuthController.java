package com.softlottery.lottery.security.controller;

import com.softlottery.lottery.security.dto.JwtDto;
import com.softlottery.lottery.security.dto.UserDto;
import com.softlottery.lottery.security.entity.User;
import com.softlottery.lottery.security.jwt.JwtProvider;
import com.softlottery.lottery.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    JwtProvider jwtProvider;

    Map<String, Object> mensajeRetorno = new HashMap<>();


    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody UserDto userDto, BindingResult bindingResult){
        try {
        	if(bindingResult.hasErrors()) {
                mensajeRetorno.put("mensaje", "Existe errores en la validación");
                return new ResponseEntity<>(mensajeRetorno, HttpStatus.BAD_REQUEST);
            }
			
        	Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtProvider.generateToken(authentication);
            
            Optional<User> userO = userService.getByUsername(userDto.getUsername());
            if (userO.isEmpty()) {
                mensajeRetorno.put("mensaje", "El usuario no existe");
                return new ResponseEntity<>(mensajeRetorno, HttpStatus.BAD_REQUEST);
            }
            User user = userO.get();
            userDto.setId(user.getId());
            userDto.setToken(jwt);

            mensajeRetorno.put("mensaje", "Sesión iniciada");
            mensajeRetorno.put("usuario", userDto);

            return new ResponseEntity<>(mensajeRetorno, HttpStatus.OK);
        	
		} catch (Exception e) {
            mensajeRetorno.put("mensaje", e.getMessage());
			return new ResponseEntity<>(mensajeRetorno, HttpStatus.BAD_REQUEST);
		}
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtDto> refresh(@RequestBody JwtDto jwtDto) throws ParseException {
        String token = jwtProvider.refreshToken(jwtDto);
        JwtDto jwt = new JwtDto(token);
        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }
}
