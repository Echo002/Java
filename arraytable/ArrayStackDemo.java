package arraytable;

import java.util.Scanner;

class ArrayStackDemo{
    public static void main(final String[] args){
        final ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true;
        final Scanner scanner = new Scanner(System.in);

        while(loop){
            System.out.println("show：显示堆栈");
            System.out.println("exit：退出程序");
            System.out.println("push：入栈");
            System.out.println("pop：出栈");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch(key){
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    final int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try{
                        final int res = stack.pop();
                        System.out.printf("出栈的数据是 %d\n",res);
                    } catch (final Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}

class ArrayStack{
    // 栈的长度
    private final int maxSize;
    // 存储栈的数组
    private final int[] stack;
    // 栈顶
    private int top = -1;

    public ArrayStack(final int maxSize){
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    public boolean isFull(){
        return top == maxSize - 1;
    }

    public boolean isEmpty(){
        return top == -1;
    }

    public void push(final int value){
        if(isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈空 没有数据");
        }
        final int value = stack[top];
        top--;
        return value;
    }

    public void list(){
        if(isEmpty()){
            System.out.println("栈空 没有数据");
            return;
        }
        for(int i = top; i >= 0; i--){
            System.out.printf("stack[%d] = %d\n", i, stack[i]);
        }
    }
}