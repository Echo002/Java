package arraytable;

public class Josepfu {
    public static void main(String[] args){
        CircleSingleList circleSingleList = new CircleSingleList();
        circleSingleList.addBoy(5);
        circleSingleList.showBoy();

        circleSingleList.countBoy(1, 2, 5);
    }
}

class CircleSingleList{
    // 创建first节点
    private Boy first = new Boy(-1);

    public void addBoy(int nums){
        if(nums < 1){
            System.out.println("nums的值不正确");
            return;
        }
        Boy curBoy = null;
        for(int i=1; i<=nums; i++){
            //根据编号创建小孩节点
            Boy boy = new Boy(i);
            // 如果是第一个节点
            if(i==1){
                first = boy;
                first.setNext(first);
                curBoy = first;
            }else{
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    } 

    public void showBoy(){
        if(first == null){
            System.out.println("链表为空");
            return;
        }
        Boy curBoy = first;
        while(true){
            System.out.printf("小孩的编号为 %d\n", curBoy.getNo());
            if(curBoy.getNext() == first){
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    public void countBoy(int startNo, int countNum, int nums){
        // 从第几个小孩数数，数几下，最初有多少小孩在圈中
        if(first == null || startNo < 1 || startNo > nums){
            System.out.println("参数输入有误");
        }
        Boy helper = first;
        while (true) {
            if(helper.getNext() == first){
                break;
            }
            helper = helper.getNext();
        }
        for(int j = 0; j < startNo - 1; j++){
            first = first.getNext();
            helper = helper.getNext();
        }
        while(true){
            if(helper == first){
                break;
            }
            for(int j = 0; j < countNum - 1; j++){
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.printf("%d 出圈\n", first.getNo());
            first = first.getNext();
            helper.setNext(first);
    }
    System.out.printf("最后%d\n", first.getNo());
}
}

class Boy{
    private int no;
    private Boy next;
    
    public Boy(int no){
        this.no = no;
    }

    public int getNo(){
        return no;
    }
    public void setNo(int no){
        this.no = no;
    }

    public Boy getNext(){
        return next;
    }
    public void setNext(Boy next){
        this.next = next;
    }
}