public class DoubleLinkedListDemo {
    public static void main(String[] args){
        System.out.println("双向链表测试");

        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");

        DoubleLinkedList doublelinkedlist = new DoubleLinkedList();
        // 添加
        doublelinkedlist.addByOrder(hero3);
        doublelinkedlist.addByOrder(hero1);
        doublelinkedlist.addByOrder(hero2);
        doublelinkedlist.addByOrder(hero4);
        doublelinkedlist.list();
        System.out.println();

        //修改
        // HeroNode2 newHeroNode = new HeroNode2(4, "公孙胜", "入云龙");
        // doublelinkedlist.update(newHeroNode);
        // doublelinkedlist.list();
        // System.out.println();

        //删除
        // doublelinkedlist.delete(3);
        // doublelinkedlist.list();
        // System.out.println();
    }
}

class DoubleLinkedList{
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead(){
        return head;
    }

    // 遍历（和单向链表一样）
    public void list(){
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.next;
        while(true){
            if(temp ==null)
                break;
            System.out.println(temp);
            temp = temp.next;
        }
    }

    // 添加
    public void add(HeroNode2 node){
        // 无序时，找到链表最后节点
        HeroNode2 temp = head;
        while(true){
            if(temp.next == null){
                break;
            }
            temp = temp.next;
        }
        temp.next = node;
        node.next = null;
        node.pre = temp;
    }

    //按序添加
    public void addByOrder(HeroNode2 heroNode){
        HeroNode2 temp = head;
        boolean flag = false;   //添加的编号是否存在
        while(true){
            if(temp.next == null){
                break;
            }
            if(temp.next.no > heroNode.no){
                break;
            }else if(temp.next.no == heroNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag == true){
            System.out.printf("待插入的英雄 %d 已经存在\n", heroNode.no);
        }else{
            if(temp.next != null){
                heroNode.next = temp.next;
                temp.next.pre = heroNode;
            }
            heroNode.pre = temp;
            temp.next = heroNode;    
            

                
        }
    }

    // 修改（和单向链表一样）
    public void update(HeroNode2 newHeroNode){
        //判断是否为空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head;
        boolean flag = false;

        while(true){
            if(temp == null){
                break;
            }
            if(temp.no == newHeroNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }

        if(flag == true){
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        }else{
            System.out.printf("没有找到编号为%d的节点", newHeroNode.no);
        }
    }

    // 删除
    public void delete(int no){
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.next;
        boolean flag = false;
        while(true){
            if(temp == null){
                break;
            }
            if(temp.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){ //找到了待删除的节点
            temp.pre.next = temp.next;
            // 下面代码要考虑到最后一节点
            if(temp.next != null)
                temp.next.pre = temp.pre;
        }else{
            System.out.println("要删除的节点不存在！");
            return;
        }
    }
}

class HeroNode2{
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String nickname){
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString(){
        return "HeroNode [no=" + no + ", name=" + name + ", nickname=" + nickname + "]";
    }


}