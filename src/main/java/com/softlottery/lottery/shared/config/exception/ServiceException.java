package com.softlottery.lottery.shared.config.exception;

public class ServiceException extends BaseException {

  public ServiceException(String key, String message) {
    super(key, message, 400);
  }

}
