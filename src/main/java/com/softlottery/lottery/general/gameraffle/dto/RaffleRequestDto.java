package com.softlottery.lottery.general.gameraffle.dto;

import com.softlottery.lottery.security.dto.UserDto;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record RaffleRequestDto(
        Long userId,

        @NotBlank(message = "Nombre es obligatorio y no puede ser vacio") String nameRaffle,

        Integer numberLotteryWin,

        @NotBlank(message = "Codigo es obligatorio y no puede ser vacio")  String code,

        String description,

        String imageProduct,

        LocalDate expirationEndGame,

        Integer status
) {
}
