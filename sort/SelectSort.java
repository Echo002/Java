import java.util.Arrays;

class SelectSort{
    public static void main(String[] args){
        int[] arr = {101, 34, 119, 1};
        System.out.printf("原数组为%s\n", Arrays.toString(arr));
        selectSort(arr);
    }

    public static void selectSort(int[] arr){
        int minIndex = 0;
        int min = arr[0];
        for(int j = 0+1; j < arr.length; j++){
            if(min > arr[j]){
                minIndex = j;
                min = arr[j];
            }
        }
        arr[minIndex] = arr[0];
        arr[0] = min;
        System.out.printf("第一轮排序后的结果是：%s\n", Arrays.toString(arr));
    }
}