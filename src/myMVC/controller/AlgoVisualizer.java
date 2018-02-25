package myMVC.controller;

import myMVC.model.MazeData;
import myMVC.model.Position;
import myMVC.model.RandomQueue;
import myMVC.tools.AlgoVisHelper;
import myMVC.view.AlgoFrame;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class AlgoVisualizer {
    private static int DELAY = 10;

    private MazeData data;

    // 视图层
    private AlgoFrame frame;

    // 上 右 顺时针方向
    private static final int d[][] = {{-1,0},{0,1},{1,0},{0,-1}};

    // todo 设置自定义变量
    private static int blockSide = 8;


    // 生成迷宫
    public AlgoVisualizer(int N,int M){
        // todo 初始化数据
        data = new MazeData(N,M);
        int screenWidth = data.getM() * blockSide;
        int screenHeight = data.getN() * blockSide;
        // 初始化视图frame
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Random Maze Generation Visualization",
                    screenWidth, screenHeight);
            // TODO: 根据情况决定是否加入键盘鼠标事件监听器
            frame.addKeyListener(new AlgoKeyListener());
            frame.addMouseListener(new AlgoMouseListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }

    private void run(){
        setData(-1, -1);
        RandomQueue<Position> queue = new RandomQueue<>();
        Position first = new Position(data.getEntranceX(),data.getEntranceY() + 1);
        queue.add(first);
        data.visited[first.getX()][first.getY()] = true;
        // 开启入口迷雾
        data.openMist(first.getX(), first.getY());
        while (queue.size() != 0){
            // 将栈顶位置拿出来
            Position curPos = queue.remove();
            for(int i = 0;i < 4;i++){
                int newX = curPos.getX() + d[i][0]*2;
                int newY = curPos.getY() + d[i][1]*2;

                if(data.inArea(newX,newY) &&
                        !data.visited[newX][newY] &&
                        data.maze[newX][newY] == MazeData.ROAD){
                    queue.add(new Position(newX,newY));
                    data.visited[newX][newY] = true;
                    // 开启迷雾
                    data.openMist(newX,newY);
                    setData(curPos.getX() + d[i][0], curPos.getY() + d[i][1]);
                }
            }
        }
        setData(-1, -1);
    }


    // 设置要绘制的数据
    private void setData(int x,int y){
        if(data.inArea(x,y)){
            data.maze[x][y] = MazeData.ROAD;
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
