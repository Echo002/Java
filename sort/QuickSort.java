import java.util.Arrays;

public class QuickSort{
    public static void main(String[] args){
        int[] arr = {-9, 78, 0, 23, -567, 70};

        quickSort(arr, 0, arr.length - 1);
        System.out.println("arr" + Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int left, int right){
        int l = left;
        int r = right;
        int pivot = arr[(left + right) / 2];
        int temp = 0;

        // while循环的目的是让比pivot小的放到左边，大的放到右边
        while(l < r){
            // 在左边一直找，找到大于等于pivot值才退出 
            while(arr[l] < pivot){
                l++;
            }

            while(arr[r] < pivot){
                r--;
            }
            // 说明pivot左右的值，已经按照左边全部是小于等于pivot的值
            // 右边全部是大于等于pivot的值
            if(l >= r){
                break;
            }
            // 交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            // 交换完成后发现arr[l] == pivot r--(前移)
            if(arr[l] == pivot){
                r -= 1;
            }
            // 交换完成后发现arr[r] == pivot l++(前移)
            if(arr[r] == pivot){
                l += 1;
            }
        }
    }
}