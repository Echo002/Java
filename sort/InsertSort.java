import java.util.Arrays;

class InsertSort{
    public static void main(String[] args){
        int[] arr = {101, 34, 119, 1};
    }

    public static void insertSort(int[] arr){
        int insertVal = arr[1];
        int insertIndex = 1 - 1;

        // 不越界、待插入的数没有找到插入位置
        while(insertIndex >= 0 && insertVal < arr[insertIndex]){
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex --;
        }
        // 当退出while循环时，说明插入的位置找到
        arr[insertIndex + 1] = insertVal;
        System.out.println("第一轮插入后");
        System.out.println(Arrays.toString(arr));
    }
}