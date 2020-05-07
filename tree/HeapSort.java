import java.util.Arrays;

public class HeapSort{
    public static void main(String[] args){
        // 要求将数组升序排列
        int arr[] = {4, 6, 8, 5, 9};
        heapSort(arr);
    }

    // 编写一个堆排序的方法
    public static void heapSort(int arr[]){
        int temp = 0;
        System.out.println("堆排序");
        // 分步完成
        // adjustHeap(arr, 1, arr.length);
        // System.out.println("第一次"+Arrays.toString(arr));

        // adjustHeap(arr, 0, arr.length);
        // System.out.println("第一次"+Arrays.toString(arr));

        // 完整步骤
        // 将无序序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆；
        for(int i = arr.length / 2 - 1; i >= 0; i--){
            adjustHeap(arr, i, arr.length);
        }
        
        for(int j = arr.length - 1; j > 0; j--){
            // 交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }
        System.out.println("数组="+Arrays.toString(arr));
    }

    // 将一个数组（二叉树），调整成一个大顶堆，将以i对应的非叶子节点的树调整成大顶堆
    // i表示非叶子节点在数组中的索引 length表示对多少元素继续调整
    public static void adjustHeap(int arr[], int i, int length){
        int temp = arr[i]; // 先取出当前元素的值，保存在临时变量
        // k指向i的左子节点
        for(int k= i * 2 + 1; k < length; k = k * 2 + 1){
            if(arr[k] < arr[k+1] && k+1 < length){ // 说明左子节点值小于右子节点的值
                k++;    // k指向右子节点
            }
            if(arr[k] > temp){      // 如果子节点大于父节点
                arr[i] = arr[k];    // 把较大的值赋值给当前节点
                i = k;              // i指向k，继续循环比较
            }else{
                break;
            }
        }
        // for循环结束后，我们已经将以i为父节点的树的最大值放在了最顶部（以i为父节点的树）
        arr[i] = temp;              // 将temp值放到调整后的位置
    }
}