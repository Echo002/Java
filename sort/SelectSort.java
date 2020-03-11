import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;

class SelectSort{
    public static void main(String[] args){
        // int[] arr = {101, 34, 119, 1};
        // System.out.printf("原数组为%s\n", Arrays.toString(arr));
        // selectSort(arr);

        // 选择排序速度测试
        int[] arr = new int[80000];
        for(int i=0; i<80000; i++){
            arr[i] = (int)(Math.random() * 80000);
        }

        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);

        selectSort(arr);

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序后的时间是=" + date2Str);
        
        // System.out.println("排序后");
        // System.out.println(Arrays.toString(arr));
    }

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
}