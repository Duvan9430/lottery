package com.softlottery.lottery.general.gameraffle.mapper;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import com.softlottery.lottery.general.gameraffle.dto.RaffleRequestDto;
import com.softlottery.lottery.general.gameraffle.dto.RaffleResponseDto;
import com.softlottery.lottery.general.gameraffle.entity.RaffleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = SPRING ,injectionStrategy = CONSTRUCTOR)
public interface RaffleRestMapper {

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "updatedAt",ignore = true)
    @Mapping(target = "deletedAt",ignore = true)
    @Mapping(target = "userId.id",source = "userId")
    RaffleEntity toDomain(RaffleRequestDto raffleRequestDto);

    @Mapping(target = "userId",source = "userId.id")
    RaffleResponseDto toResponse(RaffleEntity raffleEntity);
}
