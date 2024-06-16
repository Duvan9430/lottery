package com.softlottery.lottery.lottery.service.Impl;

import com.softlottery.lottery.lottery.dto.LotteryResponseDto;
import com.softlottery.lottery.lottery.entity.LotteryEntity;
import com.softlottery.lottery.lottery.mapper.LotteryRestMapper;
import com.softlottery.lottery.lottery.repository.LotteryRepository;
import com.softlottery.lottery.lottery.service.FindLotteryService;
import com.softlottery.lottery.shared.config.exception.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional()
public class FindLotteryServiceImpl implements FindLotteryService {

    private final LotteryRepository lotteryRepository;
    private final LotteryRestMapper mapper;

    FindLotteryServiceImpl(
            LotteryRepository lotteryRepository,
            LotteryRestMapper mapper
    ){
        this.lotteryRepository = lotteryRepository;
        this.mapper = mapper;
    }
    @Override
    public Page<LotteryEntity> pageableAllLottery(Pageable page, Long id,Long idRaffle, String filter) {
        return this.lotteryRepository.findAllPaginated(filter,id,idRaffle,page);
    }

    @Override
    public Optional<LotteryEntity> findById(Long id) {
        return Optional.ofNullable(this.lotteryRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("LOTTERY_NOT_FOUND",
                String.format("Loteria con numbero '%s' no existe", id))));
    }

    @Override
    public List<LotteryResponseDto> findAllList(Long idUser,Long raffleId) {
        return lotteryRepository.findAllByRaffleLotteryIdIdAndUserIdIdAndDeletedAtIsNullIgnoreCase(raffleId,idUser);
    }
}
