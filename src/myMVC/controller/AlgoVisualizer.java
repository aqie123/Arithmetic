package myMVC.controller;

import myMVC.model.MineSweeperData;
import myMVC.tools.AlgoVisHelper;
import myMVC.view.AlgoFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class AlgoVisualizer {
    private static int DELAY = 5;

    private MineSweeperData data;

    // 视图层
    private AlgoFrame frame;

    // 上 右 顺时针方向
    private static final int d[][] = {{-1,0},{0,1},{1,0},{0,-1}};

    // todo 设置自定义变量
    private static int blockSide = 32;  // 每个图片是32像素


    // 生成迷宫
    public AlgoVisualizer(int N,int M,int MineNumber){
        // todo 初始化数据
        data = new MineSweeperData(N,M,MineNumber);
        int screenWidth = data.getM() * blockSide;
        int screenHeight = data.getN() * blockSide;
        // 初始化视图frame
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Mine Sweeper",
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
       setData(false,-1,-1);
    }


    // 设置要绘制的数据
    private void setData(boolean isLeftClicked,int x,int y){
        if(data.inArea(x, y)) {
            if(isLeftClicked){
                data.open[x][y] = true;
            }else{
                data.flags[x][y] = !data.flags[x][y];
            }
        }
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }


    // 添加键盘监听事件
    private class AlgoKeyListener extends KeyAdapter{
        @Override
        public void keyReleased(KeyEvent event){
        }
    }

    // 添加鼠标响应事件
    private class AlgoMouseListener extends MouseAdapter{
        @Override
        // public void mouseReleased(MouseEvent event){
        public void mousePressed(MouseEvent event){
            event.translatePoint(
               -(int)(frame.getBounds().width - frame.getCanvasWidth()),
               -(int)(frame.getBounds().height - frame.getCanvasHeight())
            );
            Point pos = event.getPoint();
            int w = frame.getCanvasWidth()/data.getM();
            int h = frame.getCanvasHeight()/data.getN();

            int x = pos.y / h;
            int y = pos.x / w;
            // System.out.println(x + " , " + y);
            if(SwingUtilities.isLeftMouseButton(event)){
                setData(true,x,y);
            }else if(SwingUtilities.isRightMouseButton(event)){
                setData(false,x,y);
            }
        }
    }
}
