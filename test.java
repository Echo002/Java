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
}