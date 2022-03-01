package com.youxu.dp.a04_pattern.snapshot;

public class InputText {
    private StringBuilder sb = new StringBuilder();
    public String getText(){
        return sb.toString();
    }
    public void append(String text){
        sb.append(text);
    }
}
