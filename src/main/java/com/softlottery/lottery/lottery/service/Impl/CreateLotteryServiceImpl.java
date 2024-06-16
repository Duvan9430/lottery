package com.softlottery.lottery.lottery.service.Impl;

import com.softlottery.lottery.lottery.entity.LotteryEntity;
import com.softlottery.lottery.lottery.mapper.LotteryRestMapper;
import com.softlottery.lottery.lottery.repository.LotteryRepository;
import com.softlottery.lottery.lottery.service.CreateLotteryService;
import com.softlottery.lottery.shared.config.exception.ConstraintViolationException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional()
public class CreateLotteryServiceImpl implements CreateLotteryService {
    private final LotteryRepository lotteryRepository;

    CreateLotteryServiceImpl(
            LotteryRepository lotteryRepository
    ){
        this.lotteryRepository = lotteryRepository;
    }
    @Override
    public Long create(LotteryEntity lotteryEntity) {
        LotteryEntity lottery = new LotteryEntity();
        this.validationNumber(lotteryEntity);
        lottery.setNumberLottery(lotteryEntity.getNumberLottery());
        lottery.setUserId(lotteryEntity.getUserId());
        lottery.setValue(lotteryEntity.getValue());
        lottery.setStatus(lotteryEntity.getStatus());
        lottery.setRaffleLotteryId(lotteryEntity.getRaffleLotteryId());
        lottery.setCreatedAt(LocalDateTime.now());
        return this.lotteryRepository.save(lottery).getId();
    }
    private void validationNumber(LotteryEntity lotteryEntity){
        if(Boolean.TRUE.equals(this.lotteryRepository.existsByNumberLotteryAndRaffleLotteryIdIdAndDeletedAtIsNullIgnoreCase(lotteryEntity.getNumberLottery(),lotteryEntity.getRaffleLotteryId().getId()))){
            throw new ConstraintViolationException("LOTTERY_NUMBER_ALREADY_EXISTS",
                    String.format(" el numero  '%s' ya esta jugando",lotteryEntity.getNumberLottery()));
        }
    }
}
