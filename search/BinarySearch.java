import java.util.ArrayList;
import java.util.List;

public class BinarySearch{
    public static void main(String[] args){
        int arr[] = {1, 8, 10, 89, 1000, 1234};
        int arr2[] = {1, 8, 10, 89, 1000, 1000, 1000, 1000, 1234};

        // int resIndex = binarySearch(arr, 0, arr.length - 1, 1234);
        List<Integer> resIndexList = binarySearch2(arr2, 0, arr2.length - 1, 1000);
        System.out.println(resIndexList);
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

    public static ArrayList<Integer> binarySearch2(int[] arr, int left, int right, int findVal){
        // 当left > right时，说明递归完了整个数组
        if(left > right){
            return new ArrayList<Integer>();
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if(findVal > midVal){
            return binarySearch2(arr, mid + 1, right, findVal);
        }else if(findVal < midVal){
            return binarySearch2(arr, left, mid - 1, findVal);
        }else{
            ArrayList<Integer> resIndexlist = new ArrayList<Integer>();
            int temp = mid - 1;
            while(true){
                if(temp < 0 || arr[temp]!= findVal){
                    break;
                }
                resIndexlist.add(temp);
                temp -= 1;
            }
            resIndexlist.add(mid);
            
            temp = mid + 1;
            while(true){
                if(temp < 0 || arr[temp]!= findVal){
                    break;
                }
                resIndexlist.add(temp);
                temp += 1;
            }
            return resIndexlist;
        }
    }
}