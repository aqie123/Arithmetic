package myMVC.model;

public class Position {
    private int x,y;
    // 记录当前的 position 从哪个位置得来的
    private Position prev;
    public Position(int x, int y ,Position prev){
        this.x = x;
        this.y = y;
        this.prev = prev;
    }

    public Position(int x,int y){
        this(x,y,null);
    }

    public Position getPrev() {
        return prev;
    }

    public int getX(){return x;}
    public int getY(){return y;}
}
