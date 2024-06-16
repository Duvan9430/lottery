package com.softlottery.lottery.lottery.dto;

import com.softlottery.lottery.general.gameraffle.dto.RaffleResponseDto;
import com.softlottery.lottery.security.dto.UserDto;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record LotteryRequestDto(
        Long userId,
        Long raffleLotteryId,

        @NotBlank(message = "numero loteria es obligatorio y no puede ser vacio") Integer numberLottery,

        @NotBlank(message = "El valor es obligatorio y no puede ser vacio") BigDecimal value,

        Integer status
) {
}
