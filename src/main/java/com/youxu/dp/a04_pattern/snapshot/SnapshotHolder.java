package com.youxu.dp.a04_pattern.snapshot;

import java.util.Scanner;
import java.util.Stack;

/**
 *
 * 用户输入“:list”，程序在命令行中输出内存文本的内容
 * 用户输入“:undo”，程序会撤销上一次输入的文本
 >hello
 >:list
 hello
 >world
 >:list
 helloworld
 >:undo
 >:list
 hello
 */
public class SnapshotHolder {
    private Stack<InputText> stack = new Stack<>();

    private void pushText(String text){
        InputText inputText = new InputText();
        if (!stack.isEmpty()){
            inputText.append(stack.peek().getText());
        }
        inputText.append(text);
        stack.push(inputText);
    }

    private void popText(){
        if (stack.isEmpty()){
            return;
        }
        stack.pop();
    }

    public String list(){
        return stack.peek().getText();
    }

    public String undo(){
        popText();
        if (stack.isEmpty()){
            return null;
        }
        return stack.peek().getText();
    }

    public void input(String text){
        pushText(text);
    }

    public static void main(String[] args) {
        SnapshotHolder snapshotHolder = new SnapshotHolder();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (":list".equals(line)){
                System.out.println(snapshotHolder.list());
            } else if (":undo".equals(line)){
                System.out.println(snapshotHolder.undo());
            } else {
                snapshotHolder.input(line);
            }
        }
    }

}
