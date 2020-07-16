package arraytable;

public class Calculator {
    public static void main(String[] args) {
        String expression = "7-6-1-8";
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int res = 0;
        int oper = 0;
        char ch = ' ';
        String keepNum = "";

        while (true) {
            ch = expression.substring(index, index + 1).charAt(0);
            if(operStack.isOper(ch)){

                if(!operStack.isEmpty()){
                    if(operStack.priority(ch) <= operStack.priority(operStack.peek())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = operStack.cal(num1, num2, oper);
                        numStack.push(res);
                        operStack.push(ch);
                    }else{
                        operStack.push(ch);
                    }
                }else{
                    operStack.push(ch);
                }
            }else{
                //numStack.push(ch - 48); 处理多位数
                keepNum += ch;
                // 如果ch为最后一位
                if(index == expression.length() - 1){
                    numStack.push(Integer.parseInt(keepNum));
                }else{
                    if(operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))){
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
            }
            index ++;
            if(index >= expression.length()){
                break;
            }
        }

        while(true){
            if(operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = operStack.cal(num1, num2, oper);
            numStack.push(res);
        }
        System.out.printf("表达式 %s = %d", expression, numStack.pop());
    }
}

class ArrayStack2{
    // 栈的长度
    private final int maxSize;
    // 存储栈的数组
    private final int[] stack;
    // 栈顶
    private int top = -1;

    public ArrayStack2(final int maxSize){
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

    // 返回栈顶的值
    public int peek(){
        return stack[top];
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

    // 返回运算符的优先级（使用数字表示，数字越大 优先级越高）
    public int priority(int oper){
        if(oper == '*' || oper == '/')
            return 1;
        else if(oper == '+' || oper == '-')
            return 0;
        else
            return -1;
    }

    // 判断是否为操作符
    public boolean isOper(char val){
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    // 计算
    public int cal(int num1, int num2, int oper){
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }

}