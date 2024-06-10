package com.softlottery.lottery.shared.utils;

import java.util.Objects;

public class StringUtils {

    private StringUtils() {

    }

    public static boolean isNullOrEmpty(String str) {
        return Objects.isNull(str) || str.isEmpty();
    }

}
