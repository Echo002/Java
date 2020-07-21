import java.util.ArrayList;
import java.util.Arrays;

public class Graph{
    private ArrayList<String> vertexList;
    private int[][] edges;  // 存储图对应的邻接矩阵
    private int numOfEdges; // 表示边的数目
    // 定义给数组boolean[], 记录某个结点是否被访问
    private boolean[] isVisited;

    public static void main(String[] args) {
        // 测试图的创建
        int n = 5;
        String Vertexs[] = {"A", "B", "C", "D", "E"};
        // 创建图对象
        Graph graph = new Graph(n);
        for (String vertex: Vertexs){
            graph.insertVertex(vertex);
        }
        // 添加边AB AC BC BD BE
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);

        // 显示邻接矩阵
        graph.showGraph();

        // 测试dfs
        System.out.println("深度遍历");
        graph.dfs();
    }

    // 构造器
    public Graph(int n){
        // 初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges = 0;
        isVisited = new boolean[5];
    }

    // 得到第一个邻接结点的下标 w,如果存在就返回对应的下标， 否则为-1
    public int getFirstNeighbor(int index){
        for(int j = 0; j < vertexList.size(); j++){
            if(edges[index][j] > 0){
                return j;
            }
        }
        return -1;
    }

    // 根据前一个邻接结点的下标来获取下一个邻接结点
    public int getNextNeighbor(int v1, int v2) {
        for(int j = v2 + 1; j < vertexList.size(); j++){
            if(edges[v1][j] > 0){
                return j;
            }
        }
        return -1;
    }

    // 对dfs进行重载
    public void dfs() {
        // 遍历所有的结点进行dfs
        for(int i = 0; i < getNumOfVertex(); i++){
            if(!isVisited[i]){
                dfs(isVisited, i);
            }
        } 
    }

    // 深度优先
    private void dfs(boolean[] isVisited, int i) {
        // 首先我们访问该结点
        System.out.print(getValueByIndex(i) + "->");
        // 将该结点设置为已访问过
        isVisited[i] = true;
        
        // 查找结点i的第一个邻接结点 
        int w = getFirstNeighbor(i);
        while(w != -1){ //说明有邻接结点
            if(!isVisited[w]){
                dfs(isVisited, w);
            }
            w = getNextNeighbor(i, w);
        }

    }

    // 图中常用的方法
    // 返回结点的个数
    public int getNumOfVertex(){
        return vertexList.size();
    }
    
    // 得到边的数目
    public int getNumOfEdges(){
        return numOfEdges;
    }

    // 返回结点i对应的数据
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }

    // 返回V1 V2的权值
    public int getWeight(int v1, int v2){
        return edges[v1][v2];
    }

    // 插入结点
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }

    // 添加边 v[num]表示顶点 weight表示权值
    public void insertEdge(int v1, int v2, int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    // 显示图对应的矩阵
    public void showGraph() {
        for(int[] link : edges){
            System.out.println(Arrays.toString(link));
        }
    }
}