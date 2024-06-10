package com.softlottery.lottery.security.service;

import com.softlottery.lottery.security.entity.Role;
import com.softlottery.lottery.security.repository.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class RolService {

	@Autowired
    RoleRepository rolRepository;

    public Optional<Role> getByRolNombre(String name){
        return rolRepository.findByNameAndStateTrue(name);
    }
    
    public Optional<Role> getById(Long id) {
    	return rolRepository.findByIdAndStateTrue(id);
    }

    public void save(Role rol){
        rolRepository.save(rol);
    }
    
}
