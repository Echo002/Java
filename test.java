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