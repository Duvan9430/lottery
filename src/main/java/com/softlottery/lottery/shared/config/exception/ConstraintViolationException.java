package com.softlottery.lottery.shared.config.exception;

public class ConstraintViolationException extends BaseException {

  public ConstraintViolationException(String key, String message) {
    super(key, message, 400);
  }

}
