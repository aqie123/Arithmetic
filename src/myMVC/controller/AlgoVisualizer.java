package myMVC.controller;

import maze.processMaze.MazeData;
import myMVC.tools.AlgoVisHelper;
import myMVC.view.AlgoFrame;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class AlgoVisualizer {
    private static int DELAY = 1;

    private MazeData data;

    // 视图层
    private AlgoFrame frame;
    // todo 设置自定义变量
    private static int blockSide = 8;
    // 左，下，右，上
    private static final int d[][] = {{-1,0},{0,1},{1,0},{0,-1}};

    // 堆排序
    public AlgoVisualizer(String mazeFile){
        // todo 初始化数据
        data = new MazeData(mazeFile);
        int screenWidth = data.getM() * blockSide;
        int screenHeight = data.getN() * blockSide;
        // 初始化视图frame
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Traverse the maze (depth-first)", screenWidth, screenHeight);
            // TODO: 根据情况决定是否加入键盘鼠标事件监听器
            frame.addKeyListener(new AlgoKeyListener());
            frame.addMouseListener(new AlgoMouseListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }

    public void run(){
        setData(-1,-1,false);
        if(!go(data.getEntranceX(),data.getEntranceY())){
            System.out.println("The maze has NO solution!");
        }
        setData(-1,-1,false);
    }

    private boolean go(int x,int y){
        if(!data.inArea(x,y)){
            throw new IllegalArgumentException("x,y are out of index in go function!");
        }
        data.visited[x][y] = true;  // 已经访问过
        setData(x,y,true);
        // 已经找到出口
        if(x == data.getExitX() && y == data.getExitY()){
            return true;
        }
        // 对四个方向进行遍历 (从上开始,走顺时针)
        for(int i = 0;i<4;i++){
            int newX = x + d[i][0];
            int newY = y + d[i][1];
            /*  探索新的方向
                1.保证不越界,
                2.并且传入的新坐标是可以走的,
                3.之前没有访问过
             */
            if(data.inArea(newX,newY) &&
                    data.getMaze(newX,newY) == MazeData.ROAD &&
                    !data.visited[newX][newY]){
                // 如果在新坐标找到迷宫的解，返回true
                if(go(newX,newY)){
                    return true;
                }
            }
        }
        // 回溯，将之前失败的访问路径变为白色
        setData(x,y,false);
        return false;
    }

    // 设置要绘制的数据
    private void setData(int x,int y,boolean ispath){
        // 如果在迷宫内，标记为访问过
        if(data.inArea(x,y)){
            data.path[x][y] = ispath;
        }
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }


    // 添加键盘监听事件
    private class AlgoKeyListener extends KeyAdapter{
        @Override
        public void keyReleased(KeyEvent e) {
            // todo 键盘释放事件
        }
    }

    // 添加鼠标响应事件
    private class AlgoMouseListener extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e) {
           // todo 鼠标按压事件
        }
    }
}
