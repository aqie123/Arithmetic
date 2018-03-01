package myMVC.model;

public class CircleData {
    private int startX, startY;
    private int startR;
    private int depth;
    private int step;

    /**
     *
     * @param x 圆心横坐标
     * @param y 圆心纵坐标
     * @param r 圆心最外层半径
     * @param d 递归深度
     * @param step 内部圆和外面圆大小差
     */
    public  CircleData(int x, int y, int r, int d, int step){
        this.startX = x;
        this.startY = y;
        this.startR = r;
        this.depth = d;
        this.step = step;
    }

    public int getStartX(){ return startX; }
    public int getStartY(){ return startY; }
    public int getStartR(){ return startR; }
    public int getDepth(){ return depth; }
    public int getStep(){ return step; }
}
