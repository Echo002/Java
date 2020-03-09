class MiGong{
    public static void main(String[] args){
        //创建二维数组模拟迷宫，1表示墙
        int[][] map = new int[8][7];
        for(int i = 0; i < 7; i++){
            map[0][i] = 1;
            map[7][i] = 1;    
        }
        for(int i = 0; i < 8; i++){
            map[i][0] = 1;
            map[i][6] = 1;    
        }
        map[3][1] = 1;
        map[3][2] = 1;
        
        // 测试函数
        setWay(map, 1, 1);
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 7; j++)
                System.out.printf(map[i][j] + " ");
            System.out.println();
        }
    }

    // 使用递归回溯来给小球找路
    // i,j表示小球的位置， map[i][j]为0表示没有走过，2表示通路可以走 3表示该位置已经走过，但是走不通
    // 走迷宫的策略 下-右-上-左，如果该点走不通，则回溯
    public static boolean setWay(int[][] map, int i, int j){
        if(map[6][5] == 2)
            return true;
        else{
            if(map[i][j] == 0){
                // 按照策略
                map[i][j] = 2;
                if(setWay(map, i + 1, j))
                    return true;
                else if(setWay(map, i, j + 1))
                        return true;
                    else if(setWay(map, i - 1, j))
                            return true;
                        else if(setWay(map, i, j - 1))
                                return true;
                            else{
                                map[i][j] = 3;
                                return false;
                            }                   
            } else { // map = 1, 2, 3
                return false;
            }
        }
    }
}

