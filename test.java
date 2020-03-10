// 冒泡排序
int temp = 0;
for(int i=0; i<arr.length-1; i++){
    for(int j=0;j<arr.length-1-i; j++){
        if(arr[j] > arr[j+1]){
            temp = arr[j];
            arr[j] = arr[j+1];
            arr[j+1] = temp;
        }
    }
    System.out.println("第"+(i+1)+"趟排序后的数组：");
    System.out.println(Arrays.toString(arr));
}

// 冒泡排序(优化)
int temp = 0;
boolean flag = false;
for(int i=0; i<arr.length-1; i++){
    for(int j=0;j<arr.length-1-i; j++){
        if(arr[j] > arr[j+1]){
            flag = true;
            temp = arr[j];
            arr[j] = arr[j+1];
            arr[j+1] = temp;
        }
    }
    System.out.println("第"+(i+1)+"趟排序后的数组：");
    System.out.println(Arrays.toString(arr));
    if(!flag){ // 没有进行任何交换
        break;
    }else{
        flag = false;
    }
}