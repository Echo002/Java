import java.util.Scanner;

public class HashTableDemo{
    public static void main(String[] args){
        HashTab hashTab =  new HashTab(7);

        String key = "";
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("add:添加元素");
            System.out.println("list:遍历元素");
            System.out.println("find:查找元素");
            System.out.println("delete:删除元素");
            System.out.println("exit:退出程序");
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
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    hashTab.findEmpByID(id);
                    break;
                case "delete":
                    System.out.println("请输入要删除的id");
                    id = scanner.nextInt();
                    hashTab.delEmpByID(id);
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
        // 不要忘了分别初始化每一个链表
        for(int i = 0; i < size; i++){
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    // 根据输入的id查找元素
    public void findEmpByID(int id){
        // 使用散列函数
        int empLinkedListNo = hashFun(id); 
        Emp emp = empLinkedListArray[empLinkedListNo].findEmpByID(id);
        if(emp != null){
            System.out.printf("在第%d条链表中找到雇员id = %d\n", (empLinkedListNo + 1), id);
        }else{
            System.out.println("在哈希表中没有找到该雇员");
        }
    }

    // 根据id删除元素
    public void delEmpByID(int id){
        // 使用散列函数
        int empLinkedListNo = hashFun(id);
        boolean result = empLinkedListArray[empLinkedListNo].delEmpByID(id);
        if(result == true){
            System.out.printf("在第%d条链表中找到雇员id = %d\n", (empLinkedListNo + 1), id);
        }else{
            System.out.println("在哈希表中没有找打该雇员");
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
            return;
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

    // 根据ID查找元素
    public Emp findEmpByID(int id){
        // 判断链表是否为空
        if(head == null){
            System.out.println("链表为空");
            return null;
        }

        Emp curEmp = head;
        while(true){
            if(curEmp.id == id){
                break;
                // 此时curEmp已经指向了需要查找的雇员
            }
            if(curEmp.next == null){
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;
        }

        return curEmp;
    }

    // 根据id删除元素
    public boolean delEmpByID(int id){
        // 判断链表是否为空
        if(head == null){
            System.out.println("链表为空");
            return false;
        }
        if(head.id == id){
            head = head.next;
            return true;
        }
        Emp preEmp = head;
        Emp curEmp = preEmp.next;
        while(curEmp != null){
            if(curEmp.id == id){
                preEmp.next = curEmp.next;
                return true;
            }
            preEmp = preEmp.next;
            curEmp = curEmp.next;
        }
        return false;
    }

}