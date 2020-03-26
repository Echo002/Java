import java.util.Arrays;

public class RadixSort {
    public static void main(String[] args){
        int arr[] = {53, 3, 542, 748, 14, 214};
        radixSort(arr);
        System.out.println("1:" + Arrays.toString(arr));
    }

    public static void radixSort(int[] arr){
        // 得到数组中最大的数的位数
        int max = arr[0];
        for(int i = 1; i < arr.length; i++){
            if(arr[i] > max){
                max = arr[i];
            }
        }
        // 得到最大数是几位数
        int maxLength = (max + "").length();


        // 定义一个二维数组表示10个桶，每个桶为一个一位数组
        int [][] bucket = new int[10][arr.length];  // 空间换时间
        // 为了记录每个桶实际存放了多少个数据，定义一维数组存放各桶数据个数
        int[] bucketElementCounts = new int[10];

        for(int i = 0, n = 1; i < maxLength; i++, n*=10){
            for(int j = 0; j < arr.length; j++){
                // 取出每个元素的每个位
                int digitOfElement = arr[j] / n % 10;
                // 放入到对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
    
            // 按照桶的顺序，依次将数据放入原来的数组
            int index = 0;
            // 遍历每一个桶，将数据放入到原数组
            for(int k = 0; k < bucketElementCounts.length; k++){
                // 如果桶中有数据，我们才放入原数据
                if(bucketElementCounts[k] != 0){
                    for(int l = 0; l < bucketElementCounts[k]; l++){
                        arr[index] = bucket[k][l];
                        index++;
                    }
                }
                // 将桶置0
                bucketElementCounts[k] = 0;
            }
        }
    }
}