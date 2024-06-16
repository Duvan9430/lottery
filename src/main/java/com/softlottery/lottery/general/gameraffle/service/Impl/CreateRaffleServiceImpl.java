package com.softlottery.lottery.general.gameraffle.service.Impl;

import com.softlottery.lottery.general.gameraffle.entity.RaffleEntity;
import com.softlottery.lottery.general.gameraffle.repository.RaffleRepository;
import com.softlottery.lottery.general.gameraffle.service.CreateRaffleService;
import com.softlottery.lottery.shared.config.exception.ConstraintViolationException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
public class CreateRaffleServiceImpl implements CreateRaffleService {
    private final RaffleRepository repository;

    CreateRaffleServiceImpl(RaffleRepository repository){
        this.repository = repository;
    }

    @Override
    public Long create(RaffleEntity raffleEntity) {
        this.validateCode(raffleEntity.getCode());
        RaffleEntity raffleDb = new RaffleEntity();
        raffleDb.setUserId(raffleEntity.getUserId());
        raffleDb.setNameRaffle(raffleEntity.getNameRaffle());
        raffleDb.setNumberLotteryWin(raffleEntity.getNumberLotteryWin());
        raffleDb.setCode(raffleEntity.getCode());
        raffleDb.setDescription(raffleEntity.getDescription());
        raffleDb.setImageProduct(raffleEntity.getImageProduct());
        raffleDb.setExpirationEndRaffle(raffleEntity.getExpirationEndRaffle());
        raffleDb.setStatus(raffleEntity.getStatus());
        raffleDb.setCreatedAt(LocalDateTime.now());
        return this.repository.save(raffleDb).getId();
    }
    private void validateCode(String code){
        if (Boolean.TRUE.equals(this.repository.existsByCodeAndDeletedAtIsNullIgnoreCase(code))){
            throw new ConstraintViolationException("RAFFLE_CODE_ALREADY_EXISTS",
                    String.format(" el codigo  '%s' ya esta jugando",code));
        }
    }
}
