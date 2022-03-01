package com.youxu.dp.a04_pattern.interpreter;

import java.util.Map;

public interface Expression {
    boolean interpret(Map<String, Long> stats);
}
