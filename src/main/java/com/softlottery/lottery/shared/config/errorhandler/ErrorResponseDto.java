package com.softlottery.lottery.shared.config.errorhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public record ErrorResponseDto(String key,
                               String message,
                               String[] errors,
                               LocalDateTime dateTime) {

}
