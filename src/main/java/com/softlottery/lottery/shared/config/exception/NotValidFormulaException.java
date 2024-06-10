package com.softlottery.lottery.shared.config.exception;

public class NotValidFormulaException extends BaseException {

  public NotValidFormulaException(String key, String message) {
    super(key, message, 400);
  }

}
