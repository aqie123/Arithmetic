package myMVC.model;

public class Board {

    public static char EMPTY = '.';
    private int N, M;
    private char[][] data;

    private Board preBoard = null;
    private String swapString = "";

    public Board(String[] lines){
        if(lines == null) {
            throw new IllegalArgumentException("lines " +
                    "cannot be null in Board constructor.");
        }

        N = lines.length;
        if(N == 0) {
            throw new IllegalArgumentException("lines cannot be " +
                    "empty in Board constructor.");
        }

        M = lines[0].length();

        data = new char[N][M];
        for(int i = 0 ; i < N ; i ++){
            if(lines[i].length() != M) {
                throw new IllegalArgumentException("All " +
                        "lines' length must be same in Board constructor.");
            }
            for(int j = 0 ; j < M ; j ++) {
                data[i][j] = lines[i].charAt(j);
            }
        }
    }

    /**
     *
     * @param board
     * @param preBoard      参数和board 相同，是通过一次消除得来的
     * @param swapString    当前盘面经过如何操作得来的
     */
    public Board(Board board,Board preBoard,String swapString){
        if(board == null){
            throw new IllegalArgumentException("board " +
                    "can not be null in Board constructor!");
        }
        this.N = board.N;
        this.M = board.M;
        this.data = new char[N][M];
        for(int i = 0;i < N;i++){
            for(int j = 0;j < M;j++){
                this.data[i][j] = board.data[i][j];
            }
        }
        this.preBoard = preBoard;
        this.swapString = swapString;
    }

    public Board(Board board){
        this(board,null,"");
    }

    public int getN(){ return N; }
    public int getM(){ return M; }

    public char getData(int x,int y){
        if(!inArea(x,y)){
            throw new IllegalArgumentException("x, y are out of index in getData!");
        }
        return data[x][y];
    }

    public boolean inArea(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public void print(){
        for(int i = 0 ; i < N ; i ++)
            System.out.println(String.valueOf(data[i]));
    }

    // 判断当前盘面是否都是EMPTY
    public boolean isWin(){
        for(int i = 0;i < N;i++){
            for(int j = 0;j < M;j++){
                if(data[i][j] != EMPTY){
                    return false;
                }
            }
        }
        printSwapInfo();
        return true;
    }

    public void swap(int x1,int y1,int x2,int y2){
        if(!inArea(x1, y1) || !inArea(x2, y2)){
            throw new IllegalArgumentException("x, y are out of index in swap!");
        }
        char t = data[x1][y1];
        data[x1][y1] = data[x2][y2];
        data[x2][y2] = t;
    }

    /** 先掉落后消除
     * 处理箱子掉落 drop
     * 处理箱子消除 match
     */
    public void run(){
        do {
            drop();
        }while(match());
    }

    // 右 下
    private static int d[][] = {{0,1},{1,0}};

    // 先标记，再消除，
    private boolean match(){
        // 是否消除过
        boolean isMatched = false;

        boolean tag[][] = new boolean[N][M];
        for(int x = 0;x < N;x++){
            for(int y = 0;y < M;y++){
                if(data[x][y] != EMPTY){
                    for(int i = 0;i < 2;i++){
                        int newX1 = x + d[i][0];
                        int newY1 = y + d[i][1];
                        int newX2 = newX1 + d[i][0];
                        int newY2 = newY1 + d[i][1];
                        if(inArea(newX1, newY1) && inArea(newX2, newY2) &&
                                data[x][y] == data[newX1][newY1] &&
                                data[x][y] == data[newX2][newY2]){
                            tag[x][y] = true;
                            tag[newX1][newY1] = true;
                            tag[newX2][newY2] = true;
                            isMatched = true;
                        }
                    }
                }
            }
        }
        for(int x = 0;x < N;x++){
            for(int y = 0;y < M;y++){
                if(tag[x][y]){
                    data[x][y] = EMPTY;
                }
            }
        }
        return isMatched;
    }

    private void drop(){
        for(int j = 0;j < M;j++){   // 对每一列进行扫描
            int cur = N - 1;
            for(int i = N - 1;i >= 0;i--){
                if(data[i][j] != EMPTY){
                    data[cur][j] = data[i][j];
                    cur--;
                }
            }
            for(;cur >= 0;cur--){
                data[cur][j] = EMPTY;
            }
        }
    }

    /**
     * 打印盘面信息  线性递归
     */
    private void printSwapInfo(){
        if(preBoard != null){
            preBoard.printSwapInfo();
        }
        System.out.println(swapString);
    }
}
