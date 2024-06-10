package com.softlottery.lottery.security.service;

import com.softlottery.lottery.security.dto.UserDto;
import com.softlottery.lottery.security.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    Page<User> getDatatable(Pageable pageable);
    Optional<User> getById(Integer id);
    Optional<User> getByUsername(String username);
    Boolean existsByUsername(String username);
    void save(UserDto userDto);
    void update(UserDto userDto, Integer id) throws Exception;
}
