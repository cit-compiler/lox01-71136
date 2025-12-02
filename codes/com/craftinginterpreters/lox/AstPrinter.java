package com.craftinginterpreters.lox;

// Expr.Visitorを実装し、ASTを文字列（String）に変換する
class AstPrinter implements Expr.Visitor<String> {
    
    // ASTのルートノードを受け取り、走査を開始する
    String print(Expr expr) {
        return expr.accept(this);
    }

    // 二項演算子の処理 (例: (+ 1 2))
    @Override
    public String visitBinaryExpr(Expr.Binary expr) {
        return parenthesize(expr.operator.lexeme, expr.left, expr.right);
    }

    // グループ化の処理 (例: (group (* 1 2)))
    @Override
    public String visitGroupingExpr(Expr.Grouping expr) {
        return parenthesize("group", expr.expression);
    }

    // リテラルの処理 (例: 123, "string")
    @Override
    public String visitLiteralExpr(Expr.Literal expr) {
        if (expr.value == null) return "nil";
        return expr.value.toString();
    }

    // 単項演算子の処理 (例: (- 123))
    @Override
    public String visitUnaryExpr(Expr.Unary expr) {
        return parenthesize(expr.operator.lexeme, expr.right);
    }

    // Lisp風の前置記法を作成するための補助メソッド
    private String parenthesize(String name, Expr... exprs) {
        StringBuilder builder = new StringBuilder();

        builder.append("(").append(name);
        for (Expr expr : exprs) {
            builder.append(" ");
            builder.append(expr.accept(this)); // 再帰的に子ノードを走査
        }
        builder.append(")");

        return builder.toString();
    }
}