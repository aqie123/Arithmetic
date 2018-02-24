package myMVC.model;

public class MazeData {
    public static final char ROAD = ' ';
    public static final char WALL = '#';

    // 指定迷宫大小 (N 和M 一定是奇数)
    private int N,M;
    public MazeData(int N, int M){
        this.N = N;
        this.M = M;
    }
}
