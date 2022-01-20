package com.youxu.dp.a03_refactoring;

public interface IdGenerator {
    String generate() throws IdGenerationFailureException;
}
