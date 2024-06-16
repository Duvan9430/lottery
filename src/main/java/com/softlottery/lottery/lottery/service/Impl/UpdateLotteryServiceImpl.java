package com.softlottery.lottery.lottery.service.Impl;

import com.softlottery.lottery.lottery.entity.LotteryEntity;
import com.softlottery.lottery.lottery.mapper.LotteryRestMapper;
import com.softlottery.lottery.lottery.repository.LotteryRepository;
import com.softlottery.lottery.lottery.service.FindLotteryService;
import com.softlottery.lottery.lottery.service.UpdateLotteryService;
import com.softlottery.lottery.shared.config.exception.ConstraintViolationException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional()
public class UpdateLotteryServiceImpl implements UpdateLotteryService {
    private final LotteryRepository lotteryRepository;

    private final FindLotteryService findLotteryService;
    UpdateLotteryServiceImpl(
            LotteryRepository lotteryRepository,
            FindLotteryService findLotteryService
    ){
        this.lotteryRepository = lotteryRepository;
        this.findLotteryService = findLotteryService;
    }
    @Override
    public void update(LotteryEntity lotteryEntity, Long id) {
        this.findLotteryService.findById(id).ifPresent(data->{
            validationNumber(lotteryEntity,id);
            data.setNumberLottery(lotteryEntity.getNumberLottery());
            data.setUserId(lotteryEntity.getUserId());
            data.setValue(lotteryEntity.getValue());
            data.setRaffleLotteryId(lotteryEntity.getRaffleLotteryId());
            data.setStatus(lotteryEntity.getStatus());
            data.setUpdatedAt(LocalDateTime.now());
            this.lotteryRepository.save(data);
        });
    }
    private void validationNumber(LotteryEntity lotteryEntity, Long id){
        if (Boolean.TRUE.equals(this.lotteryRepository.existsByIdNotAndNumberLotteryAndRaffleLotteryIdIdAndDeletedAtIsNullIgnoreCase(id,lotteryEntity.getNumberLottery(),lotteryEntity.getRaffleLotteryId().getId()))){
            throw new ConstraintViolationException("LOTTERY_NUMBER_ALREADY_EXISTS",
                    String.format(" el numero  '%s' ya esta jugando",lotteryEntity.getNumberLottery()));
        }
    }
}
