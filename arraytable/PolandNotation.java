import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation{
    public static void main(String[] args) {
        // String suffixExpression = "3 4 + 5 * 6 -";
        
        // List<String> a = getListString(suffixExpression);
        // int result = calculate(a);
        // System.out.printf("%d\n", result);

        String expression = "1+((2+3)*4)-5";
        List <String> infixExpression = toInfixExpression(expression);
        System.out.printf("%s\n", infixExpression);
        // [1, +, (, (, 2, +, 3, ), *, 4, ), -, 5]
        List<String> parseSuffixExpressionList = parseSuffixExpressionList(infixExpression);
        System.out.printf("后缀表达式对应的list" + parseSuffixExpressionList);
    }

    // 将String转化为list
    public static List<String> getListString(String suffixExpression){
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for(String ele : split){
            list.add(ele);
        }
        return list;
    }

    // 将中缀表达式转化为逆波兰式
    public static List<String> parseSuffixExpressionList(List<String> ls){
        Stack<String> s1 = new Stack<String>();
        // Stack<String> s2 = new Stack<String>(); s2可以用ArrayList代替
        List<String> s2 = new ArrayList<String>();
        for(String item:ls){
            if(item.matches("\\d+")){
                s2.add(item);
            } else if(item.equals("(")){
                s1.push(item);
            } else if(item.equals(")")){
                while(!s1.peek().equals("("))
                    s2.add(s1.pop());
                s1.pop(); // 将（弹出s1堆栈
            } else {
                // 当item优先级小于或等于栈顶运算符
                while(s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }

        while(s1.size() != 0){
            s2.add(s1.pop());
        }

        return s2;
        // 存放在list中
    }

    // 将中缀表达式转化为list（考虑多位数）
    public static List<String> toInfixExpression(String s){
        List<String> ls = new ArrayList<String>();
        int i = 0;          // 遍历中缀计数
        String str;    // 多位数的拼接
        char c;
        do{
            // 非数字将其加入到ls
            if((c=s.charAt(i)) < 48 || (c=s.charAt(i)) > 57){
                ls.add("" + c);
                i++;
            }else{
            // 考虑多位数的问题
                str = "";
                while(i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57){
                    str += c;
                    i++;
                }
                ls.add(str);
            }
        }while(i < s.length());
        return ls;
    }

    // 传入后缀表达式计算其结果
    public static int calculate(List<String> ls){
        int res = 0;
        Stack<String> stack = new Stack<String>();
        for(String item:ls){
            // 正则表达式
            if(item.matches("\\d+")){
                stack.push(item);
            }else{
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                if(item.equals("+"))
                    res = num1 + num2;
                if(item.equals("-"))
                    res = num1 - num2;
                if(item.equals("*"))
                    res = num1 * num2;
                if(item.equals("/"))
                    res = num1 / num2;
                stack.push("" + res);
            }
        }
        return Integer.parseInt(stack.pop());
    }
}

// Operation 返回运算符对应的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static int getValue(String Operation){
        int result = 0;
        switch(Operation){
            case "+": 
                result = ADD;
                break;
            case "-": 
                result = SUB;
                break;
            case "*": 
                result = MUL;
                break;
            case "/": 
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }

}