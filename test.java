public static void insertSort(int[] arr){
    int insertVal = 0;
    int insertIndex = 0;
    for(int i = 1; i < arr.length; i++){
        insertVal = arr[i];
        insertIndex = i - 1;

        // 不越界、待插入的数没有找到插入位置
        while(insertIndex >= 0 && insertVal < arr[insertIndex]){
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex --;
        }
        // 当退出while循环时，说明插入的位置找到

        // 判断是否需要赋值
        if(insertIndex + 1 != i)
            arr[insertIndex + 1] = insertVal;
        // System.out.println("第"+i+"轮插入后");
        // System.out.println(Arrays.toString(arr));
    }
}