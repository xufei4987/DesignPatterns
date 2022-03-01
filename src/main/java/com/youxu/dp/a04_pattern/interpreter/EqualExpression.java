package com.youxu.dp.a04_pattern.interpreter;

import java.util.Map;

public class EqualExpression implements Expression{
    private String key;
    private Long value;

    public EqualExpression(String key, Long value) {
        this.key = key;
        this.value = value;
    }

    public EqualExpression(String strExpression){
        String[] exprs = strExpression.trim().split("\\s+");
        if (exprs.length != 3 || !exprs[1].trim().equals("==")){
            throw new RuntimeException("Expression is invalid: " + strExpression);
        }
        this.key = exprs[0];
        this.value = Long.valueOf(exprs[2]);
    }

    @Override
    public boolean interpret(Map<String, Long> stats) {
        if (stats.containsKey(key) && stats.get(key).equals(this.value)){
            return true;
        }
        return false;
    }
}
