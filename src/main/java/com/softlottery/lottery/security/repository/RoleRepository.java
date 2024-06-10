package com.softlottery.lottery.security.repository;

import com.softlottery.lottery.security.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByNameAndStateTrue(String name);
	Optional<Role> findByIdAndStateTrue(Long id);
	
}
