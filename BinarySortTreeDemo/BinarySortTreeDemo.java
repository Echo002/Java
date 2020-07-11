public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        // 循环添加结点到二叉排序树
        for(int i = 0; i < arr.length; i++){
            binarySortTree.add(new Node(arr[i]));
        }

        // 中序
        System.out.println("中序遍历二叉树");
        binarySortTree.infixOrder();

        // 测试删除叶子结点
        binarySortTree.delNode(10);
        System.out.println("删除结点后");
        binarySortTree.infixOrder();
    }
}

// 创建二叉排序树
class BinarySortTree{
    private Node root;

    // 查找结点
    public Node search(int value){
        if(root == null){
            return null;
        } else {
            return root.search(value);
        }
    }

    // 查找父节点
    public Node searchParent(int value){
        if(root == null){
            return null;
        } else {
            return root.searchParent(value);
        }
    }
    
    /*
    * 方法：
    * 删除以node为根节点的二叉排序树的最小结点的值
    */
    public int delRightTreeMin(Node node) {
        Node target = node;
        // 循环查找左节点
        while(target.left != null){
            target = target.left;
        }
        delNode(target.value);
        return target.value;
    }

    // 删除结点
    public void delNode(int value){
        if(root == null){
            return;
        } else {
            Node targetNode = search(value);
            if(targetNode == null){
                return;
            }
            if(root.left == null && root.right == null){
                root = null;
                return;
            }

            Node parent = searchParent(value);
            // 1. 如果删除叶子结点(先左再右)
            if(targetNode.left == null && targetNode.right == null){
                if(parent.left != null && parent.left.value == value){
                    parent.left = null;
                } else if(parent.right != null && parent.right.value == value){
                    parent.right = null;
                }
            } else if(targetNode.left != null && targetNode.right != null){
                // 3. 删除有两颗子树的结点
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;
            } else {
                // 2. 删除只有一颗子树的结点
                if(targetNode.left != null){
                    if(parent != null){
                        if(parent.left.value == value){
                            parent.left = targetNode.left;
                        } else {
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                    
                } else {
                    // targetNode是parent的左子节点
                    if(parent != null){
                        if(parent.left.value == value){
                            parent.left = targetNode.right;
                        } else {    // targetNode是parent的右子节点
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                    
                }
            }
            
        }
    }

    public void add(Node node){
        if(root == null){
            root = node;
        } else {
            root.add(node);
        }
    }
    // 中序遍历
    public void infixOrder(){
        if(root != null){
            root.infixOrder();
        } else {
            System.out.println("二叉排序树null");
        }
    }
}

// 创建结点
class Node{
    int value;
    Node left;
    Node right;
    public Node(int value){
        super();
        this.value = value;
    }

    // 查找结点
    public Node search(int value){
        if(value == this.value){
            return this;
        } else if(value < this.value){
            if(this.left == null){
                return null;
            }
            return this.left.search(value);
        } else {
            if(this.right == null){
                return null;
                }
            return this.right.search(value);
        }
    }

    // 查找结点的父结点
    public Node searchParent(int value){
        if((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)){
            return this;
        } else {
            if(value < this.value && this.left != null){
                return this.left.searchParent(value);
            } else if(value >= this.value && this.right != null){
                return this.right.searchParent(value);
            } else {
                return null;
            }
        }
    }

    @Override
    public String toString(){
        return "Node [vlaue" + value + "]";
    }

    // 添加结点
    // 递归的形式添加，注意满足二叉树排序树的要求
    public void add(Node node){
        if(node == null){
            return ;
        }

        // 判断结点的值和当前子树根结点的关系
        if(node.value < this.value)
        {
            // 当前结点左子结点
            if(this.left == null){
                this.left = node;
            } else {
                this.left.add(node);
            }
        }else{
            if(this.right == null){
                this.right = node;
            } else{
                this.right.add(node);
            }
        }
    }

    // 中序遍历
    public void infixOrder(){
        if(this.left != null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if(this.right != null){
            this.right.infixOrder();
        }
    }
}
