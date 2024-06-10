package com.softlottery.lottery.security.service.impl;

import com.softlottery.lottery.security.dto.UserDto;
import com.softlottery.lottery.security.entity.User;
import com.softlottery.lottery.security.repository.UserRepository;
import com.softlottery.lottery.security.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository usuarioRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Page<User> getDatatable(Pageable pageable) {
        return this.usuarioRepository.findAll(pageable);
    }

    @Override
    public Optional<User> getById(Integer id) {
        return this.usuarioRepository.findById(id);
    }

    public Optional<User> getByUsername(String username){
        return usuarioRepository.findByUsername(username);
    }

    public Boolean existsByUsername(String username){
        return usuarioRepository.existsByUsername(username);
    }

    public void save(UserDto userDto){

        User usuario = new User();
        usuario.setUsername(userDto.getUsername());
        usuario.setPassword(passwordEncoder.encode(userDto.getPassword()));

        usuarioRepository.save(usuario);
    }

    @Override
    public void update(UserDto userDto, Integer id) throws Exception {
        Optional<User> userOptional = this.getById(id);
        if (userOptional.isEmpty()) throw new Exception("Usuario no encontrado");

        User usuario = userOptional.get();
        usuario.setUsername(userDto.getUsername());
        if (!usuario.getPassword().equalsIgnoreCase(userDto.getPassword())) {
            usuario.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }
        usuarioRepository.save(usuario);
    }
}
