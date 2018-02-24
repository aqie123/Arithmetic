package myMVC.controller;

import maze.processMaze.MazeData;
import myMVC.model.Position;
import myMVC.tools.AlgoVisHelper;
import myMVC.view.AlgoFrame;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Stack;


public class AlgoVisualizer {
    private static int DELAY = 30;

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
        setData(-1, -1, false);
        Stack<Position> stack = new Stack<>();
        Position entrance = new Position(data.getEntranceX(),data.getEntranceY());
        stack.push(entrance);
        data.visited[entrance.getX()][entrance.getY()] = true;

        boolean isSolved = false;
        while(!stack.empty()){
            // 从栈顶取出一个位置
            Position curPos = stack.pop();
            // 当前取出元素绘制在路径上
            setData(curPos.getX(),curPos.getY(),true);
            // 是出口，找到迷宫的解
            if(curPos.getX() == data.getExitX() && curPos.getY() == data.getExitY()){
                isSolved = true;
                // 通过终点找到迷宫的解
                findPath(curPos);
                break;
            }
            for(int i = 0;i < 4;i++){
                int newX = curPos.getX() + d[i][0];
                int newY = curPos.getY() + d[i][1];

                /*
                    没有越界
                    没有被访问过
                    是可以走的路
                 */
                if(data.inArea(newX,newY) &&
                        !data.visited[newX][newY] &&
                        data.getMaze(newX,newY) == MazeData.ROAD){
                    // 记录元素从哪个元素来的
                    stack.push(new Position(newX,newY,curPos));
                    data.visited[newX][newY] = true;
                }
            }
        }

        if(!isSolved){
            System.out.println("The maze has no Solution!");
        }

        setData(-1, -1, false);

    }

    // 从终点开始向前找最终路径
    private void findPath(Position des){
        Position cur = des;
        while (cur != null){
            data.result[cur.getX()][cur.getY()] = true;
            cur = cur.getPrev();
        }
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
