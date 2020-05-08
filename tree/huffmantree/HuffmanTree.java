import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {
    public static void main(String[] args) {
        int arr[] = {13, 7, 8, 3, 29, 6, 1};
        createHuffmanTree(arr);
    }

    public static void createHuffmanTree(int[] arr){
        // 第一步，为了操作方便
        // 1.遍历arr数组
        // 2.将arr的每个元素构成成一个Node
        // 3.将Node放入到ArrayList中
        List<Node> nodes = new ArrayList<Node>();
        for(int value : arr){
            nodes.add(new Node(value));
        }

        
        // 排序（从小到大）
        Collections.sort(nodes);
        System.out.println("nodes = " + nodes);

        // 取出根节点权值最小的两棵二叉树
        // (1)取出权值最小的节点(二叉树)
        Node leftNode = nodes.get(0);
        // (2)取出权值第二小的节点(二叉树)
        Node rightNode = nodes.get(1);

        // (3)构建一课新的二叉树
        Node parent = new Node(leftNode.value + rightNode.value);
        parent.left = leftNode;
        parent.right = rightNode;

        // (4)从ArrayList删除处理过的二叉树
        nodes.remove(leftNode);
        nodes.remove(rightNode);

        // (5)将parent加入到nodes
        nodes.add(parent);

        System.out.println("第一次处理后" + nodes);
    }
}

// 创建节点类
// 为了让Node对象支持Collections集合排序，让Node实现接口
class Node implements Comparable<Node>{
    int value;      // 权值
    Node left;      // 指向左子节点
    Node right;     // 指向右子节点

    public Node(int value){
        this.value = value;
    }

    @Override
    public String toString(){
        return "Node [value=" + value + "]";
    }

    @Override
    public int compareTo(Node o){
        // 从小到大进行排序
        return this.value - o.value;
    }
}