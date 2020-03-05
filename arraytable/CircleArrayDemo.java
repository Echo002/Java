import java.util.Scanner;

public class CircleArrayDemo{
    public static void main(String[] args) {
        System.out.println("测试模拟数组环形队列的案例~");
        // 队列的有效数据为数组长度减1
        CircleArray queue = new CircleArray(5);
		char key = ' ';
		Scanner scanner = new Scanner(System.in);
		boolean loop = true;
		while(loop){
			System.out.println("s(show):显示队列");
			System.out.println("e(exit):退出程序");
			System.out.println("a(add):添加数据到队列");
			System.out.println("h(head):查看队列头部数据");
			key = scanner.next().charAt(0);
			switch(key){
				case 's':
					queue.showQueue();
					break;
				case 'a':
					System.out.println("输出一个数");
					int value = scanner.nextInt();
					queue.addQueue(value);
					break;
				case 'g':
					try {
						int result = queue.getQueue();
						System.out.printf("取出的数据是%d\n", result);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				case 'h':
					try {
						int result = queue.headQueue();
						System.out.printf("队列头的数据是%d\n", result);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				case 'e':
					scanner.close();
					loop = false;
					break;
				default:
					break;
			}
		}
		System.out.println("程序退出！");
    }
}

class CircleArray{
    private int maxSize;    //数组的最大容量
    private int front;      //指向队列的第一个元素
    private int rear;       //指向队列最后一个元素的后一个位置
    private int[] arr;      //用于存放数据，模拟队列

    public CircleArray(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
    }

    public boolean isFull(){
        return (rear + 1) % maxSize == front;
    }

    public boolean isEmpty() {
		return rear == front;
    }
    
    public void addQueue(int n) {
		if (isFull()) {
			System.out.println("队列满，不能加入数据~");
			return;
		}
        arr[rear] = n;
        //将rear后移，必须考虑取模
        rear = (rear + 1) % maxSize;
    }
    
    public int getQueue() {
		if (isEmpty()) {
			// 通过抛出异常
			throw new RuntimeException("队列空，不能取数据");
		}
        int result = arr[front]; // front后移
        //考虑取模
        front = (front + 1) % maxSize;
		return result;
    }
    
    public void showQueue() {
		// 遍历
		if (isEmpty()) {
			System.out.println("队列空的，没有数据~~");
			return;
        }
        //思路：从front开始遍历，遍历多少个元素就可以
        for(int i = front; i < (front + (rear + maxSize - front) % maxSize); i++){
            System.out.printf("arr[%d] = %d\n", i % maxSize, arr[i % maxSize]);        
        }
    }
    
    public int headQueue() {
		if (isEmpty()) {
			throw new RuntimeException("队列空的，没有数据~~");
		}
		return arr[front];
	}
}