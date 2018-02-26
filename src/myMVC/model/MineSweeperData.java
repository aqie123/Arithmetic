package myMVC.model;

/**
 * 雷区数据
 */
public class MineSweeperData {
    public static final String flagImageURL = "src/resources/flag.png";
    public static final String mineImageURL = "src/resources/mine.png";
    public static final String blockImageURL = "src/resources/block.png";
    public static String numberImageURL(int num){
        if(num < 0 || num >= 8)
            throw new IllegalArgumentException("No such a number image!");
        return "src/resources/" + num + ".png";
    }

    private int N, M;
    private boolean[][] mines;
    // 最初每个格子是否被打开
    public boolean[][] open;
    //
    public boolean[][] flags;
    private int[][] numbers;

    public MineSweeperData(int N,int M,int mineNumber){
        if(N <= 0 || M <= 0) {
            throw new IllegalArgumentException("Mine sweeper size is invalid!");
        }
        if(mineNumber < 0 || mineNumber > N*M) {
            throw new IllegalArgumentException("Mine number is larger than " +
                    "the size of mine sweeper board!");
        }
        this.N = N;
        this.M = M;
        // 开辟空间
        mines = new boolean[N][M];
        open = new boolean[N][M];
        flags = new boolean[N][M];
        numbers = new int[N][M];
        for(int i = 0;i < N;i++){
            for(int j = 0;j < M;j++){
                mines[i][j] = false;
                open[i][j] = false;
                flags[i][j] = false;
                numbers[i][j] = 0;
            }
        }
        generateMines(mineNumber,true);
        calculateNumbers();
    }

    public int getM() {
        return M;
    }

    public int getN() {
        return N;
    }

    // 是否是雷区
    public boolean isMine(int x, int y){
        if(!inArea(x, y))
            throw new IllegalArgumentException("Out of index in isMine function!");
        return mines[x][y];
    }

    public int getNumber(int x,int y){
        if(!inArea(x, y)){
            throw new IllegalArgumentException("Out of index in getNumber function!");
        }
        return numbers[x][y];
    }

    public boolean inArea(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    /**
     * 在 N * M 区域生成 mineNumber 雷
     * while 循环保证 不会有区域重复埋雷，但是
     * 会影响执行时间
     */
    private void generateMines(int mineNumber){
        for(int i = 0; i < mineNumber;i++){
            while (true) {
                int x = (int) (Math.random() * N);
                int y = (int) (Math.random() * M);
                if (!mines[x][y]) {       // 没有雷才生成雷
                    mines[x][y] = true;
                    break;      // 跳出 while循环
                }
            }

        }
    }

    /**
     * 等概率的洗牌算法
     * (Fisher-Yates-Knuth算法)
     * 先将雷均匀排布在前面
     * 然后去打乱牌面
     */
    private void generateMines(int mineNumber,boolean flag){
        // 实现一维坐标向二维坐标转换
        for (int i = 0; i < mineNumber;i++){
            int x = i/M;
            int y = i%M;
            mines[x][y] = true;
        }
        int swapTime = N * M;
        // 实现洗牌程序
        for(int i = swapTime - 1;i >= 0 ;i--){
            int x1 = i/M;
            int y1 = i%M;

            int x2 = (int)(Math.random() * N);
            int y2 = (int)(Math.random() * M);

            int randNumber = (int)(Math.random()*(i+1));
            // 转换成二维坐标
            int randX = randNumber/M;
            int randY = randNumber%M;
            swap(x1,y1,randX,randY);
        }
    }

    private void swap(int x1, int y1, int x2, int y2){
        boolean t = mines[x1][y1];
        mines[x1][y1] = mines[x2][y2];
        mines[x2][y2] = t;
    }

    /**
     * 根据刚刚生成的雷的信息，逐一计算每个格子雷的个数
     */
    private void calculateNumbers(){
        for(int i = 0 ; i < N ; i ++) {
            for (int j = 0; j < M; j++) {
                // 如果点开雷，游戏结束
                if (mines[i][j]) {
                    numbers[i][j] = -1;
                }
                numbers[i][j] = 0;
                // 以i,j为中心坐标的3*3矩阵
                for (int ii = i - 1; ii <= i + 1; ii++) {
                    for (int jj = j - 1; jj <= j + 1; jj++) {
                        // 在雷区有效范围,并且里面有雷
                        if (inArea(ii, jj) && isMine(ii, jj)) {
                            numbers[i][j]++;
                        }
                    }
                }
            }
        }
    }
}
