import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.lang.Integer;

public class HuffmanCode {
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        // System.out.println(contentByte.length); 40

        List<Node> nodes = getNodes(contentBytes);
        System.out.println(nodes);

        // 测试——创建二叉树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        System.out.println("前序遍历");
        huffmanTreeRoot.preOrder();

        // 测试——创建对应的Huffman编码
        // getCodes(huffmanTreeRoot, "", stringBuilder);
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        System.out.println("HuffmanCodeList:" + huffmanCodes);

        // 测试
        byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodes);
        System.out.println("huffmanCodeBytes:" + huffmanCodeBytes);
    }

    // 将字符数组通过Huffman编码表编码之后的Huffman编码
    /*
    bytes: 原始字符串对应的byte[]
    huffmanCodes: 生成的huffman编码map
    */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes){
        // 1. 利用huffmanCodes将bytes转成Huffman编码对应的字符串
        StringBuilder stringbuilder = new StringBuilder();
        // 遍历bytes数组
        for(byte b:bytes){
            stringBuilder.append(huffmanCodes.get(b));
        }
        // 统计返回的Huffman编码长度
        int len;
        if(stringBuilder.length() % 8 == 0){
            len = stringbuilder.length() / 8;
        }else{
            len = stringbuilder.length() / 8 + 1; 
        }
        // int len = (stringbuilder.length() + 7) / 8;

        // 创建存储压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;
        for(int i = 0; i < stringbuilder.length(); i += 8){ // 每8位对应一个byte
            String strByte;
            if(i+8 > stringbuilder.length()){
                strByte = stringbuilder.substring(i);
            }else{
                strByte = stringbuilder.substring(i, i + 8);
            }
            // 将strByte转化成一个byte放入
            huffmanCodeBytes[index] = (byte)Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }

    // 生成Huffman树对应的Huffman编码
    /*
    1. 将Huffman编码表存放在Map<Byte, String>形式 ASCII->编码
    2. 生成编码表时，需要拼接路径，定义一个StringBuilder存储叶子节点的路径
     */
    static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();
    static StringBuilder stringBuilder = new StringBuilder();

    // 为了调用方便我们重载getCodes
    private static Map<Byte, String> getCodes(Node root){
        if(root == null){
            return null;
        }
        // 处理root
        getCodes(root.left, "0", stringBuilder);
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodes;
    }

    // 将传入的node节点的所有叶子节点的Huffman编码得到并放入到HuffmanCodes集合中
    // node为传入节点 code为路径(左0右1)
    private static void getCodes(Node node, String code, StringBuilder stringBuilder){
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        // 将code加入到stringBuilder2中
        stringBuilder2.append(code);
        if(node != null){       // 如果node == null不处理
            // 判断当前节点是否为叶子节点
            if(node.data == null){  // 非叶子节点
                // 递归处理
                getCodes(node.left, "0", stringBuilder2);
                getCodes(node.right, "1", stringBuilder2);
            }else{                  // 叶子节点
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }


    // 前序遍历
    private static void preOrder(Node root){
        if(root != null){
            root.preOrder();
        }else{
            System.out.println("Huffman树为空");
        }
    }

    // 接收字节数组，返回List
    private static List<Node> getNodes(byte[] bytes){
        // 1 创建一个ArrayList
        ArrayList<Node> nodes = new ArrayList<Node>();
        // 遍历bytes，统计每一个byte出现的次数 map[key, value]
        Map<Byte,Integer> counts = new HashMap<>();
        for(byte b:bytes){
            Integer count = counts.get(b);
            // map没有字符数据
            if(count == null){
                counts.put(b, 1);
            }else{
                counts.put(b, count + 1);
            }
        }

        // 键值对转换成Node对象并加入到Nodes集合
        for(Map.Entry<Byte, Integer> entry: counts.entrySet()){
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    public static Node createHuffmanTree(List<Node> nodes){
        while(nodes.size() > 1){
            // 排序(从小到大)
            Collections.sort(nodes);
            // 取出第一颗最小的二叉树
            Node leftNode = nodes.get(0);
            // 取出第二颗最小的二叉树
            Node rightNode = nodes.get(1);
            // 创建一颗新的二叉树，根节点没有data只有权值
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;

            // 将已经处理的二叉树移除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            // 将新的二叉树加入
            nodes.add(parent);
        }
        // 返回最后的节点
        return nodes.get(0);
    }
}

// 节点
class Node implements Comparable<Node>{
    Byte data;      // 数据本身
    int weight;     // 权值
    Node left;
    Node right;

    public Node(Byte data, int weight){
        this.data = data;
        this.weight = weight;
    }
    @Override
    public int compareTo(Node o){
        // 从小到大排序
        return this.weight - o.weight;
    }

    public String toString(){
        return "Node [data = " + data + ",weight =" + weight + "]";
    }

    // 前序遍历
    public void preOrder(){
        System.out.println(this);
        if(this.left != null){
            this.left.preOrder();
        }
        if(this.right != null){
            this.right.preOrder();
        }
    }
}