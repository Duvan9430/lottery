package com.softlottery.lottery.security.dto;

public record UserNoRolDTO(
        Long id,
        String username,
        String password,
        String token

) {
}
