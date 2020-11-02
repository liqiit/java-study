package com.liqi.test;

import java.text.MessageFormat;

/**
 * Title: Test
 * Description:
 * Company: iFree Group
 *
 * @author liqi
 * @date 2020/9/4
 */
public class Test {
    public static void main(String[] args) {
        String url="http://localhost?imsi={0}";
        String message=MessageFormat.format(url, "123");
        System.out.print(message);
    }
}
