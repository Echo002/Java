public class InsertValueSearch{
    public static void main(String[] args){
        int[] arr = new int[100];
        for(int i = 0; i < 100; i++){
            arr[i] = i + 1;
        }
        
        int index = insertValueSearch(arr, 0, arr.length - 1, 45);
        System.out.println(index);
    }

    public static int insertValueSearch(int[] arr, int left, int right, int findVal){
        if(left > right || findVal < arr[0] || findVal > arr[arr.length - 1]){
            return -1;
        }

        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if(findVal > midVal){           // 说明应该向右插值查找
            return insertValueSearch(arr, mid + 1, right, findVal);
        }else if(findVal < midVal){     //说明应该向左插值查找
            return insertValueSearch(arr, left, mid - 1, findVal);
        }else{
            return mid;
        }
    }
}