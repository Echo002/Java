import java.util.Scanner;

public class HashTableDemo{
    public static void main(String[] args){
        HashTab hashTab =  new HashTab(7);

        String key = "";
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("add:添加元素");
            System.out.println("list:遍历元素");
            System.out.println("exit:遍历元素");
            key = scanner.next();
            switch(key){
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入姓名");
                    String name = scanner.next();

                    Emp emp = new Emp(id, name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

class Emp{
    public int id;
    public String name;
    public Emp next;    // 默认为空
    public Emp(int id, String name){
        super();
        this.id = id;
        this.name = name;
    }
}

// 创建哈希表
class HashTab{
    private EmpLinkedList[] empLinkedListArray;
    private int size;

    public HashTab(int size){
        this.size = size;
        empLinkedListArray = new EmpLinkedList[size];
        for(int i = 0; i < size; i++){
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    // 添加元素
    public void add(Emp emp){
        int empLinkedListNo = hashFun(emp.id);
        empLinkedListArray[empLinkedListNo].add(emp);
    }

    // 遍历
    public void list(){
        for(int i = 0; i < size; i++){
            empLinkedListArray[i].list(i);
        }
    }

    // 散列函数
    public int hashFun(int id){
        return id % size;
    }
}

// 创建链表
class EmpLinkedList{
    private Emp head;
    
    // 添加元素
    public void add(Emp emp){
        // 若是添加第一个员工
        if(head == null){
            head = emp;
        }

        Emp curEmp = head;
        while(true){
            if(curEmp.next == null){
                break;
            }
            curEmp = curEmp.next;
        } 
        curEmp.next = emp;
    }

    // 遍历链表
    public void list(int no){
        if(head == null){
            System.out.println("第" + no + "条链表为空");
            return;
        }
        System.out.print("第" + no + "条链表的信息为：");
        Emp curEmp = head;
        while(true){
            System.out.printf("=> id = %d, name = %s", curEmp.id, curEmp.name);
            if(curEmp.next == null){
                break;
            }
            curEmp = curEmp.next;
        }
        System.out.println();
    }

}