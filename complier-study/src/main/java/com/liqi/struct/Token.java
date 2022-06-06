package com.liqi.struct;

public class Token {
    private TokenType type;
    private String tokenContent;

    public enum TokenType{
        Identifier,IntLiteral,GT,Equal
    }
}
