package com.softlottery.lottery.lottery.service;

import com.softlottery.lottery.lottery.dto.LotteryResponseDto;
import com.softlottery.lottery.lottery.entity.LotteryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface FindLotteryService {
    Page<LotteryEntity> pageableAllLottery(Pageable page, Long id,Long idRaffle, String filter);
    Optional<LotteryEntity> findById(Long id);
    List<LotteryResponseDto> findAllList(Long idUser,Long raffleId);
}
