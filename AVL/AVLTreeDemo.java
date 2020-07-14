public class AVLTreeDemo {
    public static void main(String[] args) {
        // int[] arr = {4, 3, 6, 5, 7, 8};
        int[] arr = {10, 12, 7, 6, 8, 9};
        AVLTree avltree = new AVLTree();
        // 添加结点
        for(int i = 0; i < arr.length; i++){
            avltree.add(new Node(arr[i]));
        }
        System.out.println("中序遍历");
        avltree.infixOrder();
        System.out.println("树的根节点为：" + avltree.getRoot());
        System.out.println("树的高度为：" + avltree.getRoot().height());
        System.out.println("左子树的高度为：" + avltree.getRoot().leftHeight());
        System.out.println("右子树的高度为：" + avltree.getRoot().rightHeight());
    }
}

// 创建AVLTree 与BinarySortTree相同
class AVLTree{
    private Node root;
    public Node getRoot(){
        return root;
    }

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


    // 返回左子树的高度
    public int leftHeight(){
        if(left == null){
            return 0;
        } else {
            return left.height();
        }
    }
    // 返回右子树的高度
    public int rightHeight(){
        if(right == null){
            return 0;
        } else {
            return right.height();
        }
    }

    // 返回当前结点为根节点的树的高度
    public int height(){
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    // 左旋转
    private void leftRotate(){
        // 1. 创建新的结点，值等于当前根节点的值
        Node newNode = new Node(value);
        // 2. 把新的结点的左子树设置成当前结点的左子树
        newNode.left = left;
        // 3. 把新的结点的右子树设置成当前结点右子树的左子树
        newNode.right = right.left;
        // 4. 把当前结点的值替换成右子结点的值
        value = right.value;
        // 5.  把当前结点的右子树设置成当前结点右子树的右子树
        right = right.right;
        // 6. 把当前结点的左子树设置成新的结点
        left = newNode;
    }

    // 右旋转
    private void rightRotate(){
        // 1. 创建新的结点，值等于当前根节点的值
        Node newNode = new Node(value);
        // 2. 把新的结点的右子树设置成当前结点的右子树
        newNode.right = right;
        // 3. 把新的结点的左子树设置成当前结点左子树的右子树
        newNode.left = left.right;
        // 4. 把当前结点的值替换成左子结点的值
        value = left.value;
        // 5.  把当前结点的右子树设置成当前结点左子树的左子树
        right = left.left;
        // 6. 把当前结点的左子树设置成新的结点
        left = newNode;
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

        // 当添加完一个结点后，如果右子树的高度 - 左子树的高度 > 1 就左旋转
        if(rightHeight() - leftHeight() > 1){
            // 如果它的右子树的左子树高度大于其右子树的高度
            if(right != null && right.rightHeight() < right.leftHeight()){
                // 先对右子树进行右旋转
                right.rightRotate();
                leftRotate();
            } else {
                leftRotate();
            }
            return ;
        }

        // 当添加完一个结点后，如果左子树的高度 - 右子树的高度 > 1 就右旋转
        if(leftHeight() - rightHeight() > 1){
            // 如果它的左子树的右子树高度大于其左子树的高度
            if(left != null && left.rightHeight() > left.leftHeight()){
                // 先要对左子树进行左旋转
                left.leftRotate();
                rightRotate();
            } else {
                rightRotate();
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