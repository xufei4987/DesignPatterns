package com.youxu.dp.a04_pattern.interpreter;

import java.util.Map;

public class GreaterExpression implements Expression{
    private String key;
    private Long value;

    public GreaterExpression(String key, Long value) {
        this.key = key;
        this.value = value;
    }

    public GreaterExpression(String strExpression){
        String[] exprs = strExpression.trim().split("\\s+");
        if (exprs.length != 3 || !exprs[1].trim().equals(">")){
            throw new RuntimeException("Expression is invalid: " + strExpression);
        }
        this.key = exprs[0];
        this.value = Long.valueOf(exprs[2]);
    }

    @Override
    public boolean interpret(Map<String, Long> stats) {
        if (stats.containsKey(key) && stats.get(key) > this.value){
            return true;
        }
        return false;
    }
}
