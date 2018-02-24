package maze.processMaze;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class MazeData {
    public static final char ROAD = ' ';
    public static final char WALL = '#';

    // 入口和出口横纵坐标
    private int entranceX, entranceY;
    private int exitX, exitY;
    // 已经访问过的路径
    public boolean[][] path;
    // 点是否被访问过,拿到数据后，开空间  go 遍历时候赋值
    public boolean[][] visited;

    // 是否是出口
    public boolean[][] isExit;
    private int N,M;
    private char[][] maze;

    public MazeData(String filename){
        if(filename == null){
            throw new IllegalArgumentException("Filename can not be null!");
        }
        Scanner scanner = null;
        try{
            File file = new File(filename);
            if(!file.exists()){
                throw new IllegalArgumentException("File " + filename + " doesn't exist");
            }
            FileInputStream fis = new FileInputStream(file);
            scanner = new Scanner(new BufferedInputStream(fis),"UTF-8");

            // 读取第一行
            String nmline = scanner.nextLine();
            String[] nm = nmline.trim().split("\\s+");

            N = Integer.parseInt(nm[0]);
            M = Integer.parseInt(nm[1]);

            // 读取后续的n行
            maze = new char[N][M];
            visited = new boolean[N][M];
            path = new boolean[N][M];

            for(int i = 0;i<N;i++){
                String line = scanner.nextLine();

                // 每行保证有M个字符
                if(line.length() != M){
                    throw new IllegalArgumentException(" Maze file "+filename+" is invalid");
                }
                for(int j = 0;j<M;j++){
                    maze[i][j] = line.charAt(j);    // 返回指定位置的字符
                    visited[i][j] = false;          // 显式赋值
                    isExit = new boolean[i][j];     // 初始化是否是出口
                }
            }
        }catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            if(scanner != null){
                scanner.close();
            }
        }
        // 初始化入口和出口
        entranceX = 1;
        entranceY = 0;
        exitX = N - 2;
        exitY = M - 1;
    }

    public int getN() {
        return N;
    }

    public int getM() {
        return M;
    }

    public int getEntranceX() {
        return entranceX;
    }

    public int getEntranceY() {
        return entranceY;
    }

    public int getExitX() {
        return exitX;
    }


    public int getExitY() {
        return exitY;
    }

    // 判断是否在地图里面
    public char getMaze(int i, int j){
        if(!inArea(i,j)) {
            throw new IllegalArgumentException("i or j is out of index in getMaze!");
        }
        return maze[i][j];
    }

    // 判断是否找到出口
    public boolean isGetEntrance(int i,int j){
        return i == exitX && j == exitY;
    }

    // 判断坐标是否在迷宫范围里面
    public boolean inArea(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    public void print(){
        System.out.println(N + " " + M);
        for(int i = 0 ; i < N ; i ++){
            for(int j = 0 ; j < M ; j ++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }
}
