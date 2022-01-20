package com.youxu.dp.a03_refactoring;

public class IdGenerationFailureException extends Throwable {
    public IdGenerationFailureException(String s, Exception e) {
        super(s,e);
    }
}
