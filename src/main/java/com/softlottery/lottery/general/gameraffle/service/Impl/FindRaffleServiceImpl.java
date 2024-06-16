package com.softlottery.lottery.general.gameraffle.service.Impl;

import com.softlottery.lottery.general.gameraffle.entity.RaffleEntity;
import com.softlottery.lottery.general.gameraffle.repository.RaffleRepository;
import com.softlottery.lottery.general.gameraffle.service.FindRaffleService;
import com.softlottery.lottery.shared.config.exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FindRaffleServiceImpl implements FindRaffleService {
    private final RaffleRepository raffleRepository;
    FindRaffleServiceImpl(RaffleRepository raffleRepository){
        this.raffleRepository = raffleRepository;
    }

    @Override
    public Page<RaffleEntity> pageableAllLottery(Pageable page, String filter) {
        return this.raffleRepository.findAllPaginated(filter,page);
    }

    @Override
    public Optional<RaffleEntity> findById(Long id) {
        return Optional.ofNullable(this.raffleRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("RAFFLE_NOT_FOUND",
                String.format("Loteria con codigo '%s' no existe", id))));
    }

    @Override
    public List<RaffleEntity> findAllList() {
        return this.raffleRepository.findAll();
    }
}
