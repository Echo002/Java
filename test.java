public static void selectSort(int[] arr){
    for(int i = 0; i < arr.length - 1; i++){
        int minIndex = i;
        int min = arr[i];
        for(int j = i+1; j < arr.length; j++){
            if(min > arr[j]){ // 从大到小排只需修改这一行代码
                minIndex = j;
                min = arr[j];
            }
        }
        if(minIndex != i){
            arr[minIndex] = arr[i];
            arr[i] = min;
        }
    }
}