class Queue8{
    int max = 8;
    int[] array = new int[max];
    static int count = 0; 
    public static void main(String[] args){
        //测试
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.println("一共有" + count + "种解法");
    }

    // 放置第n个皇后 **递归函数中又有循环 --> 回溯
    private void check(int n){
        if(n == max){
            print();
            return;
        }
        // 依次放入皇后看是否冲突
        for(int i=0; i<max; i++){
            // 先把当前皇后放到该行的第一列
            array[n] = i;
            // 放置后进行判断是否冲突
            if(judge(n)){
                // 不冲突继续放 开始递归
                check(n+1);
            }
            // 冲突继续执行循环
        }
    }

    // 当我们放置第n个皇后时，检测是否和前面的n-1个皇后冲突
    private boolean judge(int n){
        for(int i=0; i < n; i++){
            // 判断n是否在同一列 || n是否和i在同一斜线
            if(array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n] - array[i]))
                return false;
            }
        return true;
    }

    // 皇后摆放的位置打印（存储位置用的一维数组，每个元素代表其所在的行，元素的值代表所在的列）
    private void print(){
        count++;
        for(int i=0; i<array.length; i++){
            System.out.printf(array[i]+" ");
        }
        System.out.println();
    }
}