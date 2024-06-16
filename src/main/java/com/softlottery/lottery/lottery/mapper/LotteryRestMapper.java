package com.softlottery.lottery.lottery.mapper;

import com.softlottery.lottery.lottery.dto.LotteryRequestDto;
import com.softlottery.lottery.lottery.dto.LotteryResponseDto;
import com.softlottery.lottery.lottery.entity.LotteryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, injectionStrategy = CONSTRUCTOR)
public interface LotteryRestMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "userId.id", source = "userId")
    @Mapping(target = "raffleLotteryId.id", source = "raffleLotteryId")
    LotteryEntity toDomain(LotteryRequestDto lotteryRequestDto);



    LotteryResponseDto toResponse(LotteryEntity entity);
}
