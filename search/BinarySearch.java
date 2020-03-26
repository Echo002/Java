public class BinarySearch{
    public static void main(String[] args){
        int arr[] = {1, 8, 10, 89, 1000, 1234};

        int resIndex = binarySearch(arr, 0, arr.length - 1, 1234);
        System.out.println(resIndex);
    }

    public static int binarySearch(int[] arr, int left, int right, int findVal){
        // 当left > right时，说明递归完了整个数组
        if(left > right){
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if(findVal > midVal){
            return binarySearch(arr, mid + 1, right, findVal);
        }else if(findVal < midVal){
            return binarySearch(arr, left, mid - 1, findVal);
        }else{
            return mid;
        }
       // return -1;
    }
}