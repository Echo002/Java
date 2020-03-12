import java.util.Arrays;
import java.util.Date;
import java.text.SimpleDateFormat;

class InsertSort{
    public static void main(String[] args){
        // int[] arr = {101, 34, 119, 1};
        // 选择排序速度测试
        int[] arr = new int[80000];
        for(int i=0; i<80000; i++){
            arr[i] = (int)(Math.random() * 80000);
        }

        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);

        insertSort(arr);

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序后的时间是=" + date2Str);
    }

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
}