package com.youxu.dp.a04_pattern.interpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AndExpression implements Expression{
    private List<Expression> expressions;

    public AndExpression(String strExpression) {
        String[] exprs = strExpression.split("&&");
        expressions = new ArrayList<>(exprs.length);
        for (String expr : exprs) {
            if (expr.contains(">")){
                expressions.add(new GreaterExpression(expr));
            } else if (expr.contains("<")){
                expressions.add(new LessExpression(expr));
            } else if (expr.contains("==")){
                expressions.add(new EqualExpression(expr));
            } else {
                throw new RuntimeException("Expression is invalid: " + expr);
            }
        }
    }

    @Override
    public boolean interpret(Map<String, Long> stats) {
        for (Expression expression : expressions) {
            if (!expression.interpret(stats)) {
                return false;
            }
        }
        return true;
    }
}
