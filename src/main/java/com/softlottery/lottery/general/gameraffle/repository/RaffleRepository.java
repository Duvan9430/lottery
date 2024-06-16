package com.softlottery.lottery.general.gameraffle.repository;

import com.softlottery.lottery.general.gameraffle.entity.RaffleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RaffleRepository extends JpaRepository<RaffleEntity,Long> {

    @Query(value = "SELECT * FROM public.raffle_lottery AS rt " +
            " WHERE  rt.deleted IS NULL  AND (lower(concat(rt.code,rt.nameRaffle)) like  lower(?1))", nativeQuery=true)
    Page<RaffleEntity> findAllPaginated(String filter, Pageable pageable);
    Boolean existsByCodeAndDeletedAtIsNullIgnoreCase(String code);

    Boolean existsByIdNotAndCodeAndDeletedAtIsNullIgnoreCase(Long id,String code);
}
