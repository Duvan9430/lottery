package com.softlottery.lottery.lottery.dto;

import com.softlottery.lottery.general.gameraffle.dto.RaffleResponseDto;
import com.softlottery.lottery.security.dto.UserNoRolDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record LotteryResponseDto(

        Long id,

        UserNoRolDTO userId,
        RaffleResponseDto raffleLotteryId,

        Integer numberLottery,

        BigDecimal value,

        Integer status,

        LocalDateTime createdAt,


        LocalDateTime updatedAt,

        LocalDateTime deletedAt
) {
}
