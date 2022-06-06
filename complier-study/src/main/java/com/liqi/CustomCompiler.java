package com.liqi;

import com.liqi.struct.Token;

import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CustomCompiler {

    public enum CharState {
        //初始化状态
        Initial,
        //标识符状态
        Identity,
        IntLiteral,
        GT,
        Equal
    }

    public static void main(String[] args) {
        Token token = new Token();
        String content = "int number=1";


    }

    private static List<Token> translate(String content) {
        List<Token> tokenList = new ArrayList<>();
        CharState charState = CharState.Initial;
        StringBuffer tokenText = new StringBuffer("");
        for (char ch : content.toCharArray()) {
            switch (charState) {
                case Initial:
                    charState = initToken(ch);
                    break;
                case Identity:
                    if (isAlpha(ch) || isDigit(ch)) {
                        tokenText.append(ch);
                    } else {
                        charState = initToken(ch);
                    }
                    break;
                case GT:
                    if (ch == '=') {
                        charState = CharState.Equal;
                    }
                    break;
            }

        }
        return null;

    }

    private static CharState initToken(char ch) {
        return null;
    }

    private static boolean isDigit(char ch) {
        String regex = "[0-9]+";
        return matchByRegex(String.valueOf(ch), regex);
    }

    private static boolean isAlpha(char ch) {
        String regex = "[a-zA-Z_]([a-zA-Z_]|[0-9])*";
        return matchByRegex(String.valueOf(ch), regex);
    }

    private static boolean matchByRegex(String text, String regex) {
        return Pattern.matches(regex, text);
    }
}
