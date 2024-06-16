package com.softlottery.lottery.lottery.service.Impl;

import com.softlottery.lottery.lottery.mapper.LotteryRestMapper;
import com.softlottery.lottery.lottery.repository.LotteryRepository;
import com.softlottery.lottery.lottery.service.DeletedLotteryService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional()
public class DeletedLotteryServiceImpl implements DeletedLotteryService {
    private final LotteryRepository lotteryRepository;

    DeletedLotteryServiceImpl(
            LotteryRepository lotteryRepository
    ){
        this.lotteryRepository = lotteryRepository;
    }
    @Override
    public void deleted(Long id) {

    }
}
