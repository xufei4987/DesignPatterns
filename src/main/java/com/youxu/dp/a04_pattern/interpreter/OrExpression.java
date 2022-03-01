package com.youxu.dp.a04_pattern.interpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrExpression implements Expression{
    private List<Expression> expressions;

    public OrExpression(String strExpression) {
        String[] exprs = strExpression.split("\\|\\|");
        expressions = new ArrayList<>(exprs.length);
        for (String expr:exprs){
            expressions.add(new AndExpression(expr));
        }
    }

    @Override
    public boolean interpret(Map<String, Long> stats) {
        for (Expression expression : expressions) {
            if (expression.interpret(stats)){
                return true;
            }
        }
        return false;
    }
}
