package com.softlottery.lottery.general.gameraffle.entity;

import com.softlottery.lottery.security.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode
@Entity
@Table(name = "raffle_lottery",schema = "public")
public class RaffleEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY  )
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User userId;

    @Column(name = "name_raffle",nullable = false)
    private String nameRaffle;

    @Column(name = "number_lottery_win",nullable = true)
    private Integer numberLotteryWin;

    @Column(name = "code",nullable = false)
    private String code;

    @Column(columnDefinition = "TEXT",name = "description",nullable = true)
    private String description;

    @Column(columnDefinition = "TEXT",name = "image_product",nullable = true)
    private String imageProduct;

    @Column(name = "expiration_end_raffle",nullable = true)
    private LocalDate expirationEndRaffle;

    @Column(name = "status",nullable = true)
    private Integer status;

    @Column(name = "created",nullable = true)
    private LocalDateTime createdAt;

    @Column(name = "updated",nullable = true)
    private LocalDateTime updatedAt;

    @Column(name = "deleted",nullable = true)
    private LocalDateTime deletedAt;

}
