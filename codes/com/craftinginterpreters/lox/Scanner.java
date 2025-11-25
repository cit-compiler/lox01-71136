package com.craftinginterpreters.lox;

import java.util.ArrayList;
import java.util.List;

public class Scanner {
    private final String source;
    private final List<Token> tokens = new ArrayList<>();

    public Scanner(String source) {
        this.source = source;
    }

    public List<Token> scanTokens() {
        // ここに字句解析のループ処理が入ります

        // 最後にEOFトークンを追加
        tokens.add(new Token(TokenType.EOF, "", null, 1));
        return tokens;
    }
}