import java.util.Arrays;

public class FibonacciSearch{
    public static int maxSize = 20;
    public static void main(String[] args){
        
        int[] arr = {1, 8, 10, 89, 1000, 1314};
        System.out.println(fibSearch(arr, 1));
    }

    // 先获取到一个斐波拉契数列(非递归方式实现)
    public static int[] fib(){
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for(int i = 2; i < maxSize; i++){
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    // 斐波拉契查找算法(非递归方式实现)
    public static int fibSearch(int[] arr, int key){
        int low = 0;
        int high = arr.length - 1;
        int k = 0;          // 表示斐波拉契分割数值的下标
        int mid = 0;        // 存放mid值
        int[] f = fib();
        // 获取到斐波拉契分割数值的下标
        while(high > f[k] - 1){
            k++;
        }
        // 因为f[k]的值可能大于arr的长度，需要构建一个新数组
        int[] temp = Arrays.copyOf(arr, f[k]);
        // 用最后的数字填充arr
        for(int i = high + 1; i < temp.length; i++){
            temp[i] = arr[high];
        }
        while(low <= high){
            mid = low + f[k - 1] - 1;
            if(key < temp[mid]){
                high = mid - 1;
                k--;        // 下次循环mid = f[k - 1 - 1] - 1 
            }else if(key > temp[mid]){
                low = mid + 1;
                k=-2;       // 下次循环mid = f[k - 1 - 2] - 1
            }else{
                if(mid <= high){
                    return mid;
                }else{
                    return high;
                }
            }
        }
        return -1;
    }
}