package com.softlottery.lottery.general.gameraffle.dto;

import com.softlottery.lottery.security.dto.UserDto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record RaffleResponseDto(
     Long id,

     UserDto userId,

     String nameRaffle,

     Integer numberLotteryWin,

     String code,

     String description,

     String imageProduct,

     LocalDate expirationEndGame,

     Integer status,

     LocalDateTime createdAt,

     LocalDateTime updatedAt,

     LocalDateTime deletedAt
) {
}
