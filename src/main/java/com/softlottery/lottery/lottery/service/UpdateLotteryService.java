package com.softlottery.lottery.lottery.service;

import com.softlottery.lottery.lottery.entity.LotteryEntity;

public interface UpdateLotteryService {
    void update(LotteryEntity lotteryEntity, Long id);
}
