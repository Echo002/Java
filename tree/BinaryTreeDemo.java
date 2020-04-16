public class BinaryTreeDemo{
    public static void main(String[] args){
        // 创建一棵二叉树
        BinaryTree binaryTree = new BinaryTree();
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");
        
        // 手动创建，后面学习递归创建
        root.setLeftNode(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeftNode(node5);
        binaryTree.setRoot(root);
        // 测试遍历
        // System.out.println("前序遍历");
        // binaryTree.preOrder();
        // System.out.println("中序遍历");
        // binaryTree.infixOrder();
        // System.out.println("后序遍历");
        // binaryTree.postOrder();

        // 测试查找
        // System.out.println("前序遍历查找");
        // HeroNode resNode = binaryTree.preOrderSearch(5);
        // if(resNode != null){
        //     System.out.printf("找到了，信息为 no = %d name = %s", resNode.getNo(), resNode.getName());
        // }else{
        //     System.out.printf("没有找到 no = %d 的英雄", 5);
        // }

        // 测试删除
        System.out.println("删除前，前序遍历");
        binaryTree.preOrder();
        binaryTree.delNode(5);
        System.out.println("删除后，前序遍历");
        binaryTree.preOrder();
    }
}

// 定义二叉树
class BinaryTree{
    private HeroNode root;
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
}

class HeroNode{
    private int no;
    private String name;
    private HeroNode left;  // default：null
    private HeroNode right; // default: null
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
    public void setRight(HeroNode right){
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
