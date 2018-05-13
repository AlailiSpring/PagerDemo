package com.lbd.learn.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCheckUtil {

    /**
     * 判断传入的参数是不是数字
     * @param numStr
     * @return
     */
    public static boolean checkIsNum(String numStr) {
        Pattern pattern=Pattern.compile("[1-9]{1}\\d*");
        Matcher matcher = pattern.matcher(numStr);
        return matcher.matches();
    }
}
