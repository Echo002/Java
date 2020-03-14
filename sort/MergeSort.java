import java.util.Arrays;

public class MergeSort{
    public static void main(String[] args){
        int arr[] = {8, 4, 5, 6, 1, 3, 6, 2};
        int temp[] = new int[arr.length];

        mergeSort(arr, 0, arr.length - 1, temp);
        System.out.println("归并排序后="+Arrays.toString(arr));
    }

    // 拆分
    public static void mergeSort(int[] arr, int left, int right, int[] temp){
        if(left < right){
            int mid = (left + right) / 2;
            // 向左递归分解
            mergeSort(arr, left, mid, temp);
            // 向右递归分解
            mergeSort(arr, mid+1, right, temp);
            // 到合并
            merge(arr, left, mid, right, temp);
        }
    }
    // 合并
    /*
    arr 排序的原始数组
    left 左边有序序列的初始索引
    mid 中间索引
    right 右边索引
    temp 中转数组
    */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp){
        int i = left;       // 初始化i，左边有序序列的初始索引
        int j = mid + 1;    // 初始化j，右边有序序列的初始索引
        int t = 0;          // 指向temp数组的当前索引

        // 先将左右两边（有序）的数据按照规则填充到temp，直到左右两边全部处理完毕
        while(i <= mid && j<= right){
            if(arr[i] <= arr[j]){
                // 左边小于等于右边时，则进行填充 然后t往后移
                temp[t] = arr[i];
                t++;
                i++;
            }else{
                temp[t] = arr[j];
                t++;
                j++;
            }
        }
        // 把有剩余数据的一边的数据依次填充到temp中
        while(i <= mid){  // 左边剩余
            temp[t] = arr[i];
            t++;
            i++;
        }
        while(j <= right){  // 左边剩余
            temp[t] = arr[j];
            t++;
            j++;
        }

        // 将temp数组的元素拷贝到arr中(并不是每次都拷贝全部)
        t = 0;
        int tempLeft = left;
        while(tempLeft <= right){
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }

    }
}