
public class ThreadedBinaryTree {
    public static void main(String[] args) {
        // 测试中序线索二叉树的功能
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");

        // 目前手动创建
        root.setLeftNode(node2);
        root.setRightNode(node3);
        node2.setLeftNode(node4);
        node2.setRightNode(node5);
        node3.setLeftNode(node6);

        // 测试线索化
        ThreadBinaryTree threadBinaryTree = new ThreadBinaryTree();
        threadBinaryTree.setRoot(root);
        threadBinaryTree.threadedNodes();

        // 测试：以10号节点
        // HeroNode leftNode = node5.getLeftNode();
        // System.out.println(leftNode);

        // 当线索化二叉树后，就不能使用原来遍历的方法
        threadBinaryTree.threadList();
    }
}

// 创建节点
class HeroNode{
    private int no;
    private String name;
    private HeroNode left;  // default：null
    private HeroNode right; // default: null

    // 若left/rightType == 0表示指向左/右子树
    // 若left/rightType == 1表示指向前驱节点
    private int leftType;
    private int rightType;

    public int getLeftType(){
        return leftType;
    }
    public int getRightType(){
        return rightType;
    }
    public void setLeftType(int leftType){
        this.leftType = leftType;
    }
    public void setRightType(int rightType){
        this.rightType = rightType;
    }

    public HeroNode(int no, String name){
        super();
        this.no = no;
        this.name = name;
    }

    public int getNo(){
        return no;
    }
    public String getName(){
        return name;
    }
    public HeroNode getLeftNode(){
        return left;
    }
    public HeroNode getRightNode(){
        return right;
    }

    public void setNo(int no){
        this.no = no;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setLeftNode(HeroNode left){
        this.left = left;
    }
    public void setRightNode(HeroNode right){
        this.right = right;
    }

    @Override
    public String toString(){
        return "HeroNode [no=" + no + ", name=" + name + "]";
    }

    // 前序遍历
    public void preOrder(){
        System.out.println(this); // 先输出父节点
        // 递归向左子树
        if(this.left != null){
            this.left.preOrder();
        }
        // 递归向右子树
        if(this.right != null){
            this.right.preOrder();
        }
    }
    // 中序遍历
    public void infixOrder(){
        // 递归向左子树
        if(this.left != null){
            this.left.infixOrder();
        }
        // 输出父节点
        System.out.println(this);
        if(this.right != null){
            this.right.infixOrder();
        }
    }
    // 后序遍历
    public void postOrder(){
        // 递归向左子树
        if(this.left != null){
            this.left.postOrder();
        }
        if(this.right != null){
            this.right.postOrder();
        }
        // 输出父节点
        System.out.println(this);
    }

    // 前序遍历查找
    public HeroNode preOrderSearch(int no){
        HeroNode resNode = null;
        // 比较当前节点
        if(this.no == no){
            return this;
        }else if(this.left != null){
            resNode = this.left.preOrderSearch(no);
        }
        if(resNode != null){    // 说明左子树找到
            return resNode;
        }
        if(this.right != null){
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }
    // 中序遍历查找
    public HeroNode infixOrderSearch(int no){
        HeroNode resNode = null;
        // 比较当前节点
        if(this.left != null){
            resNode = this.left.preOrderSearch(no);
        }
        if(resNode != null){
            return resNode;
        }
        if(this.no == no){
            return this;
        }
        if(this.right != null){
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }
    // 后序遍历查找
    public HeroNode postOrderSearch(int no){
        HeroNode resNode = null;
        if(this.left != null){
            resNode = this.left.postOrderSearch(no);
        }
        if(resNode != null){
            return resNode;
        }
        if(this.right != null){
            resNode = this.right.postOrderSearch(no);
        }
        if(resNode != null){
            return resNode;
        }
        if(this.no == no){
            return this;
        }
        return resNode;
    }

    // 删除节点
    public void delNode(int no){
        if(this.left != null && this.left.no == no){
            this.left = null;
            return;
        }
        if(this.right != null && this.right.no == no){
            this.right = null;
            return;
        }
        if(this.left != null){
            this.left.delNode(no);
        }
        if(this.right != null){
            this.right.delNode(no);
        }
    }
}

// 实现线索化二叉树
class ThreadBinaryTree{
    private HeroNode root;

    // 为了实现线索化，需要创建给当前节点的前驱结点的指针
    private HeroNode pre = null;

    public void setRoot(HeroNode root){
        this.root = root;
    }
    // 前序遍历
    public void preOrder(){
        if(this.root != null){
            this.root.preOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }
    // 中序遍历
    public void infixOrder(){
        if(this.root != null){
            this.root.infixOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }
    // 前序遍历
    public void postOrder(){
        if(this.root != null){
            this.root.postOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }

    // 前序遍历查找
    public HeroNode preOrderSearch(int no){
        if(root != null){
            return root.preOrderSearch(no);
        }else{
            return null;
        }
    }
    // 中序遍历查找
    public HeroNode infixOrderSearch(int no){
        if(root != null){
            return root.infixOrderSearch(no);
        }else{
            return null;
        }
    }
    // 后序遍历查找
    public HeroNode postOrderSearch(int no){
        if(root != null){
            return root.postOrderSearch(no);
        }else{
            return null;
        }
    }

    // 删除节点
    public void delNode(int no){
        if(root != null){
            if(root.getNo() == no){
                root = null;
            }else{
                // 进行递归删除
                root.delNode(no);
            }
        }else{
            System.out.println("空树~不能删除");
        }
    }

    // 遍历线索化二叉树
    public void threadList(){
        // 定义一个变量，存储当前遍历的节点
        HeroNode node = root;
        while(node != null){
            // 找到leftType == 1 的节点
            while(node.getLeftType() == 0){
                node = node.getLeftNode();
            }
            // 打印当前节点
            System.out.println(node);
            // 如果当前节点右指针指向后继节点，就一直输出
            while(node.getRightType() == 1){
                node = node.getRightNode();
                System.out.println(node);
            }
            // 替换这个遍历的节点
            node = node.getRightNode();
        }
    }

    // 二叉树线索化实现（中序）,node为当前需要线索化的节点
    // 在处理的过程中，需要有node的前驱节点做辅助
    
    // 方法重载
    public void threadedNodes(){
        this.threadedNodes(root);
    }

    public void threadedNodes(HeroNode node){
        if(node == null){
            return;
        }

        // 线索化左子树
        threadedNodes(node.getLeftNode());

        // 线索化当前节点
        // 处理当前节点的前驱节点
        if(node.getLeftNode() == null){
            // 当前节点的左指针指向前驱节点
            node.setLeftNode(pre);
            node.setLeftType(1);
        }
        // 处理当前节点的后继节点
        if(pre != null && pre.getRightNode() == null){
            // 让前驱节点的右指针指向当前节点
            pre.setRightNode(node);
            // 修改前驱节点的右指针类型
            pre.setRightType(1);
        }

        // 每处理一个节点，让当前节点是下一个节点的前驱节点
        
        pre = node;

        // 线索化右子树
        threadedNodes(node.getRightNode());
    }
}