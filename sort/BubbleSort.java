import java.util.Arrays;

class BubbleSort{
    public static void main(String[] args){
        int[] arr = {1, 2, 3, 4, 5, 6};
        bubbleSort(arr);
    }

    public static void bubbleSort(int arr[]){
        // 冒泡排序
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
    }
}