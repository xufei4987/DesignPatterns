package com.youxu.dp.a04_pattern.interpreter;

import java.util.HashMap;
import java.util.Map;
/**
 * 数据放到一个 Map 中
 * 自定义的告警规则只包含“||、&&、>、<、==”这五个运算符，其中，“>、<、==”运算符的优先级高于“||、&&”运算符，“&&”运算符优先级高于“||”
 * key1 > 100 && key2 < 1000 || key3 == 200
 */
public class StateInterpreter implements Expression{
    private Expression expression;
    public StateInterpreter(String strExpression){
        expression = new OrExpression(strExpression);
    }

    @Override
    public boolean interpret(Map<String, Long> stats) {
        return expression.interpret(stats);
    }

    public static void main(String[] args) {
        String strExpression = "key1 > 100 && key2 < 1000 || key3 == 200";
        HashMap<String, Long> stats = new HashMap<>();
        stats.put("key1",99L);
        stats.put("key2",1001L);
        stats.put("key3",201L);
        StateInterpreter stateInterpreter = new StateInterpreter(strExpression);
        boolean result = stateInterpreter.interpret(stats);
        System.out.println(result);
    }
}
