package com.softlottery.lottery.lottery.repository;

import com.softlottery.lottery.lottery.dto.LotteryResponseDto;
import com.softlottery.lottery.lottery.entity.LotteryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LotteryRepository extends JpaRepository<LotteryEntity,Long> {
    @Query(value = "SELECT * FROM public.lottery AS lt " +
            " WHERE lt.user_id =?2 AND lt.raffle_lottery_id =?3 AND lt.deleted IS NULL  AND (lower(concat(lt.number_lottery,lt.value)) like  lower(?1))", nativeQuery=true)
    Page<LotteryEntity> findAllPaginated(String filter, Long idUser,Long idRaffle, Pageable pageable);

    Boolean existsByNumberLotteryAndRaffleLotteryIdIdAndDeletedAtIsNullIgnoreCase(Integer numberLottery,Long raffleId);

    Boolean existsByIdNotAndNumberLotteryAndRaffleLotteryIdIdAndDeletedAtIsNullIgnoreCase(Long id,Integer numberLottery,Long raffleId);

    List<LotteryResponseDto> findAllByRaffleLotteryIdIdAndUserIdIdAndDeletedAtIsNullIgnoreCase(Long raffleId, Long userId);
}
