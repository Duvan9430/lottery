package com.softlottery.lottery.security.controller;

import com.softlottery.lottery.security.dto.UserDto;
import com.softlottery.lottery.security.entity.User;
import com.softlottery.lottery.security.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    Map<String, Object> mensajeRetorno = new HashMap<>();

    @GetMapping("/pageable")
    ResponseEntity<Page<User>> getDatatable(@PageableDefault Pageable pageable, @RequestParam String busqueda){
        try {
            Page<User> result = userService.getDatatable(pageable);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/{id}")
    ResponseEntity<Optional<User>> getById(@PathVariable Integer id){
        try {
            Optional<User> result = userService.getById(id);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> nuevo(@Valid @RequestBody UserDto userDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            mensajeRetorno.put("mensaje", "Campos invalidos");
            return new ResponseEntity<>(mensajeRetorno, HttpStatus.BAD_REQUEST);
        }

        if(userService.existsByUsername(userDto.getUsername())) {
            mensajeRetorno.put("mensaje", "El nombre de usuario ya existe");
            return new ResponseEntity<>(mensajeRetorno, HttpStatus.BAD_REQUEST);
        }

        userService.save(userDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@Valid @RequestBody UserDto userDto, @PathVariable Integer id, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            mensajeRetorno.put("mensaje", "Campos invalidos");
            return new ResponseEntity<>(mensajeRetorno, HttpStatus.BAD_REQUEST);
        }

        try {
            userService.update(userDto, id);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            mensajeRetorno.put("mensaje", ex.getMessage());
            return new ResponseEntity<>(mensajeRetorno, HttpStatus.BAD_REQUEST);
        }
    }
}
