package com.softlottery.lottery.general.gameraffle.service.Impl;

import com.softlottery.lottery.general.gameraffle.entity.RaffleEntity;
import com.softlottery.lottery.general.gameraffle.repository.RaffleRepository;
import com.softlottery.lottery.general.gameraffle.service.FindRaffleService;
import com.softlottery.lottery.general.gameraffle.service.UpdateRaffleService;
import com.softlottery.lottery.shared.config.exception.ConstraintViolationException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
public class UpdateRaffleServiceImpl implements UpdateRaffleService {
    private final RaffleRepository repository;

    private final FindRaffleService findRaffleService;

    UpdateRaffleServiceImpl(
            RaffleRepository repository,
            FindRaffleService findRaffleService
    )
    {
        this.repository = repository;
        this.findRaffleService = findRaffleService;
    }

    @Override
    public void update(RaffleEntity raffleEntity, Long id) {
        this.findRaffleService.findById(id).ifPresent(data->{
            validateCode(raffleEntity,id);
            data.setUserId(raffleEntity.getUserId());
            data.setNameRaffle(raffleEntity.getNameRaffle());
            data.setNumberLotteryWin(raffleEntity.getNumberLotteryWin());
            data.setCode(raffleEntity.getCode());
            data.setDescription(raffleEntity.getDescription());
            data.setImageProduct(raffleEntity.getImageProduct());
            data.setExpirationEndRaffle(raffleEntity.getExpirationEndRaffle());
            data.setStatus(raffleEntity.getStatus());
            data.setUpdatedAt(LocalDateTime.now());
            this.repository.save(data);
        });
    }
    private void validateCode(RaffleEntity raffleEntity,Long id){
        if (Boolean.TRUE.equals(this.repository.existsByIdNotAndCodeAndDeletedAtIsNullIgnoreCase(id,raffleEntity.getCode()))){
            throw new ConstraintViolationException("RAFFLE_CODE_ALREADY_EXISTS",
                    String.format(" el codigo  '%s' ya esta jugando",raffleEntity.getCode()));
        }
    }
}
