package com.softlottery.lottery.lottery.service;

import com.softlottery.lottery.lottery.dto.LotteryRequestDto;
import com.softlottery.lottery.lottery.entity.LotteryEntity;

public interface CreateLotteryService {
    Long create(LotteryEntity lotteryEntity);
}
