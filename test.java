// 希尔排序(移位法)
public static void shellSort2(int[] arr){
    for(int gap = arr.length / 2; gap > 0; gap /= 2){
        // 从第gap个元素，逐个对其所在的组进行直接插入排序
        for(int i = gap; i < arr.length; i++){
            int j = i;
            int temp = arr[j];
            if(arr[j] < arr[j =gap]){
                while(j - gap >= 0 && temp < arr[j - gap]){
                    // 移动
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                // 退出循环说明找到了插入的位置
                arr[j] = temp;
            }
        }
    }
}