package com.softlottery.lottery.lottery.entity;


import com.softlottery.lottery.general.gameraffle.entity.RaffleEntity;
import com.softlottery.lottery.security.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode
@Entity
@Table(name = "lottery",schema = "public")
public class LotteryEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY  )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "raffle_lottery_id",referencedColumnName = "id")
    private RaffleEntity raffleLotteryId;

    @Column(name = "number_lottery",nullable = false)
    private Integer numberLottery;

    @Column(name = "value",nullable = false)
    private BigDecimal value;

    @Column(name = "status",nullable = false)
    private Integer status;

    @Column(name = "created",nullable = true)
    private LocalDateTime createdAt;

    @Column(name = "updated",nullable = true)
    private LocalDateTime updatedAt;

    @Column(name = "deleted",nullable = true)
    private LocalDateTime deletedAt;

}
