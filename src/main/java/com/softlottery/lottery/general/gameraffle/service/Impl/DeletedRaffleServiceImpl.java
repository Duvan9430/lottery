package com.softlottery.lottery.general.gameraffle.service.Impl;

import com.softlottery.lottery.general.gameraffle.service.DeletedRaffleService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DeletedRaffleServiceImpl implements DeletedRaffleService {

    DeletedRaffleServiceImpl(){

    }

    @Override
    public void delete(Long id) {

    }
}
