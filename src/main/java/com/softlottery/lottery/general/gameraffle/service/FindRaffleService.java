package com.softlottery.lottery.general.gameraffle.service;

import com.softlottery.lottery.general.gameraffle.entity.RaffleEntity;
import com.softlottery.lottery.lottery.dto.LotteryResponseDto;
import com.softlottery.lottery.lottery.entity.LotteryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface FindRaffleService {
    Page<RaffleEntity> pageableAllLottery(Pageable page, String filter);
    Optional<RaffleEntity> findById(Long id);
    List<RaffleEntity> findAllList();
}
